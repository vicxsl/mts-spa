package com.qisen.mts.common.model.response;

import com.qisen.mts.common.model.constant.ConfigConsts;

public class PageResponse<T> extends CommObjResponse<T> {

	private Integer pageNum; // 当前页数
	private Integer pageSize; // 每页记录数
	private Integer pageCount; // 总页数
	private Integer count; // 总记录数

	public PageResponse() {
		super();
		this.pageNum = 1;
		this.pageSize = ConfigConsts.DEFAULT_PAGE_SIZE;
		this.pageCount = 0;
		this.count = 0;
	}
	
	public PageResponse(Integer pageNum, Integer pageSize, Integer count) {
		super();
		this.pageNum = pageNum;
		this.pageSize = ConfigConsts.DEFAULT_PAGE_SIZE;
		this.pageCount = 0;
		this.count = 0;
		this.setPageSize(pageSize);
		this.setCount(count);
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.setCount(this.count);
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
		if(this.pageSize > 0)
			this.pageCount = (int) Math.ceil(count.doubleValue() / this.pageSize.doubleValue());
	}

}
