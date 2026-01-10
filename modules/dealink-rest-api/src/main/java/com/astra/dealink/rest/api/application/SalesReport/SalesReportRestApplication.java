package com.astra.dealink.rest.api.application.SalesReport;

import com.astra.dewa.model.SalesReport;
import com.astra.dealink.rest.api.constants.DewaRestApiKeys;
import com.astra.dealink.rest.api.service.sales_report.SalesReportServiceimpl;
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
@ApplicationPath("/sales-report")
@Component(
      immediate = true,
      service = javax.ws.rs.core.Application.class
)
public class SalesReportRestApplication extends Application {
   private final SalesReportServiceimpl salesReportServiceimpl = new SalesReportServiceimpl();
   private final Log LOG = LogFactoryUtil.getLog(SalesReportRestApplication.class);

   public Set<Object> getSingletons() {
      return Collections.<Object>singleton(this);
   }

   boolean isRequestContainsXSS = false;

   @POST
   @Produces(ContentTypes.APPLICATION_JSON)
   public Response postSalesReport(@Context HttpServletRequest request) throws PortalException, ParseException {
      Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), DewaRestApiKeys.NAME_SITE);
      User user = PortalUtil.getUser(request);
      UploadServletRequest uploadServletRequest = PortalUtil.getUploadServletRequest(request);
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
      String message;

      try {
         dealerCode = request.getParameter("Kode Dealer Cabang");
         File file = uploadServletRequest.getFile("File");
         String fileName = uploadServletRequest.getFileName("File");
         long fileSize = uploadServletRequest.getSize("File");
         String fileExtension = FileUtil.getExtension(fileName);
         String periode = request.getParameter("Tanggal Faktur");
         String uploadDate = request.getParameter("Tanggal Upload");
         String token = request.getParameter("token");
         HashMap<String, String> payload = new HashMap<>();
         long splitterCount = fileName.chars().filter(ch -> ch == '-').count();
         long splitterCountDate = uploadDate.chars().filter(ch -> ch == '.').count();
         Date convertDate = new SimpleDateFormat("dd.MM.yyyy").parse(periode);
         String dealerCodeProps;
         String periodeProps;

         LOG.info("file-name: " + fileName);
         LOG.info("file-size: " + fileSize);
         LOG.info("file-extension: " + fileExtension);
         LOG.info("invoice date: " + periode);
         LOG.info("upload date: " + uploadDate);
         LOG.info("dealerCode: " + dealerCode);
         LOG.info("token: " + token);

         try {
//                TOKEN
            boolean isTokenValid = TokenUtil.validateToken(token);
            if (!isTokenValid) {
               LOG.info(JSONUtil.createResponseJson(0, "", "Invalid token/token is expired!").toJSONString());
               return Response
                     .status(Response.Status.UNAUTHORIZED)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Invalid token/token is expired!").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
//                FILE
            if (Validator.isNull(fileName)) {
               LOG.info(JSONUtil.createResponseJson(0, "", "file tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "file tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!(fileExtension.equalsIgnoreCase(DewaRestApiKeys.EXT_XLS) || fileExtension.equalsIgnoreCase(DewaRestApiKeys.EXT_XLSX))) {
               LOG.info(JSONUtil.createResponseJson(0, "", "Ekstensi file harus .xls atau .xlsx").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Ekstensi file harus .xls atau .xlsx").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (fileSize > 1048576) {
               LOG.info(JSONUtil.createResponseJson(0, "", "file melebihi 5 mb").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.createResponseJson(0, "", "file melebihi 5 mb").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            try {
               String[] fileProps = fileName.split("-");
               periodeProps = fileProps[1];
               String dealerProps = fileProps[2];
               String[] filedotSplit = dealerProps.split("[.]");
               dealerCodeProps = filedotSplit[0];
            } catch (SystemException e) {
               LOG.error(JSONUtil.createResponseJson(0, "", "Format nama file tidak sesuai").toJSONString());
               LOG.error(e);
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Format nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!fileName.contains("Sales Report") || splitterCount != 2) {
               LOG.info(JSONUtil.createResponseJson(0, "", "Format nama file tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Format nama file tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
//                Date
            if (uploadDate.isEmpty()) {
               LOG.info(JSONUtil.createResponseJson(0, "", "tanggal tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "tanggal tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (splitterCountDate != 2 || uploadDate.length() != 10) {
               LOG.info(JSONUtil.createResponseJson(0, "", "format tanggal tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "format tanggal tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (periode.isEmpty()) {
               LOG.info(JSONUtil.createResponseJson(0, "", "tanggal faktur tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "tanggal tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!periode.equalsIgnoreCase(periodeProps)) {
               LOG.info(JSONUtil.createResponseJson(0, "", "tanggal faktur tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "tanggal faktur tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

//                Kode Dealer
            if (dealerCode.isEmpty()) {
               LOG.info(JSONUtil.createResponseJson(0, "", "kode dealer tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "kode dealer tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!dealerCode.equalsIgnoreCase(dealerCodeProps)) {
               LOG.info(JSONUtil.createResponseJson(0, "", "kode dealer tidak sesuai").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "kode dealer tidak sesuai").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
            if (!DealerUtil.isDealerExist(dealerCode)) {
               LOG.info(JSONUtil.createResponseJson(0, "", "dealer cabang tidak tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "dealer cabang tidak tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }

            // Upload
            if (salesReportServiceimpl.salesReportIsExist(fileName, dealerCode)) {
               LOG.info(JSONUtil.createResponseJson(0, "", "file sudah tersedia").toJSONString());
               return Response
                     .status(Response.Status.BAD_REQUEST)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "file sudah tersedia").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            } else {
               try {
                  payload = FolderUtil.uploadFileIntoMenuFolder(file, fileName, group, user, DewaRestApiKeys.MENU_SALES_REPORT);
               } catch (Exception e) {
                  LOG.error(e);
                  return Response
                        .status(Response.Status.REQUEST_TIMEOUT)
                        .entity(JSONUtil.getJsonResponse(0, dealerCode, "Request timeout!").toJSONString())
                        .type(ContentTypes.APPLICATION_JSON)
                        .build();
               }
            }
            int dealerId = DealerUtil.getDealerId(dealerCode);
            String filePath = PortalUtil.getPortalURL(request) + payload.get("path");
            long fileId = Long.parseLong(payload.get("ID"));
            try {
               SalesReport addSalesReport = salesReportServiceimpl.createSalesReport(dealerId, fileId, fileName, filePath, convertDate);
               LOG.info(addSalesReport);
            } catch (SystemException e) {
               FileEntryUtil.deleteFileEntry(fileId);
               LOG.error(JSONUtil.getJsonResponse(0, dealerCode, "Gagal menambahkan ke database").toJSONString());
               LOG.error(e);
               return Response
                     .status(Response.Status.INTERNAL_SERVER_ERROR)
                     .entity(JSONUtil.getJsonResponse(0, dealerCode, "Gagal menambahkan ke database").toJSONString())
                     .type(ContentTypes.APPLICATION_JSON)
                     .build();
            }
//                FolderUtil.moveFileIntoMenuFolder(fileId, fileName, group, user, DewaRestApiKeys.MENU_SALES_REPORT);
         } catch (SystemException e) {
            LOG.error(JSONUtil.getJsonResponse(0, dealerCode, e.getMessage()).toJSONString());
            LOG.error(e);
            return Response
                  .status(Response.Status.BAD_REQUEST)
                  .entity(JSONUtil.getJsonResponse(0, dealerCode, e.getMessage()).toJSONString())
                  .type(ContentTypes.APPLICATION_JSON)
                  .build();
         }

      } catch (NullPointerException e) {
         message = "Unknown file source!";
         LOG.error(JSONUtil.getJsonResponse(0, dealerCode, message).toJSONString());
         LOG.error(e);
         return Response
               .status(Response.Status.BAD_REQUEST)
               .entity(JSONUtil.getJsonResponse(0, dealerCode, message).toJSONString())
               .type(ContentTypes.APPLICATION_JSON)
               .build();
      }

      LOG.info(JSONUtil.getJsonResponse(1, dealerCode, "").toJSONString());
      return Response
            .status(Response.Status.CREATED)
            .entity(JSONUtil.getJsonResponse(1, dealerCode, "").toJSONString())
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }
}
