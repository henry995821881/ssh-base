/**
 * 
 */
package org.sagacity.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.sagacity.core.page.Pager;

/**
 * @author LIZHITAO 基础CRUD 的hibernate DAO
 */
public interface BaseHibernateDao<T, PK>{
	/**
	 * 获取hibernate session
	 * 
	 * @return
	 */
	public Session getSession();

	/**
	 * 获取Criteria
	 * 
	 * @return
	 */
	public Criteria getCriteria();

	/**
	 * 设置泛型
	 * 
	 * @param entityClass
	 */
	public void setEntityClass(Class<T> entityClass);

	/**
	 * 获得泛型
	 * 
	 * @return
	 */
	public Class<T> getEntityClass();

	/**
	 * 获得实体的className
	 * 
	 * @return
	 */
	public String getEntityName();

	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @return
	 */
	public void save(T entity);

	/**
	 * 更新对象
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public void deleteById(PK id);

	/**
	 * 删除对象
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 根据id进行对象查询
	 * 
	 * @param id
	 * @return
	 */
	public T fetch(PK id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 更新或保存
	 */
	public void saveOrUpdate(T t);

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public Pager<T> queryPage(Integer currentPage, Integer pageSize);

	/**
	 * 获取最大的id
	 * 
	 * @return
	 */
	public Integer getMaxId();

	/**
	 * 按条件查询并分页
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	public Pager<T> queryPage(Integer currentPage, Integer pageSize,
			Criteria criteria);

	/**
	 * 按条件查询列表
	 * 
	 * @param criteria
	 * @return
	 */
	public List<T> queryByCriteria(Criteria criteria);

	/**
	 * 获取纪录条数
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getCount(Criteria criteria);

	/**
	 * 按条件查询单条记录
	 * 
	 * @param criteria
	 * @return
	 */
	public T findOne(Criteria criteria);
}
