package com.woniuxy.web.servlet.back;

import com.woniuxy.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: rua
 * @Date: 2021/7/25 2:15
 * @Description:
 */

@WebServlet("/page/admin/delete")
public class DeleteServlet extends HttpServlet {
	UserServiceImpl usi = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String[] uids = req.getParameterValues("uid");
		Integer[] ids = new Integer[uids.length];
		for (int i = 0; i < ids.length; i++) {
			if (uids[i] != null){
				ids[i] = Integer.parseInt(uids[i]);
			}
		}
		boolean removeFlag = usi.remove(ids);
		if (removeFlag) {
			resp.getWriter().write("<script>alert('删除成功')</script>");

		} else {
			resp.getWriter().write("<script>alert('删除失败')</script>");
		}

		resp.setHeader("refresh", "0;url=" + req.getContextPath() + "/page/admin/page");

	}
}
