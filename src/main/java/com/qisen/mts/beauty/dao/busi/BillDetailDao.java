package com.qisen.mts.beauty.dao.busi;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.qisen.mts.beauty.model.entity.busi.Bill;
import com.qisen.mts.beauty.model.entity.busi.BillDetail;

@Repository
public interface BillDetailDao {
	
	/**
	 * 新增单据明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param detail
	 * @return
	 */
	public Integer create(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("detail") BillDetail detail);
	
	/**
	 * 批量新增单据明细
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
	 * 撤销单据明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @return
	 */
	public Integer cancel(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId);
	
	/**
	 * 查询单据明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @return
	 */
	public List<BillDetail> findByBillId(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId);
	
	/**
	 * 查询单据明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param ctype
	 * @return
	 */
	public List<BillDetail> findByCtype(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("ctype") Integer ctype);
	
	/**
	 * 查询单据明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param btype
	 * @return
	 */
	public List<BillDetail> findByBtype(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("btype") Integer btype);
	
	/**
	 * 查询单据明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param bills
	 * @return
	 */
	public List<BillDetail> list4Bill(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("bills") List<Bill> bills);

}
