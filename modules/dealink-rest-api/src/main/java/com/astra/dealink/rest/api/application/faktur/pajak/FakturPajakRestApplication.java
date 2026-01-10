package com.astra.dealink.rest.api.application.faktur.pajak;

import com.astra.dealink.rest.api.util.ResponseUtil;
import com.astra.dealink.rest.api.util.TokenUtil;
import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.service.faktur.pajak.FakturPajakServiceImpl;
import com.astra.dealink.rest.api.util.DateUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.FolderUtil;
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
@ApplicationPath("/faktur-pajak")
@Component(
      immediate = true,
      service = Application.class
)
public class FakturPajakRestApplication extends Application {
   private final Log LOG = LogFactoryUtil.getLog(FakturPajakRestApplication.class);
   private final FakturPajakServiceImpl fakturPajakService = new FakturPajakServiceImpl();
   public Set<Object> getSingletons() { return Collections.<Object>singleton(this); }
   private int acknowledge;
   private String dealerCode;
   private String message;

   @POST
   @Consumes(ContentTypes.MULTIPART_FORM_DATA)
   @Produces(ContentTypes.APPLICATION_JSON)
   @SuppressWarnings("deprecation")
   public Response postFakturPajak(@Context HttpServletRequest request) throws PortalException {
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

      this.acknowledge = 0;
      this.dealerCode = "";
      if (isRequestContainsXSS) {
         return ResponseUtil.BAD_REQUEST(acknowledge, dealerCode, "Oops! Something went wrong.");
      }

      // token validation
      String token = request.getParameter("token");
      boolean isTokenValid = TokenUtil.validateToken(token);
      if (!isTokenValid) {
         return ResponseUtil.UNAUTHORIZED(acknowledge, "", "Invalid token / token is expired!");
      }

      // Request attribute params
      this.dealerCode = request.getParameter("Kode Dealer Cabang");
      String invoiceDateParam = request.getParameter("Tanggal Faktur");
      String uploadDateParam = request.getParameter("Tanggal Upload");

      // Request file params
      File file = null;
      String fileName = null;
      long fileSize = 0;
      try {
         file = uploadServletRequest.getFile("File");
         fileName = uploadServletRequest.getFileName("File");
         fileSize = uploadServletRequest.getSize("File");
      } catch (NullPointerException e) {
         return ResponseUtil.BAD_REQUEST(acknowledge, dealerCode, "Unknown file source!");
      }

      // File validation
      String fileExt = FileUtil.getExtension(fileName);
      if (!fileExt.equalsIgnoreCase("pdf")) {
         this.message = "Ekstensi file harus .pdf!";
         return getBadRequestResponse();
      } else if (fileSize > 1048576) {
         this.message = "Ukuran file melebihi 1MB!";
         return getBadRequestResponse();
      }

      // File attributes validation
      long splitterCount = fileName.chars().filter(ch -> ch == '-').count();
      String[] fileProps = fileName.split("-");
      String fileDealerCode = null;
      String fileInvoiceDateAsString = "";
      try {
         fileDealerCode = fileProps[1];
         for (int i = 0; i < 3; i++) {
            fileInvoiceDateAsString += fileProps[2].split("[.]")[i];
            if (i != 2) {
               fileInvoiceDateAsString += ".";
            }
         }
         if (splitterCount != 2 ||
               !fileName.startsWith(DewaRestApiKeys.REKAP_FAKTUR_PAJAK) ||
               fileDealerCode.isEmpty()) {
            this.message = "Nama file tidak valid!";
            return getBadRequestResponse();
         }
      } catch (Exception e) {
         this.message = "Nama file tidak valid!";
         return getBadRequestResponse();
      }

      // Dealer validation
      if (dealerCode == null || dealerCode.isEmpty()) {
         this.message = "Tidak terdapat kode dealer!";
         return getBadRequestResponse();
      } else if (!dealerCode.equals(fileDealerCode)) {
         this.message = "Kode dealer pada nama file tidak sesuai dengan kode dealer input!";
         return getBadRequestResponse();
      } else {
         boolean isDealerExist = DealerUtil.isDealerExist(dealerCode);
         if (!isDealerExist) {
            this.message = "Dealer dengan kode tersebut belum terdaftar!";
            return getBadRequestResponse();
         }
         boolean isHoDealer = DealerUtil.isHODealerExist(dealerCode);
         if (!isHoDealer) {
            this.message = "HO Dealer dengan kode tersebut belum terdaftar!";
            return getBadRequestResponse();
         }
      }

      SimpleDateFormat sdf = new SimpleDateFormat(DewaRestApiKeys.DATE_FORMAT_DOT);
      // Invoice date validation
      Date invoiceDate = null;
      Date fileInvoiceDate = null;
      boolean isInvoiceDateValid = true;
      boolean isInvoiceDateMatch = true;
      if (invoiceDateParam == null || invoiceDateParam.isEmpty()) {
         this.message = "Tidak terdapat tanggal faktur!";
         return getBadRequestResponse();
      } else if (!DateUtil.dateformatDot(invoiceDateParam)) {
         isInvoiceDateValid = false;
      }
      try {
         invoiceDate = sdf.parse(invoiceDateParam);
         int invoiceYear = invoiceDate.getYear() + 1900;
         if (invoiceYear <= 1900) {
            isInvoiceDateValid = false;
         }
         fileInvoiceDate = sdf.parse(fileInvoiceDateAsString);
         int fileInvoiceYear = fileInvoiceDate.getYear() + 1900;
         if (fileInvoiceYear <= 1900) {
            isInvoiceDateValid = false;
         }
         isInvoiceDateMatch = invoiceDate.equals(fileInvoiceDate);
      } catch (ParseException e) {
         isInvoiceDateValid = false;
      }
      if (!isInvoiceDateValid) {
         this.message = "Format tanggal faktur tidak valid!";
         return getBadRequestResponse();
      }
      if (!isInvoiceDateMatch) {
         this.message = "Tanggal faktur pada nama file tidak sesuai dengan tanggal faktur input!";
         return getBadRequestResponse();
      }

      // Upload date validation
      Date uploadDate = null;
      boolean isUploadDateValid = true;
      if (uploadDateParam == null || uploadDateParam.isEmpty()) {
         this.message = "Tidak terdapat tanggal upload!";
         return getBadRequestResponse();
      } else if (!DateUtil.dateformatDot(uploadDateParam)) {
         isUploadDateValid = false;
      }
      try {
         uploadDate = sdf.parse(uploadDateParam);
         int uploadYear = uploadDate.getYear() + 1900;
         if (uploadYear <= 1900) {
            isUploadDateValid = false;
         }
      } catch (ParseException e) {
         isUploadDateValid = false;
      }
      if (!isUploadDateValid) {
         this.message = "Format tanggal upload tidak valid!";
         return getBadRequestResponse();
      }

      // Data insertion
      Date currentTime = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(uploadDate);
      calendar.add(Calendar.HOUR_OF_DAY, currentTime.getHours());
      calendar.add(Calendar.MINUTE, currentTime.getMinutes());
      calendar.add(Calendar.SECOND, currentTime.getSeconds());
      Date uploadTime = calendar.getTime();

      String newFileName = setNewFileName(invoiceDate, fileExt);

      HashMap<String, String> payload = new HashMap<>();

      try {
         payload = FolderUtil.uploadFileIntoMenuFolder(file, newFileName, group, user, DewaRestApiKeys.MENU_FAKTUR_PAJAK);
      } catch (Exception e) {
         return ResponseUtil.INTERNAL_SERVER_ERROR(acknowledge, dealerCode, "Upload file gagal!");
      }

      String url = PortalUtil.getPortalURL(request) + payload.get("path");
      long fileId = Long.parseLong(payload.get("ID"));

      try {
         fakturPajakService.createFakturPajak(dealerCode, fileId, newFileName, url, invoiceDate, uploadTime);
      } catch (SystemException e) {
         return ResponseUtil.INTERNAL_SERVER_ERROR(acknowledge, dealerCode, "Terjadi kesalahan saat menambahkan ke database!");
      }

      this.acknowledge = 1;
      return ResponseUtil.CREATED(acknowledge, dealerCode, "");
   }

   private Response getBadRequestResponse() { return ResponseUtil.BAD_REQUEST(acknowledge, dealerCode, message); }

   private String setNewFileName(Date invoiceDate, String fileExt) {
      String newFileName = "";
      SimpleDateFormat sdf = new SimpleDateFormat(DewaRestApiKeys.DATE_FORMAT_DOT);
      String invoiceDateAsString = sdf.format(invoiceDate);
      newFileName = DewaRestApiKeys.REKAP_FAKTUR_PAJAK + "-" + dealerCode + "-" + invoiceDateAsString + "." + fileExt;
      return newFileName;
   }
}
