package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class MetaData extends BaseEntity {

	/**
	 * spa系统账号
	 */
	private static final long serialVersionUID = -5893656151121644946L;

	private SpaShop shop;
	private SpaMember member;
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
	
	
}
