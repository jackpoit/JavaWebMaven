package com.woniuxy.test;

import com.woniuxy.entity.Employee;
import com.woniuxy.mapper.EmployeeMapper;
import com.woniuxy.util.DBUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/23 10:47
 * @Description:
 */

/**
 * Mybatis快速搭建流程
 * 1.导入Mybatis框架的相关jar包
 * 2.编写一个db.properties数据源配置信息
 * 3.编写一个日志属性文件log4j.properties
 * 4.编写Mybatis的核心配置文件 mybatis-config.xml
 * 5.创建数据库表，编写实体类
 * 6.编写一个Mybatis持久层XxxxMapper接口
 * 7.编写SQL映射配置文件 XxxxMapper.xml（与XxxxMappe绑定）
 * 8.在mybatis-config.xml中注册XxxxMapper.xml
 * 9.完成Mybatis的测试
 */
public class MybatisTest {


	@Test
	public void findById() throws IOException {
		//1.创建一个Mybatis工厂(SqlSessionFactory)的建造者
		SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
		//将Mybatis核心配置文件读取成IO资源
		InputStream inStream = Resources.getResourceAsStream("mybatis-config.xml");
		//2.建造者来建造工厂(核心配置文件)
		SqlSessionFactory factory = factoryBuilder.build(inStream);
		//3.从工厂中获取sqlSession(sql会话:本质就是一个数据库连接对象)
		SqlSession sqlSession = factory.openSession();
		//4.sqlSession获取接口的实现类(代理对象) -->动态代理设计模式
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		//5.通过接口完成api测试
		Employee emp = mapper.findById(1);
		System.out.println(emp);
		//6.释放资源
		sqlSession.close(); //本质:将数据库连接对象归还到Mybatis内置的数据库
	}

	@Test
	public void findAll() throws IOException {
		//1.创建一个Mybatis工厂(SqlSessionFactory)的建造者
		SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
		//将Mybatis核心配置文件读取成IO资源
		InputStream inStream = Resources.getResourceAsStream("mybatis-config.xml");
		//2.建造者来建造工厂(核心配置文件)
		SqlSessionFactory factory = factoryBuilder.build(inStream);
		//3.从工厂中获取sqlSession(sql会话:本质就是一个数据库连接对象)
		SqlSession sqlSession = factory.openSession();
		//4.sqlSession获取接口的实现类(代理对象) -->动态代理设计模式
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		//5.通过接口完成api测试
		List<Employee> list = mapper.findAll();
		for (Employee emp : list) {
			System.out.println(emp);

		}
		//6.释放资源
		sqlSession.close(); //本质:将数据库连接对象归还到Mybatis内置的数据库
	}

	@Test
	public void deleteById() throws IOException {
		//1.创建一个Mybatis工厂(SqlSessionFactory)的建造者
		SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
		//将Mybatis核心配置文件读取成IO资源
		InputStream inStream = Resources.getResourceAsStream("mybatis-config.xml");
		//2.建造者来建造工厂(核心配置文件)
		SqlSessionFactory factory = factoryBuilder.build(inStream);
		//3.从工厂中获取sqlSession(sql会话:本质就是一个数据库连接对象)
		SqlSession sqlSession = factory.openSession();
		//4.sqlSession获取接口的实现类(代理对象) -->动态代理设计模式
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		//5.通过接口完成api测试
		int row = mapper.deleteById(33);
		System.out.println(row);
		//6.释放资源
		sqlSession.close(); //本质:将数据库连接对象归还到Mybatis内置的数据库
	}

	@Test
	public void testFindAll(){
		SqlSession sqlSession = DBUtil.openSqlSession();
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
		sqlSession.close();
	}

}
