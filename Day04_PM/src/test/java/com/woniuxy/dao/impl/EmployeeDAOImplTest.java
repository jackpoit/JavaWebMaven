package com.woniuxy.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/12:57
 * @Description:
 */
public class EmployeeDAOImplTest {
	EmployeeDAOImpl edi;
	@Before
	public void setUp() throws Exception {
		edi = new EmployeeDAOImpl();
	}

	@Test
	public void findAll() {
	}

	@Test
	public void deleteById() {
	}

	@Test
	public void updateById() {
	}

	@Test
	public void findByCondition() {
	}

	@Test
	public void count() {
		try {
			long count = edi.count("");
			Assert.assertEquals("不正确",18,count);
			System.out.println(count);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Test
	public void findLimit() {
	}
}