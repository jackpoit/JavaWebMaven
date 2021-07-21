package com.woniuxy.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *	通用的分页模型类
 * @Author: jackpoit
 * @Date: 2021/07/21/11:34
 * @Description:
 */
public class PageModel<T> {
	private Integer pageSize;
	//一页显示多少条数据
	private Integer currentPage;
	//当前页码
	private Integer prev;
	//当前页的上一页
	private Integer next;
	//当前页的下一页
	private Long total;
	//总记录数
	private Integer totalPage;
	//当前页的数据
	private List<T> list;

	public PageModel() {
	}

	@Override
	public String toString() {
		return "PageModal{" +
				"pageSize=" + pageSize +
				", currentPage=" + currentPage +
				", prev=" + prev +
				", next=" + next +
				", total=" + total +
				", totalPage=" + totalPage +
				", list=" + list +
				'}';
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPrev() {
		return prev;
	}

	public void setPrev(Integer prev) {
		this.prev = prev;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
