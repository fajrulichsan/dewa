package com.astra.dewa.web.command.action.upload.service;

import com.astra.dewa.model.ApplicationHeader;
import com.liferay.portal.kernel.exception.PortalException;

public interface ApplicationHeaderJournalService {
   void createApplicationHeaderJournal(ApplicationHeader header, String screenName) throws PortalException;
}
