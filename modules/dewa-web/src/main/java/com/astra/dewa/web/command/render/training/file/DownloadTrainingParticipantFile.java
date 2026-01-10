package com.astra.dewa.web.command.render.training.file;

import com.astra.dewa.model.TrainingAgendaParticipantUf;
import com.astra.dewa.service.TrainingAgendaParticipantUfLocalServiceUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=download-training-participant-file"
      },
      service = MVCResourceCommand.class
)
public class DownloadTrainingParticipantFile extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         int id = ParamUtil.getInteger(httpServletRequest, "id", 0);
         TrainingAgendaParticipantUf participantUf = TrainingAgendaParticipantUfLocalServiceUtil.getTrainingAgendaParticipantUf(id);
         String fileName = participantUf.getFileName();
         FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(participantUf.getFileId());
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