package com.astra.dewa.web.command.render.calender.participant;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
      "mvc.command.name=calender-event-participant-view"
   },
   service = MVCRenderCommand.class
)
public class CalenderEventParticipantViewRenderCommand implements MVCRenderCommand {

   private final Log log = LogFactoryUtil.getLog(CalenderEventParticipantViewRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String calenderEventId = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("calenderEventId");
      CalenderEvent calender = null;
      try {
         calender = CalenderEventLocalServiceUtil.getCalenderEvent(Integer.parseInt(calenderEventId));
      } catch (Exception e) {
         log.error(e.getMessage(), e);
      }
      assert calender != null;
      request.setAttribute("calenderEventId",  calenderEventId);
      request.setAttribute("calenderEventName",  calender.getJudul());
      return "/calender/participant/view.jsp";
   }

}
