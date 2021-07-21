package com.woniuxy.web.servlet;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.woniuxy.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rua
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private EmployeeServiceImpl esi=new EmployeeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String id = req.getParameter("id");
		if (id!=null){
			boolean removeFlag=esi.remove(Integer.parseInt(id));
			if (removeFlag){
				resp.getWriter().write("<script>alert('删除成功')</script>");

			}else {
				resp.getWriter().write("<script>alert('删除失败')</script>");
			}

		}
		//重定向到首页的Servlet,查询数据后再回到index.jsp
		resp.setHeader("refresh","0;url="+req.getContextPath()+"/page");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
