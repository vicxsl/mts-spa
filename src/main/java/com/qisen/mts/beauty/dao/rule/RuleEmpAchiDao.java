package com.qisen.mts.beauty.dao.rule;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;

public interface RuleEmpAchiDao {
	
	/**
	 * 创建规则
	 * 
	 * @param ruleItemEmpfee
	 * @return
	 */
	public Integer create(RuleEmpAchi ruleItemEmpfee);
	
	/**
	 * 修改规则
	 * 
	 * @param ruleItemEmpfee
	 * @return
	 */
	public Integer update(RuleEmpAchi ruleItemEmpfee);

	/**
	 * 删除规则
	 * 
	 * @param eid
	 * @param body.ids ID数组
	 * @return
	 */
	public Integer delete(@Param("eid") Integer eid, @Param("body") JSONObject body);

	/**
	 * 修改状态
	 * 
	 * @param eid
	 * @param body.status 0不启用1启用
	 * @param body.ids ID数组
	 * @return
	 */
	public Integer status(@Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 修改排序
	 * 
	 * @param eid
	 * @param body.ids 排序后的ID数组
	 * @return
	 */
	public Integer update4Sort(@Param("eid") Integer eid, @Param("body") JSONObject body);

	/**
	 * 查询员工业绩/提成规则
	 * 
	 * @param eid
	 * @param sid 0或空为企业全部数据，反之查询门店自定义数据
	 * @param body.type 0或空全部1项目业绩规则2项目提成规则3卖品提成规则4开卡提成规则5续充提成规则6套餐销售提成规则7总劳动业绩提成规则
	 * @param body.status 数组0不启用1启用

	 * @return
	 */
	public List<RuleEmpAchi> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);
	
	/**
	 * 从总部复制提成规则
	 * @param eid
	 * @param sid
	 * @param body.type 或空全部1项目业绩规则2项目提成规则3卖品提成规则4开卡提成规则5续充提成规则6套餐销售提成规则7总劳动业绩提成规则
	 */
	public void copyRule(@Param(value = "eid") Integer eid,@Param(value = "sid") Integer sid,@Param(value = "type") Integer type);
	
	/**
	 * 清楚门店规则
	 * @param eid
	 * @param sid
	 * @param body.type 或空全部1项目业绩规则2项目提成规则3卖品提成规则4开卡提成规则5续充提成规则6套餐销售提成规则7总劳动业绩提成规则
	 */
	public void delRuleBysid(@Param(value = "eid") Integer eid,@Param(value = "sid") Integer sid,@Param(value = "type") Integer type);
	

}
