package com.astra.dewa.web.command.action;

import com.astra.dewa.model.TrainingMateri;
import com.astra.dewa.model.TrainingMateriLampiran;
import com.astra.dewa.service.TrainingMateriLampiranLocalServiceUtil;
import com.astra.dewa.service.TrainingMateriLocalServiceUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.models.FileData;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.ArrayList;
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
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=/training-materi-action"
      },
      service = MVCResourceCommand.class
)
public class TrainingMateriActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TrainingMateriActionCommand.class);
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
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

         String entryId = ParamUtil.getString(uploadPortletRequest, "entryId");
         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else if (crudType.equalsIgnoreCase(DELETE)) {
            delete(entryId);
         } else {
            int jenisMateriId = ParamUtil.getInteger(uploadPortletRequest, "jenisMateriId");
            int topikMateriId = ParamUtil.getInteger(uploadPortletRequest, "topikMateriId");
            String judulMateri = ParamUtil.getString(uploadPortletRequest, "judulMateri");

            if (!InputValidationUtils.isBasicCharacter(judulMateri)) {
               throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_- pada judul");
            }

            List<String> allowedImageExtensions = Arrays.asList("gif", "jpg", "jpeg", "png");
            List<String> allowedDocumentExtensions = Arrays.asList("pdf", "xlsx", "docx");

            List<String> allowedImageTypes = Arrays.asList("image/gif", "image/jpeg", "image/png");;
            List<String> allowedDocumentTypes = Arrays.asList("application/pdf",
                  "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                  "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            );

            // Lampiran
            long fileId = ParamUtil.getLong(uploadPortletRequest, "trainingMateriOneFileId");
            String fileName = "";
            String filePath = "";
            if (Validator.isNotNull(fileId)) {
               FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
               fileName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
               filePath = themeDisplay.getPortalURL() +
                     themeDisplay.getPathContext() +
                     "/documents/" +
                     themeDisplay.getScopeGroupId() +
                     "/" + fileEntry.getFolderId() +
                     "/" + fileEntry.getFileName();
               String fileMimeType = fileEntry.getMimeType();

               // Validasi format file dokumen
               if (Validator.isNotNull(fileName)) {
                  String lowerCaseFileName = fileName.toLowerCase();
                  String fileExtension = FileUtil.getExtensionByStringHandling(lowerCaseFileName).get();
                  if (!allowedDocumentExtensions.contains(fileExtension)) {
                     throw new SystemException("Hanya format dokumen docx, xlsx, dan pdf yang diperbolehkan.");
                  }
                  if (!allowedDocumentTypes.contains(fileMimeType)) {
                     throw new SystemException("Hanya format dokumen docx, xlsx, dan pdf yang diperbolehkan.");
                  }
               }
            }

//            String fileName = ParamUtil.getString(uploadPortletRequest, "trainingMateriOneFileName");
//            String filePath = ParamUtil.getString(uploadPortletRequest, "trainingMateriOneFilePath");

            String[] fileIds = ParamUtil.getStringValues(uploadPortletRequest, "trainingMateriFileId");
            String[] fileNames = ParamUtil.getStringValues(uploadPortletRequest, "trainingMateriFileName");
            String[] filePaths = ParamUtil.getStringValues(uploadPortletRequest, "trainingMateriFilePath");

            long imageId = ParamUtil.getLong(uploadPortletRequest, "trainingMateriImageFileId");
            FileEntry imageEntry = DLAppLocalServiceUtil.getFileEntry(imageId);
            String imageName = imageEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
            String imagePath = themeDisplay.getPortalURL() +
                  themeDisplay.getPathContext() +
                  "/documents/" +
                  themeDisplay.getScopeGroupId() +
                  "/" + imageEntry.getFolderId() +
                  "/" + imageEntry.getFileName();
            String imageMimeType = imageEntry.getMimeType();

//            String imageName = ParamUtil.getString(uploadPortletRequest, "trainingMateriImageFileName");
//            String imagePath = ParamUtil.getString(uploadPortletRequest, "trainingMateriImageFilePath");

            String[] listDeleted = ParamUtil.getStringValues(uploadPortletRequest, "listDeleted");
            LOG.info("listDeleted : " + Arrays.toString(listDeleted));

            Date date = new Date();
            TrainingMateri materi = null;

            // Validasi format file image
            if (Validator.isNotNull(imageName)) {
               String lowerCaseImageName = imageName.toLowerCase();
               String imageExtension = FileUtil.getExtensionByStringHandling(lowerCaseImageName).get();
               if (!allowedImageExtensions.contains(imageExtension)) {
                  throw new SystemException("Hanya format gambar jpg, jpeg, png, dan gif yang diperbolehkan.");
               }
               if (!allowedImageTypes.contains(imageMimeType)) {
                  throw new SystemException("Hanya format gambar jpg, jpeg, png, dan gif yang diperbolehkan.");
               }
            }

            if (crudType.equalsIgnoreCase(CREATE)) {
               materi = TrainingMateriLocalServiceUtil.createTrainingMateri(0);
            } else {
               materi = TrainingMateriLocalServiceUtil.getTrainingMateri(Integer.parseInt(entryId));
            }
            if (!crudType.equalsIgnoreCase(DELETE)) {
               materi.setJenisMateriId(jenisMateriId);
               materi.setTopikMateriId(topikMateriId);
               materi.setJudulMateri(judulMateri);
               materi.setImageId(imageId);
               materi.setImageName(imageName);
               materi.setImagePath(imagePath);

               List<FileData> fileDatas = new ArrayList<>();
               if (crudType.equalsIgnoreCase(CREATE)) {
                  FileData fileDataOne = new FileData();
                  fileDataOne.setFileId(fileId);
                  fileDataOne.setFileName(fileName);
                  fileDataOne.setFilePath(filePath);
                  fileDatas.add(fileDataOne);
               }
               for (int i = 0; i < fileIds.length; i++) {
                  if (!fileIds[i].isEmpty()) {
                     FileData fileData = new FileData();
                     fileData.setFileId(Long.parseLong(fileIds[i]));
                     fileData.setFileName(fileNames[i]);
                     fileData.setFilePath(filePaths[i]);
                     fileDatas.add(fileData);
                  }
               }
               LOG.info("fileDatas.size() : " + fileDatas.size());

               if (crudType.equalsIgnoreCase(DELETE)) {
                  jsonObject = delete(entryId);
               } else if (crudType.equalsIgnoreCase(CREATE)) {
                  jsonObject = create(materi, date, fileDatas);
               } else if (crudType.equalsIgnoreCase(UPDATE)) {
                  jsonObject = update(materi, date, fileDatas, listDeleted);
               }
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = WARNING("Internal Server Error");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(TrainingMateri materi, Date date, List<FileData> fileDatas) {
      try {
         materi.setRowStatus(true);
         materi.setCreatedDate(date);
         materi.setCreatedBy(user.getScreenName());
         materi.setModifiedDate(date);
         materi.setModifiedBy(user.getScreenName());

         TrainingMateriLocalServiceUtil.updateTrainingMateri(materi);
         fileDatas.forEach(fileData -> {
            TrainingMateriLampiran trainingMateriLampiran = TrainingMateriLampiranLocalServiceUtil.createTrainingMateriLampiran(0);
            trainingMateriLampiran.setTrainingMateriId(materi.getId());
            trainingMateriLampiran.setFileId(fileData.getFileId());
            trainingMateriLampiran.setFileName(fileData.getFileName());
            trainingMateriLampiran.setFilePath(fileData.getFilePath());
            trainingMateriLampiran.setCreatedBy(user.getScreenName());
            trainingMateriLampiran.setModifiedBy(user.getScreenName());
            trainingMateriLampiran.setCreatedDate(date);
            trainingMateriLampiran.setModifiedDate(date);
            TrainingMateriLampiranLocalServiceUtil.addTrainingMateriLampiran(trainingMateriLampiran);
         });
         return SUCCESS("Data tersimpan", String.valueOf(materi.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(TrainingMateri materi, Date date, List<FileData> fileDatas, String[] listDeleted) {
      try {
         materi.setModifiedDate(date);
         materi.setModifiedBy(user.getScreenName());

         TrainingMateriLocalServiceUtil.updateTrainingMateri(materi);
         fileDatas.forEach(fileData -> {
            DynamicQuery query = TrainingMateriLampiranLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("FileId", fileData.getFileId()));
            List<TrainingMateriLampiran> lampiranUpdate = TrainingMateriLampiranLocalServiceUtil.dynamicQuery(query);
            // New Lampiran
            if (lampiranUpdate.isEmpty()) {
               TrainingMateriLampiran lampiranNew = TrainingMateriLampiranLocalServiceUtil.createTrainingMateriLampiran(-1);
               lampiranNew.setTrainingMateriId(materi.getId());
               lampiranNew.setFileId(fileData.getFileId());
               lampiranNew.setFileName(fileData.getFileName());
               lampiranNew.setFilePath(fileData.getFilePath());
               lampiranNew.setCreatedBy(user.getScreenName());
               lampiranNew.setCreatedDate(date);
               lampiranNew.setModifiedDate(date);
               lampiranNew.setModifiedBy(user.getScreenName());
               TrainingMateriLampiranLocalServiceUtil.addTrainingMateriLampiran(lampiranNew);
            } else {
               TrainingMateriLampiran lampiranUpdateTwo = null;
               try {
                  lampiranUpdateTwo = TrainingMateriLampiranLocalServiceUtil.getTrainingMateriLampiran(lampiranUpdate.get(0).getId());
               } catch (PortalException e) {
                  LOG.error(e);
               }
               if (lampiranUpdateTwo != null) {
                  lampiranUpdateTwo.setFileId(fileData.getFileId());
                  lampiranUpdateTwo.setFileName(fileData.getFileName());
                  lampiranUpdateTwo.setFilePath(fileData.getFilePath());
                  lampiranUpdateTwo.setModifiedDate(date);
                  lampiranUpdateTwo.setModifiedBy(user.getScreenName());
                  TrainingMateriLampiranLocalServiceUtil.updateTrainingMateriLampiran(lampiranUpdateTwo);
               }
            }
         });
         // If Deleted
         for (String fileId : listDeleted) {
            DynamicQuery queryDeleted = TrainingMateriLampiranLocalServiceUtil.dynamicQuery();
            queryDeleted.add(RestrictionsFactoryUtil.eq("FileId", Long.parseLong(fileId)));
            List<TrainingMateriLampiran> lampiranDeleted = TrainingMateriLampiranLocalServiceUtil.dynamicQuery(queryDeleted);
            if (!lampiranDeleted.isEmpty()) {
               TrainingMateriLampiranLocalServiceUtil.deleteTrainingMateriLampiran(lampiranDeleted.get(0).getId());
            }
         }
         return SUCCESS("Data terupdate.", String.valueOf(materi.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(String entryId) {
      try {
         TrainingMateri materi = TrainingMateriLocalServiceUtil.getTrainingMateri(Integer.parseInt(entryId));
         materi.setRowStatus(false);
         materi.setModifiedBy(user.getScreenName());
         materi.setModifiedDate(new Date());
         TrainingMateriLocalServiceUtil.updateTrainingMateri(materi);
         return SUCCESS("Data terupdate.", entryId);
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   /*
   private boolean isExist(String name) {
      DynamicQuery query = TrainingMateriLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      List<TrainingMateri> trainingMateris = TrainingMateriLocalServiceUtil.dynamicQuery(query);
      return trainingMateris.size() > 0;
   }
   */
}