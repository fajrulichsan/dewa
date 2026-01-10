package com.astra.dealink.rest.api.service.report_plafond;

import com.astra.dewa.model.ReportPlafond;
import com.astra.dewa.service.ReportPlafondLocalServiceUtil;
import com.astra.dewa.utils.DealerUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.Date;
import java.util.List;

public class ReportPalfondserviceImpl implements ReportPalfondService {
   @Override
   public ReportPlafond createReportPlafond(int dealerId, long fileId, String fileName, String filePath, Date periode) {
      Date currentDate = new Date();
      ReportPlafond reportPlafond = ReportPlafondLocalServiceUtil.createReportPlafond(0);
      reportPlafond.setDealerId(dealerId);
      reportPlafond.setFileId(fileId);
      reportPlafond.setFileName(fileName);
      reportPlafond.setFilePath(filePath);
      reportPlafond.setPeriode(periode);
      reportPlafond.setKeterangan("-");
      reportPlafond.setCreatedDate(currentDate);
      reportPlafond.setCreatedBy("API");
      reportPlafond.setModifiedDate(currentDate);
      reportPlafond.setModifiedBy("API");
      reportPlafond.setRowStatus(true);
      ReportPlafondLocalServiceUtil.updateReportPlafond(reportPlafond);
      return reportPlafond;
   }

   @Override
   public boolean reportPalfondIsExist(String filename, String dealerCode) {
      DynamicQuery query = ReportPlafondLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("DealerId", DealerUtils.getDealerIdByCode(dealerCode)));
      query.add(RestrictionsFactoryUtil.eq("FileName", filename));
      List<ReportPlafond> reportPlafonds = ReportPlafondLocalServiceUtil.dynamicQuery(query);
      return !reportPlafonds.isEmpty();
   }
}
