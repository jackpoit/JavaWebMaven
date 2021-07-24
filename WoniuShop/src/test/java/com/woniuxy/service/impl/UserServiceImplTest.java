package com.woniuxy.service.impl;

import com.woniuxy.entity.User;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.util.DBUtil;
import org.junit.Test;


public class UserServiceImplTest {

	@Test
	public void registerUser() {
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("devices");
		user.setPassword("asg");
		user.setPhone("agasdg");
		user.setEmail("asgsgad");
		user.setImagePath("p1.webp");
		user.setUserLevel("普通用户");
		int row = mapper.add(user);
		System.out.println(row);
		System.out.println(user);
		DBUtil.commit();
		DBUtil.close();
	}
}