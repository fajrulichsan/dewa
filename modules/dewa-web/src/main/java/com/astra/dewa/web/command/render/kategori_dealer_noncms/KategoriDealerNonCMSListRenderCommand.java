package com.astra.dewa.web.command.render.kategori_dealer_noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.KategoriDealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.KategoriDealerLocalServiceUtil;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.KATEGORI_DEALER_NONCMS,
            "mvc.command.name=/kategori-dealer-noncms-list"
      },
      service = MVCResourceCommand.class
)

public class KategoriDealerNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(KategoriDealerNonCMSListRenderCommand.class);

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
         List<KategoriDealer> kategoriDealers = new ArrayList<>();

         DynamicQuery query = KategoriDealerLocalServiceUtil.dynamicQuery();
         if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
         }
         if (!periodeReview.equalsIgnoreCase("ALL") && !periodeReview.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("PeriodeReview", periodeReview));
         }
         if (RoleDealerUtils.getUserRoleGroup(usersDealers.getUserId()) == RolesEnum.HO_DEALER.getId()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", usersDealers.getDealerId()));
         }

         if (tahun.equalsIgnoreCase("ALL") && periodeReview.equalsIgnoreCase("ALL")) {
            query.add(RestrictionsFactoryUtil.eq("rowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            kategoriDealers = KategoriDealerLocalServiceUtil.dynamicQuery(query);
         } else {
            query.add(RestrictionsFactoryUtil.eq("rowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            kategoriDealers = KategoriDealerLocalServiceUtil.dynamicQuery(query);
         }

         for (KategoriDealer kategoriDealer : kategoriDealers) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            Dealer dealer1 = DealerLocalServiceUtil.getDealer(kategoriDealer.getDealerId());

            String dateFile = "";
            if (!kategoriDealer.getModifiedDate().equals("")) {
               dateFile = DateUtil.dateToString(kategoriDealer.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH + " HH:mm:ss");
            }

            dto.put("no", count);
            dto.put("id", kategoriDealer.getId());
            dto.put("dealerCode", kategoriDealer.getDealerId());
            dto.put("dealerName", HtmlUtil.escape(dealer1.getName()));
            dto.put("judul", HtmlUtil.escape(kategoriDealer.getJudul()));
            dto.put("periodeReviewName", HtmlUtil.escape(kategoriDealer.getPeriodeReview()));
            dto.put("tahun", kategoriDealer.getTahun());
            dto.put("fileId", kategoriDealer.getFileId());
            dto.put("fileName", kategoriDealer.getFileName());
            dto.put("dateFile", dateFile);
            dto.put("dateSorting", kategoriDealer.getModifiedDate().getTime());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = kategoriDealers.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
