package com.astra.dewa.web.command.render.training_noncms.pendaftaran_materi;

import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.service.TrainingAgendaLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.PENDAFTARAN_PELATIHAN_NONCMS,
            "mvc.command.name=/pendaftaran-materi-noncms-list"
      },
      service = MVCResourceCommand.class
)

public class PendaftaranMateriNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(PendaftaranMateriNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 0;
      int count = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         DynamicQuery query = TrainingAgendaLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("StartDate"));

         List<TrainingAgenda> trainingAgendaList = TrainingAgendaLocalServiceUtil.dynamicQuery(query);

         for (TrainingAgenda trainingAgenda : trainingAgendaList) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();

            String day = DateUtil.getDays(trainingAgenda.getStartDate());
            String date = DateUtil.dateToString(trainingAgenda.getStartDate(), "dd MMMM yyyy");
            String startDate = day + ", " + date;
            String urlTemplate = getURLTemplateExcel(themeDisplay);

            String linkLocation = "";
            if (trainingAgenda.getJenisAgenda() == 0) {
               linkLocation = trainingAgenda.getLocation();
            } else {
               linkLocation = trainingAgenda.getLinkMeeting();
            }

            String ketAcara = availableAgenda(trainingAgenda.getStartDate(), trainingAgenda.getStatusAgenda());
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(trainingAgenda.getImageId());
            String urlImage = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                  themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();

            dto.put("no", count);
            dto.put("id", trainingAgenda.getId());
            dto.put("judul", trainingAgenda.getJudul());
            dto.put("jenisAgenda", trainingAgenda.getJenisAgenda());
            dto.put("statusAgenda", trainingAgenda.getStatusAgenda());
            dto.put("linkLocation", linkLocation);
            dto.put("startDate", startDate);
            dto.put("deskripsi", trainingAgenda.getDeskripsi());
            dto.put("image", urlImage);
            dto.put("urlTemplate", urlTemplate);
            dto.put("ketAcara", ketAcara);
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = trainingAgendaList.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
            jsonMessage = ERROR(401, e.getMessage());
         } else {
            LOG.error(e);
            jsonMessage = ERROR(500, e.getMessage());
         }
//            LOG.error(e);
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   private String getURLTemplateExcel(ThemeDisplay themeDisplay) {
      String url = "";

      try {
         DynamicQuery dynamicQuery = DLFileEntryLocalServiceUtil.dynamicQuery();
         dynamicQuery.add(RestrictionsFactoryUtil.eq("fileName", DewaWebPortletKeys.TEMPLATE_PENDAFTARAN_PESERTA_PELATIHAN));
         List<DLFileEntry> fileEntries = DLFileEntryLocalServiceUtil.dynamicQuery(dynamicQuery);

         if (!fileEntries.isEmpty()) {
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntries.get(0).getFileEntryId());
            url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                  themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();
         }

      } catch (Exception e) {
         LOG.error(e);
      }

      return url;
   }

   private String availableAgenda(Date date, Integer statusAgenda) {
      String result = "";

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      Calendar today = Calendar.getInstance();
      today.add(Calendar.DAY_OF_YEAR, 7);

      if (statusAgenda.equals(0)) {

         if (calendar.after(today)) {
            result = "Akan datang";
         } else {
            result = "Tersedia";
         }

      } else {
         result = "Tidak tersedia";
      }

      return result;
   }
}