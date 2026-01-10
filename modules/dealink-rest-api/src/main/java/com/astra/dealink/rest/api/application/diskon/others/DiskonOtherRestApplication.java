package com.astra.dealink.rest.api.application.diskon.others;

import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.FolderUtil;
import com.astra.dealink.rest.api.util.ResponseUtil;
import com.astra.dealink.rest.api.util.TokenUtil;
import com.astra.dewa.model.DiskonOther;
import com.astra.dewa.service.DiskonOtherLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.KuartalUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author psmahmad1402
 */
@ApplicationPath(DewaRestApiKeys.DISKON_OTHERS_API_PATH)
@Component(
        immediate = true,
        service = Application.class
)
public class DiskonOtherRestApplication extends Application {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }

    @POST
    @Consumes(ContentTypes.MULTIPART_FORM_DATA)
    @Produces(ContentTypes.APPLICATION_JSON)
    public Response postDiskonOthers(@Context HttpServletRequest httpServletRequest) throws PortalException {
        Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), DewaRestApiKeys.NAME_SITE);
        User user = PortalUtil.getUser(httpServletRequest);
        UploadServletRequest uploadServletRequest = PortalUtil.getUploadServletRequest(httpServletRequest);

        int acknowledge = 0;
        String dealerCode = StringPool.BLANK;

        // XSS payload
        boolean isRequestContainsXSS = false;
        Enumeration<String> attributes = httpServletRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = httpServletRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }

        attributes = uploadServletRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = uploadServletRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }

        if (isRequestContainsXSS) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Oops! Something went wrong.", Response.Status.FORBIDDEN);
        }

        // token validation
        String token = httpServletRequest.getParameter("token");
        boolean isTokenValid = TokenUtil.validateToken(token);
        if (!isTokenValid) {
            return ResponseUtil.UNAUTHORIZED(acknowledge, "", "Invalid token / token is expired!");
        }

        // Request attribute params
        dealerCode = httpServletRequest.getParameter("dealerCode").trim();
        if (Validator.isNull(dealerCode)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Tidak terdapat kode dealer!", Response.Status.BAD_REQUEST);
        }

        String kuartal = httpServletRequest.getParameter("kuartal").trim();
        if (Validator.isNull(kuartal)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Tidak terdapat kuartal!", Response.Status.BAD_REQUEST);
        }

        String tahun = httpServletRequest.getParameter("tahun").trim();
        if (Validator.isNull(tahun)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Tidak terdapat tahun!", Response.Status.BAD_REQUEST);
        } else if (!Validator.isNumber(tahun)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Tahun harus berupa angka!", Response.Status.BAD_REQUEST);
        }

        // Request file params
        File file = null;
        String fileName = null;
        long fileSize = 0;
        String mimeType = null;
        try {
            file = uploadServletRequest.getFile("file");
            fileName = uploadServletRequest.getFileName("file");
            fileSize = uploadServletRequest.getSize("file");
            mimeType = uploadServletRequest.getContentType("file");
        } catch (NullPointerException e) {
            _log.error(e.getMessage(), e);
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Unknown file source!", Response.Status.BAD_REQUEST);
        }

        // File validation
        String fileExt = FileUtil.getExtension(fileName);

        List<String> allowedExtensions = Arrays.asList(DewaRestApiKeys.EXT_XLSX, DewaRestApiKeys.EXT_XLS);
        List<String> allowedTypes = Arrays.asList("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel");

        if (!allowedExtensions.contains(fileExt)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Ekstensi file harus .xls atau .xlsx!", Response.Status.BAD_REQUEST);
        }
        if (!allowedTypes.contains(mimeType)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Ekstensi file harus .xls atau .xlsx!", Response.Status.BAD_REQUEST);
        }
        if (fileSize > 1048576) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Ukuran file melebihi 1MB!", Response.Status.BAD_REQUEST);
        }

        // Dealer validation
        if (!DealerUtil.isDealerExist(dealerCode)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Dealer dengan kode tersebut belum terdaftar!", Response.Status.BAD_REQUEST);
        }

        // Kuartal validation
        int kuartalId = KuartalUtils.getQuarterId(kuartal);
        if (kuartalId == -1) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Kuartal tidak valid!", Response.Status.BAD_REQUEST);
        }

        String newFileName = DewaRestApiKeys.MENU_DISKON_OTHERS +
                StringPool.DASH +
                kuartal +
                StringPool.DASH +
                tahun +
                StringPool.DASH +
                dealerCode +
                "." +
                fileExt;

        _log.info("uploading file: " + newFileName);

        // Data insertion
        HashMap<String, String> payload = new HashMap<>();

        try {
            payload = FolderUtil.uploadFileIntoMenuFolder(file, newFileName, group, user, DewaRestApiKeys.MENU_DISKON_FLEET);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Upload file gagal!", Response.Status.INTERNAL_SERVER_ERROR);
        }

        String url = PortalUtil.getPortalURL(httpServletRequest) + payload.get("path");
        long fileId = Long.parseLong(payload.get("ID"));

        try {
            Date now = new Date();
            DiskonOther diskonOther = DiskonOtherLocalServiceUtil.createDiskonOther(0);
            diskonOther.setDealerId(DealerUtil.getDealerId(dealerCode));
            diskonOther.setTahun(Integer.parseInt(tahun));
            diskonOther.setKuartalId(kuartalId);
            diskonOther.setKeterangan(StringPool.DASH);
            diskonOther.setFileId(fileId);
            diskonOther.setFileName(newFileName);
            diskonOther.setFilePath(url);
            diskonOther.setCreatedBy(DewaRestApiKeys.API);
            diskonOther.setCreatedDate(now);
            diskonOther.setModifiedBy(DewaRestApiKeys.API);
            diskonOther.setModifiedDate(now);
            diskonOther.setRowStatus(true);
            DiskonOtherLocalServiceUtil.addDiskonOther(diskonOther);
        } catch (Exception e) {
            _log.error("Error while processing diskon others request: " + e.getMessage(), e);
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Terjadi kesalahan saat menambahkan ke database!", Response.Status.INTERNAL_SERVER_ERROR);
        }

        acknowledge = 1;
        return ResponseUtil.getAPIResponse(acknowledge, dealerCode, StringPool.BLANK, Response.Status.CREATED);
    }

    @Activate
    protected void activate() {
        _log.info(">>> REST Application " + DewaRestApiKeys.DISKON_OTHERS_API_PATH + " has been activated.");
    }

    @Deactivate
    protected void deactivate() {
        _log.info(">>> REST Application " + DewaRestApiKeys.DISKON_OTHERS_API_PATH + " has been deactivated.");
    }
}
