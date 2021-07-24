package com.woniuxy.service;

import com.woniuxy.entity.Employee;
import com.woniuxy.entity.PageModel;

import java.util.List;

/**
 * 用户的业务逻辑层接口
 * @author
 * @date 2021/7/20 0020-16:03
 */
public interface EmployeeService {

    /**
     * 展示所有员工数据
     * @return
     */
    List<Employee> showAll();

    /**
     * 删除员工数据
     * @param id
     * @return
     */
    boolean remove(int id);

    /**
     * 更新员工数据（工号，性别，生日不可更新）
     * @param emp
     * @return
     */
    boolean update(Employee emp);

    /**
     * 根据关键字查询
     * @param keyword
     * @return
     */
    List<Employee> findByKeyword(String keyword);

    /**
     * 根据当前页码封装一个分页模型
     * @param currentPage
     * @return
     */
    PageModel<Employee> findOnePage(int currentPage, String keyword);


    /**
     * 添加员工
     * @param employee
     * @return
     */
    public boolean addEmployee(Employee employee);

    /**
     * 更新员工的头像
     * @param id
     * @param imgPath
     * @return
     */
    public boolean updateImg(int id,String imgPath);
}
