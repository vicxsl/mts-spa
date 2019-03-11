package com.qisen.mts.common.model.request;

import com.qisen.mts.common.model.constant.ConfigConsts;

public class PageRequest<T> extends BaseRequest<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7861414123053831925L;

	public Integer pageNum; // 椤电爜
	public Integer pageSize; // 姣忛〉璁板綍鏁�
	public Integer startIndex; // 璧峰璁板綍
	public Integer endIndex; // 缁撴潫璁板綍

	public PageRequest() {
		super();
		this.pageNum = 1;
		this.pageSize = ConfigConsts.DEFAULT_PAGE_SIZE;
		this.startIndex = 0;
		this.endIndex = 0;
	}

	public Integer getPageNum() {
		return pageNum > 0 ? pageNum : 1;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
		this.setPageSize(this.pageSize);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.setStartIndex((this.getPageNum() - 1) * pageSize + 1);
		this.setEndIndex(this.startIndex + pageSize - 1);
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

}
