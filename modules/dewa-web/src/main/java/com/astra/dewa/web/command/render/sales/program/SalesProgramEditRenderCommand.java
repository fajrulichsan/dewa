package com.astra.dewa.web.command.render.sales.program;

import com.astra.dewa.model.SalesProgram;
import com.astra.dewa.service.SalesProgramLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.ParamUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_PROGRAM,
            "mvc.command.name=/sales-program-edit"
      },
      service = {MVCRenderCommand.class}
)
public class SalesProgramEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(SalesProgramEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String id = ParamUtil.getString(httpRequest, "id");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (!id.isEmpty()) {
         action = UPDATE;
         try {
            // CSRF AUTHENTICATION
            AuthTokenUtil.checkCSRFToken(httpRequest, this.getClass().getName());

            SalesProgram salesProgram = SalesProgramLocalServiceUtil.getSalesProgram(Long.parseLong(id));
            dto.put("id", salesProgram.getId());
            dto.put("fileId", salesProgram.getFileId());
            dto.put("fileName", salesProgram.getFileName());
            dto.put("rowStatus", salesProgram.getRowStatus());
            dto.put("tahun", salesProgram.getTahun());
            dto.put("periode", salesProgram.getPeriode());
         } catch (PortalException e) {
            if (e instanceof PrincipalException) {
               LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
               LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            }
            action = INVALID;
         }
      } else {
         action = CREATE;
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/sales-program/form.jsp";
   }
}