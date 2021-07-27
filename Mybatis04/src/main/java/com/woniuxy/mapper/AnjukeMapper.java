package com.woniuxy.mapper;

import com.woniuxy.entity.Anjuke;
import com.woniuxy.entity.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: rua
 * @Date: 2021/7/27 18:55
 * @Description:
 */
public interface AnjukeMapper {


	int add(Anjuke anjuke);//添加一个

	int addList(@Param("anjukes") List<Anjuke> anjukes);//添加集合

	int deleteByIds(@Param("ids") int... ids);//删除一个或多个

	int update(Anjuke anjuke);//更新一个

	Anjuke findById(int id);//根据id查一个

	List<Anjuke> findAll(); //查所有

	List<Map<String, Object>> findAllMap(); //查所有集合

	@MapKey("id")
		//将查询到的id列的值作为Map的Key,将resultMap的结果作value
	Map<Integer, Anjuke> findAllAnjuke();//查询所有

	List<Anjuke> findLikeName(String keyword);//名字模糊查询

	List<Anjuke> findByCondition(Anjuke anjuke);//根据传入的条件查询

	List<Anjuke> findLimit(@Param("kw") String keyword,@Param("start") int start,@Param("num") int num);//分页模糊
}
