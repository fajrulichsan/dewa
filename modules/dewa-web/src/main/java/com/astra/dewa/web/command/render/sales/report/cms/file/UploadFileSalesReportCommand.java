package com.astra.dewa.web.command.render.sales.report.cms.file;

import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
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
import java.util.HashMap;

@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT,
      "mvc.command.name=/sales-report/upload-file"
   },
   service = MVCResourceCommand.class
)

public class UploadFileSalesReportCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UploadFileSalesReportCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      String documentId = ParamUtil.getString(uploadPortletRequest, "document-id");
      if (documentId.equals("undefined")) {
         documentId = ParamUtil.getString(uploadPortletRequest, "document-id-new");
      }
      long fileId = ParamUtil.getLong(uploadPortletRequest, "file-id");
      String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
      String dealerId = ParamUtil.getString(uploadPortletRequest, "dealerId");
      String periode = ParamUtil.getString(uploadPortletRequest, "periode");

      File file = uploadPortletRequest.getFile("file-upload");
      HashMap<String, String> payload = new HashMap<>();
      Date date =  DateUtil.stringToDate(periode, DewaWebKeys.DATE_FORMAT_DOT);


      try {
         String dealerCode = DealerUtils.getDealerCode(Integer.parseInt(dealerId));
         fileName = "Sales Report-" + DateUtil.dateToString(date) + "-" + dealerCode + "." + FileUtil.getExtensionByStringHandling(fileName).get();
         if(fileId == 0) {
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.SALES_REPORT);
         } else {
            payload = FileUtil.updateFile(fileId, file, fileName, themeDisplay, resourceRequest);
         }
      } catch (Exception e) {
         acknowledge = 0;
         status = "error";
         message = e.getMessage();

         log.error(e.getMessage());
         log.error(e.getLocalizedMessage());
      }

      JSONObject response = JSONFactoryUtil.createJSONObject();
      response.put("acknowledge", acknowledge);
      response.put("status", status);
      response.put("message", message);
      response.put("fileURL", payload.get("URL"));
      response.put("fileID", payload.get("ID"));
      response.put("fileName", payload.get("fileName"));

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), response.toJSONString());
   }

}
