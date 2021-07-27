package com.woniuxy.service;

import com.woniuxy.entity.PageModel;
import com.woniuxy.entity.User;

/**
 * @Author: rua
 * @Date: 2021/7/24 21:43
 * @Description:
 */
public interface UserService {
	User loginUser(String userName,String password);

//	User findByName(String userName);

	boolean registerUser(User user);

	PageModel<User> findOnePage(String keyword,int currentPage);

	boolean	remove(Integer... ids);

	boolean edit(User user);
}
