package com.astra.dewa.web.command.action;

import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.model.TrainingAgendaParticipant;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaParticipantLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.StatusAgendaEnum;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/training-agenda-action"
   },
   service = MVCResourceCommand.class
)
public class TrainingAgendaActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TrainingAgendaActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private ThemeDisplay themeDisplay;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      this.themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
//      ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      String entryId = ParamUtil.getString(uploadPortletRequest, "entryId");
      String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            Date date = new Date();
            TrainingAgenda agenda = null;

            switch (crudType) {
               case CREATE:
                  agenda = TrainingAgendaLocalServiceUtil.createTrainingAgenda(0);
                  setData(agenda);
                  jsonObject = create(agenda, date);
                  break;
               case UPDATE:
                  agenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(Integer.parseInt(entryId));
                  setData(agenda);
                  jsonObject = update(agenda, date);
                  break;
               case DELETE:
                  agenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(Integer.parseInt(entryId));
                  jsonObject = delete(agenda, date);
                  break;
               default:
                  jsonObject = WARNING("Bad request!");
                  break;
            }
         }
         /*
         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else if (crudType.equalsIgnoreCase("delete")) {
            jsonObject = delete(Integer.parseInt(entryId));
         } else {
            String judul = ParamUtil.getString(uploadPortletRequest, "judulAgenda");
            String jenisAgendaId = ParamUtil.getString(uploadPortletRequest, "jenisAgendaId");
            String lokasi = ParamUtil.getString(uploadPortletRequest, "lokasiAgenda");
            String linkMeeting = ParamUtil.getString(uploadPortletRequest, "linkAgenda");

            // startDate
            String tglAwal = ParamUtil.getString(uploadPortletRequest, "startDate");
            LocalDateTime tglAwalDate = DateUtil.stringToLocalDateTime(tglAwal);
            Date startDate = DateUtil.convertToDateViaInstant(tglAwalDate.toLocalDate());
            startDate.setHours(tglAwalDate.getHour());
            startDate.setMinutes(tglAwalDate.getMinute());
            String startDateHours = DateUtil.localDateTimeToStringHours(tglAwalDate);

            // endDate
            String tglAkhir = ParamUtil.getString(uploadPortletRequest, "endDate");
            LocalDateTime tglAkhirDate = DateUtil.stringToLocalDateTime(tglAkhir);
            Date endDate = DateUtil.convertToDateViaInstant(tglAkhirDate.toLocalDate());
            endDate.setHours(tglAkhirDate.getHour());
            endDate.setMinutes(tglAkhirDate.getMinute());
            String endDateHours = DateUtil.localDateTimeToStringHours(tglAkhirDate);

            // registrationDate
            String registrationDate = ParamUtil.getString(uploadPortletRequest, "registrationDate");
            LocalDateTime tglRegistration = DateUtil.stringToLocalDateTime(registrationDate);
            Date registrationLimitDate = DateUtil.convertToDateViaInstant(tglRegistration.toLocalDate());
            registrationLimitDate.setHours(tglRegistration.getHour());
            registrationLimitDate.setMinutes(tglRegistration.getMinute());
            String registerEndDateHours = DateUtil.localDateTimeToStringHours(tglRegistration);

            String deskripsi = ParamUtil.getString(uploadPortletRequest, "deskripsi");

            // Image
            long imageId = ParamUtil.getLong(uploadPortletRequest, "trainingAgendaImageFileId");
            String imageName = ParamUtil.getString(uploadPortletRequest, "trainingAgendaImageFileName");
            String imagePath = ParamUtil.getString(uploadPortletRequest, "trainingAgendaImageFilePath");

            if (imagePath != null) {
               String lowerCaseImagePath = imagePath.toLowerCase();
               if (!(lowerCaseImagePath.endsWith(".jpg") || lowerCaseImagePath.endsWith(".jpeg") ||
                     lowerCaseImagePath.endsWith(".png") || lowerCaseImagePath.endsWith(".gif"))) {
                  JSONObject errorJson = ERROR("Hanya file dengan format jpg, jpeg, png, dan gif yang diperbolehkan.");
                  ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), errorJson.toJSONString());
                  return;
               }
            }

            Date date = new Date();
            TrainingAgenda agenda = null;
            if (crudType.equalsIgnoreCase("create")) {
               agenda = TrainingAgendaLocalServiceUtil.createTrainingAgenda(0);
            } else {
               agenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(Integer.parseInt(entryId));
            }
            if (!crudType.equalsIgnoreCase("delete")) {
               agenda.setJudul(judul);
               agenda.setJenisAgenda(Integer.parseInt(jenisAgendaId));

               agenda.setLocation(lokasi);
               agenda.setLinkMeeting(linkMeeting);
               agenda.setStartDate(startDate);
               agenda.setStartDateHours(startDateHours);
               agenda.setEndDate(endDate);
               agenda.setEndDateHours(endDateHours);
               agenda.setRegistrationLimitDate(registrationLimitDate);
               agenda.setRegistrationLimitDateHours(registerEndDateHours);
               agenda.setDeskripsi(deskripsi);
               agenda.setImageId(imageId);
               agenda.setImageName(imageName);
               agenda.setImagePath(imagePath);
            }
            if (crudType.equalsIgnoreCase("create")) {
               jsonObject = create(agenda, date);
            } else if (crudType.equalsIgnoreCase("update")) {
               jsonObject = update(agenda, date);
            }
         }

          */
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            LOG.error(e);
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = ERROR(500, "Internal Server Error");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   @SuppressWarnings("deprecation")
   private void setData(TrainingAgenda agenda) throws SystemException, PortalException {
      String judul = ParamUtil.getString(uploadPortletRequest, "judulAgenda");
      int jenisAgendaId = ParamUtil.getInteger(uploadPortletRequest, "jenisAgendaId");
      String lokasi = ParamUtil.getString(uploadPortletRequest, "lokasiAgenda");
      String linkMeeting = ParamUtil.getString(uploadPortletRequest, "linkAgenda");

      if (!Validator.isNull(judul) && !InputValidationUtils.isBasicCharacter(judul)) {
         throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_- pada judul");
      }

      if (!Validator.isNull(lokasi) && !InputValidationUtils.isBasicCharacter(lokasi)) {
         throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_- pada lokasi");
      }

      // startDate
      String tglAwal = ParamUtil.getString(uploadPortletRequest, "startDate");
      LocalDateTime tglAwalDate = DateUtil.stringToLocalDateTime(tglAwal);
      Date startDate = DateUtil.convertToDateViaInstant(tglAwalDate.toLocalDate());
      startDate.setHours(tglAwalDate.getHour());
      startDate.setMinutes(tglAwalDate.getMinute());
      String startDateHours = DateUtil.localDateTimeToStringHours(tglAwalDate);

      // endDate
      String tglAkhir = ParamUtil.getString(uploadPortletRequest, "endDate");
      LocalDateTime tglAkhirDate = DateUtil.stringToLocalDateTime(tglAkhir);
      Date endDate = DateUtil.convertToDateViaInstant(tglAkhirDate.toLocalDate());
      endDate.setHours(tglAkhirDate.getHour());
      endDate.setMinutes(tglAkhirDate.getMinute());
      String endDateHours = DateUtil.localDateTimeToStringHours(tglAkhirDate);

      // registrationDate
      String registrationDate = ParamUtil.getString(uploadPortletRequest, "registrationDate");
      LocalDateTime tglRegistration = DateUtil.stringToLocalDateTime(registrationDate);
      Date registrationLimitDate = DateUtil.convertToDateViaInstant(tglRegistration.toLocalDate());
      registrationLimitDate.setHours(tglRegistration.getHour());
      registrationLimitDate.setMinutes(tglRegistration.getMinute());
      String registerEndDateHours = DateUtil.localDateTimeToStringHours(tglRegistration);

      // Validasi Tanggal Registrasi
      if (tglAkhirDate.isBefore(tglAwalDate)) {
         throw new SystemException("Tanggal agenda berakhir tidak bisa diisi sebelum agenda dimulai");
      }
      if (tglRegistration.isAfter(tglAwalDate)) {
         throw new SystemException("Tanggal registrasi tidak bisa diisi setelah agenda dimulai");
      }

      String deskripsi = ParamUtil.getString(uploadPortletRequest, "deskripsi");
      if (deskripsi.length() > 500) {
         throw new SystemException("Batas maksimal deskripsi adalah 500 karakter");
      }

      // Image
      long imageId = ParamUtil.getLong(uploadPortletRequest, "trainingAgendaImageFileId");
      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(imageId);
      String imageName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
      String imagePath = themeDisplay.getPortalURL() +
            themeDisplay.getPathContext() +
            "/documents/" +
            themeDisplay.getScopeGroupId() +
            "/" + fileEntry.getFolderId() +
            "/" + fileEntry.getFileName();

//      String imageName = ParamUtil.getString(uploadPortletRequest, "trainingAgendaImageFileName");
//      String imagePath = ParamUtil.getString(uploadPortletRequest, "trainingAgendaImageFilePath");

      List<String> allowedExtensions = Arrays.asList("gif", "jpg", "jpeg", "png");

//      if (imagePath != null) {
      String lowerCaseImageName = imagePath.toLowerCase();
      String fileExtension = FileUtil.getExtensionByStringHandling(lowerCaseImageName).get();

      if (!allowedExtensions.contains(fileExtension)) {
         throw new SystemException("Hanya file dengan format jpg, jpeg, png, dan gif yang diperbolehkan.");
      }
//      }

      agenda.setJudul(judul);
      agenda.setJenisAgenda(jenisAgendaId);
      agenda.setLocation(lokasi);
      agenda.setLinkMeeting(linkMeeting);
      agenda.setStartDate(startDate);
      agenda.setStartDateHours(startDateHours);
      agenda.setEndDate(endDate);
      agenda.setEndDateHours(endDateHours);
      agenda.setRegistrationLimitDate(registrationLimitDate);
      agenda.setRegistrationLimitDateHours(registerEndDateHours);
      agenda.setDeskripsi(deskripsi);
      agenda.setImageId(imageId);
      agenda.setImageName(imageName);
      agenda.setImagePath(imagePath);
   }

   private JSONObject create(TrainingAgenda agenda, Date date) {
      try {
         agenda.setRowStatus(true);
         agenda.setCreatedDate(date);
         agenda.setCreatedBy(user.getScreenName());
         agenda.setModifiedDate(date);
         agenda.setModifiedBy(user.getScreenName());
         agenda.setStatusAgenda(StatusAgendaEnum.BELUM_TERLAKSANA.ordinal());
         TrainingAgendaLocalServiceUtil.addTrainingAgenda(agenda);
         return SUCCESS("Data tersimpan", String.valueOf(agenda.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(TrainingAgenda agenda, Date date) {
      try {
         agenda.setModifiedDate(date);
         agenda.setModifiedBy(user.getScreenName());

         TrainingAgendaLocalServiceUtil.updateTrainingAgenda(agenda);
         return SUCCESS("Data terupdate.", String.valueOf(agenda.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(TrainingAgenda agenda, Date date) {
      try {
//         TrainingAgenda agenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(entryId);
         agenda.setRowStatus(false);
         agenda.setModifiedBy(user.getScreenName());
         agenda.setModifiedDate(date);
         TrainingAgendaLocalServiceUtil.updateTrainingAgenda(agenda);

         // Delete detail
         DynamicQuery query = TrainingAgendaParticipantLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("TrainingAgendaId", agenda.getId()));
         List<TrainingAgendaParticipant> participants = TrainingAgendaParticipantLocalServiceUtil.dynamicQuery(query);
         for (TrainingAgendaParticipant participant : participants) {
            TrainingAgendaParticipant tap = TrainingAgendaParticipantLocalServiceUtil.getTrainingAgendaParticipant(participant.getId());
            tap.setRowStatus(false);
            tap.setModifiedBy(user.getScreenName());
            tap.setModifiedDate(date);
            TrainingAgendaParticipantLocalServiceUtil.updateTrainingAgendaParticipant(tap);
         }
         return SUCCESS("Data terhapus.", String.valueOf(agenda.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   /*
   private boolean isExist(String name) {
      DynamicQuery query = TrainingAgendaLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      List<TrainingAgenda> trainingAgendas = TrainingAgendaLocalServiceUtil.dynamicQuery(query);
      return trainingAgendas.size() > 0;
   }
   */
}