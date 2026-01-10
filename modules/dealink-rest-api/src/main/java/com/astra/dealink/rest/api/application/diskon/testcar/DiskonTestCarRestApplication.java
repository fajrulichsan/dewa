package com.astra.dealink.rest.api.application.diskon.testcar;

import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.service.diskon.testcar.DiskonTestCarServiceImpl;
import com.astra.dealink.rest.api.service.tipekendaraan.TipeKendaraanServiceImpl;
import com.astra.dealink.rest.api.util.BulanUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.FolderUtil;
import com.astra.dealink.rest.api.util.ResponseUtil;
import com.astra.dealink.rest.api.util.TipeKendaraanUtil;
import com.astra.dealink.rest.api.util.TokenUtil;
import com.astra.dewa.utils.FilterXSS;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author psmahmad1402
 */
@ApplicationPath("/realisasi-diskon-test-car")
@Component(
        immediate = true,
        service = Application.class
)
public class DiskonTestCarRestApplication extends Application {
    private final Log _log = LogFactoryUtil.getLog(DiskonTestCarRestApplication.class);
    private final DiskonTestCarServiceImpl diskonTestCarService = new DiskonTestCarServiceImpl();
    private final TipeKendaraanServiceImpl tipeKendaraanService = new TipeKendaraanServiceImpl();

    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }

    @POST
    @Consumes(ContentTypes.MULTIPART_FORM_DATA)
    @Produces(ContentTypes.APPLICATION_JSON)
    public Response postDiskonTestCar(@Context HttpServletRequest request) throws PortalException {
        Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), DewaRestApiKeys.NAME_SITE);
        User user = PortalUtil.getUser(request);
        UploadServletRequest uploadServletRequest = PortalUtil.getUploadServletRequest(request);

        int acknowledge = 0;
        String dealerCode = StringPool.BLANK;

        // XSS payload
        boolean isRequestContainsXSS = false;
        Enumeration<String> attributes = request.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = request.getParameter(param);
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
        String token = request.getParameter("token");
        boolean isTokenValid = TokenUtil.validateToken(token);
        if (!isTokenValid) {
            return ResponseUtil.UNAUTHORIZED(acknowledge, "", "Invalid token / token is expired!");
        }

        // Request attribute params
        dealerCode = request.getParameter("dealerCode").trim();
        if (Validator.isNull(dealerCode)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Tidak terdapat kode dealer!", Response.Status.BAD_REQUEST);
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

        // File attributes validation
        String[] fileProps = fileName.split("-");
        String fileVehicleType = null;
        String fileKuartal = null;
        int fileYear = 0;
        String fileDealerCode = null;
        try {
            fileVehicleType = fileProps[0].replaceAll(DewaRestApiKeys.MENU_DISKON_TEST_CAR + " ", "");
            fileKuartal = fileProps[1];
            String fileYearAsString = fileProps[2];
            fileDealerCode = fileProps[3].split("[.]")[0];
            if (!fileName.toLowerCase().startsWith(DewaRestApiKeys.MENU_DISKON_TEST_CAR.toLowerCase()) || fileKuartal.isEmpty()) {
                return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Nama file tidak valid!", Response.Status.BAD_REQUEST);
            }
            fileYear = Integer.parseInt(fileYearAsString);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Nama file tidak valid!", Response.Status.BAD_REQUEST);
        }

        // Vehicle type validation
        String tipeKendaraan = request.getParameter("tipeKendaraan").trim();
        if (Validator.isNull(tipeKendaraan)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Tidak terdapat tipe kendaraan!", Response.Status.BAD_REQUEST);
        }

        boolean isTipeKendaraanExist = TipeKendaraanUtil.isTipeKendaraanExist(tipeKendaraan);
        if (!isTipeKendaraanExist) {
            try {
                tipeKendaraanService.createTipeKendaraan(tipeKendaraan);
            } catch (SystemException e) {
                _log.error(e.getMessage(), e);
                return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Gagal menambahkan tipe kendaraan baru!", Response.Status.INTERNAL_SERVER_ERROR);
            }
        }

        // Quarter validation
        String kuartal = request.getParameter("kuartal").trim();
        boolean isQuarterValid = BulanUtil.isKuartalExist(kuartal);
        if (!isQuarterValid) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Kuartal tidak valid!", Response.Status.BAD_REQUEST);
        }

        // Year validation
        String tahun = request.getParameter("tahun").trim();
        if (!Validator.isNumber(tahun)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Tahun tidak valid!", Response.Status.BAD_REQUEST);
        }

        // Dealer validation
        if (!dealerCode.equals(fileDealerCode)) {
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Kode dealer pada nama file tidak sesuai dengan kode dealer input!", Response.Status.BAD_REQUEST);
        } else {
            boolean isDealerExist = DealerUtil.isDealerExist(dealerCode);
            if (!isDealerExist) {
                return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Dealer dengan kode tersebut belum terdaftar!", Response.Status.BAD_REQUEST);
            }
        }

        // Data insertion
        HashMap<String, String> payload = new HashMap<>();

        try {
            payload = FolderUtil.uploadFileIntoMenuFolder(file, fileName, group, user, DewaRestApiKeys.MENU_DISKON_TEST_CAR);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Upload file gagal!", Response.Status.INTERNAL_SERVER_ERROR);
        }

        String url = PortalUtil.getPortalURL(request) + payload.get("path");
        long fileId = Long.parseLong(payload.get("ID"));

        try {
            diskonTestCarService.createDiskonTestCar(dealerCode, fileId, fileName, url, Integer.parseInt(tahun), kuartal, tipeKendaraan);
        } catch (PortalException e) {
            _log.error(e.getMessage(), e);
            return ResponseUtil.getAPIResponse(acknowledge, dealerCode, "Terjadi kesalahan saat menambahkan ke database!", Response.Status.INTERNAL_SERVER_ERROR);
        }

        acknowledge = 1;
        return ResponseUtil.getAPIResponse(acknowledge, dealerCode, StringPool.BLANK, Response.Status.CREATED);
    }

    @Activate
    protected void activate() {
        _log.info(">>> REST Application " + DewaRestApiKeys.DISKON_TEST_CAR_API_PATH + " has been activated.");
    }

    @Deactivate
    protected void deactivate() {
        _log.info(">>> REST Application " + DewaRestApiKeys.DISKON_TEST_CAR_API_PATH + " has been deactivated.");
    }
}