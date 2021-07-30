package com.woniuxy.test;

import com.alibaba.fastjson.JSON;
import com.woniuxy.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/30 10:06
 * @Description:
 */
public class FastJsonTest {

	public static void main(String[] args) {
		User user1=new User("harry","123","1236434623","sgg@qs.com");
		User user2=new User("harry","123","1236434623","sgg@qs.com");
		User user3=new User("harry","123","1236434623","sgg@qs.com");
		List<User> list=new ArrayList<>();
		list.add(user1);
		list.add(user2);
		list.add(user3);



		//1.通过fastJson来将任意类型的对象转换成json格式的字符串(序列化的中间产物)
		String jsonStr = JSON.toJSONString(user1);
		String jsonListStr = JSON.toJSONString(list);
		System.out.println(jsonStr);
		System.out.println(user1);
		System.out.println(list);
		System.out.println(jsonListStr);

		//2.如何将json格式的字符串转换成一个Java对象(反序列中间产物)
		User parseUser = JSON.parseObject(jsonStr, User.class);
		System.out.println(parseUser);

		//3.解析集合
		List<User> parseList = JSON.parseArray(jsonListStr, User.class);
		System.out.println(parseList);

	}
}
