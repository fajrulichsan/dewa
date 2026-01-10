package com.astra.dewa.web.command.render.calender.event;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT_VIEW,
      "mvc.command.name=calender-event-view"
   },
   service = MVCResourceCommand.class
)
public class CalenderEventViewRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(CalenderEventViewRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String jenisAcaraId = httpReq.getParameter("jenisAcaraId");
      String statusAcaraId = httpReq.getParameter("statusAcaraId");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         DynamicQuery query = CalenderEventLocalServiceUtil.dynamicQuery();
         if (!jenisAcaraId.equalsIgnoreCase("ALL") && !jenisAcaraId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("JenisAcaraId", jenisAcaraId));
         }
         if (!statusAcaraId.equalsIgnoreCase("ALL") && !statusAcaraId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("StatusAcaraId", statusAcaraId));
         }
         List<CalenderEvent> calenderEvents = new ArrayList<>();
         if(jenisAcaraId.equalsIgnoreCase("ALL") && statusAcaraId.equalsIgnoreCase("ALL")) {
            calenderEvents = CalenderEventLocalServiceUtil.getCalenderEvents(0, 10);
         } else {
            calenderEvents = CalenderEventLocalServiceUtil.dynamicQuery(query);
         }

         for (CalenderEvent calenderEvent : calenderEvents) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", calenderEvent.getId());
            dto.put("judul", calenderEvent.getJudul());
            dto.put("jenisAcara", calenderEvent.getJenisAcara());
            dto.put("statusAcara", calenderEvent.getStatusAcara());
            dto.put("lokasi", calenderEvent.getLocation());
            dto.put("startDate", DateUtil.convertDateToStringIndo(calenderEvent.getStartDate(), true));
            dto.put("startDateHours", calenderEvent.getStartDateHours());
            dto.put("endDate", DateUtil.convertDateToStringIndo(calenderEvent.getEndDate(), true));
            dto.put("endDateHours", calenderEvent.getEndDateHours());
            dto.put("registrationLimitDate", DateUtil.convertDateToStringIndo(calenderEvent.getRegistrationLimitDate(), true));
            dto.put("registrationLimitDateHours", calenderEvent.getRegistrationLimitDateHours());
            dto.put("deskripsi", calenderEvent.getDeskripsi());
            dto.put("imageId", calenderEvent.getImageId());
            dto.put("imageName", calenderEvent.getImageName());
            dto.put("imagePath", calenderEvent.getImagePath());

            jsonData.put(dto);
         }

         acknowledge = 1;
         count = calenderEvents.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
