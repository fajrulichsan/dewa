package com.astra.dealink.rest.api.service.diskon.fakpol;

import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.service.DiskonFakpolLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public class DiskonFakpolServiceImpl implements DiskonFakpolService {
    @Override
    public void createDiskonFakpol(String dealerCode, long fileId, String fileName, String filePath, int tahun, String bulan) throws PortalException {
        int dealerId = DealerUtil.getDealerId(dealerCode);
        Date now = new Date();
        DiskonFakpol diskonFakpol = DiskonFakpolLocalServiceUtil.createDiskonFakpol(0);
        diskonFakpol.setDealerId(dealerId);
        diskonFakpol.setTahun(tahun);
        diskonFakpol.setPeriode(bulan);
        diskonFakpol.setKeterangan("-");
        diskonFakpol.setFileId(fileId);
        diskonFakpol.setFileName(fileName);
        diskonFakpol.setFilePath(filePath);
        diskonFakpol.setCreatedDate(now);
        diskonFakpol.setCreatedBy("API");
        diskonFakpol.setModifiedDate(now);
        diskonFakpol.setModifiedBy("API");
        diskonFakpol.setRowStatus(true);
        DiskonFakpolLocalServiceUtil.updateDiskonFakpol(diskonFakpol);
    }
}
