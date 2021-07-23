package com.woniuxy.mapper;

import com.woniuxy.entity.Employee;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/23 10:30
 * @Description: 基于Mybatis的持久层接口
 * 规则: 映射文件的namespace与接口绑定
 * 接口的方法名与id一致
 * 接口方法的形参类型与parameterType一致
 * 接口方法的返回类型与resultType一致
 */
public interface EmployeeMapper {
	/**
	 * 根据id查询
	 * @param id 员工id
	 * @return 员工对象
	 */
	Employee findById(int id);

	/**
	 * 查询所有员工
	 * @return 员工集合
	 */
	List<Employee> findAll();

	/**
	 * 根据id删除员工
	 * @param id 员工id
	 * @return 受影响的行数
	 */
	int deleteById(int id);

}
