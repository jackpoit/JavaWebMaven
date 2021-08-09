package com.woniuxy.service;

import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.App;

/**
 * @Author: rua
 * @Date: 2021/8/9 9:45
 * @Description:
 */
public interface AppService {

	PageInfo<App> showByKeyword(int currentPage, App app);

	boolean doDelete(Integer... ids);

	boolean doAdd(App app);

	boolean doUpdate(App app);

}
