package com.astra.dealink.rest.api.service.sales_report;

import com.astra.dewa.model.SalesReport;

import java.util.Date;

public interface SalesReportService {
   SalesReport createSalesReport(int dealerId, long fileId, String fileName, String filePath, Date periode);

   boolean salesReportIsExist(String filename, String dealerCode);
}
