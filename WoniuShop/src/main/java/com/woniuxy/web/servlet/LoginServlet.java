package com.woniuxy.web.servlet;

import com.woniuxy.entity.User;
import com.woniuxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	UserServiceImpl usi=new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("l_username");
		String password=req.getParameter("l_pwd");
		User user=usi.findByName(username);

		String resStr="";
		if (user==null){
			resStr="没有此用户";
		}else {
			if (!password.equals(user.getPassword())){
				resStr="密码错误";
			}else {
				resStr="登录成功";
				req.setAttribute("name",user.getUsername());
				System.out.println(user);
			}
		}
		req.setAttribute("loginStr",resStr);
		req.getRequestDispatcher("/index.jsp").forward(req,resp);
	}
}
