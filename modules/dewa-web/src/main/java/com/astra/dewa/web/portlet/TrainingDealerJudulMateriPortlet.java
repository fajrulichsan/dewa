package com.astra.dewa.web.portlet;

import com.astra.dewa.model.TrainingMateri;
import com.astra.dewa.model.TrainingMateriLampiran;
import com.astra.dewa.service.JenisMateriLocalServiceUtil;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
import com.astra.dewa.service.TrainingMateriLampiranLocalServiceUtil;
import com.astra.dewa.service.TrainingMateriLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 */
@Component(
      immediate = true,
      property = {
            "com.liferay.portlet.display-category=Dewa UI",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Materi Pelatihan Detail Judul NonCMS",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/training-noncms/materi/detail-judul-materi.jsp",
            "javax.portlet.name=" + DewaWebPortletKeys.MATERI_PELATIHAN_JUDUL_NONCMS,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)
public class TrainingDealerJudulMateriPortlet extends MVCPortlet {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      int topikMateriId = ParamUtil.getInteger(httpReq, "id", 0);
      int jenisMateriId = ParamUtil.getInteger(httpReq, "jenisMateriId", 0);

      String topikMateri = "";
      String jenisMateri = "";
      List<TrainingMateri> trainingMateriList = new ArrayList<>();
      JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

      try {
         DynamicQuery query = TrainingMateriLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.add(RestrictionsFactoryUtil.eq("TopikMateriId", topikMateriId));
         query.add(RestrictionsFactoryUtil.eq("JenisMateriId", jenisMateriId));
         trainingMateriList = TrainingMateriLocalServiceUtil.dynamicQuery(query);

         for (TrainingMateri trainingMateri : trainingMateriList) {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("id", trainingMateri.getId());
            jsonObject.put("name", trainingMateri.getJudulMateri());
            JSONArray fileLampiran = JSONFactoryUtil.createJSONArray();

            DynamicQuery dynamicQuery = TrainingMateriLampiranLocalServiceUtil.dynamicQuery();
            dynamicQuery.add(RestrictionsFactoryUtil.eq("TrainingMateriId", trainingMateri.getId()));
            List<TrainingMateriLampiran> trainingMateriLampiranList = TrainingMateriLampiranLocalServiceUtil.dynamicQuery(dynamicQuery);

            for (TrainingMateriLampiran trainingMateriLampiran : trainingMateriLampiranList) {
               JSONObject object = JSONFactoryUtil.createJSONObject();
               FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(trainingMateriLampiran.getFileId());
               String urlDownlaod = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                     themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();
               object.put("nama", trainingMateriLampiran.getFileName());
               object.put("url", urlDownlaod);
               fileLampiran.put(object);
            }
            jsonObject.put("lampiran", fileLampiran);
            jsonArray.put(jsonObject);
         }

         topikMateri = TopikMateriLocalServiceUtil.getTopikMateri(topikMateriId).getName();
         jenisMateri = JenisMateriLocalServiceUtil.getJenisMateri(jenisMateriId).getName();

      } catch (Exception e) {
         LOG.error(e);
         LOG.error(e.getLocalizedMessage());
      }

      request.setAttribute("topikMateri", topikMateri);
      request.setAttribute("jenisMateri", jenisMateri);
      request.setAttribute("trainingMateriList", trainingMateriList);
      request.setAttribute("hasil", jsonArray);

      super.render(request, response);
   }

   @Override
   public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
      super.serveResource(request, response);
   }
}