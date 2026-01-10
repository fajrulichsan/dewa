package com.astra.dewa.web.command.action;

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealinkCustomValidationException;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.JenisAcaraEnum;
import com.astra.dewa.utils.StatusAcaraEnum;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
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
import java.util.Date;
import java.util.Enumeration;

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
      "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
      "mvc.command.name=calender-event-action"
   },
   service = MVCResourceCommand.class
)
public class CalenderEventActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(CalenderEventActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private ThemeDisplay themeDisplay;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

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

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Oops! Something went wrong.");
         } else {
            CalenderEvent calenderEvent = null;
            Date date = new Date();

            switch (crudType) {
               case CREATE:
                  calenderEvent = CalenderEventLocalServiceUtil.createCalenderEvent(0);
                  setData(calenderEvent);
                  jsonObject = create(calenderEvent, date);
                  break;
               case UPDATE:
                  calenderEvent = CalenderEventLocalServiceUtil.getCalenderEvent(entryId);
                  setData(calenderEvent);
                  jsonObject = update(calenderEvent, date);
                  break;
               case DELETE:
                  jsonObject = CalenderEventLocalServiceUtil.deleteCalenderEventWithDetails(entryId, this.user.getScreenName());
                  break;
               default:
                  jsonObject = WARNING("Bad request!");
                  break;
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.info("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.info("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else if (e instanceof DealinkCustomValidationException) {
            jsonObject = ERROR(400, e.getMessage());
         } else {
            jsonObject = ERROR(500, "Internal server error");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   @SuppressWarnings("deprecation")
   private void setData(CalenderEvent calenderEvent) throws DealinkCustomValidationException, PortalException {
      String judul = ParamUtil.getString(uploadPortletRequest, "judul", "");
      
      if (!InputValidationUtils.isBasicCharacter(judul)) {
         throw new DealinkCustomValidationException("Mohon tidak menggunakan karakter khusus pada judul");
      }
      
      int jenisAcaraId = ParamUtil.getInteger(uploadPortletRequest, "jenisAcaraId", -1);
      LOG.debug("jenis acara: " + jenisAcaraId);
      
      String lokasi = ParamUtil.getString(uploadPortletRequest, "lokasi", "");
      String linkMeeting = ParamUtil.getString(uploadPortletRequest, "linkMeeting", "");
      
      if (jenisAcaraId == JenisAcaraEnum.OFFLINE.ordinal()) {
         if (!InputValidationUtils.isBasicCharacter(lokasi)) {
            throw new DealinkCustomValidationException("Mohon tidak menggunakan karakter khusus pada lokasi");
         }
      } else if (jenisAcaraId == JenisAcaraEnum.ONLINE.ordinal()) {
         if (!InputValidationUtils.isLink(linkMeeting)) {
            throw new DealinkCustomValidationException("Format link belum sesuai");
         }
      } else {
         throw new DealinkCustomValidationException("Jenis acara belum sesuai");
      }

      // Start Date
      String tglAwal = ParamUtil.getString(uploadPortletRequest, "startDate");
      LocalDateTime tglAwalDate = DateUtil.stringToLocalDateTime(tglAwal);
      Date startDate = DateUtil.convertToDateViaInstant(tglAwalDate.toLocalDate());
      startDate.setHours(tglAwalDate.getHour());
      startDate.setMinutes(tglAwalDate.getMinute());
      String startDateHours = DateUtil.localDateTimeToStringHours(tglAwalDate);

      // End Date
      String tglAkhir = ParamUtil.getString(uploadPortletRequest, "endDate");
      LocalDateTime tglAkhirDate = DateUtil.stringToLocalDateTime(tglAkhir);
      Date endDate = DateUtil.convertToDateViaInstant(tglAkhirDate.toLocalDate());
      endDate.setHours(tglAkhirDate.getHour());
      endDate.setMinutes(tglAkhirDate.getMinute());
      String endDateHours = DateUtil.localDateTimeToStringHours(tglAkhirDate);

      // Registration Date
      String registrationDate = ParamUtil.getString(uploadPortletRequest, "registrationDate");
      Date registrationLimitDate = null;
      String registerEndDateHours = null;

      if (Validator.isNotNull(registrationDate)) {
         LocalDateTime tglRegistration = DateUtil.stringToLocalDateTime(registrationDate);
         registrationLimitDate = DateUtil.convertToDateViaInstant(tglRegistration.toLocalDate());
         registrationLimitDate.setHours(tglRegistration.getHour());
         registrationLimitDate.setMinutes(tglRegistration.getMinute());
         registerEndDateHours = DateUtil.localDateTimeToStringHours(tglRegistration);
      }

      // Validasi Tanggal Registrasi
      if (tglAkhirDate.isBefore(tglAwalDate)) {
         throw new DealinkCustomValidationException("Tanggal agenda berakhir tidak bisa diisi sebelum agenda dimulai");
      }

      /*
      if (tglRegistration.isAfter(tglAwalDate)) {
         throw new SystemException("Tanggal registrasi tidak bisa diisi setelah agenda dimulai");
      }
       */

      // Other
      String deskripsi = ParamUtil.getString(uploadPortletRequest, "deskripsi");
      if (deskripsi.length() > 500) {
         throw new DealinkCustomValidationException("Batas maksimal deskripsi adalah 500 karakter");
      }

      // Image
      long imageId = ParamUtil.getLong(uploadPortletRequest, "calenderEventImageFileId");
      FileEntry imageEntry = DLAppLocalServiceUtil.getFileEntry(imageId);
      String imageName = imageEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
      String imagePath = themeDisplay.getPortalURL() +
            themeDisplay.getPathContext() +
            "/documents/" +
            themeDisplay.getScopeGroupId() +
            "/" + imageEntry.getFolderId() +
            "/" + imageEntry.getFileName();

      calenderEvent.setJudul(judul);
      calenderEvent.setJenisAcara(jenisAcaraId);
      calenderEvent.setLocation(lokasi);
      calenderEvent.setLinkMeeting(linkMeeting);
      calenderEvent.setStartDate(startDate);
      calenderEvent.setStartDateHours(startDateHours);
      calenderEvent.setEndDate(endDate);
      calenderEvent.setEndDateHours(endDateHours);
      calenderEvent.setRegistrationLimitDate(registrationLimitDate);
      calenderEvent.setRegistrationLimitDateHours(registerEndDateHours);
      calenderEvent.setDeskripsi(deskripsi);
      calenderEvent.setImageId(imageId);
      calenderEvent.setImageName(imageName);
      calenderEvent.setImagePath(imagePath);
   }

   private JSONObject create(CalenderEvent calenderEvent, Date date) {
      try {
         calenderEvent.setCreatedDate(date);
         calenderEvent.setCreatedBy(user.getScreenName());
         calenderEvent.setModifiedDate(date);
         calenderEvent.setModifiedBy(user.getScreenName());
         calenderEvent.setRowStatus(true);
         calenderEvent.setStatusAcara(StatusAcaraEnum.BELUM_TERLAKSANA.ordinal());
         CalenderEventLocalServiceUtil.addCalenderEvent(calenderEvent);
         return SUCCESS("Data tersimpan", String.valueOf(calenderEvent.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(CalenderEvent calenderEvent, Date date) {
      try {
         calenderEvent.setModifiedDate(date);
         calenderEvent.setModifiedBy(user.getScreenName());

         CalenderEventLocalServiceUtil.updateCalenderEvent(calenderEvent);
         return SUCCESS("Data terupdate.", String.valueOf(calenderEvent.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }
}