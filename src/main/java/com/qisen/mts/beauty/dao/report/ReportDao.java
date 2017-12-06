package com.qisen.mts.beauty.dao.report;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.mem.CardRecord;


public interface ReportDao {

	/**
	 * 项目分类业绩汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.date 查询日期
	 * @return
	 */
	public JSONArray servicePerfSummary(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 业绩汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.date 查询日期
	 * @return
	 */
	public JSONArray sumPerfSummary(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 客数汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.date 查询日期 单日和区间二选一
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @return
	 */
	public JSONArray customerSummary(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 收入汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.date 查询日期 单日和区间二选一
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @return
	 */
	public JSONArray incomeSummary(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 项目/卖品/开充卡/项目套餐汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @return
	 */
	public JSONArray itemSummary(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 员工业绩汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @param body.gtype 级别类型
	 * @param body.payTypes 支付方式
	 * @return
	 */
	public JSONArray empFeeDetails(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 项目分类业绩汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @return
	 */
	public JSONArray itemTypeFeeDetails(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 项目业绩明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @param body.typeNo 分类编号
	 * @return
	 */
	public JSONArray itemFeeDetails(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 员工提成汇总
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @param body.gtype 级别类型
	 * @return
	 */
	public JSONArray empGainSum(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 员工提成明细
	 * 
	 * @param tbuser
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @param body.startDate 查询开始时间
	 * @param body.endDate 查询结束时间
	 * @param body.empId 员工ID
	 * @param body.payTypes 支付方式
	 * @return
	 */
	public JSONArray empServiceGainDetails(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 卡金变动流水
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public JSONArray cardFeeWater(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	/**
	 * 卡金变动流水  (选择月份的最后一天到当前日期的流水)
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param endDate
	 * @return
	 */
	public CardRecord cardFeeMap(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("endDate") Date endDate);
	
	/**
	 * 现金流水表  
	 * @param tbuser 
	 * @param 
	 * @param eid
	 * @param sid
	 * @param noList 现金支付类型编号数组
	 * @param body 其他约束条件
	 * @return
	 */
	public JSONArray sumCashReport(@Param("tbuser")String tbuser, @Param("eid")Integer eid, @Param("sid")Integer sid,@Param("noList") List<String> noList,@Param("body") JSONObject body);

	/**
	 * 现金流水表 显示日期集合
	 * @param jsonObject 
	 * @param integer2 
	 * @param integer 
	 * @param tbuser 
	 * @param 
	 * @param eid
	 * @param sid
	 * @param body 其他约束条件
	 * @return
	 */
	public JSONArray sumBday(@Param("tbuser")String tbuser,@Param("eid")Integer eid,@Param("sid") Integer sid,@Param("body") JSONObject body);
}
