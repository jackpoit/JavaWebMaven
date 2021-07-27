package com.woniuxy.test;

import com.woniuxy.entity.Department;
import com.woniuxy.entity.Employee;
import com.woniuxy.mapper.DepartmentMapper;
import com.woniuxy.utils.DBUtil;
import org.junit.Test;

import java.util.HashSet;

/**
 * @Author: rua
 * @Date: 2021/7/27 17:17
 * @Description:
 */
public class DepartmentMapperTest {

	@Test
	public void findManyById(){

		DepartmentMapper mapper = DBUtil.getMapper(DepartmentMapper.class);
		Department department = mapper.findManyById(2);
		System.out.println(department);
		HashSet<Employee> set = department.getEmps();
		for (Employee employee : set) {
			System.out.println(employee);
		}

	}

	@Test
	public void findStep(){
		DepartmentMapper mapper = DBUtil.getMapper(DepartmentMapper.class);
		Department dept = mapper.findStepById(1);
		System.out.println(dept.getDName());
		System.out.println(dept.getArea());
//		HashSet<Employee> emps = dept.getEmps();
//		for (Employee emp : emps) {
//			System.out.println(emp);
//		}

	}
}
