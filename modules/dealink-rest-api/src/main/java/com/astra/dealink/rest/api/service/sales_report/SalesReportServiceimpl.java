package com.astra.dealink.rest.api.service.sales_report;

import com.astra.dewa.model.SalesReport;
import com.astra.dewa.service.SalesReportLocalServiceUtil;
import com.astra.dewa.utils.DealerUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.Date;
import java.util.List;

public class SalesReportServiceimpl implements SalesReportService {
   @Override
   public SalesReport createSalesReport(int dealerId, long fileId, String fileName, String filePath, Date periode) {
      Date currentDate = new Date();
      SalesReport salesReport = SalesReportLocalServiceUtil.createSalesReport(0);
      salesReport.setDealerId(dealerId);
      salesReport.setFileId(fileId);
      salesReport.setFileName(fileName);
      salesReport.setFilePath(filePath);
      salesReport.setPeriode(periode);
      salesReport.setKeterangan("-");
      salesReport.setCreatedDate(currentDate);
      salesReport.setCreatedBy("API");
      salesReport.setModifiedDate(currentDate);
      salesReport.setModifiedBy("API");
      salesReport.setRowStatus(true);
      SalesReportLocalServiceUtil.updateSalesReport(salesReport);
      return salesReport;
   }

   @Override
   public boolean salesReportIsExist(String filename, String dealerCode) {
      DynamicQuery query = SalesReportLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("DealerId", DealerUtils.getDealerIdByCode(dealerCode)));
      query.add(RestrictionsFactoryUtil.eq("FileName", filename));
      List<SalesReport> salesReports = SalesReportLocalServiceUtil.dynamicQuery(query);
      return !salesReports.isEmpty();
   }
}
