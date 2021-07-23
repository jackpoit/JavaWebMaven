package com.woniuxy.service;

import java.math.BigDecimal;

/**
 * @Author: rua
 * @Date: 2021/7/23 16:13
 * @Description:
 */
public interface AccountService {

	/**
	 * 转账操作
	 * @param from 转账人
	 * @param to 收款人
	 * @param money 钱数
	 * @return true 成功
	 */
	boolean transferMoney(String from, String to, BigDecimal money);
}
