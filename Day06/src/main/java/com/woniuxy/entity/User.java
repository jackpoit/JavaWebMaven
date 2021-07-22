package com.woniuxy.entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import com.woniuxy.annotation.MyAnnotation;

import java.util.Date;

/**
 * @Author: rua
 * @Date: 2021/7/22 15:24
 * @Description:
 */

@MyAnnotation(value = "哈哈哈")
public class User {
	@MyAnnotation(value = "哈哈哈")
	public int id;
	@MyAnnotation(value = "哈哈哈")
	protected String name;
	@MyAnnotation(value = "哈哈哈")
	private String password;

	//无参构造必须
	public User() {
	}

	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Deprecated  //过时注解
	public void show(){
		System.out.println("111");
	}
	private int add(int a,int b){
		return a+b;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
