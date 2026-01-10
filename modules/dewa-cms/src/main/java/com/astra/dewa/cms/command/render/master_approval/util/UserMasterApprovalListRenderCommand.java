package com.astra.dewa.cms.command.render.master_approval.util;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.exception.NoSuchUsersDealersException;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
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
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.MASTER_APPROVAL,
            "mvc.command.name=/user-master-approval-list"
      },
      service = MVCResourceCommand.class
)
public class UserMasterApprovalListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UserMasterApprovalListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
      String roleId = httpServletRequest.getParameter("roleId");
      String term = httpServletRequest.getParameter("term");

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         if (Validator.isNotNull(roleId)) {
            jsonData = selects(roleId, term);
         } else {
            throw new NullPointerException("roleId is null");
         }
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }
      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      jsonObject.put("results", jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   public JSONArray selects(String roleId, String name) throws NoSuchUsersDealersException {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      List<UsersDealers> userList = new ArrayList<>();
            DynamicQuery userQuery = UsersDealersLocalServiceUtil.dynamicQuery();
      if (null != roleId && !roleId.isEmpty()) {
         int role = Integer.parseInt(roleId);
         DynamicQuery userRoleQuery = UserRoleTypeLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("RoleId", role))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .setProjection(ProjectionFactoryUtil.property("UserId"));
         List<Long> userRoleIds = UserRoleTypeLocalServiceUtil.dynamicQuery(userRoleQuery);
         long userRoleCount = UserRoleTypeLocalServiceUtil.dynamicQueryCount(userRoleQuery);

         if (userRoleCount > 0) {
            userQuery
                  .add(RestrictionsFactoryUtil.in("UserId", userRoleIds))
                  .add(RestrictionsFactoryUtil.ilike("FullName", "%" + name + "%"))
                  .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            userList = UsersDealersLocalServiceUtil.dynamicQuery(userQuery, 0 , 10);
         }
      }

      userList.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getUserId());
         dto.put("text", data.getFullName());
         jsonData.put(dto);
      });
      return jsonData;
   }
}