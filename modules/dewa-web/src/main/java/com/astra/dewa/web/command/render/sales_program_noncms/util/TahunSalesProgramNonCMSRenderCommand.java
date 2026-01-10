package com.astra.dewa.web.command.render.sales_program_noncms.util;

import com.astra.dewa.model.SalesProgram;
import com.astra.dewa.service.SalesProgramLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.SALES_PROGRAM_NONCMS,
      "mvc.command.name=/tahun-sales-program-noncms"
   },
   service = MVCResourceCommand.class
)
public class TahunSalesProgramNonCMSRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(TahunSalesProgramNonCMSRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         jsonData = listTahun();
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   private JSONArray listTahun () {
      List<Integer> listYear = getTahun();
      JSONArray tahunArray = JSONFactoryUtil.createJSONArray();
      try {
         for(int i=0; i< listYear.size(); i++){
            if(!listYear.contains(i)){
               JSONObject tahunObject = JSONFactoryUtil.createJSONObject();
               tahunObject.put("id", listYear.get(i));
               tahunObject.put("text", listYear.get(i));
               tahunArray.put(tahunObject);
            }
         }
      }catch (Exception e) {
         log.error(e);
      }
      return tahunArray;
   }

   private List<Integer> getTahun () {
      List<Integer> listYear = new ArrayList<>();
      try{
         DynamicQuery query = SalesProgramLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("rowStatus", true));
         List<SalesProgram> salesPrograms = SalesProgramLocalServiceUtil.dynamicQuery(query);
         for(int i=0; i< salesPrograms.size(); i++){
            Integer tahun = salesPrograms.get(i).getTahun();
            if(!listYear.contains(tahun)) {
               listYear.add(tahun);
            }
         }
         Collections.sort(listYear);
      }catch (Exception e) {
         log.error(e);
      }
      return listYear;
   }

}
