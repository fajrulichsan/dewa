package com.astra.dealink.rest.api.service.faktur.pajak;

import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dewa.model.FakturPajak;
import com.astra.dewa.service.FakturPajakLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FakturPajakServiceImpl implements FakturPajakService {
   @Override
   public FakturPajak createFakturPajak(String dealerCode, long fileId, String fileName, String filePath, Date invoiceDate, Date uploadDate) throws PortalException {
      int dealerId = DealerUtil.getDealerId(dealerCode);
      Date now = new Date();
      FakturPajak fakturPajak = FakturPajakLocalServiceUtil.createFakturPajak(0);

      // fields
      fakturPajak.setDealerId(dealerId);
      fakturPajak.setKeterangan("-");

      // file
      fakturPajak.setFileId(fileId);
      fakturPajak.setFileName(fileName);
      fakturPajak.setFilePath(filePath);

      // date
      fakturPajak.setInvoiceDate(invoiceDate);
      fakturPajak.setUploadDate(uploadDate);

      // audit
      fakturPajak.setCreatedDate(now);
      fakturPajak.setCreatedBy("API");
      fakturPajak.setModifiedDate(now);
      fakturPajak.setModifiedBy("API");
      fakturPajak.setRowStatus(true);

      FakturPajakLocalServiceUtil.updateFakturPajak(fakturPajak);
      return fakturPajak;
   }

   @Override
   public boolean isFakturPajakExist(String dealerCode, String fileName, Date invoiceDate, Date uploadDate) throws PortalException {
      // date only restriction
      Date uploadDateStart = null;
      Date uploadDateEnd = null;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(uploadDate);
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      uploadDateStart = calendar.getTime();
      calendar.add(Calendar.DAY_OF_MONTH, 1);
      uploadDateEnd = calendar.getTime();

      DynamicQuery query = FakturPajakLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("FileName", fileName));
      query.add(RestrictionsFactoryUtil.eq("InvoiceDate", invoiceDate));
      query.add(RestrictionsFactoryUtil.ge("UploadDate", uploadDateStart));
      query.add(RestrictionsFactoryUtil.lt("UploadDate", uploadDateEnd));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<FakturPajak> fakturPajakList = FakturPajakLocalServiceUtil.dynamicQuery(query);
      return !fakturPajakList.isEmpty();
   }
}
