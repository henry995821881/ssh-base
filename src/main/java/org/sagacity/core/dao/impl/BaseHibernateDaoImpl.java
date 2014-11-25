/**
 * 
 */
package org.sagacity.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.sagacity.core.dao.BaseHibernateDao;
import org.sagacity.core.page.Pager;
import org.springframework.stereotype.Repository;

/**
 * @author LIZHITAO 基础hibernate 增删改查操作类
 */
@Repository
public class BaseHibernateDaoImpl<T, PK extends Serializable> implements
		BaseHibernateDao<T, PK> {
	public static Logger logger = Logger.getLogger(BaseHibernateDaoImpl.class);

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> entityClass = null;

	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityName() {
		return entityClass.getName();
	}

	/**
	 * 创建默认构造方法，以取得真正的泛型类型
	 * 
	 * @author LIZHITAO
	 */
	@SuppressWarnings("unchecked")
	public BaseHibernateDaoImpl() {
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			entityClass = (Class<T>) parameterizedType[0];
		}
	}

	/**
	 * 获取hibernate session
	 */
	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 获取hibernate criteria
	 */
	@Override
	public Criteria getCriteria() {
		return getSession().createCriteria(getEntityClass()).setCacheable(true);
	}

	/**
	 * 根据id删除数据
	 */
	@Override
	public void deleteById(PK id) {
		if (null != fetch(id)) {
			try {
				getSession().delete(fetch(id));
				getSession().flush();
			} catch (RuntimeException e) {
				logger
						.error("Delete " + entityClass.getName() + " failed: ",
								e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据id查询数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T fetch(PK id) {
		try {
			return (T) getSession().get(getEntityClass(), id);
		} catch (RuntimeException e) {
			logger.error("Fetch id is" + "id " + entityClass.getName()
					+ " failed: ", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按条件查询列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryByCriteria(Criteria criteria) {
		if (null == criteria)
			criteria = getCriteria();
		return criteria.setCacheable(true).list();
	}

	/**
	 * 查询所有列表
	 */
	@Override
	public List<T> findAll() {
		return queryByCriteria(null);
	}

	/**
	 * 保存数据
	 */
	@Override
	public void save(T entity) {
		try {
			getSession().save(entity);
		} catch (RuntimeException e) {
			logger.error("Save " + entityClass.getName() + " failed: ", e);
			e.printStackTrace();
		}
	}

	/**
	 * 修改数据
	 */
	@Override
	public void update(T entity) {
		try {
			getSession().update(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error("Update " + entityClass.getName() + " failed: ", e);
			e.printStackTrace();
		}
	}

	/**
	 * 保存或修改数据
	 */
	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	/**
	 * 获取最大的id
	 */
	@Override
	public Integer getMaxId() {
		Integer id = (Integer) getCriteria().setProjection(
				Projections.max("id")).uniqueResult();
		return null == id ? 0 : id;
	}

	/**
	 * 分页查询所有
	 */
	@Override
	public Pager<T> queryPage(Integer currentPage, Integer pageSize) {
		return queryPage(currentPage, pageSize, null);
	}

	/**
	 * 按条件查询并分页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager<T> queryPage(Integer currentPage, Integer pageSize,
			Criteria criteria) {
		if (null == criteria) {
			criteria = getCriteria();
		}

		try {
			Pager<T> pager = new Pager<T>(pageSize, getCount(criteria),
					currentPage);
			List<T> list = criteria.setCacheable(true).setFirstResult(
					pager.getFirstResult()).setMaxResults(pageSize).list();
			pager.setDataList(list);
			return pager;
		} catch (RuntimeException e) {
			logger.error("Query Pager " + entityClass.getName() + " failed: ",
					e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取纪录条数
	 */
	@Override
	public Integer getCount(Criteria criteria) {
		if (null != criteria) {
			try {
				criteria.setCacheable(true).setProjection(
						Projections.count("id"));
				Long count = (Long) criteria.uniqueResult();
				criteria.setProjection(null);
				return null == count ? 0 : count.intValue();
			} catch (RuntimeException e) {
				logger.error("GetCount " + entityClass.getName() + " failed: ",
						e);
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 按条件查询单条记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findOne(Criteria criteria) {
		if (null != criteria) {
			try {
				return (T) criteria.setCacheable(true).uniqueResult();
			} catch (RuntimeException e) {
				logger.error("Fine one " + entityClass.getName() + " failed: ",
						e);
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 删除实体
	 */
	@Override
	public void delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error("Delete " + entityClass.getName() + " failed: ", e);
			e.printStackTrace();
		}
	}
}
