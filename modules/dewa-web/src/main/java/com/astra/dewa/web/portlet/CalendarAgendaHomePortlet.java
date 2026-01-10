package com.astra.dewa.web.portlet;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
      immediate = true,
      property = {
            "com.liferay.portlet.display-category=Dewa UI",
            "com.liferay.portlet.instanceable=false",
            "javax.portlet.display-name=Home Agenda",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/home/agenda.jsp",
            "javax.portlet.name=" + DewaWebPortletKeys.AGENDA_HOMEPAGE,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)
public class CalendarAgendaHomePortlet extends MVCPortlet {

   @Override
   public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

      List<CalenderEvent> acaraList = new ArrayList<>();
      JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      try {
         DynamicQuery query = CalenderEventLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.gt("StartDate", new Date()));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         Order defaultOrder = OrderFactoryUtil.asc("StartDate");
         query.addOrder(defaultOrder);
         acaraList = CalenderEventLocalServiceUtil.dynamicQuery(query);

         for (CalenderEvent calenderEvent : acaraList) {
            String startDate = DateUtil.dateToString(calenderEvent.getStartDate(), "dd MMMM yyyy") + " " + calenderEvent.getStartDateHours();
            String endDate = DateUtil.dateToString(calenderEvent.getEndDate(), "dd MMMM yyyy") + " " + calenderEvent.getEndDateHours();
            String lokasiOrLink = "";
            if (calenderEvent.getJenisAcara() == 0) {
               lokasiOrLink = calenderEvent.getLocation();
            } else {
               lokasiOrLink = calenderEvent.getLinkMeeting();
            }
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(calenderEvent.getImageId());
            String urlImage = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                  themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

            jsonObject.put("id", calenderEvent.getId());
            jsonObject.put("image", urlImage);
            jsonObject.put("judul", calenderEvent.getJudul());
            jsonObject.put("date", startDate + " - " + endDate);
            jsonObject.put("location", lokasiOrLink);
            jsonArray.put(jsonObject);
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      request.setAttribute("acaraList", jsonArray);
      super.render(request, response);
   }

   @Override
   public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
      super.serveResource(request, response);
   }

}