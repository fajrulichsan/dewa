package com.astra.dewa.cms.command.resource.user.management.util;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
      "mvc.command.name=/user-single-role-dealer"
   },
   service = MVCResourceCommand.class
)
public class UserSingleUserManagementResourceCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UserSingleUserManagementResourceCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
//      String screenName = httpReq.getParameter("screenName");
//      log.info("screenName : " + screenName);
      String userId = httpReq.getParameter("userId");

      JSONObject jsonData = JSONFactoryUtil.createJSONObject();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         jsonData = getSingleUser(Long.parseLong(userId));
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   public static JSONObject getSingleUser(long userId) {
      DynamicQuery dq = UserLocalServiceUtil.dynamicQuery();
//      dq.add(RestrictionsFactoryUtil.like("screenName", "%" + screenName + "%"));
//      List<User> users = UserLocalServiceUtil.dynamicQuery(dq);
      dq.add(RestrictionsFactoryUtil.eq("userId", userId));
      List<User> users = UserLocalServiceUtil.dynamicQuery(dq);
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      dto.put("userId", users.get(0).getUserId());
      dto.put("screenName", users.get(0).getScreenName());
      dto.put("fullName", users.get(0).getFullName());
      dto.put("email", users.get(0).getEmailAddress());
      return dto;
   }

}
