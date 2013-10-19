/**
 * $Id: Page.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.model;

import java.util.ArrayList;
import java.util.List;

import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.StringUtil;

/**
 * <p></p>
 * 
 * @param <T>
 * 
 * @author ganjianping
 */
public class Page<T> {
	protected int pageNo = 1; 
	protected int pageSize = 1;
	protected String orderFields = null;
	protected String orderType = null; 
	protected boolean autoCount = true;
	protected int start = 0;

	protected List<T> resultList = new ArrayList<T>();
	protected long totalCount = -1;

	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public int getFirst() {
		if (start != 0)
			return start + 1;
		return ((pageNo - 1) * pageSize) + 1;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getOrderFields() {
		return orderFields;
	}

	public void setOrderFields(String orderFields) {
		this.orderFields = orderFields;
	}

	public Page<T> orderFields(final String theOrderFields) {
		setOrderFields(theOrderFields);
		return this;
	}

	/**
	 * 获得排序方向.
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * 设置排序方式向.
	 * eg:page.setOrderType(Page.ORDER_TYPE_ASC + "," + Page.ORDER_TYPE_ASC);
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrderType(final String order) {
		// 检查order字符串的合法值
		String[] orders = StringUtil.split(StringUtil.toLowerCase(order), ",");
		for (String orderStr : orders) {
			if (!Const.DB_ORDER_TYPE_DESC.equals(orderStr) && !Const.DB_ORDER_TYPE_ASC.equals(orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}
		this.orderType = StringUtil.toLowerCase(order);
	}

	public Page<T> order(final String theOrder) {
		setOrderType(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderFieldsSetted() {
		return (StringUtil.hasText(orderFields) && StringUtil.hasText(orderType));
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	// --------------------------- 访问查询结果函数 ---------------------
	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return resultList;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.resultList = result;
	}

	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}
}
