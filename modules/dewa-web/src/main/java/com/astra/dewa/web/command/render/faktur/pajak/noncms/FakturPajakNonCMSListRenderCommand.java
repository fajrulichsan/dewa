package com.astra.dewa.web.command.render.faktur.pajak.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.FakturPajak;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.FakturPajakLocalServiceUtil;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK_NONCMS,
            "mvc.command.name=/faktur-pajak-non-cms-list"
      },
      service = MVCResourceCommand.class
)
public class FakturPajakNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(FakturPajakNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      String dealerId = httpReq.getParameter("dealerId");
      String invoiceDateParam = httpReq.getParameter("invoiceDate");
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

         Date invoiceDate = null;
         DynamicQuery query = FakturPajakLocalServiceUtil.dynamicQuery();

         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }

         if (!invoiceDateParam.equalsIgnoreCase("ALL") && !invoiceDateParam.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);
            invoiceDate = format.parse(invoiceDateParam);
            query.add(RestrictionsFactoryUtil.eq("InvoiceDate", invoiceDate));
         }

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
         query.addOrder(OrderFactoryUtil.desc("UploadDate"));

         List<FakturPajak> fakturPajakList = FakturPajakLocalServiceUtil.dynamicQuery(query);

         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);

         for (FakturPajak fakturPajak : fakturPajakList) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            String invoiceDateAsString = simpleDateFormat.format(fakturPajak.getInvoiceDate());

            String uploadDateAsString = "";
            if (!fakturPajak.getUploadDate().equals("")) {
               uploadDateAsString = DateUtil.dateToString(fakturPajak.getUploadDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
            }

            dto.put("no", count);
            dto.put("id", fakturPajak.getId());
            dto.put("dealerId", fakturPajak.getDealerId());
            dto.put("invoiceDate", HtmlUtil.escape(invoiceDateAsString));
            dto.put("invoiceDateSort", fakturPajak.getInvoiceDate().getTime());
            dto.put("uploadDate", HtmlUtil.escape(uploadDateAsString));
            dto.put("uploadDateSort", fakturPajak.getUploadDate().getTime());
            dto.put("keterangan", fakturPajak.getKeterangan());
            dto.put("fileId", fakturPajak.getFileId());
            dto.put("fileName", HtmlUtil.escape(fakturPajak.getFileName()));
            dto.put("filePath", fakturPajak.getFilePath());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = fakturPajakList.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e.getMessage());
         jsonMessage = ERROR(500, e.getMessage());
      }

      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      jsonObject.put("role", role);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}
