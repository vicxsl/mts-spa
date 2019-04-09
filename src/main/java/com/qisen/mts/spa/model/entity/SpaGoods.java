package com.qisen.mts.spa.model.entity;

import java.util.List;

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
	private Integer checkId;
	private Integer id;
	private Integer eid;
	private String appid;
	private String name;
	private String no;//商品编号
	private String barCode;//商品编码
	private Integer typeId;//商品类型id
	private String unit;//商品单位
	private String spec;//商品规格
	private Integer beginSaledNum;//初始销售数量
	private Integer saleNum;//已售数量
	private Integer num;//数量
	private double buyingPrice;//进货价
	private double costPrice;//成本价
	private double salePrice;//销售价
	private double preferencePrice;//优惠价
	private boolean activityEnable;//是否开启限时抢购
	private double activityPrice;//抢购价
	private String discountStartTime;//活动开始时间
	private String discountEndTime;//活动结束时间
	private boolean shareEnable;//是否开启分享立减
	private double shareReduce;//分享立减金额
	private boolean groupBuyingEnable;//是否启用团购
	private Integer groupBuyingNumber;//团购人数
	private double groupBuyingPrice;//团购价格
	private Integer groupBuyingTime;//团购限时
	private String groupBuyingOverTime;//团购超时处理：0自动成团，1自动退款
	private Integer goodsCompanyId;//商品品牌id
	private String goodsCompany;//商品品牌
	private String remark;//备注
	private String describeText;//商品描述
	private String supplier;//供货商
	private String imgUrl;//商品图片
	private String status;// 状态:0正常,1停售,2删除
	private List<SpaImg> goodsImgs;//商品图片
	private List<SpaImg> goodsDetailImgs;//商品详情图
	
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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
	public Integer getBeginSaledNum() {
		return beginSaledNum;
	}
	public void setBeginSaledNum(Integer beginSaledNum) {
		this.beginSaledNum = beginSaledNum;
	}
	public Integer getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
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
	public double getPreferencePrice() {
		return preferencePrice;
	}
	public void setPreferencePrice(double preferencePrice) {
		this.preferencePrice = preferencePrice;
	}
	public boolean isActivityEnable() {
		return activityEnable;
	}
	public void setActivityEnable(boolean activityEnable) {
		this.activityEnable = activityEnable;
	}
	public double getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}
	public String getDiscountStartTime() {
		return discountStartTime;
	}
	public void setDiscountStartTime(String discountStartTime) {
		this.discountStartTime = discountStartTime;
	}
	public String getDiscountEndTime() {
		return discountEndTime;
	}
	public void setDiscountEndTime(String discountEndTime) {
		this.discountEndTime = discountEndTime;
	}
	public boolean isShareEnable() {
		return shareEnable;
	}
	public void setShareEnable(boolean shareEnable) {
		this.shareEnable = shareEnable;
	}
	public double getShareReduce() {
		return shareReduce;
	}
	public void setShareReduce(double shareReduce) {
		this.shareReduce = shareReduce;
	}
	public boolean isGroupBuyingEnable() {
		return groupBuyingEnable;
	}
	public void setGroupBuyingEnable(boolean groupBuyingEnable) {
		this.groupBuyingEnable = groupBuyingEnable;
	}
	public Integer getGroupBuyingNumber() {
		return groupBuyingNumber;
	}
	public void setGroupBuyingNumber(Integer groupBuyingNumber) {
		this.groupBuyingNumber = groupBuyingNumber;
	}
	public double getGroupBuyingPrice() {
		return groupBuyingPrice;
	}
	public void setGroupBuyingPrice(double groupBuyingPrice) {
		this.groupBuyingPrice = groupBuyingPrice;
	}
	public Integer getGroupBuyingTime() {
		return groupBuyingTime;
	}
	public void setGroupBuyingTime(Integer groupBuyingTime) {
		this.groupBuyingTime = groupBuyingTime;
	}
	public String getGroupBuyingOverTime() {
		return groupBuyingOverTime;
	}
	public void setGroupBuyingOverTime(String groupBuyingOverTime) {
		this.groupBuyingOverTime = groupBuyingOverTime;
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
	public String getDescribeText() {
		return describeText;
	}
	public void setDescribeText(String describeText) {
		this.describeText = describeText;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<SpaImg> getGoodsImgs() {
		return goodsImgs;
	}
	public void setGoodsImgs(List<SpaImg> goodsImgs) {
		this.goodsImgs = goodsImgs;
	}
	public List<SpaImg> getGoodsDetailImgs() {
		return goodsDetailImgs;
	}
	public void setGoodsDetailImgs(List<SpaImg> goodsDetailImgs) {
		this.goodsDetailImgs = goodsDetailImgs;
	}
	
}
