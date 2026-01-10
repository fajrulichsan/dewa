package com.astra.dewa.web.command.render.training.participant;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.TrainingAgendaParticipant;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaParticipantLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/training-agenda-participant-single"
   },
   service = MVCResourceCommand.class
)
public class TrainingAgendaParticipantSingleRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(TrainingAgendaParticipantSingleRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String entryId = httpReq.getParameter("entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      try {
         TrainingAgendaParticipant participant = TrainingAgendaParticipantLocalServiceUtil.getTrainingAgendaParticipant(Integer.parseInt(entryId));
         dto.put("id", participant.getId());
         dto.put("email", participant.getEmail());
         dto.put("fullName", participant.getFullName());
         dto.put("phone", participant.getPhone());
         dto.put("trainingAgendaId", participant.getTrainingAgendaId());
         Dealer dealer;
         if(participant.getDealerId() != 0) {
            dealer = DealerLocalServiceUtil.getDealer(participant.getDealerId());
            dto.put("dealerId", dealer.getId());
            dto.put("dealerName", dealer.getName());
         } else {
            dto.put("dealerId", "-");
            dto.put("dealerName", "-");
         }
      } catch (Exception e) {
         log.error(e.getMessage(), e);
      }
      JSONObject jsonObject = SUCCESS("Sukses", entryId, dto);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
