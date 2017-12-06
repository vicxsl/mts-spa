package com.qisen.mts.common.dao.mem;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.mem.Card;

public interface CardDao {

	public Integer create(Card memCard);

	/**
	 * 充值
	 * 
	 * @param eid
	 * @param id
	 * @param cardFee
	 * @param presentFee
	 * @return
	 */
	public Integer update4Charge(@Param("eid") Integer eid, @Param("id") Integer id, @Param("cardFee") Double cardFee, @Param("presentFee") Double presentFee);
	
	/**
	 * 支付
	 * 
	 * @param eid
	 * @param id
	 * @param cardFee
	 * @param presentFee
	 * @return
	 */
	public Integer update4Pay(@Param("eid") Integer eid, @Param("id") Integer id, @Param("cardFee") Double cardFee, @Param("presentFee") Double presentFee);
	
	/**
	 * 充值销单回滚
	 * 
	 * @param eid
	 * @param id
	 * @param cardFee
	 * @param presentFee
	 * @return
	 */
	public Integer rollback4Charge(@Param("eid") Integer eid, @Param("id") Integer id, @Param("cardFee") Double cardFee, @Param("presentFee") Double presentFee);
	
	/**
	 * 支付销单回滚
	 * 
	 * @param eid
	 * @param id
	 * @param cardFee
	 * @param presentFee
	 * @return
	 */
	public Integer rollback4Pay(@Param("eid") Integer eid, @Param("id") Integer id, @Param("cardFee") Double cardFee, @Param("presentFee") Double presentFee);
	
	/**
	 * 更新消费信息
	 * 
	 * @param eid
	 * @param id
	 * @param fee
	 * @param rollback
	 * @return
	 */
	public Integer updateConsumeInfo(@Param("eid") Integer eid, @Param("id") Integer id, @Param("fee") Double fee, @Param("rollback") int rollback);
	
	/**
	 * 检查卡号是否重复
	 * 
	 * @param eid
	 * @param sid
	 * @param body.cardTypeNo
	 * @param body.cardNo
	 * @return
	 */
	public Integer checkCardNo(@Param("eid") Integer eid,@Param("sid") Integer sid, @Param("body") JSONObject body);
	
	/**
	 * 根据会员id查询所有的会员卡
	 * 
	 * @param sid
	 * @param memid
	 * @return
	 */
	public List<Card> findByMemId(@Param("eid") Integer eid, @Param("memId") Integer memId);
	
	/**
	 * 根据会员id查询所有的会员卡
	 * 
	 * @param eid
	 * @param id
	 * @return
	 */
	public Card find(@Param("eid") Integer eid, @Param("id") Integer id);
	
	/**
	 * 查询门店总卡金
	 * @param sid
	 * @return
	 */
	public Card getSumCardFeeBySid(@Param("sid") Integer sid);
	
	/**
	 * 查询一张卡
	 * @param eid
	 * @param sid
	 * @param id
	 * @return
	 */
	public Card getCardById(@Param("eid") Integer eid,@Param("sid") Integer sid,@Param("id") Integer id);
	
	/**
	 * 卡的变动流水总个数
	 * @param eid
	 * @param memsid 会员所在店
	 * @param id
	 * @return
	 */
	public Integer countCardRecord(@Param("eid") Integer eid,@Param("memsid") Integer memsid,@Param("id") Integer id);
	
	
	
	public void updatestatus(@Param("eid") Integer eid,@Param("status") Integer status,@Param("id") Integer id);
	
	public int deleteStatus(@Param("eid") Integer eid,@Param("sid") Integer sid,@Param("body") JSONObject body);
	
	/**
	 * 卡的变动流水
	 * @param eid
	 * @param memsid 会员所在店
	 * @param id
	 * @return
	 */
	public JSONArray cardRecord(@Param("eid") Integer eid,@Param("memsid") Integer memsid,@Param("id") Integer id,@Param("startIndex") Integer startIndex,@Param("endIndex") Integer endIndex);
	
	/**
	 * 导入卡资料,批量导入会员卡membercard
	 * @param objList
	 * @return
	 */
	public Integer importCard(@Param("list")List<JSONObject> objList);
	
	/**
	 * 根据卡类型编号，手机号码，卡号检测卡是否存在
	 * @param eid
	 * @param sid
	 * @param cardNo
	 * @param mobile
	 * @param cardTypeNo
	 * @return
	 */
	public List<Card> checkCardid(@Param("eid") Integer eid,@Param("sid") Integer sid,@Param("cardNo") String cardNo,@Param("mobile") String mobile,@Param("cardTypeNo") String cardTypeNo);
	
}
