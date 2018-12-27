package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class SpaGoods extends BaseEntity {

	/**
	 * spa商品资料
	 */
	private static final long serialVersionUID = -589365615135344946L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String name;
	private String no;//商品编号
	private String bar_code;//商品编码
	private Integer type;//商品类型id
	private Integer son_type;//商品子类型id
	private String unit;//商品单位
	private String spec;//商品规格
	private double num;//数量
	private double buyingPrice;//进货价
	private double costPrice;//成本价
	private double salePrice;//销售价
	private Integer goodsCompanyId;//商品品牌id
	private String goodsCompany;//商品品牌
	private Integer supplierId;//供货商id
	private String supplier;//供货商
	private String status;// 状态:0正常,1停售,2删除
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
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
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
	public String getBar_code() {
		return bar_code;
	}
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSon_type() {
		return son_type;
	}
	public void setSon_type(Integer son_type) {
		this.son_type = son_type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getGoodsCompanyId() {
		return goodsCompanyId;
	}
	public void setGoodsCompanyId(Integer goodsCompanyId) {
		this.goodsCompanyId = goodsCompanyId;
	}
	public String getGoodsCompany() {
		return goodsCompany;
	}
	public void setGoodsCompany(String goodsCompany) {
		this.goodsCompany = goodsCompany;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
