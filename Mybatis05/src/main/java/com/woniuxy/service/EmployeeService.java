package com.woniuxy.service;

import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.Employee;

public interface EmployeeService {

	/**
	 * 查询一页的员工分页模型数据
	 * @param currentPage 当前页
	 * @param keyword 关键词
	 * @return pageInfo模型
	 */
	PageInfo<Employee> showOnePage(int currentPage,String keyword);
}
