package com.woniuxy.web.servlet.back;

import com.woniuxy.entity.User;
import com.woniuxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * @Author: rua
 * @Date: 2021/7/25 4:11
 * @Description:
 */
@MultipartConfig
@WebServlet("/page/admin/edit")
public class EditServlet extends HttpServlet {
	UserServiceImpl usi = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		User user = new User();
		String username = req.getParameter("ename");
		user.setId(Integer.parseInt(req.getParameter("eid")));
		user.setUsername(req.getParameter("ename"));
		user.setPhone(req.getParameter("ephone"));
		user.setUserLevel(req.getParameter("elevel"));

		Part part = req.getPart("eImg");
		if (part != null) {
			String fileName = part.getSubmittedFileName();
			String suffix = fileName.substring(fileName.lastIndexOf(".")); // 文件的扩展名
			fileName = "head" + suffix;
			String uploadPath = "D:/MyProgram/Tomcat/nginx-1.18.0/html/upload/" + username;
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs(); // 创建用户目录用于存放自己的图片
			}
			uploadPath = uploadPath + File.separator + fileName;
			// 上传到服务器
			part.write(uploadPath);
			String imgPath = "http://localhost/upload/" + username + "/" + fileName;
			user.setImagePath(imgPath);
		}

		boolean flag = usi.edit(user);

		resp.getWriter().write("<script>alert('" + (flag ? "更新成功" : "更新失败") + "')</script>");
		resp.setHeader("refresh", "0;url=" + req.getContextPath() + "/page/admin/page");


	}
}
