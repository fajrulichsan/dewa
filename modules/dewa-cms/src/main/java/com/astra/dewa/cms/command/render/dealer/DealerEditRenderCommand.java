package com.astra.dewa.cms.command.render.dealer;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Wilayah;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.WilayahLocalServiceUtil;
import com.astra.dewa.utils.GroupDealerEnum;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import java.util.List;

@Component(immediate = true, property = {
      "javax.portlet.name=" + CmsPortletKeys.DEALERWEB,
      "mvc.command.name=/dealer-edit"
}, service = { MVCRenderCommand.class })
public class DealerEditRenderCommand implements MVCRenderCommand {

   private final Log LOG = LogFactoryUtil.getLog(DealerEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");
      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (Integer.parseInt(id) > 0) {
         action = "update";
         try {
            Dealer dealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(id));
            dto.put("id", dealer.getId());
            dto.put("code", dealer.getCode());
            dto.put("name", dealer.getName());

            try {
               dto.put("groupDealerId", dealer.getGroupDealer());
               if (dealer.getGroupDealer() == GroupDealerEnum.HO_DEALER.ordinal()) {
                  dto.put("hoCode", dealer.getKodeHo());
               } else {
                  dto.put("hoCode", searchHODealer(dealer.getKodeHo()));
               }
            } catch (Exception e) {
               LOG.error(e.getMessage(), e);
               dto.put("groupDealerId", "1");
            }
            Cabang cabang;
            if (dealer.getCabangId() > 0) {
               cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());
               dto.put("cabangId", cabang.getId());
               dto.put("cabangName", cabang.getName());
            } else {
               dto.put("cabangId", "-");
               dto.put("cabangName", "-");
            }
            Wilayah wilayah;
            if (dealer.getWilayahId() > 0) {
               wilayah = WilayahLocalServiceUtil.getWilayah(dealer.getWilayahId());
               dto.put("wilayahId", wilayah.getId());
               dto.put("wilayahName", wilayah.getName());
            } else {
               dto.put("wilayahId", "-");
               dto.put("wilayahName", "-");
            }
         } catch (PortalException e) {
            action = "invalid";
         }
      } else {
         action = "create";
      }
      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/dealer/form.jsp";
   }

   private Integer searchHODealer(String code) {
      Integer result = 0;
      try {
         DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.add(RestrictionsFactoryUtil.eq("GroupDealer", 0));
         query.add(RestrictionsFactoryUtil.eq("Code", code));
         List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
         result = dealers.get(0).getId();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }

}
