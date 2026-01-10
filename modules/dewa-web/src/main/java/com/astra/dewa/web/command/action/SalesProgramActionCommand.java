package com.astra.dewa.web.command.action;

import com.astra.dewa.model.SalesProgram;
import com.astra.dewa.service.SalesProgramLocalServiceUtil;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_PROGRAM,
            "mvc.command.name=/sales-program-action"
      },
      service = MVCResourceCommand.class
)
public class SalesProgramActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(SalesProgramActionCommand.class);
   private ResourceRequest resourceRequest;
   private ThemeDisplay themeDisplay;
   private ServiceContext serviceContext;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      this.resourceRequest = resourceRequest;
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
            int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
            String periode = ParamUtil.getString(uploadPortletRequest, "periode");
            // String fileName = ParamUtil.getString(uploadPortletRequest, "salesProgramFileName");
            long fileId = ParamUtil.getLong(uploadPortletRequest, "salesProgramFileId");
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

            Date date = new Date();
            SalesProgram salesProgram = null;
            if (crudType.equalsIgnoreCase("create")) {
               salesProgram = SalesProgramLocalServiceUtil.createSalesProgram(TsidUtils.longTsid());
            } else {
               salesProgram = SalesProgramLocalServiceUtil.getSalesProgram(entryId);
            }
            salesProgram.setFileId(fileId);
            salesProgram.setRowStatus(true);
            salesProgram.setTahun(tahun);
            salesProgram.setPeriode(periode);

            if (crudType.equalsIgnoreCase("delete")) {
               SalesProgram deleteSP = SalesProgramLocalServiceUtil.getSalesProgram(entryId);
               jsonObject = delete(deleteSP, date);
            } else if (crudType.equalsIgnoreCase("create")) {
               jsonObject = create(salesProgram, date, periode, tahun, crudType, fileId);

            } else if (crudType.equalsIgnoreCase("update")) {
               SalesProgram sp = SalesProgramLocalServiceUtil.getSalesProgram(entryId);

               if (sp.getTahun() != tahun || !sp.getPeriode().equalsIgnoreCase(periode)) {
                  jsonObject = create(salesProgram, date, periode, tahun, crudType, fileId);
               } else {
                  String nameFile = sp.getFileName();
                  salesProgram.setFileName(nameFile);
                  jsonObject = update(salesProgram, date, fileId);
               }
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else {
            LOG.error(e);
            jsonObject = WARNING("Bad request!");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(SalesProgram salesProgram, Date date, String periode, Integer tahun, String crudType, long fileId) {
      try {
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
         int scopePeriode = fileExist(periode, tahun) + 1;

         String renameFile = "Sales Program Indirect-Periode " + periode + " " + scopePeriode + "-" + tahun + ".pdf";
         salesProgram.setFileName(renameFile);
         salesProgram.setCreatedDate(date);
         salesProgram.setCreatedBy(user.getScreenName());
         salesProgram.setModifiedDate(date);
         salesProgram.setModifiedBy(user.getScreenName());

         SalesProgramLocalServiceUtil.updateSalesProgram(salesProgram);
         jsonObject = SUCCESS("Data tersimpan", String.valueOf(salesProgram.getId()));

         return jsonObject;
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(SalesProgram salesProgram, Date date, long fileId) {
      try {
         salesProgram.setModifiedDate(date);
         salesProgram.setModifiedBy(user.getScreenName());

         // Move file from temp to Sales Program folder
         FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, DewaWebKeys.SALES_PROGRAM_MENU);

         SalesProgramLocalServiceUtil.updateSalesProgram(salesProgram);
         return SUCCESS("Data terupdate.", String.valueOf(salesProgram.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(SalesProgram salesProgram, Date date) {
      try {
         salesProgram.setRowStatus(false);
         salesProgram.setModifiedDate(date);
         salesProgram.setModifiedBy(user.getScreenName());

         SalesProgramLocalServiceUtil.updateSalesProgram(salesProgram);
         return SUCCESS("Data terupdate.", String.valueOf(salesProgram.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private Integer fileExist(String periode, Integer tahun) {
      DynamicQuery query = SalesProgramLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Tahun", tahun));
      query.add(RestrictionsFactoryUtil.eq("Periode", periode));
      List<SalesProgram> salesPrograms = SalesProgramLocalServiceUtil.dynamicQuery(query);
      return salesPrograms.size();
   }

   /*
   private String findIdRoleDealer(long userId) {
      DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("UserId", userId));
      List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.dynamicQuery(query);
      return roleDealers.get(0).getId() + "";
   }
   */
}
