package com.astra.dewa.web.command.action.sales.report;

import com.astra.dewa.model.SalesReport;
import com.astra.dewa.service.SalesReportLocalServiceUtil;
import com.astra.dewa.utils.CRUDActionKeys;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.LogUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT,
            "mvc.command.name=/sales-report/action"
      },
      service = MVCResourceCommand.class
)
public class SalesReportActionCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(SalesReportActionCommand.class);
   private UploadPortletRequest uploadPortletRequest;
   private User user;
   private int entryId;
   private String fileName;
   private int dealerId;
   private String message;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      this.user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      if (isRequestContainsXSS) {
         jsonObject = ERROR("Your request contains XSS payload.");
      } else {
         this.entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         this.dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
         long fileId = ParamUtil.getLong(uploadPortletRequest, "salesReportFileId");
         this.fileName = ParamUtil.getString(uploadPortletRequest, "salesReportFileName");
         String filePath = ParamUtil.getString(uploadPortletRequest, "salesReportFilePath");
         String periode = ParamUtil.getString(uploadPortletRequest, "periode");
         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");

         Date date = new Date();
         Date periodDate = null;

         if (!crudType.equalsIgnoreCase(DELETE)) {
            periodDate = DateUtil.stringToDate(periode, DewaWebKeys.DATE_FORMAT_DOT);
         }

         SalesReport salesReport;

         boolean isFileExist = isExist(dealerId, periodDate);

         if (crudType.equalsIgnoreCase(CREATE)) {
            salesReport = SalesReportLocalServiceUtil.createSalesReport(entryId);
         } else {
            salesReport = SalesReportLocalServiceUtil.getSalesReport(entryId);
         }

         if (crudType.equalsIgnoreCase(DELETE)) {
            salesReport.setFileName(fileName);
         } else {
            String dealerCode = DealerUtils.getDealerCode(dealerId);
            String newFileName = DewaWebKeys.SALES_REPORT + "-" + DateUtil.dateToString(periodDate) + "-" + dealerCode + "." + FileUtil.getExtensionByStringHandling(fileName).get();
            salesReport.setFileName(newFileName);
         }

         salesReport.setDealerId(dealerId);
         salesReport.setFileId(fileId);
         salesReport.setPeriode(periodDate);
         salesReport.setFilePath(filePath);
         salesReport.setKeterangan("-");

         if (crudType.equalsIgnoreCase(DELETE)) {
            jsonObject = delete();
         } else if (crudType.equalsIgnoreCase(CREATE)) {
            jsonObject = create(salesReport, date, isFileExist, entryId, fileName);
         } else if (crudType.equalsIgnoreCase(UPDATE)) {
            jsonObject = update(salesReport, date, isFileExist, fileId, entryId, fileName);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(SalesReport salesReport, Date date, boolean isFileExist, int entryId, String fileName) {
      try {
         if (isFileExist) {
            message = "You are not allowed to upload more than one file within a single region, year, periode and same category.";
            LogUtil.logStatus(0, entryId, CRUDActionKeys.CREATE, DewaWebKeys.SALES_REPORT, dealerId, fileName, message);
            return WARNING(message);
         }

         salesReport.setRowStatus(true);
         salesReport.setCreatedDate(date);
         salesReport.setCreatedBy(user.getScreenName());
         salesReport.setModifiedDate(date);
         salesReport.setModifiedBy(user.getScreenName());
         SalesReportLocalServiceUtil.updateSalesReport(salesReport);

         // FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, DewaWebKeys.SALES_REPORT);

         LogUtil.logStatus(1, entryId, CRUDActionKeys.CREATE, DewaWebKeys.SALES_REPORT, dealerId, fileName, message);
         return SUCCESS("Data tersimpan", String.valueOf(salesReport.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(SalesReport salesReport, Date date, boolean isFileExist, long fileId, int entryId, String fileName) {
      try {
         if (isFileExist) {
            message = "You are not allowed to upload more than one file within a single region, year, periode and same category.";
            LogUtil.logStatus(0, entryId, CRUDActionKeys.UPDATE, DewaWebKeys.SALES_REPORT, dealerId, fileName, message);
            return WARNING(message);
         }

         // FileUtil.moveIntoMenuFolder(themeDisplay, resourceRequest, serviceContext, fileId, CmsKeys.SALES_REPORT);

         salesReport.setModifiedDate(date);
         salesReport.setModifiedBy(user.getScreenName());
         SalesReportLocalServiceUtil.updateSalesReport(salesReport);
         LogUtil.logStatus(1, entryId, CRUDActionKeys.UPDATE, DewaWebKeys.SALES_REPORT, dealerId, fileName, message);
         return SUCCESS("Data terupdate.", String.valueOf(salesReport.getId()));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete() {
      try {
         int entryId = ParamUtil.getInteger(uploadPortletRequest, "entryId");
         SalesReport salesReport = SalesReportLocalServiceUtil.getSalesReport(entryId);
         salesReport.setRowStatus(false);
         SalesReportLocalServiceUtil.updateSalesReport(salesReport);
         LogUtil.logStatus(1, entryId, CRUDActionKeys.DELETE, DewaWebKeys.SALES_REPORT, dealerId, fileName, message);
         return SUCCESS("Data terupdate.", String.valueOf(entryId));
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private boolean isExist(int dealerId, Date periode) {
      DynamicQuery query = SalesReportLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("Periode", periode));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<SalesReport> salesReports = SalesReportLocalServiceUtil.dynamicQuery(query);
      if (!salesReports.isEmpty()) {
         return !(salesReports.get(0).getId() == entryId);
      }
      return false;
   }

}
