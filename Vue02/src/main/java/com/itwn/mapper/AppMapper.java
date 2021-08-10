package com.itwn.mapper;

import com.itwn.bean.App;

import java.util.List;

public interface AppMapper {
    List<App> queryApp(App app);

    int deleteAppById(int id);

    int updateApp(App app);

    int addApp(App app);

    int deleteManyApp(int[] idArr);
}
