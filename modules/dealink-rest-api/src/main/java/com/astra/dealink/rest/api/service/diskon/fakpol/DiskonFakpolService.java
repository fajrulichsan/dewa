package com.astra.dealink.rest.api.service.diskon.fakpol;

import com.liferay.portal.kernel.exception.PortalException;

public interface DiskonFakpolService {
    void createDiskonFakpol(String dealerCode, long fileId, String fileName, String filePath, int tahun, String bulan) throws PortalException;
}
