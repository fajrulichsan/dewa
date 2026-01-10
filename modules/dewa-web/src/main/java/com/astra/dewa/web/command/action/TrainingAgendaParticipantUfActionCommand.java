package com.astra.dewa.web.command.action;

import com.astra.dewa.model.TrainingAgendaParticipantUf;
import com.astra.dewa.service.TrainingAgendaParticipantUfLocalServiceUtil;
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
      "mvc.command.name=/training-agenda-participant-uf-action"
   },
   service = MVCResourceCommand.class
)
public class TrainingAgendaParticipantUfActionCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(TrainingAgendaParticipantUfActionCommand.class);
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
         long fileId = ParamUtil.getLong(uploadPortletRequest, "participantUploadFileId");
         String fileName = ParamUtil.getString(uploadPortletRequest, "participantUploadFileName");
         String filePath = ParamUtil.getString(uploadPortletRequest, "participantUploadFilePath");
         String trainingAgendaId = ParamUtil.getString(uploadPortletRequest, "trainingAgendaId");
         int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerIdUpload");

         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
         Date date = new Date();
         TrainingAgendaParticipantUf participant = null;
         if (crudType.equalsIgnoreCase("create")) {
            participant = TrainingAgendaParticipantUfLocalServiceUtil.createTrainingAgendaParticipantUf(0);
         } else {
            participant = TrainingAgendaParticipantUfLocalServiceUtil.getTrainingAgendaParticipantUf(Integer.parseInt(entryId));
         }
         participant.setFileId(fileId);
         participant.setFileName(fileName);
         participant.setFilePath(filePath);
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

   private JSONObject create(TrainingAgendaParticipantUf participantUf, Date date) {
      try {
         participantUf.setRowStatus(true);
         participantUf.setCreatedDate(date);
         participantUf.setCreatedBy(user.getScreenName());
         participantUf.setModifiedDate(date);
         participantUf.setModifiedBy(user.getScreenName());

         TrainingAgendaParticipantUfLocalServiceUtil.updateTrainingAgendaParticipantUf(participantUf);
         return SUCCESS("Data tersimpan", String.valueOf(participantUf.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(TrainingAgendaParticipantUf participantUf, Date date) {
      try {
         participantUf.setModifiedDate(date);
         participantUf.setModifiedBy(user.getScreenName());

         TrainingAgendaParticipantUfLocalServiceUtil.updateTrainingAgendaParticipantUf(participantUf);
         return SUCCESS("Data terupdate.", String.valueOf(participantUf.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(String entryId) {
      try {
         TrainingAgendaParticipantUf participantUf = TrainingAgendaParticipantUfLocalServiceUtil.getTrainingAgendaParticipantUf(Integer.parseInt(entryId));
         participantUf.setRowStatus(false);
         TrainingAgendaParticipantUfLocalServiceUtil.updateTrainingAgendaParticipantUf(participantUf);
         return SUCCESS("Data terupdate.", entryId);
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   /*
   private boolean isExist(String name) {
      DynamicQuery query = TrainingAgendaParticipantUfLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      List<TrainingAgendaParticipantUf> participantUfs = TrainingAgendaParticipantUfLocalServiceUtil.dynamicQuery(query);
      return participantUfs.size() > 0;
   }
   */
}
