package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Wilayah;
import com.astra.dewa.service.WilayahLocalServiceUtil;
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
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.WILAYAHWEB,
            "mvc.command.name=/wilayah-action"
      },
      service = MVCResourceCommand.class
)
public class WilayahActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(WilayahActionCommand.class);
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

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
            String wilayahName = ParamUtil.getString(uploadPortletRequest, "wilayahName");

            if (!InputValidationUtils.isBasicCharacter(wilayahName)) {
               throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_-");
            }
            if (wilayahName.length() < 3 || wilayahName.length() > 50) {
               throw new SystemException("Nama wilayah diisi dengan 3 sampai 50 karakter");
            }

            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

            Date date = new Date();
            Wilayah wilayah = null;

            switch (crudType) {
               case CREATE:
                  wilayah = WilayahLocalServiceUtil.createWilayah(-1);
                  wilayah.setName(wilayahName);
                  jsonObject = create(wilayah, date);
                  break;
               case UPDATE:
                  wilayah = WilayahLocalServiceUtil.getWilayah(entryId);
                  wilayah.setName(wilayahName);
                  jsonObject = update(wilayah, date);
                  break;
               case DELETE:
                  wilayah = WilayahLocalServiceUtil.getWilayah(entryId);
                  jsonObject = delete(wilayah, date);
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

   private JSONObject create(Wilayah wilayah, Date date) {
      try {
         if (isExist(wilayah.getName())) {
            return WARNING("Anda tidak dapat menyimpan " + wilayah.getName() + ", karena nama wilayah sudah ada.");
         }
         wilayah.setCreatedDate(date);
         wilayah.setCreatedBy(user.getScreenName());
         wilayah.setModifiedDate(date);
         wilayah.setModifiedBy(user.getScreenName());
         wilayah.setRowStatus(true);
         WilayahLocalServiceUtil.addWilayah(wilayah);
         return SUCCESS("Data tersimpan", String.valueOf(wilayah.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(Wilayah wilayah, Date date) {
      try {
         if (isExist(wilayah.getName())) {
            return WARNING("Anda tidak dapat menyimpan " + wilayah.getName() + ", karena nama wilayah sudah ada.");
         }
         wilayah.setName(wilayah.getName());
         wilayah.setModifiedDate(date);
         wilayah.setModifiedBy(user.getScreenName());
         WilayahLocalServiceUtil.updateWilayah(wilayah);
         return SUCCESS("Data terupdate.", String.valueOf(wilayah.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(Wilayah wilayah, Date date) {
      try {
         wilayah.setRowStatus(wilayah.getRowStatus());
         wilayah.setModifiedBy(user.getScreenName());
         wilayah.setModifiedDate(date);
         WilayahLocalServiceUtil.updateWilayah(wilayah);
         return SUCCESS("Data berhasil dihapus", "");
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(String name) {
      DynamicQuery query = WilayahLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<Wilayah> wilayahs = WilayahLocalServiceUtil.dynamicQuery(query);
      return !wilayahs.isEmpty();
   }
}