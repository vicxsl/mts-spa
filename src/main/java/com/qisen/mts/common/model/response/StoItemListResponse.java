/**
 * 
 */
package com.qisen.mts.common.model.response;

import java.util.List;

import com.qisen.mts.common.model.entity.BaseEntity;
import com.qisen.mts.common.model.entity.sto.StoItemWithPdInfo;

/**
 * @author forbr
 *
 */
public class StoItemListResponse extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8771305743342731322L;
	private Integer totalStoNum;
	private Double totalStoMoney;
	private Integer totalCount;
	private List<StoItemWithPdInfo> items;
	public Integer getTotalStoNum() {
		return totalStoNum;
	}
	public void setTotalStoNum(Integer totalStoNum) {
		this.totalStoNum = totalStoNum;
	}
	public Double getTotalStoMoney() {
		return totalStoMoney;
	}
	public void setTotalStoMoney(Double totalStoMoney) {
		this.totalStoMoney = totalStoMoney;
	}
	public List<StoItemWithPdInfo> getItems() {
		return items;
	}
	public void setItems(List<StoItemWithPdInfo> items) {
		this.items = items;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
