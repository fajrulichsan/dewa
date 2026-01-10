package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.GroupDealerEnum;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.DEALERWEB,
            "mvc.command.name=/dealer-action"
      },
      service = MVCResourceCommand.class
)
public class DealerActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DealerActionCommand.class);
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

         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else if (crudType.equalsIgnoreCase(DELETE)) {
            jsonObject = delete(entryId);
         } else {
            String dealerCode = ParamUtil.getString(uploadPortletRequest, "dealerCode");
            String dealerName = ParamUtil.getString(uploadPortletRequest, "dealerName");
            String hoCode = ParamUtil.getString(uploadPortletRequest, "hoCode");
            int groupDealerId = ParamUtil.getInteger(uploadPortletRequest, "groupDealerId");
            int cabangId = ParamUtil.getInteger(uploadPortletRequest, "cabangId");
            int wilayahId = ParamUtil.getInteger(uploadPortletRequest, "wilayahId");

            if (!InputValidationUtils.isBasicCharacter(dealerName)) {
               throw new SystemException("Nama dealer hanya boleh diisi dengan karakter .,/()@&_-");
            }

            if (!InputValidationUtils.isNumberOnly(dealerCode)) {
               throw new SystemException("Mohon gunakan format kode dealer yang sesuai");
            }

            if (!InputValidationUtils.isNumberOnly(hoCode)) {
               throw new SystemException("Mohon gunakan format kode HO dealer yang sesuai");
            }

//			log.info("entryId : " + entryId);
//			log.info("dealerCode : " + dealerCode);
//			log.info("dealerName : " + dealerName);
//			log.info("hoCode : " + hoCode);
//			log.info("groupDealerId : " + groupDealerId);
//			log.info("cabangId : " + cabangId);
//			log.info("wilayahId : " + wilayahId);
//			log.info("crudType : " + crudType);

            Date date = new Date();
            Dealer dealer;
            if (crudType.equalsIgnoreCase(CREATE)) {
               dealer = DealerLocalServiceUtil.createDealer(-1);
            } else {
               dealer = DealerLocalServiceUtil.getDealer(entryId);
            }
            dealer.setCabangId(cabangId);
            dealer.setWilayahId(wilayahId);

            if (crudType.equalsIgnoreCase(CREATE)) {
               jsonObject = create(dealer, date, dealerCode, dealerName, groupDealerId, hoCode);
            } else if (crudType.equalsIgnoreCase(UPDATE)) {
               jsonObject = update(dealer, date, dealerCode, dealerName, groupDealerId, hoCode);
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e.getMessage());
            jsonObject = ERROR(400, "Bad request!");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(Dealer dealer, Date date, String dealerCode, String dealerName, Integer groupDealerId, String hoCode) {
      try {

         if (groupDealerId == GroupDealerEnum.HO_DEALER.ordinal()) {
            if (isExistNameDealer(dealerName, "")) {
               return WARNING("Anda tidak dapat menyimpan Nama Dealer " + dealerName + ", karena ada Nama Dealer yang sudah ada.");
            }

            if (isExistHODealerCode(hoCode, 0)) {
               return WARNING("Anda tidak dapat menyimpan Kode HO Dealer " + hoCode + ", karena ada Kode HO Dealer yang sudah ada.");
            }
         } else {
            if (isExistDealerCode(dealerCode, 0)) {
               return WARNING("Anda tidak dapat menyimpan Kode Dealer " + dealerCode + ", karena ada Kode Dealer yang sudah ada.");
            }
         }

         if (groupDealerId == GroupDealerEnum.HO_DEALER.ordinal()) {
            dealer.setGroupDealer(GroupDealerEnum.HO_DEALER.ordinal());
            dealer.setKodeHo(hoCode);
         } else {
            dealer.setGroupDealer(GroupDealerEnum.DEALER_CABANG.ordinal());
            String HOCode = DealerLocalServiceUtil.getDealer(Integer.parseInt(hoCode)).getKodeHo();
            dealer.setKodeHo(HOCode);
         }

         dealer.setCode(dealerCode);
         dealer.setName(dealerName);
         dealer.setCreatedDate(date);
         dealer.setCreatedBy(user.getScreenName());
         dealer.setModifiedDate(date);
         dealer.setModifiedBy(user.getScreenName());
         dealer.setRowStatus(true);

         DealerLocalServiceUtil.addDealer(dealer);
         return SUCCESS("Data tersimpan", String.valueOf(dealer.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(Dealer dealer, Date date, String dealerCode, String dealerName, Integer groupDealerId, String hoCode) {
      try {
         if (groupDealerId == GroupDealerEnum.HO_DEALER.ordinal()) {
            if (!dealer.getName().equalsIgnoreCase(dealerName)) {
               if (isExistNameDealer(dealerName, dealer.getKodeHo())) {
                  return WARNING("Anda tidak dapat menyimpan Nama Dealer " + dealerName + ", karena ada Nama Dealer yang sudah ada.");
               }
            }

            if (!dealer.getKodeHo().equalsIgnoreCase(hoCode)) {
               if (isExistHODealerCode(hoCode, dealer.getId())) {
                  return WARNING("Anda tidak dapat menyimpan Kode HO Dealer " + hoCode + ", karena ada Kode HO Dealer yang sudah ada.");
               }
            }
         } else {
            if (!dealer.getCode().equalsIgnoreCase(dealerCode)) {
               if (isExistDealerCode(dealerCode, dealer.getId())) {
                  return WARNING("Anda tidak dapat menyimpan Kode Dealer " + dealerCode + ", karena ada Kode Dealer yang sudah ada.");
               }
            }
         }

         if (groupDealerId == GroupDealerEnum.HO_DEALER.ordinal()) {
            dealer.setGroupDealer(GroupDealerEnum.HO_DEALER.ordinal());
            dealer.setKodeHo(hoCode);
         } else {
            dealer.setGroupDealer(GroupDealerEnum.DEALER_CABANG.ordinal());
            String HOCode = DealerLocalServiceUtil.getDealer(Integer.parseInt(hoCode)).getKodeHo();
            dealer.setKodeHo(HOCode);
         }

         dealer.setCode(dealerCode);
         dealer.setName(dealerName);
         dealer.setModifiedDate(date);
         dealer.setModifiedBy(user.getScreenName());

         DealerLocalServiceUtil.updateDealer(dealer);
         return SUCCESS("Data terupdate.", String.valueOf(dealer.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(int entryId) {
      try {
         Dealer dealer = DealerLocalServiceUtil.getDealer(entryId);
         dealer.setRowStatus(!dealer.getRowStatus());
         dealer.setModifiedBy(user.getScreenName());
         dealer.setModifiedDate(new Date());
         DealerLocalServiceUtil.updateDealer(dealer);
         return DELETED_SUCCESS("Data terhapus.", "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         return DELETED_NOT_SUCCESS("Data tidak terhapus.", "");
      }
   }

   private boolean isExistNameDealer(String name, String hoDealerCode) {
      LOG.info("=======================");
      LOG.info("hoDealerCode : " + hoDealerCode);
      LOG.info(!hoDealerCode.isEmpty());
      LOG.info("name : " + name);
      List<Dealer> dealers = new ArrayList<>();
      DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));

      if (!hoDealerCode.isEmpty()) {
         query.add(RestrictionsFactoryUtil.ne("KodeHo", hoDealerCode));
         dealers = DealerLocalServiceUtil.dynamicQuery(query);
      } else {
         dealers = DealerLocalServiceUtil.dynamicQuery(query);
      }
      LOG.info(dealers.size());
      LOG.info(dealers.toString());
      return !dealers.isEmpty();
   }

   private boolean isExistHODealerCode(String hoDealerCode, Integer id) {
      LOG.info("=======================");
      LOG.info("hoDealerCode : " + hoDealerCode);
      LOG.info(!id.equals(null));
      LOG.info("name : " + id);

      List<Dealer> dealers = new ArrayList<>();
      DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("KodeHo", hoDealerCode));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      if (!id.equals(null)) {
         query.add(RestrictionsFactoryUtil.ne("Id", id));
         dealers = DealerLocalServiceUtil.dynamicQuery(query);
      } else {
         dealers = DealerLocalServiceUtil.dynamicQuery(query);
      }
      return !dealers.isEmpty();
   }

   private boolean isExistDealerCode(String dealerCode, Integer id) {
      LOG.info("=======================");
      LOG.info("dealerCode : " + dealerCode);
      LOG.info(!id.equals(null));
      LOG.info("name : " + id);

      List<Dealer> dealers = new ArrayList<>();
      DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Code", dealerCode));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      if (!id.equals(null)) {
         query.add(RestrictionsFactoryUtil.ne("Id", id));
         dealers = DealerLocalServiceUtil.dynamicQuery(query);
      } else {
         dealers = DealerLocalServiceUtil.dynamicQuery(query);
      }
      return !dealers.isEmpty();
   }

}
