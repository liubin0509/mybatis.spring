package com.isoftstone.study.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.isoftstone.study.config.MybatisConfig;

//@Configuration
//@ContextConfiguration(classes=MybatisConfig.class)
//@Import({ MybatisConfig.class })
public class BaseTest {
	private static ApplicationContext context;

	@BeforeClass
	public static void initContext() {
		context = new AnnotationConfigApplicationContext(MybatisConfig.class);
	}

	public <T> T getService(Class<T> clazz){
		return context.getBean(clazz);
	}
	@AfterClass
	public static void destory() {
		if (context != null) {
			// context.
		}
	}
}
