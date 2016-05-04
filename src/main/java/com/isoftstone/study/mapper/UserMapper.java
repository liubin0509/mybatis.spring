package com.isoftstone.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isoftstone.study.domain.User;

@Repository
public interface UserMapper {
	/**
	 * 注入一个对象
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 注入单值
	 * 
	 * @param name
	 * @return
	 */
	public User getUserById(@Param("id") String id);

	/**
	 * 注入多值
	 * 
	 * @param name
	 * @param email
	 * @return
	 */
	public List<User> queryUsers(@Param("name") String name, @Param("email") String email);
}
