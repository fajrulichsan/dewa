package com.astra.dewa.web.command.render.diskon.testcar;

import com.astra.dewa.model.DiskonTestCar;
import com.astra.dewa.service.DiskonTestCarLocalServiceUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR,
      "mvc.command.name=/diskon-test-car-edit"
   },
   service = MVCResourceCommand.class
)
public class DiskonTestCarEditRenderCommand extends BaseMVCResourceCommand {
   private final Log _log = LogFactoryUtil.getLog(DiskonTestCarEditRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();

      if (id >= 0){
         try {
            DiskonTestCar diskonTestCar = DiskonTestCarLocalServiceUtil.getDiskonTestCar(id);
            dto.put("id", diskonTestCar.getId());
            dto.put("dealerId", diskonTestCar.getDealerId());
            dto.put("fileId", diskonTestCar.getFileId());
            dto.put("fileName", diskonTestCar.getFileName());
            dto.put("filePath", diskonTestCar.getFilePath());
            dto.put("tahun", diskonTestCar.getTahun());
            dto.put("kuartalId", diskonTestCar.getKuartalId());
            dto.put("tipeKendaraanId", diskonTestCar.getTipeKendaraanId());
            dto.put("keterangan", diskonTestCar.getKeterangan());
         } catch (PortalException e) {
            _log.error("Error fetching data: " + e.getMessage());
         }
         ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
      }
   }
}