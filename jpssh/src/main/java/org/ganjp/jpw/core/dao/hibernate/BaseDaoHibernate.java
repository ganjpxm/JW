/**
 * $Id: HibernateDao.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ganjp.jpw.core.dao.BaseDao;
import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.model.PropertyFilter;
import org.ganjp.jpw.core.model.PropertyFilter.MatchType;
import org.ganjp.jpw.core.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * <p>Base Hibernate Dao</p>
 * 
 * @param <T> Type used by Dao
 * @param <PK> Primary Key
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class BaseDaoHibernate<T, PK extends Serializable> implements BaseDao<T, PK> {

	//-------------------------------------------   construct   ------------------------------------------
	public BaseDaoHibernate() {
		//this.entityClass = ReflectionUtil.getSuperClassGenricType(getClass());
	}
		
	//------------------------------------------- Save Or Update   ------------------------------------------
	/**
	 * <p>save new entity</p>
	 * 
	 * @param T
	 */
	public void save(final T entity) {
		Assert.notNull(entity, "entity can't be empty");
		getSession().save(entity);
		log.debug("save entity: {}", entity);
	}

	/**
	 * <p>update new entity</p>
	 * 
	 * @param T
	 */
	public void update(final T entity) {
		Assert.notNull(entity, "entity can't be empty");
		getSession().update(entity);
		log.debug("save entity: {}", entity);
	}
	/**
	 * <p>save or update new entity</p>
	 * 
	 * @param T
	 */
	public void saveOrUpdate(final T entity) {
		Assert.notNull(entity, "entity can't be empty");
		getSession().saveOrUpdate(entity);
		log.debug("save entity: {}", entity);
	}
	
	//-------------------------------------------   delete   ------------------------------------------
	/**
	 * <p>delete the entity</p>
	 * 
	 * @param entity the entity must be from session or transient object that has primary key attribute
	 */
	public void delete(final T entity) {
		Assert.notNull(entity, "entity can't be empty");
		getSession().delete(entity);
		log.debug("delete entity: {}", entity);
	}
	/**
	 * <p>delete a entity by primary key</p>
	 * 
	 * @param PK
	 */
	public void delete(Class<T> entityClass, final PK id) {
		Assert.notNull(id, "id can't be empty");
		delete(findById(entityClass, id));
		log.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}
	
	//-------------------------------------------   find   ------------------------------------------
	//------------return T
	/**
	 * <p>get model by primary key</p>
	 * 
	 * @param PK
	 * @return T
	 */
	public T findById(Class<T> entityClass, final PK id) {
		Assert.notNull(id, "id can't be empty");
		List<T> list = findByField(entityClass, getPkName(entityClass), id);
		if (list==null || list.isEmpty()) {
			return null;
		} else {
			return (T) list.get(0);
		}
	}
	
	//------------by property return List
	/**
	 * <p>get all record</p>
	 *
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass) {
		List<T> list = buildCriteria(entityClass).list();
		return list;
	}
	/**
	 * <p>get all records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllWithOrder(Class<T> entityClass, String orderBy, boolean isAsc) {
		Criteria c = buildCriteria(entityClass, orderBy, isAsc);
		return c.list();
	}
	/**
	 * <p>get match records by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<T>
	 */
	public List<T> findByField(Class<T> entityClass, final String fieldName, final Object value) {
		Assert.hasText(fieldName, "field name can not be empty");
		Criterion criterion = Restrictions.eq(fieldName, value);
		List<T> list = findByCriterion(entityClass, criterion);
		return list;
	}
	
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
			   final String orderBy, final boolean isAsc) {
		Assert.hasText(fieldName, "field name can not be empty");
		Criterion criterion = Restrictions.eq(fieldName, value);
		List<T> list = findByCriterion(entityClass, orderBy, isAsc, criterion);
		return list;
	}
	
	/**
	 * <p>get match records by primary key list</p>
	 * 
	 * @param List<PK>
	 * @return List<T>
	 */
	public List<T> findBy(Class<T> entityClass, List<PK> idList) {
		return findByCriterion(entityClass, Restrictions.in(getPkName(entityClass), idList));
	}

	/**
	 * <p>find By PropertyFilter List</p>
	 * 
	 * @param List<PropertyFilter> new PropertyFilter("EQS_loginName", "admin")
	 * @return List<T>
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findByCriterion(entityClass, criterions);
	}
	
	/**
	 * <p>findByPropertyFilterList</p>
	 * 
	 * @param entityClass
	 * @param filters
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters, String orderBy, boolean isAsc) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findByCriterion(entityClass, orderBy, isAsc, criterions);
	}
	//------------ find by hql
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg: from User u where loginName=? and userPassword=?
	 * @param Object... "ganjp","123"
	 * @return X
	 */
	@SuppressWarnings("unchecked")
	public <X> X findUniqueByHql(final String hql, final Object... values) {
		return (X) buildQuery(hql, values).uniqueResult();
	}
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg:from AmUser where userName=:userName
	 * @param Map eg:map.put("userName", "ganjp");
	 * @return X
	 */
	@SuppressWarnings("unchecked")
	public <X> X findUniqueByHql(final String hql, final Map<String, ?> values) {
		return (X) buildQuery(hql, values).uniqueResult();
	}
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg:from User u where loginName=? and userPassword=?
	 * @param values eg:"ganjp","123"
	 * @return List<X>
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> findByHql(final String hql, final Object... values) {
		return buildQuery(hql, values).list();
	}
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg: from AmUser where userName=:userName
	 * @param values map.put("userName", "ganjp");
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> findByHql(final String hql, final Map<String, ?> values) {
		return buildQuery(hql, values).list();
	}
	
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param pageNo eg:1
	 * @param pageSize eg:5
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T>
	 */
	public Page<T> fetchPageByHql(final int pageNo, final int pageSize, final String hql, final Object... values) {
		Page<T> page = new Page<T>(pageNo,pageSize);
		return this.fetchPageByHql(page, hql, values);
	}
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param Page<T> eg:new Page<AmUser>(5).
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T> 
	 */
	@SuppressWarnings("unchecked")
	public Page<T> fetchPageByHql(final Page<T> page, final String hql, final Object... values) {
		Assert.notNull(page, "page cann't be empty");
		Query q = buildQuery(hql, values);
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		buildPageQuery(q, page);
		page.setResult(q.list());
		return page;
	}
	
	/**
	 * <p>execute hql</p>
	 * 
	 * @param hql eg:"update User set status='disabled' where id=?"
	 * @param Object... eg:'122222222222323435345443'
	 */
	public void batchExecute(final String hql, final Object... values) {
		buildQuery(hql, values).executeUpdate();
	}
	
	/**
	 * <p>count result record number</p>
	 * 
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @result long
	 */
	protected long countHqlResult(final String hql, final Object... values) {
		String fromHql = hql;
		fromHql = "from " + StringUtil.substringAfter(fromHql, "from");
		fromHql = StringUtil.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUniqueByHql(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}
	
	//------------------ base method
	/**
	 * <p>build PropertyFilter Criterions</p>
	 * 
	 * @param List<PropertyFilter> 
	 * @return Criterion[]
	 */
	protected Criterion[] buildPropertyFilterCriterions(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			if (!filter.isMultiProperty()) {
				Criterion criterion = buildPropertyFilterCriterion(filter.getPropertyName(), filter.getPropertyValue(),
						filter.getMatchType());
				criterionList.add(criterion);
			} else {
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param, filter.getPropertyValue(), 
						filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}
	/**
	 * <p>build PropertyFilter Criterion</p>
	 * 
	 * @param String propertyName
	 * @param Object propertyValue
	 * @param MatchType matchType
	 * @return Criterion
	 */
	protected Criterion buildPropertyFilterCriterion(final String propertyName, final Object propertyValue, 
		final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName cann't be empty");
		Criterion criterion = null;
		try {
			if (MatchType.EQ.equals(matchType)) {
				criterion = Restrictions.eq(propertyName, propertyValue);
			} else if (MatchType.LIKE.equals(matchType)) {
				criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			} else if (MatchType.LE.equals(matchType)) {
				criterion = Restrictions.le(propertyName, propertyValue);
			} else if (MatchType.LT.equals(matchType)) {
				criterion = Restrictions.lt(propertyName, propertyValue);
			} else if (MatchType.GE.equals(matchType)) {
				criterion = Restrictions.ge(propertyName, propertyValue);
			} else if (MatchType.GT.equals(matchType)) {
				criterion = Restrictions.gt(propertyName, propertyValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return criterion;
	}
	/**
	 * <p>create Query object</p>
	 * 
	 * @param values 
	 * @return Query
	 */
	public Query buildQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString can not be empty");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}
	/**
	 * <p>create Query object with Page</p>
	 * 
	 * @param Query .
	 * @param Page<T>
	 * @return Query
	 */
	protected Query buildPageQuery(final Query q, final Page<T> page) {
		//hibernate's firstResult from 0 to start
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}
	/**
	 * <p>create Query object with Page</p>
	 * 
	 * @param values
	 * @return Query
	 */
	public Query buildQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString can not be empty.");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	/**
	 * <p>get match records by Criterias</p>
	 * 
	 * @param criterions
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriterion(Class<T> entityClass, final Criterion... criterions) {
		return buildCriteria(entityClass, criterions).list();
	}
	
	/**
	 * <p>get match records by Criterias with order</p>
	 * 
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriterion(Class<T> entityClass, String orderBy, boolean isAsc, final Criterion... criterions) {
		return buildCriteria(entityClass, orderBy, isAsc, criterions).list();
	}
	
	//------------return Criteria
	/**
	 * <p>build Criteria by critersions</p> 
	 * 
	 * @param criterions
	 * @return Criteria
	 */
	public Criteria buildCriteria(Class<T> entityClass, final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		criteria.addOrder(Order.desc("modifyTimestamp")); 
		return criteria;
	}
	
	/**
	 * <p>build Criteria by critersions with order</p>
	 * 
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	public Criteria buildCriteria(Class<T> entityClass, String orderBy, boolean isAsc, final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}
	
	//-------------------------------------- other -----------------------------------------
	/**
	 * <p>get primary key name</p>
	 * 
	 * @return String
	 */
	public String getPkName(Class<T> entityClass) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}
	
	//-------------------------------------------   session   ------------------------------------------
	/**
	 * <p>get current Session</p>
	 * 
	 * @return Session
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * <p>flush and clear current session.</p>
	 */
	public void flushAndClear() {
		getSession().flush();
		getSession().clear();
	}
	
	/**
	 * <p>get SessionFactory.</p>
	 * 
	 * @return SessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private SessionFactory sessionFactory;
	
}
