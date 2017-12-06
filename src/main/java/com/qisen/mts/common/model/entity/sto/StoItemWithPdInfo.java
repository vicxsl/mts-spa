/**
 * 
 */
package com.qisen.mts.common.model.entity.sto;

/**
 * @author forbr
 *
 */
public class StoItemWithPdInfo extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7601448147927065118L;
	private String itemNo;
	private String itemName;
	private String itemTypeNo;
	private String itemFlag;
	private String itemProp;
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemTypeNo() {
		return itemTypeNo;
	}
	public void setItemTypeNo(String itemTypeNo) {
		this.itemTypeNo = itemTypeNo;
	}
	public String getItemFlag() {
		return itemFlag;
	}
	public void setItemFlag(String itemFlag) {
		this.itemFlag = itemFlag;
	}
	public String getItemProp() {
		return itemProp;
	}
	public void setItemProp(String itemProp) {
		this.itemProp = itemProp;
	}
}
