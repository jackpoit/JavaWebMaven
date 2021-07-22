package com.woniuxy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rua
 * 员工实体类
 */
public class Employee implements Serializable {
	private static final long serialVersionUID = -8758717030059239984L;
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

	public Employee() {
	}

	public Employee(Integer id, String tno, String name, String gender, Date birthday, String title, BigDecimal salary, Integer managerId, Integer deptId, String imagePath) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", tno='" + tno + '\'' +
				", name='" + name + '\'' +
				", gender='" + gender + '\'' +
				", birthday=" + birthday +
				", title='" + title + '\'' +
				", salary=" + salary +
				", managerId=" + managerId +
				", deptId=" + deptId +
				", imagePath='" + imagePath + '\'' +
				'}';
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
