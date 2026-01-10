package com.astra.dewa.web.command.render.upload.util;

import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author psmahmad1402
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_MONITORING,
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_DOCUMENT,
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_STNK_MONITORING,
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_STNK_DOCUMENT,
            "mvc.command.name=[/non-cms/download-upload-menu-file]"
      },
      service = MVCResourceCommand.class
)
public class DownloadUploadMenuCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      try {
         // CSRF Authentication
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         int id = ParamUtil.getInteger(httpServletRequest, "id", 0);
         ApplicationHeader applicationHeader = ApplicationHeaderLocalServiceUtil.getApplicationHeader(id);
         String fileName = applicationHeader.getFileName();
         long fileId = applicationHeader.getFileId();

         FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
         PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }
   }
}