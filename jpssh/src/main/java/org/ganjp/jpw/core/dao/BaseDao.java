/**
 * $Id: BaseDao.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.model.PropertyFilter;

/**
 * <p>all database access must use this interface</p> 
 *
 * @author GanJianping
 * @since 1.0
 */
public interface BaseDao<T, PK extends Serializable> {

	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new entity</p>
	 * 
	 * @param T
	 */
	public void save(final T entity);
	/**
	 * <p>update new entity</p>
	 * 
	 * @param T
	 */
	public void update(final T entity);
	/**
	 * <p>save or update new entity</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(final T entity);
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the entity</p>
	 * 
	 * @param entity the entity must be from session or transient object that has primary key attribute
	 */
	public void delete(final T entity);
	/**
	 * <p>delete a entity by primary key</p>
	 * 
	 * @param PK
	 */
	public void delete(Class<T> entityClass, final PK id);
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return T
	/**
	 * <p>get entity by primary key</p>
	 * 
	 * @param PK
	 * @return T
	 */
	public T findById(Class<T> entityClass, final PK id);
	
	//------------by property return List
	/**
	 * <p>get all record</p>
	 *
	 * @return List
	 */
	public List<T> findAll(Class<T> entityClass);
	/**
	 * <p>get all records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<T>
	 */
	public List<T> findAllWithOrder(Class<T> entityClass, String orderBy, boolean isAsc);
	/**
	 * <p>get match records by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<T>
	 */
	public List<T> findByField(Class<T> entityClass, final String fieldName, final Object value);
	/**
	 * <p>get match records by primary key list</p>
	 * 
	 * @param List<PK>
	 * @return List<T>
	 */
	public List<T> findBy(Class<T> entityClass, List<PK> idList);
	/**
	 * <p>get match records by fieldName with order</p>
	 * 
	 * @param entityClass
	 * @param fieldName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return List<T>
	 */
	public List<T> findByField(Class<T> entityClass, final String fieldName, final Object value, 
			   final String orderBy, final boolean isAsc);
	
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg: from User u where loginName=? and userPassword=?
	 * @param Object... "ganjp","123"
	 * @return X
	 */
	public <X> X findUniqueByHql(final String hql, final Object... values);
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg:from AmUser where userName=:userName
	 * @param Map eg:map.put("userName", "ganjp");
	 * @return X
	 */
	public <X> X findUniqueByHql(final String hql, final Map<String, ?> values);
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg:from User u where loginName=? and userPassword=?
	 * @param values eg:"ganjp","123"
	 * @return List<X>
	 */
	public <X> List<X> findByHql(final String hql, final Object... values);
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg: from AmUser where userName=:userName
	 * @param values map.put("userName", "ganjp");
	 * @return
	 */
	public <X> List<X> findByHql(final String hql, final Map<String, ?> values);
	
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param pageNo eg:1
	 * @param pageSize eg:5
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T>
	 */
	public Page<T> fetchPageByHql(final int pageNo, final int pageSize, final String hql, final Object... values);
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param Page<T> eg:new Page<AmUser>(5).
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T> 
	 */
	public Page<T> fetchPageByHql(final Page<T> page, final String hql, final Object... values);
	
	/**
	 * <p>execute hql</p>
	 * 
	 * @param hql eg:"update User set status='disabled' where id=?"
	 * @param Object... eg:'122222222222323435345443'
	 */
	public void batchExecute(final String hql, final Object... values);
	
	/**
	 * <p>find By PropertyFilter List</p>
	 * 
	 * @param List<PropertyFilter> new PropertyFilter("EQS_loginName", "admin")
	 * @return List<T>
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters);
	
	/**
	 * <p>findByPropertyFilterList</p>
	 * 
	 * @param entityClass
	 * @param filters
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters, String orderBy, boolean isAsc); 
}
