package com.woniuxy.service.impl;

import com.woniuxy.entity.PageModel;
import com.woniuxy.entity.User;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.service.UserService;
import com.woniuxy.util.DBUtil;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/24 22:03
 * @Description:
 */
public class UserServiceImpl implements UserService {
	@Override
	public User findByName(String userName) {
		if (userName == null) {
			return null;
		}
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		User user = mapper.findByName(userName);
		DBUtil.close();
		return user;
	}

	@Override
	public boolean registerUser(User user) {
		if (user == null) {
			return false;
		}
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		int row = mapper.add(user);
		System.out.println(row);
		System.out.println(user);
		DBUtil.close();
		return row > 0;
	}

	@Override
	public PageModel<User> findOnePage(String keyword, int currentPage) {

		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		//创建分页模型
		PageModel<User> model = new PageModel<>();
		//1.封装一页显示多少条数据
		int pageSize = 5;
		model.setPageSize(pageSize);
		//2.封装当前页码
		model.setCurrentPage(currentPage);
		//3.查询员工总条数
		final long count = mapper.count(keyword);
		model.setTotal(count);
		//4.计算总页数并封装到model中
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize); //取整
		model.setTotalPage(totalPage);
		//5.封装上一页
		model.setPrev(currentPage == 1 ? totalPage : currentPage - 1);
		//6.封装下一页
		model.setNext(currentPage == totalPage ? 1 : currentPage + 1);
		//7.查询当前页的数据
		int start = (currentPage - 1) * pageSize; //查询起始行
		List<User> list = mapper.findLimitByKeyword(keyword, start, pageSize);
		for (User user : list) {
			System.out.println(user);
		}
		model.setList(list);
		model.setKeyword(keyword);
		DBUtil.close();
		return model;

	}

	@Override
	public boolean remove(Integer...ids) {
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		int row = mapper.deleteByIds(ids);
		DBUtil.close();
		return row > 0;
	}

	@Override
	public boolean edit(User user) {
		if (user == null) {
			return false;
		}
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		int row = mapper.update(user);
		DBUtil.close();
		return row > 0;
	}
}
