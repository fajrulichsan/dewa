package com.astra.dewa.web.command.render.kategori_dealer;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.KategoriDealer;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.KategoriDealerLocalServiceUtil;
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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.KATEGORI_DEALER,
            "mvc.command.name=/kategori-dealer-list"
      },
      service = MVCResourceCommand.class
)
public class KategoriDealerListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(KategoriDealerListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String tahun = httpReq.getParameter("tahun");
      String periodeReview = httpReq.getParameter("periodeReview");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         DynamicQuery query = KategoriDealerLocalServiceUtil.dynamicQuery();
         if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
         }
         if (!periodeReview.equalsIgnoreCase("ALL") && !periodeReview.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("PeriodeReview", periodeReview));
         }
         List<KategoriDealer> kategoriDealers = new ArrayList<>();
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
            Dealer dealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(String.valueOf(kategoriDealer.getDealerId())));

            String dateFile = "";
            if (!kategoriDealer.getModifiedDate().equals("")) {
               dateFile = DateUtil.dateToString(kategoriDealer.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH + " HH:mm:ss");
            }

            dto.put("no", count);
            dto.put("id", kategoriDealer.getId());
            dto.put("dealerCode", kategoriDealer.getDealerId());
            dto.put("dealerName", HtmlUtil.escape(dealer.getName()));
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
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e);
            jsonMessage = ERROR(500, e.getMessage());
         }
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}