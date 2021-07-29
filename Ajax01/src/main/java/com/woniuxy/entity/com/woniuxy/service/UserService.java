package com.woniuxy.entity.com.woniuxy.service;

import com.woniuxy.entity.User;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/29 14:59
 * @Description:
 */
public interface UserService {


	/**
	 * 根据用户名查询该用户是否存在
	 * @param name
	 * @return
	 */
	boolean checkUserByName(String name);

	List<User> showAll();

}
