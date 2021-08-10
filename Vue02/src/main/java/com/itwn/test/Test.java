package com.itwn.test;

import com.itwn.bean.App;
import com.itwn.service.AppService;
import com.itwn.service.impl.AppServiceImpl;

public class Test {
    @org.junit.Test
    public void test01(){
        AppService appService=new AppServiceImpl();
        System.out.println(appService.queryApp(new App()));
    }
}
