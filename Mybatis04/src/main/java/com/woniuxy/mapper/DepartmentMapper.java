package com.woniuxy.mapper;

import com.woniuxy.entity.Department;

/**
 * @Author: rua
 * @Date: 2021/7/27 16:40
 * @Description:
 */
public interface DepartmentMapper {

	Department findById(int id);//根据id查询对应的部门


	Department findManyById(int id);//根据id查询部门(一对多)


	Department findStepById(int id);//根据id查询部门(分布懒加载关联映射查询)

}
