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
	private static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<>();
	//每个servlet -->serviceImpl -->DButil --> ThreadLocal<SqlSession> threadLocal
	//不同的请求 的threadLocal是不同的 调用不会出问题
	//同一请求 (比如pageServlet) 的threadLocal都是相同的
	//如果执行完一个请求后,关闭了sqlSession,  sqlSession没有归还,还存在在该线程的ThreadLocalMap中
	//下次如果该请求还是这个线程的,调用的就是这个sqlSession 就会报Executor was closed的错误
	//但如果该请求是不同的线程处理的,就不会报错,
	// 因为不同请求的ThreadLocalMap不同,sqlSession存在这个map中只是key是相同的(都是同一个threadLocal)
	//所以要remove 不然即使sqlSession close()了,也会因为这个threadLocal的引用而得不到回收 ?


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
	 * factory.openSession(false); 手动提交(默认) 用于事务
	 * factory.openSession(true);  自动提交
	 */
	public static SqlSession openSqlSession(boolean flag){
		//1.先判断该线程内是否存在sqlSession
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession==null){ //如果会话不存在
			sqlSession = factory.openSession(flag); //新建一个会话
			threadLocal.set(sqlSession); //把新方法放进去
		}
//		System.out.println(sqlSession);
		return sqlSession;
	}

	/**
	 * 获取Mybatis接口Mapper的代理对象(非事务)
	 * @param clazz 接口的字节码对象
	 * @param <T> 接口类型
	 * @return 接口Mapper的代理对象
	 */
	public static <T> T getMapper(Class<T> clazz){
		SqlSession sqlSession = openSqlSession(true); //自动提交
		return sqlSession.getMapper(clazz);
	}
	/**
	 * 获取Mybatis接口Mapper的代理对象(事务)
	 * @param clazz 接口的字节码对象
	 * @param <T> 接口类型
	 * @return 接口Mapper的代理对象
	 */
	public static <T> T getTransMapper(Class<T> clazz){
		SqlSession sqlSession = openSqlSession(false); //自动提交
		return sqlSession.getMapper(clazz);
	}

	/**
	 * 提交sqlSession[事务]
	 */
	public static void commit(){
		SqlSession sqlSession = openSqlSession(false);
		sqlSession.commit();
	}
	/**
	 * 回滚sqlSession[事务]
	 */
	public static void rollback(){
		SqlSession sqlSession = openSqlSession(false);
		sqlSession.commit();
	}
	/**
	 * 归还sqlSession[事务+非事务]
	 */
	public static void close(){
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession!=null){
//			System.out.println(sqlSession);
			sqlSession.close();
		}
		threadLocal.remove();
	}




}
