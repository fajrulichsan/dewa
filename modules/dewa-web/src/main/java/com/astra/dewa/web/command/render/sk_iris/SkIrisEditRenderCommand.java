package com.astra.dewa.web.command.render.sk_iris;

import com.astra.dewa.model.SkIris;
import com.astra.dewa.service.SkIrisLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS,
      "mvc.command.name=/sk-iris-edit"
   },
   service = MVCResourceCommand.class
)
public class SkIrisEditRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(SkIrisEditRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

      int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");

      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id >= 0){
         try {
            SkIris skIris = SkIrisLocalServiceUtil.getSkIris(id);
            dto.put("kategori",skIris.getKategori());
            dto.put("id", skIris.getId());
            dto.put("tahun",skIris.getTahun());
            dto.put("periode", skIris.getPeriode());
            dto.put("wilayahId", skIris.getWilayahId());
            dto.put("dealerId",skIris.getDealerId());
            dto.put("fileId", skIris.getFileId());
            dto.put("fileName", skIris.getFileName());
            dto.put("filePath", skIris.getFilePath());
         } catch (PortalException e) {
            log.debug(e.getMessage());
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
   }
}