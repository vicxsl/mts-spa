/**
 * 
 */
package com.qisen.mts.common.model.entity.sto;

import java.util.List;

/**
 * @author forbr
 *
 */
public class BillWithDetails extends Bill{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7311338333528312696L;
	private List<BillDetail> items;
	public List<BillDetail> getItems() {
		return items;
	}
	public void setItems(List<BillDetail> items) {
		this.items = items;
	}
}
