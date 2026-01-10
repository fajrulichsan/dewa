package com.astra.dewa.web.command.action;

import com.astra.dewa.model.SkIris;
import com.astra.dewa.service.SkIrisLocalServiceUtil;
import com.astra.dewa.utils.*;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS,
      "mvc.command.name=/sk-iris-action"
   },
   service = MVCResourceCommand.class
)
public class SkIrisActionCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(SkIrisActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private ThemeDisplay themeDisplay;
   private User user;
   private int entryId;
   private String fileName;
   private int dealerId;
   private String message;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      this.themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      if (isRequestContainsXSS) {
         jsonObject = ERROR("Your request contains XSS payload.");
      } else {
         entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         int tahun = ParamUtil.getInteger(uploadPortletRequest, "tahun");
         String periode = ParamUtil.getString(uploadPortletRequest, "periode");
         String kategori = ParamUtil.getString(uploadPortletRequest, "fileKategori");
         long fileId = ParamUtil.getLong(uploadPortletRequest, "skIrisFileId");
         long wilayahId = ParamUtil.getLong(uploadPortletRequest, "wilayahId");
         String wilayahName = ParamUtil.getString(uploadPortletRequest, "wilayahName");
         dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
         fileName = ParamUtil.getString(uploadPortletRequest, "skIrisFileName");
         String filePath = ParamUtil.getString(uploadPortletRequest, "skIrisFilePath");
         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

         Date date = new Date();
         SkIris skIris = null;
         boolean isFileExist = isExist(entryId,wilayahId, tahun, periode, kategori,dealerId);
         if (crudType.equalsIgnoreCase("create")) {
            skIris = SkIrisLocalServiceUtil.createSkIris(entryId);
         } else {
            skIris = SkIrisLocalServiceUtil.getSkIris(entryId);
         }

         if (crudType.equalsIgnoreCase("delete")) {
            skIris.setFileName(fileName);
         } else {
            String newFileName = "SK IRIS "+ kategori +"-" + wilayahName + "-" + periode + "-" + tahun + "." + FileUtil.getExtensionByStringHandling(fileName).get();
            skIris.setFileName(newFileName);
         }

         skIris.setTahun(tahun);
         skIris.setPeriode(periode);
         skIris.setWilayahId(wilayahId);
         skIris.setDealerId(dealerId);
         skIris.setKategori(kategori);
         skIris.setFileId(fileId);
         skIris.setFilePath(filePath);

         if (crudType.equalsIgnoreCase("delete")) {
            jsonObject = delete();
         } else if (crudType.equalsIgnoreCase("create")) {
            jsonObject = create(skIris, date, isFileExist,fileId, entryId, fileName);
         } else if (crudType.equalsIgnoreCase("update")) {
            jsonObject = update(skIris, date, isFileExist,entryId, fileName);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(SkIris skIris, Date date, boolean isFileExist, long fileId, int entryId,String fileName) {
      try {
         skIris.setRowStatus(true);
         skIris.setCreatedDate(date);
         skIris.setCreatedBy(user.getScreenName());
         skIris.setModifiedDate(date);
         skIris.setModifiedBy(user.getScreenName());
         if(isFileExist){
            LogUtil.logStatus(0,entryId, CRUDActionKeys.CREATE,DewaWebKeys.SK_IRIS_MENU,dealerId,fileName,message);
            return WARNING("You are not allowed to upload more than one file within a single region, year, periode, dealer and same category.");
         }

         SkIrisLocalServiceUtil.updateSkIris(skIris);
//         FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, DewaWebKeys.SK_IRIS_MENU);
         LogUtil.logStatus(1,entryId,CRUDActionKeys.CREATE,DewaWebKeys.SK_IRIS_MENU,dealerId,fileName,message);
         return SUCCESS("Data tersimpan", String.valueOf(skIris.getId()));

      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(SkIris skIris, Date date,boolean isFileExist,int entryId,String fileName) {
      try {
         skIris.setModifiedDate(date);
         skIris.setModifiedBy(user.getScreenName());
         if(isFileExist){
            LogUtil.logStatus(0,entryId,CRUDActionKeys.UPDATE,DewaWebKeys.SK_IRIS_MENU,dealerId,fileName,message);
            return WARNING("You are not allowed to upload more than one file within a single region, year, periode, dealer and same category.");
         }
         SkIrisLocalServiceUtil.updateSkIris(skIris);
         LogUtil.logStatus(1,entryId,CRUDActionKeys.UPDATE,DewaWebKeys.SK_IRIS_MENU,dealerId,fileName,message);
         return SUCCESS("Data terupdate.", String.valueOf(skIris.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete() {
      try {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         SkIris skIris = SkIrisLocalServiceUtil.getSkIris(entryId);
         skIris.setRowStatus(false);
         SkIrisLocalServiceUtil.updateSkIris(skIris);
         LogUtil.logStatus(1,entryId,CRUDActionKeys.DELETE,DewaWebKeys.SK_IRIS_MENU,dealerId,fileName,message);
         return SUCCESS("Data terupdate.", String.valueOf(entryId));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(int entryId, long wilayah, Integer tahun, String periode, String kategori ,int dealerId) {
      DynamicQuery query = SkIrisLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("WilayahId",wilayah));
      query.add(RestrictionsFactoryUtil.eq("Tahun", tahun));
      query.add(RestrictionsFactoryUtil.eq("Periode", periode));
      query.add(RestrictionsFactoryUtil.eq("Kategori", kategori));
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<SkIris> skIriss = SkIrisLocalServiceUtil.dynamicQuery(query);
      if(!skIriss.isEmpty()){
         return !(skIriss.get(0).getId() == entryId);
      }
      return false;
   }

}
