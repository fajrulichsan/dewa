package com.astra.dealink.rest.api.util;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class FileEntryUtil {
   private static final Log log = LogFactoryUtil.getLog(FileEntryUtil.class);

   public static void deleteFileEntry(long fileId) {
      try {
         DLAppServiceUtil.deleteFileEntry(fileId);
      } catch (Exception e) {
         log.error("Error menghapus file! " + e.getMessage());
      }
   }

}
