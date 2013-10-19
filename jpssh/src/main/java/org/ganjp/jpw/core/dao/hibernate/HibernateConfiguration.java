/**
 * $Id: HibernateConfiguration.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.dao.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.ganjp.jpw.core.Config;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

//@Configuration
public class HibernateConfiguration {

//	@Value("#{dataSource}")
	private DataSource dataSource;

//	@Bean
	public AnnotationSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", Config.getValue("hibernate.dialect"));
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.autoReconnect", "true");

		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{});//AmUser.class
   		
		bean.setHibernateProperties(props);
		bean.setDataSource(this.dataSource);
		bean.setSchemaUpdate(true);
		return bean;
	}

//	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactoryBean().getObject());
	}

}
/**
 * hibernate.c3p0.min_size=5
　　hibernate.c3p0.max_size=20
　　hibernate.c3p0.timeout=1800
　　hibernate.c3p0.max_statements=50
 idleConnectionTestPeriod   设置空闲连接测试周期 
               preferredTestQuery : 设置一查询语句，用于重连测试 
              testConnectionOnCheckin设置为true 
              testConnectionOnCheckout设置为true 
*/