package com.woniuxy.service;

import com.woniuxy.entity.Employee;
import com.woniuxy.entity.PageModel;

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
	List<Employee> showByKeyword(String str);

	/**
	 * 根据当前页码封装一个分页模型
	 * @param currentPage 当前页
	 * @return 分页模型
	 */
	PageModel<Employee> findOnePage(int currentPage,String keyword);

	/**
	 * 添加员工
	 * @param emp 员工对象
	 * @return 是否添加成功
	 */
	boolean addEmp(Employee emp);


}
