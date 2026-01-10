package com.astra.dealink.rest.api.service.tipekendaraan;

import com.astra.dewa.model.TipeKendaraan;
import com.astra.dewa.service.TipeKendaraanLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public class TipeKendaraanServiceImpl implements TipeKendaraanService {

   @Override
   public TipeKendaraan createTipeKendaraan(String tipeKendaraanName) throws PortalException {

      Date date = new Date();

      TipeKendaraan tipeKendaraan = TipeKendaraanLocalServiceUtil.createTipeKendaraan(0);
      tipeKendaraan.setName(tipeKendaraanName);
      tipeKendaraan.setCreatedDate(date);
      tipeKendaraan.setCreatedBy("API");
      tipeKendaraan.setModifiedDate(date);
      tipeKendaraan.setModifiedBy("API");
      tipeKendaraan.setRowStatus(true);
      TipeKendaraanLocalServiceUtil.updateTipeKendaraan(tipeKendaraan);
      return tipeKendaraan;

   }

}
