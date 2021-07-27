package com.woniuxy.utils;

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

	//1.同一请求,不同的请求 的threadLocal是都是相同的 (因为是DBUtil的静态变量)
	//2.同一请求,不同请求 所用的的线程可能不同 也可能相同

	//如果执行完一个请求后,close()了sqlSession,  sqlSession虽然归还了,
	//但还存在在该线程的ThreadLocalMap中(threadLocal<sqlSession> 保留着对它的引用)

	//所以导致当请求用到之前用过的同一线程时,得到之前close()过的sqlSession,导致出现Executor was closed的异常
	//根本原因就是没remove()导致get到的sqlSession是之前用过并close()了的,不是被factory->open得到的,
	//所以要remove
	//不然即使sqlSession close()了,归还到了数据库连接池,
	//也会因为这个threadLocal的引用导致下次直接被调用,
	//而不是被factory  通过openSqlSession()得到的

	//sqlSession被引用了会归还数据库连接池吗?
	//会的 只要close()了就会归还连接池,
	//只是如果不remove-->下次请求 (同一线程时) 用到getSqlSession()得到的是之前
	//ThreadLocalMap中以threadLocal<sqlSession> 为key 映射的sqlSession
	//但是该sqlSession已经close()了,就不会被打开 -->导致出现Executor was closed的异常


	//而且不remove() 会因为threadLocal对sqlSession的引用导致内存泄漏


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
		sqlSession.rollback();
	}
	/**
	 * 归还sqlSession[事务+非事务]
	 */
	public static void close(){
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession!=null){
//			System.out.println(sqlSession);
			sqlSession.close();
			threadLocal.remove();
		}
	}

	public static void main(String[] args) {
		System.out.println(openSqlSession(true));
	}



}
