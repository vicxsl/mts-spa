package com.qisen.mts.common.dao.mem;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.common.model.entity.mem.CardRecord;

public interface CardRecordDao {

	/**
	 * 创建
	 * 
	 * @param tbuser
	 * @param eid
	 * @param memCard
	 * /**
	 * @param cardRecord
	 * cardRecord.sid//门店id
	 * cardRecord.memid//会员id
	 * cardRecord.memsid//会员所在店id
	 * cardRecord.memcardid//会员卡id
	 * cardRecord.cardfee//卡余额
	 * cardRecord.paycardfee//消耗卡金
	 * cardRecord.presentfee//卡赠送金
	 * cardRecord.paypresentfee//消耗赠送金
	 * cardRecord.cardtypeno//卡编号
	 * cardRecord.cardno//卡号
	 * cardRecord.cardname//卡名称
	 * cardRecord.type//单据类型
	 * cardRecord.cardno//卡号
	 *
	 * @return
	 */
	public Integer create(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("cardRecord") CardRecord cardRecord);

	/**
	 * 销单回滚
	 * 
	 * @param tbuser
	 * @param eid
	 * @param billId
	 * @return
	 */
	public Integer rollback4Bill(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("billId") Long billId);
	
	/**
	 * 根据单据ID查询
	 * 
	 * @param tbuser
	 * @param eid
	 * @param billId
	 * @return
	 */
	public CardRecord findByBillId(@Param("tbuser") String tbuser, @Param("eid") Integer eid, @Param("billId") Long billId);
}
