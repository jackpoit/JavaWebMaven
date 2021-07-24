package com.woniuxy.web.servlet;

import com.woniuxy.entity.Employee;
import com.woniuxy.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author
 * @date 2021/7/21 0021-10:54
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    EmployeeServiceImpl esi = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数（查询关键词）
        String keyword = req.getParameter("keyword");
        //2. 调用业务逻辑层API
        List<Employee> list = esi.findByKeyword(keyword);
        //3. 将数据存放到request域中
        req.setAttribute("empList",list);
        //4. 转发到首页
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
