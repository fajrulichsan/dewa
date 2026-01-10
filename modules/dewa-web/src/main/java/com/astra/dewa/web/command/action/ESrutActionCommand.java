package com.astra.dewa.web.command.action;

import com.astra.dewa.model.ESrut;
import com.astra.dewa.service.ESrutLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.LogUtil.logStatus;
import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.E_SRUT,
            "mvc.command.name=/e-srut-action"
      },
      service = MVCResourceCommand.class
)
public class ESrutActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(ESrutActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private User user;
   private int entryId;
   private String fileName;
   private int dealerId;
   private String periodParam;
   private String message;
//   private static final String MENU = "E-" + DewaWebKeys.E_SRUT_MENU;

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
            this.entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
            this.dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
            this.periodParam = ParamUtil.getString(uploadPortletRequest, "periode");
            long fileId = ParamUtil.getLong(uploadPortletRequest, "eSrutFileId");
            this.fileName = ParamUtil.getString(uploadPortletRequest, "eSrutFileName");
            String filePath = ParamUtil.getString(uploadPortletRequest, "eSrutFilePath");
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

            Date date = new Date();
            Date periodDate = null;

            if (!crudType.equalsIgnoreCase(DELETE)) {
               try {
                  SimpleDateFormat sdf = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_SLASH);
                  periodDate = sdf.parse(periodParam);
               } catch (ParseException e) {
                  message = e.getMessage();
                  logStatus(0, entryId, crudType, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, message);
                  jsonObject = ERROR(400, message);
               }
            }

            ESrut eSrut = null;

            if (crudType.equalsIgnoreCase(CREATE)) {
               eSrut = ESrutLocalServiceUtil.createESrut(entryId);
               eSrut.setFileId(fileId);
            } else {
               eSrut = ESrutLocalServiceUtil.getESrut(entryId);
            }

            eSrut.setDealerId(dealerId);
            eSrut.setFilePath(filePath);
            eSrut.setPeriodDate(periodDate);
            eSrut.setKeterangan("-");
            eSrut.setRowStatus(true);
            eSrut.setFileName(fileName);

            boolean isFileExist = isFileExist(entryId, dealerId, fileName, periodDate);

            if (crudType.equalsIgnoreCase(CREATE)) {
               jsonObject = create(eSrut, date, isFileExist, fileId);
            } else if (crudType.equalsIgnoreCase(UPDATE)) {
               jsonObject = update(eSrut, date, isFileExist, fileId);
            } else if (crudType.equalsIgnoreCase(DELETE)) {
               jsonObject = delete();
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            message = "Unauthorized request!";
         } else {
            LOG.error(e);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(ESrut eSrut, Date date, boolean isFileExist, long fileId) {
      try {
         eSrut.setCreatedDate(date);
         eSrut.setCreatedBy(user.getScreenName());
         eSrut.setModifiedDate(date);
         eSrut.setModifiedBy(user.getScreenName());
//         FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, "E-" + DewaWebKeys.E_SRUT_MENU);

         if (isFileExist) {
            message = "Duplikat file pada periode yang sama!";
            logStatus(0, entryId, CREATE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, null, periodParam, message);
            return ERROR(409, message);
         }

         ESrutLocalServiceUtil.updateESrut(eSrut);
         entryId = ESrutLocalServiceUtil.getESrutsCount();
         logStatus(1, entryId, CREATE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, null, periodParam, "");
         return SUCCESS("Data berhasil disimpan!", String.valueOf(eSrut.getId()));
      } catch (Exception e) {
         message = e.getMessage();
         logStatus(0, entryId, CREATE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, null, periodParam, message);
         return NOT_SUCCESS(message);
      }
   }

   private JSONObject update(ESrut eSrut, Date date, boolean isFileExist, long fileId) {
      try {
         eSrut.setModifiedDate(date);
         eSrut.setModifiedBy(user.getScreenName());
//         FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, "E-" + DewaWebKeys.E_SRUT_MENU);

         if (isFileExist) {
            message = "Duplikat file pada periode yang sama!";
            logStatus(0, entryId, UPDATE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, null, periodParam, message);
            return ERROR(409, message);
         }

         ESrutLocalServiceUtil.updateESrut(eSrut);
         logStatus(1, entryId, UPDATE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, null, periodParam,"");
         return SUCCESS("Data berhasil diubah!", String.valueOf(eSrut.getId()));
      } catch (Exception e) {
         message = e.getMessage();
         logStatus(0, entryId, UPDATE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, null, periodParam, message);
         return NOT_SUCCESS(message);
      }
   }

   private JSONObject delete() {
      try {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         ESrut eSrut = ESrutLocalServiceUtil.getESrut(entryId);
         Date date = new Date();
         eSrut.setRowStatus(false);
         eSrut.setModifiedDate(date);
         eSrut.setModifiedBy(user.getScreenName());
         ESrutLocalServiceUtil.updateESrut(eSrut);
         fileName = eSrut.getFileName();
         logStatus(1, entryId, DELETE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, "");
         return SUCCESS("Data berhasil dihapus!", String.valueOf(eSrut.getId()));
      } catch (Exception e) {
         message = e.getMessage();
         logStatus(0, entryId, DELETE, DewaWebKeys.E_SRUT_MENU, dealerId, fileName, message);
         return NOT_SUCCESS(message);
      }
   }

   private boolean isFileExist(int entryId, int dealerId, String fileName, Date period) {
      DynamicQuery query = ESrutLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("FileName", fileName));
      query.add(RestrictionsFactoryUtil.eq("PeriodDate", period));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<ESrut> eSrutList = ESrutLocalServiceUtil.dynamicQuery(query);
      if (!eSrutList.isEmpty()) {
         return !(eSrutList.get(0).getId() == entryId);
      }
      return false;
   }
}