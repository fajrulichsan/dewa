package com.astra.dewa.web.command.render.training.materi;

import com.astra.dewa.model.JenisMateri;
import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.model.TrainingMateri;
import com.astra.dewa.model.TrainingMateriLampiran;
import com.astra.dewa.service.JenisMateriLocalServiceUtil;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
import com.astra.dewa.service.TrainingMateriLampiranLocalServiceUtil;
import com.astra.dewa.service.TrainingMateriLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.INVALID;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=/training-materi-edit"
      },
      service = {MVCRenderCommand.class}
)
public class TrainingMateriEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(TrainingMateriEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (Integer.parseInt(id) > 0) {
         action = UPDATE;
         try {
            DynamicQuery query = TrainingMateriLampiranLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("TrainingMateriId", Integer.parseInt(id)));
            List<TrainingMateriLampiran> trainingMateriLampirans = TrainingMateriLampiranLocalServiceUtil.dynamicQuery(query);

            TrainingMateri trainingMateri = TrainingMateriLocalServiceUtil.getTrainingMateri(Integer.parseInt(id));
            dto.put("id", trainingMateri.getId());
            if (trainingMateri.getJenisMateriId() > 0) {
               JenisMateri jenisMateri = JenisMateriLocalServiceUtil.getJenisMateri(trainingMateri.getJenisMateriId());
               dto.put("jenisMateriId", jenisMateri.getId());
               dto.put("jenisMateriName", jenisMateri.getName());
            } else {
               dto.put("jenisMateriId", 0);
               dto.put("jenisMateriName", "-");
            }
            if (trainingMateri.getTopikMateriId() > 0) {
               TopikMateri topikMateri = TopikMateriLocalServiceUtil.getTopikMateri(trainingMateri.getTopikMateriId());
               dto.put("topikMateriId", topikMateri.getId());
               dto.put("topikMateriName", topikMateri.getName());
            } else {
               dto.put("topikMateriId", 0);
               dto.put("topikMateriName", "-");
            }
            dto.put("judulMateri", trainingMateri.getJudulMateri());
            if (trainingMateriLampirans.size() > 0) {
               dto.put("fileId", trainingMateriLampirans.get(0).getFileId());
               dto.put("fileName", trainingMateriLampirans.get(0).getFileName());
               dto.put("filePath", trainingMateriLampirans.get(0).getFilePath());
               dto.put("deleted", false);
            } else {
               dto.put("fileId", 0);
               dto.put("fileName", "");
               dto.put("filePath", "");
            }
            dto.put("trainingMateriLampirans", trainingMateriLampirans);
            dto.put("imageId", trainingMateri.getImageId());
            dto.put("imageName", trainingMateri.getImageName());
            dto.put("imagePath", trainingMateri.getImagePath());
         } catch (PortalException e) {
            LOG.error(e);
            action = INVALID;
         }
      } else {
         action = CREATE;
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/training/materi/form.jsp";
   }

}