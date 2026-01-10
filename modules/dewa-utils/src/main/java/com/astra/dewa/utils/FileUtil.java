package com.astra.dewa.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class FileUtil {

   private static final Log LOGGER = LogFactoryUtil.getLog(FileUtil.class);

   // private void updateDLFile(DLFileEntry file, byte[] content) throws Exception
   // {
   // long userId =
   // UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId());
   // String[] tagsEntries =
   // TagsEntryLocalServiceUtil.getEntryNames(DLFileEntry.class.getName(),
   // file.getFileEntryId());
   // ServiceContext serviceContext = new ServiceContext();
   // serviceContext.setTagsEntries(tagsEntries);
   //
   // DLFileEntryLocalServiceUtil.updateFileEntry(userId, file.getFolderId(),
   // file.getFolderId(), file.getName(), file.getName(), file.getTitle(),
   // file.getDescription(), file.getExtraSettings(), content, serviceContext);
   // }

   public static HashMap<String, String> uploadFile(File file, String filename, ThemeDisplay themeDisplay,
         PortletRequest portletRequest) {
      String fileName = "";
      HashMap<String, String> payload = new HashMap<>();

      long repository = themeDisplay.getScopeGroupId();
      long idUploadTo = getFolder(themeDisplay, portletRequest);

      String mimeType = MimeTypesUtil.getContentType(file);
      String changeLog = String.valueOf(LocalTime.now());

      try {

         ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), portletRequest);
         Date dNow = new Date();
         SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSSS");

         String nameFolderWithoutSpaces = filename.replaceAll("\\s+", "_");
         String realFileName = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[0];
         String ext = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[1];

         fileName = realFileName + "_" + ft.format(dNow) + "." + ext;
         String savingFileName = realFileName + "." + ext;

         FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
               serviceContext.getUserId(),
               repository,
               idUploadTo,
               fileName,
               mimeType,
               fileName,
               "Uploaded by system",
               changeLog,
               file,
               serviceContext
         );

         String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
               + themeDisplay.getScopeGroupId() + "/" + idUploadTo + "/" + fileName;

         LOGGER.info("upload success, url : " + url);
         LOGGER.info("Final File Name : " + savingFileName);
         LOGGER.info("File: " + fileEntry.toString());

         payload.put("URL", url);
         payload.put("ID", String.valueOf(fileEntry.getFileEntryId()));
         payload.put("fileName", savingFileName);

         return payload;
      } catch (Exception e) {

         LOGGER.error("error upload file ; " + file.getName());
         LOGGER.error(e);

         payload.put("URL", "");
         payload.put("ID", "");
         payload.put("fileName", "");
         return payload;
      }
   }

   public static HashMap<String, String> updateFile(Long fileId, File file, String filename, ThemeDisplay themeDisplay,
         PortletRequest portletRequest) {
      String fileName = "";
      HashMap<String, String> payload = new HashMap<>();

      // long repository = themeDisplay.getScopeGroupId();
      // long idUploadTo = getFolder(themeDisplay, portletRequest);

      String mimeType = MimeTypesUtil.getContentType(file);
      String changeLog = String.valueOf(LocalTime.now());
      String description = "";

      try {
         ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), portletRequest);
         Date dNow = new Date();
         SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSSS");

         String nameFolderWithoutSpaces = filename.replaceAll("\\s+", "_");
         String realFileName = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[0];
         String ext = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[1];

         fileName = realFileName + "_" + ft.format(dNow) + "." + ext;
         String savingFileName = realFileName + "." + ext;

         FileEntry fileEntry = DLAppLocalServiceUtil.updateFileEntry(
               serviceContext.getUserId(),
               fileId.longValue(),
               fileName,
               mimeType,
               fileName,
               fileName,
               description,
               changeLog,
               DLVersionNumberIncrease.MINOR,
               file,
               null,
               null,
               serviceContext);

         String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
               + themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileName;

         LOGGER.info("upload success, url : " + url);
         LOGGER.info("Final File Name : " + savingFileName);
         LOGGER.info("File: " + fileEntry.toString());

         payload.put("URL", url);
         payload.put("ID", String.valueOf(fileEntry.getFileEntryId()));
         payload.put("fileName", savingFileName);

         return payload;
      } catch (Exception e) {

         LOGGER.error("error upload file ; " + file.getName());
         LOGGER.error(e);

         payload.put("URL", "");
         payload.put("ID", "");
         payload.put("fileName", "");
         return payload;
      }
   }

   public static long getFolder(ThemeDisplay themeDisplay, PortletRequest portletRequest) {
      long repositoryId = themeDisplay.getScopeGroupId();// repository id is same as groupId
      long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID; // or 0
      try {
         DLFolder rootFolder = DLFolderLocalServiceUtil.getFolder(repositoryId, parentFolderId, "Dewa");
         return rootFolder.getFolderId();
      } catch (Exception e) {
         try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(), portletRequest);
            Folder folder = DLAppLocalServiceUtil.addFolder(
                  null,
                  serviceContext.getUserId(),
                  repositoryId,
                  parentFolderId,
                  "Dewa",
                  "Created by system",
                  serviceContext);
            return folder.getFolderId();
         } catch (PortalException | SystemException e1) {
            e1.printStackTrace();
            return 0;
         }
      }
   }

   public static HashMap<String, String> uploadFileIntoCompanyFolder(File file, String filename,
         ThemeDisplay themeDisplay, PortletRequest portletRequest, String companyId) {
      String fileName = "";
      HashMap<String, String> payload = new HashMap<>();

      long repository = themeDisplay.getScopeGroupId();
      long idUploadTo = getFolderCompany(themeDisplay, portletRequest, companyId);

      String mimeType = MimeTypesUtil.getContentType(file);
      String changeLog = String.valueOf(LocalTime.now());

      try {
         ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), portletRequest);
         Date dNow = new Date();
         SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSSS");

         String nameFolderWithoutSpaces = filename.replaceAll("\\s+", "_");
         String realFileName = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[0];
         String ext = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[1];

         fileName = realFileName + "_" + ft.format(dNow) + "." + ext;
         String savingFileName = realFileName + "." + ext;

         FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
               serviceContext.getUserId(),
               repository,
               idUploadTo,
               fileName,
               mimeType,
               fileName,
               "Uploaded by system",
               changeLog,
               file,
               serviceContext
         );

         String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
               + themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileName;

         LOGGER.info("upload success, url : " + url);
         LOGGER.info("Final File Name : " + savingFileName);
         LOGGER.info("File: " + fileEntry.toString());

         payload.put("URL", url);
         payload.put("ID", String.valueOf(fileEntry.getFileEntryId()));
         payload.put("fileName", savingFileName);

         // For Permission Purposes
         Role siteMemberRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), RoleConstants.SITE_MEMBER);

         // Change File Permission Site Member Can Update
         System.out.println(fileEntry.toString());
         if (!ResourcePermissionLocalServiceUtil
               .hasResourcePermission(
                     themeDisplay.getCompanyId(),
                     DLFileEntry.class.getName(),
                     ResourceConstants.SCOPE_INDIVIDUAL,
                     String.valueOf(fileEntry.getPrimaryKey()),
                     siteMemberRole.getRoleId(),
                     ActionKeys.UPDATE)) {
            ResourcePermissionLocalServiceUtil
                  .setResourcePermissions(
                        themeDisplay.getCompanyId(),
                        DLFileEntry.class.getName(),
                        ResourceConstants.SCOPE_INDIVIDUAL,
                        String.valueOf(fileEntry.getPrimaryKey()),
                        siteMemberRole.getRoleId(),
                        new String[] { ActionKeys.UPDATE, ActionKeys.VIEW, ActionKeys.DELETE, ActionKeys.ADD_DISCUSSION,
                              ActionKeys.PERMISSIONS });
         }

         return payload;
      } catch (Exception e) {

         LOGGER.error("error upload file ; " + file.getName());
         LOGGER.error(e);

         payload.put("URL", "");
         payload.put("ID", "");
         payload.put("fileName", "");
         return payload;
      }
   }

   public static HashMap<String, String> uploadFileIntoMenuFolder(File file, String filename, ThemeDisplay themeDisplay,
         PortletRequest portletRequest, String menuName) {
      String fileName = "";
      HashMap<String, String> payload = new HashMap<>();

      long repository = themeDisplay.getScopeGroupId();
      long idUploadTo = getFolderMenu(themeDisplay, portletRequest, menuName);

      String mimeType = MimeTypesUtil.getContentType(file);
      String changeLog = String.valueOf(LocalTime.now());

      try {
         ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), portletRequest);
         Date dNow = new Date();
         SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSSS");

         String nameFolderWithoutSpaces = filename.replaceAll("\\s+", "_");
         String realFileName = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[0];
         String ext = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[1];

         fileName = realFileName + "_" + ft.format(dNow) + "." + ext;
         String savingFileName = realFileName + "." + ext;

         FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
               serviceContext.getUserId(),
               repository,
               idUploadTo,
               fileName,
               mimeType,
               fileName,
               "Uploaded by system",
               changeLog,
               file,
               serviceContext
         );

         String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
               + themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileName;

         LOGGER.info("upload success, url : " + url);
         LOGGER.info("Final File Name : " + savingFileName);
         LOGGER.info("File: " + fileEntry.toString());

         payload.put("URL", url);
         payload.put("ID", String.valueOf(fileEntry.getFileEntryId()));
         payload.put("fileName", savingFileName);

         // For Permission Purposes
         Role siteMemberRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), RoleConstants.SITE_MEMBER);

         // Change File Permission Site Member Can Update
         System.out.println(fileEntry.toString());
         if (!ResourcePermissionLocalServiceUtil
               .hasResourcePermission(
                     themeDisplay.getCompanyId(),
                     DLFileEntry.class.getName(),
                     ResourceConstants.SCOPE_INDIVIDUAL,
                     String.valueOf(fileEntry.getPrimaryKey()),
                     siteMemberRole.getRoleId(),
                     ActionKeys.UPDATE)) {
            ResourcePermissionLocalServiceUtil
                  .setResourcePermissions(
                        themeDisplay.getCompanyId(),
                        DLFileEntry.class.getName(),
                        ResourceConstants.SCOPE_INDIVIDUAL,
                        String.valueOf(fileEntry.getPrimaryKey()),
                        siteMemberRole.getRoleId(),
                        new String[] { ActionKeys.UPDATE, ActionKeys.VIEW, ActionKeys.DELETE, ActionKeys.ADD_DISCUSSION,
                              ActionKeys.PERMISSIONS });
         }

         return payload;
      } catch (Exception e) {

         LOGGER.error("error upload file ; " + file.getName());
         LOGGER.error(e);

         payload.put("URL", "");
         payload.put("ID", "");
         payload.put("fileName", "");
         return payload;
      }
   }

   public static long getFolderCompany(ThemeDisplay themeDisplay, PortletRequest portletRequest, String companyId) {
      long repositoryId = themeDisplay.getScopeGroupId();// repository id is same as groupId
      long parentFolderId = getFolder(themeDisplay, portletRequest); // or 0
      try {
         DLFolder rootFolder = DLFolderLocalServiceUtil.getFolder(repositoryId, parentFolderId, companyId);
         return rootFolder.getFolderId();
      } catch (Exception e) {
         try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(), portletRequest);
            Folder folder = DLAppLocalServiceUtil.addFolder(
                  null,
                  serviceContext.getUserId(),
                  repositoryId,
                  parentFolderId,
                  companyId,
                  companyId,
                  serviceContext);
            return folder.getFolderId();
         } catch (PortalException | SystemException e1) {
            e1.printStackTrace();
            return 0;
         }
      }
   }

   public static long getFolderMenu(ThemeDisplay themeDisplay, PortletRequest portletRequest, String menuName) {
      long repositoryId = themeDisplay.getScopeGroupId();// repository id is same as groupId
      long parentFolderId = getFolder(themeDisplay, portletRequest); // or 0
      try {
         DLFolder rootFolder = DLFolderLocalServiceUtil.getFolder(repositoryId, parentFolderId, menuName);
         return rootFolder.getFolderId();
      } catch (Exception e) {
         try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(), portletRequest);
            Folder folder = DLAppLocalServiceUtil.addFolder(
                  null,
                  serviceContext.getUserId(),
                  repositoryId,
                  parentFolderId,
                  menuName,
                  "Created by system",
                  serviceContext);
            return folder.getFolderId();
         } catch (PortalException | SystemException e1) {
            e1.printStackTrace();
            return 0;
         }
      }
   }

   public static String getFileUrl(DLFileEntry dlFileEntry, ThemeDisplay themeDisplay, PortletRequest portletRequest) {
      long idUploadTo = getFolder(themeDisplay, portletRequest);
      String fileName = dlFileEntry.getFileName();
      String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
            + themeDisplay.getScopeGroupId() + "/" + idUploadTo + "/" + fileName;
      return url;
   }

   public static String getFileUrl2(DLFileEntry dlFileEntry, ThemeDisplay themeDisplay) {
      long folderId = dlFileEntry.getFolderId();
      String fileName = dlFileEntry.getFileName();
      String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
            + themeDisplay.getScopeGroupId() + "/" + folderId + "/" + fileName;
      return url;
   }

   public static void deleteFile(long fileId) {
      try {
         DLAppServiceUtil.deleteFileEntry(fileId);
      } catch (PortalException e) {
         LOGGER.error(e);
      }
   }

   public static void manipulatePdf(String src, String dest, ThemeDisplay themeDisplay)
         throws IOException, PortalException {

      PdfReader reader = new PdfReader(src);
      PdfStamper stamper = null;
      try {
         stamper = new PdfStamper(reader, new FileOutputStream(dest));

         Long fileFooterId = getFileFooter();
         DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileFooterId);
         InputStream in = new BufferedInputStream(dlFileEntry.getContentStream());
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         int n = 0;
         while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
         }
         out.close();
         in.close();
         byte[] outByte = out.toByteArray();

         Image img = Image.getInstance(outByte);
         float x = 23;
         float y = 1;
         float w = img.getScaledWidth();
         float h = img.getScaledHeight();
         img.scaleAbsolute(600, 79);
         img.setAbsolutePosition(x, y);
         stamper.getOverContent(1).addImage(img);
         Rectangle linkLocation = new Rectangle(x, y, x + w, y + h);
         PdfDestination destination = new PdfDestination(PdfDestination.FIT);
         PdfAnnotation link = PdfAnnotation.createLink(stamper.getWriter(), linkLocation,
               PdfAnnotation.HIGHLIGHT_INVERT, reader.getNumberOfPages(), destination);
         link.setBorder(new PdfBorderArray(0, 0, 0));
         stamper.addAnnotation(link, 1);
         stamper.close();
         reader.close();
      } catch (DocumentException e) {
         LOGGER.error(e);
      }
   }

   private static Long getFileFooter() {

      // DynamicQuery query = CommonLocalServiceUtil.dynamicQuery();
      // query.add(RestrictionsFactoryUtil.eq("CommonKey", "FILE_FOOTTER"));
      // query.add(RestrictionsFactoryUtil.eq("IsActive", true));
      // List<Common> commonList = CommonLocalServiceUtil.dynamicQuery(query);
      //
      // if (commonList.size() > 0) {
      // return Long.parseLong(commonList.get(0).getCommonDesc1());
      // }

      return 0L;
   }

   public static String encodeFileToBase64Binary(File file) throws IOException {
      byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
      return new String(encoded, StandardCharsets.US_ASCII);
   }

   public static HashMap<String, String> uploadFile(File file, String filename, ThemeDisplay themeDisplay,
         HttpServletRequest httpServletRequest) {
      String fileName = "";
      HashMap<String, String> payload = new HashMap<>();

      long repository = themeDisplay.getScopeGroupId();
      long idUploadTo = getFolder(themeDisplay, httpServletRequest);

      String mimeType = MimeTypesUtil.getContentType(file);
      String changeLog = String.valueOf(LocalTime.now());

      try {

         ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
               httpServletRequest);
         Date dNow = new Date();
         SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSSS");

         String nameFolderWithoutSpaces = filename.replaceAll("\\s+", "_");
         String realFileName = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[0];
         String ext = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[1];

         fileName = realFileName + "_" + ft.format(dNow) + "." + ext;
         String savingFileName = realFileName + "." + ext;

         FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
               serviceContext.getUserId(),
               repository,
               idUploadTo,
               fileName,
               mimeType,
               fileName,
               "Uploaded by system",
               changeLog,
               file,
               serviceContext
         );

         String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/"
               + themeDisplay.getScopeGroupId() + "/" + idUploadTo + "/" + fileName;

         LOGGER.info("upload success, url : " + url);
         LOGGER.info("Final File Name : " + savingFileName);
         LOGGER.info("File: " + fileEntry.toString());

         payload.put("URL", url);
         payload.put("ID", String.valueOf(fileEntry.getFileEntryId()));
         payload.put("fileName", savingFileName);

         return payload;
      } catch (Exception e) {

         LOGGER.error("error upload file ; " + file.getName());
         LOGGER.error(e);

         payload.put("URL", "");
         payload.put("ID", "");
         payload.put("fileName", "");
         return payload;
      }
   }

   public static long getFolder(ThemeDisplay themeDisplay, HttpServletRequest httpServletRequest) {
      long repositoryId = themeDisplay.getScopeGroupId();// repository id is same as groupId
      long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID; // or 0
      try {
         DLFolder rootFolder = DLFolderLocalServiceUtil.getFolder(repositoryId, parentFolderId, "Dewa");
         return rootFolder.getFolderId();
      } catch (Exception e) {
         try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(),
                  httpServletRequest);
            Folder folder = DLAppLocalServiceUtil.addFolder(
                  null,
                  serviceContext.getUserId(),
                  repositoryId,
                  parentFolderId,
                  "Dewa",
                  "Created by system",
                  serviceContext);
            return folder.getFolderId();
         } catch (PortalException | SystemException e1) {
            e1.printStackTrace();
            return 0;
         }
      }
   }

   public static Optional<String> getExtensionByStringHandling(String filename) {
      return Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf(".") + 1));
   }

   public static void moveIntoMenuFolder(ThemeDisplay themeDisplay, PortletRequest portletRequest,
         ServiceContext serviceContext, long fileId, String menuName) {
      long folderId = getFolderMenu(themeDisplay, portletRequest, menuName);
      try {
         DLAppServiceUtil.moveFileEntry(fileId, folderId, serviceContext);
      } catch (PortalException | SystemException e) {
         LOGGER.error("Error moving file: " + e.getMessage());
         e.printStackTrace();
      }
   }

}
