package com.woniuxy.mapper;


import com.woniuxy.entity.Employee;

import java.util.List;

public interface EmployeeMapper {


	Employee findById(int id);//一对一关联查询


	Employee findStepById(int id); //高效的分布懒加载查询

	List<Employee> findByDeptId(int id); //根据部门编号查找员工

	List<Employee> findAll();
	/**
	 * 组合查询
	 * @param condition
	 * @return
	 */
	List<Employee> findByCondition(Employee emp);

}