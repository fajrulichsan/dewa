package com.astra.dewa.web.command.render.calender.participant;

import com.astra.dewa.model.CalenderEventParticipant;
import com.astra.dewa.service.CalenderEventParticipantLocalServiceUtil;
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

@Component(immediate = true, property = {
      "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
      "mvc.command.name=calender-event-participant-edit"
}, service = { MVCRenderCommand.class })
public class CalenderEventParticipantEditRenderCommand implements MVCRenderCommand {

   private final Log log = LogFactoryUtil.getLog(CalenderEventParticipantEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id != "0") {
         action = "update";
         try {
            CalenderEventParticipant participant = CalenderEventParticipantLocalServiceUtil
                  .getCalenderEventParticipant(Integer.parseInt(id));
            dto.put("id", participant.getId());
            dto.put("calenderEventId", participant.getCalenderEventId());
            dto.put("dealerId", participant.getDealerId());
            dto.put("fullName", participant.getFullName());
            dto.put("email", participant.getEmail());
            dto.put("phone", participant.getPhone());
         } catch (PortalException e) {
            log.error(e.getMessage(), e);
            action = "invalid";
         }
      } else {
         action = "create";
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/calender/participant/form.jsp";
   }

}