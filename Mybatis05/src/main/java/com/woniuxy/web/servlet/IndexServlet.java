package com.woniuxy.web.servlet;

import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.Employee;
import com.woniuxy.service.impl.EmployeeServiceImpl;
import com.woniuxy.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: rua
 * @Date: 2021/7/28 15:21
 * @Description:
 */
@WebServlet("/page")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeServiceImpl esi=new EmployeeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String cp = req.getParameter("currentPage");
		int currentPage=1;
		if (!StringUtil.isEmpty(cp)){
			currentPage=Integer.parseInt(cp);
		}
		String keyword = req.getParameter("keyword");
		if (keyword==null){
			keyword="";
		}
		PageInfo<Employee> info = esi.showOnePage(currentPage, keyword);
		req.setAttribute("info",info);
		req.setAttribute("kw",keyword);
		req.getRequestDispatcher("/index.jsp").forward(req,resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
