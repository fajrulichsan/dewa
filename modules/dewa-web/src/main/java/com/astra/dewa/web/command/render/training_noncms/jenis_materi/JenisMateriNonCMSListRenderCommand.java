package com.astra.dewa.web.command.render.training_noncms.jenis_materi;

import com.astra.dewa.model.JenisMateri;
import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.model.TrainingMateri;
import com.astra.dewa.service.JenisMateriLocalServiceUtil;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
import com.astra.dewa.service.TrainingMateriLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.MATERI_PELATIHAN_NONCMS,
            "mvc.command.name=/jenis-materi-noncms-list"
      },
      service = MVCResourceCommand.class
)

public class JenisMateriNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(JenisMateriNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      String date = ParamUtil.getString(request, "date");
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         DynamicQuery query = JenisMateriLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         if (!date.isEmpty()) {
            Date dateFilter = DateUtil.convertDate(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFilter);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date startOfTheDay = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date endOfTheDay = calendar.getTime();
            query.add(RestrictionsFactoryUtil.ge("CreatedDate", startOfTheDay));
            query.add(RestrictionsFactoryUtil.lt("CreatedDate", endOfTheDay));
         }
         List<JenisMateri> jenisMateriList = JenisMateriLocalServiceUtil.dynamicQuery(query);


         for (JenisMateri jenisMateri : jenisMateriList) {
            LOG.debug(jenisMateri);
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(jenisMateri.getImageId());
            String urlImage = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                  themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();

            dto.put("no", count);
            dto.put("id", jenisMateri.getId());
            dto.put("name", jenisMateri.getName());
            dto.put("imageUrl", urlImage);
            dto.put("date", DateUtil.dateToMonthName(jenisMateri.getCreatedDate()));
            dto.put("topikList", topikList(jenisMateri.getId()));
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = jenisMateriList.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   private JSONArray topikList(Integer idJenisMateri) {
      JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

      DynamicQuery dynamicQuery = TrainingMateriLocalServiceUtil.dynamicQuery();
      dynamicQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      dynamicQuery.add(RestrictionsFactoryUtil.eq("JenisMateriId", idJenisMateri));
      List<TrainingMateri> trainingMateriList = TrainingMateriLocalServiceUtil.dynamicQuery(dynamicQuery);

      List<TopikMateri> topikMateriList = listTopikMateri(trainingMateriList);

      for (TopikMateri topikMateri : topikMateriList) {
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
         jsonObject.put("topikId", topikMateri.getId());
         jsonObject.put("topikName", topikMateri.getName());
         jsonObject.put("topikDate", DateUtil.dateToMonthName(topikMateri.getCreatedDate()));
         jsonArray.put(jsonObject);
      }
      return jsonArray;
   }

   private List<TopikMateri> listTopikMateri(List<TrainingMateri> trainingMateriList) {
      List<TopikMateri> result = new ArrayList<>();
      List<Integer> topikMateriList = new ArrayList<>();

      try {
         topikMateriList = getTopikMateri(trainingMateriList);

         if (topikMateriList.size() != 0) {
            DynamicQuery query = TopikMateriLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            query.add(RestrictionsFactoryUtil.in("Id", topikMateriList));
            result = TopikMateriLocalServiceUtil.dynamicQuery(query);
         }

      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }

      return result;
   }

   private List<Integer> getTopikMateri(List<TrainingMateri> trainingMateriList) {
      List<Integer> topikMateriList = new ArrayList<>();

      try {
         for (TrainingMateri trainingMateri : trainingMateriList) {
            if (!topikMateriList.contains(trainingMateri.getTopikMateriId())) {
               topikMateriList.add(trainingMateri.getTopikMateriId());
            }
         }
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }
      return topikMateriList;
   }
}
