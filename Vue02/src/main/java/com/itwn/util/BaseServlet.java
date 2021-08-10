package com.itwn.util;
import com.github.pagehelper.util.StringUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
/**
 * 处理所有请求的通用Servlet
 **/
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1. 处理POST请求乱码与相应乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 2.获取请求参数 ： 执行的方法名
        String methodName = req.getParameter("m");// 方法名
        if(StringUtil.isEmpty(methodName)){
            try {
                throw new NoSuchMethodException("not found "+methodName+" method");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        //3.根据请求的Servlet + methodName 动态执行此方法
        Class<? extends BaseServlet> clazz = this.getClass(); //  或当前执行类的字节码对象
        try {
            //4.查找类中的方法
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //5. 执行此方法
            method.setAccessible(true);
            Object obj = method.invoke(this, req, resp);
            // 6. 框架提供了两种处理方式（forward:转发  redirect: 重定向）
            if(obj != null && obj instanceof String){
                String path = (String)obj;
                if(path.startsWith("forward:/")){  // 转发
                    path = path.substring(path.indexOf("/"));
                    req.getRequestDispatcher(path).forward(req,resp); // 转发实现
                }else if(path.startsWith("redirect:/")){ // 重定向
                    path = path.substring(path.indexOf("/"));
                    resp.setHeader("Refresh","0;url="+req.getContextPath()+path); // 定时刷新重定向
                }
            }
        } catch (Exception e) {
           e.printStackTrace(); // 暴露出执行框架时的所有异常
        }
    }
}
