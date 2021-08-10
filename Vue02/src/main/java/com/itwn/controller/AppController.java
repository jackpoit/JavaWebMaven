package com.itwn.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itwn.bean.App;
import com.itwn.service.AppService;
import com.itwn.service.impl.AppServiceImpl;
import com.itwn.util.BaseServlet;
import com.itwn.util.UpLoadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet("/appController")
public class AppController extends BaseServlet{
    private AppService appService=new AppServiceImpl();
    private int nowPage=1;
    private int pageCount=4;
    private int lastPage=0;
    public void queryApp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr=req.getParameter("nowPage");
        if(pageStr!=null && !"".equals(pageStr)){
            int temp=Integer.parseInt(pageStr);
            if(temp<0){//说明你点的是上一页或者下一页
                if(temp==-1&&nowPage>1){//上一页
                    nowPage--;
                }

                if(temp==-2&&nowPage<lastPage){
                    nowPage++;
                }
            }else{//说明你点的是数字页码
                nowPage=temp;
            }
        }
        App app=new App();
        app.setAppName(req.getParameter("appName"));
        app.setAppType(req.getParameter("appType"));
        app.setAppPlatform(req.getParameter("appPlatform"));

        //1.设置分页的当前页数  以及一页显示的最大数据条数
        PageHelper.startPage(nowPage,pageCount);
        List<App> appList=appService.queryApp(app);
        PageInfo<App> info=new PageInfo<>(appList);
        lastPage=info.getNavigateLastPage();//第一查询出分页数据 记录最后一页
        //2.响应json数据至客户端
        resp.getWriter().write(JSON.toJSONString(info));
    }

    public void deleteApp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String idStr=req.getParameter("id");
        String status="0";
        if(idStr!=null&&!"".equals(idStr)){
            int id=Integer.parseInt(idStr);
            if(appService.deleteAppById(id)>0){
                status="1";
            }
        }
        resp.getWriter().write(status);
    }

    public void addApp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        App app=new App();
        app.setAppName(req.getParameter("appName"));
        app.setAppType(req.getParameter("appType"));
        app.setAppPlatform(req.getParameter("appPlatform"));
        app.setAppSize(Integer.parseInt(req.getParameter("appSize")));
        app.setAppImg(UpLoadUtil.up("appImg","app",req));
        resp.getWriter().write(appService.addApp(app)>0?"1":"0");
    }
    public void updateApp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        App app=new App();
        app.setId(Integer.parseInt(req.getParameter("id")));
        app.setAppName(req.getParameter("appName"));
        app.setAppType(req.getParameter("appType"));
        app.setAppPlatform(req.getParameter("appPlatform"));
        app.setAppSize(Integer.parseInt(req.getParameter("appSize")));
        app.setAppImg(UpLoadUtil.up("appImg","app",req));

        resp.getWriter().write(appService.updateApp(app)>0?"1":"0");
    }
    public void deleteManyApp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String idStr=req.getParameter("idStr");
        String[] idStrArray=idStr.split(",");
        int[] ids=new int[idStrArray.length];
        for(int i=0;i<ids.length;i++){
            ids[i]=Integer.parseInt(idStrArray[i]);
        }
        resp.getWriter().write(appService.deleteManyApp(ids)>0?"1":"0");
    }
}
