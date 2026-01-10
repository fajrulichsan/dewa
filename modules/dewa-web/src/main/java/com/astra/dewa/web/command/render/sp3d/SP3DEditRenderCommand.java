package com.astra.dewa.web.command.render.sp3d;

import com.astra.dewa.model.SP3D;
import com.astra.dewa.service.SP3DLocalServiceUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.SP3D_PORTLET,
      "mvc.command.name=/sp3d-edit"
   },
   service = MVCResourceCommand.class
)
public class SP3DEditRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(SP3DEditRenderCommand.class);


   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

      int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");

      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id >= 0){
         try {
            SP3D sp3d = SP3DLocalServiceUtil.getSP3D(id);
            dto.put("id", sp3d.getId());
            dto.put("tahun", sp3d.getTahun());
            dto.put("periode", sp3d.getBulan());
            dto.put("dealerId", sp3d.getDealerId());
            dto.put("fileId", sp3d.getFileId());
            dto.put("fileName", sp3d.getFileName());
            dto.put("filePath", sp3d.getFilePath());
         } catch (PortalException e) {
            log.debug(e.getMessage());
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
   }
}