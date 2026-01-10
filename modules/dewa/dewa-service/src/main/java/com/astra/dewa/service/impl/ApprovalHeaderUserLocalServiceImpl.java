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

package com.astra.dewa.service.impl;

import com.astra.dewa.model.ApprovalDetailUser;
import com.astra.dewa.model.ApprovalHeaderUser;
import com.astra.dewa.model.ApprovalHistoryUser;
import com.astra.dewa.service.ApprovalDetailUserLocalServiceUtil;
import com.astra.dewa.service.ApprovalHeaderUserLocalServiceUtil;
import com.astra.dewa.service.ApprovalHistoryUserLocalServiceUtil;
import com.astra.dewa.service.base.ApprovalHeaderUserLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.astra.dewa.model.ApprovalHeaderUser",
	service = AopService.class
)
public class ApprovalHeaderUserLocalServiceImpl
	extends ApprovalHeaderUserLocalServiceBaseImpl {

	@Transactional(rollbackFor = Exception.class)
	public ApprovalHeaderUser createApprovalHeaderUserWithHistoryAndDetails(ApprovalHeaderUser approvalHeaderUser, String roles) throws SystemException {
		ApprovalHeaderUser newApprovalHeaderUser;
		try {
			newApprovalHeaderUser = ApprovalHeaderUserLocalServiceUtil.addApprovalHeaderUser(approvalHeaderUser);
			ApprovalHistoryUser approvalHistoryUser = ApprovalHistoryUserLocalServiceUtil.createApprovalHistoryUser(0);
			approvalHistoryUser.setApprovalHeaderUserId(newApprovalHeaderUser.getId());
			approvalHistoryUser.setApplicationAssignStatusId(newApprovalHeaderUser.getApplicationAssignStatusId());
			approvalHistoryUser.setDealerId(newApprovalHeaderUser.getDealerId());
			approvalHistoryUser.setCabangId(newApprovalHeaderUser.getCabangId());
			approvalHistoryUser.setRequesterName(newApprovalHeaderUser.getRequesterName());
			approvalHistoryUser.setRequesterEmail(newApprovalHeaderUser.getRequesterEmail());
			approvalHistoryUser.setRowStatus(newApprovalHeaderUser.getRowStatus());
			approvalHistoryUser.setCreatedBy(newApprovalHeaderUser.getCreatedBy());
			approvalHistoryUser.setCreatedDate(newApprovalHeaderUser.getCreatedDate());
			approvalHistoryUser.setModifiedBy(newApprovalHeaderUser.getModifiedBy());
			approvalHistoryUser.setModifiedDate(newApprovalHeaderUser.getModifiedDate());
			ApprovalHistoryUserLocalServiceUtil.addApprovalHistoryUser(approvalHistoryUser);

			JSONArray roleIds = JSONFactoryUtil.createJSONArray(roles);
			for (Object object : roleIds) {
				JSONObject role = (JSONObject) object;
				int roleId = role.getInt("id");
				ApprovalDetailUser approvalDetailUser = ApprovalDetailUserLocalServiceUtil.createApprovalDetailUser(0);
				approvalDetailUser.setApprovalHeaderUserId(approvalHeaderUser.getId());
				approvalDetailUser.setRoleId(roleId);
				approvalDetailUser.setRowStatus(true);
				approvalDetailUser.setCreatedBy(newApprovalHeaderUser.getRequesterEmail());
				approvalDetailUser.setCreatedDate(new Date());
				approvalDetailUser.setModifiedBy(newApprovalHeaderUser.getRequesterEmail());
				approvalDetailUser.setModifiedDate(new Date());
				ApprovalDetailUserLocalServiceUtil.addApprovalDetailUser(approvalDetailUser);
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return newApprovalHeaderUser;
	}

	@Transactional(rollbackFor = Exception.class)
	public ApprovalHeaderUser updateApprovalHeaderUserWithHistory(ApprovalHeaderUser approvalHeaderUser) throws SystemException {
		ApprovalHeaderUser updatedApprovalHeaderUser;
		try {
			updatedApprovalHeaderUser = ApprovalHeaderUserLocalServiceUtil.updateApprovalHeaderUser(approvalHeaderUser);
			ApprovalHistoryUser approvalHistoryUser = ApprovalHistoryUserLocalServiceUtil.createApprovalHistoryUser(0);
			approvalHistoryUser.setApprovalHeaderUserId(updatedApprovalHeaderUser.getId());
			approvalHistoryUser.setApplicationAssignStatusId(updatedApprovalHeaderUser.getApplicationAssignStatusId());
			approvalHistoryUser.setApproverUserId(updatedApprovalHeaderUser.getApproverUserId());
			approvalHistoryUser.setDealerId(updatedApprovalHeaderUser.getDealerId());
			approvalHistoryUser.setCabangId(updatedApprovalHeaderUser.getCabangId());
			approvalHistoryUser.setRequesterName(updatedApprovalHeaderUser.getRequesterName());
			approvalHistoryUser.setRequesterEmail(updatedApprovalHeaderUser.getRequesterEmail());
			approvalHistoryUser.setRowStatus(updatedApprovalHeaderUser.getRowStatus());
			approvalHistoryUser.setCreatedBy(updatedApprovalHeaderUser.getModifiedBy());
			approvalHistoryUser.setCreatedDate(updatedApprovalHeaderUser.getCreatedDate());
			approvalHistoryUser.setModifiedBy(updatedApprovalHeaderUser.getModifiedBy());
			approvalHistoryUser.setModifiedDate(updatedApprovalHeaderUser.getModifiedDate());
			ApprovalHistoryUserLocalServiceUtil.addApprovalHistoryUser(approvalHistoryUser);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return updatedApprovalHeaderUser;
	}
}