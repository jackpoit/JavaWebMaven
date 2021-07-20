package com.woniuxy.service.impl;

import com.woniuxy.service.EmployeeService;
import com.woniuxy.dao.impl.EmployeeDAOImpl;
import com.woniuxy.entity.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * @author rua
 */
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAOImpl edi = new EmployeeDAOImpl();

	@Override
	public List<Employee> showAll() {

		try {
			return edi.findAll();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean remove(int id) {
		try {
			return edi.deleteById(id) > 0;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean edit(Employee emp) {
		try {
			return edi.updateById(emp) > 0;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Employee> showByName(String str) {
		try {
			return edi.findLikeByName(str);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
}
