package com.isoftstone.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

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
		User user = contructUser();

		int count = userService.insertUser(user);
		Assert.assertEquals(1, count);
	}

	@Test
	public void testInsertUsers() {
		List<User> users = new ArrayList<User>();
		int size = 10;
		for (int i = 0; i < size; i++) {
			users.add(addUser());
		}

		int count = userService.insertUsers(users);
		Assert.assertEquals(size, count);
	}

	@Test
	public void testGetUserById() {
		User newUser = addUser();

		UUID id = newUser.getId();
		User user = userService.getUserById(id);
		Assert.assertNotNull(user);
		Assert.assertEquals(id, user.getId());
	}

	@Test
	public void testQueryUsers() {
		User newUser = addUser();

		String email = newUser.getEmail();
		String name = newUser.getName();
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

		User newUser = addUser();
		ids.add(newUser.getId().toString());

		newUser = addUser();
		ids.add(newUser.getId().toString());

		users = userService.queryUsersByIds(ids);
		Assert.assertEquals(ids.size(), users.size());
	}

	@Test
	public void testQueryUsersByUsers() {
		List<User> usersParams = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User newUser = addUser();
			usersParams.add(newUser);
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
	public void testApiAdd() {
		int add1 = 5;
		int add2 = 6;
		int s = userService.apiAdd(add1, add2);
		Assert.assertEquals(add1 + add2, s);
	}

	@Test
	public void testQueryUserByOrg() {
		String orgId = "58b9e1c2-4800-4f87-acfb-3964c3911622";
		List<User> users = userService.queryUsersByOrg(orgId);
		Assert.assertFalse(CollectionUtils.isEmpty(users));
		for (User user : users) {
			Assert.assertEquals(orgId, user.getOrgId());
		}
	}

	@Test
	public void testUpdateUser() {
		User oriUser = addUser();

		User updUser = new User();
		BeanUtils.copyProperties(oriUser, updUser);

		updUser.setActived(!oriUser.getActived());
		updUser.setAge(15);
		updUser.setEmail("a" + oriUser.getEmail());
		updUser.setName("upd" + oriUser.getName());
		updUser.setPass("13579");

		int count = userService.updateUser(updUser);
		Assert.assertEquals(1, count);

		User user = userService.getUserById(updUser.getId());

		Assert.assertEquals(updUser.getActived(), user.getActived());
		Assert.assertEquals(updUser.getAge(), user.getAge());
		Assert.assertEquals(updUser.getEmail(), user.getEmail());
		Assert.assertEquals(updUser.getName(), user.getName());
		Assert.assertEquals(updUser.getPass(), user.getPass());
		Assert.assertEquals(updUser.getId(), user.getId());
		Assert.assertEquals(updUser.getOrgId(), user.getOrgId());
	}

	@Test
	public void testDeleteUser() {
		User oriUser = addUser();
		int count = userService.deletetUser(oriUser.getId());
		Assert.assertEquals(1, count);

		User user = userService.getUserById(oriUser.getId());
		Assert.assertNull(user);
	}

	private User addUser() {
		User user = contructUser();

		int count = userService.insertUser(user);
		Assert.assertEquals(1, count);
		return user;
	}

	private User contructUser() {
		Random r = new Random(System.nanoTime());
		int size = r.nextInt(5) + 5;
		char[] cs = new char[size];
		for (int i = 0; i < size; i++) {
			cs[i] = (char) (97 + r.nextInt(26));
		}
		User user = new User();
		user.setAge(size);
		user.setActived(size % 2 == 0);
		user.setName(new String(cs));
		user.setEmail(user.getName() + "@def.gh");
		user.setPass("123456");
		user.setOrgId(size % 3 > 0 ? "58b9e1c2-4800-4f87-acfb-3964c3911622" : null);
		return user;

	}
}
