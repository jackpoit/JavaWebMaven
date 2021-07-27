package com.woniuxy.mapper;

import com.woniuxy.entity.Employee;
import com.woniuxy.utils.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class EmployeeMapperTest {
	@Test
	public void findAllEmp(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		Map<Integer, Employee> map = mapper.findAllEmp();
		for (Map.Entry<Integer,Employee> entry:map.entrySet()){
			System.out.println(entry.getKey()+"-->"+entry.getValue());
		}
		System.out.println(map.containsKey(10));

		DBUtil.close();
	}

	@Test
	public void findAll(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}

		DBUtil.close();
	}
	@Test
	public void findAllMap(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Map<String, Object>> list = mapper.findAllMap();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}

		DBUtil.close();
	}
}