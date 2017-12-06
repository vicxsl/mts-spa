/**
 * 
 */
package com.qisen.mts.common.model.request;

import java.util.List;

/**
 * @author forbr
 *
 */
public class BulkChangeTypeNoRequest {
	private List<Integer> ids;
	private String typeNo;
	
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
}
