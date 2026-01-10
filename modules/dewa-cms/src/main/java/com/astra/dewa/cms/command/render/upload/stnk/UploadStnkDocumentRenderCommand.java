package com.astra.dewa.cms.command.render.upload.stnk;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.utils.RoleDealerUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + CmsPortletKeys.UPLOAD_STNK,
      "mvc.command.name=upload-stnk-document"
   },
   service = {MVCRenderCommand.class}
)
public class UploadStnkDocumentRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadStnkDocumentRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      long userId = themeDisplay.getUser().getUserId();
      try {
         UsersDealers roleDealer = RoleDealerUtils.userId(userId);
         assert roleDealer != null;
         request.setAttribute("roleId",  roleDealer.getRoleId());
      } catch (Exception e) {
         LOG.error(e);
      }
      return "/upload/stnk/document.jsp";
   }
}