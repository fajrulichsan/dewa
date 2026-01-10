package com.astra.dewa.web.command.render.training.participant_uf;

import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.TrainingAgendaParticipantUf;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.TrainingAgendaParticipantUfLocalServiceUtil;
import com.astra.dewa.utils.ExcelUtil;
import com.astra.dewa.utils.dto.TrainingParticipantDto;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=training-agenda-participant-uf-list-download"
   },
   service = MVCResourceCommand.class
)
public class TrainingAgendaParticipantUfListDownloadCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(TrainingAgendaParticipantUfListDownloadCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
      // HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request))
      // String trainingAgendaId = httpReq.getParameter("trainingAgendaId")
      String[] fileIds = ParamUtil.getStringValues(uploadPortletRequest, "participants");

      List<String> fileDatas = new ArrayList<>();
      for (String fileId : fileIds) {
         if (!fileId.isEmpty()) {
            fileDatas.add(fileId);
         }
      }

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         for (String id : fileDatas) {
            TrainingAgendaParticipantUf participantFile = TrainingAgendaParticipantUfLocalServiceUtil.getTrainingAgendaParticipantUf(Integer.parseInt(id));
            FileEntry fileEntry = DLAppServiceUtil.getFileEntry(participantFile.getFileId());
            
            // Convert FileEntry to File for Excel processing
            File file = File.createTempFile("excel_", ".xlsx");
            try (java.io.InputStream is = fileEntry.getContentStream();
                 java.io.FileOutputStream fos = new java.io.FileOutputStream(file)) {
               byte[] buffer = new byte[1024];
               int bytesRead;
               while ((bytesRead = is.read(buffer)) != -1) {
                  fos.write(buffer, 0, bytesRead);
               }
            }
            
            List<TrainingParticipantDto> participantsList = ExcelUtil.getTrainingParticipants(file);
            file.delete(); // Clean up temp file

            Dealer dealer = DealerLocalServiceUtil.getDealer(participantFile.getDealerId());
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("dealerId", dealer.getId());
            dto.put("dealerCode", dealer.getCode());
            if(dealer.getCabangId() > 0) {
               Cabang cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());
               dto.put("dealerName", dealer.getName() + " - " + cabang.getName());
            } else {
               dto.put("dealerName", dealer.getName());
            }
            dto.put("participants", participantsList);
            jsonData.put(dto);
         }
         acknowledge = 1;
         count = fileDatas.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e.getMessage(), e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
