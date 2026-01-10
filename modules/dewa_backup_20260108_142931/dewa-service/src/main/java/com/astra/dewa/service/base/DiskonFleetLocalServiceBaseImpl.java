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

package com.astra.dewa.service.base;

import com.astra.dewa.model.DiskonFleet;
import com.astra.dewa.service.DiskonFleetLocalService;
import com.astra.dewa.service.DiskonFleetLocalServiceUtil;
import com.astra.dewa.service.persistence.ApplicationAssignJournalPersistence;
import com.astra.dewa.service.persistence.ApplicationAssignPersistence;
import com.astra.dewa.service.persistence.ApplicationAssignStatusPersistence;
import com.astra.dewa.service.persistence.ApplicationCategoryPersistence;
import com.astra.dewa.service.persistence.ApplicationHeaderJournalPersistence;
import com.astra.dewa.service.persistence.ApplicationHeaderPersistence;
import com.astra.dewa.service.persistence.ApplicationHeaderStatusPersistence;
import com.astra.dewa.service.persistence.ApplicationPersistence;
import com.astra.dewa.service.persistence.ApprovalDetailUserPersistence;
import com.astra.dewa.service.persistence.ApprovalHeaderUserPersistence;
import com.astra.dewa.service.persistence.ApprovalHistoryUserPersistence;
import com.astra.dewa.service.persistence.BannerPersistence;
import com.astra.dewa.service.persistence.BulanPersistence;
import com.astra.dewa.service.persistence.CabangPersistence;
import com.astra.dewa.service.persistence.CalenderEventParticipantPersistence;
import com.astra.dewa.service.persistence.CalenderEventPersistence;
import com.astra.dewa.service.persistence.CommonPersistence;
import com.astra.dewa.service.persistence.DealerPersistence;
import com.astra.dewa.service.persistence.DiskonFakpolPersistence;
import com.astra.dewa.service.persistence.DiskonFleetPersistence;
import com.astra.dewa.service.persistence.DiskonOtherPersistence;
import com.astra.dewa.service.persistence.DiskonTestCarPersistence;
import com.astra.dewa.service.persistence.ESrutPersistence;
import com.astra.dewa.service.persistence.EmailDomainPersistence;
import com.astra.dewa.service.persistence.FakturIndirectPersistence;
import com.astra.dewa.service.persistence.FakturPajakPersistence;
import com.astra.dewa.service.persistence.JenisMateriPersistence;
import com.astra.dewa.service.persistence.KategoriDealerPersistence;
import com.astra.dewa.service.persistence.KuartalPersistence;
import com.astra.dewa.service.persistence.MasterApprovalDetailJournalPersistence;
import com.astra.dewa.service.persistence.MasterApprovalDetailPersistence;
import com.astra.dewa.service.persistence.MasterApprovalJournalPersistence;
import com.astra.dewa.service.persistence.MasterApprovalPersistence;
import com.astra.dewa.service.persistence.MenuAuthorizationPersistence;
import com.astra.dewa.service.persistence.OTPPersistence;
import com.astra.dewa.service.persistence.PeriodeReviewPersistence;
import com.astra.dewa.service.persistence.ReportPlafondPersistence;
import com.astra.dewa.service.persistence.RolesPersistence;
import com.astra.dewa.service.persistence.SP3DPersistence;
import com.astra.dewa.service.persistence.SalesProgramPersistence;
import com.astra.dewa.service.persistence.SalesReportPersistence;
import com.astra.dewa.service.persistence.SettingPersistence;
import com.astra.dewa.service.persistence.SkIrisPersistence;
import com.astra.dewa.service.persistence.SuratPenaltyStockPersistence;
import com.astra.dewa.service.persistence.TahunPersistence;
import com.astra.dewa.service.persistence.TicketPersistence;
import com.astra.dewa.service.persistence.TipeKendaraanPersistence;
import com.astra.dewa.service.persistence.TokenPersistence;
import com.astra.dewa.service.persistence.TopikMateriPersistence;
import com.astra.dewa.service.persistence.TrainingAgendaParticipantPersistence;
import com.astra.dewa.service.persistence.TrainingAgendaParticipantUfPersistence;
import com.astra.dewa.service.persistence.TrainingAgendaPersistence;
import com.astra.dewa.service.persistence.TrainingMateriLampiranPersistence;
import com.astra.dewa.service.persistence.TrainingMateriPersistence;
import com.astra.dewa.service.persistence.UserRoleTypePersistence;
import com.astra.dewa.service.persistence.UsersDealersPersistence;
import com.astra.dewa.service.persistence.WilayahPersistence;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the diskon fleet local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.astra.dewa.service.impl.DiskonFleetLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.astra.dewa.service.impl.DiskonFleetLocalServiceImpl
 * @generated
 */
public abstract class DiskonFleetLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements DiskonFleetLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DiskonFleetLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>DiskonFleetLocalServiceUtil</code>.
	 */

	/**
	 * Adds the diskon fleet to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFleet the diskon fleet
	 * @return the diskon fleet that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DiskonFleet addDiskonFleet(DiskonFleet diskonFleet) {
		diskonFleet.setNew(true);

		return diskonFleetPersistence.update(diskonFleet);
	}

	/**
	 * Creates a new diskon fleet with the primary key. Does not add the diskon fleet to the database.
	 *
	 * @param Id the primary key for the new diskon fleet
	 * @return the new diskon fleet
	 */
	@Override
	@Transactional(enabled = false)
	public DiskonFleet createDiskonFleet(int Id) {
		return diskonFleetPersistence.create(Id);
	}

	/**
	 * Deletes the diskon fleet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet that was removed
	 * @throws PortalException if a diskon fleet with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DiskonFleet deleteDiskonFleet(int Id) throws PortalException {
		return diskonFleetPersistence.remove(Id);
	}

	/**
	 * Deletes the diskon fleet from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFleet the diskon fleet
	 * @return the diskon fleet that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DiskonFleet deleteDiskonFleet(DiskonFleet diskonFleet) {
		return diskonFleetPersistence.remove(diskonFleet);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return diskonFleetPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			DiskonFleet.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return diskonFleetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return diskonFleetPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return diskonFleetPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return diskonFleetPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return diskonFleetPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DiskonFleet fetchDiskonFleet(int Id) {
		return diskonFleetPersistence.fetchByPrimaryKey(Id);
	}

	/**
	 * Returns the diskon fleet with the primary key.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet
	 * @throws PortalException if a diskon fleet with the primary key could not be found
	 */
	@Override
	public DiskonFleet getDiskonFleet(int Id) throws PortalException {
		return diskonFleetPersistence.findByPrimaryKey(Id);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(diskonFleetLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DiskonFleet.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("Id");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			diskonFleetLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DiskonFleet.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("Id");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(diskonFleetLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DiskonFleet.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("Id");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return diskonFleetPersistence.create(
			((Integer)primaryKeyObj).intValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement DiskonFleetLocalServiceImpl#deleteDiskonFleet(DiskonFleet) to avoid orphaned data");
		}

		return diskonFleetLocalService.deleteDiskonFleet(
			(DiskonFleet)persistedModel);
	}

	@Override
	public BasePersistence<DiskonFleet> getBasePersistence() {
		return diskonFleetPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return diskonFleetPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @return the range of diskon fleets
	 */
	@Override
	public List<DiskonFleet> getDiskonFleets(int start, int end) {
		return diskonFleetPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of diskon fleets.
	 *
	 * @return the number of diskon fleets
	 */
	@Override
	public int getDiskonFleetsCount() {
		return diskonFleetPersistence.countAll();
	}

	/**
	 * Updates the diskon fleet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFleet the diskon fleet
	 * @return the diskon fleet that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DiskonFleet updateDiskonFleet(DiskonFleet diskonFleet) {
		return diskonFleetPersistence.update(diskonFleet);
	}

	/**
	 * Returns the application local service.
	 *
	 * @return the application local service
	 */
	public com.astra.dewa.service.ApplicationLocalService
		getApplicationLocalService() {

		return applicationLocalService;
	}

	/**
	 * Sets the application local service.
	 *
	 * @param applicationLocalService the application local service
	 */
	public void setApplicationLocalService(
		com.astra.dewa.service.ApplicationLocalService
			applicationLocalService) {

		this.applicationLocalService = applicationLocalService;
	}

	/**
	 * Returns the application persistence.
	 *
	 * @return the application persistence
	 */
	public ApplicationPersistence getApplicationPersistence() {
		return applicationPersistence;
	}

	/**
	 * Sets the application persistence.
	 *
	 * @param applicationPersistence the application persistence
	 */
	public void setApplicationPersistence(
		ApplicationPersistence applicationPersistence) {

		this.applicationPersistence = applicationPersistence;
	}

	/**
	 * Returns the application assign local service.
	 *
	 * @return the application assign local service
	 */
	public com.astra.dewa.service.ApplicationAssignLocalService
		getApplicationAssignLocalService() {

		return applicationAssignLocalService;
	}

	/**
	 * Sets the application assign local service.
	 *
	 * @param applicationAssignLocalService the application assign local service
	 */
	public void setApplicationAssignLocalService(
		com.astra.dewa.service.ApplicationAssignLocalService
			applicationAssignLocalService) {

		this.applicationAssignLocalService = applicationAssignLocalService;
	}

	/**
	 * Returns the application assign persistence.
	 *
	 * @return the application assign persistence
	 */
	public ApplicationAssignPersistence getApplicationAssignPersistence() {
		return applicationAssignPersistence;
	}

	/**
	 * Sets the application assign persistence.
	 *
	 * @param applicationAssignPersistence the application assign persistence
	 */
	public void setApplicationAssignPersistence(
		ApplicationAssignPersistence applicationAssignPersistence) {

		this.applicationAssignPersistence = applicationAssignPersistence;
	}

	/**
	 * Returns the application assign journal local service.
	 *
	 * @return the application assign journal local service
	 */
	public com.astra.dewa.service.ApplicationAssignJournalLocalService
		getApplicationAssignJournalLocalService() {

		return applicationAssignJournalLocalService;
	}

	/**
	 * Sets the application assign journal local service.
	 *
	 * @param applicationAssignJournalLocalService the application assign journal local service
	 */
	public void setApplicationAssignJournalLocalService(
		com.astra.dewa.service.ApplicationAssignJournalLocalService
			applicationAssignJournalLocalService) {

		this.applicationAssignJournalLocalService =
			applicationAssignJournalLocalService;
	}

	/**
	 * Returns the application assign journal persistence.
	 *
	 * @return the application assign journal persistence
	 */
	public ApplicationAssignJournalPersistence
		getApplicationAssignJournalPersistence() {

		return applicationAssignJournalPersistence;
	}

	/**
	 * Sets the application assign journal persistence.
	 *
	 * @param applicationAssignJournalPersistence the application assign journal persistence
	 */
	public void setApplicationAssignJournalPersistence(
		ApplicationAssignJournalPersistence
			applicationAssignJournalPersistence) {

		this.applicationAssignJournalPersistence =
			applicationAssignJournalPersistence;
	}

	/**
	 * Returns the application assign status local service.
	 *
	 * @return the application assign status local service
	 */
	public com.astra.dewa.service.ApplicationAssignStatusLocalService
		getApplicationAssignStatusLocalService() {

		return applicationAssignStatusLocalService;
	}

	/**
	 * Sets the application assign status local service.
	 *
	 * @param applicationAssignStatusLocalService the application assign status local service
	 */
	public void setApplicationAssignStatusLocalService(
		com.astra.dewa.service.ApplicationAssignStatusLocalService
			applicationAssignStatusLocalService) {

		this.applicationAssignStatusLocalService =
			applicationAssignStatusLocalService;
	}

	/**
	 * Returns the application assign status persistence.
	 *
	 * @return the application assign status persistence
	 */
	public ApplicationAssignStatusPersistence
		getApplicationAssignStatusPersistence() {

		return applicationAssignStatusPersistence;
	}

	/**
	 * Sets the application assign status persistence.
	 *
	 * @param applicationAssignStatusPersistence the application assign status persistence
	 */
	public void setApplicationAssignStatusPersistence(
		ApplicationAssignStatusPersistence applicationAssignStatusPersistence) {

		this.applicationAssignStatusPersistence =
			applicationAssignStatusPersistence;
	}

	/**
	 * Returns the application category local service.
	 *
	 * @return the application category local service
	 */
	public com.astra.dewa.service.ApplicationCategoryLocalService
		getApplicationCategoryLocalService() {

		return applicationCategoryLocalService;
	}

	/**
	 * Sets the application category local service.
	 *
	 * @param applicationCategoryLocalService the application category local service
	 */
	public void setApplicationCategoryLocalService(
		com.astra.dewa.service.ApplicationCategoryLocalService
			applicationCategoryLocalService) {

		this.applicationCategoryLocalService = applicationCategoryLocalService;
	}

	/**
	 * Returns the application category persistence.
	 *
	 * @return the application category persistence
	 */
	public ApplicationCategoryPersistence getApplicationCategoryPersistence() {
		return applicationCategoryPersistence;
	}

	/**
	 * Sets the application category persistence.
	 *
	 * @param applicationCategoryPersistence the application category persistence
	 */
	public void setApplicationCategoryPersistence(
		ApplicationCategoryPersistence applicationCategoryPersistence) {

		this.applicationCategoryPersistence = applicationCategoryPersistence;
	}

	/**
	 * Returns the application header local service.
	 *
	 * @return the application header local service
	 */
	public com.astra.dewa.service.ApplicationHeaderLocalService
		getApplicationHeaderLocalService() {

		return applicationHeaderLocalService;
	}

	/**
	 * Sets the application header local service.
	 *
	 * @param applicationHeaderLocalService the application header local service
	 */
	public void setApplicationHeaderLocalService(
		com.astra.dewa.service.ApplicationHeaderLocalService
			applicationHeaderLocalService) {

		this.applicationHeaderLocalService = applicationHeaderLocalService;
	}

	/**
	 * Returns the application header persistence.
	 *
	 * @return the application header persistence
	 */
	public ApplicationHeaderPersistence getApplicationHeaderPersistence() {
		return applicationHeaderPersistence;
	}

	/**
	 * Sets the application header persistence.
	 *
	 * @param applicationHeaderPersistence the application header persistence
	 */
	public void setApplicationHeaderPersistence(
		ApplicationHeaderPersistence applicationHeaderPersistence) {

		this.applicationHeaderPersistence = applicationHeaderPersistence;
	}

	/**
	 * Returns the application header journal local service.
	 *
	 * @return the application header journal local service
	 */
	public com.astra.dewa.service.ApplicationHeaderJournalLocalService
		getApplicationHeaderJournalLocalService() {

		return applicationHeaderJournalLocalService;
	}

	/**
	 * Sets the application header journal local service.
	 *
	 * @param applicationHeaderJournalLocalService the application header journal local service
	 */
	public void setApplicationHeaderJournalLocalService(
		com.astra.dewa.service.ApplicationHeaderJournalLocalService
			applicationHeaderJournalLocalService) {

		this.applicationHeaderJournalLocalService =
			applicationHeaderJournalLocalService;
	}

	/**
	 * Returns the application header journal persistence.
	 *
	 * @return the application header journal persistence
	 */
	public ApplicationHeaderJournalPersistence
		getApplicationHeaderJournalPersistence() {

		return applicationHeaderJournalPersistence;
	}

	/**
	 * Sets the application header journal persistence.
	 *
	 * @param applicationHeaderJournalPersistence the application header journal persistence
	 */
	public void setApplicationHeaderJournalPersistence(
		ApplicationHeaderJournalPersistence
			applicationHeaderJournalPersistence) {

		this.applicationHeaderJournalPersistence =
			applicationHeaderJournalPersistence;
	}

	/**
	 * Returns the application header status local service.
	 *
	 * @return the application header status local service
	 */
	public com.astra.dewa.service.ApplicationHeaderStatusLocalService
		getApplicationHeaderStatusLocalService() {

		return applicationHeaderStatusLocalService;
	}

	/**
	 * Sets the application header status local service.
	 *
	 * @param applicationHeaderStatusLocalService the application header status local service
	 */
	public void setApplicationHeaderStatusLocalService(
		com.astra.dewa.service.ApplicationHeaderStatusLocalService
			applicationHeaderStatusLocalService) {

		this.applicationHeaderStatusLocalService =
			applicationHeaderStatusLocalService;
	}

	/**
	 * Returns the application header status persistence.
	 *
	 * @return the application header status persistence
	 */
	public ApplicationHeaderStatusPersistence
		getApplicationHeaderStatusPersistence() {

		return applicationHeaderStatusPersistence;
	}

	/**
	 * Sets the application header status persistence.
	 *
	 * @param applicationHeaderStatusPersistence the application header status persistence
	 */
	public void setApplicationHeaderStatusPersistence(
		ApplicationHeaderStatusPersistence applicationHeaderStatusPersistence) {

		this.applicationHeaderStatusPersistence =
			applicationHeaderStatusPersistence;
	}

	/**
	 * Returns the approval detail user local service.
	 *
	 * @return the approval detail user local service
	 */
	public com.astra.dewa.service.ApprovalDetailUserLocalService
		getApprovalDetailUserLocalService() {

		return approvalDetailUserLocalService;
	}

	/**
	 * Sets the approval detail user local service.
	 *
	 * @param approvalDetailUserLocalService the approval detail user local service
	 */
	public void setApprovalDetailUserLocalService(
		com.astra.dewa.service.ApprovalDetailUserLocalService
			approvalDetailUserLocalService) {

		this.approvalDetailUserLocalService = approvalDetailUserLocalService;
	}

	/**
	 * Returns the approval detail user persistence.
	 *
	 * @return the approval detail user persistence
	 */
	public ApprovalDetailUserPersistence getApprovalDetailUserPersistence() {
		return approvalDetailUserPersistence;
	}

	/**
	 * Sets the approval detail user persistence.
	 *
	 * @param approvalDetailUserPersistence the approval detail user persistence
	 */
	public void setApprovalDetailUserPersistence(
		ApprovalDetailUserPersistence approvalDetailUserPersistence) {

		this.approvalDetailUserPersistence = approvalDetailUserPersistence;
	}

	/**
	 * Returns the approval header user local service.
	 *
	 * @return the approval header user local service
	 */
	public com.astra.dewa.service.ApprovalHeaderUserLocalService
		getApprovalHeaderUserLocalService() {

		return approvalHeaderUserLocalService;
	}

	/**
	 * Sets the approval header user local service.
	 *
	 * @param approvalHeaderUserLocalService the approval header user local service
	 */
	public void setApprovalHeaderUserLocalService(
		com.astra.dewa.service.ApprovalHeaderUserLocalService
			approvalHeaderUserLocalService) {

		this.approvalHeaderUserLocalService = approvalHeaderUserLocalService;
	}

	/**
	 * Returns the approval header user persistence.
	 *
	 * @return the approval header user persistence
	 */
	public ApprovalHeaderUserPersistence getApprovalHeaderUserPersistence() {
		return approvalHeaderUserPersistence;
	}

	/**
	 * Sets the approval header user persistence.
	 *
	 * @param approvalHeaderUserPersistence the approval header user persistence
	 */
	public void setApprovalHeaderUserPersistence(
		ApprovalHeaderUserPersistence approvalHeaderUserPersistence) {

		this.approvalHeaderUserPersistence = approvalHeaderUserPersistence;
	}

	/**
	 * Returns the approval history user local service.
	 *
	 * @return the approval history user local service
	 */
	public com.astra.dewa.service.ApprovalHistoryUserLocalService
		getApprovalHistoryUserLocalService() {

		return approvalHistoryUserLocalService;
	}

	/**
	 * Sets the approval history user local service.
	 *
	 * @param approvalHistoryUserLocalService the approval history user local service
	 */
	public void setApprovalHistoryUserLocalService(
		com.astra.dewa.service.ApprovalHistoryUserLocalService
			approvalHistoryUserLocalService) {

		this.approvalHistoryUserLocalService = approvalHistoryUserLocalService;
	}

	/**
	 * Returns the approval history user persistence.
	 *
	 * @return the approval history user persistence
	 */
	public ApprovalHistoryUserPersistence getApprovalHistoryUserPersistence() {
		return approvalHistoryUserPersistence;
	}

	/**
	 * Sets the approval history user persistence.
	 *
	 * @param approvalHistoryUserPersistence the approval history user persistence
	 */
	public void setApprovalHistoryUserPersistence(
		ApprovalHistoryUserPersistence approvalHistoryUserPersistence) {

		this.approvalHistoryUserPersistence = approvalHistoryUserPersistence;
	}

	/**
	 * Returns the banner local service.
	 *
	 * @return the banner local service
	 */
	public com.astra.dewa.service.BannerLocalService getBannerLocalService() {
		return bannerLocalService;
	}

	/**
	 * Sets the banner local service.
	 *
	 * @param bannerLocalService the banner local service
	 */
	public void setBannerLocalService(
		com.astra.dewa.service.BannerLocalService bannerLocalService) {

		this.bannerLocalService = bannerLocalService;
	}

	/**
	 * Returns the banner persistence.
	 *
	 * @return the banner persistence
	 */
	public BannerPersistence getBannerPersistence() {
		return bannerPersistence;
	}

	/**
	 * Sets the banner persistence.
	 *
	 * @param bannerPersistence the banner persistence
	 */
	public void setBannerPersistence(BannerPersistence bannerPersistence) {
		this.bannerPersistence = bannerPersistence;
	}

	/**
	 * Returns the bulan local service.
	 *
	 * @return the bulan local service
	 */
	public com.astra.dewa.service.BulanLocalService getBulanLocalService() {
		return bulanLocalService;
	}

	/**
	 * Sets the bulan local service.
	 *
	 * @param bulanLocalService the bulan local service
	 */
	public void setBulanLocalService(
		com.astra.dewa.service.BulanLocalService bulanLocalService) {

		this.bulanLocalService = bulanLocalService;
	}

	/**
	 * Returns the bulan persistence.
	 *
	 * @return the bulan persistence
	 */
	public BulanPersistence getBulanPersistence() {
		return bulanPersistence;
	}

	/**
	 * Sets the bulan persistence.
	 *
	 * @param bulanPersistence the bulan persistence
	 */
	public void setBulanPersistence(BulanPersistence bulanPersistence) {
		this.bulanPersistence = bulanPersistence;
	}

	/**
	 * Returns the cabang local service.
	 *
	 * @return the cabang local service
	 */
	public com.astra.dewa.service.CabangLocalService getCabangLocalService() {
		return cabangLocalService;
	}

	/**
	 * Sets the cabang local service.
	 *
	 * @param cabangLocalService the cabang local service
	 */
	public void setCabangLocalService(
		com.astra.dewa.service.CabangLocalService cabangLocalService) {

		this.cabangLocalService = cabangLocalService;
	}

	/**
	 * Returns the cabang persistence.
	 *
	 * @return the cabang persistence
	 */
	public CabangPersistence getCabangPersistence() {
		return cabangPersistence;
	}

	/**
	 * Sets the cabang persistence.
	 *
	 * @param cabangPersistence the cabang persistence
	 */
	public void setCabangPersistence(CabangPersistence cabangPersistence) {
		this.cabangPersistence = cabangPersistence;
	}

	/**
	 * Returns the calender event local service.
	 *
	 * @return the calender event local service
	 */
	public com.astra.dewa.service.CalenderEventLocalService
		getCalenderEventLocalService() {

		return calenderEventLocalService;
	}

	/**
	 * Sets the calender event local service.
	 *
	 * @param calenderEventLocalService the calender event local service
	 */
	public void setCalenderEventLocalService(
		com.astra.dewa.service.CalenderEventLocalService
			calenderEventLocalService) {

		this.calenderEventLocalService = calenderEventLocalService;
	}

	/**
	 * Returns the calender event persistence.
	 *
	 * @return the calender event persistence
	 */
	public CalenderEventPersistence getCalenderEventPersistence() {
		return calenderEventPersistence;
	}

	/**
	 * Sets the calender event persistence.
	 *
	 * @param calenderEventPersistence the calender event persistence
	 */
	public void setCalenderEventPersistence(
		CalenderEventPersistence calenderEventPersistence) {

		this.calenderEventPersistence = calenderEventPersistence;
	}

	/**
	 * Returns the calender event participant local service.
	 *
	 * @return the calender event participant local service
	 */
	public com.astra.dewa.service.CalenderEventParticipantLocalService
		getCalenderEventParticipantLocalService() {

		return calenderEventParticipantLocalService;
	}

	/**
	 * Sets the calender event participant local service.
	 *
	 * @param calenderEventParticipantLocalService the calender event participant local service
	 */
	public void setCalenderEventParticipantLocalService(
		com.astra.dewa.service.CalenderEventParticipantLocalService
			calenderEventParticipantLocalService) {

		this.calenderEventParticipantLocalService =
			calenderEventParticipantLocalService;
	}

	/**
	 * Returns the calender event participant persistence.
	 *
	 * @return the calender event participant persistence
	 */
	public CalenderEventParticipantPersistence
		getCalenderEventParticipantPersistence() {

		return calenderEventParticipantPersistence;
	}

	/**
	 * Sets the calender event participant persistence.
	 *
	 * @param calenderEventParticipantPersistence the calender event participant persistence
	 */
	public void setCalenderEventParticipantPersistence(
		CalenderEventParticipantPersistence
			calenderEventParticipantPersistence) {

		this.calenderEventParticipantPersistence =
			calenderEventParticipantPersistence;
	}

	/**
	 * Returns the common local service.
	 *
	 * @return the common local service
	 */
	public com.astra.dewa.service.CommonLocalService getCommonLocalService() {
		return commonLocalService;
	}

	/**
	 * Sets the common local service.
	 *
	 * @param commonLocalService the common local service
	 */
	public void setCommonLocalService(
		com.astra.dewa.service.CommonLocalService commonLocalService) {

		this.commonLocalService = commonLocalService;
	}

	/**
	 * Returns the common persistence.
	 *
	 * @return the common persistence
	 */
	public CommonPersistence getCommonPersistence() {
		return commonPersistence;
	}

	/**
	 * Sets the common persistence.
	 *
	 * @param commonPersistence the common persistence
	 */
	public void setCommonPersistence(CommonPersistence commonPersistence) {
		this.commonPersistence = commonPersistence;
	}

	/**
	 * Returns the dealer local service.
	 *
	 * @return the dealer local service
	 */
	public com.astra.dewa.service.DealerLocalService getDealerLocalService() {
		return dealerLocalService;
	}

	/**
	 * Sets the dealer local service.
	 *
	 * @param dealerLocalService the dealer local service
	 */
	public void setDealerLocalService(
		com.astra.dewa.service.DealerLocalService dealerLocalService) {

		this.dealerLocalService = dealerLocalService;
	}

	/**
	 * Returns the dealer persistence.
	 *
	 * @return the dealer persistence
	 */
	public DealerPersistence getDealerPersistence() {
		return dealerPersistence;
	}

	/**
	 * Sets the dealer persistence.
	 *
	 * @param dealerPersistence the dealer persistence
	 */
	public void setDealerPersistence(DealerPersistence dealerPersistence) {
		this.dealerPersistence = dealerPersistence;
	}

	/**
	 * Returns the diskon fakpol local service.
	 *
	 * @return the diskon fakpol local service
	 */
	public com.astra.dewa.service.DiskonFakpolLocalService
		getDiskonFakpolLocalService() {

		return diskonFakpolLocalService;
	}

	/**
	 * Sets the diskon fakpol local service.
	 *
	 * @param diskonFakpolLocalService the diskon fakpol local service
	 */
	public void setDiskonFakpolLocalService(
		com.astra.dewa.service.DiskonFakpolLocalService
			diskonFakpolLocalService) {

		this.diskonFakpolLocalService = diskonFakpolLocalService;
	}

	/**
	 * Returns the diskon fakpol persistence.
	 *
	 * @return the diskon fakpol persistence
	 */
	public DiskonFakpolPersistence getDiskonFakpolPersistence() {
		return diskonFakpolPersistence;
	}

	/**
	 * Sets the diskon fakpol persistence.
	 *
	 * @param diskonFakpolPersistence the diskon fakpol persistence
	 */
	public void setDiskonFakpolPersistence(
		DiskonFakpolPersistence diskonFakpolPersistence) {

		this.diskonFakpolPersistence = diskonFakpolPersistence;
	}

	/**
	 * Returns the diskon fleet local service.
	 *
	 * @return the diskon fleet local service
	 */
	public DiskonFleetLocalService getDiskonFleetLocalService() {
		return diskonFleetLocalService;
	}

	/**
	 * Sets the diskon fleet local service.
	 *
	 * @param diskonFleetLocalService the diskon fleet local service
	 */
	public void setDiskonFleetLocalService(
		DiskonFleetLocalService diskonFleetLocalService) {

		this.diskonFleetLocalService = diskonFleetLocalService;
	}

	/**
	 * Returns the diskon fleet persistence.
	 *
	 * @return the diskon fleet persistence
	 */
	public DiskonFleetPersistence getDiskonFleetPersistence() {
		return diskonFleetPersistence;
	}

	/**
	 * Sets the diskon fleet persistence.
	 *
	 * @param diskonFleetPersistence the diskon fleet persistence
	 */
	public void setDiskonFleetPersistence(
		DiskonFleetPersistence diskonFleetPersistence) {

		this.diskonFleetPersistence = diskonFleetPersistence;
	}

	/**
	 * Returns the diskon other local service.
	 *
	 * @return the diskon other local service
	 */
	public com.astra.dewa.service.DiskonOtherLocalService
		getDiskonOtherLocalService() {

		return diskonOtherLocalService;
	}

	/**
	 * Sets the diskon other local service.
	 *
	 * @param diskonOtherLocalService the diskon other local service
	 */
	public void setDiskonOtherLocalService(
		com.astra.dewa.service.DiskonOtherLocalService
			diskonOtherLocalService) {

		this.diskonOtherLocalService = diskonOtherLocalService;
	}

	/**
	 * Returns the diskon other persistence.
	 *
	 * @return the diskon other persistence
	 */
	public DiskonOtherPersistence getDiskonOtherPersistence() {
		return diskonOtherPersistence;
	}

	/**
	 * Sets the diskon other persistence.
	 *
	 * @param diskonOtherPersistence the diskon other persistence
	 */
	public void setDiskonOtherPersistence(
		DiskonOtherPersistence diskonOtherPersistence) {

		this.diskonOtherPersistence = diskonOtherPersistence;
	}

	/**
	 * Returns the diskon test car local service.
	 *
	 * @return the diskon test car local service
	 */
	public com.astra.dewa.service.DiskonTestCarLocalService
		getDiskonTestCarLocalService() {

		return diskonTestCarLocalService;
	}

	/**
	 * Sets the diskon test car local service.
	 *
	 * @param diskonTestCarLocalService the diskon test car local service
	 */
	public void setDiskonTestCarLocalService(
		com.astra.dewa.service.DiskonTestCarLocalService
			diskonTestCarLocalService) {

		this.diskonTestCarLocalService = diskonTestCarLocalService;
	}

	/**
	 * Returns the diskon test car persistence.
	 *
	 * @return the diskon test car persistence
	 */
	public DiskonTestCarPersistence getDiskonTestCarPersistence() {
		return diskonTestCarPersistence;
	}

	/**
	 * Sets the diskon test car persistence.
	 *
	 * @param diskonTestCarPersistence the diskon test car persistence
	 */
	public void setDiskonTestCarPersistence(
		DiskonTestCarPersistence diskonTestCarPersistence) {

		this.diskonTestCarPersistence = diskonTestCarPersistence;
	}

	/**
	 * Returns the email domain local service.
	 *
	 * @return the email domain local service
	 */
	public com.astra.dewa.service.EmailDomainLocalService
		getEmailDomainLocalService() {

		return emailDomainLocalService;
	}

	/**
	 * Sets the email domain local service.
	 *
	 * @param emailDomainLocalService the email domain local service
	 */
	public void setEmailDomainLocalService(
		com.astra.dewa.service.EmailDomainLocalService
			emailDomainLocalService) {

		this.emailDomainLocalService = emailDomainLocalService;
	}

	/**
	 * Returns the email domain persistence.
	 *
	 * @return the email domain persistence
	 */
	public EmailDomainPersistence getEmailDomainPersistence() {
		return emailDomainPersistence;
	}

	/**
	 * Sets the email domain persistence.
	 *
	 * @param emailDomainPersistence the email domain persistence
	 */
	public void setEmailDomainPersistence(
		EmailDomainPersistence emailDomainPersistence) {

		this.emailDomainPersistence = emailDomainPersistence;
	}

	/**
	 * Returns the e srut local service.
	 *
	 * @return the e srut local service
	 */
	public com.astra.dewa.service.ESrutLocalService getESrutLocalService() {
		return eSrutLocalService;
	}

	/**
	 * Sets the e srut local service.
	 *
	 * @param eSrutLocalService the e srut local service
	 */
	public void setESrutLocalService(
		com.astra.dewa.service.ESrutLocalService eSrutLocalService) {

		this.eSrutLocalService = eSrutLocalService;
	}

	/**
	 * Returns the e srut persistence.
	 *
	 * @return the e srut persistence
	 */
	public ESrutPersistence getESrutPersistence() {
		return eSrutPersistence;
	}

	/**
	 * Sets the e srut persistence.
	 *
	 * @param eSrutPersistence the e srut persistence
	 */
	public void setESrutPersistence(ESrutPersistence eSrutPersistence) {
		this.eSrutPersistence = eSrutPersistence;
	}

	/**
	 * Returns the faktur indirect local service.
	 *
	 * @return the faktur indirect local service
	 */
	public com.astra.dewa.service.FakturIndirectLocalService
		getFakturIndirectLocalService() {

		return fakturIndirectLocalService;
	}

	/**
	 * Sets the faktur indirect local service.
	 *
	 * @param fakturIndirectLocalService the faktur indirect local service
	 */
	public void setFakturIndirectLocalService(
		com.astra.dewa.service.FakturIndirectLocalService
			fakturIndirectLocalService) {

		this.fakturIndirectLocalService = fakturIndirectLocalService;
	}

	/**
	 * Returns the faktur indirect persistence.
	 *
	 * @return the faktur indirect persistence
	 */
	public FakturIndirectPersistence getFakturIndirectPersistence() {
		return fakturIndirectPersistence;
	}

	/**
	 * Sets the faktur indirect persistence.
	 *
	 * @param fakturIndirectPersistence the faktur indirect persistence
	 */
	public void setFakturIndirectPersistence(
		FakturIndirectPersistence fakturIndirectPersistence) {

		this.fakturIndirectPersistence = fakturIndirectPersistence;
	}

	/**
	 * Returns the faktur pajak local service.
	 *
	 * @return the faktur pajak local service
	 */
	public com.astra.dewa.service.FakturPajakLocalService
		getFakturPajakLocalService() {

		return fakturPajakLocalService;
	}

	/**
	 * Sets the faktur pajak local service.
	 *
	 * @param fakturPajakLocalService the faktur pajak local service
	 */
	public void setFakturPajakLocalService(
		com.astra.dewa.service.FakturPajakLocalService
			fakturPajakLocalService) {

		this.fakturPajakLocalService = fakturPajakLocalService;
	}

	/**
	 * Returns the faktur pajak persistence.
	 *
	 * @return the faktur pajak persistence
	 */
	public FakturPajakPersistence getFakturPajakPersistence() {
		return fakturPajakPersistence;
	}

	/**
	 * Sets the faktur pajak persistence.
	 *
	 * @param fakturPajakPersistence the faktur pajak persistence
	 */
	public void setFakturPajakPersistence(
		FakturPajakPersistence fakturPajakPersistence) {

		this.fakturPajakPersistence = fakturPajakPersistence;
	}

	/**
	 * Returns the jenis materi local service.
	 *
	 * @return the jenis materi local service
	 */
	public com.astra.dewa.service.JenisMateriLocalService
		getJenisMateriLocalService() {

		return jenisMateriLocalService;
	}

	/**
	 * Sets the jenis materi local service.
	 *
	 * @param jenisMateriLocalService the jenis materi local service
	 */
	public void setJenisMateriLocalService(
		com.astra.dewa.service.JenisMateriLocalService
			jenisMateriLocalService) {

		this.jenisMateriLocalService = jenisMateriLocalService;
	}

	/**
	 * Returns the jenis materi persistence.
	 *
	 * @return the jenis materi persistence
	 */
	public JenisMateriPersistence getJenisMateriPersistence() {
		return jenisMateriPersistence;
	}

	/**
	 * Sets the jenis materi persistence.
	 *
	 * @param jenisMateriPersistence the jenis materi persistence
	 */
	public void setJenisMateriPersistence(
		JenisMateriPersistence jenisMateriPersistence) {

		this.jenisMateriPersistence = jenisMateriPersistence;
	}

	/**
	 * Returns the kategori dealer local service.
	 *
	 * @return the kategori dealer local service
	 */
	public com.astra.dewa.service.KategoriDealerLocalService
		getKategoriDealerLocalService() {

		return kategoriDealerLocalService;
	}

	/**
	 * Sets the kategori dealer local service.
	 *
	 * @param kategoriDealerLocalService the kategori dealer local service
	 */
	public void setKategoriDealerLocalService(
		com.astra.dewa.service.KategoriDealerLocalService
			kategoriDealerLocalService) {

		this.kategoriDealerLocalService = kategoriDealerLocalService;
	}

	/**
	 * Returns the kategori dealer persistence.
	 *
	 * @return the kategori dealer persistence
	 */
	public KategoriDealerPersistence getKategoriDealerPersistence() {
		return kategoriDealerPersistence;
	}

	/**
	 * Sets the kategori dealer persistence.
	 *
	 * @param kategoriDealerPersistence the kategori dealer persistence
	 */
	public void setKategoriDealerPersistence(
		KategoriDealerPersistence kategoriDealerPersistence) {

		this.kategoriDealerPersistence = kategoriDealerPersistence;
	}

	/**
	 * Returns the kuartal local service.
	 *
	 * @return the kuartal local service
	 */
	public com.astra.dewa.service.KuartalLocalService getKuartalLocalService() {
		return kuartalLocalService;
	}

	/**
	 * Sets the kuartal local service.
	 *
	 * @param kuartalLocalService the kuartal local service
	 */
	public void setKuartalLocalService(
		com.astra.dewa.service.KuartalLocalService kuartalLocalService) {

		this.kuartalLocalService = kuartalLocalService;
	}

	/**
	 * Returns the kuartal persistence.
	 *
	 * @return the kuartal persistence
	 */
	public KuartalPersistence getKuartalPersistence() {
		return kuartalPersistence;
	}

	/**
	 * Sets the kuartal persistence.
	 *
	 * @param kuartalPersistence the kuartal persistence
	 */
	public void setKuartalPersistence(KuartalPersistence kuartalPersistence) {
		this.kuartalPersistence = kuartalPersistence;
	}

	/**
	 * Returns the master approval local service.
	 *
	 * @return the master approval local service
	 */
	public com.astra.dewa.service.MasterApprovalLocalService
		getMasterApprovalLocalService() {

		return masterApprovalLocalService;
	}

	/**
	 * Sets the master approval local service.
	 *
	 * @param masterApprovalLocalService the master approval local service
	 */
	public void setMasterApprovalLocalService(
		com.astra.dewa.service.MasterApprovalLocalService
			masterApprovalLocalService) {

		this.masterApprovalLocalService = masterApprovalLocalService;
	}

	/**
	 * Returns the master approval persistence.
	 *
	 * @return the master approval persistence
	 */
	public MasterApprovalPersistence getMasterApprovalPersistence() {
		return masterApprovalPersistence;
	}

	/**
	 * Sets the master approval persistence.
	 *
	 * @param masterApprovalPersistence the master approval persistence
	 */
	public void setMasterApprovalPersistence(
		MasterApprovalPersistence masterApprovalPersistence) {

		this.masterApprovalPersistence = masterApprovalPersistence;
	}

	/**
	 * Returns the master approval detail local service.
	 *
	 * @return the master approval detail local service
	 */
	public com.astra.dewa.service.MasterApprovalDetailLocalService
		getMasterApprovalDetailLocalService() {

		return masterApprovalDetailLocalService;
	}

	/**
	 * Sets the master approval detail local service.
	 *
	 * @param masterApprovalDetailLocalService the master approval detail local service
	 */
	public void setMasterApprovalDetailLocalService(
		com.astra.dewa.service.MasterApprovalDetailLocalService
			masterApprovalDetailLocalService) {

		this.masterApprovalDetailLocalService =
			masterApprovalDetailLocalService;
	}

	/**
	 * Returns the master approval detail persistence.
	 *
	 * @return the master approval detail persistence
	 */
	public MasterApprovalDetailPersistence
		getMasterApprovalDetailPersistence() {

		return masterApprovalDetailPersistence;
	}

	/**
	 * Sets the master approval detail persistence.
	 *
	 * @param masterApprovalDetailPersistence the master approval detail persistence
	 */
	public void setMasterApprovalDetailPersistence(
		MasterApprovalDetailPersistence masterApprovalDetailPersistence) {

		this.masterApprovalDetailPersistence = masterApprovalDetailPersistence;
	}

	/**
	 * Returns the master approval detail journal local service.
	 *
	 * @return the master approval detail journal local service
	 */
	public com.astra.dewa.service.MasterApprovalDetailJournalLocalService
		getMasterApprovalDetailJournalLocalService() {

		return masterApprovalDetailJournalLocalService;
	}

	/**
	 * Sets the master approval detail journal local service.
	 *
	 * @param masterApprovalDetailJournalLocalService the master approval detail journal local service
	 */
	public void setMasterApprovalDetailJournalLocalService(
		com.astra.dewa.service.MasterApprovalDetailJournalLocalService
			masterApprovalDetailJournalLocalService) {

		this.masterApprovalDetailJournalLocalService =
			masterApprovalDetailJournalLocalService;
	}

	/**
	 * Returns the master approval detail journal persistence.
	 *
	 * @return the master approval detail journal persistence
	 */
	public MasterApprovalDetailJournalPersistence
		getMasterApprovalDetailJournalPersistence() {

		return masterApprovalDetailJournalPersistence;
	}

	/**
	 * Sets the master approval detail journal persistence.
	 *
	 * @param masterApprovalDetailJournalPersistence the master approval detail journal persistence
	 */
	public void setMasterApprovalDetailJournalPersistence(
		MasterApprovalDetailJournalPersistence
			masterApprovalDetailJournalPersistence) {

		this.masterApprovalDetailJournalPersistence =
			masterApprovalDetailJournalPersistence;
	}

	/**
	 * Returns the master approval journal local service.
	 *
	 * @return the master approval journal local service
	 */
	public com.astra.dewa.service.MasterApprovalJournalLocalService
		getMasterApprovalJournalLocalService() {

		return masterApprovalJournalLocalService;
	}

	/**
	 * Sets the master approval journal local service.
	 *
	 * @param masterApprovalJournalLocalService the master approval journal local service
	 */
	public void setMasterApprovalJournalLocalService(
		com.astra.dewa.service.MasterApprovalJournalLocalService
			masterApprovalJournalLocalService) {

		this.masterApprovalJournalLocalService =
			masterApprovalJournalLocalService;
	}

	/**
	 * Returns the master approval journal persistence.
	 *
	 * @return the master approval journal persistence
	 */
	public MasterApprovalJournalPersistence
		getMasterApprovalJournalPersistence() {

		return masterApprovalJournalPersistence;
	}

	/**
	 * Sets the master approval journal persistence.
	 *
	 * @param masterApprovalJournalPersistence the master approval journal persistence
	 */
	public void setMasterApprovalJournalPersistence(
		MasterApprovalJournalPersistence masterApprovalJournalPersistence) {

		this.masterApprovalJournalPersistence =
			masterApprovalJournalPersistence;
	}

	/**
	 * Returns the menu authorization local service.
	 *
	 * @return the menu authorization local service
	 */
	public com.astra.dewa.service.MenuAuthorizationLocalService
		getMenuAuthorizationLocalService() {

		return menuAuthorizationLocalService;
	}

	/**
	 * Sets the menu authorization local service.
	 *
	 * @param menuAuthorizationLocalService the menu authorization local service
	 */
	public void setMenuAuthorizationLocalService(
		com.astra.dewa.service.MenuAuthorizationLocalService
			menuAuthorizationLocalService) {

		this.menuAuthorizationLocalService = menuAuthorizationLocalService;
	}

	/**
	 * Returns the menu authorization persistence.
	 *
	 * @return the menu authorization persistence
	 */
	public MenuAuthorizationPersistence getMenuAuthorizationPersistence() {
		return menuAuthorizationPersistence;
	}

	/**
	 * Sets the menu authorization persistence.
	 *
	 * @param menuAuthorizationPersistence the menu authorization persistence
	 */
	public void setMenuAuthorizationPersistence(
		MenuAuthorizationPersistence menuAuthorizationPersistence) {

		this.menuAuthorizationPersistence = menuAuthorizationPersistence;
	}

	/**
	 * Returns the otp local service.
	 *
	 * @return the otp local service
	 */
	public com.astra.dewa.service.OTPLocalService getOTPLocalService() {
		return otpLocalService;
	}

	/**
	 * Sets the otp local service.
	 *
	 * @param otpLocalService the otp local service
	 */
	public void setOTPLocalService(
		com.astra.dewa.service.OTPLocalService otpLocalService) {

		this.otpLocalService = otpLocalService;
	}

	/**
	 * Returns the otp persistence.
	 *
	 * @return the otp persistence
	 */
	public OTPPersistence getOTPPersistence() {
		return otpPersistence;
	}

	/**
	 * Sets the otp persistence.
	 *
	 * @param otpPersistence the otp persistence
	 */
	public void setOTPPersistence(OTPPersistence otpPersistence) {
		this.otpPersistence = otpPersistence;
	}

	/**
	 * Returns the periode review local service.
	 *
	 * @return the periode review local service
	 */
	public com.astra.dewa.service.PeriodeReviewLocalService
		getPeriodeReviewLocalService() {

		return periodeReviewLocalService;
	}

	/**
	 * Sets the periode review local service.
	 *
	 * @param periodeReviewLocalService the periode review local service
	 */
	public void setPeriodeReviewLocalService(
		com.astra.dewa.service.PeriodeReviewLocalService
			periodeReviewLocalService) {

		this.periodeReviewLocalService = periodeReviewLocalService;
	}

	/**
	 * Returns the periode review persistence.
	 *
	 * @return the periode review persistence
	 */
	public PeriodeReviewPersistence getPeriodeReviewPersistence() {
		return periodeReviewPersistence;
	}

	/**
	 * Sets the periode review persistence.
	 *
	 * @param periodeReviewPersistence the periode review persistence
	 */
	public void setPeriodeReviewPersistence(
		PeriodeReviewPersistence periodeReviewPersistence) {

		this.periodeReviewPersistence = periodeReviewPersistence;
	}

	/**
	 * Returns the report plafond local service.
	 *
	 * @return the report plafond local service
	 */
	public com.astra.dewa.service.ReportPlafondLocalService
		getReportPlafondLocalService() {

		return reportPlafondLocalService;
	}

	/**
	 * Sets the report plafond local service.
	 *
	 * @param reportPlafondLocalService the report plafond local service
	 */
	public void setReportPlafondLocalService(
		com.astra.dewa.service.ReportPlafondLocalService
			reportPlafondLocalService) {

		this.reportPlafondLocalService = reportPlafondLocalService;
	}

	/**
	 * Returns the report plafond persistence.
	 *
	 * @return the report plafond persistence
	 */
	public ReportPlafondPersistence getReportPlafondPersistence() {
		return reportPlafondPersistence;
	}

	/**
	 * Sets the report plafond persistence.
	 *
	 * @param reportPlafondPersistence the report plafond persistence
	 */
	public void setReportPlafondPersistence(
		ReportPlafondPersistence reportPlafondPersistence) {

		this.reportPlafondPersistence = reportPlafondPersistence;
	}

	/**
	 * Returns the roles local service.
	 *
	 * @return the roles local service
	 */
	public com.astra.dewa.service.RolesLocalService getRolesLocalService() {
		return rolesLocalService;
	}

	/**
	 * Sets the roles local service.
	 *
	 * @param rolesLocalService the roles local service
	 */
	public void setRolesLocalService(
		com.astra.dewa.service.RolesLocalService rolesLocalService) {

		this.rolesLocalService = rolesLocalService;
	}

	/**
	 * Returns the roles persistence.
	 *
	 * @return the roles persistence
	 */
	public RolesPersistence getRolesPersistence() {
		return rolesPersistence;
	}

	/**
	 * Sets the roles persistence.
	 *
	 * @param rolesPersistence the roles persistence
	 */
	public void setRolesPersistence(RolesPersistence rolesPersistence) {
		this.rolesPersistence = rolesPersistence;
	}

	/**
	 * Returns the sales program local service.
	 *
	 * @return the sales program local service
	 */
	public com.astra.dewa.service.SalesProgramLocalService
		getSalesProgramLocalService() {

		return salesProgramLocalService;
	}

	/**
	 * Sets the sales program local service.
	 *
	 * @param salesProgramLocalService the sales program local service
	 */
	public void setSalesProgramLocalService(
		com.astra.dewa.service.SalesProgramLocalService
			salesProgramLocalService) {

		this.salesProgramLocalService = salesProgramLocalService;
	}

	/**
	 * Returns the sales program persistence.
	 *
	 * @return the sales program persistence
	 */
	public SalesProgramPersistence getSalesProgramPersistence() {
		return salesProgramPersistence;
	}

	/**
	 * Sets the sales program persistence.
	 *
	 * @param salesProgramPersistence the sales program persistence
	 */
	public void setSalesProgramPersistence(
		SalesProgramPersistence salesProgramPersistence) {

		this.salesProgramPersistence = salesProgramPersistence;
	}

	/**
	 * Returns the sales report local service.
	 *
	 * @return the sales report local service
	 */
	public com.astra.dewa.service.SalesReportLocalService
		getSalesReportLocalService() {

		return salesReportLocalService;
	}

	/**
	 * Sets the sales report local service.
	 *
	 * @param salesReportLocalService the sales report local service
	 */
	public void setSalesReportLocalService(
		com.astra.dewa.service.SalesReportLocalService
			salesReportLocalService) {

		this.salesReportLocalService = salesReportLocalService;
	}

	/**
	 * Returns the sales report persistence.
	 *
	 * @return the sales report persistence
	 */
	public SalesReportPersistence getSalesReportPersistence() {
		return salesReportPersistence;
	}

	/**
	 * Sets the sales report persistence.
	 *
	 * @param salesReportPersistence the sales report persistence
	 */
	public void setSalesReportPersistence(
		SalesReportPersistence salesReportPersistence) {

		this.salesReportPersistence = salesReportPersistence;
	}

	/**
	 * Returns the setting local service.
	 *
	 * @return the setting local service
	 */
	public com.astra.dewa.service.SettingLocalService getSettingLocalService() {
		return settingLocalService;
	}

	/**
	 * Sets the setting local service.
	 *
	 * @param settingLocalService the setting local service
	 */
	public void setSettingLocalService(
		com.astra.dewa.service.SettingLocalService settingLocalService) {

		this.settingLocalService = settingLocalService;
	}

	/**
	 * Returns the setting persistence.
	 *
	 * @return the setting persistence
	 */
	public SettingPersistence getSettingPersistence() {
		return settingPersistence;
	}

	/**
	 * Sets the setting persistence.
	 *
	 * @param settingPersistence the setting persistence
	 */
	public void setSettingPersistence(SettingPersistence settingPersistence) {
		this.settingPersistence = settingPersistence;
	}

	/**
	 * Returns the sk iris local service.
	 *
	 * @return the sk iris local service
	 */
	public com.astra.dewa.service.SkIrisLocalService getSkIrisLocalService() {
		return skIrisLocalService;
	}

	/**
	 * Sets the sk iris local service.
	 *
	 * @param skIrisLocalService the sk iris local service
	 */
	public void setSkIrisLocalService(
		com.astra.dewa.service.SkIrisLocalService skIrisLocalService) {

		this.skIrisLocalService = skIrisLocalService;
	}

	/**
	 * Returns the sk iris persistence.
	 *
	 * @return the sk iris persistence
	 */
	public SkIrisPersistence getSkIrisPersistence() {
		return skIrisPersistence;
	}

	/**
	 * Sets the sk iris persistence.
	 *
	 * @param skIrisPersistence the sk iris persistence
	 */
	public void setSkIrisPersistence(SkIrisPersistence skIrisPersistence) {
		this.skIrisPersistence = skIrisPersistence;
	}

	/**
	 * Returns the sp3d local service.
	 *
	 * @return the sp3d local service
	 */
	public com.astra.dewa.service.SP3DLocalService getSP3DLocalService() {
		return sp3dLocalService;
	}

	/**
	 * Sets the sp3d local service.
	 *
	 * @param sp3dLocalService the sp3d local service
	 */
	public void setSP3DLocalService(
		com.astra.dewa.service.SP3DLocalService sp3dLocalService) {

		this.sp3dLocalService = sp3dLocalService;
	}

	/**
	 * Returns the sp3d persistence.
	 *
	 * @return the sp3d persistence
	 */
	public SP3DPersistence getSP3DPersistence() {
		return sp3dPersistence;
	}

	/**
	 * Sets the sp3d persistence.
	 *
	 * @param sp3dPersistence the sp3d persistence
	 */
	public void setSP3DPersistence(SP3DPersistence sp3dPersistence) {
		this.sp3dPersistence = sp3dPersistence;
	}

	/**
	 * Returns the surat penalty stock local service.
	 *
	 * @return the surat penalty stock local service
	 */
	public com.astra.dewa.service.SuratPenaltyStockLocalService
		getSuratPenaltyStockLocalService() {

		return suratPenaltyStockLocalService;
	}

	/**
	 * Sets the surat penalty stock local service.
	 *
	 * @param suratPenaltyStockLocalService the surat penalty stock local service
	 */
	public void setSuratPenaltyStockLocalService(
		com.astra.dewa.service.SuratPenaltyStockLocalService
			suratPenaltyStockLocalService) {

		this.suratPenaltyStockLocalService = suratPenaltyStockLocalService;
	}

	/**
	 * Returns the surat penalty stock persistence.
	 *
	 * @return the surat penalty stock persistence
	 */
	public SuratPenaltyStockPersistence getSuratPenaltyStockPersistence() {
		return suratPenaltyStockPersistence;
	}

	/**
	 * Sets the surat penalty stock persistence.
	 *
	 * @param suratPenaltyStockPersistence the surat penalty stock persistence
	 */
	public void setSuratPenaltyStockPersistence(
		SuratPenaltyStockPersistence suratPenaltyStockPersistence) {

		this.suratPenaltyStockPersistence = suratPenaltyStockPersistence;
	}

	/**
	 * Returns the tahun local service.
	 *
	 * @return the tahun local service
	 */
	public com.astra.dewa.service.TahunLocalService getTahunLocalService() {
		return tahunLocalService;
	}

	/**
	 * Sets the tahun local service.
	 *
	 * @param tahunLocalService the tahun local service
	 */
	public void setTahunLocalService(
		com.astra.dewa.service.TahunLocalService tahunLocalService) {

		this.tahunLocalService = tahunLocalService;
	}

	/**
	 * Returns the tahun persistence.
	 *
	 * @return the tahun persistence
	 */
	public TahunPersistence getTahunPersistence() {
		return tahunPersistence;
	}

	/**
	 * Sets the tahun persistence.
	 *
	 * @param tahunPersistence the tahun persistence
	 */
	public void setTahunPersistence(TahunPersistence tahunPersistence) {
		this.tahunPersistence = tahunPersistence;
	}

	/**
	 * Returns the ticket local service.
	 *
	 * @return the ticket local service
	 */
	public com.astra.dewa.service.TicketLocalService getTicketLocalService() {
		return ticketLocalService;
	}

	/**
	 * Sets the ticket local service.
	 *
	 * @param ticketLocalService the ticket local service
	 */
	public void setTicketLocalService(
		com.astra.dewa.service.TicketLocalService ticketLocalService) {

		this.ticketLocalService = ticketLocalService;
	}

	/**
	 * Returns the ticket persistence.
	 *
	 * @return the ticket persistence
	 */
	public TicketPersistence getTicketPersistence() {
		return ticketPersistence;
	}

	/**
	 * Sets the ticket persistence.
	 *
	 * @param ticketPersistence the ticket persistence
	 */
	public void setTicketPersistence(TicketPersistence ticketPersistence) {
		this.ticketPersistence = ticketPersistence;
	}

	/**
	 * Returns the tipe kendaraan local service.
	 *
	 * @return the tipe kendaraan local service
	 */
	public com.astra.dewa.service.TipeKendaraanLocalService
		getTipeKendaraanLocalService() {

		return tipeKendaraanLocalService;
	}

	/**
	 * Sets the tipe kendaraan local service.
	 *
	 * @param tipeKendaraanLocalService the tipe kendaraan local service
	 */
	public void setTipeKendaraanLocalService(
		com.astra.dewa.service.TipeKendaraanLocalService
			tipeKendaraanLocalService) {

		this.tipeKendaraanLocalService = tipeKendaraanLocalService;
	}

	/**
	 * Returns the tipe kendaraan persistence.
	 *
	 * @return the tipe kendaraan persistence
	 */
	public TipeKendaraanPersistence getTipeKendaraanPersistence() {
		return tipeKendaraanPersistence;
	}

	/**
	 * Sets the tipe kendaraan persistence.
	 *
	 * @param tipeKendaraanPersistence the tipe kendaraan persistence
	 */
	public void setTipeKendaraanPersistence(
		TipeKendaraanPersistence tipeKendaraanPersistence) {

		this.tipeKendaraanPersistence = tipeKendaraanPersistence;
	}

	/**
	 * Returns the token local service.
	 *
	 * @return the token local service
	 */
	public com.astra.dewa.service.TokenLocalService getTokenLocalService() {
		return tokenLocalService;
	}

	/**
	 * Sets the token local service.
	 *
	 * @param tokenLocalService the token local service
	 */
	public void setTokenLocalService(
		com.astra.dewa.service.TokenLocalService tokenLocalService) {

		this.tokenLocalService = tokenLocalService;
	}

	/**
	 * Returns the token persistence.
	 *
	 * @return the token persistence
	 */
	public TokenPersistence getTokenPersistence() {
		return tokenPersistence;
	}

	/**
	 * Sets the token persistence.
	 *
	 * @param tokenPersistence the token persistence
	 */
	public void setTokenPersistence(TokenPersistence tokenPersistence) {
		this.tokenPersistence = tokenPersistence;
	}

	/**
	 * Returns the topik materi local service.
	 *
	 * @return the topik materi local service
	 */
	public com.astra.dewa.service.TopikMateriLocalService
		getTopikMateriLocalService() {

		return topikMateriLocalService;
	}

	/**
	 * Sets the topik materi local service.
	 *
	 * @param topikMateriLocalService the topik materi local service
	 */
	public void setTopikMateriLocalService(
		com.astra.dewa.service.TopikMateriLocalService
			topikMateriLocalService) {

		this.topikMateriLocalService = topikMateriLocalService;
	}

	/**
	 * Returns the topik materi persistence.
	 *
	 * @return the topik materi persistence
	 */
	public TopikMateriPersistence getTopikMateriPersistence() {
		return topikMateriPersistence;
	}

	/**
	 * Sets the topik materi persistence.
	 *
	 * @param topikMateriPersistence the topik materi persistence
	 */
	public void setTopikMateriPersistence(
		TopikMateriPersistence topikMateriPersistence) {

		this.topikMateriPersistence = topikMateriPersistence;
	}

	/**
	 * Returns the training agenda local service.
	 *
	 * @return the training agenda local service
	 */
	public com.astra.dewa.service.TrainingAgendaLocalService
		getTrainingAgendaLocalService() {

		return trainingAgendaLocalService;
	}

	/**
	 * Sets the training agenda local service.
	 *
	 * @param trainingAgendaLocalService the training agenda local service
	 */
	public void setTrainingAgendaLocalService(
		com.astra.dewa.service.TrainingAgendaLocalService
			trainingAgendaLocalService) {

		this.trainingAgendaLocalService = trainingAgendaLocalService;
	}

	/**
	 * Returns the training agenda persistence.
	 *
	 * @return the training agenda persistence
	 */
	public TrainingAgendaPersistence getTrainingAgendaPersistence() {
		return trainingAgendaPersistence;
	}

	/**
	 * Sets the training agenda persistence.
	 *
	 * @param trainingAgendaPersistence the training agenda persistence
	 */
	public void setTrainingAgendaPersistence(
		TrainingAgendaPersistence trainingAgendaPersistence) {

		this.trainingAgendaPersistence = trainingAgendaPersistence;
	}

	/**
	 * Returns the training agenda participant local service.
	 *
	 * @return the training agenda participant local service
	 */
	public com.astra.dewa.service.TrainingAgendaParticipantLocalService
		getTrainingAgendaParticipantLocalService() {

		return trainingAgendaParticipantLocalService;
	}

	/**
	 * Sets the training agenda participant local service.
	 *
	 * @param trainingAgendaParticipantLocalService the training agenda participant local service
	 */
	public void setTrainingAgendaParticipantLocalService(
		com.astra.dewa.service.TrainingAgendaParticipantLocalService
			trainingAgendaParticipantLocalService) {

		this.trainingAgendaParticipantLocalService =
			trainingAgendaParticipantLocalService;
	}

	/**
	 * Returns the training agenda participant persistence.
	 *
	 * @return the training agenda participant persistence
	 */
	public TrainingAgendaParticipantPersistence
		getTrainingAgendaParticipantPersistence() {

		return trainingAgendaParticipantPersistence;
	}

	/**
	 * Sets the training agenda participant persistence.
	 *
	 * @param trainingAgendaParticipantPersistence the training agenda participant persistence
	 */
	public void setTrainingAgendaParticipantPersistence(
		TrainingAgendaParticipantPersistence
			trainingAgendaParticipantPersistence) {

		this.trainingAgendaParticipantPersistence =
			trainingAgendaParticipantPersistence;
	}

	/**
	 * Returns the training agenda participant uf local service.
	 *
	 * @return the training agenda participant uf local service
	 */
	public com.astra.dewa.service.TrainingAgendaParticipantUfLocalService
		getTrainingAgendaParticipantUfLocalService() {

		return trainingAgendaParticipantUfLocalService;
	}

	/**
	 * Sets the training agenda participant uf local service.
	 *
	 * @param trainingAgendaParticipantUfLocalService the training agenda participant uf local service
	 */
	public void setTrainingAgendaParticipantUfLocalService(
		com.astra.dewa.service.TrainingAgendaParticipantUfLocalService
			trainingAgendaParticipantUfLocalService) {

		this.trainingAgendaParticipantUfLocalService =
			trainingAgendaParticipantUfLocalService;
	}

	/**
	 * Returns the training agenda participant uf persistence.
	 *
	 * @return the training agenda participant uf persistence
	 */
	public TrainingAgendaParticipantUfPersistence
		getTrainingAgendaParticipantUfPersistence() {

		return trainingAgendaParticipantUfPersistence;
	}

	/**
	 * Sets the training agenda participant uf persistence.
	 *
	 * @param trainingAgendaParticipantUfPersistence the training agenda participant uf persistence
	 */
	public void setTrainingAgendaParticipantUfPersistence(
		TrainingAgendaParticipantUfPersistence
			trainingAgendaParticipantUfPersistence) {

		this.trainingAgendaParticipantUfPersistence =
			trainingAgendaParticipantUfPersistence;
	}

	/**
	 * Returns the training materi local service.
	 *
	 * @return the training materi local service
	 */
	public com.astra.dewa.service.TrainingMateriLocalService
		getTrainingMateriLocalService() {

		return trainingMateriLocalService;
	}

	/**
	 * Sets the training materi local service.
	 *
	 * @param trainingMateriLocalService the training materi local service
	 */
	public void setTrainingMateriLocalService(
		com.astra.dewa.service.TrainingMateriLocalService
			trainingMateriLocalService) {

		this.trainingMateriLocalService = trainingMateriLocalService;
	}

	/**
	 * Returns the training materi persistence.
	 *
	 * @return the training materi persistence
	 */
	public TrainingMateriPersistence getTrainingMateriPersistence() {
		return trainingMateriPersistence;
	}

	/**
	 * Sets the training materi persistence.
	 *
	 * @param trainingMateriPersistence the training materi persistence
	 */
	public void setTrainingMateriPersistence(
		TrainingMateriPersistence trainingMateriPersistence) {

		this.trainingMateriPersistence = trainingMateriPersistence;
	}

	/**
	 * Returns the training materi lampiran local service.
	 *
	 * @return the training materi lampiran local service
	 */
	public com.astra.dewa.service.TrainingMateriLampiranLocalService
		getTrainingMateriLampiranLocalService() {

		return trainingMateriLampiranLocalService;
	}

	/**
	 * Sets the training materi lampiran local service.
	 *
	 * @param trainingMateriLampiranLocalService the training materi lampiran local service
	 */
	public void setTrainingMateriLampiranLocalService(
		com.astra.dewa.service.TrainingMateriLampiranLocalService
			trainingMateriLampiranLocalService) {

		this.trainingMateriLampiranLocalService =
			trainingMateriLampiranLocalService;
	}

	/**
	 * Returns the training materi lampiran persistence.
	 *
	 * @return the training materi lampiran persistence
	 */
	public TrainingMateriLampiranPersistence
		getTrainingMateriLampiranPersistence() {

		return trainingMateriLampiranPersistence;
	}

	/**
	 * Sets the training materi lampiran persistence.
	 *
	 * @param trainingMateriLampiranPersistence the training materi lampiran persistence
	 */
	public void setTrainingMateriLampiranPersistence(
		TrainingMateriLampiranPersistence trainingMateriLampiranPersistence) {

		this.trainingMateriLampiranPersistence =
			trainingMateriLampiranPersistence;
	}

	/**
	 * Returns the user role type local service.
	 *
	 * @return the user role type local service
	 */
	public com.astra.dewa.service.UserRoleTypeLocalService
		getUserRoleTypeLocalService() {

		return userRoleTypeLocalService;
	}

	/**
	 * Sets the user role type local service.
	 *
	 * @param userRoleTypeLocalService the user role type local service
	 */
	public void setUserRoleTypeLocalService(
		com.astra.dewa.service.UserRoleTypeLocalService
			userRoleTypeLocalService) {

		this.userRoleTypeLocalService = userRoleTypeLocalService;
	}

	/**
	 * Returns the user role type persistence.
	 *
	 * @return the user role type persistence
	 */
	public UserRoleTypePersistence getUserRoleTypePersistence() {
		return userRoleTypePersistence;
	}

	/**
	 * Sets the user role type persistence.
	 *
	 * @param userRoleTypePersistence the user role type persistence
	 */
	public void setUserRoleTypePersistence(
		UserRoleTypePersistence userRoleTypePersistence) {

		this.userRoleTypePersistence = userRoleTypePersistence;
	}

	/**
	 * Returns the users dealers local service.
	 *
	 * @return the users dealers local service
	 */
	public com.astra.dewa.service.UsersDealersLocalService
		getUsersDealersLocalService() {

		return usersDealersLocalService;
	}

	/**
	 * Sets the users dealers local service.
	 *
	 * @param usersDealersLocalService the users dealers local service
	 */
	public void setUsersDealersLocalService(
		com.astra.dewa.service.UsersDealersLocalService
			usersDealersLocalService) {

		this.usersDealersLocalService = usersDealersLocalService;
	}

	/**
	 * Returns the users dealers persistence.
	 *
	 * @return the users dealers persistence
	 */
	public UsersDealersPersistence getUsersDealersPersistence() {
		return usersDealersPersistence;
	}

	/**
	 * Sets the users dealers persistence.
	 *
	 * @param usersDealersPersistence the users dealers persistence
	 */
	public void setUsersDealersPersistence(
		UsersDealersPersistence usersDealersPersistence) {

		this.usersDealersPersistence = usersDealersPersistence;
	}

	/**
	 * Returns the wilayah local service.
	 *
	 * @return the wilayah local service
	 */
	public com.astra.dewa.service.WilayahLocalService getWilayahLocalService() {
		return wilayahLocalService;
	}

	/**
	 * Sets the wilayah local service.
	 *
	 * @param wilayahLocalService the wilayah local service
	 */
	public void setWilayahLocalService(
		com.astra.dewa.service.WilayahLocalService wilayahLocalService) {

		this.wilayahLocalService = wilayahLocalService;
	}

	/**
	 * Returns the wilayah persistence.
	 *
	 * @return the wilayah persistence
	 */
	public WilayahPersistence getWilayahPersistence() {
		return wilayahPersistence;
	}

	/**
	 * Sets the wilayah persistence.
	 *
	 * @param wilayahPersistence the wilayah persistence
	 */
	public void setWilayahPersistence(WilayahPersistence wilayahPersistence) {
		this.wilayahPersistence = wilayahPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.astra.dewa.model.DiskonFleet", diskonFleetLocalService);

		_setLocalServiceUtilService(diskonFleetLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.astra.dewa.model.DiskonFleet");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DiskonFleetLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DiskonFleet.class;
	}

	protected String getModelClassName() {
		return DiskonFleet.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = diskonFleetPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		DiskonFleetLocalService diskonFleetLocalService) {

		try {
			Field field = DiskonFleetLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, diskonFleetLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = com.astra.dewa.service.ApplicationLocalService.class)
	protected com.astra.dewa.service.ApplicationLocalService
		applicationLocalService;

	@BeanReference(type = ApplicationPersistence.class)
	protected ApplicationPersistence applicationPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApplicationAssignLocalService.class
	)
	protected com.astra.dewa.service.ApplicationAssignLocalService
		applicationAssignLocalService;

	@BeanReference(type = ApplicationAssignPersistence.class)
	protected ApplicationAssignPersistence applicationAssignPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApplicationAssignJournalLocalService.class
	)
	protected com.astra.dewa.service.ApplicationAssignJournalLocalService
		applicationAssignJournalLocalService;

	@BeanReference(type = ApplicationAssignJournalPersistence.class)
	protected ApplicationAssignJournalPersistence
		applicationAssignJournalPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApplicationAssignStatusLocalService.class
	)
	protected com.astra.dewa.service.ApplicationAssignStatusLocalService
		applicationAssignStatusLocalService;

	@BeanReference(type = ApplicationAssignStatusPersistence.class)
	protected ApplicationAssignStatusPersistence
		applicationAssignStatusPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApplicationCategoryLocalService.class
	)
	protected com.astra.dewa.service.ApplicationCategoryLocalService
		applicationCategoryLocalService;

	@BeanReference(type = ApplicationCategoryPersistence.class)
	protected ApplicationCategoryPersistence applicationCategoryPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApplicationHeaderLocalService.class
	)
	protected com.astra.dewa.service.ApplicationHeaderLocalService
		applicationHeaderLocalService;

	@BeanReference(type = ApplicationHeaderPersistence.class)
	protected ApplicationHeaderPersistence applicationHeaderPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApplicationHeaderJournalLocalService.class
	)
	protected com.astra.dewa.service.ApplicationHeaderJournalLocalService
		applicationHeaderJournalLocalService;

	@BeanReference(type = ApplicationHeaderJournalPersistence.class)
	protected ApplicationHeaderJournalPersistence
		applicationHeaderJournalPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApplicationHeaderStatusLocalService.class
	)
	protected com.astra.dewa.service.ApplicationHeaderStatusLocalService
		applicationHeaderStatusLocalService;

	@BeanReference(type = ApplicationHeaderStatusPersistence.class)
	protected ApplicationHeaderStatusPersistence
		applicationHeaderStatusPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApprovalDetailUserLocalService.class
	)
	protected com.astra.dewa.service.ApprovalDetailUserLocalService
		approvalDetailUserLocalService;

	@BeanReference(type = ApprovalDetailUserPersistence.class)
	protected ApprovalDetailUserPersistence approvalDetailUserPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApprovalHeaderUserLocalService.class
	)
	protected com.astra.dewa.service.ApprovalHeaderUserLocalService
		approvalHeaderUserLocalService;

	@BeanReference(type = ApprovalHeaderUserPersistence.class)
	protected ApprovalHeaderUserPersistence approvalHeaderUserPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ApprovalHistoryUserLocalService.class
	)
	protected com.astra.dewa.service.ApprovalHistoryUserLocalService
		approvalHistoryUserLocalService;

	@BeanReference(type = ApprovalHistoryUserPersistence.class)
	protected ApprovalHistoryUserPersistence approvalHistoryUserPersistence;

	@BeanReference(type = com.astra.dewa.service.BannerLocalService.class)
	protected com.astra.dewa.service.BannerLocalService bannerLocalService;

	@BeanReference(type = BannerPersistence.class)
	protected BannerPersistence bannerPersistence;

	@BeanReference(type = com.astra.dewa.service.BulanLocalService.class)
	protected com.astra.dewa.service.BulanLocalService bulanLocalService;

	@BeanReference(type = BulanPersistence.class)
	protected BulanPersistence bulanPersistence;

	@BeanReference(type = com.astra.dewa.service.CabangLocalService.class)
	protected com.astra.dewa.service.CabangLocalService cabangLocalService;

	@BeanReference(type = CabangPersistence.class)
	protected CabangPersistence cabangPersistence;

	@BeanReference(
		type = com.astra.dewa.service.CalenderEventLocalService.class
	)
	protected com.astra.dewa.service.CalenderEventLocalService
		calenderEventLocalService;

	@BeanReference(type = CalenderEventPersistence.class)
	protected CalenderEventPersistence calenderEventPersistence;

	@BeanReference(
		type = com.astra.dewa.service.CalenderEventParticipantLocalService.class
	)
	protected com.astra.dewa.service.CalenderEventParticipantLocalService
		calenderEventParticipantLocalService;

	@BeanReference(type = CalenderEventParticipantPersistence.class)
	protected CalenderEventParticipantPersistence
		calenderEventParticipantPersistence;

	@BeanReference(type = com.astra.dewa.service.CommonLocalService.class)
	protected com.astra.dewa.service.CommonLocalService commonLocalService;

	@BeanReference(type = CommonPersistence.class)
	protected CommonPersistence commonPersistence;

	@BeanReference(type = com.astra.dewa.service.DealerLocalService.class)
	protected com.astra.dewa.service.DealerLocalService dealerLocalService;

	@BeanReference(type = DealerPersistence.class)
	protected DealerPersistence dealerPersistence;

	@BeanReference(type = com.astra.dewa.service.DiskonFakpolLocalService.class)
	protected com.astra.dewa.service.DiskonFakpolLocalService
		diskonFakpolLocalService;

	@BeanReference(type = DiskonFakpolPersistence.class)
	protected DiskonFakpolPersistence diskonFakpolPersistence;

	@BeanReference(type = DiskonFleetLocalService.class)
	protected DiskonFleetLocalService diskonFleetLocalService;

	@BeanReference(type = DiskonFleetPersistence.class)
	protected DiskonFleetPersistence diskonFleetPersistence;

	@BeanReference(type = com.astra.dewa.service.DiskonOtherLocalService.class)
	protected com.astra.dewa.service.DiskonOtherLocalService
		diskonOtherLocalService;

	@BeanReference(type = DiskonOtherPersistence.class)
	protected DiskonOtherPersistence diskonOtherPersistence;

	@BeanReference(
		type = com.astra.dewa.service.DiskonTestCarLocalService.class
	)
	protected com.astra.dewa.service.DiskonTestCarLocalService
		diskonTestCarLocalService;

	@BeanReference(type = DiskonTestCarPersistence.class)
	protected DiskonTestCarPersistence diskonTestCarPersistence;

	@BeanReference(type = com.astra.dewa.service.EmailDomainLocalService.class)
	protected com.astra.dewa.service.EmailDomainLocalService
		emailDomainLocalService;

	@BeanReference(type = EmailDomainPersistence.class)
	protected EmailDomainPersistence emailDomainPersistence;

	@BeanReference(type = com.astra.dewa.service.ESrutLocalService.class)
	protected com.astra.dewa.service.ESrutLocalService eSrutLocalService;

	@BeanReference(type = ESrutPersistence.class)
	protected ESrutPersistence eSrutPersistence;

	@BeanReference(
		type = com.astra.dewa.service.FakturIndirectLocalService.class
	)
	protected com.astra.dewa.service.FakturIndirectLocalService
		fakturIndirectLocalService;

	@BeanReference(type = FakturIndirectPersistence.class)
	protected FakturIndirectPersistence fakturIndirectPersistence;

	@BeanReference(type = com.astra.dewa.service.FakturPajakLocalService.class)
	protected com.astra.dewa.service.FakturPajakLocalService
		fakturPajakLocalService;

	@BeanReference(type = FakturPajakPersistence.class)
	protected FakturPajakPersistence fakturPajakPersistence;

	@BeanReference(type = com.astra.dewa.service.JenisMateriLocalService.class)
	protected com.astra.dewa.service.JenisMateriLocalService
		jenisMateriLocalService;

	@BeanReference(type = JenisMateriPersistence.class)
	protected JenisMateriPersistence jenisMateriPersistence;

	@BeanReference(
		type = com.astra.dewa.service.KategoriDealerLocalService.class
	)
	protected com.astra.dewa.service.KategoriDealerLocalService
		kategoriDealerLocalService;

	@BeanReference(type = KategoriDealerPersistence.class)
	protected KategoriDealerPersistence kategoriDealerPersistence;

	@BeanReference(type = com.astra.dewa.service.KuartalLocalService.class)
	protected com.astra.dewa.service.KuartalLocalService kuartalLocalService;

	@BeanReference(type = KuartalPersistence.class)
	protected KuartalPersistence kuartalPersistence;

	@BeanReference(
		type = com.astra.dewa.service.MasterApprovalLocalService.class
	)
	protected com.astra.dewa.service.MasterApprovalLocalService
		masterApprovalLocalService;

	@BeanReference(type = MasterApprovalPersistence.class)
	protected MasterApprovalPersistence masterApprovalPersistence;

	@BeanReference(
		type = com.astra.dewa.service.MasterApprovalDetailLocalService.class
	)
	protected com.astra.dewa.service.MasterApprovalDetailLocalService
		masterApprovalDetailLocalService;

	@BeanReference(type = MasterApprovalDetailPersistence.class)
	protected MasterApprovalDetailPersistence masterApprovalDetailPersistence;

	@BeanReference(
		type = com.astra.dewa.service.MasterApprovalDetailJournalLocalService.class
	)
	protected com.astra.dewa.service.MasterApprovalDetailJournalLocalService
		masterApprovalDetailJournalLocalService;

	@BeanReference(type = MasterApprovalDetailJournalPersistence.class)
	protected MasterApprovalDetailJournalPersistence
		masterApprovalDetailJournalPersistence;

	@BeanReference(
		type = com.astra.dewa.service.MasterApprovalJournalLocalService.class
	)
	protected com.astra.dewa.service.MasterApprovalJournalLocalService
		masterApprovalJournalLocalService;

	@BeanReference(type = MasterApprovalJournalPersistence.class)
	protected MasterApprovalJournalPersistence masterApprovalJournalPersistence;

	@BeanReference(
		type = com.astra.dewa.service.MenuAuthorizationLocalService.class
	)
	protected com.astra.dewa.service.MenuAuthorizationLocalService
		menuAuthorizationLocalService;

	@BeanReference(type = MenuAuthorizationPersistence.class)
	protected MenuAuthorizationPersistence menuAuthorizationPersistence;

	@BeanReference(type = com.astra.dewa.service.OTPLocalService.class)
	protected com.astra.dewa.service.OTPLocalService otpLocalService;

	@BeanReference(type = OTPPersistence.class)
	protected OTPPersistence otpPersistence;

	@BeanReference(
		type = com.astra.dewa.service.PeriodeReviewLocalService.class
	)
	protected com.astra.dewa.service.PeriodeReviewLocalService
		periodeReviewLocalService;

	@BeanReference(type = PeriodeReviewPersistence.class)
	protected PeriodeReviewPersistence periodeReviewPersistence;

	@BeanReference(
		type = com.astra.dewa.service.ReportPlafondLocalService.class
	)
	protected com.astra.dewa.service.ReportPlafondLocalService
		reportPlafondLocalService;

	@BeanReference(type = ReportPlafondPersistence.class)
	protected ReportPlafondPersistence reportPlafondPersistence;

	@BeanReference(type = com.astra.dewa.service.RolesLocalService.class)
	protected com.astra.dewa.service.RolesLocalService rolesLocalService;

	@BeanReference(type = RolesPersistence.class)
	protected RolesPersistence rolesPersistence;

	@BeanReference(type = com.astra.dewa.service.SalesProgramLocalService.class)
	protected com.astra.dewa.service.SalesProgramLocalService
		salesProgramLocalService;

	@BeanReference(type = SalesProgramPersistence.class)
	protected SalesProgramPersistence salesProgramPersistence;

	@BeanReference(type = com.astra.dewa.service.SalesReportLocalService.class)
	protected com.astra.dewa.service.SalesReportLocalService
		salesReportLocalService;

	@BeanReference(type = SalesReportPersistence.class)
	protected SalesReportPersistence salesReportPersistence;

	@BeanReference(type = com.astra.dewa.service.SettingLocalService.class)
	protected com.astra.dewa.service.SettingLocalService settingLocalService;

	@BeanReference(type = SettingPersistence.class)
	protected SettingPersistence settingPersistence;

	@BeanReference(type = com.astra.dewa.service.SkIrisLocalService.class)
	protected com.astra.dewa.service.SkIrisLocalService skIrisLocalService;

	@BeanReference(type = SkIrisPersistence.class)
	protected SkIrisPersistence skIrisPersistence;

	@BeanReference(type = com.astra.dewa.service.SP3DLocalService.class)
	protected com.astra.dewa.service.SP3DLocalService sp3dLocalService;

	@BeanReference(type = SP3DPersistence.class)
	protected SP3DPersistence sp3dPersistence;

	@BeanReference(
		type = com.astra.dewa.service.SuratPenaltyStockLocalService.class
	)
	protected com.astra.dewa.service.SuratPenaltyStockLocalService
		suratPenaltyStockLocalService;

	@BeanReference(type = SuratPenaltyStockPersistence.class)
	protected SuratPenaltyStockPersistence suratPenaltyStockPersistence;

	@BeanReference(type = com.astra.dewa.service.TahunLocalService.class)
	protected com.astra.dewa.service.TahunLocalService tahunLocalService;

	@BeanReference(type = TahunPersistence.class)
	protected TahunPersistence tahunPersistence;

	@BeanReference(type = com.astra.dewa.service.TicketLocalService.class)
	protected com.astra.dewa.service.TicketLocalService ticketLocalService;

	@BeanReference(type = TicketPersistence.class)
	protected TicketPersistence ticketPersistence;

	@BeanReference(
		type = com.astra.dewa.service.TipeKendaraanLocalService.class
	)
	protected com.astra.dewa.service.TipeKendaraanLocalService
		tipeKendaraanLocalService;

	@BeanReference(type = TipeKendaraanPersistence.class)
	protected TipeKendaraanPersistence tipeKendaraanPersistence;

	@BeanReference(type = com.astra.dewa.service.TokenLocalService.class)
	protected com.astra.dewa.service.TokenLocalService tokenLocalService;

	@BeanReference(type = TokenPersistence.class)
	protected TokenPersistence tokenPersistence;

	@BeanReference(type = com.astra.dewa.service.TopikMateriLocalService.class)
	protected com.astra.dewa.service.TopikMateriLocalService
		topikMateriLocalService;

	@BeanReference(type = TopikMateriPersistence.class)
	protected TopikMateriPersistence topikMateriPersistence;

	@BeanReference(
		type = com.astra.dewa.service.TrainingAgendaLocalService.class
	)
	protected com.astra.dewa.service.TrainingAgendaLocalService
		trainingAgendaLocalService;

	@BeanReference(type = TrainingAgendaPersistence.class)
	protected TrainingAgendaPersistence trainingAgendaPersistence;

	@BeanReference(
		type = com.astra.dewa.service.TrainingAgendaParticipantLocalService.class
	)
	protected com.astra.dewa.service.TrainingAgendaParticipantLocalService
		trainingAgendaParticipantLocalService;

	@BeanReference(type = TrainingAgendaParticipantPersistence.class)
	protected TrainingAgendaParticipantPersistence
		trainingAgendaParticipantPersistence;

	@BeanReference(
		type = com.astra.dewa.service.TrainingAgendaParticipantUfLocalService.class
	)
	protected com.astra.dewa.service.TrainingAgendaParticipantUfLocalService
		trainingAgendaParticipantUfLocalService;

	@BeanReference(type = TrainingAgendaParticipantUfPersistence.class)
	protected TrainingAgendaParticipantUfPersistence
		trainingAgendaParticipantUfPersistence;

	@BeanReference(
		type = com.astra.dewa.service.TrainingMateriLocalService.class
	)
	protected com.astra.dewa.service.TrainingMateriLocalService
		trainingMateriLocalService;

	@BeanReference(type = TrainingMateriPersistence.class)
	protected TrainingMateriPersistence trainingMateriPersistence;

	@BeanReference(
		type = com.astra.dewa.service.TrainingMateriLampiranLocalService.class
	)
	protected com.astra.dewa.service.TrainingMateriLampiranLocalService
		trainingMateriLampiranLocalService;

	@BeanReference(type = TrainingMateriLampiranPersistence.class)
	protected TrainingMateriLampiranPersistence
		trainingMateriLampiranPersistence;

	@BeanReference(type = com.astra.dewa.service.UserRoleTypeLocalService.class)
	protected com.astra.dewa.service.UserRoleTypeLocalService
		userRoleTypeLocalService;

	@BeanReference(type = UserRoleTypePersistence.class)
	protected UserRoleTypePersistence userRoleTypePersistence;

	@BeanReference(type = com.astra.dewa.service.UsersDealersLocalService.class)
	protected com.astra.dewa.service.UsersDealersLocalService
		usersDealersLocalService;

	@BeanReference(type = UsersDealersPersistence.class)
	protected UsersDealersPersistence usersDealersPersistence;

	@BeanReference(type = com.astra.dewa.service.WilayahLocalService.class)
	protected com.astra.dewa.service.WilayahLocalService wilayahLocalService;

	@BeanReference(type = WilayahPersistence.class)
	protected WilayahPersistence wilayahPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		DiskonFleetLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}