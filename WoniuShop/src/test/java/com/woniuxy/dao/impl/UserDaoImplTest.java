package com.woniuxy.dao.impl;

import com.woniuxy.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/15:07
 * @Description:
 */
public class UserDaoImplTest {
	private UserDaoImpl udi;
	@Before
	public void setUp() throws Exception {
		udi=new UserDaoImpl();
	}

	@Test
	public void add() {
		try {
			int rows=udi.add(new User("tari","10086","125125","623098@qq.com"));
			System.out.println(rows==0?"失败":"成功");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Test
	public void deleteById() {
		try {
			int rows=udi.deleteById(2);
			System.out.println(rows==0?"失败":"成功");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Test
	public void update() {
		try {
			int rows=udi.update(new User(2,"tom","123456","125125","623098@qq.com"));
			System.out.println(rows==0?"失败":"成功");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Test
	public void findById() {
		try {
			User user=udi.findById(1);
			System.out.println(user);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Test
	public void findAll() {
		try {
			List<User> list=udi.findAll();
			System.out.println(list);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Test
	public void findLike() {
	}

	@Test
	public void findByPage(){
		List<User> list= null;
		try {
			list = udi.findByPage(1,3);
			System.out.println(list);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
	@Test
	public void findByName() {
	}
}