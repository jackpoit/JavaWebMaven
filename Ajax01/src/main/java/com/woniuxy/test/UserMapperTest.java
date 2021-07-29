package com.woniuxy.test;

import com.woniuxy.entity.User;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.util.DBUtil;
import org.junit.Test;

import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/29 14:53
 * @Description:
 */
public class UserMapperTest {

	@Test
	public void findById(){
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		User user = mapper.findByPrimaryKey(1);
		System.out.println(user);
		DBUtil.close();
	}

	@Test
	public void findCondition(){
		UserMapper mapper = DBUtil.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("jack");
		List<User> list = mapper.findCondition(null);//传null查所有
		for (User u : list) {
			System.out.println(u);
		}


		DBUtil.close();
	}

}
