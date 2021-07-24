package com.woniuxy.service;

import com.woniuxy.entity.PageModel;
import com.woniuxy.entity.User;

/**
 * @Author: rua
 * @Date: 2021/7/24 21:43
 * @Description:
 */
public interface UserService {

	User findByName(String userName);

	boolean registerUser(User user);

	PageModel<User> findOnePage(String keyword,int currentPage);

	boolean	remove(int id);

	boolean edit(User user);
}
