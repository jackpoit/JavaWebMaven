package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

/**
 * @Author: rua
 * @Date: 2021/7/27 15:48
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	private  Integer id;
	private String dName;
	private  String location;
	private  String area;
	private HashSet<Employee> emps;
}
