package com.qisen.mts.common.model.entity.sto;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;;

public class BillDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4944548372312919356L;
	
	private Integer id;
	private Integer eid;
	private Integer billId;
	private Date day;
	private Integer outAppid;
	private Integer inAppid;
	private String empNo;
	private Integer itemId;
	private String itemNo;
	private Integer num;
	private Double money;
	/**
	 * 单据大类 :1入库单,2出库单,3调拨单
	 */
	private Integer bType;
	/**
	 * 单据小类:1商品销售,2采购进货,3盘盈入库,4顾客退货,5获赠入库,6其他入库,7员工领用,8员工自购,9盘亏出库,10赠送出库,11损坏出库,12退货出库,13其他出库,14调拨
	 */
	private Integer sType;
	private Integer status;
	private Integer version;
	private Double inPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Integer getOutAppid() {
		return outAppid;
	}

	public void setOutAppid(Integer outAppid) {
		this.outAppid = outAppid;
	}

	public Integer getInAppid() {
		return inAppid;
	}

	public void setInAppid(Integer inAppid) {
		this.inAppid = inAppid;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getbType() {
		return bType;
	}

	public void setbType(Integer bType) {
		this.bType = bType;
	}

	public Integer getsType() {
		return sType;
	}

	public void setsType(Integer sType) {
		this.sType = sType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

}
