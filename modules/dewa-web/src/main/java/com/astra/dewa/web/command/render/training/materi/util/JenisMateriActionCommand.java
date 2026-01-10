package com.astra.dewa.web.command.render.training.materi.util;

import com.astra.dewa.model.JenisMateri;
import com.astra.dewa.service.JenisMateriLocalServiceUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
                "mvc.command.name=/jenis-materi-action"
        },
        service = MVCResourceCommand.class
)
public class JenisMateriActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(JenisMateriActionCommand.class);
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
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            String entryId = ParamUtil.getString(uploadPortletRequest, "entryId");

            /*
            String jenisMateriName = ParamUtil.getString(uploadPortletRequest, "jenisMateriName");
            long imageId = ParamUtil.getLong(uploadPortletRequest, "jenisMateriImageFileId");
            String imageName = ParamUtil.getString(uploadPortletRequest, "jenisMateriImageFileName");
            String imagePath = ParamUtil.getString(uploadPortletRequest, "jenisMateriImageFilePath");

            // Validasi format file image
            if (imageName != null && !imageName.isEmpty()) {
               String lowerCaseImageName = imageName.toLowerCase();
               if (!(lowerCaseImageName.endsWith(".jpg") || lowerCaseImageName.endsWith(".jpeg") ||
                     lowerCaseImageName.endsWith(".png") || lowerCaseImageName.endsWith(".gif"))) {
                  jsonObject = ERROR("Upload File Gagal. Hanya file dengan format jpg, jpeg, png, dan gif yang diperbolehkan.");
                  ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
                  return;
               }
            }

            if (!InputValidationUtils.isBasicCharacter(jenisMateriName)) {
               throw new SystemException("Mohon tidak menggunakan karakter khusus pada judul");
            }

            FileEntry imageFile = DLAppLocalServiceUtil.getFileEntry(imageId);
            LOG.debug(imageFile);
             */

            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
            JenisMateri jenisMateri = null;

            switch (crudType) {
               case CREATE:
                  jenisMateri = JenisMateriLocalServiceUtil.createJenisMateri(0);
                  setData(jenisMateri);
                  jsonObject = create(jenisMateri);
                  break;
               case UPDATE:
                  jenisMateri = JenisMateriLocalServiceUtil.getJenisMateri(Integer.parseInt(entryId));
                  setData(jenisMateri);
                  jsonObject = update(jenisMateri);
                  break;
               case DELETE:
                  jenisMateri = JenisMateriLocalServiceUtil.getJenisMateri(Integer.parseInt(entryId));
                  jsonObject = delete(jenisMateri);
                  break;
               default:
                  throw new SystemException("Bad request");
            }

            /*
            if (crudType.equalsIgnoreCase("create")) {
               jenisMateri = JenisMateriLocalServiceUtil.createJenisMateri(0);
            } else {
               jenisMateri = JenisMateriLocalServiceUtil.getJenisMateri(Integer.parseInt(entryId));
            }
            jenisMateri.setName(jenisMateriName);
            jenisMateri.setImageId(imageId);
            jenisMateri.setImageName(imageName);
            jenisMateri.setImagePath(imagePath);

            if (crudType.equalsIgnoreCase("delete")) {
               jsonObject = delete();
            } else if (crudType.equalsIgnoreCase("create")) {
               jsonObject = create(jenisMateri);
            } else if (crudType.equalsIgnoreCase("update")) {
               jsonObject = update(jenisMateri);
            }

             */
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else if (e instanceof SystemException) {
            LOG.error(e);
            jsonObject = ERROR(400, e.getMessage());
         } else {
            jsonObject = ERROR(500, "Internal Server Error");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private void setData(JenisMateri jenisMateri) throws SystemException {
      String jenisMateriName = ParamUtil.getString(uploadPortletRequest, "jenisMateriName");
      long imageId = ParamUtil.getLong(uploadPortletRequest, "jenisMateriImageFileId");
      String imageName = ParamUtil.getString(uploadPortletRequest, "jenisMateriImageFileName");
      String imagePath = ParamUtil.getString(uploadPortletRequest, "jenisMateriImageFilePath");

      jenisMateri.setName(jenisMateriName);
      jenisMateri.setImageId(imageId);
      jenisMateri.setImageName(imageName);
      jenisMateri.setImagePath(imagePath);

      List<String> allowedExtensions = Arrays.asList("gif", "jpg", "jpeg", "png");

      // Validasi format file image
      if (imageName != null && !imageName.isEmpty()) {
         String lowerCaseImageName = imageName.toLowerCase();
         String fileExtension = FileUtil.getExtensionByStringHandling(lowerCaseImageName).get();
         if (!allowedExtensions.contains(fileExtension)) {
            throw new SystemException("Hanya file dengan format jpg, jpeg, png, dan gif yang diperbolehkan.");
         }
      }

      if (!InputValidationUtils.isBasicCharacter(jenisMateriName)) {
         throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_-");
      }

      try {
         FileEntry imageFile = DLAppLocalServiceUtil.getFileEntry(imageId);
         LOG.debug(imageFile);
      } catch (PortalException pe) {
         LOG.debug(pe);
      }
   }

   private JSONObject create(JenisMateri jenisMateri) {
      try {
         if (isExist(jenisMateri.getName())) {
            return JSONResponseFormatUtil.ERROR("Data already exist.");
         }
         Date date = new Date();
         jenisMateri.setRowStatus(true);
         jenisMateri.setCreatedDate(date);
         jenisMateri.setCreatedBy(user.getScreenName());
         jenisMateri.setModifiedDate(date);
         jenisMateri.setModifiedBy(user.getScreenName());
         JenisMateri jm = JenisMateriLocalServiceUtil.addJenisMateri(jenisMateri);
         JSONObject dto = JSONFactoryUtil.createJSONObject()
                 .put("id", jm.getId())
                 .put("text", jm.getName());
         return SUCCESS("Data tersimpan", String.valueOf(jenisMateri.getId()), dto);
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(JenisMateri jenisMateri) {
      try {
         Date date = new Date();
         jenisMateri.setModifiedDate(date);
         jenisMateri.setModifiedBy(user.getScreenName());
         JenisMateriLocalServiceUtil.updateJenisMateri(jenisMateri);
         return SUCCESS("Data terupdate.", String.valueOf(jenisMateri.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(JenisMateri jenisMateri) {
      try {
//         String entryId = ParamUtil.getString(uploadPortletRequest, "entryId");
//         JenisMateri jenisMateri = JenisMateriLocalServiceUtil.getJenisMateri(Integer.parseInt(entryId));
         jenisMateri.setRowStatus(false);
         jenisMateri.setModifiedBy(user.getScreenName());
         jenisMateri.setModifiedDate(new Date());
         JenisMateriLocalServiceUtil.updateJenisMateri(jenisMateri);
         return SUCCESS("Data terupdate.", String.valueOf(jenisMateri.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(String name) {
      DynamicQuery query = JenisMateriLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      List<JenisMateri> jenisMateris = JenisMateriLocalServiceUtil.dynamicQuery(query);
      return jenisMateris.size() > 0;
   }

}
