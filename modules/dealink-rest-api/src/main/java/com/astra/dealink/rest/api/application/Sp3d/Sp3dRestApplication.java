package com.astra.dealink.rest.api.application.Sp3d;

import com.astra.dealink.rest.api.service.sp3d.Sp3dServiceImpl;
import com.astra.dewa.model.SP3D;
import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.util.BulanUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.FileEntryUtil;
import com.astra.dealink.rest.api.util.FolderUtil;
import com.astra.dealink.rest.api.util.JSONUtil;
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

/**
 * @author psmafifd1401
 */
@ApplicationPath("/sp3d")
@Component(
      immediate = true,
      service = Application.class
)

public class Sp3dRestApplication extends Application {
   private final Sp3dServiceImpl sp3dService = new Sp3dServiceImpl();
   private final Log LOGGER = LogFactoryUtil.getLog(Sp3dRestApplication.class);

   public Set<Object> getSingletons() {
      return Collections.<Object>singleton(this);
   }

   boolean isRequestContainsXSS = false;

   @POST
   @Produces(ContentTypes.APPLICATION_JSON)
   public Response postSp3d(@Context HttpServletRequest request) throws PortalException, ParseException {

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

         Date convertDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);

         long splitterCount = fileName.chars().filter(ch -> ch == '-').count();
         long splitterDotCount = fileName.chars().filter(ch -> ch == '.').count();
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
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "file melebihi 5 mb").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!fileName.contains(DewaRestApiKeys.MENU_SP3D) || splitterCount != 2 || splitterDotCount != 1) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "Nama file tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            String bulan;
            int tahun;
            try {
               String[] fileProps = fileName.split("-");
               String[] tahunProps = fileProps[2].split("[.]");
               bulan = fileProps[1];
               tahun = Integer.parseInt(tahunProps[0]);
               if (!BulanUtil.isMonthExist(bulan)) {
                  LOGGER.info(JSONUtil.createResponseJson(0, "", "Bulan file tidak valid").toJSONString());
                  return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(JSONUtil.getJsonResponse(0, dealerCode, "Bulan file tidak valid").toJSONString())
                        .type(ContentTypes.APPLICATION_JSON)
                        .build();
               }
            } catch (Exception e) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "Nama file tidak sesuai").toJSONString());
               LOGGER.error(e);
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

//            DATE
            if (date.isEmpty()) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "tidak terdapat tanggal").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "tidak terdapat tanggal").toJSONString())
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

//            KODE DEALER
            if (dealerCode.isEmpty()) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "tidak terdapat kode dealer").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "file tidak tersedia").toJSONString())
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

//            FILE EXIST
            if (sp3dService.sp3dIsExist(fileName, DealerUtil.getDealerId(dealerCode))) {
               LOGGER.info(JSONUtil.createResponseJson(0, "", "file sudah tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "file sudah tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            } else {
               try {
                  payload = FolderUtil.uploadFileIntoMenuFolder(file, fileName, group, user, DewaRestApiKeys.MENU_SP3D);
               } catch (Exception e) {
                  LOGGER.error(e);
                  return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(JSONUtil.getJsonResponse(0, dealerCode, "Gagal mengupload file").toJSONString())
                        .type(ContentTypes.APPLICATION_JSON)
                        .build();
               }
            }

            String filePath = PortalUtil.getPortalURL(request) + payload.get("path");
            long fileId = Long.parseLong(payload.get("ID"));
            try {
               SP3D addSp3d = sp3dService.createSp3d(dealerCode, fileName, fileId, filePath, tahun, bulan, convertDate);
               LOGGER.info(addSp3d);
            } catch (SystemException e) {
               FileEntryUtil.deleteFileEntry(fileId);
               LOGGER.error(JSONUtil.getJsonResponse(0, dealerCode, "Gagal menambahkan ke database").toJSONString());
               LOGGER.error(e);
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Gagal menambahkan ke database").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
//                FolderUtil.moveFileIntoMenuFolder(fileId, fileName, group, user, DewaRestApiKeys.MENU_SP3D);

         } catch (SystemException e) {
            LOGGER.warn(JSONUtil.getJsonResponse(0, dealerCode, e.getMessage()).toJSONString());
            LOGGER.error(e);
            return Response
                  .status(Response.Status.BAD_REQUEST)
                  .entity(JSONUtil.getJsonResponse(0, dealerCode, e.getMessage()).toJSONString())
                  .type(ContentTypes.APPLICATION_JSON)
                  .build();
         }
      } catch (NullPointerException e) {
         message = "Unknown file source!";
         LOGGER.error(e);
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