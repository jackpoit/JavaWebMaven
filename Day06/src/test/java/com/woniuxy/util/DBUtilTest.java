package com.woniuxy.util;

import com.woniuxy.entity.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DBUtilTest {

	@Test
	public void selectAll() {
		String sql="SELECT id,tno,name,gender,birthday,title,salary,manager_id AS managerId,dept_id AS deptId,image AS imagePath FROM t_emp WHERE id<?";

		try {
			List<Employee> list = DBUtil.selectAll(sql, Employee.class,20);
			for (Employee employee : list) {
				System.out.println(employee);
				System.out.println(employee.getSalary().add(new BigDecimal("111")));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}