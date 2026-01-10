package com.astra.dewa.web.command.render.e_srut.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.ESrut;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.ESrutLocalServiceUtil;
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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.E_SRUT_NONCMS,
            "mvc.command.name=/e-srut-non-cms-list"
      },
      service = MVCResourceCommand.class
)
public class ESrutNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(ESrutNonCMSListRenderCommand.class);
   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      String dealerId = httpReq.getParameter("dealerId");
      String periodParam = httpReq.getParameter("periodDate");
      String role = "";

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
         assert user != null;
         int roleGroupId = RoleDealerUtils.getUserRoleGroup(user.getUserId());
         role = RoleDealerUtils.getUserRoleGroupName(roleGroupId);

         if (roleGroupId == RolesEnum.DEALER.getId()) {
            dealerId = String.valueOf(user.getDealerId());
         }

         Date periodDate = null;
         DynamicQuery query = ESrutLocalServiceUtil.dynamicQuery();

         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }

         if (!periodParam.equalsIgnoreCase("ALL") && !periodParam.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);
            periodDate = sdf.parse(periodParam);
            query.add(RestrictionsFactoryUtil.eq("PeriodDate", periodDate));
         }

         List<ESrut> eSrutList = new ArrayList<>();

         if (dealerId.equals("ALL")) {
            if (roleGroupId == RolesEnum.HO_DEALER.getId()) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
               DynamicQuery dealerQuery = DealerLocalServiceUtil.dynamicQuery();
               dealerQuery.add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()));
               dealerQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
               dealerQuery.setProjection(PropertyFactoryUtil.forName("Id"));
               query.add(PropertyFactoryUtil.forName("DealerId").in(dealerQuery));
            }
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         eSrutList = ESrutLocalServiceUtil.dynamicQuery(query);

         SimpleDateFormat sdf = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);

         for (ESrut eSrut : eSrutList) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            String fileDate = "";

            if (!eSrut.getModifiedBy().isEmpty()) {
               fileDate = DateUtil.dateToString(eSrut.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
            }

            dto.put("no", count);
            dto.put("id", eSrut.getId());
            dto.put("periodDate", HtmlUtil.escape(sdf.format(eSrut.getPeriodDate())));
            dto.put("fileDate", HtmlUtil.escape(fileDate));
            dto.put("fileDateSort", eSrut.getModifiedDate().getTime());
            dto.put("fileId", eSrut.getFileId());
            dto.put("fileName", HtmlUtil.escape(eSrut.getFileName()));
            dto.put("filePath", eSrut.getFilePath());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = eSrutList.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof ParseException) {
            log.error("Error parsing date: " + e.getMessage());
         } else {
            log.error(e.getMessage(), e);
         }
         jsonMessage = ERROR(500, e.getMessage());
      }

      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      jsonObject.put("role", role);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}
