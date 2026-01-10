package com.astra.dewa.web.command.render.training_noncms.pendaftaran_materi;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Setting;
import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.model.TrainingAgendaParticipant;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaParticipantLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.MailUtil;
import com.astra.dewa.web.command.render.training_noncms.pendaftaran_materi.file.UploadFilePendaftaranMateriNonCMSCommand;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.PENDAFTARAN_PELATIHAN_NONCMS,
            "mvc.command.name=/pendaftaran-materi-noncms-post"
      },
      service = MVCResourceCommand.class
)
public class PendaftaranMateriNonCMSPostCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadFilePendaftaranMateriNonCMSCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         // CSRF Authentication
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

         String[] name = ParamUtil.getParameterValues(uploadPortletRequest, "name", null);
         String[] email = ParamUtil.getParameterValues(uploadPortletRequest, "email", null);
         String[] phone = ParamUtil.getParameterValues(uploadPortletRequest, "phone", null);
         String[] company = ParamUtil.getParameterValues(uploadPortletRequest, "company", null);
         int id = ParamUtil.getInteger(uploadPortletRequest, "id");

         if (FilterXSS.checkXSS(name) ||
               FilterXSS.checkXSS(email) ||
               FilterXSS.checkXSS(phone) ||
               FilterXSS.checkXSSEncoded(name) ||
               FilterXSS.checkXSSEncoded(email) ||
               FilterXSS.checkXSSEncoded(phone)) {
//            jsonObject = ERROR("Your request contains XSS payload.");
            throw new SystemException("Your request contains XSS payload.");
         }

         TrainingAgenda trainingAgenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(id);

//         TrainingAgenda trainingAgenda = null;
//         try {
//            trainingAgenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(id);
//         } catch (PortalException e) {
//            e.printStackTrace();
//         }

         Date registrationLimitDate = changeDateValidation(trainingAgenda.getRegistrationLimitDate(), trainingAgenda.getRegistrationLimitDateHours());
         int compareInt = (new Date()).compareTo(registrationLimitDate);

         if (compareInt > 0) {
            jsonObject = WARNING("Request timeout!");
         } else {
//            UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
            // File[] files = uploadPortletRequest.getFiles("file");
//            try {

            // Validate Input
            for (int i = 0; i < name.length; i++) {
               if (!InputValidationUtils.isBasicCharacter(name[i])) {
                  throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_-");
               }
               if (name[i].length() < 5 || name[i].length() > 100) {
                  throw new SystemException("Mohon input nama antara 5 sampai 100 karakter");
               }
               if (!InputValidationUtils.isNumberOnly(phone[i])) {
                  throw new SystemException("Mohon input nomor handphone yang sesuai");
               }
               if (phone[i].length() < 9 || phone[i].length() > 14) {
                  throw new SystemException("Mohon input nomor handphone yang sesuai");
               }
               if (!InputValidationUtils.isEmailValid(email[i])) {
                  throw new SystemException("Mohon input email yang sesuai");
               }
               if (email[i].length() < 5 || email[i].length() > 255) {
                  throw new SystemException("Mohon input email maksimal 255 karakter");
               }
            }

            for (int i = 0; i < name.length; i++) {
               TrainingAgendaParticipant tap = TrainingAgendaParticipantLocalServiceUtil.createTrainingAgendaParticipant(0);
               tap.setTrainingAgendaId(id);
               tap.setFullName(name[i]);
               tap.setEmail(email[i]);
               tap.setPhone(phone[i]);
               tap.setDealerId(Integer.parseInt(company[i]));
               tap.setCreatedBy(user.getScreenName());
               tap.setCreatedDate(new Date());
               tap.setModifiedBy(user.getScreenName());
               tap.setModifiedDate(new Date());
               tap.setRowStatus(Boolean.valueOf(true));
               tap = TrainingAgendaParticipantLocalServiceUtil.addTrainingAgendaParticipant(tap);

               TrainingAgenda trainingAgenda1 = TrainingAgendaLocalServiceUtil.getTrainingAgenda(id);
               String dateStart = DateUtil.dateToString(trainingAgenda1.getStartDate(), "dd MMMM yyyy") + " " + trainingAgenda1.getStartDateHours();
               String dateEnd = DateUtil.dateToString(trainingAgenda1.getEndDate(), "dd MMMM yyyy") + " " + trainingAgenda1.getEndDateHours();
               String lokasiOrLink = "";
               if (trainingAgenda1.getJenisAgenda() == 0) {
                  lokasiOrLink = trainingAgenda1.getLocation();
               } else {
                  lokasiOrLink = trainingAgenda1.getLinkMeeting();
               }

               Dealer dealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(company[i]));
               String subject = dealer.getName() + " - " + trainingAgenda1.getJudul();
               Setting bodyMail = SettingLocalServiceUtil.findCredential("mail", "body_calendar_event");
               String body = bodyMail.getValue();
               body = MailUtil.replace(body, new String[]{"[nama peserta]", "[nama agenda]", "[tanggal mulai]", "[tanggal selesai]", "[Lokasi/Link Meeting]"}, new String[]{name[i], trainingAgenda1.getJudul(), dateStart, dateEnd, lokasiOrLink});
               String startDateEmail = DateUtil.changeDateToSDF(trainingAgenda1.getStartDate(), trainingAgenda1.getStartDateHours());
               String endDateEmail = DateUtil.changeDateToSDF(trainingAgenda1.getEndDate(), trainingAgenda1.getEndDateHours());

               MailUtil.sendEmailAndCalendar(new String[]{email[i]}, null, subject, body, startDateEmail, endDateEmail);
            }

            jsonObject = SUCCESS("success", "");

//            } catch (Exception e) {
//               e.printStackTrace();
//               jsonObject = ERROR("error");
//            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = WARNING("Bad request!");
         }
         LOG.error(e.getLocalizedMessage());
         LOG.error(e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
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
