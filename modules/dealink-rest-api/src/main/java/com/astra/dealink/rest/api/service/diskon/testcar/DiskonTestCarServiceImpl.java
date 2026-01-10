package com.astra.dealink.rest.api.service.diskon.testcar;

import com.astra.dewa.model.DiskonTestCar;
import com.astra.dealink.rest.api.util.BulanUtil;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dealink.rest.api.util.TipeKendaraanUtil;
import com.astra.dewa.service.DiskonTestCarLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public class DiskonTestCarServiceImpl implements DiskonTestCarService {
    @Override
    public void createDiskonTestCar(String dealerCode, long fileId, String fileName, String filePath, int tahun, String kuartal, String tipeKendaraanName) throws PortalException {
        int dealerId = DealerUtil.getDealerId(dealerCode);
        String kuartalId = BulanUtil.getKuartalId(kuartal);
        int tipeKendaraanId = TipeKendaraanUtil.getTipeKendaraanId(tipeKendaraanName);
        Date now = new Date();
        DiskonTestCar diskonTestCar = DiskonTestCarLocalServiceUtil.createDiskonTestCar(0);
        diskonTestCar.setDealerId(dealerId);
        diskonTestCar.setTahun(tahun);
        diskonTestCar.setKuartalId(kuartalId);
        diskonTestCar.setTipeKendaraanId(tipeKendaraanId);
        diskonTestCar.setKeterangan("-");
        diskonTestCar.setFileId(fileId);
        diskonTestCar.setFileName(fileName);
        diskonTestCar.setFilePath(filePath);
        diskonTestCar.setCreatedDate(now);
        diskonTestCar.setCreatedBy("API");
        diskonTestCar.setModifiedDate(now);
        diskonTestCar.setModifiedBy("API");
        diskonTestCar.setRowStatus(true);
        DiskonTestCarLocalServiceUtil.updateDiskonTestCar(diskonTestCar);
    }
}
