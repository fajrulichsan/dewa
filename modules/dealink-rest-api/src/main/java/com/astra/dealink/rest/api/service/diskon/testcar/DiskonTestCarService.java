package com.astra.dealink.rest.api.service.diskon.testcar;

import com.liferay.portal.kernel.exception.PortalException;

public interface DiskonTestCarService {
    void createDiskonTestCar(String dealerCode, long fileId, String fileName, String filePath, int tahun, String kuartal, String tipeKendaraanName) throws PortalException;
}
