package com.astra.dealink.rest.api.service.skiris;

import com.astra.dewa.model.SkIris;

import java.util.Date;

public interface SkIrisService {
    SkIris createSkiris(int tahun, String periode, int wilayahId, int dealerId, String kategori,String fileName, long fileId, String filePath, Date date);
    boolean skIrisIsExist(String fileName, int dealerId);

}
