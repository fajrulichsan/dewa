package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;

@Component(
   property = {
      "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
      "mvc.command.name=/find_user_by_windows_id"
   },
   service = {MVCResourceCommand.class}
)
public class FindUserByWindowsIdResourceCommand extends BaseMVCResourceCommand {

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      // Get Request Parameter
      String windowsId = ParamUtil.getString(resourceRequest, "windowsId");
      JSONObject result = JSONFactoryUtil.createJSONObject();

      try {
         JSONArray data = JSONFactoryUtil.createJSONArray();
         DynamicQuery userDQ = UserLocalServiceUtil.dynamicQuery();
         userDQ.add(PropertyFactoryUtil.forName("screenName").like("%" + windowsId + "%"));
         List<User> users = UserLocalServiceUtil.dynamicQuery(userDQ);

         for (User user : users) {
            JSONObject userJSON = JSONFactoryUtil.createJSONObject();
            userJSON.put("UserId", user.getUserId());
            userJSON.put("ScreenName", user.getScreenName());
            userJSON.put("FullName", user.getFullName());
            userJSON.put("Email", user.getEmailAddress());
            userJSON.put("PhotoURL", user.getPortraitURL(themeDisplay));
            data.put(userJSON);
         }

         result.put("Acknowledge", 1);
         result.put("Error", "");
         result.put("Data", data);
      } catch (NoSuchUserException e) {
         result = ERROR("User " + windowsId + " not found.");
      } catch (Exception e) {
         result = ERROR("Internal server error.");
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), result.toJSONString());
   }

}
