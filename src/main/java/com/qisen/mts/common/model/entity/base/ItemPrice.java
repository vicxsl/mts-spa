package com.qisen.mts.common.model.entity.base;

import com.qisen.mts.common.model.entity.BaseEntity;

public class ItemPrice extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1630140863280972613L;

	private Integer id;
	private Integer sid;
	private String itemNo;
	private String img;
	private String tagNos;
	private Double price;
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTagNos() {
		return tagNos;
	}

	public void setTagNos(String tagNos) {
		this.tagNos = tagNos;
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

	@Override
	public String toString() {
		return "ItemPrice [id=" + id + ", sid=" + sid + ", itemNo=" + itemNo + ", img=" + img + ", tagNos=" + tagNos
				+ ", price=" + price + ", type=" + type + "]";
	}

	
}
