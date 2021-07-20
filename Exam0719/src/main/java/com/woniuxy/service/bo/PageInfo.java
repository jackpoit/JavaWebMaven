package com.woniuxy.service.bo;

import java.util.List;

public class PageInfo<T> {

	private List<T> list;
	private int pageNumber;
	private int pageSize;
	private boolean isFirst;
	private boolean isLast;
}
