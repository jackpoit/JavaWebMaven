package com.woniuxy.web.servlet;

import com.sun.deploy.net.HttpRequest;
import com.woniuxy.entity.User;
import com.woniuxy.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: rua
 * @Date: 2021/7/28 16:52
 * @Description: 使用通用的BaseServlet模板来创建自己的servlet
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	public String add(HttpServletRequest req, HttpServletResponse resp) throws Exception{

		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = new User(name, password);
		req.setAttribute("user",user);

		return "forward:/success.jsp";
	}

	public String edit(HttpServletRequest req, HttpServletResponse resp) throws Exception{

		resp.getWriter().write("<script>alert('重定向测试')</script>");
		return "redirect:/success.jsp";

	}


}
