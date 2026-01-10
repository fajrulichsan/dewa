package com.astra.dewa.web.command.render.training.materi.util;

import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/topik-materi-single-training"
   },
   service = MVCResourceCommand.class
)
public class TopikMateriSingleRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TopikMateriSingleRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
//      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);

      String entryId = httpReq.getParameter("entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      JSONObject jsonObject;

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         TopikMateri tm = TopikMateriLocalServiceUtil.getTopikMateri(Integer.parseInt(entryId));
         dto.put("id", tm.getId());
         dto.put("topikMateriName", tm.getName());
         jsonObject = SUCCESS("Sukses", entryId, dto);
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e);
            jsonObject = ERROR(500, e.getMessage());
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}