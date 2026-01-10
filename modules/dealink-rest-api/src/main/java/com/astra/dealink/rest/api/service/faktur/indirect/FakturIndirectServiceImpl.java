package com.astra.dealink.rest.api.service.faktur.indirect;

import com.astra.dewa.model.FakturIndirect;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dewa.service.FakturIndirectLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FakturIndirectServiceImpl implements FakturIndirectService {
   @Override
   public FakturIndirect createFakturIndirect(String dealerCode, long fileId, String fileName, String filePath, Date invoiceDate, Date uploadDate) throws PortalException {
      try {
         int dealerId = DealerUtil.getDealerId(dealerCode);
         Date now = new Date();

         FakturIndirect fakturIndirect = FakturIndirectLocalServiceUtil.createFakturIndirect(0);

         // field
         fakturIndirect.setDealerId(dealerId);
         fakturIndirect.setKeterangan("-");

         // date
         fakturIndirect.setInvoiceDate(invoiceDate);
         fakturIndirect.setUploadDate(uploadDate);

         // file
         fakturIndirect.setFileId(fileId);
         fakturIndirect.setFileName(fileName);
         fakturIndirect.setFilePath(filePath);

         // audit
         fakturIndirect.setCreatedDate(now);
         fakturIndirect.setCreatedBy("API");
         fakturIndirect.setModifiedDate(now);
         fakturIndirect.setModifiedBy("API");
         fakturIndirect.setRowStatus(true);
         FakturIndirectLocalServiceUtil.updateFakturIndirect(fakturIndirect);
         return fakturIndirect;
      } catch (Exception e) {
         throw new PortalException();
      }
   }

   @Override
   public boolean isFakturIndirectExist(String dealerCode, String fileName, Date invoiceDate, Date uploadDate) throws PortalException {
      try {
         // date only restriction
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(uploadDate);
         calendar.set(Calendar.HOUR_OF_DAY, 0);
         calendar.set(Calendar.MINUTE, 0);
         calendar.set(Calendar.SECOND, 0);
         Date uploadDateStart = calendar.getTime();
         calendar.add(Calendar.DAY_OF_MONTH, 1);
         Date uploadDateEnd = calendar.getTime();

         int dealerId = DealerUtil.getDealerId(dealerCode);

         DynamicQuery query = FakturIndirectLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("FileName", fileName));
         query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
         query.add(RestrictionsFactoryUtil.eq("InvoiceDate", invoiceDate));
         query.add(RestrictionsFactoryUtil.ge("UploadDate", uploadDateStart));
         query.add(RestrictionsFactoryUtil.lt("UploadDate", uploadDateEnd));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<FakturIndirect> fakturIndirectList = FakturIndirectLocalServiceUtil.dynamicQuery(query);
         return !fakturIndirectList.isEmpty();
      } catch (Exception e) {
         throw new PortalException();
      }
   }
}