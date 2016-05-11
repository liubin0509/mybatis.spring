package com.isoftstone.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
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
		int count = userService.insertUser(user);
		Assert.assertEquals(1, count);
	}

	@Test
	public void testInsertUsers() {
		List<User> users = new ArrayList<User>();
		int size = 10;
		for (int i = 0; i < size; i++) {
			User user = new User();
			user.setActived(false);
			user.setEmail("abc@def.gh");
			user.setName("abc" + i);
			user.setPass("123456");
			users.add(user);
		}

		int count = userService.insertUsers(users);
		Assert.assertEquals(size, count);
	}

	@Test
	public void testGetUserById() {
		UUID id = UUID.fromString("58b9e1c2-4800-4f87-acfb-3964c3911627");
		User user = userService.getUserById(id);
		Assert.assertNotNull(user);
		Assert.assertEquals(id, user.getId());
	}

	@Test
	public void testQueryUsers() {
		String email = "abc@def.gh";
		String name = "abc";
		List<User> users = userService.queryUsers(name, email);
		for (User u : users) {
			Assert.assertEquals(name, u.getName());
			Assert.assertEquals(email, u.getEmail());
		}
	}

	@Test
	public void testGetUserByIds() {
		List<String> ids = new ArrayList<String>();
		List<User> users = userService.queryUsersByIds(ids);
		LOG.debug(users.size());

		ids.add("58b9e1c2-4800-4f87-acfb-3964c3911627");
		ids.add("25743c4f-6d0e-4395-9e20-348854631111");
		users = userService.queryUsersByIds(ids);
		Assert.assertEquals(ids.size(), users.size());
	}

	@Test
	public void testQueryUsersByUsers() {
		List<User> usersParams = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setEmail("abc@def.gh");
			user.setName("abc" + i);
			usersParams.add(user);
		}
		List<User> users = userService.queryUsersByUsers(usersParams);
		LOG.debug(users.size());
	}

	@Test
	public void testQueryUsersGroupByStatus() {
		List<User> users = userService.queryUsersGroupByStatus(0);
		for (User user : users) {
			Assert.assertEquals(false, user.getActived());
		}
		users = userService.queryUsersGroupByStatus(1);
		for (User user : users) {
			Assert.assertEquals(true, user.getActived());
		}
		users = userService.queryUsersGroupByStatus(2);
		for (User user : users) {
			Assert.assertNull(user.getActived());
		}
	}
	@Test
	public void testApiAdd(){
		int add1 = 5;
		int add2 = 6;
		int s = userService.apiAdd(add1, add2);
		Assert.assertEquals(add1 + add2, s);
	}
}
