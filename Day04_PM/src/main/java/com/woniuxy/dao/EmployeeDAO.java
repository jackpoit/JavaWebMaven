package com.woniuxy.dao;

import com.woniuxy.entity.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * 员工的持久层接口
 *
 * @author rua
 */
public interface EmployeeDAO {
	/**
	 * 查询所有员工对象
	 *
	 * @return 返回员工集合
	 */
	List<Employee> findAll() throws SQLException;

	/**
	 * 删除
	 *
	 * @param id id
	 * @return 受影响行数
	 */
	int deleteById(int id) throws SQLException;

	/**
	 * @param employee 用户对象
	 * @return 受影响行数
	 */
	int updateById(Employee employee) throws SQLException;

	/**
	 *
	 * @param str 模糊名字
	 * @return 返回员工集合
	 * @throws SQLException
	 */
	List<Employee> findLikeByName(String str) throws SQLException;
}
