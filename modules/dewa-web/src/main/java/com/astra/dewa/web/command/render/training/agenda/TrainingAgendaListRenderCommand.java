package com.astra.dewa.web.command.render.training.agenda;

import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.StatusAgendaEnum;
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
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=/training-agenda-list"
      },
      service = MVCResourceCommand.class
)
public class TrainingAgendaListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TrainingAgendaListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      String statusAgendaId = httpReq.getParameter("statusAgendaId");
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
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         DynamicQuery query = TrainingAgendaLocalServiceUtil.dynamicQuery();
         DynamicQuery queryCount = TrainingAgendaLocalServiceUtil.dynamicQuery();

         if (!statusAgendaId.equalsIgnoreCase("ALL") && !statusAgendaId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("StatusAgenda", Integer.parseInt(statusAgendaId)));
            queryCount.add(RestrictionsFactoryUtil.eq("StatusAgenda", Integer.parseInt(statusAgendaId)));
         }

         List<TrainingAgenda> agendas = new ArrayList<>();

         // Searching
         if (!search.isEmpty()) {
            query.add(RestrictionsFactoryUtil.like("Judul", "%" + search + "%"));
            queryCount.add(RestrictionsFactoryUtil.like("Judul", "%" + search + "%"));
         }

         // Order By
         int column = 0;
         if (orderColumn != null && !orderColumn.isEmpty()) {
            column = Integer.parseInt(orderColumn);
         }
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
         } else if (column == 3) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("JenisAgenda"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("JenisAgenda"));
            }
         } else if (column == 4) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("StatusAgenda"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("StatusAgenda"));
            }
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         queryCount.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         agendas = TrainingAgendaLocalServiceUtil.dynamicQuery(query, first, end);
         recordsTotal = TrainingAgendaLocalServiceUtil.dynamicQueryCount(queryCount);

         for (TrainingAgenda agenda : agendas) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", agenda.getId());
            dto.put("judul", HtmlUtil.escape(agenda.getJudul()));
            dto.put("jenisAgenda", agenda.getJenisAgenda());
            dto.put("statusAgenda", agenda.getStatusAgenda());
            LocalDateTime startDate = DateUtil.convertToLocalDateTimeViaInstant(agenda.getStartDate());
            LocalDateTime currentDate = LocalDateTime.now();
            boolean expired = startDate.isBefore(currentDate);
//            LOG.info("startDate : " + startDate + " currentDate : " + currentDate);
            if (expired) {
               agenda.setStatusAgenda(StatusAgendaEnum.SUDAH_TERLAKSANA.ordinal());
               TrainingAgendaLocalServiceUtil.updateTrainingAgenda(agenda);
            }
            dto.put("lokasi", HtmlUtil.escape(agenda.getLocation()));
            dto.put("linkMeeting", agenda.getLinkMeeting());
            dto.put("startDate", DateUtil.convertDateToStringIndo(agenda.getStartDate(), true));
            dto.put("startDateHours", HtmlUtil.escape(agenda.getStartDateHours()));
            dto.put("endDate", DateUtil.convertDateToStringIndo(agenda.getEndDate(), true));
            dto.put("endDateHours", HtmlUtil.escape(agenda.getEndDateHours()));
            dto.put("registrationLimitDate", DateUtil.convertDateToStringIndo(agenda.getRegistrationLimitDate(), true));
            dto.put("registrationLimitDateHours", HtmlUtil.escape(agenda.getRegistrationLimitDateHours()));
            dto.put("deskripsi", HtmlUtil.escape(agenda.getDeskripsi()));
            dto.put("imageId", agenda.getImageId());
            dto.put("imageName", HtmlUtil.escape(agenda.getImageName()));
            dto.put("imagePath", HtmlUtil.escape(agenda.getImagePath()));
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