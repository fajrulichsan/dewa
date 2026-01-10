package com.astra.dewa.web.command.render.faktur.pajak;

import com.astra.dewa.model.FakturPajak;
import com.astra.dewa.service.FakturPajakLocalServiceUtil;
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
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK,
      "mvc.command.name=/faktur-pajak-list"
   },
   service = MVCResourceCommand.class
)
public class FakturPajakListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(FakturPajakListRenderCommand.class);

   @SuppressWarnings("deprecation")
   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String dealerId = httpReq.getParameter("dealerId");
      String invoiceDate = httpReq.getParameter("invoiceDate");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         DynamicQuery query = FakturPajakLocalServiceUtil.dynamicQuery();
         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }
         if (!invoiceDate.equalsIgnoreCase("ALL") && !invoiceDate.isEmpty()) {
            try {
               Date invoice = null;
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);
               invoice = simpleDateFormat.parse(invoiceDate);
               query.add(RestrictionsFactoryUtil.eq("InvoiceDate", invoice));
            } catch (ParseException e) {
               LOG.error("Error parsing date: " + e.getMessage());
            }
         }
         List<FakturPajak> fakturPajaks = new ArrayList<>();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("UploadDate"));
         fakturPajaks = FakturPajakLocalServiceUtil.dynamicQuery(query);

         for (FakturPajak fakturPajak : fakturPajaks) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);
            String invoiceDateAsString = simpleDateFormat.format(fakturPajak.getInvoiceDate());

            String uploadDateAsString = "";
            if (!fakturPajak.getUploadDate().equals("")) {
               uploadDateAsString = DateUtil.dateToString(fakturPajak.getUploadDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
            }

            dto.put("no", count);
            dto.put("id", fakturPajak.getId());
            dto.put("dealerId", fakturPajak.getDealerId());
            dto.put("year", fakturPajak.getInvoiceDate().getYear());
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
         count = fakturPajaks.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
