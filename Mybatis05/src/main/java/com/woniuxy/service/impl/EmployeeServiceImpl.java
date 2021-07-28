package com.woniuxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.Employee;
import com.woniuxy.mapper.EmployeeMapper;
import com.woniuxy.service.EmployeeService;
import com.woniuxy.utils.DBUtil;
import com.woniuxy.utils.StringUtil;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/28 15:30
 * @Description:
 */
public class EmployeeServiceImpl implements EmployeeService {
	@Override
	public PageInfo<Employee> showOnePage(int currentPage, String keyword) {
		if (  keyword==null) {
			return null;
		}

		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);

		int pageSize = 5;
		PageHelper.startPage(currentPage, pageSize);

		Employee emp = new Employee();
		emp.setName(keyword);
		List<Employee> list = mapper.findByCondition(emp);


		DBUtil.close();


		return 	new PageInfo<>(list);

	}
}
