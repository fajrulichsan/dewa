package com.astra.dealink.rest.api.application.esrut;

import com.astra.dealink.rest.api.service.esrut.ESrutServiceImpl;
import com.astra.dealink.rest.api.util.DateUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.FolderUtil;
import com.astra.dealink.rest.api.util.ResponseUtil;
import com.astra.dealink.rest.api.util.TokenUtil;
import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
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
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

/**
 * @author psmahmad1402
 */
@ApplicationPath("/e-srut")
@Component(
      immediate = true,
      service = Application.class
)
public class ESrutRestApplication extends Application {
   private final Log LOG = LogFactoryUtil.getLog(ESrutRestApplication.class);
   private final ESrutServiceImpl eSrutService = new ESrutServiceImpl();
   public Set<Object> getSingletons() { return Collections.<Object>singleton(this); }
   private int acknowledge;
   private String dealerCode;
   private String message;

   @POST
   @Consumes(ContentTypes.MULTIPART_FORM_DATA)
   @Produces(ContentTypes.APPLICATION_JSON)
   @SuppressWarnings("deprecation")
   public Response postESrut(@Context HttpServletRequest request) throws PortalException {
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
         LOG.error(e);
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
      try {
         if (splitterCount != 1 || !fileName.startsWith(DewaRestApiKeys.SRUT)) {
            this.message = "Nama file tidak valid!";
            return getBadRequestResponse();
         }
      } catch (Exception e) {
         LOG.error(e);
         this.message = "Nama file tidak valid!";
         return getBadRequestResponse();
      }

      // Kode validation
      if (dealerCode == null || dealerCode.isEmpty()) {
         this.message = "Tidak terdapat kode dealer!";
         return getBadRequestResponse();
      } else {
         boolean isDealerExist = DealerUtil.isDealerExist(dealerCode);
         if (!isDealerExist) {
            this.message = "Dealer dengan kode tersebut belum terdaftar!";
            return getBadRequestResponse();
         }
      }

      SimpleDateFormat sdf = new SimpleDateFormat(DewaRestApiKeys.DATE_FORMAT_DOT);

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
         LOG.error(e);
         isUploadDateValid = false;
      }
      if (!isUploadDateValid) {
         this.message = "Format tanggal upload tidak valid!";
         return getBadRequestResponse();
      }

      boolean isFileExist = eSrutService.isESrutExist(dealerCode, fileName, uploadDate);
      HashMap<String, String> payload = new HashMap<>();

      if (!isFileExist) {
         return ResponseUtil.CONFLICT(acknowledge, dealerCode, "Duplikat file pada periode yang sama!");
      } else {
         try {
//            payload = FolderUtil.uploadFileIntoMenuFolder(file, fileName, group, user, DewaRestApiKeys.TEMP_FOLDER);
            payload = FolderUtil.uploadFileIntoMenuFolder(file, fileName, group, user, DewaRestApiKeys.MENU_E_SRUT);
         } catch (Exception e) {
            LOG.error(e);
            return ResponseUtil.INTERNAL_SERVER_ERROR(acknowledge, dealerCode, "Upload file gagal!");
         }
      }

      String url = PortalUtil.getPortalURL(request) + payload.get("path");
      long fileId = Long.parseLong(payload.get("ID"));

      try {
         eSrutService.createESrut(dealerCode, fileId, fileName, url, uploadDate);
      } catch (SystemException e) {
         LOG.error(e);
         return ResponseUtil.INTERNAL_SERVER_ERROR(acknowledge, dealerCode, "Terjadi kesalahan saat menambahkan ke database!");
      }

//      FolderUtil.moveFileIntoMenuFolder(fileId, fileName, group, user, "E-" + DewaRestApiKeys.MENU_E_SRUT);

      this.acknowledge = 1;
      return ResponseUtil.CREATED(acknowledge, dealerCode, "");
   }

   private Response getBadRequestResponse() { return ResponseUtil.BAD_REQUEST(acknowledge, dealerCode, message); }
}
