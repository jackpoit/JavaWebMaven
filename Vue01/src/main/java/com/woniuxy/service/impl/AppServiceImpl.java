package com.woniuxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.entity.App;
import com.woniuxy.mapper.AppMapper;
import com.woniuxy.service.AppService;
import com.woniuxy.util.DBUtil;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/8/9 10:03
 * @Description:
 */
public class AppServiceImpl implements AppService {
	@Override
	public PageInfo<App> showByKeyword(int currentPage, App app) {
		AppMapper mapper = DBUtil.getMapper(AppMapper.class);
		PageHelper.startPage(currentPage,4);
		List<App> list = mapper.findByCondition(app);
		DBUtil.close();
		return new PageInfo<>(list);
	}



	@Override
	public boolean doDelete(Integer... ids) {
		if (ids == null) {
			return false;
		}
		AppMapper mapper = DBUtil.getMapper(AppMapper.class);
		int row = mapper.deleteByIds(ids);
		DBUtil.close();
		return row > 0;
	}

	@Override
	public boolean doAdd(App app) {
		if (app==null) {
			return false;
		}
		AppMapper mapper = DBUtil.getMapper(AppMapper.class);
		int row = mapper.add(app);
		DBUtil.close();
		return row>0;
	}

	@Override
	public boolean doUpdate(App app) {
		if (app==null) {
			return false;
		}
		AppMapper mapper = DBUtil.getMapper(AppMapper.class);
		int row = mapper.update(app);
		DBUtil.close();
		return row>0;
	}
}
