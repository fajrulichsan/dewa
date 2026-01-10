package com.astra.dealink.rest.api.service.esrut;

import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dewa.model.ESrut;
import com.astra.dewa.service.ESrutLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;
import java.util.List;

public class ESrutServiceImpl implements ESrutService {
   @Override
   public ESrut createESrut(String dealerCode, long fileId, String fileName, String filePath, Date periodDate) throws PortalException {
      int dealerId = DealerUtil.getDealerId(dealerCode);
      Date currentDate = new Date();
      ESrut eSrut = ESrutLocalServiceUtil.createESrut(0);
      eSrut.setDealerId(dealerId);
      eSrut.setFileId(fileId);
      eSrut.setFileName(fileName);
      eSrut.setFilePath(filePath);
      eSrut.setPeriodDate(periodDate);
      eSrut.setKeterangan("-");
      eSrut.setCreatedDate(currentDate);
      eSrut.setCreatedBy("API");
      eSrut.setModifiedDate(currentDate);
      eSrut.setModifiedBy("API");
      eSrut.setRowStatus(true);
      ESrutLocalServiceUtil.updateESrut(eSrut);
      return eSrut;
   }

   @Override
   public boolean isESrutExist(String dealerCode, String fileName, Date uploadDate) throws PortalException {
      int dealerId = DealerUtil.getDealerId(dealerCode);
      DynamicQuery query = ESrutLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
      query.add(RestrictionsFactoryUtil.eq("FileName", fileName));
      query.add(RestrictionsFactoryUtil.eq("PeriodDate", uploadDate));
      List<ESrut> eSrutList = ESrutLocalServiceUtil.dynamicQuery(query);
      return eSrutList.isEmpty();
   }
}
