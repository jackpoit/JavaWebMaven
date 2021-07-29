package com.woniuxy.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: rua
 * @Date: 2021/7/29 10:50
 * @Description:
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		//模拟数据库的验证
		String text="admin".equals(uname)&&"123".equals(pwd)?"ok":"no";

		//返回异步请求的响应(此处不能用转发和重定向)
		resp.getWriter().write(text);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
