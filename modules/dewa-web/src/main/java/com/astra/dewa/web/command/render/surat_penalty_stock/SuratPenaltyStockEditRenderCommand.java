package com.astra.dewa.web.command.render.surat_penalty_stock;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.SuratPenaltyStock;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SuratPenaltyStockLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
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

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SURAT_PENALTY_STOCK,
            "mvc.command.name=/surat-penalty-stock-edit"
      },
      service = {MVCRenderCommand.class}
)
public class SuratPenaltyStockEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(SuratPenaltyStockEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");

      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id.length() > 2) {
         action = "update";
         try {
            SuratPenaltyStock suratPenaltyStock = SuratPenaltyStockLocalServiceUtil.getSuratPenaltyStock(Long.parseLong(id));
            dto.put("id", suratPenaltyStock.getId());
            dto.put("dealerCode", suratPenaltyStock.getDealerId());
            Dealer dealer1 = DealerLocalServiceUtil.getDealer((int) suratPenaltyStock.getDealerId());
            dto.put("dealerName", dealer1.getName());
            dto.put("fileId", suratPenaltyStock.getFileId());
            dto.put("fileName", suratPenaltyStock.getFileName());
            dto.put("judul", suratPenaltyStock.getJudul());
            dto.put("tahun", suratPenaltyStock.getTahun());
            dto.put("periodeReviewId", suratPenaltyStock.getPeriode());
         } catch (PortalException e) {
            action = "invalid";
            LOG.error(e.getMessage(), e);
         }
      } else {
         action = "create";
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/surat-penalty-stock/form.jsp";
   }

}