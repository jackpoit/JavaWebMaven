package com.itwn.service.impl;

import com.itwn.bean.App;
import com.itwn.mapper.AppMapper;
import com.itwn.service.AppService;
import com.itwn.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AppServiceImpl implements AppService {
    private AppMapper appMapper;
    {
        //自动提交
        SqlSession sqlSession= SqlSessionUtil.createSqlSession("mybatis-config.xml",true);
        appMapper=sqlSession.getMapper(AppMapper.class);
    }
    @Override
    public List<App> queryApp(App app) {
        return appMapper.queryApp(app);
    }

    @Override
    public int deleteAppById(int id) {
        return appMapper.deleteAppById(id);
    }

    @Override
    public int updateApp(App app) {
        return appMapper.updateApp(app);
    }

    @Override
    public int addApp(App app) {
        return appMapper.addApp(app);
    }

    @Override
    public int deleteManyApp(int[] idArr) {
        return appMapper.deleteManyApp(idArr);
    }
}
