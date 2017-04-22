package org.hema.spring_rest;

import java.util.Properties;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:application.properties")
public class JPAConfig {
	
	
	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean emf()
	{
		
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());              //equivalent of setting provider property in persistence.xml file.
		
		
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("org.hema.spring_rest.entity");
		
		emf.setJpaProperties(jpaProperties());
		
		return emf;
	}
	@Bean
	public DataSource getDataSource()               //we do not need a persistence file but we can load persistence file and use it in program that is an another way.
	{
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.user","root"));//these are default values..when no parameter match in property file then it will match with this default value.
		ds.setPassword(env.getProperty("db.password", "root"));
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager txnManager(EntityManagerFactory emf)
	{
		return new JpaTransactionManager(emf);
	}
	
	private Properties jpaProperties()
	{
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl"));
		props.setProperty("hibernate.show_sql",env.getProperty("hibernate.show.sql"));
		props.setProperty("hibernate.format_sql",env.getProperty("hibernate.format.sql"));
		return props;
	}
}
