package com.astra.dewa.web.command.render.training.materi.util;

import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/topik-materi-action"
   },
   service = MVCResourceCommand.class
)
public class TopikMateriActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TopikMateriActionCommand.class);
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      this.user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

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
            String topikMateriId = ParamUtil.getString(uploadPortletRequest, "topikMateriId");
            String topikMateriName = ParamUtil.getString(uploadPortletRequest, "topikMateriName");

            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
            TopikMateri topikMateri = null;

            if (!InputValidationUtils.isBasicCharacter(topikMateriName)) {
               throw new SystemException("Mohon tidak menggunakan karakter selain selain .,/()@&_-");
            }

            switch (crudType) {
               case CREATE:
                  topikMateri = TopikMateriLocalServiceUtil.createTopikMateri(0);
                  topikMateri.setName(topikMateriName);
                  jsonObject = create(topikMateri);
                  break;
               case UPDATE:
                  topikMateri = TopikMateriLocalServiceUtil.getTopikMateri(Integer.parseInt(topikMateriId));
                  topikMateri.setName(topikMateriName);
                  jsonObject = update(topikMateri);
                  break;
               case DELETE:
                  topikMateri = TopikMateriLocalServiceUtil.getTopikMateri(Integer.parseInt(topikMateriId));
                  jsonObject = delete(topikMateri);
                  break;
               default:
                  throw new SystemException("Bad request");
            }

            /*
            if (crudType.equalsIgnoreCase("create")) {
               topikMateri = TopikMateriLocalServiceUtil.createTopikMateri(0);
            } else {
               topikMateri = TopikMateriLocalServiceUtil.getTopikMateri(Integer.parseInt(topikMateriId));
            }
            topikMateri.setName(topikMateriName);

            if (crudType.equalsIgnoreCase("delete")) {
               jsonObject = delete();
            } else if (crudType.equalsIgnoreCase("create")) {
               jsonObject = create(topikMateri);
            } else if (crudType.equalsIgnoreCase("update")) {
               jsonObject = update(topikMateri);
            }
             */
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = WARNING("Internal Server Error");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(TopikMateri topikMateri) {
      try {
         if (isExist(topikMateri.getName())) {
            return JSONResponseFormatUtil.ERROR("Data already exist.");
         }
         Date date = new Date();
         topikMateri.setRowStatus(true);
         topikMateri.setCreatedDate(date);
         topikMateri.setCreatedBy(user.getScreenName());
         topikMateri.setModifiedDate(date);
         topikMateri.setModifiedBy(user.getScreenName());
         TopikMateri tm = TopikMateriLocalServiceUtil.addTopikMateri(topikMateri);
         JSONObject dto = JSONFactoryUtil.createJSONObject()
            .put("id", tm.getId())
            .put("text", tm.getName());
         return SUCCESS("Data tersimpan", String.valueOf(topikMateri.getId()), dto);
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(TopikMateri topikMateri) {
      try {
         Date date = new Date();
         topikMateri.setModifiedBy(user.getScreenName());
         topikMateri.setModifiedDate(date);
         TopikMateriLocalServiceUtil.updateTopikMateri(topikMateri);
         return SUCCESS("Data terupdate.", String.valueOf(topikMateri.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(TopikMateri topikMateri) {
      try {
//         String topikMateriId = ParamUtil.getString(uploadPortletRequest, "topikMateriId");
//         TopikMateri topikMateri = TopikMateriLocalServiceUtil.getTopikMateri(Integer.parseInt(topikMateriId));
         topikMateri.setRowStatus(false);
         topikMateri.setModifiedBy(user.getScreenName());
         topikMateri.setModifiedDate(new Date());
         TopikMateriLocalServiceUtil.updateTopikMateri(topikMateri);
         return SUCCESS("Data terupdate.", String.valueOf(topikMateri.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(String name) {
      DynamicQuery query = TopikMateriLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      List<TopikMateri> topikMateris = TopikMateriLocalServiceUtil.dynamicQuery(query);
      return topikMateris.size() > 0;
   }
}