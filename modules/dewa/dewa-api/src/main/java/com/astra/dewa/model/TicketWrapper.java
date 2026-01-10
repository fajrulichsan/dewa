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
 * This class is a wrapper for {@link Ticket}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Ticket
 * @generated
 */
public class TicketWrapper
	extends BaseModelWrapper<Ticket> implements ModelWrapper<Ticket>, Ticket {

	public TicketWrapper(Ticket ticket) {
		super(ticket);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("DealerCode", getDealerCode());
		attributes.put("TicketDate", getTicketDate());
		attributes.put("TicketNumber", getTicketNumber());
		attributes.put("RequestName", getRequestName());
		attributes.put("FirstApprover", getFirstApprover());
		attributes.put("Email", getEmail());
		attributes.put("EmailCc", getEmailCc());
		attributes.put("Phone", getPhone());
		attributes.put("RequestCategory", getRequestCategory());
		attributes.put("RequestDescription", getRequestDescription());
		attributes.put("BussinesBenefit", getBussinesBenefit());
		attributes.put("NameFile", getNameFile());
		attributes.put("PathFile", getPathFile());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("ModifiedDate", getModifiedDate());
		attributes.put("ModifiedBy", getModifiedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String Id = (String)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		String DealerCode = (String)attributes.get("DealerCode");

		if (DealerCode != null) {
			setDealerCode(DealerCode);
		}

		Date TicketDate = (Date)attributes.get("TicketDate");

		if (TicketDate != null) {
			setTicketDate(TicketDate);
		}

		String TicketNumber = (String)attributes.get("TicketNumber");

		if (TicketNumber != null) {
			setTicketNumber(TicketNumber);
		}

		String RequestName = (String)attributes.get("RequestName");

		if (RequestName != null) {
			setRequestName(RequestName);
		}

		String FirstApprover = (String)attributes.get("FirstApprover");

		if (FirstApprover != null) {
			setFirstApprover(FirstApprover);
		}

		String Email = (String)attributes.get("Email");

		if (Email != null) {
			setEmail(Email);
		}

		String EmailCc = (String)attributes.get("EmailCc");

		if (EmailCc != null) {
			setEmailCc(EmailCc);
		}

		String Phone = (String)attributes.get("Phone");

		if (Phone != null) {
			setPhone(Phone);
		}

		String RequestCategory = (String)attributes.get("RequestCategory");

		if (RequestCategory != null) {
			setRequestCategory(RequestCategory);
		}

		String RequestDescription = (String)attributes.get(
			"RequestDescription");

		if (RequestDescription != null) {
			setRequestDescription(RequestDescription);
		}

		String BussinesBenefit = (String)attributes.get("BussinesBenefit");

		if (BussinesBenefit != null) {
			setBussinesBenefit(BussinesBenefit);
		}

		String NameFile = (String)attributes.get("NameFile");

		if (NameFile != null) {
			setNameFile(NameFile);
		}

		String PathFile = (String)attributes.get("PathFile");

		if (PathFile != null) {
			setPathFile(PathFile);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		String CreatedBy = (String)attributes.get("CreatedBy");

		if (CreatedBy != null) {
			setCreatedBy(CreatedBy);
		}

		Date ModifiedDate = (Date)attributes.get("ModifiedDate");

		if (ModifiedDate != null) {
			setModifiedDate(ModifiedDate);
		}

		String ModifiedBy = (String)attributes.get("ModifiedBy");

		if (ModifiedBy != null) {
			setModifiedBy(ModifiedBy);
		}
	}

	@Override
	public Ticket cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the bussines benefit of this ticket.
	 *
	 * @return the bussines benefit of this ticket
	 */
	@Override
	public String getBussinesBenefit() {
		return model.getBussinesBenefit();
	}

	/**
	 * Returns the created by of this ticket.
	 *
	 * @return the created by of this ticket
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this ticket.
	 *
	 * @return the created date of this ticket
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer code of this ticket.
	 *
	 * @return the dealer code of this ticket
	 */
	@Override
	public String getDealerCode() {
		return model.getDealerCode();
	}

	/**
	 * Returns the email of this ticket.
	 *
	 * @return the email of this ticket
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the email cc of this ticket.
	 *
	 * @return the email cc of this ticket
	 */
	@Override
	public String getEmailCc() {
		return model.getEmailCc();
	}

	/**
	 * Returns the first approver of this ticket.
	 *
	 * @return the first approver of this ticket
	 */
	@Override
	public String getFirstApprover() {
		return model.getFirstApprover();
	}

	/**
	 * Returns the ID of this ticket.
	 *
	 * @return the ID of this ticket
	 */
	@Override
	public String getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this ticket.
	 *
	 * @return the modified by of this ticket
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this ticket.
	 *
	 * @return the modified date of this ticket
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name file of this ticket.
	 *
	 * @return the name file of this ticket
	 */
	@Override
	public String getNameFile() {
		return model.getNameFile();
	}

	/**
	 * Returns the path file of this ticket.
	 *
	 * @return the path file of this ticket
	 */
	@Override
	public String getPathFile() {
		return model.getPathFile();
	}

	/**
	 * Returns the phone of this ticket.
	 *
	 * @return the phone of this ticket
	 */
	@Override
	public String getPhone() {
		return model.getPhone();
	}

	/**
	 * Returns the primary key of this ticket.
	 *
	 * @return the primary key of this ticket
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the request category of this ticket.
	 *
	 * @return the request category of this ticket
	 */
	@Override
	public String getRequestCategory() {
		return model.getRequestCategory();
	}

	/**
	 * Returns the request description of this ticket.
	 *
	 * @return the request description of this ticket
	 */
	@Override
	public String getRequestDescription() {
		return model.getRequestDescription();
	}

	/**
	 * Returns the request name of this ticket.
	 *
	 * @return the request name of this ticket
	 */
	@Override
	public String getRequestName() {
		return model.getRequestName();
	}

	/**
	 * Returns the ticket date of this ticket.
	 *
	 * @return the ticket date of this ticket
	 */
	@Override
	public Date getTicketDate() {
		return model.getTicketDate();
	}

	/**
	 * Returns the ticket number of this ticket.
	 *
	 * @return the ticket number of this ticket
	 */
	@Override
	public String getTicketNumber() {
		return model.getTicketNumber();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the bussines benefit of this ticket.
	 *
	 * @param BussinesBenefit the bussines benefit of this ticket
	 */
	@Override
	public void setBussinesBenefit(String BussinesBenefit) {
		model.setBussinesBenefit(BussinesBenefit);
	}

	/**
	 * Sets the created by of this ticket.
	 *
	 * @param CreatedBy the created by of this ticket
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this ticket.
	 *
	 * @param CreatedDate the created date of this ticket
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer code of this ticket.
	 *
	 * @param DealerCode the dealer code of this ticket
	 */
	@Override
	public void setDealerCode(String DealerCode) {
		model.setDealerCode(DealerCode);
	}

	/**
	 * Sets the email of this ticket.
	 *
	 * @param Email the email of this ticket
	 */
	@Override
	public void setEmail(String Email) {
		model.setEmail(Email);
	}

	/**
	 * Sets the email cc of this ticket.
	 *
	 * @param EmailCc the email cc of this ticket
	 */
	@Override
	public void setEmailCc(String EmailCc) {
		model.setEmailCc(EmailCc);
	}

	/**
	 * Sets the first approver of this ticket.
	 *
	 * @param FirstApprover the first approver of this ticket
	 */
	@Override
	public void setFirstApprover(String FirstApprover) {
		model.setFirstApprover(FirstApprover);
	}

	/**
	 * Sets the ID of this ticket.
	 *
	 * @param Id the ID of this ticket
	 */
	@Override
	public void setId(String Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this ticket.
	 *
	 * @param ModifiedBy the modified by of this ticket
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this ticket.
	 *
	 * @param ModifiedDate the modified date of this ticket
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the name file of this ticket.
	 *
	 * @param NameFile the name file of this ticket
	 */
	@Override
	public void setNameFile(String NameFile) {
		model.setNameFile(NameFile);
	}

	/**
	 * Sets the path file of this ticket.
	 *
	 * @param PathFile the path file of this ticket
	 */
	@Override
	public void setPathFile(String PathFile) {
		model.setPathFile(PathFile);
	}

	/**
	 * Sets the phone of this ticket.
	 *
	 * @param Phone the phone of this ticket
	 */
	@Override
	public void setPhone(String Phone) {
		model.setPhone(Phone);
	}

	/**
	 * Sets the primary key of this ticket.
	 *
	 * @param primaryKey the primary key of this ticket
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the request category of this ticket.
	 *
	 * @param RequestCategory the request category of this ticket
	 */
	@Override
	public void setRequestCategory(String RequestCategory) {
		model.setRequestCategory(RequestCategory);
	}

	/**
	 * Sets the request description of this ticket.
	 *
	 * @param RequestDescription the request description of this ticket
	 */
	@Override
	public void setRequestDescription(String RequestDescription) {
		model.setRequestDescription(RequestDescription);
	}

	/**
	 * Sets the request name of this ticket.
	 *
	 * @param RequestName the request name of this ticket
	 */
	@Override
	public void setRequestName(String RequestName) {
		model.setRequestName(RequestName);
	}

	/**
	 * Sets the ticket date of this ticket.
	 *
	 * @param TicketDate the ticket date of this ticket
	 */
	@Override
	public void setTicketDate(Date TicketDate) {
		model.setTicketDate(TicketDate);
	}

	/**
	 * Sets the ticket number of this ticket.
	 *
	 * @param TicketNumber the ticket number of this ticket
	 */
	@Override
	public void setTicketNumber(String TicketNumber) {
		model.setTicketNumber(TicketNumber);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected TicketWrapper wrap(Ticket ticket) {
		return new TicketWrapper(ticket);
	}

}