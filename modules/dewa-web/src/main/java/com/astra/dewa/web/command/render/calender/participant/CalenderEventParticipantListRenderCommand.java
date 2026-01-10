package com.astra.dewa.web.command.render.calender.participant;

import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.CalenderEventParticipant;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.CalenderEventParticipantLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
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
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
      "mvc.command.name=calender-event-participant-list"
   },
   service = MVCResourceCommand.class
)

public class CalenderEventParticipantListRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(CalenderEventParticipantListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String calenderEventId = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("calenderEventId");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         List<CalenderEventParticipant> participants = new ArrayList<>();
         DynamicQuery query = CalenderEventParticipantLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("CalenderEventId", Integer.parseInt(calenderEventId)));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         participants = CalenderEventParticipantLocalServiceUtil.dynamicQuery(query);
         for (CalenderEventParticipant participant : participants) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", participant.getId());
            dto.put("calenderEventId", participant.getCalenderEventId());
            Dealer dealer = DealerLocalServiceUtil.getDealer(participant.getDealerId());
            dto.put("dealerId", dealer.getId());
            dto.put("dealerCode", dealer.getCode());
            dto.put("dealerName", dealer.getName());
            if(dealer.getCabangId() > 0) {
               Cabang cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());
               dto.put("cabangId", cabang.getId());
               dto.put("cabangName", cabang.getName());
            } else {
               dto.put("cabangId", "-");
               dto.put("cabangName", "-");
            }
            dto.put("fullName", participant.getFullName());
            dto.put("email", participant.getEmail());
            dto.put("phone", participant.getPhone());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = participants.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
       JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
       ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
