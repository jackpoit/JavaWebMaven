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
 * 首页 Servlet
 * @author
 * @date 2021/7/20 0020-15:52
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private EmployeeServiceImpl esi = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.准备从数据库查询出首页数据(业务逻辑层 + DAO层)
        List<Employee> list = esi.showAll();
        //2. 将数据放在request域中
        req.setAttribute("empList",list);
        //3. 转发到首页
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
