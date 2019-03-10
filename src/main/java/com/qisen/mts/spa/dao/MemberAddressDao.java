package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.MemberAddress;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.entity.SpaMember;

public interface MemberAddressDao {

	/**
	 * 通过订单查询地址
	 * @param 
	 * @return
	 */
	public MemberAddress queryByOrder(SpaMallOrder order);
	/**
	 * 查询会员最后一次地址
	 * @param 
	 * @return
	 */
	public MemberAddress queryNewAddressByMember(SpaMember member);
	/**
	 * 通过订单删除地址
	 * @param 
	 * @return
	 */
	public void deleteByOrder(SpaMallOrder order);
	
	/**
	 * 新增地址
	 * @param address
	 */
	public void create(MemberAddress address);
	
	/**
	 * 批量更新地址
	 * @param address
	 */
	public void updateList(List<MemberAddress> list);
	
}
