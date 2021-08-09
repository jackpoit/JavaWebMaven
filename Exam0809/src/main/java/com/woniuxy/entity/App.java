package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rua
 * @Date: 2021/8/9 9:32
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class App {
	private Integer id;
	private String appName;
	private Integer appSize;
	private String appType;
	private String appPlatform;
	private Integer appCount;
	private String appImg;
}
