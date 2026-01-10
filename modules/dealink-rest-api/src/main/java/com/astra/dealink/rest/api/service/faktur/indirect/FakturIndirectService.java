package com.astra.dealink.rest.api.service.faktur.indirect;

import com.astra.dewa.model.FakturIndirect;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public interface FakturIndirectService {
   FakturIndirect createFakturIndirect(String dealerCode, long fileId, String fileName, String filePath, Date invoiceDate, Date uploadDate) throws PortalException;
   boolean isFakturIndirectExist(String dealerCode, String fileName, Date invoiceDate, Date uploadDate) throws PortalException;
}
