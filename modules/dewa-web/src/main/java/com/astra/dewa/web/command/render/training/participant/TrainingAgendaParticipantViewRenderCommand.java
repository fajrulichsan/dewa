package com.astra.dewa.web.command.render.training.participant;

import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/training-agenda-participant-manual-view"
   },
   service = MVCRenderCommand.class
)
public class TrainingAgendaParticipantViewRenderCommand implements MVCRenderCommand {

   private final Log log = LogFactoryUtil.getLog(TrainingAgendaParticipantViewRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String trainingAgendaId = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("trainingAgendaId");
      TrainingAgenda agenda = null;
      try {
         agenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(Integer.parseInt(trainingAgendaId));
      } catch (Exception e) {
         log.error(e.getMessage(), e);
      }
      assert agenda != null;
      request.setAttribute("trainingAgendaId",  trainingAgendaId);
      request.setAttribute("trainingAgendaName",  agenda.getJudul());
      request.setAttribute("jenisAgenda",  agenda.getJenisAgenda());
      return "/training/participant/view.jsp";
   }
}
