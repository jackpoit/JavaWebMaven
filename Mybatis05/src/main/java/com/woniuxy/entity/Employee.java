package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rua
 * 员工实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	private Integer id;
	private String tno;
	private String name;
	private String gender;
	private Date birthday;
	private String title;
	private BigDecimal salary;
	private Integer managerId;
	private Department dept;
	private String imagePath;


}
