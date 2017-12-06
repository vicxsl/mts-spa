package com.qisen.mts.beauty.dao.busi;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.busi.Bill;

public interface BillDao {

	/**
	 * 新增单据
	 * 
	 * @param tbuser
	 * @param eid
	 * @param bill
	 * @return
	 */
	public Integer create(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("bill") Bill bill);

	/**
	 * 修改单据状态
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param status
	 * @return
	 */
	public Integer status(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("id") Long billId, @Param("status") Integer status);
	
	/**
	 * 查询单据
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param id
	 * @return
	 */
	public Bill find(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("id") Long id);
	
	/**
	 * 查询会员最后消费日期
	 * 
	 * @param tbuser
	 * @param eid
	 * @param memId
	 * @return
	 */
	public Date findLastBday4Member(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("memId") Integer memId);
	
	/**
	 * 查询会员最后服务员工
	 * 
	 * @param tbuser
	 * @param eid
	 * @param memId
	 * @return
	 */
	public String findLastEmp4Member(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("memId") Integer memId);
	
	/**
	 * 查询单据总数
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param body.billNo //单号
	 * @param body.startDate //查询开始时间
	 * @param body.endDate //查询结束时间
	 * @param body.depCode //部门 ALL
	 * @param body.type //数组 单据类型 1消费2开卡3充值4销售套餐
	 * @param body.crossFlag //跨店消费 置空所有0非跨店1跨店
	 * @param body.optId //操作人ID
	 * @param body.status //数组 状态 0撤销1已对单2未对单
	 * @return
	 */
	public JSONArray summary(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);
	
	/**
	 * 查询单据
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param body.billNo //单号
	 * @param body.startDate //查询开始时间
	 * @param body.endDate //查询结束时间
	 * @param body.depCode //部门 ALL
	 * @param body.type //数组 单据类型 1消费2开卡3充值4销售套餐
	 * @param body.crossFlag //跨店消费 置空所有0非跨店1跨店
	 * @param body.optId //操作人ID
	 * @param body.status //数组 状态 0撤销1已对单2未对单
	 * @param startIndex 分页开始记录
	 * @param endIndex 分页结束记录 为空时不分页
	 * @return
	 */
	public List<Bill> list(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body, @Param("startIndex") Integer startIndex, @Param("endIndex") Integer endIndex);
	
	/**
	 * 检查单号是否重复
	 * 
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param body
	 * @return
	 */
	public Integer check(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);
	
	/**
	 * 
	 * @param tbuser
	 * @param eid
	 * @param id 会员id
	 * @return
	 */
	public Integer countListByMemId(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("id") Integer id);// 根据会员id查询单据个数

	/**
	 * 
	 * @param tbuser
	 * @param eid
	 * @param id 会员id
	 * @param startIndex 起始记录
	 * @param endIndex 结束记录
	 * @return
	 */
	public JSONArray findListByMemId(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("id") Integer id,@Param("startIndex") Integer startIndex,@Param("endIndex") Integer endIndex);// 根据会员id查询结果集

}
