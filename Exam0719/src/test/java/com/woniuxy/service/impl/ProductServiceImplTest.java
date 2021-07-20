package com.woniuxy.service.impl;

import com.woniuxy.entity.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceImplTest {
	ProductServiceImpl psi;

	@Before
	public void setUp() throws Exception {
		psi=new ProductServiceImpl();
	}

	@Test
	public void addProduct() {
	}

	@Test
	public void selectOneById() {
	}

	@Test
	public void selectAll() {
		List<Product> products = psi.selectAll();
		for (Product product : products) {
			System.out.println(product);
		}
	}

	@Test
	public void selectByPage() {
	}
}