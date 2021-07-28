package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: rua
 * @Date: 2021/7/28 17:31
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String password;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
}
