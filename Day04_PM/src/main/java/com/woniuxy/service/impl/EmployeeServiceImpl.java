package com.woniuxy.service.impl;

import com.woniuxy.entity.PageModel;
import com.woniuxy.service.EmployeeService;
import com.woniuxy.dao.impl.EmployeeDAOImpl;
import com.woniuxy.entity.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * @author rua
 */
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAOImpl edi = new EmployeeDAOImpl();

	@Override
	public List<Employee> showAll() {

		try {
			return edi.findAll();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean remove(int id) {
		try {
			return edi.deleteById(id) > 0;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean edit(Employee emp) {
		//先进行一层防护  如果emp为空 或id为空 就无法更新
		if (emp == null || emp.getId() == null) {
			return false;
		}
		try {
			return edi.updateById(emp) > 0;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	public List<Employee> showByKeyword(String str) {
		if (str == null) {
			str = "";
		}
		try {
			return edi.findByCondition(str);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public PageModel<Employee> findOnePage(int currentPage) {
		//创建分页模型
		PageModel<Employee> model = new PageModel<>();
		//1.封装一页显示多少条数据
		int pageSize = 5;
		model.setPageSize(pageSize);
		//2.封装当前页码
		model.setCurrentPage(currentPage);
		try {
			//3.查询员工总条数
			final long count = edi.count();
			model.setTotal(count);
			//4.计算总页数并封装到model中
			int totalPage = (int) Math.ceil(count * 1.0 / pageSize); //取整
			model.setTotalPage(totalPage);
			//5.封装上一页
			model.setPrev(currentPage == 1 ? totalPage : currentPage - 1);
			//6.封装下一页
			model.setNext(currentPage == totalPage ? 1 : currentPage + 1);
			//7.查询当前页的数据
			int start=(currentPage-1)*pageSize; //查询起始行
			List<Employee> list = edi.findLimit(start, pageSize);
			model.setList(list);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return model;

	}
}
