package com.fpt.projectfinal.configs;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration(exclude = { //
		DataSourceAutoConfiguration.class, //
		DataSourceTransactionManagerAutoConfiguration.class, //
		HibernateJpaAutoConfiguration.class })
public class HibernateConfig {

	@Autowired
	private org.springframework.core.env.Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("DriverClassName"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername(env.getProperty("username"));
		dataSource.setPassword(env.getProperty("password"));

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		Properties properties = new Properties();

//		// See: application.properties
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("show-sql"));
		properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.connection.CharSet", env.getProperty("charSet"));
		properties.put("hibernate.connection.characterEncoding", env.getProperty("characterEncoding"));
		properties.put("hibernate.connection.useUnicode", env.getProperty("useUnicode"));
		properties.put("connection.pool_size", env.getProperty("pool_size"));
		properties.put("cache.provider_class", env.getProperty("cache.provider_class"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		// Package contain entity classes
		factoryBean.setPackagesToScan(new String[] { "" });
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		//
		SessionFactory sf = factoryBean.getObject();
		System.out.println("## getSessionFactory: " + sf);
		return sf;
	}

//	private final Properties hibernateProperties() {
//		Properties hibernateProperties = new Properties();
//		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
//		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//		hibernateProperties.setProperty("hibernate.connection.CharSet", "utf8");
//		hibernateProperties.setProperty("hibernate.connection.characterEncoding", "utf8");
//		hibernateProperties.setProperty("hibernate.connection.useUnicode", "true");
//		hibernateProperties.setProperty("connection.pool_size", "100");
//		hibernateProperties.setProperty("current_session_context_class",
//				"org.springframework.orm.hibernate5.SpringSessionContext");
//		hibernateProperties.setProperty("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvide");
//		hibernateProperties.setProperty("show_sql", env.getProperty("spring.jpa.show-sql"));
//// 
//		return hibernateProperties;
//	}
	
	 @Autowired
	    @Bean(name = "transactionManager")
	    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	 
	        return transactionManager;
	    }
}
