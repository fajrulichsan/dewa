package com.astra.dealink.rest.api.util;

import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

/*
    Catatan:
    Folder Diskon Fakpol, Diskon Fleet, Diskon Test Car, SK-Iris, dan SP3D sudah ada di document and media
*/

public class FolderUtil {
    private static final Log log = LogFactoryUtil.getLog(FolderUtil.class);

    public static HashMap<String, String> uploadFileIntoMenuFolder(File file, String filename, Group group, User user, String menuName) {
        String fileName = "";
        InputStream is = null;
        HashMap<String, String> payload = new HashMap<>();

        long repository = group.getGroupId();
        long idUploadTo = getFolderMenu(group, menuName);

        String mimeType = MimeTypesUtil.getContentType(file);
        String changeLog = String.valueOf(LocalTime.now());

        try {
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setCompanyId(group.getCompanyId());
            serviceContext.setUserId(user.getUserId());
            serviceContext.setScopeGroupId(group.getGroupId());
            is = new FileInputStream(file);
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSSS");
            String nameFolderWithoutSpaces = filename.replaceAll("\\s+", "_");
            String realFileName = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[0];
            String ext = nameFolderWithoutSpaces.split("\\.(?=[^\\.]+$)")[1];
            fileName = realFileName + "_" + ft.format(dNow) + "." + ext;
            String savingFileName = realFileName + "." + ext;

            FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                    user.getUserId(), repository, idUploadTo, fileName, mimeType, fileName,
                    "Upload By API", changeLog, is, file.length(), serviceContext);

            String path = "/documents/" + repository + "/" + idUploadTo + "/" + fileName;

            payload.put("ID", String.valueOf(fileEntry.getFileEntryId()));
            payload.put("path", path);
            payload.put("fileName", savingFileName);

            // For Permission Purposes
            Role siteMemberRole = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SITE_MEMBER);

            // Change File Permission Site Member Can Update
            System.out.println(fileEntry.toString());
            if (!ResourcePermissionLocalServiceUtil
                    .hasResourcePermission(
                            user.getCompanyId(),
                            DLFileEntry.class.getName(),
                            ResourceConstants.SCOPE_INDIVIDUAL,
                            String.valueOf(fileEntry.getPrimaryKey()),
                            siteMemberRole.getRoleId(),
                            ActionKeys.UPDATE
                    )
            ) {
                ResourcePermissionLocalServiceUtil
                        .setResourcePermissions(
                                user.getCompanyId(),
                                DLFileEntry.class.getName(),
                                ResourceConstants.SCOPE_INDIVIDUAL,
                                String.valueOf(fileEntry.getPrimaryKey()),
                                siteMemberRole.getRoleId(),
                                new String[]{ActionKeys.UPDATE, ActionKeys.VIEW, ActionKeys.DELETE, ActionKeys.ADD_DISCUSSION, ActionKeys.PERMISSIONS}
                        );
            }

            log.info(payload.toString());

            return payload;
        } catch (Exception e) {
            log.error("error upload file : " + file.getName());
            log.error(e.getMessage(), e);

            payload.put("URL", "");
            payload.put("ID", "");
            payload.put("fileName", "");
            return payload;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error("inputstream close error");
                log.error(e);
            }
        }
    }

    public static long getFolderMenu(Group group, String menuName) {
        long repositoryId = group.getGroupId();//repository id is same as groupId
        long parentFolderId = getFolder(group); // or 0
        long folderId = 0L;
        try {
            DLFolder rootFolder = DLFolderLocalServiceUtil.getFolder(repositoryId, parentFolderId, menuName);
            folderId = rootFolder.getFolderId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folderId;
    }

    public static long getFolder(Group group) {
        long repositoryId = group.getGroupId();//repository id is same as groupId
        long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID; // or 0
        long rootFolderId = 0L;
        try {
            DLFolder rootFolder = DLFolderLocalServiceUtil.getFolder(repositoryId, parentFolderId, DewaRestApiKeys.DEWA_SITE_FOLDER);
            rootFolderId = rootFolder.getFolderId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootFolderId;
    }

    public static void moveFileIntoMenuFolder(long fileId, String fileName, Group group, User user, String menuName) {
        long folderId = getFolderMenu(group, menuName);

        try {
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setCompanyId(group.getCompanyId());
            serviceContext.setUserId(user.getUserId());
            serviceContext.setScopeGroupId(group.getGroupId());

            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);

            if (fileEntry != null) {
                DLAppLocalServiceUtil.moveFileEntry(serviceContext.getUserId(), fileId, folderId, serviceContext);
            }

            log.info("File berhasil dipindahkan ke folder: " + menuName);
        } catch (Exception e) {
            log.error("Error moving file: " + fileName);
            log.error("Message: " + e.getMessage());
        }
    }
}
