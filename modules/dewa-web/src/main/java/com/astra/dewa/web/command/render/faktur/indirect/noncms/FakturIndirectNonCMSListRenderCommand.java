package com.astra.dewa.web.command.render.faktur.indirect.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.FakturIndirect;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.FakturIndirectLocalServiceUtil;
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

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT_NONCMS,
            "mvc.command.name=/non-cms/faktur-indirect/list"
      },
      service = MVCResourceCommand.class
)
public class FakturIndirectNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(FakturIndirectNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      String dealerId = httpReq.getParameter("dealerId");;
      String role  = "";
      String invoiceDateParam = httpReq.getParameter("invoiceDate");

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

         DynamicQuery query = FakturIndirectLocalServiceUtil.dynamicQuery();

         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }

         if (!invoiceDateParam.equalsIgnoreCase("ALL") && !invoiceDateParam.isEmpty()) {
            Date invoiceDate = DateUtil.stringToDate(invoiceDateParam, DewaWebKeys.DATE_FORMAT_DOT);
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

         List<FakturIndirect> fakturIndirects = FakturIndirectLocalServiceUtil.dynamicQuery(query);

         for (FakturIndirect fakturIndirect : fakturIndirects) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();

            String invoiceDateAsString = "";
            if (!fakturIndirect.getInvoiceDate().equals("")) {
               invoiceDateAsString = DateUtil.dateToString(fakturIndirect.getInvoiceDate(), (DewaWebKeys.DATE_FORMAT_DOT));
            }

            String uploadDateAsString = "";
            if (!fakturIndirect.getUploadDate().equals("")) {
               uploadDateAsString = DateUtil.dateToString(fakturIndirect.getUploadDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
            }

            dto.put("no", count);
            dto.put("id", fakturIndirect.getId());
            dto.put("dealerId", dealerId);
            dto.put("invoiceDate", invoiceDateAsString);
            dto.put("invoiceDateSort", fakturIndirect.getInvoiceDate().getTime());
            dto.put("uploadDate", uploadDateAsString);
            dto.put("uploadDateSort", fakturIndirect.getUploadDate().getTime());
            dto.put("keterangan", fakturIndirect.getKeterangan());
            dto.put("fileId", fakturIndirect.getFileId());
            dto.put("fileName", fakturIndirect.getFileName());
            dto.put("filePath", fakturIndirect.getFilePath());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = fakturIndirects.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }

      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      jsonObject.put("role", role);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}