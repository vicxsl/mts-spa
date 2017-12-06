package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.RuleItemCardDisc;

public interface RuleItemCardDiscDao {

	/**
	 * 新增卡级折扣会员价
	 * 
	 * @param itemCardDisc
	 * @return
	 */
	public Integer create(RuleItemCardDisc itemCardDisc);

	/**
	 * 删除卡级折扣会员价
	 * 
	 * @param eid
	 * @param sid
	 * @param body.ids 待删除的记录ID
	 */
	public Integer delete(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 更新卡级折扣会员价
	 * 
	 * @param itemPrice id为0或空时根据项目编号和类型更新记录
	 */
	public Integer update(RuleItemCardDisc itemCardDisc);

	/**
	 * 检查是否设置卡级折扣会员价
	 * 
	 * @param eid
	 * @param sid
	 * @param itemNo
	 * @param cardTypeNo
	 * @return
	 */
	public Integer check(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("itemNo") String itemNo, @Param("cardTypeNo") String cardTypeNo);

	/**
	 * 查询项目卡级折扣会员价
	 * 
	 * @param eid
	 * @param sid 为空时查询企业数据
	 * @param itemNo
	 * @return
	 */
	public List<RuleItemCardDisc> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("itemNo") String itemNo);
}
