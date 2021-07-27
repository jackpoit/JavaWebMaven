package com.woniuxy.mapper;

import com.woniuxy.entity.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeMapper {


	List<Map<String, Object>> findAllMap();//查询所有员工(多表查询)

	List<Employee> findAll(); //查询所有员工

	@MapKey("id")		//将查询到的id列的值作为Map的Key,将resultMap的结果作value
	Map<Integer, Employee> findAllEmp();//查询所有员工

	List<HashMap<String, Object>> findEmpAndDept(); //查询所有的员工及对应的部门信息

	List<Employee> findLikeName(String keyword);

	List<Employee> findOrder(String rule);//test

	/**
	 * 组合查询
	 * @param condition
	 * @return
	 */
	List<Employee> findByCondition(Employee employee);

	int insert(Employee emp);//动态插入
}