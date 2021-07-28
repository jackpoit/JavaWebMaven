package com.woniuxy.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.Employee;
import com.woniuxy.mapper.EmployeeMapper;
import com.woniuxy.utils.DBUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/28 14:32
 * @Description:
 */
public class EmplpyeeMapperTest {
	@Test
	public void pageTest(){

		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);

		PageHelper.startPage(7, 5);

		List<Employee> list = mapper.findAll();

		for (Employee employee : list) {
			System.out.println(employee);
		}
		PageInfo<Employee> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getNextPage());
		System.out.println(pageInfo);

	}
}
