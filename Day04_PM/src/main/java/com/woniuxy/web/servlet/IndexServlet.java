package com.woniuxy.web.servlet;

import com.woniuxy.service.impl.EmployeeServiceImpl;
import com.woniuxy.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
	//indexServlet是单例 所有不需要加static
	private EmployeeServiceImpl esi=new EmployeeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Employee> list=esi.showAll();
		req.setAttribute("empList",list);
		req.getRequestDispatcher("/index.jsp").forward(req,resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
