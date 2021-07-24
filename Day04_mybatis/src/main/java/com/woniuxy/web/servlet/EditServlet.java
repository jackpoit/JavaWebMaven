package com.woniuxy.web.servlet;

import com.woniuxy.entity.Employee;
import com.woniuxy.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author
 * @date 2021/7/21 0021-9:41
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    EmployeeServiceImpl esi = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 处理请求乱码和响应乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //2. 获取更新的表单参数（数据校验）
        Integer id = Integer.parseInt(req.getParameter("eid"));
        String ename = req.getParameter("ename");
        String title = req.getParameter("title");
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
        // 3. 封装对象
        Employee employee = new Employee(id, ename, title, salary, managerId, deptId);
        //4. 调用业务逻辑层来完成更新业务
        boolean flag = esi.update(employee);
        resp.getWriter().write("<script>alert('"+(flag?"更新成功":"更新失败")+"')</script>");
        resp.setHeader("Refresh","0;url="+req.getContextPath()+"/page");  // 更新结束后需要数据回显
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
