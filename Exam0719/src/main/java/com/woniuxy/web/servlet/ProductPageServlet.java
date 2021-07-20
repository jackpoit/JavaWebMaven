package com.woniuxy.web.servlet;

import com.woniuxy.entity.Product;
import com.woniuxy.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/productPage")
public class ProductPageServlet extends HttpServlet {
	private static ProductServiceImpl psi = new ProductServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = Integer.parseInt(req.getParameter("page"));
		int len= Integer.parseInt(req.getParameter("len"));
		List<Product> list=psi.selectByPage(page,len);



		if (list == null) {
			req.setAttribute("productPageFlag", "2");
			System.out.println("111");
		}
		req.setAttribute("productPageList",list);
		req.setAttribute("productPage",page);
		req.getRequestDispatcher("/index.jsp").forward(req,resp);

	}
}
