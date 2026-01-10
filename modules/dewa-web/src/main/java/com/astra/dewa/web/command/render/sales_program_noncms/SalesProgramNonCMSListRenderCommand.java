package com.astra.dewa.web.command.render.sales_program_noncms;

import com.astra.dewa.model.SalesProgram;
import com.astra.dewa.service.SalesProgramLocalServiceUtil;
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
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_PROGRAM_NONCMS,
            "mvc.command.name=/sales-program-noncms-list"
      },
      service = MVCResourceCommand.class
)

public class SalesProgramNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(SalesProgramNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String tahun = httpReq.getParameter("tahun");
      String periode = httpReq.getParameter("periode");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         DynamicQuery query = SalesProgramLocalServiceUtil.dynamicQuery();
         if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
         }
         if (!periode.equalsIgnoreCase("ALL") && !periode.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Periode", periode));
         }
         List<SalesProgram> salesPrograms = new ArrayList<>();
         if (tahun.equalsIgnoreCase("ALL") && periode.equalsIgnoreCase("ALL")) {
            query.add(RestrictionsFactoryUtil.eq("rowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            salesPrograms = SalesProgramLocalServiceUtil.dynamicQuery(query);
         } else {
            query.add(RestrictionsFactoryUtil.eq("rowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            salesPrograms = SalesProgramLocalServiceUtil.dynamicQuery(query);
         }

         for (SalesProgram salesProgram : salesPrograms) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            String dateFile = "";
            if (!salesProgram.getModifiedDate().equals("")) {
               dateFile = DateUtil.dateToString(salesProgram.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH + " HH:mm:ss");
            }

            dto.put("no", count);
            dto.put("id", salesProgram.getId());
            dto.put("fileId", salesProgram.getFileId());
            dto.put("fileName", HtmlUtil.escape(salesProgram.getFileName()));
            dto.put("rowStatus", salesProgram.getRowStatus());
            dto.put("tahun", salesProgram.getTahun());
            dto.put("periode", salesProgram.getPeriode());
            dto.put("periodeNumber", DateUtil.getMonthNumberByName(salesProgram.getPeriode()));
            dto.put("yearFile", salesProgram.getTahun());
            dto.put("dateFile", HtmlUtil.escape(dateFile));
            dto.put("dateSorting", salesProgram.getModifiedDate().getTime());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = salesPrograms.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
