package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: rua
 * @Date: 2021/7/27 18:59
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anjuke {
	private Integer id;
	private String zone;
	private String trainLine;
	private BigDecimal rentMoney;
	private String room;
	private String rentType;
	private String layout;
	private String direction;

	public Anjuke(String zone, BigDecimal rentMoney) {
		this.zone = zone;
		this.rentMoney = rentMoney;
	}
}
