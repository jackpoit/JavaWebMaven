package com.woniuxy.util;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import com.alibaba.druid.pool.DruidDataSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	private static DruidDataSource dataSource = null;

	// 静态代码块【当类加载的时候优先被加载，且只会加载一次，一般用于加载资源】

	//解耦
	static {
		String path = DBUtil.class.getClassLoader().getResource("").getPath();
		File file = new File(path + "config.xml");

		Document document = null;
		try {
			document = Jsoup.parse(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		JXDocument doc = new JXDocument(document);
		try {
			List<JXNode> properties = doc.selN("//bean[@id=dataSource]/property");
			String driver = doc.selN("//bean[@id=dataSource]/property[@name=driverClassName]/text()").get(0).toString();
			String url = doc.selN("//bean[@id=dataSource]/property[@name=jdbcUrl]/text()").get(0).toString();
			String user = doc.selN("//bean[@id=dataSource]/property[@name=username]/text()").get(0).toString();
			String password = doc.selN("//bean[@id=dataSource]/property[@name=password]/text()").get(0).toString();
			int initSize = Integer.parseInt(doc.selN("//bean[@id=dataSource]/property[@name=initSize]/text()").get(0).toString());
			int maxSize = Integer.parseInt(doc.selN("//bean[@id=dataSource]/property[@name=maxSize]/text()").get(0).toString());
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			dataSource.setInitialSize(initSize);
			//初始准备5个数据库连接对象
			dataSource.setMaxActive(maxSize);
			//数据库最大可以存在20个连接对象
		} catch (XpathSyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 *
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}


	/**
	 * 释放资源
	 *
	 * @param conn 连接对象
	 * @param ps   预编译对象
	 * @param rs   结果集对象
	 */
	public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public static void release(Connection conn, PreparedStatement ps) {
		release(conn, ps, null);
	}

	/**
	 * 添加,删除,修改的通用模板方法
	 *
	 * @param sql    SQL语句
	 * @param params 占位符的实参列表
	 * @return 受影响的行数
	 */
	public static int update(String sql, Object... params) throws SQLException {
		if (sql == null || "".equals(sql)) {
			throw new IllegalArgumentException("SQL is empty");
		}

		//1.获取数据库连接
		Connection conn = getConnection();
		//2.获取预编译对象并进行占位符替换
		PreparedStatement ps = preSql(conn, sql, params);
		//3.执行SQL
		int rows = ps.executeUpdate();
		release(conn, ps);
		return rows;
	}

	/**
	 * 通用的查询单个对象的方法
	 *
	 * @param sql    SQL语句
	 * @param clazz
	 * @param params
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> selectAll(String sql, Class<T> clazz, Object... params) throws SQLException {
		if (sql == null || "".equals(sql) || clazz == null) {
			throw new IllegalArgumentException("SQL is empty");
		}
		//1.获取数据库连接
		Connection conn = getConnection();
		//2.获取预编译对象并进行占位符替换
		PreparedStatement ps = preSql(conn, sql, params);
		//3.发送SQL并查询结果
		ResultSet rs = ps.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();
		int count = metaData.getColumnCount(); //获取列信息

		ArrayList<T> list = new ArrayList<>();
		try {
			//4.循环遍历结果集
			while (rs.next()) { //判断是否存在下一条记录
				//通过反射创建对象

				T obj = clazz.newInstance();

				//5.分析查询结果集的列信息和值
				for (int i = 1; i <= count; i++) {
					//1.数据库的列名
					String column = metaData.getColumnLabel(i);//获取到列的 别名
					Object val = rs.getObject(column);
					//2.根据数据库的列名来查询是否存在相关的属性
					try {
						Field field = clazz.getDeclaredField(column);
						field.setAccessible(true);
						field.set(obj, val);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.isEmpty() ? null : list;
	}

	/**
	 * 占位符替换方法
	 *
	 * @param conn 连接对象
	 * @return ps对象
	 */
	private static PreparedStatement preSql(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		//占位符替换
		ParameterMetaData metaData = ps.getParameterMetaData();//获取占位符参数的信息对象
		int count = metaData.getParameterCount(); //获取到占位符?的个数
		//判断实参的个数与占位符的个数是否一致
		if (count != params.length) {
			throw new IllegalArgumentException("wrong params num");
		}
		for (int i = 1; i <= count; i++) {
			ps.setObject(i, params[i - 1]);  //循环替换占位符
		}
		return ps;
	}


	public static void main(String[] args) {
//		System.out.println(getConnection());
//		String sql = "DELETE FROM t_emp WHERE id=?";
//		try {
//			int row = update(sql);
//		} catch (SQLException throwables) {
//			throwables.printStackTrace();
//		}
		System.out.println(getConnection());

	}


}
