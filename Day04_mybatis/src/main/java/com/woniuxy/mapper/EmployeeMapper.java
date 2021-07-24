package com.woniuxy.mapper;

import com.woniuxy.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/23 10:30
 * @Description: 基于Mybatis的持久层接口
 * 规则: 映射文件的namespace与接口绑定
 * 接口的方法名与id一致
 * 接口方法的形参类型与parameterType一致
 * 接口方法的返回类型与resultType一致
 */
public interface EmployeeMapper {

	int add(Employee employee);//添加员工对象

	int addList(@Param("emps") List<Employee> list);//插入一个员工集合

	int deleteByPrimaryKey(int id); //根据主键来删除员工对象

	int deleteByIds(@Param("ids") int...ids);//根据多个id来删除

	int update(Employee employee);//更新员工对象

	Employee findById(int id); //根据id查询指定的员工对象

	List<Employee> findAll();//查询所有员工对象

	List<String> findNames();//查询所有员工的姓名

	List<HashMap<String,Object>> findNameAndTitle(); //返回HashMap<列名,值>

	List<Employee> findLikeName(String keyword);//模糊查询

	List<Employee> findLimit(@Param("kw") String keyword ,@Param("start") int start,@Param("num") int num);
	//模糊查询

	long count(@Param("kw") String keyword);//查询个数

	List<Employee> findByKeyword(String keyword);//模糊查询




}
