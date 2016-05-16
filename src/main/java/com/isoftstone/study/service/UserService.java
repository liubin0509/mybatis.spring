package com.isoftstone.study.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isoftstone.study.domain.ApiAdd;
import com.isoftstone.study.domain.User;
import com.isoftstone.study.mapper.UserMapper;

@Service
@Transactional(rollbackFor=Throwable.class)
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public int insertUser(User user) throws RuntimeException{
		user.setId(UUID.randomUUID());
		int count = userMapper.insertUser(user);
		return count;
	}

	public List<User> queryUsers(String name, String email) {
		return userMapper.queryUsers(name, email);
	}

	public User getUserById(UUID id) {
		return userMapper.getUserById(id);
	}

	public List<User> queryUsersByIds(List<String> ids) {
		return userMapper.queryUsersByIds(ids);
	}

	public int insertUsers(List<User> users) {
		for(User user : users){
			user.setId(UUID.randomUUID());
		}
		return userMapper.insertUsers(users);
	}

	public List<User> queryUsersByUsers(List<User> usersParams) {
		return userMapper.queryUsersByUsers(usersParams);
	}

	public List<User> queryUsersGroupByStatus(int group) {
		return userMapper.queryUsersGroupByStatus(group);
	}

	public int apiAdd(int add1, int add2) {
		
		ApiAdd a = new ApiAdd();
		a.setAdd1(add1);
		a.setAdd2(add2);
		a.setS(0);
		userMapper.apiAdd(a);
		Integer s = a.getS();
		if(s == null){
			s = -1;
		}
		return s;
	}

	public List<User> queryUsersByOrg(String orgId) {
		return userMapper.queryUsersByOrg(orgId);
	}

	public int updateUser(User updUser) {
		return userMapper.updateUser(updUser);
	}

	public int deletetUser(UUID id) {
		return userMapper.deletetUser(id);
	}
}
