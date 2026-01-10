package com.astra.dewa.web.command.render.faktur.indirect.cms;

import com.astra.dewa.model.FakturIndirect;
import com.astra.dewa.service.FakturIndirectLocalServiceUtil;
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
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmahmad1402
 */
@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT,
      "mvc.command.name=/faktur-indirect/list"
   },
   service = MVCResourceCommand.class
)
public class FakturIndirectListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(FakturIndirectListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      int start = ParamUtil.getInteger(httpServletRequest, "start");
      int length = ParamUtil.getInteger(httpServletRequest, "length");
      int end = start + length;
      int draw = ParamUtil.getInteger(httpServletRequest, "draw");
      int orderColumn = ParamUtil.getInteger(httpServletRequest, "order[0][column]");
      String orderDir = ParamUtil.getString(httpServletRequest, "order[0][dir]");

      String dealerId = httpServletRequest.getParameter("dealerId");
      String invoiceDateParam = httpServletRequest.getParameter("invoiceDate");

      int count = start;
      long totalRecords = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         List<FakturIndirect> fakturIndirectList = new ArrayList<>();

         DynamicQuery query = FakturIndirectLocalServiceUtil.dynamicQuery();
         DynamicQuery countQuery = FakturIndirectLocalServiceUtil.dynamicQuery();

         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
            countQuery.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }

         if (!invoiceDateParam.equalsIgnoreCase("ALL") && !invoiceDateParam.isEmpty()) {
            Date invoiceDate = DateUtil.stringToDate(invoiceDateParam, DewaWebKeys.DATE_FORMAT_DOT);
            query.add(RestrictionsFactoryUtil.eq("InvoiceDate", invoiceDate));
            countQuery.add(RestrictionsFactoryUtil.eq("InvoiceDate", invoiceDate));
         }

         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
//         query.addOrder(OrderFactoryUtil.desc("UploadDate"));

         switch (orderColumn) {
            case 1:
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("FileName"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("FileName"));
               }
               break;
            case 2:
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("InvoiceDate"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("InvoiceDate"));
               }
               break;
            case 3:
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("UploadDate"));
               } else {
                  query.addOrder(OrderFactoryUtil.desc("UploadDate"));
               }
               break;
            default:
               query.addOrder(OrderFactoryUtil.desc("UploadDate"));
         }

         fakturIndirectList = FakturIndirectLocalServiceUtil.dynamicQuery(query, start, end);

         for (FakturIndirect fakturIndirect : fakturIndirectList) {
            try {
               count++;
               JSONObject dto = JSONFactoryUtil.createJSONObject();

               String invoiceDateAsString = "";
               if (Validator.isNotNull(fakturIndirect.getInvoiceDate())) {
                  invoiceDateAsString = DateUtil.dateToString(fakturIndirect.getInvoiceDate(), DewaWebKeys.DATE_FORMAT_DOT);
               }

               String uploadDateAsString = "";
               if (Validator.isNotNull(fakturIndirect.getInvoiceDate())) {
                  uploadDateAsString = DateUtil.dateToString(fakturIndirect.getUploadDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
               }

               dto.put("no", count);
               dto.put("id", fakturIndirect.getId());
               dto.put("invoiceDate", invoiceDateAsString);
               dto.put("invoiceDateSort", fakturIndirect.getInvoiceDate().getTime());
               dto.put("uploadDate", uploadDateAsString);
               dto.put("uploadDateSort", fakturIndirect.getUploadDate().getTime());
               dto.put("fileName", fakturIndirect.getFileName());
               dto.put("fileId", fakturIndirect.getFileId());
               jsonData.put(dto);
            } catch (Exception e) {
               LOG.error(e.getLocalizedMessage(), e);
            }
         }

         totalRecords = FakturIndirectLocalServiceUtil.dynamicQueryCount(countQuery);
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }
      JSONObject jsonObject = FORMAT(totalRecords, totalRecords, draw, jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
