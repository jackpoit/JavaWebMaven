package com.woniuxy.web.servlet;

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
 * Created with IntelliJ IDEA.
 * 文件上传的Servlet
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/17:03
 * @Description:
 */


/**
 * 文件上传
 * 给Servlet配置注解
 */
@MultipartConfig

@WebServlet("/upload")
public class   UploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.处理乱码
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		//2.获取普通请求参数
		String user = req.getParameter("user");
//		String file = req.getParameter("file");
		System.out.println(user);
//		System.out.println(file); //null


		//3.获取文件上传的参数(上传的文件)
		Part part = req.getPart("myFile"); //文件上传工具
		//4.获取文件名
		String fileName = part.getSubmittedFileName();
		//	System.out.println(fileName);


//		System.out.println(part.getContentType());
		// image/xxx 图片的格式

		//5.创建一个用户专属的目录用于用户存放自己的文件
		String uploadPath = "D:/MyProgram/Tomcat/nginx-1.18.0/html/upload/" + user;
		File dir = new File(uploadPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}


		//6.完成文件的上传
		part.write(uploadPath + "/" + fileName);  //IO流的底层逻辑

		String imgPath = "http://localhost/upload/" + user + "/" + fileName;
		//图片在服务器的真实路径

		req.setAttribute("imgPath", imgPath);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
