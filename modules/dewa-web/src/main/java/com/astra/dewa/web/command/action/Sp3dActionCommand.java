package com.astra.dewa.web.command.action;

import com.astra.dewa.model.SP3D;
import com.astra.dewa.service.SP3DLocalServiceUtil;
import com.astra.dewa.utils.CRUDActionKeys;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.LogUtil;
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
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SP3D_PORTLET,
            "mvc.command.name=/sp3d-action"
      },
      service = MVCResourceCommand.class
)
public class Sp3dActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(Sp3dActionCommand.class);
   private ResourceRequest resourceRequest;
   private UploadPortletRequest uploadPortletRequest;
   private ThemeDisplay themeDisplay;
   private ServiceContext serviceContext;
   private User user;
   private String fileName;
   private int dealerId;
   private String message;

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
            int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
            int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
            String periode = ParamUtil.getString(uploadPortletRequest, "periode").trim();
            dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
            int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
            long fileId = ParamUtil.getLong(uploadPortletRequest, "sp3dFileId");
            fileName = ParamUtil.getString(uploadPortletRequest, "sp3dFileName").trim();
            String filePath = ParamUtil.getString(uploadPortletRequest, "sp3dFilePath").trim();
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType".trim());

            Date date = new Date();
            SP3D sp3d = null;

            boolean isFileExist = isExist(entryId, tahun, periode, dealerId);
            if (crudType.equalsIgnoreCase(CREATE)) {
               sp3d = SP3DLocalServiceUtil.createSP3D(entryId);
            } else {
               sp3d = SP3DLocalServiceUtil.getSP3D(entryId);
            }

            if (crudType.equalsIgnoreCase(DELETE)) {
               sp3d.setFileName(fileName);
            } else {
               String newFileName = "SP3D-" + periode + "-" + tahun + "." + FileUtil.getExtensionByStringHandling(fileName).get();
               sp3d.setFileName(newFileName);
            }

            sp3d.setTahun(tahun);
            sp3d.setBulan(periode);
            sp3d.setDealerId(dealerId);
            sp3d.setFileId(fileId);
            sp3d.setFilePath(filePath);

            if (crudType.equalsIgnoreCase(DELETE)) {
               jsonObject = delete();
            } else if (crudType.equalsIgnoreCase(CREATE)) {
               jsonObject = create(sp3d, date, isFileExist, fileId, entryId, fileName);
            } else if (crudType.equalsIgnoreCase(UPDATE)) {
               jsonObject = update(sp3d, date, isFileExist, fileId, entryId, fileName);
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

   private JSONObject create(SP3D sp3d, Date date, boolean isFileExist, long fileId, int entryId, String fileName) {
      try {
         if (isFileExist) {
            LogUtil.logStatus(0, entryId, CRUDActionKeys.CREATE, DewaWebKeys.SP3D_MENU, dealerId, fileName, message);
            return WARNING("You are not allowed to upload more than one file within a single region, year, periode and same category.");
         }
         sp3d.setCreatedDate(date);
         sp3d.setRowStatus(true);
         sp3d.setCreatedBy(user.getScreenName());
         sp3d.setModifiedDate(date);
         sp3d.setModifiedBy(user.getScreenName());
         SP3DLocalServiceUtil.updateSP3D(sp3d);
//         FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, DewaWebKeys.SP3D_MENU);
         LogUtil.logStatus(1, entryId, CRUDActionKeys.CREATE, DewaWebKeys.SP3D_MENU, dealerId, fileName, message);
         return SUCCESS("Data tersimpan", String.valueOf(sp3d.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(SP3D sp3d, Date date, boolean isFileExist, long fileId, int entryId, String fileName) {
      try {
         sp3d.setModifiedDate(date);
         sp3d.setModifiedBy(user.getScreenName());
         if (isFileExist) {
            LogUtil.logStatus(0, entryId, CRUDActionKeys.UPDATE, DewaWebKeys.SP3D_MENU, dealerId, fileName, message);
            return WARNING("You are not allowed to upload more than one file within a single region, year, periode and same category.");
         }
         FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, DewaWebKeys.SP3D_MENU);
         SP3DLocalServiceUtil.updateSP3D(sp3d);
         LogUtil.logStatus(1, entryId, CRUDActionKeys.UPDATE, DewaWebKeys.SP3D_MENU, dealerId, fileName, message);
         return SUCCESS("Data terupdate.", String.valueOf(sp3d.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete() {
      try {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         SP3D sp3d = SP3DLocalServiceUtil.getSP3D(entryId);
         sp3d.setRowStatus(false);
         SP3DLocalServiceUtil.updateSP3D(sp3d);
         LogUtil.logStatus(1, entryId, CRUDActionKeys.DELETE, DewaWebKeys.SP3D_MENU, dealerId, fileName, message);
         return SUCCESS("Data terupdate.", String.valueOf(entryId));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(int entryId, Integer tahun, String periode, int dealerId) {
      DynamicQuery query = SP3DLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Tahun", tahun));
      query.add(RestrictionsFactoryUtil.eq("Bulan", periode));
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<SP3D> sp3ds = SP3DLocalServiceUtil.dynamicQuery(query);
      LOG.debug(sp3ds);
      if (!sp3ds.isEmpty()) {
         return !(sp3ds.get(0).getId() == entryId);
      }
      return false;
   }
}