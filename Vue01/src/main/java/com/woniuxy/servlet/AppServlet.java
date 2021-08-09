package com.woniuxy.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.App;
import com.woniuxy.service.impl.AppServiceImpl;
import com.woniuxy.util.BaseServlet;
import com.woniuxy.util.StringUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;


/**
 * @Author: rua
 * @Date: 2021/8/9 9:46
 * @Description:
 */
@MultipartConfig
@WebServlet("/app")
public class AppServlet extends BaseServlet {

	private AppServiceImpl asi = new AppServiceImpl();

	//根据关键词分页 后台分页
	public void showByKeyword(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String name = req.getParameter("name");
		if (name == null || "-1".equals(name))
			name = "";

		String type = req.getParameter("type");
		if (type == null || "-1".equals(type))
			type = "";

		String plat = req.getParameter("plat");
		if (plat == null || "-1".equals(plat))
			plat = "";

		String cp = req.getParameter("currentPage");
		int currentPage = 1;
		if (!StringUtil.isEmpty(cp))
			currentPage = Integer.parseInt(cp);

		App app = new App();
		app.setAppName(name);
		app.setAppType(type);
		app.setAppPlatform(plat);

		PageInfo<App> info = asi.showByKeyword(currentPage, app);


		String s = JSON.toJSONString(info);
		System.out.println(s);
		resp.getWriter().write(s);

	}

	//根据id删除订单
	public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String[] aids = req.getParameterValues("ids");
		Integer[] ids = new Integer[aids.length];
		for (int i = 0; i < ids.length; i++) {
			if (aids[i] != null) {
				ids[i] = Integer.parseInt(aids[i]);
			}
		}
		boolean flag = asi.doDelete(ids);
		System.out.println(flag);
		resp.getWriter().write(flag ? "Y" : "N");

	}


	public void addApp(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		App app = new App();
		String appName = req.getParameter("appName");
			app.setAppName(appName);

		String appSize = req.getParameter("appSize");
		if (!StringUtil.isEmpty(appSize)){
			app.setAppSize(Integer.parseInt(appSize));
		}
		app.setAppType(req.getParameter("appType"));
		app.setAppPlatform(req.getParameter("appPlatform"));

		Part part = req.getPart("appImg");
		if (part.getSize() != 0) {
			String fileName = part.getSubmittedFileName();
			String suffix = fileName.substring(fileName.lastIndexOf(".")); // 文件的扩展名
			fileName = "head" + suffix;
			String uploadPath = "D:/MyProgram/nginx-1.18.0/html/upload/" + appName;
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs(); // 创建用户目录用于存放自己的图片
			}
			uploadPath = uploadPath + File.separator + fileName;
			// 上传到服务器
			part.write(uploadPath);
			String imgPath = "http://localhost/upload/" + appName + "/" + fileName;
			app.setAppImg(imgPath);
		}

		boolean flag = asi.doAdd(app);
		resp.getWriter().write(flag ? "Y" : "N");

	}

	public void updateApp(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		App app = new App();

		String id = req.getParameter("id");
		if (!StringUtil.isEmpty(id)){
			app.setId(Integer.parseInt(id));
		}else {
			return;
		}

		String appName = req.getParameter("appName");
		app.setAppName(appName);

		String appSize = req.getParameter("appSize");
		if (!StringUtil.isEmpty(appSize)){
			app.setAppSize(Integer.parseInt(appSize));
		}
		app.setAppType(req.getParameter("appType"));
		app.setAppPlatform(req.getParameter("appPlatform"));

		Part part = req.getPart("appImg");
		if (part.getSize() != 0) {
			String fileName = part.getSubmittedFileName();
			String suffix = fileName.substring(fileName.lastIndexOf(".")); // 文件的扩展名
			fileName = "head" + suffix;
			String uploadPath = "D:/MyProgram/nginx-1.18.0/html/upload/" + appName;
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs(); // 创建用户目录用于存放自己的图片
			}
			uploadPath = uploadPath + File.separator + fileName;
			// 上传到服务器
			part.write(uploadPath);
			String imgPath = "http://localhost/upload/" + appName + "/" + fileName;
			app.setAppImg(imgPath);
		}

		boolean flag = asi.doUpdate(app);
		resp.getWriter().write(flag ? "Y" : "N");

	}
}
