package com.woniuxy.test;

import com.woniuxy.mapper.AccountMapper;
import com.woniuxy.service.impl.AccountServiceImpl;
import com.woniuxy.service.impl.AccountSuperServiceImpl;
import com.woniuxy.util.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author: rua
 * @Date: 2021/7/23 16:06
 * @Description:
 */
public class AccountMapperTest {


	@Test
	public void superTransMonry(){
		AccountSuperServiceImpl assi = new AccountSuperServiceImpl();
		boolean flag = assi.transferMoney("张三", "李四", new BigDecimal("100"));
		System.out.println(flag?"转账成功":"转账失败");
	}

	@Test
	public void transMonry(){
		AccountServiceImpl asi = new AccountServiceImpl();
		boolean flag = asi.transferMoney("张三", "李四", new BigDecimal("100"));
		System.out.println(flag?"转账成功":"转账失败");
	}

	@Test
	public void plus(){
		SqlSession sqlSession = DBUtil.openSqlSession(true);
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		int row = mapper.plus("张三", new BigDecimal("100"));
		System.out.println(row>0?"+成功":"+失败");
	}

	@Test
	public void mins(){
		SqlSession sqlSession = DBUtil.openSqlSession(true);
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		int row = mapper.mins("张三", new BigDecimal("100"));
		System.out.println(row>0?"-成功":"-失败");
	}
}
