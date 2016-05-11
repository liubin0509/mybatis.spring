package com.isoftstone.study.mapper;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isoftstone.study.domain.ApiAdd;
import com.isoftstone.study.domain.User;

@Repository
public interface UserMapper {
	/**
	 * 新增一个用户 - 注入一个对象
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 新增多个用户 - 注入多个对象 如果 不加@Param("users")，则mybatis默认是list； 如果输入参数是数组User[],
	 * mybatis默认是array。
	 * 
	 * @param users
	 * @return
	 */
	public int insertUsers(@Param("users") List<User> users);

	/**
	 * 查询用户 - 注入单值
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(@Param("id") UUID id);

	/**
	 * 查询用户清单 - 注入多值
	 * 
	 * @param name
	 * @param email
	 * @return
	 */
	public List<User> queryUsers(@Param("name") String name, @Param("email") String email);

	/**
	 * 根据多个id查询用户 - in子句的使用
	 * 
	 * @param ids
	 *            多个id
	 * @return
	 */
	public List<User> queryUsersByIds(List<String> ids);

	/**
	 * 根据多个users查询用户 - in子句的使用 传入对象的list
	 * 
	 * @param users
	 *            多个用户信息
	 * @return
	 */
	public List<User> queryUsersByUsers(@Param("users") List<User> users);

	/**
	 * 按照状态分组用户，取得个数
	 * 
	 * @return
	 */
	public List<User> queryUsersGroupByStatus(@Param("group") int group);

	public void apiAdd(ApiAdd apiAdd);
}
