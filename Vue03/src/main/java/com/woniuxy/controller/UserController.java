package com.woniuxy.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jackpoit.util.BaseServlet;
import com.jackpoit.util.MD5Util;
import com.woniuxy.bean.User;
import com.woniuxy.service.impl.UserServiceImpl;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.text.SimpleDateFormat;

/**
 * @Author: rua
 * @Date: 2021/8/10 18:48
 * @Description:
 */
@MultipartConfig
@WebServlet("/user")
public class UserController extends BaseServlet {
	UserServiceImpl usi = new UserServiceImpl();


	public void showPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String currentUserPageStr = req.getParameter("currentPage");
		int currentUserPage = 1;
		if (!StringUtil.isEmpty(currentUserPageStr)) {
			currentUserPage = Integer.parseInt(currentUserPageStr);
		}

		Integer id = null;
		String idStr = req.getParameter("uid");
		if (!StringUtil.isEmpty(idStr)) {
			id = Integer.parseInt(idStr);
		}
		String username = req.getParameter("uname");
		if (username == null) {
			username = "";
		}
		String mobile = req.getParameter("uphone");
		if (mobile == null) {
			mobile = "";
		}

		PageInfo<User> info = usi.findOnePage(currentUserPage, id, username, mobile);

		resp.getWriter().write(JSON.toJSONString(info));
	}

	//根据id删除用户
	public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String[] uids = req.getParameterValues("ids");
		Integer[] ids = new Integer[uids.length];
		for (int i = 0; i < ids.length; i++) {
			if (uids[i] != null) {
				ids[i] = Integer.parseInt(uids[i]);
			}
		}
		boolean flag = usi.doDelete(ids);
		resp.getWriter().write(flag ? "Y" : "N");
	}

	//更新用户
	public void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = new User();
		String id = req.getParameter("userId");
		if (!StringUtil.isEmpty(id))
			user.setId(Integer.parseInt(id));

		String username = req.getParameter("userName");
		if (!StringUtil.isEmpty(username)) {
			user.setUsername(username);
		}
		String password = req.getParameter("userPass");
		if (!StringUtil.isEmpty(password)) {
			user.setPassword(password);
			user.setMd5Code(MD5Util.getMd5(password, "com.woniuxy"));
		}
		user.setMobile(req.getParameter("userPhone"));
		user.setGender("男");

		user.setLevel(0);
		user.setUType(0);

		String imgPath = "";
		Part part = req.getPart("userImg");

		if (part.getSize() == 0) { //有name 但不传文件
			user.setImage(imgPath);
		} else {    //有name 传文件
			String fileName = part.getSubmittedFileName();
			String suffix = fileName.substring(fileName.lastIndexOf(".")); // 文件的扩展名
			fileName = "head" + suffix;
			String uploadPath = "D:/MyProgram/nginx-1.18.0/html/upload/" + username;
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs(); // 创建用户目录用于存放自己的图片
			}
			uploadPath = uploadPath + File.separator + fileName;
			// 上传到服务器
			part.write(uploadPath);
			imgPath = "http://localhost/upload/" + username + "/" + fileName;
			user.setImage(imgPath);
		}

		boolean flag = usi.doUpdate(user);

		resp.getWriter().write(flag ? "Y" : "N");
	}


	public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = new User();
		String username = req.getParameter("userName");
		if (!StringUtil.isEmpty(username)) {
			user.setUsername(username);
		}
		String password = req.getParameter("userPass");
		if (!StringUtil.isEmpty(password)) {
			user.setPassword(password);
			user.setMd5Code(MD5Util.getMd5(password, "com.woniuxy"));
		}
		user.setMobile(req.getParameter("userPhone"));
		user.setGender("男");

		user.setLevel(0);
		user.setUType(0);

		String imgPath = "";
		Part part = req.getPart("userImg");

		if (part.getSize() == 0) { //有name 但不传文件
			user.setImage(imgPath);
		} else {    //有name 传文件
			String fileName = part.getSubmittedFileName();
			String suffix = fileName.substring(fileName.lastIndexOf(".")); // 文件的扩展名
			fileName = "head" + suffix;
			String uploadPath = "D:/MyProgram/nginx-1.18.0/html/upload/" + username;
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs(); // 创建用户目录用于存放自己的图片
			}
			uploadPath = uploadPath + File.separator + fileName;
			// 上传到服务器
			part.write(uploadPath);
			imgPath = "http://localhost/upload/" + username + "/" + fileName;
			user.setImage(imgPath);
		}
		boolean flag = usi.doRegister(user);
		resp.getWriter().write(flag ? "Y" : "N");
	}

}
