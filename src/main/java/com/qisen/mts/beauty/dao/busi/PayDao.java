package com.qisen.mts.beauty.dao.busi;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.qisen.mts.beauty.model.entity.busi.Bill;
import com.qisen.mts.beauty.model.entity.busi.Pay;

@Repository
public interface PayDao {
	
	/**
	 * 新增单据支付流水
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param pay
	 * @return
	 */
	public Integer create(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("pay") Pay pay);
	
	/**
	 * 新增单据支付流水
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param list
	 * @return
	 */
	public Integer createBatch(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("list") JSONArray list);

	/**
	 * 撤销单据支付流水
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @return
	 */
	public Integer cancel(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId);
	
	/**
	 * 查询单据支付流水
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @return
	 */
	public List<Pay> findByBillId(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId);
	
	/**
	 * 查询单据支付流水
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param depCode
	 * @return
	 */
	public Pay findByDepCode(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("depCode") String depCode);
	
	/**
	 * 查询单据支付流水
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param bills
	 * @return
	 */
	public List<Pay> list4Bill(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("bills") List<Bill> bills);

}
