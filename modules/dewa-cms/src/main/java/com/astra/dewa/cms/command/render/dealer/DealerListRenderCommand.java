package com.astra.dewa.cms.command.render.dealer;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Wilayah;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.WilayahLocalServiceUtil;
import com.astra.dewa.utils.GroupDealerEnum;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
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
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(immediate = true, property = {
      "javax.portlet.name=" + CmsPortletKeys.DEALERWEB,
      "mvc.command.name=/dealer-list"
}, service = MVCResourceCommand.class)
public class DealerListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DealerListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String draw = httpReq.getParameter("draw");
      if (draw.isEmpty()) {
         draw = "0";
      }
      String start = httpReq.getParameter("start");
      if (start.isEmpty()) {
         start = "0";
      }
      String length = httpReq.getParameter("length");
      if (length.isEmpty()) {
         length = "0";
      }
      int first = Integer.parseInt(start);
      int end = first + Integer.parseInt(length);

      String search = httpReq.getParameter("search[value]");
      String orderColumn = httpReq.getParameter("order[0][column]");
      String orderDir = httpReq.getParameter("order[0][dir]");

      if (orderColumn == null) {
         orderColumn = "-1";
      }

      long recordsTotal = 0;
      int count = first;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
         DynamicQuery countQuery = DealerLocalServiceUtil.dynamicQuery();
         List<Dealer> dealers;
         // Search
         if (!search.isEmpty()) {
            Criterion criterion = null;
            criterion = RestrictionsFactoryUtil.like("Name", "%" + search + "%");
            criterion = RestrictionsFactoryUtil.or(criterion,
                  RestrictionsFactoryUtil.like("Code", "%" + search + "%"));
            criterion = RestrictionsFactoryUtil.or(criterion,
                  RestrictionsFactoryUtil.like("KodeHo", "%" + search + "%"));
            query.add(criterion);
            countQuery.add(criterion);
         }
         // Order By
         Integer newOrderColumn = Integer.parseInt(orderColumn);
         if (newOrderColumn.equals(0)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("Code"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("Code"));
            }
         } else if (newOrderColumn.equals(1)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("KodeHo"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("KodeHo"));
            }
         } else if (newOrderColumn.equals(2)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("Name"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("Name"));
            }
         } else if (newOrderColumn.equals(3)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("GroupDealer"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("GroupDealer"));
            }
         } else if (newOrderColumn.equals(4)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("CabangId"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("CabangId"));
            }
         } else if (newOrderColumn.equals(5)) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("WilayahId"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("WilayahId"));
            }
         } else {
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         dealers = DealerLocalServiceUtil.dynamicQuery(query, first, end);
         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         recordsTotal = DealerLocalServiceUtil.dynamicQueryCount(countQuery);

         for (Dealer dealer : dealers) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", dealer.getId());
            dto.put("code", dealer.getCode());
            dto.put("name", dealer.getName());
            dto.put("hoCode", dealer.getKodeHo());
            try {
               if (dealer.getGroupDealer() == GroupDealerEnum.HO_DEALER.ordinal()) {
                  dto.put("groupDealer", "Ho Dealer");
               } else {
                  dto.put("groupDealer", "Dealer");
               }
            } catch (Exception e) {
               dto.put("groupDealer", "-");
            }
            try {
               Cabang cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());
               dto.put("cabangId", cabang.getId());
               dto.put("cabangName", cabang.getName());
            } catch (Exception e) {
               dto.put("cabangId", "-");
               dto.put("cabangName", "-");
            }
            try {
               Wilayah wilayah = WilayahLocalServiceUtil.getWilayah(dealer.getWilayahId());
               dto.put("wilayahId", wilayah.getId());
               dto.put("wilayahName", wilayah.getName());
            } catch (Exception e) {
               dto.put("wilayahId", "-");
               dto.put("wilayahName", "-");
            }
            jsonData.put(dto);
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: "
                  + PortalUtil.getUserId(httpReq));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpReq, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage());
         }
      }
      JSONObject jsonObject = FORMAT(recordsTotal, recordsTotal, Integer.parseInt(draw), jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
