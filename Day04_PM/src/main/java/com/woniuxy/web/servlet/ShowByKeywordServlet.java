package com.woniuxy.web.servlet;

import com.woniuxy.entity.Employee;
import com.woniuxy.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author rua
 */
@WebServlet("/showByKeyword")
public class ShowByKeywordServlet extends HttpServlet {
	EmployeeServiceImpl esi=new EmployeeServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter writer = resp.getWriter();
		String str=req.getParameter("keyword");

		List<Employee> list=esi.showByKeyword(str);
		if (list==null){
			writer.write("<script>alert('查不到')</script>");
			resp.setHeader("refresh","0;url="+req.getContextPath()+"/");
		}else {
			req.setAttribute("empList",list);
			req.getRequestDispatcher("/index.jsp").forward(req,resp);
		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
