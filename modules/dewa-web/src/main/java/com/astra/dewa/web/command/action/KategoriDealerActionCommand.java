package com.astra.dewa.web.command.action;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.KategoriDealer;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.KategoriDealerLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.TsidUtils;
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
            "javax.portlet.name=" + DewaWebPortletKeys.KATEGORI_DEALER,
            "mvc.command.name=/kategori-dealer-action"
      },
      service = MVCResourceCommand.class
)
public class KategoriDealerActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(KategoriDealerActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

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
            Integer dealerCode = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
            String judul = ParamUtil.getString(uploadPortletRequest, "judul");
            int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
            String periodeReviewName = ParamUtil.getString(uploadPortletRequest, "periodeReviewName");
            long fileId = ParamUtil.getLong(uploadPortletRequest, "kategoriDealerFileId");
            String fileName = ParamUtil.getString(uploadPortletRequest, "kategoriDealerFileName");
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

            Date date = new Date();

            KategoriDealer kategoriDealer = null;
            if (crudType.equalsIgnoreCase(CREATE)) {
               kategoriDealer = KategoriDealerLocalServiceUtil.createKategoriDealer(TsidUtils.longTsid());
            } else {
               kategoriDealer = KategoriDealerLocalServiceUtil.getKategoriDealer(entryId);
            }

            kategoriDealer.setDealerId(Integer.parseInt(String.valueOf(dealerCode)));
            kategoriDealer.setJudul(judul);
            kategoriDealer.setTahun(tahun);
            kategoriDealer.setPeriodeReview(periodeReviewName);
            kategoriDealer.setFileId(fileId);
            kategoriDealer.setRowStatus(true);

            if (crudType.equalsIgnoreCase(DELETE)) {
               kategoriDealer.setFileName(fileName);
               jsonObject = delete();
            } else if (crudType.equalsIgnoreCase(CREATE)) {

               jsonObject = create(kategoriDealer, date, tahun, dealerCode, periodeReviewName, crudType, fileId);

            } else if (crudType.equalsIgnoreCase(UPDATE)) {
               KategoriDealer kd = KategoriDealerLocalServiceUtil.getKategoriDealer(entryId);

               if (kd.getTahun() != tahun || !kd.getPeriodeReview().equalsIgnoreCase(periodeReviewName) || kd.getDealerId() != dealerCode) {
                  jsonObject = create(kategoriDealer, date, tahun, dealerCode, periodeReviewName, crudType, fileId);
               } else {
                  String name = kd.getFileName();
                  kategoriDealer.setFileName(name);
                  jsonObject = update(kategoriDealer, date, fileId);
               }
            }
            LOG.info("==== Kategori Dealer ====");
            LOG.info(kategoriDealer.toString());
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else {
            jsonObject = ERROR(500, "Internal Server Error");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(KategoriDealer kategoriDealer, Date date, Integer tahun, Integer dealerCode, String periodeReviewName, String crudType, long fileId) {
      try {
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

         if (isExist(periodeReviewName, tahun, dealerCode)) {
            jsonObject = WARNING("You are not allowed to upload more than one file within a single period, year and same dealer.");
         } else {
            Dealer dealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(String.valueOf(dealerCode)));
            String name = "Kategori Dealer-" + dealer.getName() + "-" + tahun + ".pdf";
            kategoriDealer.setFileName(name);
            kategoriDealer.setModifiedDate(date);
            kategoriDealer.setModifiedBy(user.getScreenName());

            if (crudType.equalsIgnoreCase("create")) {
               kategoriDealer.setCreatedDate(date);
               kategoriDealer.setCreatedBy(user.getScreenName());
            }

            KategoriDealerLocalServiceUtil.updateKategoriDealer(kategoriDealer);
            jsonObject = SUCCESS("Data tersimpan", String.valueOf(kategoriDealer.getId()));
         }

         return jsonObject;
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(KategoriDealer kategoriDealer, Date date, long fileId) {
      try {
         kategoriDealer.setModifiedDate(date);
         kategoriDealer.setModifiedBy(user.getScreenName());

         // for move file from folder temp to folder kategori dealer
         // FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, DewaWebKeys.KATERGORI_DEALER_MENU);

         KategoriDealerLocalServiceUtil.updateKategoriDealer(kategoriDealer);
         return SUCCESS("Data terupdate.", String.valueOf(kategoriDealer.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete() {
      try {
         Long entryId = ParamUtil.getLong(uploadPortletRequest, "entryId");
         KategoriDealer kategoriDealer = KategoriDealerLocalServiceUtil.getKategoriDealer(entryId);
         kategoriDealer.setRowStatus(false);
         kategoriDealer.setModifiedDate(new Date());
         kategoriDealer.setModifiedBy(user.getScreenName());

         KategoriDealerLocalServiceUtil.updateKategoriDealer(kategoriDealer);
         return SUCCESS("Data terupdate.", String.valueOf(entryId));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(String periodeReview, Integer tahun, Integer dealerId) {
      DynamicQuery query = KategoriDealerLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("rowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("PeriodeReview", periodeReview));
      query.add(RestrictionsFactoryUtil.eq("Tahun", tahun));
      List<KategoriDealer> kategoriDealers = KategoriDealerLocalServiceUtil.dynamicQuery(query);
      return kategoriDealers.size() > 0;
   }
}