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

import com.astra.dewa.exception.NoSuchUsersDealersException;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.service.base.UsersDealersLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.astra.dewa.model.UsersDealers",
	service = AopService.class
)
public class UsersDealersLocalServiceImpl
	extends UsersDealersLocalServiceBaseImpl {

	@Transactional(rollbackFor = Exception.class)
	public UsersDealers createDealerUserWithRoles(UsersDealers dealerUser, List<Integer> roles) throws SystemException {
		UsersDealers newDealerUser;
		try {
			newDealerUser = UsersDealersLocalServiceUtil.addUsersDealers(dealerUser);
			for (Integer role : roles) {
				UserRoleType userRoleType = UserRoleTypeLocalServiceUtil.createUserRoleType(0);
				userRoleType.setUserId(newDealerUser.getUserId());
				userRoleType.setRoleId(role);
				userRoleType.setRowStatus(newDealerUser.getRowStatus());
				userRoleType.setCreatedBy(newDealerUser.getCreatedBy());
				userRoleType.setCreatedDate(newDealerUser.getCreatedDate());
				userRoleType.setModifiedBy(newDealerUser.getModifiedBy());
				userRoleType.setModifiedDate(newDealerUser.getModifiedDate());
				UserRoleTypeLocalServiceUtil.addUserRoleType(userRoleType);
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return newDealerUser;
	}

	@Transactional(rollbackFor = Exception.class)
	public UsersDealers deleteDealerUserWithRoles(UsersDealers dealerUser) throws SystemException {
		UsersDealers updatedDealerUser;
		try {
			updatedDealerUser = UsersDealersLocalServiceUtil.updateUsersDealers(dealerUser);
			DynamicQuery query = UserRoleTypeLocalServiceUtil.dynamicQuery()
					.add(RestrictionsFactoryUtil.eq("UserId", updatedDealerUser.getUserId()))
					.add(RestrictionsFactoryUtil.eq("RowStatus", true));
			List<UserRoleType> roles = UserRoleTypeLocalServiceUtil.dynamicQuery(query);
			for (UserRoleType role : roles) {
				role.setModifiedDate(updatedDealerUser.getModifiedDate());
				role.setModifiedBy(updatedDealerUser.getModifiedBy());
				role.setRowStatus(false);
				UserRoleTypeLocalServiceUtil.updateUserRoleType(role);
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return updatedDealerUser;
	}

	public UsersDealers findUsersDealersIdFromUserId (long userId, boolean rowStatus) throws NoSuchUsersDealersException {
		return usersDealersPersistence.findByUsersDealersIdFromUserId(userId, rowStatus);
	}

	public List<UsersDealers> getDealerUsers(String order, int start, int length, String searchValue, String roleQuery) throws Exception {
		Session session = usersDealersPersistence.openSession();
		List<UsersDealers> result = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder();

			String searchParam = ("AND (d.KodeHo LIKE '" + searchValue + "%' " +
					"OR ud.FullName LIKE '" + searchValue + "%' " +
					"OR ud.UserEmail LIKE '" + searchValue + "%' " +
					"OR d.Name LIKE '" + searchValue + "%' " +
					"OR c.Name LIKE '" + searchValue + "%')\n");

			String searchUnionParam = ("AND (FullName LIKE '" + searchValue + "%' OR UserEmail LIKE '" + searchValue + "%')\n");

			String jdbcDriver = PropsUtil.get("jdbc.dewa.driverClassName");
			jdbcDriver = jdbcDriver.toLowerCase();

			sql.append("SELECT ud.Id AS Id, ud.UserId AS UserId, d.KodeHo AS KodeHo, ud.FullName AS FullName,\n" +
					"ud.UserEmail AS UserEmail,\n" +
					"d.Name AS DealerName, c.Name AS CabangName,\n" +
					"ud.ApprovedDate AS ApprovedDate, ud.ModifiedDate AS ModifiedDate " +
					"FROM UsersDealers ud\n" +
					"INNER JOIN Dealer d ON ud.DealerId = d.Id\n" +
					"INNER JOIN Cabang c ON d.CabangId = c.Id\n" +
					"WHERE ud.RowStatus = 1\n");
			sql.append(searchParam);

			if (!roleQuery.isEmpty()) {
				sql.append(roleQuery);
			} else {
				sql.append("UNION SELECT Id, UserId, '-' AS KodeHo, FullName, UserEmail, '-' AS DealerName, '-' AS CabangName, ApprovedDate, ModifiedDate FROM UsersDealers\n" +
						"WHERE DealerId = 0 AND RowStatus = 1\n");
				sql.append(searchUnionParam);
			}

			sql.append(order);

			if (jdbcDriver.contains("mysql")) {
				sql.append("LIMIT ?, ?");
			} else {
				sql.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
			}

			SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
			sqlQuery.setCacheable(true);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);
			queryPos.add(start);
			queryPos.add(length);

			@SuppressWarnings("unchecked")
			List<Object[]> queryResults = sqlQuery.list();

			if (!queryResults.isEmpty()) {
				for (Object[] o : queryResults) {
					result.add(UsersDealersLocalServiceUtil.getUsersDealers((Integer) o[0]));
				}
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e);
		} finally {
			session.close();
		}
	}

	public long getDealerUsersCount(String searchValue, String roleQuery) throws Exception {
		Session session = usersDealersPersistence.openSession();
		long count = 0;
		try {
			StringBuilder sql = new StringBuilder();

			String searchParam = ("AND (d.KodeHo LIKE '" + searchValue + "%' " +
					"OR ud.FullName LIKE '" + searchValue + "%' " +
					"OR ud.UserEmail LIKE '" + searchValue + "%' " +
					"OR d.Name LIKE '" + searchValue + "%' " +
					"OR c.Name LIKE '" + searchValue + "%')\n");

			String searchUnionParam = ("AND (FullName LIKE '" + searchValue + "%' OR UserEmail LIKE '" + searchValue + "%')\n");

			sql.append("SELECT COUNT(UserId) FROM (\nSELECT DISTINCT UserId FROM (\n");
			sql.append("SELECT ud.Id AS Id, ud.UserId AS UserId, d.KodeHo AS KodeHo, ud.FullName AS FullName,\n" +
					"ud.UserEmail AS UserEmail,\n" +
					"d.Name AS DealerName, c.Name AS CabangName,\n" +
					"ud.ApprovedDate AS ApprovedDate, ud.ModifiedDate AS ModifiedDate " +
					"FROM UsersDealers ud\n" +
					"INNER JOIN Dealer d ON ud.DealerId = d.Id\n" +
					"INNER JOIN Cabang c ON d.CabangId = c.Id\n" +
					"WHERE ud.RowStatus = 1\n");
			sql.append(searchParam);

			if (!roleQuery.isEmpty()) {
				sql.append(roleQuery);
			} else {
				sql.append("UNION SELECT Id, UserId, '-' AS KodeHo, FullName, UserEmail, '-' AS DealerName, '-' AS CabangName, ApprovedDate, ModifiedDate FROM UsersDealers\n" +
						"WHERE DealerId = 0 AND RowStatus = 1\n");
				sql.append(searchUnionParam);
			}
			sql.append(") AS FilteredUser\n) AS Count;");

			SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
			sqlQuery.setCacheable(true);

			count = ((Number) sqlQuery.uniqueResult()).longValue();

			return count;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			session.close();
		}
	}
}