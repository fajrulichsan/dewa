package com.astra.dewa.web.command.render.training.materi.util;

import com.astra.dewa.model.JenisMateri;
import com.astra.dewa.service.JenisMateriLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/jenis-materi-list-training"
   },
   service = MVCResourceCommand.class
)
public class JenisMateriListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(JenisMateriListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      int count = 0;
      long totalRecords = 0;

      int start = ParamUtil.getInteger(httpServletRequest, "start");
      int length = ParamUtil.getInteger(httpServletRequest, "length");
      int end = start + length;
      int draw = ParamUtil.getInteger(httpServletRequest, "draw");
      String orderColumn = ParamUtil.getString(httpServletRequest, "order[0][column]");
      String orderDir = ParamUtil.getString(httpServletRequest, "order[0][dir]");

      LOG.debug("start: " + start + " length: " + length + " draw: " + draw);

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         DynamicQuery query = JenisMateriLocalServiceUtil.dynamicQuery();
         DynamicQuery countQuery = JenisMateriLocalServiceUtil.dynamicQuery();

         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         if (orderColumn.equals("1")) {
            if (orderDir.equals("asc")) {
               query.addOrder(OrderFactoryUtil.asc("Name"));
            } else {
               query.addOrder(OrderFactoryUtil.desc("Name"));
            }
         } else {
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         }

         List<JenisMateri> result = JenisMateriLocalServiceUtil.dynamicQuery(query, start, end);

         for (JenisMateri jm : result) {
            try {
               count++;
               JSONObject dto = JSONFactoryUtil.createJSONObject();
               dto.put("no", count);
               dto.put("id", jm.getId());
               dto.put("jenisMateriName", HtmlUtil.escape(jm.getName()));
               dto.put("imageId", jm.getImageId());

               FileEntry imageEntry = DLAppLocalServiceUtil.getFileEntry(jm.getImageId());
               String imagePath =
                     themeDisplay.getPortalURL() +
                           themeDisplay.getPathContext() +
                           "/documents/" +
                           themeDisplay.getScopeGroupId() +
                           "/" + imageEntry.getFolderId() +
                           "/" + imageEntry.getFileName();

               dto.put("imagePath", imagePath);

//               dto.put("imageName", HtmlUtil.escape(jm.getImageName()));

//               if (jm.getImagePath().startsWith(themeDisplay.getPortalURL())) {
//                  dto.put("imagePath", HtmlUtil.escape(jm.getImagePath()));
//               } else {
//                  dto.put("imagePath", "-");
//               }
               jsonData.put(dto);
            } catch (Exception e) {
               LOG.error(e.getMessage(), e);
            }
         }

         totalRecords = JenisMateriLocalServiceUtil.dynamicQueryCount(countQuery);

//         DynamicQuery query = JenisMateriLocalServiceUtil.dynamicQuery()
//               .addOrder(OrderFactoryUtil.desc("ModifiedDate"));
//         List<JenisMateri> jenisMateris = JenisMateriLocalServiceUtil.dynamicQuery(query);
//         for (JenisMateri jm : jenisMateris) {
//            count++;
//            JSONObject dto = JSONFactoryUtil.createJSONObject();
//            dto.put("no", count);
//            dto.put("id", jm.getId());
//            dto.put("jenisMateriName", HtmlUtil.escape(jm.getName()));
//            dto.put("imageId", jm.getImageId());
//            dto.put("imageName", HtmlUtil.escape(jm.getImageName()));
//            if (jm.getImagePath().startsWith(themeDisplay.getPortalURL())) {
//               dto.put("imagePath", HtmlUtil.escape(jm.getImagePath()));
//            } else {
//               dto.put("imagePath", "-");
//            }
//            jsonData.put(dto);
//         }

         count = jsonData.length();
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }

      JSONObject jsonObject = FORMAT(totalRecords, totalRecords, draw, jsonData);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}