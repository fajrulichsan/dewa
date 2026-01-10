package com.astra.dewa.web.command.action.upload.service;

import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationHeader;
import com.liferay.portal.kernel.exception.PortalException;

public interface ApplicationAssignService {
   ApplicationAssign createApplicationAssign(ApplicationHeader header, int applicationAssignStatusId, String notes, long profileId, String screenName) throws PortalException;
}
