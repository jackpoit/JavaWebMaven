package com.woniuxy.entity.com.woniuxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.User;
import com.woniuxy.entity.com.woniuxy.service.UserService;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.util.DBUtil;
import com.woniuxy.util.StringUtil;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/29 15:00
 * @Description: 业务逻辑层实现类
 */
public class UserServiceImpl implements UserService {

	@Override
	public boolean checkUserByName(String name) {
		if (StringUtil.isEmpty(name)){
			return false;
		}
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername(name);
		List<User> list = mapper.findCondition(user);
		DBUtil.close();
		return !list.isEmpty();
	}

	@Override
	public List<User> showAll() {
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		List<User> list = mapper.findCondition(null);
		DBUtil.close();
		return list.isEmpty()?null:list;
	}

	@Override
	public PageInfo<User> findOnePage(int currentPage) {

		UserMapper mapper = DBUtil.getMapper(UserMapper.class);

		PageHelper.startPage(currentPage,5);

		List<User> list = mapper.findCondition(null);

		DBUtil.close();

		return new PageInfo<>(list);


	}


}
