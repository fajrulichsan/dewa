package com.astra.dewa.web.command.action;

import com.astra.dewa.model.ReportPlafond;
import com.astra.dewa.service.ReportPlafondLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.LogUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
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

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * @author psmafifd1401
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND,
            "mvc.command.name=/report-plafond/action"
      },
      service = MVCResourceCommand.class
)
public class ReportPlafondActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(ReportPlafondActionCommand.class);
   private User user;
   private String message;

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
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject;
      if (isRequestContainsXSS) {
         jsonObject = ERROR("Your request contains XSS payload.");
      } else {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
         long fileId = ParamUtil.getLong(uploadPortletRequest, "reportPlafondFileId");
         String originalFileName = ParamUtil.getString(uploadPortletRequest, "reportPlafondFileName");
         String newFileName;
         String filePath = ParamUtil.getString(uploadPortletRequest, "reportPlafondFilePath");
         String period = ParamUtil.getString(uploadPortletRequest, "periode");
         Date periodInDateFormat;

         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
         ReportPlafond reportPlafond;
         switch (crudType) {
            case CREATE:
               periodInDateFormat = DateUtil.stringToDate(period);
               newFileName = setNewFileName(originalFileName, periodInDateFormat, dealerId);
               reportPlafond = ReportPlafondLocalServiceUtil.createReportPlafond(entryId);
               reportPlafond.setDealerId(dealerId);
               reportPlafond.setFileId(fileId);
               reportPlafond.setFileName(newFileName);
               reportPlafond.setFilePath(filePath);
               reportPlafond.setPeriode(periodInDateFormat);
               jsonObject = create(reportPlafond);
               break;
            case UPDATE:
               periodInDateFormat = DateUtil.stringToDate(period);
               newFileName = setNewFileName(originalFileName, periodInDateFormat, dealerId);
               reportPlafond = ReportPlafondLocalServiceUtil.getReportPlafond(entryId);
               reportPlafond.setDealerId(dealerId);
               reportPlafond.setFileId(fileId);
               reportPlafond.setFileName(newFileName);
               reportPlafond.setFilePath(filePath);
               reportPlafond.setPeriode(periodInDateFormat);
               jsonObject = update(reportPlafond);
               break;
            case DELETE:
               reportPlafond = ReportPlafondLocalServiceUtil.getReportPlafond(entryId);
               jsonObject = delete(reportPlafond);
               break;
            default:
               jsonObject = NOT_SUCCESS("Bad request");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(ReportPlafond reportPlafond) {
      try {
         if (isExist(reportPlafond.getId(), reportPlafond.getDealerId(), reportPlafond.getPeriode())) {
            this.message = "Dokumen dengan nama ini sudah terdaftar";
            LogUtil.logStatus(
                  0,
                  reportPlafond.getId(),
                  CREATE,
                  DewaWebKeys.REPORT_PLAFOND,
                  reportPlafond.getDealerId(),
                  reportPlafond.getFileName(),
                  message
            );
            return WARNING(message);
         }

         Date now = new Date();
         reportPlafond.setKeterangan("-");
         reportPlafond.setRowStatus(true);
         reportPlafond.setCreatedDate(now);
         reportPlafond.setCreatedBy(user.getScreenName());
         reportPlafond.setModifiedDate(now);
         reportPlafond.setModifiedBy(user.getScreenName());
         ReportPlafondLocalServiceUtil.updateReportPlafond(reportPlafond);

         LogUtil.logStatus(
               1,
               reportPlafond.getId(),
               CREATE,
               DewaWebKeys.REPORT_PLAFOND,
               reportPlafond.getDealerId(),
               reportPlafond.getFileName(),
               message
         );

         return SUCCESS("Data tersimpan", String.valueOf(reportPlafond.getId()));
      } catch (SystemException se) {
         LOG.error("Gagal menambah data: " + se.getMessage());
         return NOT_SUCCESS(se.getMessage());
      }
   }

   private JSONObject update(ReportPlafond reportPlafond) {
      try {
         if (isExist(reportPlafond.getId(), reportPlafond.getDealerId(), reportPlafond.getPeriode())) {
            this.message = "Dokumen dengan nama ini sudah terdaftar";
            LogUtil.logStatus(
                  0,
                  reportPlafond.getId(),
                  CREATE,
                  DewaWebKeys.REPORT_PLAFOND,
                  reportPlafond.getDealerId(),
                  reportPlafond.getFileName(),
                  message
            );
            return WARNING(message);
         }

         reportPlafond.setKeterangan("-");
         reportPlafond.setModifiedDate(new Date());
         reportPlafond.setModifiedBy(user.getScreenName());
         ReportPlafondLocalServiceUtil.updateReportPlafond(reportPlafond);

         LogUtil.logStatus(
               1,
               reportPlafond.getId(),
               UPDATE,
               DewaWebKeys.REPORT_PLAFOND,
               reportPlafond.getDealerId(),
               reportPlafond.getFileName(),
               message
         );

         return SUCCESS("Data terupdate", String.valueOf(reportPlafond.getId()));
      } catch (SystemException se) {
         LOG.error("Gagal memperbarui data: " + se.getMessage());
         return NOT_SUCCESS(se.getMessage());
      }
   }

   private JSONObject delete(ReportPlafond reportPlafond) {
      try {
         reportPlafond.setRowStatus(false);
         ReportPlafondLocalServiceUtil.updateReportPlafond(reportPlafond);

         LogUtil.logStatus(
               1,
               reportPlafond.getId(),
               DELETE,
               DewaWebKeys.REPORT_PLAFOND,
               reportPlafond.getDealerId(),
               reportPlafond.getFileName(),
               message
         );

         return SUCCESS("Data terupdate", String.valueOf(reportPlafond.getId()));
      } catch (SystemException se) {
         LOG.error("Gagal menghapus data: " + se.getMessage());
         return NOT_SUCCESS(se.getMessage());
      }
   }

   private boolean isExist(int entryId, int dealerId, Date periode) {
      DynamicQuery query = ReportPlafondLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("Periode", periode));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<ReportPlafond> reportPlafonds = ReportPlafondLocalServiceUtil.dynamicQuery(query);
      return !reportPlafonds.isEmpty() && !(reportPlafonds.get(0).getId() == entryId);
   }

   private String setNewFileName(String originalFileName, Date period, int dealerId) {
      String dealerCode = DealerUtils.getDealerCode(dealerId);
      String periodInDotFormat = DateUtil.dateToString(period, DewaWebKeys.DATE_FORMAT_DOT);
      @SuppressWarnings("OptionalGetWithoutIsPresent")
      String newFileName = DewaWebKeys.REPORT_PLAFOND + "-" + periodInDotFormat + "-" + dealerCode + "." + FileUtil.getExtensionByStringHandling(originalFileName).get();
      return newFileName;
   }
}