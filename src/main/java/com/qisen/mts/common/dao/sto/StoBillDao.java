package com.qisen.mts.common.dao.sto;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qisen.mts.common.model.entity.sto.Bill;
import com.qisen.mts.common.model.entity.sto.BillWithDetails;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoBillListRequest;
import com.qisen.mts.common.model.response.StoBillListResponse;
@Repository
public interface StoBillDao {

	public Integer create(Bill stoBill);
	
	public void update(Bill stoBill);
	
	/**
	 * 商品销售销单
	 * 
	 * @param eid
	 * @param billId
	 * @return
	 */
	public Integer cancel4Sale(@Param("eid") Integer eid, @Param("billId") Long billId);
	
	/**
	 * 获取单据汇总信息
	 * @param request
	 * @return
	 */
	public StoBillListResponse listCounts(PageRequest<StoBillListRequest> request);

	/**
	 * 根据各种条件查询单据列表
	 * @param request
	 * @return
	 */
	public List<BillWithDetails> stoBillList(PageRequest<StoBillListRequest> request);
	
	/**
	 * 插入与STOBILL关联的STOBILLDETAIL记录
	 * @param body
	 */
	public void createDetails(BillWithDetails body);

		
	/**
	 * 获取一个STOBILL和相关的STOBILLDETAIL记录
	 * @param stoBillId
	 * @return
	 */
	public BillWithDetails loadBillWithDetails(Integer stoBillId);
	
}
