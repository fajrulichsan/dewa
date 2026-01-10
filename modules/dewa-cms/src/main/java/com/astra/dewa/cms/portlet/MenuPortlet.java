package com.astra.dewa.cms.portlet;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author cstagung0825
 */
@Component(
   immediate = true,
   property = {
      "com.liferay.portlet.display-category=Dewa UI",
      "com.liferay.portlet.instanceable=true",
      "javax.portlet.display-name=Menu",
      "javax.portlet.init-param.template-path=/",
      "javax.portlet.init-param.view-template=/menu/view.jsp",
      "javax.portlet.name=" + CmsPortletKeys.MENU,
      "javax.portlet.resource-bundle=content.Language",
      "javax.portlet.security-role-ref=power-user,user"
   },
   service = Portlet.class
)
public class MenuPortlet extends MVCPortlet {
   @Override
   public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

      // ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//      UserCompany userCompany = CommonUtil.getUserCompanyByUserLiferayId(themeDisplay.getUserId());

      int userType = 0;
      String typeName = "";
      String companyName = "";

//      if (Validator.isNotNull(userCompany)) {
//         userType = userCompany.getType();
//         typeName = userType == 1 ? "Affco" : "SO Function";
//
//         try {
//            companyName = CompanyLocalServiceUtil.getCompany(userCompany.getCompanyId()).getName();
//         } catch (Exception e) {
//            // e.printStackTrace();
//         }
//      }

      renderRequest.setAttribute("userType", userType);
      renderRequest.setAttribute("typeName", typeName);
      renderRequest.setAttribute("companyName", companyName);

      super.render(renderRequest, renderResponse);
   }
}