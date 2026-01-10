package com.astra.dewa.web.command.render.sp3d_noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.SP3D;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SP3DLocalServiceUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.SP3D_PORTLET_NONCMS,
            "mvc.command.name=/sp3d-non-cms-list"
      },
      service = MVCResourceCommand.class
)
public class SP3DNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(SP3DNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String tahun = httpReq.getParameter("tahun");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
         assert user != null;
         int roleGroupId = RoleDealerUtils.getUserRoleGroup(user.getUserId());

         DynamicQuery query = SP3DLocalServiceUtil.dynamicQuery();
         if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
         }
         if (roleGroupId == RolesEnum.HO_DEALER.getId()) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
            DynamicQuery dealerQuery = DealerLocalServiceUtil.dynamicQuery()
                  .add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()))
                  .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                  .setProjection(PropertyFactoryUtil.forName("Id"));
            query.add(PropertyFactoryUtil.forName("DealerId").in(dealerQuery));
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));

         List<SP3D> sp3d = SP3DLocalServiceUtil.dynamicQuery(query);

         for (SP3D listSp3d : sp3d) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(listSp3d.getDealerId());
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", listSp3d.getId());
            dto.put("tahun", listSp3d.getTahun());
            dto.put("dealerName", dealer.getName());
            dto.put("fileId", listSp3d.getFileId());
            dto.put("fileName", listSp3d.getFileName());
            dto.put("filePath", listSp3d.getFilePath());
            dto.put("uploadDate", DateUtil.dateToString(listSp3d.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H));
            dto.put("uploadDateSort", listSp3d.getModifiedDate().getTime());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = sp3d.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
