package com.astra.dewa.web.command.render.calender.event;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
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
            "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
            "mvc.command.name=calender-event-edit"
      },
      service = {MVCRenderCommand.class}
)
public class CalenderEventEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(CalenderEventEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String id = httpRequest.getParameter("id");

      int entryId = Integer.parseInt(id);
      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();

      if (entryId > 0) {
         action = UPDATE;
         try {
            // CSRF AUTHENTICATION
            AuthTokenUtil.checkCSRFToken(httpRequest, this.getClass().getName());

            CalenderEvent calender = CalenderEventLocalServiceUtil.getCalenderEvent(entryId);
            dto.put("id", calender.getId());
            dto.put("judul", calender.getJudul());
            dto.put("jenisAcara", calender.getJenisAcara());
            dto.put("statusAcara", calender.getStatusAcara());
            dto.put("lokasi", calender.getLocation());
            dto.put("linkMeeting", calender.getLinkMeeting());

            // startDate
            String[] startMinutes = calender.getStartDateHours().split(":");
            LocalDateTime startDateFull = DateUtil.convertToLocalDateTimeViaInstant(calender.getStartDate())
                  .withHour(Integer.parseInt(startMinutes[0]))
                  .withMinute(Integer.parseInt(startMinutes[1]));
            dto.put("startDate", calender.getStartDate());
            dto.put("startDateHours", calender.getStartDateHours());
            dto.put("startDateFull", startDateFull);

            // endDate
            String[] endMinutes = calender.getEndDateHours().split(":");
            LocalDateTime endDateFull = DateUtil.convertToLocalDateTimeViaInstant(calender.getEndDate())
                  .withHour(Integer.parseInt(endMinutes[0]))
                  .withMinute(Integer.parseInt(endMinutes[1]));
            dto.put("endDate", calender.getEndDate());
            dto.put("endDateHours", calender.getEndDateHours());
            dto.put("endDateFull", endDateFull);

            LocalDateTime registrationDateFull = null;
            // registrationLimitDate
            if (Validator.isNotNull(calender.getRegistrationLimitDateHours()) && Validator.isNotNull(calender.getRegistrationLimitDate())) {
               String[] registrationMinutes = calender.getRegistrationLimitDateHours().split(":");

               registrationDateFull = DateUtil.convertToLocalDateTimeViaInstant(calender.getRegistrationLimitDate())
                  .withHour(Integer.parseInt(registrationMinutes[0]))
                  .withMinute(Integer.parseInt(registrationMinutes[1]));
            }

            dto.put("registrationLimitDate", calender.getRegistrationLimitDate());
            dto.put("registrationLimitDateHours", calender.getRegistrationLimitDateHours());
            dto.put("registrationDateFull", registrationDateFull);

            dto.put("deskripsi", calender.getDeskripsi());
            dto.put("imageId", calender.getImageId());
            dto.put("imageName", calender.getImageName());
            dto.put("imagePath", calender.getImagePath());
         } catch (PortalException e) {
            if (e instanceof PrincipalException) {
               LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
               LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            }
            action = INVALID;
         }
      } else {
         action = CREATE;
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/calender/event/form.jsp";
   }
}