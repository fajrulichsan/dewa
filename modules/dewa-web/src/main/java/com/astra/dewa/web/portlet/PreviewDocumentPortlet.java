package com.astra.dewa.web.portlet;

import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.PDFProcessorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(
      immediate = true,
      property = {
            "com.liferay.portlet.display-category=Dewa UI",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Preview Document",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/preview-document/view.jsp",
            "javax.portlet.name=" + DewaWebPortletKeys.PREVIEW_DOCUMENT,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)

public class PreviewDocumentPortlet extends MVCPortlet {
   private final Log log = LogFactoryUtil.getLog(PreviewDocumentPortlet.class);

   @Override
   public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
      ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
      long userId = themeDisplay.getUserId();
      long documentId = ParamUtil.getLong(httpRequest, "id", 0L);

      boolean hasAccess = false;
      boolean isDocumentAvailable = false;
      List<String> documentPagesURL = new ArrayList<>();
      String currentSiteURL = themeDisplay.getSiteGroup().getDisplayURL(themeDisplay, true);

      try {
         FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(documentId);
         FileVersion fileVersion = fileEntry.getFileVersion();
         boolean hasPDFImages = PDFProcessorUtil.hasImages(fileVersion);

         if (hasPDFImages) {
            int previewFileCount = PDFProcessorUtil.getPreviewFileCount(fileVersion);
            String previewQueryString = "&previewFileIndex=";
            for (int i = 1; i <= previewFileCount; i++) {
               String previewURL = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                     themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + 
                     fileEntry.getFileName() + "?" + previewQueryString + i;
               documentPagesURL.add(previewURL);
            }
         }

         boolean isDSO = RoleDealerUtils.getUserRoleGroup(userId) == RolesEnum.ADMIN_DSO.getId() || RoleDealerUtils.getUserRoleGroup(userId) == RolesEnum.DSO.getId();

         if (isDSO) {
            hasAccess = true;
         }

         isDocumentAvailable = true;

      } catch (Exception e) {
         log.error(e.getMessage(), e);
      }

      renderRequest.setAttribute("documentAccess", hasAccess);
      renderRequest.setAttribute("documentPages", documentPagesURL);
      renderRequest.setAttribute("currentSiteURL", currentSiteURL);
      renderRequest.setAttribute("documentAvailable", isDocumentAvailable);

      super.render(renderRequest, renderResponse);
   }
}

