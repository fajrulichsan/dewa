package com.astra.dealink.rest.api.service.diskon.fleet;

import com.astra.dewa.model.DiskonFleet;
import com.astra.dealink.rest.api.util.BulanUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dewa.service.DiskonFleetLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public class DiskonFleetServiceImpl implements DiskonFleetService {
    @Override
    public void createDiskonFleet(String dealerCode, long fileId, String fileName, String filePath, int tahun, String kuartal) throws PortalException {
        int dealerId = DealerUtil.getDealerId(dealerCode);
        String kuartalId = BulanUtil.getKuartalId(kuartal);
        Date now = new Date();
        DiskonFleet diskonFleet = DiskonFleetLocalServiceUtil.createDiskonFleet(0);
        diskonFleet.setDealerId(dealerId);
        diskonFleet.setTahun(tahun);
        diskonFleet.setKuartal(kuartalId);
        diskonFleet.setKeterangan("-");
        diskonFleet.setFileId(fileId);
        diskonFleet.setFileName(fileName);
        diskonFleet.setFilePath(filePath);
        diskonFleet.setCreatedDate(now);
        diskonFleet.setCreatedBy("API");
        diskonFleet.setModifiedDate(now);
        diskonFleet.setModifiedBy("API");
        diskonFleet.setRowStatus(true);
        DiskonFleetLocalServiceUtil.updateDiskonFleet(diskonFleet);
    }
}
