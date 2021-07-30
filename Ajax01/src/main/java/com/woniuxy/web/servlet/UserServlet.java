package com.woniuxy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.woniuxy.entity.User;
import com.woniuxy.entity.com.woniuxy.service.impl.UserServiceImpl;
import com.woniuxy.util.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/29 14:23
 * @Description:
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
	UserServiceImpl usi = new UserServiceImpl();

	//Ajax 验证用户名是否存在
	public void checkUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1.获取异步请求发送的请求参数
		String uname = req.getParameter("uname");

		//2.调用业务逻辑层的接口api完成相关的业务操作
		boolean flag = usi.checkUserByName(uname);

		//3.返回ajax异步请求响应
		resp.getWriter().write(flag ? "ok" : "no");
	}

	//用户注册
	public void doRegister(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String[] courses = req.getParameterValues("course");
		String city = req.getParameter("city");

		//返回ajax响应
		resp.getWriter().write("ok");

	}

	public void showAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1.调用service
		List<User> list = usi.showAll();
		//2.需要将list转换成json格式的字符串(fastjson)
		String jsonStr = JSON.toJSONString(list);

		//2.返回ajax请求的响应
		resp.getWriter().write(jsonStr);

	}

	/**
	 * 用户分页查询 user?m=page&currentPage=1&keyword=xxx
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		//1.
		String cp = req.getParameter("currentPage");

		int currentPage=1;  //无参数查首页
		if (!StringUtil.isEmpty(cp)){
			currentPage=Integer.parseInt(cp);
		}

		//2.
		PageInfo<User> info = usi.findOnePage(currentPage);
		String jsonStr = JSON.toJSONString(info);
		resp.getWriter().write(jsonStr);

	}














}
