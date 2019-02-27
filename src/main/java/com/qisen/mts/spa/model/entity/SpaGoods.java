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
	private String appid;
	private String name;
	private String no;//商品编号
	private String barCode;//商品编码
	private Integer type;//商品类型id
	private Integer son_type;//商品子类型id
	private String unit;//商品单位
	private String spec;//商品规格
	private double num;//数量
	private double buyingPrice;//进货价
	private double preferencePrice;//优惠价
	private double salePrice;//销售价
	private double costPrice;//成本价
	private Integer goodsCompanyId;//商品品牌id
	private String goodsCompany;//商品品牌
	private String remark;//备注
	private String describe;//商品描述
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
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
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
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
	public double getPreferencePrice() {
		return preferencePrice;
	}
	public void setPreferencePrice(double preferencePrice) {
		this.preferencePrice = preferencePrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
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
