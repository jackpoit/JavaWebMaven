package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rua
 * 员工实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee  {
	private Integer id;
	private String tno;
	private String name;
	private String gender;
	private Date birthday;
	private String title;
	private BigDecimal salary;
	private Integer managerId;
	private Integer deptId;
	private String imagePath;

	public Employee(String tno, String name, String gender, Date birthday, String title, BigDecimal salary, Integer managerId, Integer deptId, String imagePath) {
		this.tno = tno;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.title = title;
		this.salary = salary;
		this.managerId = managerId;
		this.deptId = deptId;
		this.imagePath = imagePath;
	}

	public Employee(Integer id, String name, String title, BigDecimal salary, Integer managerId, Integer deptId) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.salary = salary;
		this.managerId = managerId;
		this.deptId = deptId;
	}

	public Employee(String tno, String name, String gender, Date birthday, String title, BigDecimal salary, Integer managerId, Integer deptId) {
		this.tno = tno;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.title = title;
		this.salary = salary;
		this.managerId = managerId;
		this.deptId = deptId;
	}
}
