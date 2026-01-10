package com.astra.dewa.cms.command.action.upload.service;

import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationHeader;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

public interface ApplicationAssignService {
   ApplicationAssign createApplicationAssign(ApplicationHeader header, int applicationAssignStatusId, User user) throws PortalException;
   ApplicationAssign updateApplicationAssign(ApplicationAssign applicationAssign, int applicationAssignStatusId, String notes, long profileId, String screenName) throws PortalException;
}
