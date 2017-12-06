package com.qisen.mts.common.model.entity.base;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class Item extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer eid;
	private String name;
	private String no;
	private Double price;
	/**
	 * 类型:1项目,2卖品,3会员卡
	 */
	private Integer type;
	private String img;
	private String typeNo;
	private String shopBlock;
	private String depAuth;
	private JSONObject prop;
	private Integer status;
	/**
	 * 大小项 :0小项,1大项/是否卖品:0非卖品,1卖品
	 */
	private Integer flag;
	private Date inputDate;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getShopBlock() {
		return shopBlock;
	}

	public void setShopBlock(String shopBlock) {
		this.shopBlock = shopBlock;
	}

	public String getDepAuth() {
		return depAuth;
	}

	public void setDepAuth(String depAuth) {
		this.depAuth = depAuth;
	}

	public JSONObject getProp() {
		return prop;
	}

	public void setProp(JSONObject prop) {
		this.prop = prop;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

}
