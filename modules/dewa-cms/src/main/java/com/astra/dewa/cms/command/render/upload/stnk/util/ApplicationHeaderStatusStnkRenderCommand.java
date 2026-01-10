package com.astra.dewa.cms.command.render.upload.stnk.util;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.utils.revisi.ApplicationHeaderStatusUtils;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + CmsPortletKeys.UPLOAD_STNK,
      "mvc.command.name=upload-stnk-application-header-status"
   },
   service = MVCResourceCommand.class
)
public class ApplicationHeaderStatusStnkRenderCommand extends BaseMVCResourceCommand {

   private final Log LOG = LogFactoryUtil.getLog(ApplicationHeaderStatusStnkRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      boolean isDisplay = true;
      if (null != httpReq.getParameter("isDisplay")) {
         isDisplay = Boolean.parseBoolean(httpReq.getParameter("isDisplay"));
      }
      try {
         if (isDisplay) jsonData = ApplicationHeaderStatusUtils.selectNoDraft();
         else jsonData = ApplicationHeaderStatusUtils.selectWaitingStatuses();
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
