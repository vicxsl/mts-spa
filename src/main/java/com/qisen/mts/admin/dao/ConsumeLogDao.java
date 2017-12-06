package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.ConsumeLog;

public interface ConsumeLogDao {
	/**
	 * 
	 * @param eid 
	 * @param sid 
	 * @param body.orgNo//所属片区
	 * @param body.byEmpId//经手人
	 * @param body.soEmpId//收款人
	 * @param body.sName//商户名称
	 * @param body.beginConsumeTime//开始消费日期
	 * @param body.endConsumeTime//结束消费日期
	 * @param body.beginPayTime//开始收款日期
	 * @param body.endPayTime//结束收款日期
	 * @param body.classNo//产品类型
	 * @param body.productNo//产品
	 * @param body.cashNo//收款方式
	 * @param body.clientType//客户类型0--新开1--续费
	 * @param body.auditFlag//是否审核0--未审核1--已审核
	 * @param body.payFlag//是否付款0--未付款1--已付款2--赠送
	 * @param body.id//记录id
	 * @return
	 */
	public Integer count(@Param("body") JSONObject body,@Param("eid") Integer eid,@Param("sid") Integer sid);// 查询总个数

	/**
	 * 
	 * @param body.orgNo//所属片区
	 * @param body.byEmpId//经手人
	 * @param body.soEmpId//收款人
	 * @param body.sName//商户名称
	 * @param body.beginConsumeTime//开始消费日期
	 * @param body.endConsumeTime//结束消费日期
	 * @param body.beginPayTime//开始收款日期
	 * @param body.endPayTime//结束收款日期
	 * @param body.classNo//产品类型
	 * @param body.productNo//产品
	 * @param body.cashNo//收款方式
	 * @param body.clientType//客户类型0--新开1--续费
	 * @param body.auditFlag//是否审核0--未审核1--已审核
	 * @param body.payFlag//是否付款0--未付款1--已付款2--赠送
	 * @param body.id//记录id
	 * @param startIndex
	 * @param endIndex
	 * @param eid 
	 * @param sid 
	 * @return
	 */
	public List<ConsumeLog> list(@Param("body") JSONObject body, @Param("startIndex") Integer startIndex,
			@Param("endIndex") Integer endIndex,@Param("eid") Integer eid,@Param("sid") Integer sid);// 查询结果集

	/**
	 * 
	 * @param body.payflag//是否付款0--未付款1--已付款2--赠送
	 * @param body.remark//备注
	 * @param body.byEmpId//经手人
	 * @param body.soEmpId//收款人
	 * @param body.orgNo//所属片区
	 * @param body.clientType//客户类型0--新开1--续费
	 * @param body.payTime//收款日期
	 * @param body.id
	 * 
	 */
	public void update(ConsumeLog consumeLog);// 修改消费记录
	
	public void updateAuditFlag(@Param("auditFlag") String auditFlag,@Param("id") Integer id);// 修改审核状态
	
	/**
	 * 新增消费记录
	 * @param consumeLog
	 */
	public int create(ConsumeLog consumeLog);
}
