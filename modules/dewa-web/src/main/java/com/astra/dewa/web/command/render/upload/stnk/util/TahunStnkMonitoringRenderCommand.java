package com.astra.dewa.web.command.render.upload.stnk.util;

import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.TahunUtils;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_STNK_MONITORING,
      "mvc.command.name=upload-stnk-monitoring-tahun"
   },
   service = MVCResourceCommand.class
)
public class TahunStnkMonitoringRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(TahunStnkMonitoringRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      try {
         jsonData = getTahun();
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

   private JSONArray getTahun() {
      JSONArray result = JSONFactoryUtil.createJSONArray();
      try{
         List<Integer> listYear = new ArrayList<>();
         DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_STNK.ordinal() + 1));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<ApplicationHeader> applicationHeaders = ApplicationHeaderLocalServiceUtil.dynamicQuery(query);

         for(int i=0; i< applicationHeaders.size(); i++){
            Integer tahun = applicationHeaders.get(i).getReqYear();
            if(!listYear.contains(tahun)) {
               listYear.add(tahun);
            }
         }
         Collections.sort(listYear);

         result = TahunUtils.listTahun(listYear);

      }catch (Exception e) {
         log.error(e);
      }
      return result;
   }

}
