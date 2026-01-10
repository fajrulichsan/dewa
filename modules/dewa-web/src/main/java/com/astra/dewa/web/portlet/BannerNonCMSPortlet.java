package com.astra.dewa.web.portlet;

import com.astra.dewa.model.Banner;
import com.astra.dewa.service.BannerLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=Banner NonCMS",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/banner/non-cms/view.jsp",
                "javax.portlet.name=" + DewaWebPortletKeys.BANNER_NONCMS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class BannerNonCMSPortlet extends MVCPortlet {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        // HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        List<Banner> bannerList = new ArrayList<>();
        List<String> imageBannerURL = new ArrayList<>();

        try {
            // AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

            DynamicQuery query = BannerLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            query.add(RestrictionsFactoryUtil.eq("IsShow", true));
            bannerList = BannerLocalServiceUtil.dynamicQuery(query);

            for (Banner banner : bannerList) {
                FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(banner.getFileId());
                String urlImage = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                      themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();
                imageBannerURL.add(urlImage);
            }

        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }

        request.setAttribute("bannerList", bannerList);
        request.setAttribute("bannerImages", imageBannerURL);
        super.render(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        super.serveResource(request, response);
    }
}
