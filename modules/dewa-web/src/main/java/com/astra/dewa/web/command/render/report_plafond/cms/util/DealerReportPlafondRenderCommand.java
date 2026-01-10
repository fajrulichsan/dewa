//package com.astra.dewa.cms.command.render.report_plafond.util;
//
//import com.astra.dewa.model.UsersDealers;
//import com.astra.dewa.utils.DealerUtils;
//import com.astra.dewa.utils.RoleDealerUtils;
//import com.astra.dewa.web.constants.DewaWebPortletKeys;
//import com.liferay.portal.kernel.json.JSONArray;
//import com.liferay.portal.kernel.json.JSONFactoryUtil;
//import com.liferay.portal.kernel.json.JSONObject;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
//import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
//import com.liferay.portal.kernel.service.ServiceContext;
//import com.liferay.portal.kernel.service.ServiceContextFactory;
//import com.liferay.portal.kernel.servlet.ServletResponseUtil;
//import com.liferay.portal.kernel.util.PortalUtil;
//import org.osgi.service.component.annotations.Component;
//
//import javax.portlet.ResourceRequest;
//import javax.portlet.ResourceResponse;
//
//import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
//import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
//import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
//
//@Component(
//   immediate = true,
//   property = {
//      "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND,
//      "mvc.command.name=/dealer-report-plafond"
//   },
//   service = MVCResourceCommand.class
//)
//public class DealerReportPlafondRenderCommand extends BaseMVCResourceCommand {
//
//   private final Log log = LogFactoryUtil.getLog(DealerReportPlafondRenderCommand.class);
//
//   @Override
//   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
//      ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
//      UsersDealers roleDealer = RoleDealerUtils.userId(serviceContext.getUserId());
//
//      int acknowledge = 0;
//      int count = 0;
//      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
//      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
//      try {
//         jsonData = DealerUtils.selects();
//         acknowledge = 1;
//         count = jsonData.length();
//         jsonMessage = SUCCESS(200, "OK");
//      } catch (Exception e) {
//         log.error(e);
//         jsonMessage = ERROR(500, e.getMessage());
//      }
//      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
//      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
//   }
//
//}
