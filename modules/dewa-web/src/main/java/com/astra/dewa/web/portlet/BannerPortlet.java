package com.astra.dewa.web.portlet;

import com.astra.dewa.model.Banner;
import com.astra.dewa.service.BannerLocalServiceUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      immediate = true,
      property = {
            "com.liferay.portlet.display-category=Dewa UI",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Banner",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/banner/view.jsp",
            "javax.portlet.name=" + DewaWebPortletKeys.BANNER,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)

public class BannerPortlet extends MVCPortlet {
   private final Log LOG = LogFactoryUtil.getLog(BannerPortlet.class);

   @Override
   public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
      super.render(request, response);
   }

   @Override
   public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      String state = ParamUtil.getString(request, "state");
      PrintWriter writer = response.getWriter();

      if (state.equalsIgnoreCase("POST")) {
         UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

         long fileId = ParamUtil.getLong(uploadPortletRequest, "bannerImageFileId");
         String fileName = ParamUtil.getString(uploadPortletRequest, "bannerImageFileName");
         String filePath = ParamUtil.getString(uploadPortletRequest, "bannerImageFilePath");

         try {
            DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileId);
            String extensionFile = fileEntry.getExtension();

            if (extensionFile.equalsIgnoreCase("PNG") || extensionFile.equalsIgnoreCase("JPG") ||
                  extensionFile.equalsIgnoreCase("JPEG")) {

               Banner banner = BannerLocalServiceUtil.createBanner(0);
               banner.setFileId(fileId);
               banner.setFileName(fileName);
               banner.setFilePath(filePath);
               banner.setIsShow(false);
               banner.setRowStatus(true);
               banner.setCreatedBy(themeDisplay.getUser().getFullName());
               banner.setCreatedDate(new Date());
               banner.setModifiedBy(themeDisplay.getUser().getFullName());
               banner.setModifiedDate(new Date());
               BannerLocalServiceUtil.addBanner(banner);

               jsonObject = SUCCESS("Data tersimpan", "");

            } else {
               jsonObject = WARNING("Periksa kembali jenis file yang diinput");
            }

         } catch (Exception e) {
            e.printStackTrace();
         }
         writer.print(jsonObject.toJSONString());

      } else if (state.equalsIgnoreCase("UPLOAD_FILE")) {

         UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
         int acknowledge = 1;
         String status = "success";
         String message = "";

         String fileName = ParamUtil.getString(request, "file-name");
         File file = uploadPortletRequest.getFile("file-upload");
         HashMap<String, String> payload = new HashMap<>();

         fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "." + FileUtil.getExtensionByStringHandling(fileName).get();

         try {
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, request, DewaWebKeys.BANNER_MENU);
         } catch (Exception e) {
            acknowledge = 0;
            status = "error";
            message = e.getMessage();

            LOG.error(e.getMessage());
            LOG.error(e.getLocalizedMessage());
         }

         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
         jsonObject.put("acknowledge", acknowledge);
         jsonObject.put("status", status);
         jsonObject.put("message", message);
         jsonObject.put("fileURL", payload.get("URL"));
         jsonObject.put("fileID", payload.get("ID"));
         jsonObject.put("fileName", payload.get("fileName"));
         writer.print(jsonObject.toJSONString());

      } else if (state.equalsIgnoreCase("LIST_DATA")) {
         int itemPerPage = ParamUtil.getInteger(request, "itemPerPage");
         int currentPage = ParamUtil.getInteger(request, "currentPage");

         int start = (currentPage - 1) * itemPerPage;
         int end = currentPage * itemPerPage;
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

         try {
            DynamicQuery query = BannerLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("CreatedDate"));

            List<Banner> banners = BannerLocalServiceUtil.dynamicQuery(query, start, end);

            DynamicQuery dynamicQuery = BannerLocalServiceUtil.dynamicQuery();
            dynamicQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            Long count = BannerLocalServiceUtil.dynamicQueryCount(dynamicQuery);

            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            if (!banners.isEmpty()) {
               for (Banner banner : banners) {
                  JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

                  FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(banner.getFileId());
                  String urlImage = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                        themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();

                  jsonObj.put("id", banner.getId());
                  jsonObj.put("url", urlImage);
                  jsonObj.put("isShow", banner.getIsShow());
                  jsonArray.put(jsonObj);
               }
            }

            jsonObject.put("count", count);
            jsonObject.put("data", jsonArray);
            writer.print(jsonObject);

         } catch (Exception e) {
            e.printStackTrace();
         }

      } else if (state.equalsIgnoreCase("TOGGLE_SHOW")) {
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

         int bannerId = ParamUtil.getInteger(request, "bannerId");
         String status = ParamUtil.getString(request, "status");

         try {
            Banner banner = BannerLocalServiceUtil.getBanner(bannerId);

            if (Validator.isNotNull(banner)) {
               if (status.equalsIgnoreCase("true")) {
                  banner.setIsShow(false);
               } else {
                  banner.setIsShow(true);
               }

               banner.setModifiedDate(new Date());
               banner.setModifiedBy(themeDisplay.getUser().getFullName());
               BannerLocalServiceUtil.updateBanner(banner);
               jsonObject = SUCCESS("Data tersimpan", "");
            }

         } catch (Exception e) {
            e.printStackTrace();
         }
         writer.print(jsonObject.toJSONString());

      } else if (state.equalsIgnoreCase(DELETE)) {
         JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

         int bannerId = ParamUtil.getInteger(request, "bannerId");

         try {
            Banner banner = BannerLocalServiceUtil.getBanner(bannerId);

            banner.setModifiedDate(new Date());
            banner.setModifiedBy(themeDisplay.getUser().getFullName());
            banner.setIsShow(false);
            banner.setRowStatus(false);
            BannerLocalServiceUtil.updateBanner(banner);
            jsonObject = SUCCESS("Data tersimpan", "");

         } catch (Exception e) {
            e.printStackTrace();
         }
         writer.print(jsonObject.toJSONString());

      }
      super.serveResource(request, response);
   }
}