package com.astra.dewa.web.command.render.training.materi.util;

import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
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
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/topik-materi-list-training"
   },
   service = MVCResourceCommand.class
)
public class TopikMateriListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TopikMateriListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      // Data Filter
      int start = ParamUtil.getInteger(httpServletRequest, "start");
      int length = ParamUtil.getInteger(httpServletRequest, "length");
      int end = start + length;
      int draw = ParamUtil.getInteger(httpServletRequest, "draw");
      String orderColumn = ParamUtil.getString(httpServletRequest, "order[0][column]");
      String orderDir = ParamUtil.getString(httpServletRequest, "order[0][dir]");

      int count = 0;
      long totalRecords = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         DynamicQuery query = TopikMateriLocalServiceUtil.dynamicQuery();
         DynamicQuery countQuery = TopikMateriLocalServiceUtil.dynamicQuery();

         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         if (orderColumn.equals("0")) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("Name"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("Name"));
            }
         } else {
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         }

         List<TopikMateri> result = TopikMateriLocalServiceUtil.dynamicQuery(query, start, end);

         for (TopikMateri tm : result) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", tm.getId());
            dto.put("topikMateriName", HtmlUtil.escape(tm.getName()));
            jsonData.put(dto);
         }

         totalRecords = TopikMateriLocalServiceUtil.dynamicQueryCount(countQuery);

//         DynamicQuery query = TopikMateriLocalServiceUtil.dynamicQuery()
//               .addOrder(OrderFactoryUtil.desc("ModifiedDate"));
//         List<TopikMateri> topikMateris = TopikMateriLocalServiceUtil.dynamicQuery(query);
//         for (TopikMateri jm : topikMateris) {
//            count++;
//            JSONObject dto = JSONFactoryUtil.createJSONObject();
//            dto.put("no", count);
//            dto.put("id", jm.getId());
//            dto.put("topikMateriName", HtmlUtil.escape(jm.getName()));
//            jsonData.put(dto);
//         }

         count = jsonData.length();
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