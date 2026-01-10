package com.astra.dewa.cms.command.render.wilayah;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Wilayah;
import com.astra.dewa.service.WilayahLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.INVALID;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.WILAYAHWEB,
            "mvc.command.name=/wilayah-edit"
      },
      service = {MVCRenderCommand.class}
)
public class WilayahEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(WilayahEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");
      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (Integer.parseInt(id) > 0) {
         action = UPDATE;
         try {
            Wilayah wilayah = WilayahLocalServiceUtil.getWilayah(Integer.parseInt(id));
            dto.put("id", wilayah.getId());
            dto.put("name", wilayah.getName());
         } catch (PortalException e) {
            LOG.error(e.getMessage(), e);
            action = INVALID;
         }
      } else {
         action = CREATE;
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/wilayah/form.jsp";
   }
}