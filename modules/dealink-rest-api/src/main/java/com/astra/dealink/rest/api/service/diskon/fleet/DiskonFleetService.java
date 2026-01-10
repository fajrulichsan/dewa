package com.astra.dealink.rest.api.service.diskon.fleet;

import com.liferay.portal.kernel.exception.PortalException;

public interface DiskonFleetService {
    void createDiskonFleet(String dealerCode, long fileId, String fileName, String filePath, int tahun, String kuartal) throws PortalException;
}
