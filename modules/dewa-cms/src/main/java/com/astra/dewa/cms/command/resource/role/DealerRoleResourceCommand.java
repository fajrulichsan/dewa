package com.astra.dewa.cms.command.resource.role;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.utils.RoleDealerUtils;
import com.liferay.portal.kernel.exception.SystemException;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

/**
 * @author psmahmad1402
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
            "mvc.command.name=/cms/dealer-roles"
      },
      service = MVCResourceCommand.class
)
public class DealerRoleResourceCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      try {
         jsonData = RoleDealerUtils.selectNoAdminAndDSO();
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (SystemException se) {
         LOG.error(se);
         jsonMessage = ERROR(500, se.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
