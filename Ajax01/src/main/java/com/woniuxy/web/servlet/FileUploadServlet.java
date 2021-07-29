package com.woniuxy.web.servlet;

import com.woniuxy.util.BaseServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;

/**
 * @Author: rua
 * @Date: 2021/7/29 17:01
 * @Description:
 */

@MultipartConfig
@WebServlet("/upload")
public class FileUploadServlet extends BaseServlet {

	public void userImg(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String username = req.getParameter("username");
		System.out.println(username);

		//后端的文件上传
		Part part = req.getPart("myFile");
		String fileName = part.getSubmittedFileName();
		String nginxPath="D:/MyProgram/Tomcat/nginx-1.18.0/html/upload/"+username;
		File file = new File(nginxPath);
		if (!file.exists()){
			file.mkdirs();
		}
		nginxPath=nginxPath+"/"+fileName;
		part.write(nginxPath);

		String path="http://localhost/upload/"+username+"/"+fileName;
		//返回响应

		resp.getWriter().write(path);
	}
}
