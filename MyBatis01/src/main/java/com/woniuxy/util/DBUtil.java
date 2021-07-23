package com.woniuxy.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: rua
 * @Date: 2021/7/23 11:45
 * @Description: 基于Mybatis框架的数据库工具类
 */
public class DBUtil {
	private static SqlSessionFactory factory;

	//在类加载时优先加载且只会执行一次(一般用于初始化操作的
	static {
		try {
			//1.创建一个Mybatis工厂(SqlSessionFactory)的建造者
			SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
			//将Mybatis核心配置文件读取成IO资源
			InputStream inStream = Resources.getResourceAsStream("mybatis-config.xml");
			//2.建造者来建造工厂(核心配置文件)
			 factory = factoryBuilder.build(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @return 返回一个基于Mybatis的sqlSession会话(本质就是数据库连接对象)
	 */
	public static SqlSession openSqlSession(){
		return factory.openSession();
	}
}
