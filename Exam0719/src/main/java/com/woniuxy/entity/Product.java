package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID =1l;
	private Integer id;
	private String pname;
	private String pinfo;
	private BigDecimal price;
	private Integer stock;
	private Integer sale;
	private String imagePath;

	public Product(String pname, String pinfo, BigDecimal price, Integer stock, Integer sale, String imagePath) {
		this.pname = pname;
		this.pinfo = pinfo;
		this.price = price;
		this.stock = stock;
		this.sale = sale;
		this.imagePath = imagePath;
	}






}
