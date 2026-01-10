package com.astra.dewa.web.command.render.training.participant_uf;

import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.model.TrainingAgendaParticipantUf;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaParticipantUfLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=/training-agenda-participant-uf-list"
      },
      service = MVCResourceCommand.class
)
public class TrainingAgendaParticipantUfListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TrainingAgendaParticipantUfListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String trainingAgendaId = httpReq.getParameter("trainingAgendaId");
      TrainingAgenda agenda = null;

      int acknowledge = 0;
      int count = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         agenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(Integer.parseInt(trainingAgendaId));

         List<TrainingAgendaParticipantUf> participants = new ArrayList<>();
         DynamicQuery query = TrainingAgendaParticipantUfLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("TrainingAgendaId", Integer.parseInt(trainingAgendaId)));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         participants = TrainingAgendaParticipantUfLocalServiceUtil.dynamicQuery(query);
         for (TrainingAgendaParticipantUf participant : participants) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", participant.getId());
            dto.put("trainingAgendaId", participant.getTrainingAgendaId());
            dto.put("trainingAgendaName", agenda.getJudul());
            Dealer dealer = null;
            if (participant.getDealerId() > 0) {
               dealer = DealerLocalServiceUtil.getDealer(participant.getDealerId());
               dto.put("dealerId", dealer.getId());
               dto.put("dealerCode", dealer.getCode());
               dto.put("dealerName", dealer.getName());
            } else {
               dto.put("dealerId", "-");
               dto.put("dealerCode", "-");
               dto.put("dealerName", "-");
            }
            assert dealer != null;
            if (dealer.getCabangId() > 0) {
               Cabang cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());
               dto.put("cabangId", cabang.getId());
               dto.put("cabangName", cabang.getName());
            } else {
               dto.put("cabangId", "-");
               dto.put("cabangName", "-");
            }
            dto.put("fileId", participant.getFileId());
            dto.put("fileName", participant.getFileName());
            dto.put("filePath", participant.getFilePath());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = participants.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpReq));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpReq, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else  {
            jsonMessage = ERROR(400, "Bad request!");
         }
         LOG.error(e.getMessage(), e);
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}