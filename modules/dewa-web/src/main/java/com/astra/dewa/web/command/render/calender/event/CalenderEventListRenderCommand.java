package com.astra.dewa.web.command.render.calender.event;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.StatusAcaraEnum;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
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
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
            "mvc.command.name=calender-event-list"
      },
      service = MVCResourceCommand.class
)
public class CalenderEventListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(CalenderEventListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String statusAcaraId = httpReq.getParameter("statusAcaraId");
      String draw = httpReq.getParameter("draw");
      if (draw.isEmpty()) {
         draw = "0";
      }
      String start = httpReq.getParameter("start");
      if (start.isEmpty()) {
         start = "0";
      }
      String length = httpReq.getParameter("length");
      if (length.isEmpty()) {
         length = "0";
      }
      int first = Integer.parseInt(start);
      int end = first + Integer.parseInt(length);

      String search = httpReq.getParameter("search[value]");
      String orderColumn = httpReq.getParameter("order[0][column]");
      String orderDir = httpReq.getParameter("order[0][dir]");

//      LOG.info("draw : " + draw + " start : " + start + " length : " + length);
//      LOG.info("search : " + search + " regex : " + regex);
//      LOG.info("orderColumn : " + orderColumn + " orderDir : " + orderDir);

      long recordsTotal = 0;
      long recordsFiltered = 0;
      int count = first;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      try {
         DynamicQuery query = CalenderEventLocalServiceUtil.dynamicQuery();
         DynamicQuery queryCount = CalenderEventLocalServiceUtil.dynamicQuery();
         if (!statusAcaraId.equalsIgnoreCase("ALL") && !statusAcaraId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("StatusAcara", Integer.parseInt(statusAcaraId)));
            queryCount.add(RestrictionsFactoryUtil.eq("StatusAcara", Integer.parseInt(statusAcaraId)));
         }
         List<CalenderEvent> calenderEvents;
         // Searching
         if (!search.isEmpty()) {
            query.add(RestrictionsFactoryUtil.like("Judul", "%" + search + "%"));
            queryCount.add(RestrictionsFactoryUtil.like("Judul", "%" + search + "%"));
         }
         // Order By
         int column = Integer.parseInt(orderColumn);
         if (column == 1) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("Judul"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("Judul"));
            }
         } else if (column == 2) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("StartDate"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("StartDate"));
            }
         } else if (column == 4) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("JenisAcara"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("JenisAcara"));
            }
         } else if (column == 5) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("StatusAcara"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("StatusAcara"));
            }
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         queryCount.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         calenderEvents = CalenderEventLocalServiceUtil.dynamicQuery(query, first, end);
         recordsTotal = CalenderEventLocalServiceUtil.dynamicQueryCount(queryCount);
         for (CalenderEvent calender : calenderEvents) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", calender.getId());
            dto.put("judul", calender.getJudul());
            dto.put("jenisAcara", calender.getJenisAcara());
            dto.put("statusAcara", calender.getStatusAcara());

            LocalDateTime startDate = DateUtil.convertToLocalDateTimeViaInstant(calender.getStartDate());
            LocalDateTime currentDate = LocalDateTime.now();
            boolean expired = startDate.isBefore(currentDate);

//            LOG.info("startDate : " + startDate + " currentDate : " + currentDate);

            if (expired) {
               calender.setStatusAcara(StatusAcaraEnum.SUDAH_TERLAKSANA.ordinal());
               CalenderEventLocalServiceUtil.updateCalenderEvent(calender);
            }

            String registrationLimitDate = "";
            if (Validator.isNotNull(calender.getRegistrationLimitDate())) {
               registrationLimitDate = DateUtil.convertDateToStringIndo(calender.getRegistrationLimitDate(), true);
            }

            String registrationLimitDateHours = "";
            if (Validator.isNotNull(calender.getRegistrationLimitDateHours())) {
               registrationLimitDateHours = calender.getRegistrationLimitDateHours();
            }

            dto.put("lokasi", calender.getLocation());
            dto.put("linkMeeting", calender.getLinkMeeting());
            dto.put("startDate", DateUtil.convertDateToStringIndo(calender.getStartDate(), true) + " " + calender.getStartDateHours());
            dto.put("startDateHours", calender.getStartDateHours());
            dto.put("endDate", DateUtil.convertDateToStringIndo(calender.getEndDate(), true));
            dto.put("endDateHours", calender.getEndDateHours());
            dto.put("registrationLimitDate", registrationLimitDate);
            dto.put("registrationLimitDateHours", registrationLimitDateHours);
            dto.put("deskripsi", calender.getDeskripsi());
            dto.put("imageId", calender.getImageId());
            dto.put("imageName", calender.getImageName());
            dto.put("imagePath", calender.getImagePath());
            jsonData.put(dto);
         }
         recordsFiltered = recordsTotal;
      } catch (Exception e) {
         LOG.error(e);
      }
      JSONObject jsonObject = FORMAT(recordsTotal, recordsFiltered, Integer.parseInt(draw), jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}