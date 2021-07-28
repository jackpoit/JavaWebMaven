package com.woniuxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.Employee;
import com.woniuxy.entity.PageModel;
import com.woniuxy.mapper.EmployeeMapper;
import com.woniuxy.service.EmployeeService;
import com.woniuxy.util.DBUtil;
import java.util.List;

/**
 * @author
 * @date 2021/7/20 0020-16:04
 */
public class EmployeeServiceImpl implements EmployeeService {


	@Override
	public List<Employee> showAll() {
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findAll();
		DBUtil.close();
		return list;
	}

	@Override
	public boolean remove(int id) {
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		System.out.println(mapper);
		int row = mapper.deleteByIds(id);
		DBUtil.close();
		return row > 0;
	}

	@Override
	public boolean update(Employee emp) {
		if (emp == null || emp.getId() == null)
			return false;
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);

		int row = mapper.update(emp);
		System.out.println("dasgdgsa:::row"+row);
		DBUtil.close();
		return row > 0;
	}

	@Override
	public List<Employee> findByKeyword(String keyword) {
		if (keyword == null)
			keyword = "";

		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.findByKeyword(keyword);
		DBUtil.close();
		return list;
	}

	@Override
	public PageModel<Employee> findOnePage(int currentPage, String keyword) {

		//创建分页模型
		PageModel<Employee> model = new PageModel<>();
		//1.封装一页显示多少条数据
		int pageSize = 5;
		model.setPageSize(pageSize);
		//2.封装当前页码
		model.setCurrentPage(currentPage);
		//3.查询并封装员工的总条数
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		final long count = mapper.count(keyword);
		model.setTotal(count);
		//4. 计算总页数并封装到model中
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);
		model.setTotalPage(totalPage);
		//5.封装上一页
		model.setPrev(currentPage == 1 ? totalPage : currentPage - 1);
		//6.封装下一页
		model.setNext(currentPage == totalPage ? 1 : currentPage + 1);
		//7.查询当前页的数据
		int start = (currentPage - 1) * pageSize; // 查询的起始行
		List<Employee> list = mapper.findLimit(keyword, start, pageSize);
		model.setList(list);
		//8. 封装模糊关键字
		model.setKeyword(keyword);
		DBUtil.close();
		return model;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		if (employee == null) {
			return false;
		}
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		int row = mapper.update(employee);
		DBUtil.close();
		return row > 0;
	}

	@Override
	public boolean updateImg(int id, String imgPath) {
		if (id == 0 || imgPath == null || "".equals(imgPath)) {
			return false;
		}
		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		Employee emp = new Employee();
		emp.setImagePath(imgPath);
		int row = mapper.update(emp);
		DBUtil.close();
		return row > 0;
	}

	@Override
	public PageInfo<Employee> showOnePage(int currentPage, String keyword) {
		if (currentPage<0||keyword==null){
			return null;
		}

		EmployeeMapper mapper = DBUtil.getMapper(EmployeeMapper.class);
		PageHelper.startPage(currentPage,5);
		List<Employee> list = mapper.findByKeyword(keyword);
		PageInfo<Employee> info = new PageInfo<>(list);
		DBUtil.close();
		return info;

	}
}
