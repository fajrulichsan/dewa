package com.astra.dewa.web.portlet;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.StatusAcaraEnum;
import com.astra.dewa.utils.StringUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

@Component
      (
            immediate = true,
            property = {
                  "com.liferay.portlet.display-category=Dewa UI",
                  "com.liferay.portlet.instanceable=true",
                  "javax.portlet.display-name=Calender Event NonCMS",
                  "javax.portlet.init-param.template-path=/",
                  "javax.portlet.init-param.view-template=/calender/non-cms/view.jsp",
                  "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT_NONCMS,
                  "javax.portlet.resource-bundle=content.Language", "" +
                  "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
      )
public class CalendarEventNonCMSPortlet extends MVCPortlet {
   private final Log LOG = LogFactoryUtil.getLog(CalendarEventNonCMSPortlet.class);

   public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
      HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(resourceRequest);

      String state = ParamUtil.getString(httpServletRequest, "state");

      PrintWriter writer = resourceResponse.getWriter();
      try {
         // CSRF Authentication
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         if (state.equalsIgnoreCase("calendar")) {
            DynamicQuery query = CalenderEventLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("RowStatus", Boolean.valueOf(true)));
            List<CalenderEvent> acara = CalenderEventLocalServiceUtil.dynamicQuery(query, -1, -1);
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            if (!acara.isEmpty())
               for (CalenderEvent row : acara) {
                  JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
                  String color = "#f1ea0a";
                  String url = "/group/dealink/calendar-detail?id=" + row.getId();
                  String body = String.format("<i class=\"fas fa-link\" style='margin-right: 3px;'></i><a data-senna-off=\"true\" href='%s'>&nbsp;Lihat Detail</a>", new Object[]{url});
                  String date = DateUtil.dateToString(row.getStartDate(), "dd MMMM yyyy");
                  String date2 = DateUtil.dateToString(row.getEndDate(), "dd MMMM yyyy");
                  String locationOrLink = "";
                  if (row.getJenisAcara() == 0) {
                     locationOrLink = row.getLocation();
                  } else {
                     locationOrLink = String.format("online: <a href='%s'>link meeting</a>", new Object[]{row.getLinkMeeting()});
                  }
                  jsonObj.put("id", row.getId());
                  jsonObj.put("calendarId", "1");
                  jsonObj.put("title", HtmlUtil.escape(row.getJudul()));
                  jsonObj.put("location", locationOrLink);
                  jsonObj.put("start", date + " " + row.getStartDateHours());
                  jsonObj.put("end", date2 + " " + row.getEndDateHours());
                  jsonObj.put("color", "#000000");
                  jsonObj.put("bgColor", color);
                  jsonObj.put("dragBgColor", color);
                  jsonObj.put("borderColor", "#000000");
                  jsonObj.put("category", "time");
                  jsonObj.put("body", body);
                  jsonArray.put(jsonObj);
               }
            JSONObject tableData = JSONFactoryUtil.createJSONObject();
            tableData.put("data", jsonArray);
            writer.print(tableData);
         } else if (state.equalsIgnoreCase("acara")) {
            DynamicQuery query = CalenderEventLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.gt("StartDate", new Date()));
            query.add(RestrictionsFactoryUtil.eq("RowStatus", Boolean.valueOf(true)));
            Order defaultOrder = OrderFactoryUtil.asc("StartDate");
            query.addOrder(defaultOrder);
            List<CalenderEvent> acara = CalenderEventLocalServiceUtil.dynamicQuery(query, 0, 3);
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            if (!acara.isEmpty())
               for (CalenderEvent row : acara) {
                  JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
                  String date = DateUtil.dateToString(row.getStartDate(), "dd MMMM yyyy");
                  // String time = DateUtil.dateToString(row.getStartDate(), "HH:mm");
                  String date2 = DateUtil.dateToString(row.getEndDate(), "dd MMMM yyyy");
                  // String time2 = DateUtil.dateToString(row.getEndDate(), "HH:mm");
                  String color = "#f1ea0a";
                  String locationOrLink = "";
                  if (row.getJenisAcara() == 0) {
                     locationOrLink = row.getLocation();
                  } else {
                     locationOrLink = String.format("online: <a href='%s'>link meeting</a>", new Object[]{row.getLinkMeeting()});
                  }
                  jsonObj.put("id", row.getId());
                  jsonObj.put("title", HtmlUtil.escape(row.getJudul()));
                  jsonObj.put("status", row.getStatusAcara());
                  jsonObj.put("lokasi", locationOrLink);
                  jsonObj.put("date", date + " " + row.getStartDateHours());
                  jsonObj.put("date2", date2 + " " + row.getEndDateHours());
                  jsonObj.put("color", color);
                  jsonObj.put("url", "/group/dealink/calendar-detail?id=" + row.getId());
                  jsonArray.put(jsonObj);
               }
            JSONObject tableData = JSONFactoryUtil.createJSONObject();
            tableData.put("data", jsonArray);
            writer.print(tableData);
         } else if (state.equalsIgnoreCase("reloadstatus")) {
            DynamicQuery query = CalenderEventLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.lt("StartDate", new Date()));
            query.add(RestrictionsFactoryUtil.eq("RowStatus", Boolean.valueOf(true)));
            query.add(RestrictionsFactoryUtil.eq("StatusAcara", Integer.valueOf(StatusAcaraEnum.BELUM_TERLAKSANA.ordinal())));
            List<CalenderEvent> acara = CalenderEventLocalServiceUtil.dynamicQuery(query, -1, -1);
            if (!acara.isEmpty())
               for (CalenderEvent row : acara) {
                  CalenderEvent agendaAcara = null;
                  try {
                     agendaAcara = CalenderEventLocalServiceUtil.getCalenderEvent(row.getId());
                  } catch (PortalException e) {
                     e.printStackTrace();
                  }
                  if (StringUtil.hasValue(agendaAcara)) {
                     agendaAcara.setStatusAcara(StatusAcaraEnum.SUDAH_TERLAKSANA.ordinal());
                     agendaAcara.setModifiedDate(new Date());
                     CalenderEventLocalServiceUtil.updateCalenderEvent(agendaAcara);
                  }
               }
            writer.print("success");
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
         } else {
            LOG.error(e);
         }
      }
      super.serveResource(resourceRequest, resourceResponse);
   }
}