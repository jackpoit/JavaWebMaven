package com.woniuxy.web.servlet;

import com.woniuxy.entity.Employee;
import com.woniuxy.service.impl.EmployeeServiceImpl;
import com.woniuxy.utils.DBUtil;
import com.woniuxy.utils.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/15:51
 * @Description:
 */
@MultipartConfig
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	EmployeeServiceImpl esi=new EmployeeServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		Employee emp = new Employee();

		String user=req.getParameter("tno");
		emp.setTno(req.getParameter("tno"));
		emp.setName(req.getParameter("name"));
		emp.setGender(req.getParameter("gender"));

		if (!"".equals(req.getParameter("birthday"))) {
			emp.setBirthday(DateUtil.parse(req.getParameter("birthday")));
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

		Part part = req.getPart("add_imgFile");
		String fileName=part.getSubmittedFileName();
		String uploadPath = "D:/MyProgram/Tomcat/nginx-1.18.0/html/upload/" + user;
		File dir = new File(uploadPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		part.write(uploadPath + "/" + fileName);
		String imgPath = "http://localhost/upload/" + user + "/" + fileName;
		emp.setImagePath(imgPath);



		System.out.println(emp);
		boolean addFlag = esi.addEmp(emp);
		resp.getWriter().write("<script>alert('"+(addFlag?"添加成功":"添加失败")+"')</script>");
		resp.setHeader("Refresh","0;url="+req.getContextPath()+"/page");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
