package com.woniuxy.web.servlet;

import com.woniuxy.service.impl.EmployeeServiceImpl;

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
 * @author
 * @date 2021/7/22 0022-10:43
 */
@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    EmployeeServiceImpl esi = new EmployeeServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1. 处理请求乱码和响应乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //2.获取普通请求参数（员工id）
        int id = Integer.parseInt(req.getParameter("eid"));
        String tno = req.getParameter("etno");
        //3.获取上传的文件工具对象
        Part part = req.getPart("myImg");
        //3.1 获取到上传的文件名字(保证每次上传的都是最新的头像)
        String fileName = part.getSubmittedFileName();
        String suffix = fileName.substring(fileName.lastIndexOf(".")); // 文件的扩展名
        fileName = "head"+suffix;
        //4. 将图片上传到图片服务器(每个用户上传到自己的文件夹中：文件夹的名字就是)
        String uploadPath = "E:/MyPrograme/nginx-1.18.0/html/upload/"+tno;
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdirs(); // 创建用户目录用于存放自己的图片
        }
        uploadPath = uploadPath + File.separator + fileName;
        // 上传到服务器
        part.write(uploadPath);
        //5. 获取图片在nginx服务器的真实存放路径
        String imgPath = "http://localhost/upload/"+tno+"/"+fileName;
        //6.调用业务逻辑层api完成头像访问路径更新
        boolean flag = esi.updateImg(id, imgPath);
        //7. 根据处理结果返回客户端响应
        resp.getWriter().write("<script>alert('"+(flag?"上传成功":"上传失败")+"')</script>");
        resp.setHeader("Refresh","0;url="+req.getContextPath()+"/page");
    }
}
