package com.woniuxy.test;

import com.woniuxy.entity.Employee;
import com.woniuxy.mapper.EmployeeMapper;
import com.woniuxy.util.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

/**
 * @Author: rua
 * @Date: 2021/7/23 14:09
 * @Description:
 */
public class EmployeeMapperTest {
	@Test
	public void test01(){

//		System.out.println(new Date()!="");
		Integer a=10;
//		System.out.println(a!="");

	}
	@Test
	public void findLikeName(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findLikeName("张");
		for (Employee emp : list) {
			System.out.println(emp);
		}
		DBUtil.close();
	}


	@Test
	public void findNameAndTitle(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<HashMap<String, Object>> list = mapper.findNameAndTitle();
		for (HashMap<String, Object> map: list) {
			System.out.println(map);
		}
		DBUtil.close();
	}
	@Test
	public void findAll(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findAll();
		for (Employee emp : list) {
			System.out.println(emp);

		}
		DBUtil.close();
	}

	@Test
	public void findById(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		Employee emp = mapper.findById(5);
		System.out.println(emp);
		DBUtil.close();
	}

	@Test
	public void update(){
		SqlSession sqlSession = DBUtil.openSqlSession(true);
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setId(19);
		employee.setBirthday(new Date());
		employee.setTitle("修罗");
		int row = mapper.update(employee);
		System.out.println(row>0?"成功":"失败");
		sqlSession.close();
	}


	@Test
	public void deleteByIds(){
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		int row = mapper.deleteByIds(34);
		System.out.println(row>0?"成功":"失败");
		DBUtil.close();
	}

	@Test
	public void deleteByPrimaryKey(){
		SqlSession sqlSession = DBUtil.openSqlSession(true);
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		int row = mapper.deleteByPrimaryKey(42);
		System.out.println(row>0?"成功":"失败");
		sqlSession.close();
	}

	@Test
	public void addList(){
		SqlSession sqlSession = DBUtil.openSqlSession(true);
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		ArrayList<Employee> list = new ArrayList<>();
		list.add(new Employee("WNSH1300","miziha","男"));
		list.add(new Employee("WNSH1301","hazima","男"));
		list.add(new Employee("WNSH1302","yamata","男"));
		int row = mapper.addList(list);
		System.out.println(row>0?"成功":"失败");
		sqlSession.close();
	}

	@Test
	public void add(){
		SqlSession sqlSession = DBUtil.openSqlSession(true);
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setTno("WNSH1227");
		employee.setName("苇名一心");
		employee.setTitle("修罗");
		int row = mapper.add(employee);
		System.out.println(row>0?"成功":"失败");
		System.out.println(employee);
//		Setting autocommit to false on JDBC Connection
		//Mybatis中连接对象被默认设置为手动提交

//		sqlSession.commit();//手动提交sqlSession
		sqlSession.close(); //关闭会自动提交?
	}
}
