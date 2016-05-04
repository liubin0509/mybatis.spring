package com.isoftstone.study.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.isoftstone.study.domain.User;

public class UserServiceTest extends BaseTest {

	@Autowired
	private static UserService userService;

	@Before
	public void init() {
		userService = this.getService(UserService.class);
	}

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setActived(false);
		user.setEmail("abc@def.gh");
		user.setName("abc");
		user.setPass("123456");
		userService.insertUser(user);
	}

}
