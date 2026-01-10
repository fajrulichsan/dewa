package com.astra.dewa.cms.command.render.otorisasi_menu;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
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
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.OTORISASIMENU,
                "mvc.command.name=/otorisasi-menu-list"
        },
        service = MVCResourceCommand.class
)
public class OtorisasiMenuListRenderCommand extends BaseMVCResourceCommand {

    private final Log log = LogFactoryUtil.getLog(OtorisasiMenuListRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        int acknowledge = 0;
        int count = 0;

        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
        try {
            List<MenuAuthorization> menuAuthorizations = new ArrayList<>();

            DynamicQuery query = MenuAuthorizationLocalServiceUtil.dynamicQuery();
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            menuAuthorizations = MenuAuthorizationLocalServiceUtil.dynamicQuery(query);

            for (MenuAuthorization otorisasi : menuAuthorizations) {
                count++;
                JSONObject dto = JSONFactoryUtil.createJSONObject();
                dto.put("no", count);
                dto.put("id", otorisasi.getId());
                dto.put("name", HtmlUtil.escape(RolesLocalServiceUtil.getRoles(otorisasi.getRoleId()).getName()));

                jsonData.put(dto);
            }
            acknowledge = 1;
            log.info(jsonData);
            count = menuAuthorizations.size();
            jsonMessage = SUCCESS(200, "OK");
        } catch (Exception e) {
            log.error(e);
            jsonMessage = ERROR(500, e.getMessage());
        }
        JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }

}
