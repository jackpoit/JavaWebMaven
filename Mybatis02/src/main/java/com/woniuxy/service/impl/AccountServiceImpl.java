package com.woniuxy.service.impl;

import com.woniuxy.mapper.AccountMapper;
import com.woniuxy.service.AccountService;
import com.woniuxy.util.DBUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @Author: rua
 * @Date: 2021/7/23 16:14
 * @Description: 业务实现类(调用Mybatis的Mapper接口对象)
 */
public class AccountServiceImpl implements AccountService {
	@Override
	public boolean transferMoney(String from, String to, BigDecimal money) {
		SqlSession sqlSession = DBUtil.openSqlSession(false);
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
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

			sqlSession.commit();
			int a=10/0;
			return true;
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback(); //出错后回滚
		}
		finally {
			sqlSession.close(); //释放资源(归还到数据库连接池
		}
		return false;
	}
}
