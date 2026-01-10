package com.astra.dewa.web.command.render.report_plafond.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.ReportPlafond;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.ReportPlafondLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmafifd1401
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND_NONCMS,
            "mvc.command.name=/non-cms/report-plafond/list"
      },
      service = MVCResourceCommand.class
)
public class ReportPlafondNonCMSListRendeCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(ReportPlafondNonCMSListRendeCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      String dealerId = httpReq.getParameter("dealerId");
      String periode = httpReq.getParameter("periode");
      String role = "";

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
         assert user != null;
         int roleGroupId = RoleDealerUtils.getUserRoleGroup(user.getUserId());
         role = RoleDealerUtils.getUserRoleGroupName(roleGroupId);

         if (roleGroupId == RolesEnum.DEALER.getId()) {
            dealerId = String.valueOf(user.getDealerId());
         }

         DynamicQuery query = ReportPlafondLocalServiceUtil.dynamicQuery();
         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }
         if (!periode.equalsIgnoreCase("ALL") && !periode.isEmpty()) {
            Date periodDate = DateUtil.stringToDate(periode);
            query.add(RestrictionsFactoryUtil.eq("Periode", periodDate));
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         if (dealerId.equalsIgnoreCase("ALL")) {
            if (roleGroupId == RolesEnum.HO_DEALER.getId()) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
               DynamicQuery dealersQuery = DealerLocalServiceUtil.dynamicQuery();
               dealersQuery.add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()));
               dealersQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
               dealersQuery.setProjection(PropertyFactoryUtil.forName("Id"));
               query.add(PropertyFactoryUtil.forName("DealerId").in(dealersQuery));
            }
         }
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         List<ReportPlafond> reportPlafonds = ReportPlafondLocalServiceUtil.dynamicQuery(query);

         for (ReportPlafond reportPlafond : reportPlafonds) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", reportPlafond.getId());
            dto.put("fileId", reportPlafond.getFileId());
            dto.put("fileName", reportPlafond.getFileName());
            dto.put("periode", DateUtil.dateToString(reportPlafond.getPeriode()));
            dto.put("periodeSort", reportPlafond.getModifiedDate().getTime());
            dto.put("uploadDate", DateUtil.dateToString(reportPlafond.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H));
            dto.put("uploadDateSort", reportPlafond.getModifiedDate().getTime());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = reportPlafonds.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      jsonObject.put("role", role);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}
