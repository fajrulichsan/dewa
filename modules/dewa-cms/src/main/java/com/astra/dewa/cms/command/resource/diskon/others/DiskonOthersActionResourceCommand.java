package com.astra.dewa.cms.command.resource.diskon.others;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DiskonOther;
import com.astra.dewa.service.DiskonOtherLocalServiceUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.DealinkCustomValidationException;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.utils.KuartalUtils;
import com.astra.dewa.utils.TahunUtils;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + CmsKeys.CMS_PATH + CmsKeys.REALISASI_DISKON_PATH + "/others/action"
        },
        service = MVCResourceCommand.class
)
public class DiskonOthersActionResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        // response
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        // XSS payload
        boolean isRequestContainsXSS = false;
        Enumeration<String> attributes = resourceRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = resourceRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }
        attributes = uploadPortletRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = uploadPortletRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }

        try {
            // csrf
            AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());
            // xss
            if (isRequestContainsXSS) {
                throw new DealinkCustomValidationException("Oops! Something went wrong.");
            }
            // get user
            User user = themeDisplay.getUser();
            // param
            String cmd = ParamUtil.getString(uploadPortletRequest, Constants.CMD, "");
            int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId", 0);
            DiskonOther diskonOther;

            switch (cmd) {
                case Constants.ADD:
                    diskonOther = DiskonOtherLocalServiceUtil.createDiskonOther(entryId);
                    setData(diskonOther, themeDisplay, resourceRequest);
                    jsonObject = create(diskonOther, user);
                    break;
                case Constants.UPDATE:
                    diskonOther = DiskonOtherLocalServiceUtil.getDiskonOther(entryId);
                    setData(diskonOther, themeDisplay, resourceRequest);
                    jsonObject = update(diskonOther, user);
                    break;
                case Constants.DELETE:
                    diskonOther = DiskonOtherLocalServiceUtil.getDiskonOther(entryId);
                    jsonObject = delete(diskonOther, user);
                    break;
                default:
                    throw new DealinkCustomValidationException("Bad request! Invalid action");
            }
        } catch (Exception e) {
            if (e instanceof PrincipalException) {
                _log.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
                _log.error("Invalid CSRF token! Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
                jsonObject = JSONResponseFormatUtil.getCommonFormat(0, "error", "Unauthorized request! Please refresh the page");
            } else if (e instanceof DealinkCustomValidationException) {
                jsonObject = JSONResponseFormatUtil.getCommonFormat(0, "error", e.getMessage());
            } else {
                jsonObject = JSONResponseFormatUtil.getCommonFormat(0, "error", "Internal server error");
            }
            _log.error(e.getMessage(), e);
        }

        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }

    private void setData(DiskonOther diskonOther, ThemeDisplay themeDisplay, ResourceRequest resourceRequest) throws DealinkCustomValidationException, PortalException {
        int dealerId = ParamUtil.getInteger(resourceRequest, "dealerId");
        int tahun = ParamUtil.getInteger(resourceRequest, "tahun");
        int kuartalId = ParamUtil.getInteger(resourceRequest, "kuartalId");
        long fileId = ParamUtil.getLong(resourceRequest, "diskonOthersFileId");

        // dealer
        Dealer dealer = DealerUtils.getDealer(dealerId, true);
        if (null == dealer) {
            throw new DealinkCustomValidationException("Dealer tidak terdaftar");
        }
        // kuartal
        String kuartalName = KuartalUtils.getQuarterName(kuartalId);
        if (null == kuartalName) {
            throw new DealinkCustomValidationException("Kuartal tidak valid");
        }
        // tahun
        List<Integer> years = TahunUtils.yearList();
        if (!years.contains(tahun)) {
            throw new DealinkCustomValidationException("Tahun tidak valid");
        }
        // file entry
        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
        String fileName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
        String filePath = themeDisplay.getPortalURL() +
                themeDisplay.getPathContext() +
                "/documents/" +
                themeDisplay.getScopeGroupId() +
                "/" + fileEntry.getFolderId() +
                "/" + fileEntry.getFileName();
        // noinspection
        fileName = CmsKeys.DISKON_OTHERS_MENU + "-" + kuartalName + "-" + tahun + "-" + dealer.getCode() + "." + FileUtil.getExtensionByStringHandling(fileName).get();
        // set data
        diskonOther.setFileId(fileId);
        diskonOther.setFileName(fileName);
        diskonOther.setFilePath(filePath);
        diskonOther.setDealerId(dealerId);
        diskonOther.setTahun(tahun);
        diskonOther.setKuartalId(kuartalId);
        diskonOther.setKeterangan("-");
        diskonOther.setRowStatus(true);
    }

    private JSONObject create(DiskonOther diskonOther, User user) {
        try {
            diskonOther.setCreatedBy(user.getScreenName());
            diskonOther.setCreatedDate(new Date());
            diskonOther.setModifiedBy(user.getScreenName());
            diskonOther.setModifiedDate(new Date());
            DiskonOtherLocalServiceUtil.updateDiskonOther(diskonOther);
            return JSONResponseFormatUtil.getCommonFormat(1, "success", "Data berhasil ditambahkan!");
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return JSONResponseFormatUtil.getCommonFormat(0, "error", "Gagal menambahkan data!");
        }
    }

    private JSONObject update(DiskonOther diskonOther, User user) {
        try {
            diskonOther.setModifiedBy(user.getScreenName());
            diskonOther.setModifiedDate(new Date());
            DiskonOtherLocalServiceUtil.updateDiskonOther(diskonOther);
            return JSONResponseFormatUtil.getCommonFormat(1, "success", "Data berhasil diubah!");
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return JSONResponseFormatUtil.getCommonFormat(0, "error", "Gagal memperbarui data!");
        }
    }

    private JSONObject delete(DiskonOther diskonOther, User user) {
        try {
            diskonOther.setModifiedBy(user.getScreenName());
            diskonOther.setModifiedDate(new Date());
            diskonOther.setRowStatus(false);
            DiskonOtherLocalServiceUtil.updateDiskonOther(diskonOther);
            return JSONResponseFormatUtil.getCommonFormat(1, "success", "Data berhasil dihapus!");
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return JSONResponseFormatUtil.getCommonFormat(0, "error", "Gagal menghapus data!");
        }
    }
}
