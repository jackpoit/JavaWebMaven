package com.woniuxy.web.servlet;

import com.woniuxy.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @date 2021/7/20 0020-17:18
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    EmployeeServiceImpl esi = new EmployeeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //1. 获取删除员工工号 id
        String id = request.getParameter("id");
        //2. 调用业务层完成删除
        if(id!= null){
            boolean removeFlag = esi.remove(Integer.parseInt(id));
            if(removeFlag){ // 删除成功
                response.getWriter().write("<script>alert('恭喜，删除成功')</script>");
            }else{ // 删除失败
                response.getWriter().write("<script>alert('很遗憾，删除失败')</script>");
            }
        }
        // 重定向到首页的Servlet，查询数据后再回到index.jsp
        response.setHeader("refresh","0;url="+request.getContextPath()+"/page");
    }
}
