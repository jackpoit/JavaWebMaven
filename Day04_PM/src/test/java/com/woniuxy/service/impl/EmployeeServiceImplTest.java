package com.woniuxy.service.impl;

import com.woniuxy.entity.Employee;
import com.woniuxy.entity.PageModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/12:33
 * @Description:
 */
public class EmployeeServiceImplTest {
	EmployeeServiceImpl esi;
	@Before
	public void setUp() throws Exception {
		 esi= new EmployeeServiceImpl();
	}

	@Test
	public void showAll() {
	}

	@Test
	public void remove() {
	}

	@Test
	public void edit() {
	}

	@Test
	public void showByKeyword() {
	}

	@Test
	public void findOnePage() {
		PageModel<Employee> modal = esi.findOnePage(2);
		System.out.println(modal);
		for (Employee employee : modal.getList()) {
			System.out.println(employee);
		}

	}
}