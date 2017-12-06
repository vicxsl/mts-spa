package com.qisen.mts.beauty.dao.busi;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.qisen.mts.beauty.model.entity.busi.Bill;
import com.qisen.mts.beauty.model.entity.busi.EmpFee;

public interface EmpFeeDao {
	
	/**
	 * 新增单据服务/提成员工
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param detailId
	 * @param empFee
	 * @return
	 */
	public Integer create(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("detailId") Long detailId, @Param("empFee") EmpFee empFee);
	
	/**
	 * 批量新增单据服务/提成员工
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param detailId
	 * @param list
	 * @return
	 */
	public Integer createBatch(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("detailId") Long detailId, @Param("list") JSONArray list);

	/**
	 * 撤销单据服务/提成员工
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @return
	 */
	public Integer cancel(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId);
	
	/**
	 * 查询单据服务/提成员工
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @return
	 */
	public List<EmpFee> findByBillId(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId);
	
	/**
	 * 查询单据服务/提成员工（重新计算提成用）
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @return
	 */
	public JSONArray find4Recalc(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId);
	
	/**
	 * 更新重新计算提成
	 * 
	 * @param tbuser
	 * @param eid
	 * @param emps
	 */
	public void update4Recalc(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("emps") JSONArray emps);
	
	/**
	 * 查询单据服务/提成员工
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param bills
	 * @return
	 */
	public List<EmpFee> list4Bill(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("bills") List<Bill> bills);

}
