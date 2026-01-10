package com.astra.dealink.rest.api.service.sp3d;

import com.astra.dewa.model.SP3D;
import com.astra.dealink.rest.api.util.DealerUtil;
import com.astra.dewa.service.SP3DLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.Date;
import java.util.List;

public class Sp3dServiceImpl implements Sp3dService{
    @Override
    public SP3D createSp3d(String dealerCode,String fileName, long fileId, String filePath, int year,String bulan, Date convertDate) {
        Date currentDate = new Date();
        SP3D sp3d = null;
        sp3d = SP3DLocalServiceUtil.createSP3D(0);
        sp3d.setTahun(year);
        sp3d.setBulan(bulan);
        sp3d.setDealerId(DealerUtil.getDealerId(dealerCode));
//        sp3d.setDealerName(DealerUtil.getDealerName(dealerCode));
        sp3d.setFileName(fileName);
        sp3d.setFileId(fileId);
        sp3d.setFilePath(filePath);
        sp3d.setCreatedDate(convertDate);
        sp3d.setRowStatus(true);
        sp3d.setCreatedBy("API");
        sp3d.setModifiedDate(currentDate);
        sp3d.setModifiedBy("API");
        SP3DLocalServiceUtil.updateSP3D(sp3d);
        return sp3d;
    }

    @Override
    public boolean sp3dIsExist(String fileName,int dealerId) {
        DynamicQuery query = SP3DLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        query.add(RestrictionsFactoryUtil.eq("DealerId", dealerId));
        query.add(RestrictionsFactoryUtil.eq("FileName", fileName));
        List<SP3D> sp3d = SP3DLocalServiceUtil.dynamicQuery(query);
        if(sp3d.isEmpty()){
            return false;
        }
        return true;
    }
}
