package com.astra.dealink.rest.api.service.skiris;

import com.astra.dewa.model.SkIris;
import com.astra.dewa.service.SkIrisLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.Date;
import java.util.List;

public class SkirisServiceImpl implements SkIrisService {
    @Override
    public SkIris createSkiris(int tahun, String periode, int wilayahId, int dealerId, String kategori,String fileName, long fileId, String filePath, Date date) {
        Date currentDate = new Date();

        SkIris skIris = null;
        skIris = SkIrisLocalServiceUtil.createSkIris(0);
        skIris.setTahun(tahun);
        skIris.setPeriode(periode);
        skIris.setWilayahId(wilayahId);
        skIris.setDealerId(dealerId);
        skIris.setKategori(kategori);
        skIris.setFileName(fileName);
        skIris.setFileId(fileId);
        skIris.setFilePath(filePath);
        skIris.setRowStatus(true);
        skIris.setCreatedDate(date);
        skIris.setCreatedBy("API");
        skIris.setModifiedDate(currentDate);
        skIris.setModifiedBy("API");
        SkIrisLocalServiceUtil.updateSkIris(skIris);

        return skIris;
    }

    @Override
    public boolean skIrisIsExist(String fileName, int dealerId) {
        DynamicQuery query = SkIrisLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
        query.add(RestrictionsFactoryUtil.eq("FileName", fileName));
        List<SkIris> skIris = SkIrisLocalServiceUtil.dynamicQuery(query);
        if(skIris.isEmpty()){
            return false;
        }
        return true;
    }
}
