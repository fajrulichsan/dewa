package com.astra.dewa.web.command.render.report_plafond.cms;

import com.astra.dewa.model.ReportPlafond;
import com.astra.dewa.service.ReportPlafondLocalServiceUtil;
import com.astra.dewa.service.SalesReportLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

/**
 * @author psmafifd1401
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND,
            "mvc.command.name=/report-plafond/list"
      },
      service = MVCResourceCommand.class
)
public class ReportPlafondListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(ReportPlafondListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());
      } catch (PrincipalException pe) {
         LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
         LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), pe);
      }

      String dealerId = httpReq.getParameter("dealerId");
      String periode = httpReq.getParameter("periode");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      try {
         DynamicQuery query = ReportPlafondLocalServiceUtil.dynamicQuery();
         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }
         if (!periode.equalsIgnoreCase("ALL") && !periode.isEmpty()) {
            Date periodDate = DateUtil.stringToDate(periode);
            query.add(RestrictionsFactoryUtil.eq("Periode", periodDate));
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         List<ReportPlafond> reportPlafonds = SalesReportLocalServiceUtil.dynamicQuery(query);
         for (ReportPlafond reportPlafond : reportPlafonds) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", reportPlafond.getId());
            dto.put("periode", DateUtil.dateToString(reportPlafond.getPeriode()));
            dto.put("periodeSort", reportPlafond.getModifiedDate().getTime());
            dto.put("fileName", reportPlafond.getFileName());
            dto.put("uploadDate", DateUtil.dateToString(reportPlafond.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H));
            dto.put("uploadDateSort", reportPlafond.getModifiedDate().getTime());
            jsonData.put(dto);
         }
         acknowledge = 1;
         count = reportPlafonds.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
