package com.astra.dewa.web.command.render.kategori_dealer;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.KategoriDealer;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.KategoriDealerLocalServiceUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.KATEGORI_DEALER,
            "mvc.command.name=/kategori-dealer-edit"
      },
      service = {MVCRenderCommand.class}
)
public class KategoriDealerEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(KategoriDealerEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (!id.isEmpty()) {
         action = UPDATE;
         try {
            // CSRF AUTHENTICATION
            AuthTokenUtil.checkCSRFToken(httpRequest, this.getClass().getName());

            KategoriDealer kategoriDealer = KategoriDealerLocalServiceUtil.getKategoriDealer(Long.parseLong(id));
            dto.put("id", kategoriDealer.getId());
            dto.put("dealerCode", kategoriDealer.getDealerId());
            Dealer dealer = DealerLocalServiceUtil.getDealer(kategoriDealer.getDealerId());
            dto.put("dealerName", dealer.getName());
            dto.put("judul", kategoriDealer.getJudul());
            dto.put("tahun", kategoriDealer.getTahun());
            dto.put("periodeReviewId", kategoriDealer.getPeriodeReview());
            dto.put("fileId", kategoriDealer.getFileId());
            dto.put("fileName", kategoriDealer.getFileName());
         } catch (PortalException e) {
            if (e instanceof PrincipalException) {
               LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
               LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            } else {
               LOG.error(e.getMessage(), e);
            }
            action = INVALID;
         }
      } else {
         action = CREATE;
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/kategori-dealer/form.jsp";
   }
}