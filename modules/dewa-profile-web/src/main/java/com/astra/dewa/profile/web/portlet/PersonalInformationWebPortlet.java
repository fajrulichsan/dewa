package com.astra.dewa.profile.web.portlet;

import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.profile.web.constants.DewaProfileWebPortletKeys;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * psmmutia0113
 */
@Component(
      immediate = true,
      property = {
            "com.liferay.portlet.display-category=Dewa UI",
            "com.liferay.portlet.header-portlet-css=/assets/css/main.css",
            "com.liferay.portlet.instanceable=false",
            "javax.portlet.display-name=Personal Information",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/personal-information/view.jsp",
            "javax.portlet.name=" + DewaProfileWebPortletKeys.PERSONAL_INFORMATION_WEB,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)

public class PersonalInformationWebPortlet extends MVCPortlet {
   private final Log LOG = LogFactoryUtil.getLog(PersonalInformationWebPortlet.class);
   private ThemeDisplay themeDisplay;
   private User user;

   @Override
   public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
      this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      JSONArray jsonRoles = JSONFactoryUtil.createJSONArray();

      try {
         this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
         UsersDealers usersDealers = UsersDealersLocalServiceUtil.findUsersDealersIdFromUserId(this.user.getUserId(), true);

         String photoUrl = "";

         if (Validator.isNotNull(usersDealers.getPhotoFileId())) {
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(usersDealers.getPhotoFileId());
            photoUrl = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                    themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();
         }

         List<UserRoleType> userRoles = RoleDealerUtils.getUserRoles(this.user.getUserId());

         for (UserRoleType data : userRoles) {
            JSONObject role = JSONFactoryUtil.createJSONObject()
                  .put("id", data.getId())
                  .put("name", RoleUtils.getRoleName(data.getRoleId()));
            jsonRoles.put(role);
         }

         request.setAttribute("userFullName", usersDealers.getFullName());
         request.setAttribute("roles", jsonRoles);
         request.setAttribute("photoUrl", photoUrl);
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }
      super.render(request, response);
   }

   @Override
   public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
      String state = ParamUtil.getString(request, "state");
      PrintWriter writer = response.getWriter();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         if (state.equalsIgnoreCase("post")) {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            long fileId = ParamUtil.getLong(uploadPortletRequest, "profileFileId");
            String userFullName = ParamUtil.getString(uploadPortletRequest, "userFullName");

            if (!InputValidationUtils.isBasicCharacter(userFullName)) {
               throw new SystemException("Nama lengkap hanya boleh diisi dengan karakter .,/()@&_-");
            }

            try {
               ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
               if (Validator.isNotNull(fileId)) {
                  DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileId);
                  String extensionFile = fileEntry.getExtension();
                  if (extensionFile.equalsIgnoreCase("PNG") ||
                        extensionFile.equalsIgnoreCase("JPG") ||
                        extensionFile.equalsIgnoreCase("JPEG")) {

                     FileUtil.moveIntoMenuFolder(themeDisplay, request, serviceContext, fileId, DewaProfileWebPortletKeys.PROFILE_FOLDER);
                  } else {
                     jsonObject = WARNING("Periksa kembali jenis file yang diinput");
                  }
               }

               UsersDealers usersDealers = UsersDealersLocalServiceUtil.findUsersDealersIdFromUserId(this.user.getUserId(), true);
               if (Validator.isNotNull(fileId)) {
                  usersDealers.setPhotoFileId(fileId);
               }
               usersDealers.setFullName(userFullName);
               usersDealers.setModifiedBy(this.user.getScreenName());
               usersDealers.setModifiedDate(new Date());
               UsersDealersLocalServiceUtil.updateUsersDealers(usersDealers);

               jsonObject = SUCCESS("Data tersimpan", "");

            } catch (Exception e) {
               LOG.error(e.getMessage(), e);
            }
            writer.print(jsonObject.toJSONString());

         } else if (state.equalsIgnoreCase("uploadFile")) {
            int acknowledge = 1;
            String status = "success";
            String message = "";

            List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
            List<String> allowedTypes = Arrays.asList("image/jpeg", "image/png");

            String fileName = ParamUtil.getString(request, "file-name");
            String mimeType = uploadPortletRequest.getContentType("file-upload");
            String fileExtension = FileUtil.getExtensionByStringHandling(fileName).get();
            File file = uploadPortletRequest.getFile("file-upload");
            HashMap<String, String> payload = new HashMap<>();

            // File Validation
            if (!allowedExtensions.contains(fileExtension)) {
               message = "Invalid file extension. Only .jpg, .jpeg, .png files are allowed.";
            }
            if (!allowedTypes.contains(mimeType)) {
               message = "Invalid mime type. Only .jpg, .jpeg, .png files are allowed.";
            }

            fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "." + FileUtil.getExtensionByStringHandling(fileName).get();

            try {
               payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, request, DewaProfileWebPortletKeys.TEMP_FOLDER);
               LOG.info(">>>>>>>>>>>>>Masuk ke folder Temp<<<<<<<<<<<<<<<<<<");
            } catch (Exception e) {
               acknowledge = 0;
               status = "error";
               message = e.getMessage();

               LOG.error(e.getMessage());
               LOG.error(e.getLocalizedMessage());
            }

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("acknowledge", acknowledge);
            jsonObject.put("status", status);
            jsonObject.put("message", message);
            jsonObject.put("fileURL", payload.get("URL"));
            jsonObject.put("fileID", payload.get("ID"));
            jsonObject.put("fileName", payload.get("fileName"));

            writer.print(jsonObject.toJSONString());
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonMessage = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonMessage = WARNING(e.getMessage());
         } else {
            jsonMessage = WARNING("Bad request!");
         }
         LOG.error(e.getLocalizedMessage(), e);
         writer.print(jsonMessage.toJSONString());
      }

      super.serveResource(request, response);
   }
}
