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

import com.astra.dewa.exception.NoSuchApprovalDetailUserException;
import com.astra.dewa.model.ApprovalDetailUser;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.service.ApprovalDetailUserLocalServiceUtil;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.base.ApprovalDetailUserLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.astra.dewa.model.ApprovalDetailUser",
	service = AopService.class
)
public class ApprovalDetailUserLocalServiceImpl
	extends ApprovalDetailUserLocalServiceBaseImpl {

	@Transactional(rollbackFor = Exception.class)
	public ApprovalDetailUser updateDetailUserAndUserRole(ApprovalDetailUser approvalDetailUser, long userId, int roleId) throws SystemException {
		ApprovalDetailUser updatedDetailUser;
		try {
			updatedDetailUser = ApprovalDetailUserLocalServiceUtil.updateApprovalDetailUser(approvalDetailUser);
			Date now = new Date();

			// CHECK IF USER HAS SELECTED ROLE
			DynamicQuery userRoleQuery = UserRoleTypeLocalServiceUtil.dynamicQuery()
					.add(RestrictionsFactoryUtil.eq("UserId", userId))
					.add(RestrictionsFactoryUtil.eq("RoleId", roleId));
			List<UserRoleType> userRoles = UserRoleTypeLocalServiceUtil.dynamicQuery(userRoleQuery);

			// USER HAS ROLE BUT THE ROLE IS INACTIVE
			if (!userRoles.isEmpty()) {
				// REACTIVATE THE ROLE
				UserRoleType userRoleType = userRoles.get(0);
				userRoleType.setRowStatus(true);
				userRoleType.setModifiedBy(updatedDetailUser.getModifiedBy());
				userRoleType.setModifiedDate(now);
				UserRoleTypeLocalServiceUtil.updateUserRoleType(userRoleType);
			} else {
				// CREATE NEW USER ROLE
				UserRoleType userRoleType = UserRoleTypeLocalServiceUtil.createUserRoleType(0);
				userRoleType.setUserId(userId);
				userRoleType.setRoleId(roleId);
				userRoleType.setRowStatus(true);
				userRoleType.setCreatedBy(updatedDetailUser.getCreatedBy());
				userRoleType.setCreatedDate(now);
				userRoleType.setModifiedBy(updatedDetailUser.getModifiedBy());
				userRoleType.setModifiedDate(now);
				UserRoleTypeLocalServiceUtil.addUserRoleType(userRoleType);
			}
		} catch (Exception e) {
			throw new SystemException();
		}
		return updatedDetailUser;
	}

	public ApprovalDetailUser findDetailUser (Integer approvalHeaderUserId) throws NoSuchApprovalDetailUserException {
		return approvalDetailUserPersistence.findByFindDetailUser(approvalHeaderUserId, true);
	}
}