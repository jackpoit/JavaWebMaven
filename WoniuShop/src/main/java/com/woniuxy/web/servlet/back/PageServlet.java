package com.woniuxy.web.servlet.back;

import com.woniuxy.entity.PageModel;
import com.woniuxy.entity.User;
import com.woniuxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: rua
 * @Date: 2021/7/25 0:45
 * @Description:
 */
@WebServlet("/page/admin/page")
public class PageServlet extends HttpServlet {
	UserServiceImpl usi = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		//1.获取当前页码
		String currentUserPageStr = req.getParameter("currentUserPage");
		int currentUserPage = 1;
		if (currentUserPageStr != null && !"".equals(currentUserPageStr)) {
			currentUserPage = Integer.parseInt(currentUserPageStr);
		}
		//2.获取模糊查询关键字
		String keyword = req.getParameter("keyword");

		String userIdKeyword = req.getParameter("uid");
		String usernameKeyword = req.getParameter("uname");
		String userPhoneKeyword = req.getParameter("uphone");
		if (keyword == null) {
			if (userIdKeyword == null && usernameKeyword == null && userPhoneKeyword == null) {
				keyword = "";
			} else if ("".equals(userIdKeyword) && "".equals(usernameKeyword) && "".equals(userPhoneKeyword)) {
				keyword = "";
			} else if (!"".equals(userIdKeyword)) {
				keyword = userIdKeyword;
			} else {
				keyword = "".equals(usernameKeyword) ? userPhoneKeyword : usernameKeyword;
			}
		}

		//2.调用业务层api查询分页模型
		PageModel<User> userModel = usi.findOnePage(keyword, currentUserPage);

		System.out.println(userModel);
		req.setAttribute("userPageModel", userModel);
		req.getRequestDispatcher("/page/admin/backstage.jsp").forward(req, resp);


	}
}
