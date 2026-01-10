package com.astra.dealink.rest.api.application.Skiris;

import com.astra.dealink.rest.api.service.skiris.SkirisServiceImpl;
import com.astra.dewa.model.SkIris;
import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.util.BulanUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.FileEntryUtil;
import com.astra.dealink.rest.api.util.FolderUtil;
import com.astra.dealink.rest.api.util.JSONUtil;
import com.astra.dealink.rest.api.util.TokenUtil;
import com.astra.dealink.rest.api.util.WilayahUtil;
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
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author psmafifd1401
 */
@ApplicationPath("/sk-iris")
@Component(
      immediate = true,
      service = Application.class
)
public class SkIrisRestApplication extends Application {
   private final SkirisServiceImpl skirisService = new SkirisServiceImpl();
   private final Log LOGGER = LogFactoryUtil.getLog(SkIrisRestApplication.class);

   public Set<Object> getSingletons() {
      return Collections.<Object>singleton(this);
   }

   boolean isRequestContainsXSS = false;

   @POST
   @Produces(ContentTypes.APPLICATION_JSON)
   public Response postSkiris(@Context HttpServletRequest request) throws PortalException, ParseException {
      Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), DewaRestApiKeys.NAME_SITE);
      User user = PortalUtil.getUser(request);
      UploadServletRequest uploadServletRequest = PortalUtil.getUploadServletRequest(request);

      Enumeration<String> attributes = request.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = request.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOGGER.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadServletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadServletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOGGER.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      String dealerCode = "Unknown";
      String message;
      try {
         File file = uploadServletRequest.getFile("File");
         String fileName = uploadServletRequest.getFileName("File");
         long fileSize = uploadServletRequest.getSize("File");
         String fileExtension = FileUtil.getExtension(fileName);
         dealerCode = request.getParameter("Kode Dealer Ho");
         String date = request.getParameter("Tanggal");
         String token = request.getParameter("token");

         HashMap<String, String> payload = new HashMap<>();
         long splitterCount = fileName.chars().filter(ch -> ch == '-').count();
         long splitterCountDot = fileName.chars().filter(ch -> ch == '.').count();
         long splitterCountDate = date.chars().filter(ch -> ch == '.').count();

         LOGGER.info("file name : " + fileName);
         LOGGER.info("file size : " + fileSize);
         LOGGER.info("file extension : " + fileExtension);
         LOGGER.info("dealer code : " + dealerCode);
         LOGGER.info("date : " + date);
         LOGGER.info("token : " + token);

         try {
//            TOKEN
            boolean isTokenValid = TokenUtil.validateToken(token);
            if (!isTokenValid) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "Invalid token/token is expired!").toJSONString());
               return Response
                     .status(Response.Status.UNAUTHORIZED)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Invalid token/token is expired!").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
//            FILE
            if (Validator.isNull(fileName)) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "file tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "file tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!fileExtension.equalsIgnoreCase("pdf")) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "ekstensi file harus .pdf").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "ekstensi file harus .pdf").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (fileSize > 5242880) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "file melebihi 5 mb").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.createResponseJson(0, "", "file melebihi 5 mb").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!fileName.contains("SK IRIS") || splitterCount != 3 || splitterCountDot != 1) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "Format nama file tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Format nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            String kategori = "";

            if (fileName.toLowerCase().contains(DewaRestApiKeys.NON_PSP.toLowerCase())) {
               kategori = DewaRestApiKeys.NON_PSP;
            } else if (fileName.toLowerCase().contains(DewaRestApiKeys.PSP.toLowerCase())) {
               kategori = DewaRestApiKeys.PSP;
            }

            Pattern patternNon = Pattern.compile("Non");
            Pattern patternPsp = Pattern.compile("PSP");
            Matcher non = patternNon.matcher(fileName);
            Matcher psp = patternPsp.matcher(fileName);

            int countNon = 0;
            int countPsp = 0;

            while (non.find()) {
               countNon++;
            }

            while (psp.find()) {
               countPsp++;
            }

            if (countNon > 1 || countPsp > 1) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "Kategori tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Kategori tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

            int tahun;
            String periode;
            String wilayahName;
            Date convertDate;

            try {
               convertDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
               String[] fileProps = fileName.split("-");
               String[] tahunProps = fileProps[3].split("[.]");
               tahun = Integer.parseInt(tahunProps[0]);
               periode = fileProps[2];
               wilayahName = fileProps[1];
            } catch (Exception e) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "Nama file tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

            if (!BulanUtil.isMonthExist(periode)) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "bulam tidak tersedia / Format nama file tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "bulam tidak tersedia / Format nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!WilayahUtil.isWilayahExist(wilayahName)) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "wilayah tidak tersedia / Format nama file tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "wilayah tidak tersedia / Format nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            int wilayahId = WilayahUtil.getWilayahId(wilayahName);

//           DATE
            if (date.isEmpty()) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "tanggal tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "tanggal tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (splitterCountDate != 2 || date.length() != 10) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "format tanggal tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "format tanggal tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

//            KODE DEalER
            if (dealerCode.isEmpty()) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "kode dealer tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "kode dealer tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

            if (!DealerUtil.isHODealerExist(dealerCode)) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "dealer Ho tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "dealer Ho tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

            if (skirisService.skIrisIsExist(fileName, DealerUtil.getDealerId(dealerCode))) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "file sudah tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "file sudah tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            } else {
               try {
                  payload = FolderUtil.uploadFileIntoMenuFolder(file, fileName, group, user, DewaRestApiKeys.MENU_SKIRIS);
               } catch (Exception e) {
                  LOGGER.error(e);
                  return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(JSONUtil.getJsonResponse(0, dealerCode, "Gagal mengupload file").toJSONString())
                        .type(ContentTypes.APPLICATION_JSON)
                        .build();
               }
            }
            int dealerId = DealerUtil.getDealerId(dealerCode);
            String filePath = PortalUtil.getPortalURL(request) + payload.get("path");
            long fileId = Long.parseLong(payload.get("ID"));
            try {
               SkIris addSkIris = skirisService.createSkiris(tahun, periode, wilayahId, dealerId, kategori, fileName, fileId, filePath, convertDate);
               LOGGER.info(addSkIris);
            } catch (SystemException e) {
               FileEntryUtil.deleteFileEntry(fileId);
               LOGGER.error(JSONUtil.getJsonResponse(0, dealerCode, "Gagal menambahkan ke database").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Gagal menambahkan ke database").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
//                FolderUtil.moveFileIntoMenuFolder(fileId, fileName, group, user, DewaRestApiKeys.MENU_SKIRIS);

         } catch (SystemException e) {
            LOGGER.warn(JSONUtil.getJsonResponse(0, dealerCode, e.getMessage()).toJSONString());
            return Response
                  .status(Response.Status.BAD_REQUEST)
                  .entity(JSONUtil.getJsonResponse(0, dealerCode, e.getMessage()).toJSONString())
                  .type(ContentTypes.APPLICATION_JSON)
                  .build();
         }
      } catch (NullPointerException e) {
         message = "Unknown file source!";
         LOGGER.error(JSONUtil.getJsonResponse(0, dealerCode, message).toJSONString());
         return Response
               .status(Response.Status.BAD_REQUEST)
               .entity(JSONUtil.getJsonResponse(0, dealerCode, message).toJSONString())
               .type(ContentTypes.APPLICATION_JSON)
               .build();
      }
      LOGGER.info(JSONUtil.getJsonResponse(1, dealerCode, "").toJSONString());
      return Response
            .status(Response.Status.CREATED)
            .entity(JSONUtil.getJsonResponse(1, dealerCode, "").toJSONString())
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }
}