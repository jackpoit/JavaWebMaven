package com.woniuxy.service.impl;

import com.woniuxy.entity.Employee;
import com.woniuxy.entity.PageModel;
import com.woniuxy.mapper.EmployeeMapper;
import com.woniuxy.util.DBUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class EmployeeServiceImplTest {
	EmployeeServiceImpl esi;

	@Before
	public void init(){
		esi=new EmployeeServiceImpl();
	}

	@Test
	public void showAll() {
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findAll();
		System.out.println(list);
		DBUtil.close();

	}

	@Test
	public void remove() {
	}

	@Test
	public void update() {
	}

	@Test
	public void findByKeyword() {
		List<Employee> list = esi.findByKeyword("");
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	@Test
	public void findOnePage() {
		PageModel<Employee> list = esi.findOnePage(1, "");
		System.out.println(list);
	}

	@Test
	public void addEmployee() {
	}

	@Test
	public void updateImg() {
	}

	@Test
	public void count(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		long count = mapper.count("");
		System.out.println(count);

	}
}