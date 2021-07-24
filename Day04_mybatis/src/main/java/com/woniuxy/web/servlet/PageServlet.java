package com.woniuxy.web.servlet;

import com.woniuxy.entity.Employee;
import com.woniuxy.entity.PageModel;
import com.woniuxy.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 分页的servlet
 * @author
 * @date 2021/7/21 0021-11:49
 */
@WebServlet("/page")
public class PageServlet extends HttpServlet {
    EmployeeServiceImpl esi = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       //1. 获取当前页码
       String currentPageStr = req.getParameter("currentPage");// 约定优于编程
       int currentPage = 1;
       if(currentPageStr != null && !"".equals(currentPageStr)){
           currentPage = Integer.parseInt(currentPageStr);
       }
       //2. 获取模糊查询关键字
        String keyword = req.getParameter("keyword");
        if(keyword == null){
            keyword = "";
        }
        //3. 调用业务层api查询分页模型
        PageModel<Employee> model = esi.findOnePage(currentPage, keyword);
        //4. 将分页模型存放到request域中
        req.setAttribute("pageModel", model);
        //5. 转发到page.jsp
        req.getRequestDispatcher("/page.jsp").forward(req,resp);
    }
}
