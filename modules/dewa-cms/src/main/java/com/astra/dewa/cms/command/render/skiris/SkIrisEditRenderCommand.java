package com.astra.dewa.cms.command.render.skiris;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Wilayah;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
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

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + CmsPortletKeys.SKIRISWEB,
      "mvc.command.name=/skiris-edit"
   },
   service = {MVCRenderCommand.class}
)
public class SkIrisEditRenderCommand implements MVCRenderCommand {

   private final Log log = LogFactoryUtil.getLog(SkIrisEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");
      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id.length() > 2){
         action="update";
         try {
            Dealer dealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(id));
            Cabang cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());
            Wilayah wilayah = WilayahLocalServiceUtil.getWilayah(dealer.getCabangId());
            dto.put("id", dealer.getId());
            dto.put("code", dealer.getCode());
            dto.put("name", dealer.getName());
            dto.put("kodeHo", dealer.getKodeHo());
//            dto.put("hoName", dealer.getHoName());
            dto.put("cabangId", dealer.getCabangId());
            dto.put("cabangId", cabang.getId());
            dto.put("cabangName", cabang.getName());
            dto.put("wilayahId", wilayah.getId());
            dto.put("wilayahName", wilayah.getName());
         } catch (PortalException e) {
            action="invalid";
         }
      } else {
         action="create";
      }
      request.setAttribute("data",  dto);
      request.setAttribute("action",  action);
      log.info("data : " + dto + "action : " + action);
      return "/skiris/form.jsp";
   }

}
