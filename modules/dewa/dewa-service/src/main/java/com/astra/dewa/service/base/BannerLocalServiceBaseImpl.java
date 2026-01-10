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

import com.astra.dewa.model.Banner;
import com.astra.dewa.service.BannerLocalService;
import com.astra.dewa.service.BannerLocalServiceUtil;
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
import com.liferay.portal.aop.AopService;
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
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the banner local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.astra.dewa.service.impl.BannerLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.astra.dewa.service.impl.BannerLocalServiceImpl
 * @generated
 */
public abstract class BannerLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, BannerLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>BannerLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>BannerLocalServiceUtil</code>.
	 */

	/**
	 * Adds the banner to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BannerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param banner the banner
	 * @return the banner that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Banner addBanner(Banner banner) {
		banner.setNew(true);

		return bannerPersistence.update(banner);
	}

	/**
	 * Creates a new banner with the primary key. Does not add the banner to the database.
	 *
	 * @param Id the primary key for the new banner
	 * @return the new banner
	 */
	@Override
	@Transactional(enabled = false)
	public Banner createBanner(int Id) {
		return bannerPersistence.create(Id);
	}

	/**
	 * Deletes the banner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BannerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the banner
	 * @return the banner that was removed
	 * @throws PortalException if a banner with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Banner deleteBanner(int Id) throws PortalException {
		return bannerPersistence.remove(Id);
	}

	/**
	 * Deletes the banner from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BannerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param banner the banner
	 * @return the banner that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Banner deleteBanner(Banner banner) {
		return bannerPersistence.remove(banner);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return bannerPersistence.dslQuery(dslQuery);
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
			Banner.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return bannerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.BannerModelImpl</code>.
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

		return bannerPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.BannerModelImpl</code>.
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

		return bannerPersistence.findWithDynamicQuery(
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
		return bannerPersistence.countWithDynamicQuery(dynamicQuery);
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

		return bannerPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Banner fetchBanner(int Id) {
		return bannerPersistence.fetchByPrimaryKey(Id);
	}

	/**
	 * Returns the banner with the primary key.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner
	 * @throws PortalException if a banner with the primary key could not be found
	 */
	@Override
	public Banner getBanner(int Id) throws PortalException {
		return bannerPersistence.findByPrimaryKey(Id);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(bannerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Banner.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("Id");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(bannerLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Banner.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("Id");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(bannerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Banner.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("Id");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return bannerPersistence.create(((Integer)primaryKeyObj).intValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement BannerLocalServiceImpl#deleteBanner(Banner) to avoid orphaned data");
		}

		return bannerLocalService.deleteBanner((Banner)persistedModel);
	}

	@Override
	public BasePersistence<Banner> getBasePersistence() {
		return bannerPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return bannerPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the banners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.BannerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of banners
	 * @param end the upper bound of the range of banners (not inclusive)
	 * @return the range of banners
	 */
	@Override
	public List<Banner> getBanners(int start, int end) {
		return bannerPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of banners.
	 *
	 * @return the number of banners
	 */
	@Override
	public int getBannersCount() {
		return bannerPersistence.countAll();
	}

	/**
	 * Updates the banner in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BannerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param banner the banner
	 * @return the banner that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Banner updateBanner(Banner banner) {
		return bannerPersistence.update(banner);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			BannerLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		bannerLocalService = (BannerLocalService)aopProxy;

		_setLocalServiceUtilService(bannerLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BannerLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Banner.class;
	}

	protected String getModelClassName() {
		return Banner.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = bannerPersistence.getDataSource();

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
		BannerLocalService bannerLocalService) {

		try {
			Field field = BannerLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, bannerLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected ApplicationPersistence applicationPersistence;

	@Reference
	protected ApplicationAssignPersistence applicationAssignPersistence;

	@Reference
	protected ApplicationAssignJournalPersistence
		applicationAssignJournalPersistence;

	@Reference
	protected ApplicationAssignStatusPersistence
		applicationAssignStatusPersistence;

	@Reference
	protected ApplicationCategoryPersistence applicationCategoryPersistence;

	@Reference
	protected ApplicationHeaderPersistence applicationHeaderPersistence;

	@Reference
	protected ApplicationHeaderJournalPersistence
		applicationHeaderJournalPersistence;

	@Reference
	protected ApplicationHeaderStatusPersistence
		applicationHeaderStatusPersistence;

	@Reference
	protected ApprovalDetailUserPersistence approvalDetailUserPersistence;

	@Reference
	protected ApprovalHeaderUserPersistence approvalHeaderUserPersistence;

	@Reference
	protected ApprovalHistoryUserPersistence approvalHistoryUserPersistence;

	protected BannerLocalService bannerLocalService;

	@Reference
	protected BannerPersistence bannerPersistence;

	@Reference
	protected BulanPersistence bulanPersistence;

	@Reference
	protected CabangPersistence cabangPersistence;

	@Reference
	protected CalenderEventPersistence calenderEventPersistence;

	@Reference
	protected CalenderEventParticipantPersistence
		calenderEventParticipantPersistence;

	@Reference
	protected CommonPersistence commonPersistence;

	@Reference
	protected DealerPersistence dealerPersistence;

	@Reference
	protected DiskonFakpolPersistence diskonFakpolPersistence;

	@Reference
	protected DiskonFleetPersistence diskonFleetPersistence;

	@Reference
	protected DiskonOtherPersistence diskonOtherPersistence;

	@Reference
	protected DiskonTestCarPersistence diskonTestCarPersistence;

	@Reference
	protected EmailDomainPersistence emailDomainPersistence;

	@Reference
	protected ESrutPersistence eSrutPersistence;

	@Reference
	protected FakturIndirectPersistence fakturIndirectPersistence;

	@Reference
	protected FakturPajakPersistence fakturPajakPersistence;

	@Reference
	protected JenisMateriPersistence jenisMateriPersistence;

	@Reference
	protected KategoriDealerPersistence kategoriDealerPersistence;

	@Reference
	protected KuartalPersistence kuartalPersistence;

	@Reference
	protected MasterApprovalPersistence masterApprovalPersistence;

	@Reference
	protected MasterApprovalDetailPersistence masterApprovalDetailPersistence;

	@Reference
	protected MasterApprovalDetailJournalPersistence
		masterApprovalDetailJournalPersistence;

	@Reference
	protected MasterApprovalJournalPersistence masterApprovalJournalPersistence;

	@Reference
	protected MenuAuthorizationPersistence menuAuthorizationPersistence;

	@Reference
	protected OTPPersistence otpPersistence;

	@Reference
	protected PeriodeReviewPersistence periodeReviewPersistence;

	@Reference
	protected ReportPlafondPersistence reportPlafondPersistence;

	@Reference
	protected RolesPersistence rolesPersistence;

	@Reference
	protected SalesProgramPersistence salesProgramPersistence;

	@Reference
	protected SalesReportPersistence salesReportPersistence;

	@Reference
	protected SettingPersistence settingPersistence;

	@Reference
	protected SkIrisPersistence skIrisPersistence;

	@Reference
	protected SP3DPersistence sp3dPersistence;

	@Reference
	protected SuratPenaltyStockPersistence suratPenaltyStockPersistence;

	@Reference
	protected TahunPersistence tahunPersistence;

	@Reference
	protected TicketPersistence ticketPersistence;

	@Reference
	protected TipeKendaraanPersistence tipeKendaraanPersistence;

	@Reference
	protected TokenPersistence tokenPersistence;

	@Reference
	protected TopikMateriPersistence topikMateriPersistence;

	@Reference
	protected TrainingAgendaPersistence trainingAgendaPersistence;

	@Reference
	protected TrainingAgendaParticipantPersistence
		trainingAgendaParticipantPersistence;

	@Reference
	protected TrainingAgendaParticipantUfPersistence
		trainingAgendaParticipantUfPersistence;

	@Reference
	protected TrainingMateriPersistence trainingMateriPersistence;

	@Reference
	protected TrainingMateriLampiranPersistence
		trainingMateriLampiranPersistence;

	@Reference
	protected UserRoleTypePersistence userRoleTypePersistence;

	@Reference
	protected UsersDealersPersistence usersDealersPersistence;

	@Reference
	protected WilayahPersistence wilayahPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BannerLocalServiceBaseImpl.class);

}