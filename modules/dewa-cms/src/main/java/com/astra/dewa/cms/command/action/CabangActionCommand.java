package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
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

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + CmsPortletKeys.CABANGWEB,
      "mvc.command.name=/cabang-action"
   },
   service = MVCResourceCommand.class
)
public class CabangActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(CabangActionCommand.class);
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            String cabangName = ParamUtil.getString(uploadPortletRequest, "cabangName");

            if (!InputValidationUtils.isBasicCharacter(cabangName)) {
               throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_-");
            }
            if (cabangName.length() < 3 || cabangName.length() > 50) {
               throw new SystemException("Nama cabang diisi dengan 3 sampai 50 karakter");
            }

            Cabang cabang = null;
            Date date = new Date();
            switch (crudType) {
               case CREATE:
                  cabang = CabangLocalServiceUtil.createCabang(-1);
                  cabang.setName(cabangName);
                  jsonObject = create(cabang, date);
                  break;
               case UPDATE:
                  cabang = CabangLocalServiceUtil.getCabang(entryId);
                  cabang.setName(cabangName);
                  jsonObject = update(cabang, date);
                  break;
               case DELETE:
                  cabang = CabangLocalServiceUtil.getCabang(entryId);
                  jsonObject = delete(cabang);
                  break;
               default:
                  jsonObject = WARNING("Bad request!");
                  break;
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = WARNING("Bad request");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(Cabang cabang, Date date) {
      try {
         if (isExist(cabang.getName())) {
            return WARNING("Anda tidak dapat menyimpan " + cabang.getName() + ", karena nama cabang sudah ada.");
         }
         cabang.setCreatedDate(date);
         cabang.setCreatedBy(user.getScreenName());
         cabang.setModifiedDate(date);
         cabang.setModifiedBy(user.getScreenName());
         cabang.setRowStatus(true);

         CabangLocalServiceUtil.addCabang(cabang);
         return SUCCESS("Data tersimpan", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING(e.getMessage());
      }
   }

   private JSONObject update(Cabang cabang, Date date) {
      try {
         if (isExist(cabang.getName())) {
            return WARNING("Anda tidak dapat menyimpan " + cabang.getName() + ", karena nama cabang sudah ada.");
         }

         cabang.setModifiedDate(date);
         cabang.setModifiedBy(user.getScreenName());

         CabangLocalServiceUtil.updateCabang(cabang);
         return SUCCESS("Data terupdate", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING(e.getMessage());
      }
   }

   private JSONObject delete(Cabang cabang) {
      try {
         cabang.setRowStatus(cabang.getRowStatus());
         cabang.setModifiedBy(user.getScreenName());
         cabang.setModifiedDate(new Date());
         CabangLocalServiceUtil.updateCabang(cabang);
         return SUCCESS("Data berhasil dihapus", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return WARNING(e.getMessage());
      }
   }

   private boolean isExist(String name) {
      DynamicQuery query = CabangLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<Cabang> cabangs = CabangLocalServiceUtil.dynamicQuery(query);
      return !cabangs.isEmpty();
   }
}