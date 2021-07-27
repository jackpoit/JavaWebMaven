package com.woniuxy.mapper;

import com.woniuxy.entity.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {


	List<Map<String,Object>> findAllMap();//查询所有员工(多表查询)

	List<Employee> findAll(); //查询所有员工

	@MapKey("id") //将查询到的id列的值作为Map的Key,将resultMap的结果作value
	Map<Integer,Employee> findAllEmp();//查询所有员工
}
