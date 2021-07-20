package com.woniuxy.test;

import com.woniuxy.dao.impl.EmployeeDAOImpl;
import com.woniuxy.entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class EmployeeServiceImplTest {
	EmployeeDAOImpl edi;
	@Before
	public void init(){
		edi=new EmployeeDAOImpl();
	}

	@Test
	public void showAll() {
		EmployeeDAOImpl edi=new EmployeeDAOImpl();
		try {
			List<Employee> list = edi.findAll();
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Test
	public void testShowAll() {
	}

	@Test
	public void remove() {
	}

	@Test
	public void edit() {
	}

	@Test
	public void showByName() {
	}
}