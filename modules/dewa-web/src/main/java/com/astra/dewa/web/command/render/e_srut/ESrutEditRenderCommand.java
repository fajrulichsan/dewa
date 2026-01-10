package com.astra.dewa.web.command.render.e_srut;

import com.astra.dewa.model.ESrut;
import com.astra.dewa.service.ESrutLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
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
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.text.SimpleDateFormat;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.E_SRUT,
            "mvc.command.name=/e-srut-edit"
      },
      service = MVCResourceCommand.class
)
public class ESrutEditRenderCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(ESrutEditRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();

      if (id > 0) {
         try {
            ESrut eSrut = ESrutLocalServiceUtil.getESrut(id);

            SimpleDateFormat sdf = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_SLASH);
            String periodDate = sdf.format(eSrut.getPeriodDate());

            dto.put("id", eSrut.getId());
            dto.put("dealerId", eSrut.getDealerId());
            dto.put("periodDate", periodDate);
            dto.put("fileId", eSrut.getFileId());
            dto.put("fileName", eSrut.getFileName());
            dto.put("filePath", eSrut.getFilePath());
         } catch (PortalException e) {
            log.error("Error fetching data: " + e.getMessage());
         }
      }

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
   }
}
