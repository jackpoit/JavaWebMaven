package com.woniuxy.test;

import com.woniuxy.entity.Employee;
import com.woniuxy.mapper.EmployeeMapper;
import com.woniuxy.utils.DBUtil;
import org.junit.Test;

/**
 * @Author: rua
 * @Date: 2021/7/27 16:07
 * @Description:
 */
public class EmployeeMapperTest {
	@Test
	public void findById(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		Employee employee = mapper.findById(1);
		System.out.println(employee);
		DBUtil.close();
	}
	@Test
	public void findStepById(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		Employee employee = mapper.findStepById(11);
		System.out.println(employee.getSalary());
		System.out.println(employee.getTitle());
		System.out.println(employee.getDept());

		DBUtil.close();
	}

}
