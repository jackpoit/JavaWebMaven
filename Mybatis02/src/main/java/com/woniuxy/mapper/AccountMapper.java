package com.woniuxy.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @Author: rua
 * @Date: 2021/7/23 16:00
 * @Description:
 */
public interface AccountMapper {

	/**
	 * 存钱的方法
	 * @param name
	 * @param money
	 * @return
	 */
	int plus(@Param("name") String name, @Param("money") BigDecimal money);

	/**
	 * 取钱的方法
	 * @param name
	 * @param money
	 * @return
	 */
	int mins(@Param("name")String name, @Param("money")BigDecimal money);

}
