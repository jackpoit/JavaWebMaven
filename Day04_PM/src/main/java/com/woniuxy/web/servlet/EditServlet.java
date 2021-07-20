package com.woniuxy.web.servlet;

import com.woniuxy.entity.Employee;
import com.woniuxy.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	EmployeeServiceImpl esi = new EmployeeServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		Employee emp = new Employee();
		emp.setId(Integer.parseInt(req.getParameter("id")));
		emp.setTno(req.getParameter("tno"));
		emp.setName(req.getParameter("name"));
		emp.setGender(req.getParameter("gender"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		try {
			if (!"".equals(req.getParameter("birthday"))) {
				emp.setBirthday(sdf.parse(req.getParameter("birthday")));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		emp.setTitle(req.getParameter("title"));
		if (!"".equals(req.getParameter("salary"))) {
			emp.setSalary(new BigDecimal(req.getParameter("salary")));

		}
		if (!"".equals(req.getParameter("managerId"))) {
			emp.setManagerId(Integer.parseInt(req.getParameter("managerId")));

		}
		if (!"".equals(req.getParameter("deptId"))) {
			emp.setDeptId(Integer.parseInt(req.getParameter("deptId")));
		}


		boolean editFlag = esi.edit(emp);
		if (editFlag) {
			resp.getWriter().write("<script>alert('更新成功')</script>");

		} else {
			resp.getWriter().write("<script>alert('更新失败')</script>");
		}


		//重定向到首页的Servlet,查询数据后再回到index.jsp
		resp.setHeader("refresh", "0;url=" + req.getContextPath() + "/index");


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
