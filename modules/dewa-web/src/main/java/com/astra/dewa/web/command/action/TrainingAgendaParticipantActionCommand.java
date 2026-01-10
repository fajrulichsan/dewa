package com.astra.dewa.web.command.action;

import com.astra.dewa.model.TrainingAgendaParticipant;
import com.astra.dewa.service.TrainingAgendaParticipantLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/training-peserta-manual-action"
   },
   service = MVCResourceCommand.class
)
public class TrainingAgendaParticipantActionCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(TrainingAgendaParticipantActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      // ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      this.user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

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
         String entryId = ParamUtil.getString(uploadPortletRequest, "entryId");
         String fullName = ParamUtil.getString(uploadPortletRequest, "fullName");
         String email = ParamUtil.getString(uploadPortletRequest, "email");
         String phone = ParamUtil.getString(uploadPortletRequest, "phone");
         String trainingAgendaId = ParamUtil.getString(uploadPortletRequest, "trainingAgendaId");
         int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");

         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
         Date date = new Date();
         TrainingAgendaParticipant participant = null;
         if (crudType.equalsIgnoreCase("create")) {
            participant = TrainingAgendaParticipantLocalServiceUtil.createTrainingAgendaParticipant(0);
         } else {
            participant = TrainingAgendaParticipantLocalServiceUtil.getTrainingAgendaParticipant(Integer.parseInt(entryId));
         }
         participant.setFullName(fullName);
         participant.setEmail(email);
         participant.setPhone(phone);
         participant.setTrainingAgendaId(Integer.parseInt(trainingAgendaId));
         participant.setDealerId(dealerId);

         if (crudType.equalsIgnoreCase("delete")) {
            jsonObject = delete(entryId);
         } else if (crudType.equalsIgnoreCase("create")) {
            jsonObject = create(participant, date);
         } else if (crudType.equalsIgnoreCase("update")) {
            jsonObject = update(participant, date);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(TrainingAgendaParticipant participant, Date date) {
      try {
         participant.setRowStatus(true);
         participant.setCreatedDate(date);
         participant.setCreatedBy(user.getScreenName());
         participant.setModifiedDate(date);
         participant.setModifiedBy(user.getScreenName());

         TrainingAgendaParticipantLocalServiceUtil.updateTrainingAgendaParticipant(participant);
         return SUCCESS("Data tersimpan", String.valueOf(participant.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(TrainingAgendaParticipant participant, Date date) {
      try {
         participant.setModifiedDate(date);
         participant.setModifiedBy(user.getScreenName());

         TrainingAgendaParticipantLocalServiceUtil.updateTrainingAgendaParticipant(participant);
         return SUCCESS("Data terupdate.", String.valueOf(participant.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(String entryId) {
      try {
         TrainingAgendaParticipant participant = TrainingAgendaParticipantLocalServiceUtil.getTrainingAgendaParticipant(Integer.parseInt(entryId));
         participant.setRowStatus(false);
         TrainingAgendaParticipantLocalServiceUtil.updateTrainingAgendaParticipant(participant);
         return SUCCESS("Data terupdate.", entryId);
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   /*
   private boolean isExist(String name) {
      DynamicQuery query = TrainingAgendaParticipantLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      List<TrainingAgendaParticipant> participants = TrainingAgendaParticipantLocalServiceUtil.dynamicQuery(query);
      return participants.size() > 0;
   }
   */
}
