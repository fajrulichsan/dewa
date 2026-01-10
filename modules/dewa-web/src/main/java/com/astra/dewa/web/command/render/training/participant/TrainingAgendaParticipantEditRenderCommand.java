package com.astra.dewa.web.command.render.training.participant;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.TrainingAgendaParticipant;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaParticipantLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
      "mvc.command.name=/training-peserta-manual-edit"
   },
   service = {MVCRenderCommand.class}
)
public class TrainingAgendaParticipantEditRenderCommand implements MVCRenderCommand {

   private final Log log = LogFactoryUtil.getLog(TrainingAgendaParticipantEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");
      String trainingAgendaId = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("trainingAgendaId");
      String trainingAgendaName = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("trainingAgendaName");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id.length() > 2){
         action="update";
         try {
            TrainingAgendaParticipant trainingMateri = TrainingAgendaParticipantLocalServiceUtil.getTrainingAgendaParticipant(Integer.parseInt(id));
            dto.put("id", trainingMateri.getId());
            dto.put("fullName", trainingMateri.getFullName());
            dto.put("email", trainingMateri.getEmail());
            dto.put("phone", trainingMateri.getPhone());
            dto.put("trainingAgendaId", trainingMateri.getTrainingAgendaId());
            Dealer dealer;
            if(trainingMateri.getDealerId() != 0) {
               dealer = DealerLocalServiceUtil.getDealer(trainingMateri.getDealerId());
               dto.put("dealerId", dealer.getId());
               dto.put("dealerName", dealer.getName());
            } else {
               dto.put("dealerId", "-");
               dto.put("dealerName", "-");
            }
         } catch (PortalException e) {
            action="invalid";
         }
      } else {
         action="create";
      }
      request.setAttribute("trainingAgendaId",  trainingAgendaId);
      request.setAttribute("trainingAgendaName",  trainingAgendaName);
      request.setAttribute("data",  dto);
      request.setAttribute("action",  action);
      log.info("dto : " + dto);
      return "/training/participant/form.jsp";
   }

}