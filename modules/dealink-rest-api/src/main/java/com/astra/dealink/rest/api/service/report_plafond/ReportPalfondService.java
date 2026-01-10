package com.astra.dealink.rest.api.service.report_plafond;

import com.astra.dewa.model.ReportPlafond;

import java.util.Date;

public interface ReportPalfondService {
    ReportPlafond createReportPlafond(int dealerId, long fileId, String fileName, String filePath, Date periode);
    boolean reportPalfondIsExist (String filename, String dealerCode);
}
