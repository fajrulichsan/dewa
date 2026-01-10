package com.astra.dealink.rest.api.service.faktur.pajak;

import com.astra.dewa.model.FakturPajak;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public interface FakturPajakService {
   FakturPajak createFakturPajak(String dealerCode, long fileId, String fileName, String filePath, Date invoiceDate, Date uploadDate) throws PortalException;
   boolean isFakturPajakExist(String dealerCode, String fileName, Date invoiceDate, Date uploadDate) throws PortalException;
}
