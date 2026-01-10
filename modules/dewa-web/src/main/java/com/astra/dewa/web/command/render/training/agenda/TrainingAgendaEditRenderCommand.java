package com.astra.dewa.web.command.render.training.agenda;

import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
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
import java.time.LocalDateTime;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.INVALID;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=/training-agenda-edit"
      },
      service = {MVCRenderCommand.class}
)
public class TrainingAgendaEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(TrainingAgendaEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (Integer.parseInt(id) > 0) {
         action = UPDATE;
         try {
            TrainingAgenda trainingAgenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(Integer.parseInt(id));
            dto.put("id", trainingAgenda.getId());
            dto.put("judul", trainingAgenda.getJudul());
            dto.put("jenisAgenda", trainingAgenda.getJenisAgenda());
            dto.put("statusAgenda", trainingAgenda.getStatusAgenda());
            dto.put("lokasi", trainingAgenda.getLocation());
            dto.put("linkMeeting", trainingAgenda.getLinkMeeting());
            // startDate
            String[] startMinutes = trainingAgenda.getStartDateHours().split(":");
            LocalDateTime startDateFull = DateUtil.convertToLocalDateTimeViaInstant(trainingAgenda.getStartDate())
                  .withHour(Integer.parseInt(startMinutes[0]))
                  .withMinute(Integer.parseInt(startMinutes[1]));
            dto.put("startDate", trainingAgenda.getStartDate());
            dto.put("startDateHours", trainingAgenda.getStartDateHours());
            dto.put("startDateFull", startDateFull);
            // endDate
            String[] endMinutes = trainingAgenda.getEndDateHours().split(":");
            LocalDateTime endDateFull = DateUtil.convertToLocalDateTimeViaInstant(trainingAgenda.getEndDate())
                  .withHour(Integer.parseInt(endMinutes[0]))
                  .withMinute(Integer.parseInt(endMinutes[1]));
            dto.put("endDate", trainingAgenda.getEndDate());
            dto.put("endDateHours", trainingAgenda.getEndDateHours());
            dto.put("endDateFull", endDateFull);
            // registrationLimitDate
            String[] registrationMinutes = trainingAgenda.getRegistrationLimitDateHours().split(":");
            LocalDateTime registrationDateFull = DateUtil.convertToLocalDateTimeViaInstant(trainingAgenda.getRegistrationLimitDate())
                  .withHour(Integer.parseInt(registrationMinutes[0]))
                  .withMinute(Integer.parseInt(registrationMinutes[1]));
            dto.put("registrationLimitDate", trainingAgenda.getRegistrationLimitDate());
            dto.put("registrationLimitDateHours", trainingAgenda.getRegistrationLimitDateHours());
            dto.put("registrationDateFull", registrationDateFull);

            dto.put("deskripsi", trainingAgenda.getDeskripsi());
            dto.put("imageId", trainingAgenda.getImageId());
            dto.put("imageName", trainingAgenda.getImageName());
            dto.put("imagePath", trainingAgenda.getImagePath());
         } catch (PortalException e) {
            LOG.error(e.getMessage(), e.fillInStackTrace());
            action = INVALID;
         }
      } else {
         action = CREATE;
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/training/agenda/form.jsp";
   }
}