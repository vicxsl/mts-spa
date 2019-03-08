package com.qisen.mts.spa.model.entity;

import java.util.List;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class MetaData extends BaseEntity {

	/**
	 * spa系统缓存数据
	 */
	private static final long serialVersionUID = -5893656151121644946L;

	private SpaShop shop;
	private SpaMember member;
	private List<SpaGoodsShopCar> shopCarList;//购物车
	private List<SpaGoodsType> goodsTypes;//商品类型集合
	private String photoPath;//图片地址
	private MemberAddress memberAddress;//默认收货地址
	
	public MemberAddress getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(MemberAddress memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public SpaShop getShop() {
		return shop;
	}
	public void setShop(SpaShop shop) {
		this.shop = shop;
	}
	public SpaMember getMember() {
		return member;
	}
	public void setMember(SpaMember member) {
		this.member = member;
	}
	public List<SpaGoodsShopCar> getShopCarList() {
		return shopCarList;
	}
	public void setShopCarList(List<SpaGoodsShopCar> shopCarList) {
		this.shopCarList = shopCarList;
	}
	public List<SpaGoodsType> getGoodsTypes() {
		return goodsTypes;
	}
	public void setGoodsTypes(List<SpaGoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}
	
	
	
}
