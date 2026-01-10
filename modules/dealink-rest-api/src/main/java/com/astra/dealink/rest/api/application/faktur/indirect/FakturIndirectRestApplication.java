package com.astra.dealink.rest.api.application.faktur.indirect;

import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.service.faktur.indirect.FakturIndirectServiceImpl;
import com.astra.dealink.rest.api.util.DateUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.FolderUtil;
import com.astra.dealink.rest.api.util.ResponseUtil;
import com.astra.dealink.rest.api.util.TokenUtil;
import com.astra.dewa.utils.FilterXSS;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
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
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

/**
 * @author psmahmad1402
 */
@ApplicationPath("/faktur-indirect")
@Component(
      immediate = true,
      service = Application.class
)
public class FakturIndirectRestApplication extends Application {
   private final Log LOG = LogFactoryUtil.getLog(FakturIndirectRestApplication.class);
   private final FakturIndirectServiceImpl fakturIndirectService = new FakturIndirectServiceImpl();

   public Set<Object> getSingletons() { return Collections.<Object>singleton(this); }

   @POST
   @Consumes(ContentTypes.MULTIPART_FORM_DATA)
   @Produces(ContentTypes.APPLICATION_JSON)
   @SuppressWarnings("deprecation")
   public Response postFakturIndirect(@Context HttpServletRequest request) throws PortalException {
      Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), DewaRestApiKeys.NAME_SITE);
      User user = PortalUtil.getUser(request);
      UploadServletRequest uploadServletRequest = PortalUtil.getUploadServletRequest(request);

      // XSS payload
      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = request.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = request.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadServletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadServletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      String dealerCode = "Unknown";
      int acknowledge = 0;

      try {
         // token validation
         String token = request.getParameter("token");
         boolean isTokenValid = TokenUtil.validateToken(token);
         if (!isTokenValid) {
            return ResponseUtil.UNAUTHORIZED(acknowledge, "", "Invalid token / token is expired!");
         }

         if (isRequestContainsXSS) {
            throw new SystemException("Oops! Something went wrong.");
         }

         // Request attribute params
         dealerCode = request.getParameter("Kode Dealer Cabang");
         String invoiceDateParam = request.getParameter("Tanggal Faktur");
         String uploadDateParam = request.getParameter("Tanggal Upload");

         // Request file params
         File file = uploadServletRequest.getFile("File");

         // Validate Dealer Code Param
         if (Validator.isNull(dealerCode)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Tidak terdapat kode dealer!");
         }

         // Validate File Param
         if (Validator.isNull(file)) {
            throw new NullPointerException("File tidak tersedia!");
         }

         String fileName = uploadServletRequest.getFileName("File");
         long fileSize = uploadServletRequest.getSize("File");

         LOG.info("==== Creating Faktur Indirect ====");
         LOG.info("File name: " + fileName);
         LOG.info("Dealer code: " + dealerCode);
         LOG.info("Invoice date: " + invoiceDateParam);
         LOG.info("Upload date: " + uploadDateParam);
         LOG.info("==================================");

         // File validation
         String fileExt = FileUtil.getExtension(fileName);
         if (!fileExt.equalsIgnoreCase(DewaRestApiKeys.EXT_PDF)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Ekstensi file harus .pdf!");
         } else if (fileSize > 1048576) {
            return getBadRequestResponse(acknowledge, dealerCode, "Ukuran file melebihi 1MB!");
         }

         // Mime type checking
         String mimeType = uploadServletRequest.getContentType("File");
         if (!mimeType.equals("application/pdf")) {
            return getBadRequestResponse(acknowledge, dealerCode, "Invalid mime type. Only .pdf files are allowed.");
         }

         // File attributes validation
         // long splitterCount = fileName.chars().filter(ch -> ch == '-').count();
         String[] fileProps = fileName.split("-");
         String fileDealerCode = null;

         fileDealerCode = fileProps[1];

         String noFilling1 = fileProps[2];
         String noFilling2 = fileProps[3].replace("." + DewaRestApiKeys.EXT_PDF, "");

         if (!fileName.startsWith(DewaRestApiKeys.REKAP_FAKTUR_KENDARAAN) ||
               Validator.isNull(fileDealerCode) ||
               Validator.isNull(noFilling1) ||
               Validator.isNull(noFilling2)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Nama file tidak valid!");
         }

         // Dealer validation
         if (!dealerCode.equals(fileDealerCode)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Kode dealer pada nama file tidak sesuai dengan kode dealer input!");
         } else {
            boolean isDealerExist = DealerUtil.isDealerExist(dealerCode);
            if (!isDealerExist) {
               return getBadRequestResponse(acknowledge, dealerCode, "Dealer dengan kode tersebut belum terdaftar!");
            }
         }

         SimpleDateFormat sdf = new SimpleDateFormat(DewaRestApiKeys.DATE_FORMAT_DOT);
         Date currentTime = new Date();

         // Invoice date validation
         Date invoiceDate = null;

         if (Validator.isNull(invoiceDateParam)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Tidak terdapat tanggal faktur!");
         } else if (!DateUtil.dateformatDot(invoiceDateParam)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Format tanggal faktur tidak valid");
         }

         invoiceDate = sdf.parse(invoiceDateParam);
         int invoiceYear = invoiceDate.getYear() + 1900;
         if (invoiceYear <= 1900 || invoiceDateParam.length() != 10) {
            return getBadRequestResponse(acknowledge, dealerCode, "Format tanggal faktur tidak valid");
         }

         // Upload date validation
         Date uploadDate = null;
         if (Validator.isNull(uploadDateParam)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Tidak terdapat tanggal upload!");
         } else if (!DateUtil.dateformatDot(uploadDateParam)) {
            return getBadRequestResponse(acknowledge, dealerCode, "Format Tanggal tidak valid");
         }

         uploadDate = sdf.parse(uploadDateParam);
         int uploadYear = uploadDate.getYear() + 1900;
         if (uploadYear <= 1900 || uploadDateParam.length() != 10) {
            return getBadRequestResponse(acknowledge, dealerCode, "Format tanggal upload tidak valid");
         }

         // Data insertion
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(uploadDate);
         calendar.add(Calendar.HOUR_OF_DAY, currentTime.getHours());
         calendar.add(Calendar.MINUTE, currentTime.getMinutes());
         calendar.add(Calendar.SECOND, currentTime.getSeconds());
         Date uploadTime = calendar.getTime();

         boolean isFakturIndirectExist = fakturIndirectService.isFakturIndirectExist(dealerCode, fileName, invoiceDate, uploadDate);
         HashMap<String, String> payload = new HashMap<>();

         if (isFakturIndirectExist) {
            return ResponseUtil.CONFLICT(acknowledge, dealerCode, "Duplikat file di tanggal faktur dan tanggal upload yang sama!");
         } else {
            try {
               payload = FolderUtil.uploadFileIntoMenuFolder(file, fileName, group, user, DewaRestApiKeys.MENU_FAKTUR_INDIRECT);
            } catch (Exception e) {
               LOG.error(e.getMessage(), e);
               return ResponseUtil.REQUEST_TIMEOUT(acknowledge, dealerCode, "Request timeout!");
            }
         }

         long fileId = Long.parseLong(payload.get("ID"));
         String url = PortalUtil.getPortalURL(request) + payload.get("path");

         try {
            fakturIndirectService.createFakturIndirect(dealerCode, fileId, fileName, url, invoiceDate, uploadTime);
         } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
               DLAppLocalServiceUtil.deleteFileEntry(fileId);
               return ResponseUtil.INTERNAL_SERVER_ERROR(acknowledge, dealerCode, "Terjadi kesalahan saat menambahkan ke database!");
            } catch (Exception ne) {
               LOG.error(ne.getMessage(), ne);
               return ResponseUtil.INTERNAL_SERVER_ERROR(acknowledge, dealerCode, "Internal server error!");
            }
         }

         acknowledge = 1;
         return ResponseUtil.CREATED(acknowledge, dealerCode, "");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         if (e instanceof NullPointerException) {
            return getBadRequestResponse(acknowledge, dealerCode, "File tidak tersedia");
         } else if (e instanceof ParseException) {
            return getBadRequestResponse(acknowledge, dealerCode, "Format tanggal tidak sesuai");
         } else if (e instanceof ArrayIndexOutOfBoundsException) {
            return getBadRequestResponse(acknowledge, dealerCode, "Format nama file tidak valid");
         } else {
            return ResponseUtil.INTERNAL_SERVER_ERROR(acknowledge, dealerCode, "Internal server error!");
//            return getBadRequestResponse(acknowledge, dealerCode, e.getMessage());
         }
      }
   }

   private Response getBadRequestResponse(int acknowledge, String dealerCode, String message) {
      return ResponseUtil.BAD_REQUEST(acknowledge, dealerCode, message);
   }
}