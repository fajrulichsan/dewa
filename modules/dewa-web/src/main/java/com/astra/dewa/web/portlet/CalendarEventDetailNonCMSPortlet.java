package com.astra.dewa.web.portlet;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.model.CalenderEventParticipant;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Setting;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.service.CalenderEventParticipantLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.MailUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.StatusAcaraEnum;
import com.astra.dewa.utils.StringUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

@Component
      (
            immediate = true,
            property = {
                  "com.liferay.portlet.display-category=Dewa UI",
                  "com.liferay.portlet.instanceable=true",
                  "javax.portlet.display-name=Calender Event Detail NonCMS",
                  "javax.portlet.init-param.template-path=/",
                  "javax.portlet.init-param.view-template=/calender/non-cms/detail.jsp",
                  "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT_DETAIL_NONCMS,
                  "javax.portlet.resource-bundle=content.Language",
                  "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
      )
public class CalendarEventDetailNonCMSPortlet extends MVCPortlet {
   private final Log LOG = LogFactoryUtil.getLog(CalendarEventDetailNonCMSPortlet.class);
   private HttpServletRequest httpRequest;

   public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
      ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
      this.httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
      String acaraId = ParamUtil.getString(httpRequest, "id");
      boolean notDSO = false;
      try {
//         DynamicQuery dynamicQuery = UsersDealersLocalServiceUtil.dynamicQuery();
//         dynamicQuery.add(RestrictionsFactoryUtil.eq("UserId", Long.valueOf(themeDisplay.getUserId())));
//         List<UsersDealers> usersDealersList = UsersDealersLocalServiceUtil.dynamicQuery(dynamicQuery);
//         notDSO = usersDealersList.get(0).getRoleId() != RolesEnum.DSO.ordinal();

         if (!RoleDealerUtils.isSuperUser(themeDisplay.getUserId())) {
            notDSO = true;
         }
         LOG.info("User in DSO Group: " + notDSO);
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }
      renderRequest.setAttribute("acaraId", acaraId);
      renderRequest.setAttribute("notDSO", notDSO);
      super.doView(renderRequest, renderResponse);
   }

   public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
      PrintWriter writer = resourceResponse.getWriter();

      this.httpRequest = PortalUtil.getHttpServletRequest(resourceRequest);
      String state = ParamUtil.getString(httpRequest, "state");
      int id = ParamUtil.getInteger(httpRequest, "id");

      try {
         // CSRF Authentication
         AuthTokenUtil.checkCSRFToken(httpRequest, this.getClass().getName());

         LOG.info("state: " + state);
         if (state.equalsIgnoreCase("datalist")) {
            DynamicQuery query = CalenderEventLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.gt("StartDate", new Date()));
            query.add(RestrictionsFactoryUtil.eq("RowStatus", Boolean.valueOf(true)));
            query.add(RestrictionsFactoryUtil.ne("Id", Integer.valueOf(id)));
            Order defaultOrder = OrderFactoryUtil.asc("StartDate");
            query.addOrder(defaultOrder);
            List<CalenderEvent> acara = CalenderEventLocalServiceUtil.dynamicQuery(query, 0, 5);
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            if (!acara.isEmpty())
               for (CalenderEvent row : acara) {
                  JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
                  String day = DateUtil.getDays(row.getStartDate());
                  String date = DateUtil.dateToString(row.getStartDate(), "dd MMMM yyyy");
                  String time = row.getStartDateHours();
                  jsonObj.put("id", row.getId());
                  jsonObj.put("title", row.getJudul());
                  jsonObj.put("desc", row.getDeskripsi());
                  jsonObj.put("status", row.getRowStatus());
                  jsonObj.put("lokasi", row.getLocation());
                  jsonObj.put("day", day);
                  jsonObj.put("date", date);
                  jsonObj.put("time", time);
                  jsonObj.put("image", row.getImagePath());
                  jsonObj.put("url", "/group/dealink/calendar-detail?id=" + row.getId());
                  jsonArray.put(jsonObj);
               }
            JSONObject tableData = JSONFactoryUtil.createJSONObject();
            tableData.put("data", jsonArray);
            LOG.info("acara =>" + tableData.toString());
            writer.print(tableData);
         } else if (state.equalsIgnoreCase("getbyid")) {
            CalenderEvent row = null;
            try {
               row = CalenderEventLocalServiceUtil.getCalenderEvent(id);
            } catch (PortalException e) {
               e.printStackTrace();
               System.out.println("eror id => eror");
            }
            JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
            if (StringUtil.hasValue(row)) {
               String day = DateUtil.getDays(row.getStartDate());
               String date = DateUtil.dateToString(row.getStartDate(), "dd MMMM yyyy");
               String time = row.getStartDateHours();
               String status = "";
               boolean isOpen = true;
               if (row.getStatusAcara() == StatusAcaraEnum.BELUM_TERLAKSANA.ordinal()) {
                  status = "Tersedia";
                  if (Validator.isNull(row.getRegistrationLimitDate())) {
                     isOpen = false;
                  }
               } else {
                  status = "Tidak Tersedia";
                  isOpen = false;
               }
               String locationOrLink = "";
               if (row.getJenisAcara() == 0) {
                  locationOrLink = row.getLocation();
               } else {
                  locationOrLink = row.getLinkMeeting();
               }
               
               LOG.debug("open registration: " + isOpen);
               
               jsonObj.put("id", row.getId());
               jsonObj.put("title", HtmlUtil.escape(row.getJudul()));
               jsonObj.put("desc", row.getDeskripsi());
               jsonObj.put("status", status);
               jsonObj.put("isOpen", isOpen);
               jsonObj.put("lokasi", locationOrLink);
               jsonObj.put("day", day);
               jsonObj.put("date", date);
               jsonObj.put("time", time);
               jsonObj.put("image", row.getImagePath());
               jsonObj.put("url", "/group/dealink/calendar-detail?id=" + row.getId());
               jsonObj.put("jenisAcara", row.getJenisAcara());
            }
            JSONObject tableData = JSONFactoryUtil.createJSONObject();
            tableData.put("data", jsonObj);
            writer.print(tableData);
         } else if (state.equalsIgnoreCase("post")) {
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
            String[] name = ParamUtil.getParameterValues(resourceRequest, "name", null);
            String[] email = ParamUtil.getParameterValues(resourceRequest, "email", null);
            String[] phone = ParamUtil.getParameterValues(resourceRequest, "phone", null);
            String[] company = ParamUtil.getParameterValues(resourceRequest, "company", null);

            if (FilterXSS.checkXSS(name) ||
                  FilterXSS.checkXSS(email) ||
                  FilterXSS.checkXSS(phone) ||
                  FilterXSS.checkXSSEncoded(name) ||
                  FilterXSS.checkXSSEncoded(email) ||
                  FilterXSS.checkXSSEncoded(phone)) {
               System.out.println("XSS Payload Detected");
               writer.print("error");
               return;
            }

            LOG.info("CHECK 1");
            CalenderEvent acr = null;
            try {
               acr = CalenderEventLocalServiceUtil.getCalenderEvent(id);
            } catch (PortalException e) {
               e.printStackTrace();
            }

            LOG.info("CHECK 2");

            boolean isClosed = false;
            assert acr != null;

            if (Validator.isNotNull(acr.getRegistrationLimitDate())) {
               Date registrationLimitDate = changeDateValidation(acr.getRegistrationLimitDate(), acr.getRegistrationLimitDateHours());
               int compareInt = (new Date()).compareTo(registrationLimitDate);
               if (compareInt > 0) { isClosed = true; }
            } else {
               isClosed = true;
            }

//            if ((new Date()).after(acr.getRegistrationLimitDate())) {
            if (isClosed) {
               writer.print("waktuHabis");
            } else {
               // UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
               // File[] files = uploadPortletRequest.getFiles("file");
               try {
                  LOG.info("CHECK 3");
                  for (int i = 0; i < name.length; i++) {
                     if (!InputValidationUtils.isEmailValid(email[i])) {
                        throw new SystemException("Invalid email format: " + email[i]);
                     }
                     if (!InputValidationUtils.isNumberOnly(phone[i])) {
                        throw new SystemException("Invalid phone number format: " + phone[i]);
                     }
                     if (!InputValidationUtils.isBasicCharacter(name[i])) {
                        throw new SystemException("Invalid name format: " + name[i]);
                     }

                     CalenderEventParticipant reg = CalenderEventParticipantLocalServiceUtil.createCalenderEventParticipant(0);
                     reg.setCalenderEventId(id);
                     reg.setFullName(name[i]);
                     reg.setEmail(email[i]);
                     reg.setPhone(phone[i]);
                     reg.setDealerId(Integer.parseInt(company[i]));
                     reg.setCreatedBy(themeDisplay.getUser().getFullName());
                     reg.setCreatedDate(new Date());
                     reg.setModifiedBy(themeDisplay.getUser().getFullName());
                     reg.setModifiedDate(new Date());
                     reg.setRowStatus(Boolean.valueOf(true));
                     reg = CalenderEventParticipantLocalServiceUtil.addCalenderEventParticipant(reg);
                     LOG.info("CHECK 4");
                     CalenderEvent calenderEvent = CalenderEventLocalServiceUtil.getCalenderEvent(id);
                     String dateStart = DateUtil.dateToString(calenderEvent.getStartDate(), "dd MMMM yyyy") + " " + calenderEvent.getStartDateHours();
                     String dateEnd = DateUtil.dateToString(calenderEvent.getEndDate(), "dd MMMM yyyy") + " " + calenderEvent.getEndDateHours();
                     String lokasiOrLink = "";
                     if (calenderEvent.getJenisAcara() == 0) {
                        lokasiOrLink = calenderEvent.getLocation();
                     } else {
                        lokasiOrLink = calenderEvent.getLinkMeeting();
                     }

                     LOG.info("SEND EMAIL DEWA");
                     Dealer dealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(company[i]));
                     String subject = dealer.getCode() + " - " + calenderEvent.getJudul();
                     LOG.info("subject: " + subject);
                     Setting bodyMail = SettingLocalServiceUtil.findCredential("mail", "body_calendar_event");
                     LOG.info("body1: " + bodyMail.toString());
                     String body = bodyMail.getValue();
                     LOG.info("body2: " + body);
                     body = MailUtil.replace(body, new String[]{"[nama peserta]", "[nama agenda]", "[tanggal mulai]", "[tanggal selesai]", "[Lokasi/Link Meeting]"}, new String[]{name[i], calenderEvent.getJudul(), dateStart, dateEnd, lokasiOrLink});
                     LOG.info("body: " + body);
                     String startDateEmail = changeDateToSDF(calenderEvent.getStartDate(), calenderEvent.getStartDateHours());
                     LOG.info("startDateEmail: " + startDateEmail);
                     String endDateEmail = changeDateToSDF(calenderEvent.getEndDate(), calenderEvent.getEndDateHours());

                     LOG.info("subject: " + subject);
                     LOG.info("body: " + body);
                     LOG.info("startDateEmail: " + startDateEmail);
                     LOG.info("endDateEmail: " + endDateEmail);
                     LOG.info("email: " + email[i]);

                     MailUtil.sendEmailAndCalendar(new String[]{email[i]}, null, subject, body, startDateEmail, endDateEmail);
                  }
                  writer.print("success");
               } catch (Exception e) {
                  if (e instanceof SystemException) {
                     writer.print("Error");
                  } else {
                     e.printStackTrace();
                     writer.print("error");
                  }
               }
            }
         } else if (state.equalsIgnoreCase("company")) {
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            try {
               jsonArray = DealerUtils.selectDealerAndCabang();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
            JSONObject tableData = JSONFactoryUtil.createJSONObject();
            tableData.put("data", jsonArray);
            writer.print(tableData);
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpRequest, "p_auth", "none"), e);
         } else {
            LOG.error(e);
         }
      }
      super.serveResource(resourceRequest, resourceResponse);
   }

   private String changeDateToSDF(Date date, String hour) {
      String result = "";
      try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
         SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat sdfDateMinute = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         String dateAfter = sdfDate.format(date) + " " + hour;
         Date dateFinal = sdfDateMinute.parse(dateAfter);
         result = sdf.format(dateFinal);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }

   private Date changeDateValidation(Date date, String hour) {
      Date mergedDateTime = null;
      try {
         SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
         Date timeValue = timeFormat.parse(hour);
         long dateTimeValue = date.getTime();
         mergedDateTime = new Date(dateTimeValue + timeValue.getTime());
      } catch (Exception e) {
         e.printStackTrace();
      }
      return mergedDateTime;
   }
}