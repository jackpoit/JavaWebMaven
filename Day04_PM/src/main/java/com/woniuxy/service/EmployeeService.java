package com.woniuxy.service;

import com.woniuxy.entity.Employee;

import java.util.List;

/**
 * @author rua
 * 用户业务逻辑层接口
 */
public interface EmployeeService {
	/**
	 *
	 * @return 员工集合
	 */
	List<Employee> showAll();

	/**
	 * 删除员工
	 * @param id 员工id
	 * @return 是否删除
	 */
	boolean remove(int id);

	/**
	 * 更新员工信息
	 * @param emp 员工
	 * @return 是否更新成功
	 */
	boolean edit(Employee emp);

	/**
	 *
	 * @return 员工集合
	 */
	List<Employee> showByName(String str);
}
