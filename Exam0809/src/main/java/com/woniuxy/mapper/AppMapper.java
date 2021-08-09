package com.woniuxy.mapper;

import com.woniuxy.entity.App;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/8/9 9:35
 * @Description:
 */
public interface AppMapper {

	//根据条件查询
	List<App> findByCondition(App app);

	/**
	 * 删除
	 * @param ids id集合
	 * @return 受影响行数
	 */
	int deleteByIds(@Param("ids") Integer... ids);


	int add(App app);

	int update(App app);
}
