package com.astra.dealink.rest.api.service.tipekendaraan;

import com.astra.dewa.model.TipeKendaraan;
import com.liferay.portal.kernel.exception.PortalException;

public interface TipeKendaraanService {

   TipeKendaraan createTipeKendaraan(String tipeKendaraanName) throws PortalException;

}
