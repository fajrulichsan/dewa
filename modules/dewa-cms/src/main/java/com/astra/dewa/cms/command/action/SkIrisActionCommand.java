package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CmsPortletKeys.SKIRISWEB,
		"mvc.command.name=/skiris-action"
	},
	service = MVCResourceCommand.class
)
public class SkIrisActionCommand extends BaseMVCResourceCommand {

	private final Log log = LogFactoryUtil.getLog(SkIrisActionCommand.class);
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
				log.warn(value +" contains XSS payload");
				isRequestContainsXSS = true;
				break;
			}
		}

		attributes = uploadPortletRequest.getParameterNames();
		while (attributes.hasMoreElements()) {
			String param = attributes.nextElement();
			String value = uploadPortletRequest.getParameter(param);
			if (FilterXSS.checkXSS(value)) {
				log.warn(value +" contains XSS payload");
				isRequestContainsXSS = true;
				break;
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		if (isRequestContainsXSS) {
			jsonObject = ERROR("Your request contains XSS payload.");
		} else {
			int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
			String dealerCode = ParamUtil.getString(uploadPortletRequest, "dealerCode");
			String dealerName = ParamUtil.getString(uploadPortletRequest, "dealerName");
			// String roleDealerId = ParamUtil.getString(uploadPortletRequest, "roleDealerId");
			// String roleDealerName = ParamUtil.getString(uploadPortletRequest, "roleDealerName");
			int cabangId = ParamUtil.getInteger(uploadPortletRequest, "cabangId");
			int wilayahId = ParamUtil.getInteger(uploadPortletRequest, "wilayahId");

			String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
			Date date = new Date();
			Dealer dealer = null;
			if (crudType.equalsIgnoreCase("create")) {
				dealer = DealerLocalServiceUtil.createDealer(-1);
			} else {
				dealer = DealerLocalServiceUtil.getDealer(entryId);
			}
			dealer.setCode(dealerCode);
			dealer.setName(dealerName);
//			dealer.setHoCode(roleDealerId);
//			dealer.setHoName(RoleDealerUtils.getRoleDealerName(roleDealerName));
			dealer.setCabangId(cabangId);
			dealer.setWilayahId(wilayahId);

			if (crudType.equalsIgnoreCase("delete")) {
				jsonObject = delete(entryId);
			} else if (crudType.equalsIgnoreCase("create")) {
				jsonObject = create(dealer, date);
			} else if (crudType.equalsIgnoreCase("update")) {
				jsonObject = update(dealer, date);
			}
		}
		ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
	}

	private JSONObject create(Dealer dealer, Date date) {
		try {
			if(isExist(dealer.getName())) {
				return WARNING("Anda tidak dapat menyimpan " + dealer.getName() + ", karena ada dokumen yang sudah ada.");
			}
			dealer.setCreatedDate(date);
			dealer.setCreatedBy(user.getScreenName());
			dealer.setModifiedDate(date);
			dealer.setModifiedBy(user.getScreenName());
			dealer.setRowStatus(true);

			DealerLocalServiceUtil.addDealer(dealer);
			return SUCCESS("Data tersimpan", String.valueOf(dealer.getId()));
		} catch (Exception e) {
			log.error(e);
			return NOT_SUCCESS(e.getMessage());
		}
	}

	private JSONObject update(Dealer dealer, Date date) {
		try {
			dealer.setModifiedDate(date);
			dealer.setModifiedBy(user.getScreenName());

			DealerLocalServiceUtil.updateDealer(dealer);
			return SUCCESS("Data terupdate.", String.valueOf(dealer.getId()));
		} catch (Exception e) {
			log.error(e);
			return NOT_SUCCESS(e.getMessage());
		}
	}

	private JSONObject delete(int entryId) {
		try {
			Dealer dealer = DealerLocalServiceUtil.getDealer(entryId);
			dealer.setRowStatus(dealer.getRowStatus());
			DealerLocalServiceUtil.updateDealer(dealer);
			return SUCCESS("Data terupdate.", String.valueOf(entryId));
		} catch (Exception e) {
			log.error(e);
			return NOT_SUCCESS(e.getMessage());
		}
	}

	private boolean isExist(String name){
		DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
		query.add(RestrictionsFactoryUtil.eq("Name", name));
		List<Dealer> dealers =  DealerLocalServiceUtil.dynamicQuery(query);
		return dealers.size() > 0;
	}

}
