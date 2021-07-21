package com.woniuxy.dao.impl;

import com.woniuxy.dao.EmployeeDAO;
import com.woniuxy.entity.Employee;
import com.woniuxy.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author rua
 */
public class EmployeeDAOImpl implements EmployeeDAO {
	@Override
	public List<Employee> findAll() throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT id,tno,name,gender,birthday,title,salary,manager_id,dept_id FROM t_emp";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Employee> list = new ArrayList<>();
		Employee emp = null;
		while (rs.next()) {
			emp = new Employee();
			emp.setId(rs.getInt("id"));
			emp.setTno(rs.getString("tno"));
			emp.setName(rs.getString("name"));
			emp.setGender(rs.getString("gender"));
			java.sql.Date birthday = rs.getDate("birthday");
			if (birthday != null) {
				emp.setBirthday(new Date(birthday.getTime()));
			}
			emp.setTitle(rs.getString("title"));
			emp.setSalary(rs.getBigDecimal("salary"));

			if (rs.getString("manager_id") != null) {
				emp.setManagerId(rs.getInt("manager_id"));
			}
			if (rs.getString("dept_id") != null) {
				emp.setDeptId(rs.getInt("dept_id"));

			}
			list.add(emp);
		}
		DBUtil.release(conn, ps, rs);
		return list.isEmpty() ? null : list;

	}

	@Override
	public int deleteById(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "DELETE FROM t_emp WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, id);
		int rows = ps.executeUpdate();
		DBUtil.release(conn, ps);
		return rows;
	}

	@Override
	public int updateById(Employee emp) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "UPDATE t_emp SET tno=?,name=?,gender=?,birthday=?,salary=?,title=?,manager_id=?,dept_id=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, emp.getTno());
		ps.setObject(2, emp.getName());
		ps.setObject(3, emp.getGender());
		ps.setObject(4, emp.getBirthday());
		ps.setObject(5, emp.getSalary());
		ps.setObject(6, emp.getTitle());
		ps.setObject(7, emp.getManagerId());
		ps.setObject(8, emp.getDeptId());
		ps.setObject(9, emp.getId());
		int rows = ps.executeUpdate();
		DBUtil.release(conn, ps);
		return rows;
	}

	@Override
	public List<Employee> findByCondition(String str) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT id,tno,name,gender,birthday,title,salary,manager_id,dept_id from t_emp " +
				"WHERE name LIKE ? OR title LIKE ? OR tno LIKE ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		String kw = "%" + str + "%";
		ps.setObject(1, kw);
		ps.setObject(2, kw);
		ps.setObject(3, kw);
		ResultSet rs = ps.executeQuery();
		Employee emp = null;
		List<Employee> list = new ArrayList<>();
		while (rs.next()) {
			emp = new Employee();
			emp.setId(rs.getInt("id"));
			emp.setTno(rs.getString("tno"));
			emp.setName(rs.getString("name"));
			emp.setGender(rs.getString("gender"));
			java.sql.Date birthday = rs.getDate("birthday");
			if (birthday != null) {
				emp.setBirthday(new Date(birthday.getTime()));
			}
			emp.setTitle(rs.getString("title"));
			emp.setSalary(rs.getBigDecimal("salary"));

			if (rs.getString("manager_id") != null) {
				emp.setManagerId(rs.getInt("manager_id"));
			}
			if (rs.getString("dept_id") != null) {
				emp.setDeptId(rs.getInt("dept_id"));
			}
			list.add(emp);
		}
		DBUtil.release(conn, ps, rs);
		return list.isEmpty() ? null : list;
	}

	@Override
	public long count( String keyword) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM t_emp WHERE name LIKE ? OR title LIKE ? OR tno LIKE ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		String kw = "%" + keyword + "%";
		ps.setObject(1, kw);
		ps.setObject(2, kw);
		ps.setObject(3, kw);
		ResultSet rs = ps.executeQuery();
		long count = 0;
		while (rs.next()) {
			count=rs.getLong("COUNT(*)");
		}
		DBUtil.release(conn, ps, rs);
		return count;


	}

	@Override
	public List<Employee> findLimit(String keyword ,int start, int len) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT id,tno,name,gender,birthday,title,salary,manager_id,dept_id " +
				"From t_emp WHERE name LIKE ? OR title LIKE ? OR tno LIKE ? " +
				"Limit ?,?";
		PreparedStatement ps = conn.prepareStatement(sql);
		String kw = "%" + keyword + "%";
		ps.setObject(1, kw);
		ps.setObject(2, kw);
		ps.setObject(3, kw);
		ps.setObject(4, start);
		ps.setObject(5, len);
		ResultSet rs = ps.executeQuery();
		Employee emp = null;
		List<Employee> list = new ArrayList<>();
		while (rs.next()) {
			emp = new Employee();
			emp.setId(rs.getInt("id"));
			emp.setTno(rs.getString("tno"));
			emp.setName(rs.getString("name"));
			emp.setGender(rs.getString("gender"));
			java.sql.Date birthday = rs.getDate("birthday");
			if (birthday != null) {
				emp.setBirthday(new Date(birthday.getTime()));
			}
			emp.setTitle(rs.getString("title"));
			emp.setSalary(rs.getBigDecimal("salary"));

			if (rs.getString("manager_id") != null) {
				emp.setManagerId(rs.getInt("manager_id"));
			}
			if (rs.getString("dept_id") != null) {
				emp.setDeptId(rs.getInt("dept_id"));
			}
			list.add(emp);
		}
		DBUtil.release(conn, ps, rs);
		return list.isEmpty() ? null : list;
	}

	@Override
	public int insert(Employee emp) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql="INSERT INTO t_emp(tno,name,gender,salary,title,birthday,manager_id,dept_id) " +
				"VALUE(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1,emp.getTno());
		ps.setObject(2,emp.getName());
		ps.setObject(3,emp.getGender());
		ps.setObject(4,emp.getSalary());
		ps.setObject(5,emp.getTitle());
		ps.setObject(6,emp.getBirthday());
		ps.setObject(7,emp.getManagerId());
		ps.setObject(8,emp.getDeptId());
		int row = ps.executeUpdate();
		DBUtil.release(conn,ps);
		return row;
	}
}
