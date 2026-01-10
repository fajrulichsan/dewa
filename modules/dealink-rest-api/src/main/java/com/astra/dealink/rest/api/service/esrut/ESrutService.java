package com.astra.dealink.rest.api.service.esrut;

import com.astra.dewa.model.ESrut;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

public interface ESrutService {
   ESrut createESrut(String dealerCode, long fileId, String fileName, String filePath, Date period) throws PortalException;
   boolean isESrutExist(String dealerCode, String fileName, Date uploadDate) throws PortalException;
}
