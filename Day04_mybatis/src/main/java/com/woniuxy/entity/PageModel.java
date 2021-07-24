package com.woniuxy.entity;

import java.util.List;

/**
 * 通用的分页模型类（普通分页 ，模糊分页）
 * @author
 * @date 2021/7/21 0021-11:34
 */
public class PageModel<T> {

    private Integer pageSize; // 一页显示多少条数据
    private Integer currentPage; // 当前页码
    private Integer prev; // 当前页的上一页
    private Integer next; // 当前页的下一页
    private Long total; // 总记录数
    private Integer totalPage; // 总页数
    private String keyword;  // 模糊查询关键字
    private List<T> list; // 当前页的数据集合

    public PageModel() {
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", prev=" + prev +
                ", next=" + next +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", keyword='" + keyword + '\'' +
                ", list=" + list +
                '}';
    }
}
