package com.astra.dewa.web.command.action;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.SuratPenaltyStock;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SuratPenaltyStockLocalServiceUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.TsidUtils;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SURAT_PENALTY_STOCK,
            "mvc.command.name=/surat-penalty-stock-action"
      },
      service = MVCResourceCommand.class
)
public class SuratPenaltyStockActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(SuratPenaltyStockActionCommand.class);

   private ResourceRequest resourceRequest;
   private UploadPortletRequest uploadPortletRequest;
   private ThemeDisplay themeDisplay;
   private ServiceContext serviceContext;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.resourceRequest = resourceRequest;
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      this.themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      this.user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            long entryId = ParamUtil.getLong(uploadPortletRequest, "entryId");
            int dealerCode = ParamUtil.getInteger(uploadPortletRequest, "dealerCode");
            String judul = ParamUtil.getString(uploadPortletRequest, "judul");
            int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
            String periodeReviewId = ParamUtil.getString(uploadPortletRequest, "periodeReviewId");
            long fileId = ParamUtil.getLong(uploadPortletRequest, "suratPenaltyStockFileId");
            String fileName = ParamUtil.getString(uploadPortletRequest, "suratPenaltyStockFileName");
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

            Date date = new Date();
            SuratPenaltyStock suratPenaltyStock = null;
            if (crudType.equalsIgnoreCase(CREATE)) {
               suratPenaltyStock = SuratPenaltyStockLocalServiceUtil.createSuratPenaltyStock(TsidUtils.longTsid());
            } else {
               suratPenaltyStock = SuratPenaltyStockLocalServiceUtil.getSuratPenaltyStock(entryId);
            }
            suratPenaltyStock.setDealerId(dealerCode);
            suratPenaltyStock.setFileId(fileId);
            suratPenaltyStock.setJudul(judul);
            suratPenaltyStock.setTahun(tahun);
            suratPenaltyStock.setPeriode(periodeReviewId);
            suratPenaltyStock.setRowStatus(true);

            if (crudType.equalsIgnoreCase(DELETE)) {
               suratPenaltyStock.setFileName(fileName);
               jsonObject = delete();
            } else if (crudType.equalsIgnoreCase(CREATE)) {
               jsonObject = create(suratPenaltyStock, date, tahun, dealerCode, periodeReviewId, crudType, fileId);

            } else if (crudType.equalsIgnoreCase(UPDATE)) {
               SuratPenaltyStock sps = SuratPenaltyStockLocalServiceUtil.getSuratPenaltyStock(entryId);

               if (sps.getTahun() != tahun || !sps.getPeriode().equalsIgnoreCase(periodeReviewId) || sps.getDealerId() != dealerCode) {
                  jsonObject = create(suratPenaltyStock, date, tahun, dealerCode, periodeReviewId, crudType, fileId);
               } else {
                  String name = sps.getFileName();
                  suratPenaltyStock.setFileName(name);
                  jsonObject = update(suratPenaltyStock, date, fileId);
               }
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e.getMessage());
            jsonObject = ERROR(500, "Internal Server Error!");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(SuratPenaltyStock suratPenaltyStock, Date date, Integer tahun, Integer dealerCode, String periodeReviewName, String crudType, long fileId) {
      try {
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

         if (isExist(periodeReviewName, tahun, dealerCode)) {
            jsonObject = WARNING("You are not allowed to upload more than one file within a single period, year and same dealer.");
         } else {
            Dealer dealer = DealerLocalServiceUtil.getDealer(dealerCode);
            String name = "Surat Penalty Stock-" + dealer.getName() + "-" + tahun + ".pdf";
            suratPenaltyStock.setFileName(name);
            suratPenaltyStock.setModifiedDate(date);
            suratPenaltyStock.setModifiedBy(user.getScreenName());

            if (crudType.equalsIgnoreCase("create")) {
               suratPenaltyStock.setCreatedDate(date);
               suratPenaltyStock.setCreatedBy(user.getScreenName());
            }

            SuratPenaltyStockLocalServiceUtil.updateSuratPenaltyStock(suratPenaltyStock);
            jsonObject = SUCCESS("Data tersimpan", String.valueOf(suratPenaltyStock.getId()));
         }

         return jsonObject;
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(SuratPenaltyStock suratPenaltyStock, Date date, long fileId) {
      try {
         suratPenaltyStock.setModifiedDate(date);
         suratPenaltyStock.setModifiedBy(user.getScreenName());

         //for move file from folder temp to folder Surat Penalty Stock
         FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, DewaWebKeys.SURAT_PENALTY_STOCK_MENU);

         SuratPenaltyStockLocalServiceUtil.updateSuratPenaltyStock(suratPenaltyStock);
         return SUCCESS("Data terupdate.", String.valueOf(suratPenaltyStock.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete() {
      try {
         Long entryId = ParamUtil.getLong(uploadPortletRequest, "entryId");
         SuratPenaltyStock suratPenaltyStock = SuratPenaltyStockLocalServiceUtil.getSuratPenaltyStock(entryId);
         suratPenaltyStock.setRowStatus(false);
         suratPenaltyStock.setModifiedDate(new Date());
         suratPenaltyStock.setModifiedBy(user.getScreenName());

         SuratPenaltyStockLocalServiceUtil.updateSuratPenaltyStock(suratPenaltyStock);
         return SUCCESS("Data terupdate.", String.valueOf(entryId));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(String periodeReview, Integer tahun, Integer dealerId) {
      DynamicQuery query = SuratPenaltyStockLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("Periode", periodeReview));
      query.add(RestrictionsFactoryUtil.eq("Tahun", tahun));
      List<SuratPenaltyStock> suratPenaltyStocks = SuratPenaltyStockLocalServiceUtil.dynamicQuery(query);
      return suratPenaltyStocks.size() > 0;
   }
}
