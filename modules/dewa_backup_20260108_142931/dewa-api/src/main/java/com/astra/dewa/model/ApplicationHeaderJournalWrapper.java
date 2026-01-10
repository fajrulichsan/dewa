/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.astra.dewa.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ApplicationHeaderJournal}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderJournal
 * @generated
 */
public class ApplicationHeaderJournalWrapper
	extends BaseModelWrapper<ApplicationHeaderJournal>
	implements ApplicationHeaderJournal,
			   ModelWrapper<ApplicationHeaderJournal> {

	public ApplicationHeaderJournalWrapper(
		ApplicationHeaderJournal applicationHeaderJournal) {

		super(applicationHeaderJournal);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("ApplicationHeaderId", getApplicationHeaderId());
		attributes.put("ApplicationId", getApplicationId());
		attributes.put(
			"ApplicationHeaderStatusId", getApplicationHeaderStatusId());
		attributes.put("ApplicationCategoryId", getApplicationCategoryId());
		attributes.put("DealerId", getDealerId());
		attributes.put("TicketNo", getTicketNo());
		attributes.put("TicketCode", getTicketCode());
		attributes.put("ReqDate", getReqDate());
		attributes.put("ReqYear", getReqYear());
		attributes.put("ReqMonth", getReqMonth());
		attributes.put("ReqScreenName", getReqScreenName());
		attributes.put("ReqName", getReqName());
		attributes.put("ReqEmail", getReqEmail());
		attributes.put("ReqCCEmail", getReqCCEmail());
		attributes.put("ReqPhone", getReqPhone());
		attributes.put("NominalPengajuan", getNominalPengajuan());
		attributes.put("ReqDesc", getReqDesc());
		attributes.put("BusinessBenefit", getBusinessBenefit());
		attributes.put("FileId", getFileId());
		attributes.put("FileName", getFileName());
		attributes.put("FileUrl", getFileUrl());
		attributes.put("Notes", getNotes());
		attributes.put("NotesHistory", getNotesHistory());
		attributes.put("RowStatus", getRowStatus());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("ModifiedBy", getModifiedBy());
		attributes.put("ModifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer Id = (Integer)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		Integer ApplicationHeaderId = (Integer)attributes.get(
			"ApplicationHeaderId");

		if (ApplicationHeaderId != null) {
			setApplicationHeaderId(ApplicationHeaderId);
		}

		Integer ApplicationId = (Integer)attributes.get("ApplicationId");

		if (ApplicationId != null) {
			setApplicationId(ApplicationId);
		}

		Integer ApplicationHeaderStatusId = (Integer)attributes.get(
			"ApplicationHeaderStatusId");

		if (ApplicationHeaderStatusId != null) {
			setApplicationHeaderStatusId(ApplicationHeaderStatusId);
		}

		Integer ApplicationCategoryId = (Integer)attributes.get(
			"ApplicationCategoryId");

		if (ApplicationCategoryId != null) {
			setApplicationCategoryId(ApplicationCategoryId);
		}

		Integer DealerId = (Integer)attributes.get("DealerId");

		if (DealerId != null) {
			setDealerId(DealerId);
		}

		String TicketNo = (String)attributes.get("TicketNo");

		if (TicketNo != null) {
			setTicketNo(TicketNo);
		}

		String TicketCode = (String)attributes.get("TicketCode");

		if (TicketCode != null) {
			setTicketCode(TicketCode);
		}

		Date ReqDate = (Date)attributes.get("ReqDate");

		if (ReqDate != null) {
			setReqDate(ReqDate);
		}

		Integer ReqYear = (Integer)attributes.get("ReqYear");

		if (ReqYear != null) {
			setReqYear(ReqYear);
		}

		Integer ReqMonth = (Integer)attributes.get("ReqMonth");

		if (ReqMonth != null) {
			setReqMonth(ReqMonth);
		}

		String ReqScreenName = (String)attributes.get("ReqScreenName");

		if (ReqScreenName != null) {
			setReqScreenName(ReqScreenName);
		}

		String ReqName = (String)attributes.get("ReqName");

		if (ReqName != null) {
			setReqName(ReqName);
		}

		String ReqEmail = (String)attributes.get("ReqEmail");

		if (ReqEmail != null) {
			setReqEmail(ReqEmail);
		}

		String ReqCCEmail = (String)attributes.get("ReqCCEmail");

		if (ReqCCEmail != null) {
			setReqCCEmail(ReqCCEmail);
		}

		String ReqPhone = (String)attributes.get("ReqPhone");

		if (ReqPhone != null) {
			setReqPhone(ReqPhone);
		}

		Integer NominalPengajuan = (Integer)attributes.get("NominalPengajuan");

		if (NominalPengajuan != null) {
			setNominalPengajuan(NominalPengajuan);
		}

		String ReqDesc = (String)attributes.get("ReqDesc");

		if (ReqDesc != null) {
			setReqDesc(ReqDesc);
		}

		String BusinessBenefit = (String)attributes.get("BusinessBenefit");

		if (BusinessBenefit != null) {
			setBusinessBenefit(BusinessBenefit);
		}

		Long FileId = (Long)attributes.get("FileId");

		if (FileId != null) {
			setFileId(FileId);
		}

		String FileName = (String)attributes.get("FileName");

		if (FileName != null) {
			setFileName(FileName);
		}

		String FileUrl = (String)attributes.get("FileUrl");

		if (FileUrl != null) {
			setFileUrl(FileUrl);
		}

		String Notes = (String)attributes.get("Notes");

		if (Notes != null) {
			setNotes(Notes);
		}

		String NotesHistory = (String)attributes.get("NotesHistory");

		if (NotesHistory != null) {
			setNotesHistory(NotesHistory);
		}

		Boolean RowStatus = (Boolean)attributes.get("RowStatus");

		if (RowStatus != null) {
			setRowStatus(RowStatus);
		}

		String CreatedBy = (String)attributes.get("CreatedBy");

		if (CreatedBy != null) {
			setCreatedBy(CreatedBy);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		String ModifiedBy = (String)attributes.get("ModifiedBy");

		if (ModifiedBy != null) {
			setModifiedBy(ModifiedBy);
		}

		Date ModifiedDate = (Date)attributes.get("ModifiedDate");

		if (ModifiedDate != null) {
			setModifiedDate(ModifiedDate);
		}
	}

	@Override
	public ApplicationHeaderJournal cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application category ID of this application header journal.
	 *
	 * @return the application category ID of this application header journal
	 */
	@Override
	public int getApplicationCategoryId() {
		return model.getApplicationCategoryId();
	}

	/**
	 * Returns the application header ID of this application header journal.
	 *
	 * @return the application header ID of this application header journal
	 */
	@Override
	public int getApplicationHeaderId() {
		return model.getApplicationHeaderId();
	}

	/**
	 * Returns the application header status ID of this application header journal.
	 *
	 * @return the application header status ID of this application header journal
	 */
	@Override
	public int getApplicationHeaderStatusId() {
		return model.getApplicationHeaderStatusId();
	}

	/**
	 * Returns the application ID of this application header journal.
	 *
	 * @return the application ID of this application header journal
	 */
	@Override
	public int getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the business benefit of this application header journal.
	 *
	 * @return the business benefit of this application header journal
	 */
	@Override
	public String getBusinessBenefit() {
		return model.getBusinessBenefit();
	}

	/**
	 * Returns the created by of this application header journal.
	 *
	 * @return the created by of this application header journal
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this application header journal.
	 *
	 * @return the created date of this application header journal
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this application header journal.
	 *
	 * @return the dealer ID of this application header journal
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this application header journal.
	 *
	 * @return the file ID of this application header journal
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this application header journal.
	 *
	 * @return the file name of this application header journal
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file url of this application header journal.
	 *
	 * @return the file url of this application header journal
	 */
	@Override
	public String getFileUrl() {
		return model.getFileUrl();
	}

	/**
	 * Returns the ID of this application header journal.
	 *
	 * @return the ID of this application header journal
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this application header journal.
	 *
	 * @return the modified by of this application header journal
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this application header journal.
	 *
	 * @return the modified date of this application header journal
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the nominal pengajuan of this application header journal.
	 *
	 * @return the nominal pengajuan of this application header journal
	 */
	@Override
	public Integer getNominalPengajuan() {
		return model.getNominalPengajuan();
	}

	/**
	 * Returns the notes of this application header journal.
	 *
	 * @return the notes of this application header journal
	 */
	@Override
	public String getNotes() {
		return model.getNotes();
	}

	/**
	 * Returns the notes history of this application header journal.
	 *
	 * @return the notes history of this application header journal
	 */
	@Override
	public String getNotesHistory() {
		return model.getNotesHistory();
	}

	/**
	 * Returns the primary key of this application header journal.
	 *
	 * @return the primary key of this application header journal
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the req cc email of this application header journal.
	 *
	 * @return the req cc email of this application header journal
	 */
	@Override
	public String getReqCCEmail() {
		return model.getReqCCEmail();
	}

	/**
	 * Returns the req date of this application header journal.
	 *
	 * @return the req date of this application header journal
	 */
	@Override
	public Date getReqDate() {
		return model.getReqDate();
	}

	/**
	 * Returns the req desc of this application header journal.
	 *
	 * @return the req desc of this application header journal
	 */
	@Override
	public String getReqDesc() {
		return model.getReqDesc();
	}

	/**
	 * Returns the req email of this application header journal.
	 *
	 * @return the req email of this application header journal
	 */
	@Override
	public String getReqEmail() {
		return model.getReqEmail();
	}

	/**
	 * Returns the req month of this application header journal.
	 *
	 * @return the req month of this application header journal
	 */
	@Override
	public Integer getReqMonth() {
		return model.getReqMonth();
	}

	/**
	 * Returns the req name of this application header journal.
	 *
	 * @return the req name of this application header journal
	 */
	@Override
	public String getReqName() {
		return model.getReqName();
	}

	/**
	 * Returns the req phone of this application header journal.
	 *
	 * @return the req phone of this application header journal
	 */
	@Override
	public String getReqPhone() {
		return model.getReqPhone();
	}

	/**
	 * Returns the req screen name of this application header journal.
	 *
	 * @return the req screen name of this application header journal
	 */
	@Override
	public String getReqScreenName() {
		return model.getReqScreenName();
	}

	/**
	 * Returns the req year of this application header journal.
	 *
	 * @return the req year of this application header journal
	 */
	@Override
	public Integer getReqYear() {
		return model.getReqYear();
	}

	/**
	 * Returns the row status of this application header journal.
	 *
	 * @return the row status of this application header journal
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the ticket code of this application header journal.
	 *
	 * @return the ticket code of this application header journal
	 */
	@Override
	public String getTicketCode() {
		return model.getTicketCode();
	}

	/**
	 * Returns the ticket no of this application header journal.
	 *
	 * @return the ticket no of this application header journal
	 */
	@Override
	public String getTicketNo() {
		return model.getTicketNo();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application category ID of this application header journal.
	 *
	 * @param ApplicationCategoryId the application category ID of this application header journal
	 */
	@Override
	public void setApplicationCategoryId(int ApplicationCategoryId) {
		model.setApplicationCategoryId(ApplicationCategoryId);
	}

	/**
	 * Sets the application header ID of this application header journal.
	 *
	 * @param ApplicationHeaderId the application header ID of this application header journal
	 */
	@Override
	public void setApplicationHeaderId(int ApplicationHeaderId) {
		model.setApplicationHeaderId(ApplicationHeaderId);
	}

	/**
	 * Sets the application header status ID of this application header journal.
	 *
	 * @param ApplicationHeaderStatusId the application header status ID of this application header journal
	 */
	@Override
	public void setApplicationHeaderStatusId(int ApplicationHeaderStatusId) {
		model.setApplicationHeaderStatusId(ApplicationHeaderStatusId);
	}

	/**
	 * Sets the application ID of this application header journal.
	 *
	 * @param ApplicationId the application ID of this application header journal
	 */
	@Override
	public void setApplicationId(int ApplicationId) {
		model.setApplicationId(ApplicationId);
	}

	/**
	 * Sets the business benefit of this application header journal.
	 *
	 * @param BusinessBenefit the business benefit of this application header journal
	 */
	@Override
	public void setBusinessBenefit(String BusinessBenefit) {
		model.setBusinessBenefit(BusinessBenefit);
	}

	/**
	 * Sets the created by of this application header journal.
	 *
	 * @param CreatedBy the created by of this application header journal
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this application header journal.
	 *
	 * @param CreatedDate the created date of this application header journal
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this application header journal.
	 *
	 * @param DealerId the dealer ID of this application header journal
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this application header journal.
	 *
	 * @param FileId the file ID of this application header journal
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this application header journal.
	 *
	 * @param FileName the file name of this application header journal
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file url of this application header journal.
	 *
	 * @param FileUrl the file url of this application header journal
	 */
	@Override
	public void setFileUrl(String FileUrl) {
		model.setFileUrl(FileUrl);
	}

	/**
	 * Sets the ID of this application header journal.
	 *
	 * @param Id the ID of this application header journal
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this application header journal.
	 *
	 * @param ModifiedBy the modified by of this application header journal
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this application header journal.
	 *
	 * @param ModifiedDate the modified date of this application header journal
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the nominal pengajuan of this application header journal.
	 *
	 * @param NominalPengajuan the nominal pengajuan of this application header journal
	 */
	@Override
	public void setNominalPengajuan(Integer NominalPengajuan) {
		model.setNominalPengajuan(NominalPengajuan);
	}

	/**
	 * Sets the notes of this application header journal.
	 *
	 * @param Notes the notes of this application header journal
	 */
	@Override
	public void setNotes(String Notes) {
		model.setNotes(Notes);
	}

	/**
	 * Sets the notes history of this application header journal.
	 *
	 * @param NotesHistory the notes history of this application header journal
	 */
	@Override
	public void setNotesHistory(String NotesHistory) {
		model.setNotesHistory(NotesHistory);
	}

	/**
	 * Sets the primary key of this application header journal.
	 *
	 * @param primaryKey the primary key of this application header journal
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the req cc email of this application header journal.
	 *
	 * @param ReqCCEmail the req cc email of this application header journal
	 */
	@Override
	public void setReqCCEmail(String ReqCCEmail) {
		model.setReqCCEmail(ReqCCEmail);
	}

	/**
	 * Sets the req date of this application header journal.
	 *
	 * @param ReqDate the req date of this application header journal
	 */
	@Override
	public void setReqDate(Date ReqDate) {
		model.setReqDate(ReqDate);
	}

	/**
	 * Sets the req desc of this application header journal.
	 *
	 * @param ReqDesc the req desc of this application header journal
	 */
	@Override
	public void setReqDesc(String ReqDesc) {
		model.setReqDesc(ReqDesc);
	}

	/**
	 * Sets the req email of this application header journal.
	 *
	 * @param ReqEmail the req email of this application header journal
	 */
	@Override
	public void setReqEmail(String ReqEmail) {
		model.setReqEmail(ReqEmail);
	}

	/**
	 * Sets the req month of this application header journal.
	 *
	 * @param ReqMonth the req month of this application header journal
	 */
	@Override
	public void setReqMonth(Integer ReqMonth) {
		model.setReqMonth(ReqMonth);
	}

	/**
	 * Sets the req name of this application header journal.
	 *
	 * @param ReqName the req name of this application header journal
	 */
	@Override
	public void setReqName(String ReqName) {
		model.setReqName(ReqName);
	}

	/**
	 * Sets the req phone of this application header journal.
	 *
	 * @param ReqPhone the req phone of this application header journal
	 */
	@Override
	public void setReqPhone(String ReqPhone) {
		model.setReqPhone(ReqPhone);
	}

	/**
	 * Sets the req screen name of this application header journal.
	 *
	 * @param ReqScreenName the req screen name of this application header journal
	 */
	@Override
	public void setReqScreenName(String ReqScreenName) {
		model.setReqScreenName(ReqScreenName);
	}

	/**
	 * Sets the req year of this application header journal.
	 *
	 * @param ReqYear the req year of this application header journal
	 */
	@Override
	public void setReqYear(Integer ReqYear) {
		model.setReqYear(ReqYear);
	}

	/**
	 * Sets the row status of this application header journal.
	 *
	 * @param RowStatus the row status of this application header journal
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the ticket code of this application header journal.
	 *
	 * @param TicketCode the ticket code of this application header journal
	 */
	@Override
	public void setTicketCode(String TicketCode) {
		model.setTicketCode(TicketCode);
	}

	/**
	 * Sets the ticket no of this application header journal.
	 *
	 * @param TicketNo the ticket no of this application header journal
	 */
	@Override
	public void setTicketNo(String TicketNo) {
		model.setTicketNo(TicketNo);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected ApplicationHeaderJournalWrapper wrap(
		ApplicationHeaderJournal applicationHeaderJournal) {

		return new ApplicationHeaderJournalWrapper(applicationHeaderJournal);
	}

}