package com.astra.dealink.rest.api.service.sp3d;

import com.astra.dewa.model.SP3D;

import java.util.Date;

public interface Sp3dService {
    SP3D createSp3d(String dealerCode,String fileName, long fileId, String filePath, int year,String bulan, Date convertDate );
    boolean sp3dIsExist(String fileName,int dealerId);
}