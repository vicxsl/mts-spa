package com.qisen.mts.common.dao.sto;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.qisen.mts.common.model.entity.sto.BillDetail;
@Repository
public interface StoBillDetailDao {

	public void create(BillDetail stoBillDetail);
	
	public Integer update(BillDetail stoBillDetail);
	
	/**
	 * 销售
	 * 
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param list
	 * @return
	 */
	public Integer createBatch4Sale(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Integer billId, @Param("list") JSONArray list);
	
	/**
	 * 销售销单
	 * 
	 * @param eid
	 * @param billId
	 * @return
	 */
	public Integer cancel4Sale(@Param("eid") Integer eid, @Param("billId") Long billId);
	
	/**
	 * 查询单据明细
	 * 
	 * @param eid
	 * @param billId
	 * @return
	 */
	public List<BillDetail> findByBillId(@Param("eid") Integer eid, @Param("billId") Long billId);
}
