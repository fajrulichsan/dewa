package com.astra.dewa.web.command.render.surat_penalty_stock_noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.SuratPenaltyStock;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SuratPenaltyStockLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
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
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SURAT_PENALTY_STOCK_NONCMS,
            "mvc.command.name=/surat-penalty-stock-noncms-list"
      },
      service = MVCResourceCommand.class
)

public class SuratPenaltyStockNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(SuratPenaltyStockNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String tahun = httpReq.getParameter("tahun");
      String periodeReview = httpReq.getParameter("periodeReview");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         UsersDealers usersDealers = UsersDealersLocalServiceUtil.findUsersDealersIdFromUserId(themeDisplay.getUserId(), true);
         List<SuratPenaltyStock> suratPenaltyStocks = new ArrayList<>();

         DynamicQuery query = SuratPenaltyStockLocalServiceUtil.dynamicQuery();
         if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
         }
         if (!periodeReview.equalsIgnoreCase("ALL") && !periodeReview.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Periode", periodeReview));
         }
         if (RoleDealerUtils.getUserRoleGroup(usersDealers.getUserId()) == RolesEnum.HO_DEALER.getId()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", usersDealers.getDealerId()));
         }

         if (tahun.equalsIgnoreCase("ALL") && periodeReview.equalsIgnoreCase("ALL")) {
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            suratPenaltyStocks = SuratPenaltyStockLocalServiceUtil.dynamicQuery(query);
         } else {
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            suratPenaltyStocks = SuratPenaltyStockLocalServiceUtil.dynamicQuery(query);
         }

         for (SuratPenaltyStock suratPenaltyStock : suratPenaltyStocks) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            Dealer dealer = DealerLocalServiceUtil.getDealer((int) suratPenaltyStock.getDealerId());

            String dateFile = "";
            if (!suratPenaltyStock.getModifiedDate().equals("")) {
               dateFile = DateUtil.dateToString(suratPenaltyStock.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH + " HH:mm:ss");
            }

            dto.put("no", count);
            dto.put("id", suratPenaltyStock.getId());
            dto.put("dealerCode", suratPenaltyStock.getDealerId());
            dto.put("dealerName", HtmlUtil.escape(dealer.getName()));
            dto.put("judul", HtmlUtil.escape(suratPenaltyStock.getJudul()));
            dto.put("periodeReviewName", HtmlUtil.escape(suratPenaltyStock.getPeriode()));
            dto.put("tahun", suratPenaltyStock.getTahun());
            dto.put("fileId", suratPenaltyStock.getFileId());
            dto.put("fileName", HtmlUtil.escape(suratPenaltyStock.getFileName()));
            dto.put("dateFile", dateFile);
            dto.put("dateSorting", suratPenaltyStock.getModifiedDate().getTime());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = suratPenaltyStocks.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}