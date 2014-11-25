/**
 * 
 */
package org.sagacity.core.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.sagacity.core.dao.BaseHibernateDao;
import org.sagacity.core.page.Pager;
import org.sagacity.core.service.BaseHibernateService;

/**
 * @author LIZHITAO 基础hibernate 增删改查操作类
 */
public abstract class BaseHibernateServiceImpl<T, DaoImpl extends BaseHibernateDao<T, Integer>>
		implements BaseHibernateService<T, DaoImpl> {

	@Override
	public void delete(T entity) {
		getDaoImpl().delete(entity);
	}

	@Override
	public void deleteById(Integer id) {
		getDaoImpl().deleteById(id);
	}

	@Override
	public T fetch(Integer id) {
		return getDaoImpl().fetch(id);
	}

	@Override
	public List<T> findAll() {
		return getDaoImpl().findAll();
	}

	@Override
	public T findOne(Criteria criteria) {
		return getDaoImpl().findOne(criteria);
	}

	@Override
	public Integer getCount(Criteria criteria) {
		return getDaoImpl().getCount(criteria);
	}

	@Override
	public Criteria getCriteria() {
		return getDaoImpl().getCriteria();
	}

	@Override
	public Integer getMaxId() {
		return getDaoImpl().getMaxId();
	}

	@Override
	public Session getSession() {
		return getDaoImpl().getSession();
	}

	@Override
	public List<T> queryByCriteria(Criteria criteria) {
		return getDaoImpl().queryByCriteria(criteria);
	}

	@Override
	public Pager<T> queryPage(Integer currentPage, Integer pageSize) {
		return getDaoImpl().queryPage(currentPage, pageSize);
	}

	@Override
	public Pager<T> queryPage(Integer currentPage, Integer pageSize,
			Criteria criteria) {
		return getDaoImpl().queryPage(currentPage, pageSize, criteria);
	}

	@Override
	public T save(T entity) {
		getDaoImpl().save(entity);
		return getDaoImpl().fetch(getDaoImpl().getMaxId());
	}

	@Override
	public void saveOrUpdate(T t) {
		getDaoImpl().saveOrUpdate(t);
	}

	@Override
	public void update(T entity) {
		getDaoImpl().update(entity);
	}
}
