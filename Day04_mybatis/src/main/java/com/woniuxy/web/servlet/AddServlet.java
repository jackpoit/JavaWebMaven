package com.woniuxy.web.servlet;

import com.woniuxy.entity.Employee;
import com.woniuxy.service.impl.EmployeeServiceImpl;
import com.woniuxy.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @date 2021/7/21 0021-15:51
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {

    EmployeeServiceImpl esi = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 处理请求和响应乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //2. 获取请求参数
        String tno = req.getParameter("tno");
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String gender = req.getParameter("gender");
        String birthdayStr = req.getParameter("birthday");
        Date birthday = DateUtil.parse(birthdayStr);
        String salaryStr = req.getParameter("salary");
        BigDecimal salary = new BigDecimal(salaryStr);
        String managerIdStr = req.getParameter("managerId");
        Integer managerId = null;
        if(!"BOSS".equals(managerIdStr)){
            managerId = Integer.parseInt(managerIdStr);
        }
        String deptIdStr = req.getParameter("deptId");
        Integer deptId = null;
        if(!"轮岗".equals(deptIdStr)){
            deptId = Integer.parseInt(deptIdStr);
        }
        //3. 封装对象
        Employee employee = new Employee(tno, name, gender, birthday, title, salary, managerId, deptId);
        //4.  调用业务层api
        boolean flag = esi.addEmployee(employee);
        //5. 根据处理结果来返回响应
        resp.getWriter().write("<script>alert('"+(flag?"添加成功":"添加失败")+"')</script>");
        resp.setHeader("Refresh","0;url="+req.getContextPath()+"/page");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
