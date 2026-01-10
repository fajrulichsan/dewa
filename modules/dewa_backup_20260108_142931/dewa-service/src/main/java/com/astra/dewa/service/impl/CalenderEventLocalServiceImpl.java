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

import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.model.CalenderEventParticipant;
import com.astra.dewa.service.CalenderEventLocalServiceUtil;
import com.astra.dewa.service.CalenderEventParticipantLocalServiceUtil;
import com.astra.dewa.service.base.CalenderEventLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.astra.dewa.model.CalenderEvent",
	service = AopService.class
)
public class CalenderEventLocalServiceImpl
	extends CalenderEventLocalServiceBaseImpl {

	@Transactional(rollbackFor = Exception.class)
	public JSONObject deleteCalenderEventWithDetails(int id, String screenName) throws SystemException {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			CalenderEvent calenderEvent = CalenderEventLocalServiceUtil.getCalenderEvent(id);
			calenderEvent.setRowStatus(false);
			calenderEvent.setModifiedDate(new Date());
			calenderEvent.setModifiedBy(screenName);
			CalenderEventLocalServiceUtil.updateCalenderEvent(calenderEvent);

			// Delete Calendar Event Details
			DynamicQuery query = CalenderEventParticipantLocalServiceUtil
					.dynamicQuery()
					.add(RestrictionsFactoryUtil.eq("CalenderEventId", calenderEvent.getId()));
			List<CalenderEventParticipant> participants = CalenderEventParticipantLocalServiceUtil.dynamicQuery(query);

			for (CalenderEventParticipant participant : participants) {
				CalenderEventParticipant cep = CalenderEventParticipantLocalServiceUtil.getCalenderEventParticipant(participant.getId());
				cep.setRowStatus(false);
				CalenderEventParticipantLocalServiceUtil.updateCalenderEventParticipant(cep);
			}

			jsonObject
					.put("acknowledge", 1)
					.put("status", "success")
					.put("message", "Data berhasil dihapus");
			return jsonObject;
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
}