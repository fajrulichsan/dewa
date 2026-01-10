package com.astra.dewa.cms.command.render.master_role;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Roles;
import com.astra.dewa.service.RolesLocalServiceUtil;
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
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

/**
 * @author psmfajru1107
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.MASTER_ROLE,
                "mvc.command.name=/master-role-list"
        },
        service = MVCResourceCommand.class
)
public class MasterRoleListRenderCommand extends BaseMVCResourceCommand {
    private final Log LOG = LogFactoryUtil.getLog(MasterRoleListRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        int acknowledge = 0;
        int count = 0;

        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage;
        try {
            DynamicQuery query = RolesLocalServiceUtil.dynamicQuery();
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<Roles> roleList = RolesLocalServiceUtil.dynamicQuery(query);

            for (Roles role : roleList) {
                if (role.getId() != 1){
                    count++;
                    JSONObject dto = JSONFactoryUtil.createJSONObject();
                    dto.put("no", count);
                    dto.put("id", role.getId());
                    dto.put("name", HtmlUtil.escape(role.getName()));
                    jsonData.put(dto);
                }
            }
            acknowledge = 1;
            LOG.info(jsonData);
            count = roleList.size();
            jsonMessage = SUCCESS(200, "OK");
        } catch (Exception e) {
            LOG.error(e);
            jsonMessage = ERROR(500, e.getMessage());
        }
        JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }

}
