package com.astra.dewa.cms.command.resource.user.management.util;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(immediate = true, property = {
      "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
      "mvc.command.name=/user-role-dealer"
}, service = MVCResourceCommand.class)
public class UserUserManagementResourceCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UserUserManagementResourceCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String term = httpReq.getParameter("term");
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      try {
         jsonData = selects(term);
      } catch (Exception e) {
         log.error(e);
      }
      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      jsonObject.put("results", jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   public static JSONArray selects(String name) {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
      disjunction
            .add(RestrictionsFactoryUtil.like("firstName", name + "%"))
            .add(RestrictionsFactoryUtil.like("middleName", name + "%"))
            .add(RestrictionsFactoryUtil.like("lastName", name + "%"))
            .add(RestrictionsFactoryUtil.like("emailAddress", name + "%"));
      
      DynamicQuery query = UserLocalServiceUtil
            .dynamicQuery()
            .add(disjunction);

      List<User> userList = UserLocalServiceUtil.dynamicQuery(query, 0, 50);
      userList.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getUserId());
         dto.put("text", data.getFullName());
         jsonData.put(dto);
      });
      
      return jsonData;
   }

}
