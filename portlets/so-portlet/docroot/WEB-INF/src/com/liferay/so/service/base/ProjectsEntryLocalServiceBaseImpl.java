/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.ProjectsEntryLocalService;
import com.liferay.so.service.persistence.FavoriteSiteFinder;
import com.liferay.so.service.persistence.FavoriteSitePersistence;
import com.liferay.so.service.persistence.MemberRequestPersistence;
import com.liferay.so.service.persistence.ProjectsEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the projects entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.so.service.impl.ProjectsEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.so.service.impl.ProjectsEntryLocalServiceImpl
 * @see com.liferay.so.service.ProjectsEntryLocalServiceUtil
 * @generated
 */
public abstract class ProjectsEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ProjectsEntryLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.so.service.ProjectsEntryLocalServiceUtil} to access the projects entry local service.
	 */

	/**
	 * Adds the projects entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectsEntry the projects entry
	 * @return the projects entry that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ProjectsEntry addProjectsEntry(ProjectsEntry projectsEntry)
		throws SystemException {
		projectsEntry.setNew(true);

		return projectsEntryPersistence.update(projectsEntry);
	}

	/**
	 * Creates a new projects entry with the primary key. Does not add the projects entry to the database.
	 *
	 * @param projectsEntryId the primary key for the new projects entry
	 * @return the new projects entry
	 */
	@Override
	public ProjectsEntry createProjectsEntry(long projectsEntryId) {
		return projectsEntryPersistence.create(projectsEntryId);
	}

	/**
	 * Deletes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectsEntryId the primary key of the projects entry
	 * @return the projects entry that was removed
	 * @throws PortalException if a projects entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ProjectsEntry deleteProjectsEntry(long projectsEntryId)
		throws PortalException, SystemException {
		return projectsEntryPersistence.remove(projectsEntryId);
	}

	/**
	 * Deletes the projects entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectsEntry the projects entry
	 * @return the projects entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ProjectsEntry deleteProjectsEntry(ProjectsEntry projectsEntry)
		throws SystemException {
		return projectsEntryPersistence.remove(projectsEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ProjectsEntry.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return projectsEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return projectsEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return projectsEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return projectsEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return projectsEntryPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ProjectsEntry fetchProjectsEntry(long projectsEntryId)
		throws SystemException {
		return projectsEntryPersistence.fetchByPrimaryKey(projectsEntryId);
	}

	/**
	 * Returns the projects entry with the primary key.
	 *
	 * @param projectsEntryId the primary key of the projects entry
	 * @return the projects entry
	 * @throws PortalException if a projects entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ProjectsEntry getProjectsEntry(long projectsEntryId)
		throws PortalException, SystemException {
		return projectsEntryPersistence.findByPrimaryKey(projectsEntryId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return projectsEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the projects entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projects entries
	 * @param end the upper bound of the range of projects entries (not inclusive)
	 * @return the range of projects entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ProjectsEntry> getProjectsEntries(int start, int end)
		throws SystemException {
		return projectsEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of projects entries.
	 *
	 * @return the number of projects entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getProjectsEntriesCount() throws SystemException {
		return projectsEntryPersistence.countAll();
	}

	/**
	 * Updates the projects entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param projectsEntry the projects entry
	 * @return the projects entry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ProjectsEntry updateProjectsEntry(ProjectsEntry projectsEntry)
		throws SystemException {
		return projectsEntryPersistence.update(projectsEntry);
	}

	/**
	 * Returns the favorite site local service.
	 *
	 * @return the favorite site local service
	 */
	public com.liferay.so.service.FavoriteSiteLocalService getFavoriteSiteLocalService() {
		return favoriteSiteLocalService;
	}

	/**
	 * Sets the favorite site local service.
	 *
	 * @param favoriteSiteLocalService the favorite site local service
	 */
	public void setFavoriteSiteLocalService(
		com.liferay.so.service.FavoriteSiteLocalService favoriteSiteLocalService) {
		this.favoriteSiteLocalService = favoriteSiteLocalService;
	}

	/**
	 * Returns the favorite site persistence.
	 *
	 * @return the favorite site persistence
	 */
	public FavoriteSitePersistence getFavoriteSitePersistence() {
		return favoriteSitePersistence;
	}

	/**
	 * Sets the favorite site persistence.
	 *
	 * @param favoriteSitePersistence the favorite site persistence
	 */
	public void setFavoriteSitePersistence(
		FavoriteSitePersistence favoriteSitePersistence) {
		this.favoriteSitePersistence = favoriteSitePersistence;
	}

	/**
	 * Returns the favorite site finder.
	 *
	 * @return the favorite site finder
	 */
	public FavoriteSiteFinder getFavoriteSiteFinder() {
		return favoriteSiteFinder;
	}

	/**
	 * Sets the favorite site finder.
	 *
	 * @param favoriteSiteFinder the favorite site finder
	 */
	public void setFavoriteSiteFinder(FavoriteSiteFinder favoriteSiteFinder) {
		this.favoriteSiteFinder = favoriteSiteFinder;
	}

	/**
	 * Returns the member request local service.
	 *
	 * @return the member request local service
	 */
	public com.liferay.so.service.MemberRequestLocalService getMemberRequestLocalService() {
		return memberRequestLocalService;
	}

	/**
	 * Sets the member request local service.
	 *
	 * @param memberRequestLocalService the member request local service
	 */
	public void setMemberRequestLocalService(
		com.liferay.so.service.MemberRequestLocalService memberRequestLocalService) {
		this.memberRequestLocalService = memberRequestLocalService;
	}

	/**
	 * Returns the member request persistence.
	 *
	 * @return the member request persistence
	 */
	public MemberRequestPersistence getMemberRequestPersistence() {
		return memberRequestPersistence;
	}

	/**
	 * Sets the member request persistence.
	 *
	 * @param memberRequestPersistence the member request persistence
	 */
	public void setMemberRequestPersistence(
		MemberRequestPersistence memberRequestPersistence) {
		this.memberRequestPersistence = memberRequestPersistence;
	}

	/**
	 * Returns the projects entry local service.
	 *
	 * @return the projects entry local service
	 */
	public com.liferay.so.service.ProjectsEntryLocalService getProjectsEntryLocalService() {
		return projectsEntryLocalService;
	}

	/**
	 * Sets the projects entry local service.
	 *
	 * @param projectsEntryLocalService the projects entry local service
	 */
	public void setProjectsEntryLocalService(
		com.liferay.so.service.ProjectsEntryLocalService projectsEntryLocalService) {
		this.projectsEntryLocalService = projectsEntryLocalService;
	}

	/**
	 * Returns the projects entry persistence.
	 *
	 * @return the projects entry persistence
	 */
	public ProjectsEntryPersistence getProjectsEntryPersistence() {
		return projectsEntryPersistence;
	}

	/**
	 * Sets the projects entry persistence.
	 *
	 * @param projectsEntryPersistence the projects entry persistence
	 */
	public void setProjectsEntryPersistence(
		ProjectsEntryPersistence projectsEntryPersistence) {
		this.projectsEntryPersistence = projectsEntryPersistence;
	}

	/**
	 * Returns the social office remote service.
	 *
	 * @return the social office remote service
	 */
	public com.liferay.so.service.SocialOfficeService getSocialOfficeService() {
		return socialOfficeService;
	}

	/**
	 * Sets the social office remote service.
	 *
	 * @param socialOfficeService the social office remote service
	 */
	public void setSocialOfficeService(
		com.liferay.so.service.SocialOfficeService socialOfficeService) {
		this.socialOfficeService = socialOfficeService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
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
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.so.model.ProjectsEntry",
			projectsEntryLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.so.model.ProjectsEntry");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return ProjectsEntry.class;
	}

	protected String getModelClassName() {
		return ProjectsEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = projectsEntryPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.so.service.FavoriteSiteLocalService.class)
	protected com.liferay.so.service.FavoriteSiteLocalService favoriteSiteLocalService;
	@BeanReference(type = FavoriteSitePersistence.class)
	protected FavoriteSitePersistence favoriteSitePersistence;
	@BeanReference(type = FavoriteSiteFinder.class)
	protected FavoriteSiteFinder favoriteSiteFinder;
	@BeanReference(type = com.liferay.so.service.MemberRequestLocalService.class)
	protected com.liferay.so.service.MemberRequestLocalService memberRequestLocalService;
	@BeanReference(type = MemberRequestPersistence.class)
	protected MemberRequestPersistence memberRequestPersistence;
	@BeanReference(type = com.liferay.so.service.ProjectsEntryLocalService.class)
	protected com.liferay.so.service.ProjectsEntryLocalService projectsEntryLocalService;
	@BeanReference(type = ProjectsEntryPersistence.class)
	protected ProjectsEntryPersistence projectsEntryPersistence;
	@BeanReference(type = com.liferay.so.service.SocialOfficeService.class)
	protected com.liferay.so.service.SocialOfficeService socialOfficeService;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private ProjectsEntryLocalServiceClpInvoker _clpInvoker = new ProjectsEntryLocalServiceClpInvoker();
}