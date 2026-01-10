package com.astra.dewa.cms.command.render.master_approval;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.MasterApproval;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalLocalServiceUtil;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.revisi.ApplicationUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.MASTER_APPROVAL,
            "mvc.command.name=/master-approval-list"
      },
      service = MVCResourceCommand.class
)
public class MasterApprovalListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(MasterApprovalListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         DynamicQuery query = MasterApprovalLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         List<MasterApproval> masterApprovalList = MasterApprovalLocalServiceUtil.dynamicQuery(query);

         for (MasterApproval masterApproval : masterApprovalList) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();

            DynamicQuery appHeaderQuery = ApplicationHeaderLocalServiceUtil.dynamicQuery();
            appHeaderQuery.add(RestrictionsFactoryUtil.eq("ApplicationId", masterApproval.getMenuId()));
            appHeaderQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            appHeaderQuery.add(RestrictionsFactoryUtil.or(
                  PropertyFactoryUtil.forName("ApplicationHeaderStatusId").eq(ApplicationHeaderStatusEnum.WAITING.ordinal() + 1),
                  PropertyFactoryUtil.forName("ApplicationHeaderStatusId").eq(ApplicationHeaderStatusEnum.REVISE.ordinal() + 1)
            ));

            List<ApplicationHeader> headers = ApplicationHeaderLocalServiceUtil.dynamicQuery(appHeaderQuery);

            dto.put("isEditable", headers.isEmpty());

            String menuName = "";

            if (masterApproval.getMenuId() != 0) {
               menuName = ApplicationUtils.getApplicationName(masterApproval.getMenuId());
            }

            dto.put("no", count);
            dto.put("id", masterApproval.getId());
            dto.put("name", menuName);
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = masterApprovalList.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e.getMessage(), e);
            jsonMessage = ERROR(500, e.getMessage());
         }
      }

      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}
