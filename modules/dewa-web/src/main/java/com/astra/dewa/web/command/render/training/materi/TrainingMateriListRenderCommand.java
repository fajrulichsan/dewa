package com.astra.dewa.web.command.render.training.materi;

import com.astra.dewa.model.JenisMateri;
import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.model.TrainingMateri;
import com.astra.dewa.service.JenisMateriLocalServiceUtil;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
import com.astra.dewa.service.TrainingMateriLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
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
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=/training-materi-list"
      },
      service = MVCResourceCommand.class
)
public class TrainingMateriListRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(TrainingMateriListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      String jenisMateriId = httpReq.getParameter("jenisMateriId");
      String topikMateriId = httpReq.getParameter("topikMateriId");
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

//      log.info("draw : " + draw + " start : " + start + " length : " + length);
//      log.info("search : " + search + " regex : " + regex);
//      log.info("orderColumn : " + orderColumn + " orderDir : " + orderDir);

      long recordsTotal = 0;
      long recordsFiltered = 0;

      int count = first;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         DynamicQuery query = TrainingMateriLocalServiceUtil.dynamicQuery();
         DynamicQuery countQuery = TrainingMateriLocalServiceUtil.dynamicQuery();

         if (!jenisMateriId.equalsIgnoreCase("ALL") && !jenisMateriId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("JenisMateriId", Integer.parseInt(jenisMateriId)));
            countQuery.add(RestrictionsFactoryUtil.eq("JenisMateriId", Integer.parseInt(jenisMateriId)));
         }
         if (!topikMateriId.equalsIgnoreCase("ALL") && !topikMateriId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("TopikMateriId", Integer.parseInt(topikMateriId)));
            countQuery.add(RestrictionsFactoryUtil.eq("TopikMateriId", Integer.parseInt(topikMateriId)));
         }

         List<TrainingMateri> materis = new ArrayList<>();

         // Searching
         if (!search.isEmpty()) {
//            query.add(RestrictionsFactoryUtil.like("JudulMateri", "%" + search + "%"));
//            query.add(RestrictionsFactoryUtil.like("TopikMateri", "%" + search + "%"));
//
//            queryCount.add(RestrictionsFactoryUtil.like("JudulMateri", "%" + search + "%"));
//            queryCount.add(RestrictionsFactoryUtil.like("TopikMateri", "%" + search + "%"));

            Criterion criterion = null;
            criterion = RestrictionsFactoryUtil.like("JudulMateri", "%" + search + "%");
            // criterion = RestrictionsFactoryUtil.and(criterion, RestrictionsFactoryUtil.between("create_date",10/02/2012,10/03/2012));
            // criterion = RestrictionsFactoryUtil.or(criterion , RestrictionsFactoryUtil.eq("status", "Pending"));
//            criterion = RestrictionsFactoryUtil.or(criterion , RestrictionsFactoryUtil.like("TopikMateri", "%" + search + "%"));

//            Criterion criterionCount = null;
//            criterionCount = RestrictionsFactoryUtil.like("JudulMateri", "%" + search + "%");
            // criterion = RestrictionsFactoryUtil.and(criterion, RestrictionsFactoryUtil.between("create_date",10/02/2012,10/03/2012));
            // criterion = RestrictionsFactoryUtil.or(criterion , RestrictionsFactoryUtil.eq("status", "Pending"));
//            criterionCount = RestrictionsFactoryUtil.or(criterionCount , RestrictionsFactoryUtil.like("TopikMateri", "%" + search + "%"));

            query.add(criterion);
            countQuery.add(criterion);
         }

         // ORDER BY
         int column = 0;
         if (orderColumn != null && !orderColumn.isEmpty()) {
            column = Integer.parseInt(orderColumn);
         }

         switch (column) {
            case 1:
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("JenisMateriId"));
               } else if (orderDir.equals("desc")) {
                  query.addOrder(OrderFactoryUtil.desc("JenisMateriId"));
               } else {
                  query.addOrder(OrderFactoryUtil.asc("CreatedDate"));
               }
               break;
            case 2:
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("TopikMateriId"));
               } else if (orderDir.equals("desc")) {
                  query.addOrder(OrderFactoryUtil.desc("TopikMateriId"));
               } else {
                  query.addOrder(OrderFactoryUtil.asc("CreatedDate"));
               }
               break;
            case 3:
               if (orderDir.equals("asc")) {
                  query.addOrder(OrderFactoryUtil.asc("JudulMateri"));
               } else if (orderDir.equals("desc")) {
                  query.addOrder(OrderFactoryUtil.desc("JudulMateri"));
               } else {
                  query.addOrder(OrderFactoryUtil.asc("CreatedDate"));
               }
               break;
            default:
               query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
               break;
         }

//         if (column == 3) {
//            if (orderDir.equals("asc")) {
//               query.addOrder(OrderFactoryUtil.asc("JudulMateri"));
//            } else if (orderDir.equals("desc")) {
//               query.addOrder(OrderFactoryUtil.desc("JudulMateri"));
//            } else {
//               query.addOrder(OrderFactoryUtil.asc("CreatedDate"));
//            }
//         } else if (column == 2) {
//            if (orderDir.equals("asc")) {
//               query.addOrder(OrderFactoryUtil.asc("TopikMateriId"));
//            } else if (orderDir.equals("desc")) {
//               query.addOrder(OrderFactoryUtil.desc("TopikMateriId"));
//            } else {
//               query.addOrder(OrderFactoryUtil.asc("CreatedDate"));
//            }
//         } else if (column == 1) {
//            if (orderDir.equals("asc")) {
//               query.addOrder(OrderFactoryUtil.asc("JenisMateriId"));
//            } else if (orderDir.equals("desc")) {
//               query.addOrder(OrderFactoryUtil.desc("JenisMateriId"));
//            } else {
//               query.addOrder(OrderFactoryUtil.asc("CreatedDate"));
//            }
//         } else {
//            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
//         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         materis = TrainingMateriLocalServiceUtil.dynamicQuery(query, first, end);
         recordsTotal = TrainingMateriLocalServiceUtil.dynamicQueryCount(countQuery);

         for (TrainingMateri materi : materis) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", materi.getId());
            if (materi.getJenisMateriId() > 0) {
               JenisMateri jenisMateri = JenisMateriLocalServiceUtil.getJenisMateri(materi.getJenisMateriId());
               dto.put("jenisMateriId", jenisMateri.getId());
               dto.put("jenisMateriName", HtmlUtil.escape(jenisMateri.getName()));
            } else {
               dto.put("jenisMateriId", 0);
               dto.put("jenisMateriName", "-");
            }
            if (materi.getTopikMateriId() > 0) {
               TopikMateri topikMateri = TopikMateriLocalServiceUtil.getTopikMateri(materi.getTopikMateriId());
               dto.put("topikMateriId", topikMateri.getId());
               dto.put("topikMateriName", HtmlUtil.escape(topikMateri.getName()));
            } else {
               dto.put("topikMateriId", 0);
               dto.put("topikMateriName", "-");
            }
            dto.put("judulMateri", HtmlUtil.escape(materi.getJudulMateri()));
            dto.put("imageId", materi.getImageId());
            dto.put("imageName", HtmlUtil.escape(materi.getImageName()));
            if (materi.getImagePath().startsWith(themeDisplay.getPortalURL())) {
               dto.put("imagePath", HtmlUtil.escape(materi.getImagePath()));
            } else {
               dto.put("imagePath", "-");
            }
            jsonData.put(dto);
         }
         recordsFiltered = recordsTotal;
      } catch (Exception e) {
         log.error(e);
      }
      JSONObject jsonObject = FORMAT(recordsTotal, recordsFiltered, Integer.parseInt(draw), jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}