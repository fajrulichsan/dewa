package com.astra.dewa.web.command.render.training_noncms.pendaftaran_materi.file;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Setting;
import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.model.TrainingAgendaParticipantUf;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaParticipantUfLocalServiceUtil;
import com.astra.dewa.utils.*;
import com.astra.dewa.utils.dto.TrainingAgendaParticipantDto;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
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
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.PENDAFTARAN_PELATIHAN_NONCMS,
            "mvc.command.name=/submit-upload-pendaftaran-materi-noncms"
      },
      service = MVCResourceCommand.class
)

public class SubmitUploadPendaftaranMateriNonCMSCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(SubmitUploadPendaftaranMateriNonCMSCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

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

//      String fileName = ParamUtil.getString(uploadPortletRequest, "trainingAgendaFileName");
//      String filePath = ParamUtil.getString(uploadPortletRequest, "trainingAgendaFilePath");

      int dealerId = ParamUtil.getInteger(uploadPortletRequest, "company");
      int agendaId = ParamUtil.getInteger(uploadPortletRequest, "acaraId");

      if (isRequestContainsXSS) {
         jsonObject = ERROR("Your request contains XSS payload.");
      } else {
         try {
            // CSRF Authentication
            AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

            User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

            long fileId = ParamUtil.getLong(uploadPortletRequest, "trainingAgendaFileId");

             if (!checkIsXLSXFile(fileId)) {
               throw new SystemException("Mohon perisa kembali jenis file yang diinput");
            }

            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
            String fileName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
            String filePath = themeDisplay.getPortalURL() +
                  themeDisplay.getPathContext() +
                  "/documents/" +
                  themeDisplay.getScopeGroupId() +
                  "/" + fileEntry.getFolderId() +
                  "/" + fileEntry.getFileName();

            // Convert FileEntry to File for Excel validation
            File file = File.createTempFile("excel_", ".xlsx");
            try (java.io.InputStream is = fileEntry.getContentStream();
                 java.io.FileOutputStream fos = new java.io.FileOutputStream(file)) {
               byte[] buffer = new byte[1024];
               int bytesRead;
               while ((bytesRead = is.read(buffer)) != -1) {
                  fos.write(buffer, 0, bytesRead);
               }
            }
            
            List<String> resultValidation = ExcelUtil.checkValidationPendaftaranPesertaPelatihan(file);
            file.delete(); // Clean up temp file
            LOG.info(resultValidation);

            if (resultValidation.isEmpty()) {
               TrainingAgendaParticipantUf tapuf = TrainingAgendaParticipantUfLocalServiceUtil.createTrainingAgendaParticipantUf(0);
               tapuf.setTrainingAgendaId(agendaId);
               tapuf.setDealerId(dealerId);
               tapuf.setFileId(fileId);
               tapuf.setFileName(fileName);
               tapuf.setFilePath(filePath);
               tapuf.setCreatedDate(new Date());
               tapuf.setCreatedBy(user.getScreenName());
               tapuf.setModifiedDate(new Date());
               tapuf.setModifiedBy(user.getScreenName());
               tapuf.setRowStatus(true);
               tapuf = TrainingAgendaParticipantUfLocalServiceUtil.addTrainingAgendaParticipantUf(tapuf);
               LOG.info("tapuf ===>> " + tapuf.toString());

               List<TrainingAgendaParticipantDto> tapDTO = ExcelUtil.convertPendaftaranPesertaPelatihan(file, agendaId, dealerId, themeDisplay.getUser().getFullName());

               LOG.info(tapDTO.toString());

               TrainingAgenda trainingAgenda = TrainingAgendaLocalServiceUtil.getTrainingAgenda(agendaId);
               String dateStart = DateUtil.dateToString(trainingAgenda.getStartDate(), "dd MMMM yyyy") + " " + trainingAgenda.getStartDateHours();
               String dateEnd = DateUtil.dateToString(trainingAgenda.getEndDate(), "dd MMMM yyyy") + " " + trainingAgenda.getEndDateHours();
               String lokasiOrLink = "";
               if (trainingAgenda.getJenisAgenda() == 0) {
                  lokasiOrLink = trainingAgenda.getLocation();
               } else {
                  lokasiOrLink = trainingAgenda.getLinkMeeting();
               }

               for (TrainingAgendaParticipantDto trainingParticipantDTO : tapDTO) {
                  System.out.println(trainingParticipantDTO.getEmail());

                  Dealer dealer = DealerLocalServiceUtil.getDealer(dealerId);
                  String subject = dealer.getName() + " - " + trainingAgenda.getJudul();
                  Setting bodyMail = SettingLocalServiceUtil.findCredential("mail", "body_calendar_event");
                  String body = bodyMail.getValue();
                  body = MailUtil.replace(
                        body,
                        new String[]{"[nama peserta]", "[nama agenda]", "[tanggal mulai]", "[tanggal selesai]", "[Lokasi/Link Meeting]"},
                        new String[]{trainingParticipantDTO.getName(), trainingAgenda.getJudul(), dateStart, dateEnd, lokasiOrLink}
                  );
                  String startDateEmail = DateUtil.changeDateToSDF(trainingAgenda.getStartDate(), trainingAgenda.getStartDateHours());
                  String endDateEmail = DateUtil.changeDateToSDF(trainingAgenda.getEndDate(), trainingAgenda.getEndDateHours());

                  MailUtil.sendEmailAndCalendar(new String[]{trainingParticipantDTO.getEmail()}, null, subject, body, startDateEmail, endDateEmail);
               }

               jsonObject = SUCCESS("Data tersimpan", "");
            } else {
               jsonObject = WARNING("Mohon periksa kembali data yang diinput");
            }

         } catch (Exception e) {
            if (e instanceof PrincipalException) {
               LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
               LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
               jsonObject = WARNING("Unauthorized request!");
            } else if (e instanceof SystemException) {
               LOG.error(e.getMessage(), e);
               jsonObject = WARNING(e.getMessage());
            } else {
               LOG.error(e.getMessage(), e);
            }
//            throw new RuntimeException(e);
         }
      }

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private Boolean checkIsXLSXFile(Long fileId) {
      try {
         FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);

         if (!fileEntry.getExtension().equalsIgnoreCase("xlsx")) {
            return false;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return true;
   }
}