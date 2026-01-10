package com.astra.dewa.web.command.action;

import com.astra.dewa.model.CalenderEventParticipant;
import com.astra.dewa.service.CalenderEventParticipantLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.Enumeration;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
      "mvc.command.name=calender-event-participant-action"
   },
   service = MVCResourceCommand.class
)
public class CalenderEventParticipantActionCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(CalenderEventParticipantActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      if (isRequestContainsXSS) {
         jsonObject = ERROR("Your request contains XSS payload.");
      } else {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         int calenderEventId = ParamUtil.getInteger(uploadPortletRequest, "calenderEventId");
         int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
         String fullName = ParamUtil.getString(uploadPortletRequest, "fullName");
         String email = ParamUtil.getString(uploadPortletRequest, "email");
         String phone = ParamUtil.getString(uploadPortletRequest, "phone");

         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
         Date date = new Date();
         CalenderEventParticipant participant = null;
         if (crudType.equalsIgnoreCase("create")) {
            participant = CalenderEventParticipantLocalServiceUtil.createCalenderEventParticipant(-1);
         } else {
            participant = CalenderEventParticipantLocalServiceUtil.getCalenderEventParticipant(entryId);
         }
         participant.setCalenderEventId(calenderEventId);
         participant.setDealerId(dealerId);
         participant.setFullName(fullName);
         participant.setEmail(email);
         participant.setPhone(phone);

         if (crudType.equalsIgnoreCase("delete")) {
            jsonObject = delete();
         } else if (crudType.equalsIgnoreCase("create")) {
            jsonObject = create(participant, date);
         } else if (crudType.equalsIgnoreCase("update")) {
            jsonObject = update(participant, date);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(CalenderEventParticipant participant, Date date) {
      try {
         participant.setCreatedDate(date);
         participant.setCreatedBy(user.getScreenName());
         participant.setModifiedDate(date);
         participant.setModifiedBy(user.getScreenName());
         participant.setRowStatus(true);

         CalenderEventParticipantLocalServiceUtil.updateCalenderEventParticipant(participant);
         return SUCCESS("Data tersimpan", String.valueOf(participant.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(CalenderEventParticipant participant, Date date) {
      try {
         participant.setModifiedDate(date);
         participant.setModifiedBy(user.getScreenName());

         CalenderEventParticipantLocalServiceUtil.updateCalenderEventParticipant(participant);
         return SUCCESS("Data terupdate.", String.valueOf(participant.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete() {
      try {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         CalenderEventParticipant participant = CalenderEventParticipantLocalServiceUtil.getCalenderEventParticipant(entryId);
         participant.setRowStatus(false);
         CalenderEventParticipantLocalServiceUtil.updateCalenderEventParticipant(participant);
         return SUCCESS("Data terupdate.", String.valueOf(entryId));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

}
