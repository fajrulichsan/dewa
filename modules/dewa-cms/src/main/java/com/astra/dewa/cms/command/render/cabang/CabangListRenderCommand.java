package com.astra.dewa.cms.command.render.cabang;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.service.CabangLocalServiceUtil;
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
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.CABANGWEB,
            "mvc.command.name=/cabang-list"
      },
      service = MVCResourceCommand.class
)
public class CabangListRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(CabangListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      int acknowledge = 0;
      int count = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         DynamicQuery query = CabangLocalServiceUtil.dynamicQuery();
         List<Cabang> cabangs = new ArrayList<>();

         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         cabangs = CabangLocalServiceUtil.dynamicQuery(query);

         for (Cabang cabang : cabangs) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", cabang.getId());
            dto.put("name", cabang.getName());
            jsonData.put(dto);
         }
         acknowledge = 1;
         count = cabangs.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
