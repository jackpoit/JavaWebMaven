package com.woniuxy.mapper;

import com.woniuxy.entity.Employee;
import com.woniuxy.utils.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmployeeMapperTest {

	@Test
	public void insert(){

		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		Employee emp=new Employee();
		emp.setTno("WNSH1565");
		emp.setName("nanamei");
		emp.setTitle("hazuku");
		int row = mapper.insert(emp);
		System.out.println(row>0?"success":"failed");
		SqlSession sqlSession = DBUtil.openSqlSession(true);

	}


	@Test
	public void findByCondition(){

		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		Employee emp = new Employee();
//		emp.setId(1);
		emp.setTitle("Java开发攻城狮");
		emp.setGender("男");
		List<Employee> list = mapper.findByCondition(emp);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}




	//List<Employee> findOrder(String rule);//test
	@Test
	public void findOrder(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findOrder("dept_id");
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}


	@Test
	public void findLikeName(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findLikeName("张");
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}


	@Test
	public void findEmpAndDept() {
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<HashMap<String, Object>> list = mapper.findEmpAndDept();
		for (HashMap<String, Object> map : list) {
			System.out.println(map);
		}
	}



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
