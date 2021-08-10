package com.itwn.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {
	public static SqlSession createSqlSession(String path,boolean flag) {
		InputStream ins=null;
		SqlSession sqlSession=null;
		try {
			ins = Resources.getResourceAsStream(path);
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(ins);
			//true 自动提交       false 手动提交
			sqlSession=ssf.openSession(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				ins.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		return sqlSession;
	}
	
	//1.手动提交 
	public static SqlSession createSqlSession(String path) {
		return createSqlSession(path, false);
	}
	
}
