/**
 * 
 */
package com.qisen.mts.common.model.response;

import java.util.List;

import com.qisen.mts.common.model.entity.BaseEntity;
import com.qisen.mts.common.model.entity.sto.BillWithDetails;

/**
 * @author forbr
 *
 */
public class StoBillListResponse extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8771305743342731322L;
	private Double totalMoney;
	private Integer totalCount;
	private List<BillWithDetails> items;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<BillWithDetails> getItems() {
		return items;
	}
	public void setItems(List<BillWithDetails> items) {
		this.items = items;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
}
