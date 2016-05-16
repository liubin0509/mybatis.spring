package com.isoftstone.study.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.isoftstone.study.handler.UUIDTypeHandler;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.isoftstone.study.service" })
@PropertySource({ "classpath:/application.properties" })
@MapperScan(basePackages = { "com.isoftstone.study.mapper",
		"com.isoftstone.study.handler" }, annotationClass = Component.class)
public class MybatisConfig {
	@Bean
	public DataSource dataSource(Environment env) {
		// PooledDataSource dataSource = new PooledDataSource();
		UnpooledDataSource dataSource = new UnpooledDataSource();
		dataSource.setDriver(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		// dataSource.setPoolMaximumActiveConnections(env.getProperty("jdbc.pool.maxPoolSize",
		// Integer.class));
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean
				.setMapperLocations(resourcePatternResolver.getResources("classpath*:/mybatis/*Mapper.xml"));
		sqlSessionFactoryBean.setTypeHandlersPackage(UUIDTypeHandler.class.getPackage().getName());
//		sqlSessionFactoryBean.setTypeHandlers(new TypeHandler<?>[]{new UUIDTypeHandler()});
		
		Properties sqlSessionFactoryProperties = new Properties();
		sqlSessionFactoryProperties.setProperty("lazyLoadingEnabled", "true");
		sqlSessionFactoryProperties.setProperty("aggressiveLazyLoading", "true");
		sqlSessionFactoryBean.setConfigurationProperties(sqlSessionFactoryProperties);
		return sqlSessionFactoryBean;
	}
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		dataSourceTransactionManager.setRollbackOnCommitFailure(true);
		return dataSourceTransactionManager;
	}
}
