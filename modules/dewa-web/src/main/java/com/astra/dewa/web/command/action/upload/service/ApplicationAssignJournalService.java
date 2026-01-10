package com.astra.dewa.web.command.action.upload.service;

import com.astra.dewa.model.ApplicationAssign;
import com.liferay.portal.kernel.exception.PortalException;

public interface ApplicationAssignJournalService {
   void createApplicationAssignJournal(ApplicationAssign assign) throws PortalException;
}
