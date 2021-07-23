package com.woniuxy.service.impl;

import com.woniuxy.mapper.AccountMapper;
import com.woniuxy.service.AccountService;
import com.woniuxy.util.DBUtil;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @Author: rua
 * @Date: 2021/7/23 16:46
 * @Description:
 */
public class AccountSuperServiceImpl implements AccountService {
	//使用封装好的框架
	@Override
	public boolean transferMoney(String from, String to, BigDecimal money) {
		AccountMapper mapper = DBUtil.getTransMapper(AccountMapper.class);
		try {
			//1.转账人扣钱
			int row=mapper.mins(from,money);
			if (row==0){
				throw new SQLException();
			}
			//2.收款人加钱
			row=mapper.plus(to,money);
			if (row==0){
				throw new SQLException();
			}
			//3.所以操作全部成功
			DBUtil.commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			DBUtil.rollback();
		}
		finally {
			DBUtil.close();
		}

		return false;
	}
}
