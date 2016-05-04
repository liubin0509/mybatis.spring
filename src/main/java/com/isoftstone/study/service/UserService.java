package com.isoftstone.study.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isoftstone.study.domain.User;
import com.isoftstone.study.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public int insertUser(User user) {
		user.setId(UUID.randomUUID().toString());
		return userMapper.insertUser(user);
	}
}
