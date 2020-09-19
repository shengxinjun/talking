package com.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author sheng
 */
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = -5383516184368409746L;
    /**
     * 当前位于多少页
     */
    private int pageNumber = 1;
    /**
     * 每页的数量
     */
    private int pageSize = 10;
    /**
     * 用于作为查询条件的关键词
     */
    private String keyword;
    /**
     * 用于作为查询条件的时间范围
     */
    private DateRange dateRange;
    /**
     * 用于作为排序依据的字段
     */
    private String orderField;
    /**
     * 正序排序还是倒叙排序
     */
    private boolean orderDesc;
    /**
     * 返回数据总数
     */
    private int totalCount;
    /**
     * 查询出的数据集合
     */
    private List<T> list;
    /**
     * 访问次数统计
     */
    private int visitCount;
    
    

    public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public static <T> Paging<T> create() {
        return new Paging<>();
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public Paging<T> setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Paging<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public Paging<T> setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public Paging<T> setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
        return this;
    }

    public String getOrderField() {
        return orderField;
    }

    public Paging setOrderField(String orderField) {
        this.orderField = orderField;
        return this;
    }

    public boolean isOrderDesc() {
        return orderDesc;
    }

    public Paging setOrderDesc(boolean orderDesc) {
        this.orderDesc = orderDesc;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Paging<T> setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0)
            totalPage += 1;
        return totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public Paging<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public int offset() {
        return pageSize * (pageNumber - 1);
    }

    @Override
    public String toString() {
        return "Paging{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", keyword='" + keyword + '\'' +
                ", dateRange=" + dateRange +
                ", orderField='" + orderField + '\'' +
                ", orderDesc=" + orderDesc +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}