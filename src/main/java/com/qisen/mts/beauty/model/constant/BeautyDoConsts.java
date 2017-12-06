package com.qisen.mts.beauty.model.constant;

public class BeautyDoConsts {

	/**
	 * 单据类型：项目/消费
	 */
	public static final int BILL_BTYPE_SERVICE = 1;

	/**
	 * 单据类型：商品
	 */
	public static final int BILL_BTYPE_PRODUCT = 2;

	/**
	 * 单据类型：开卡
	 */
	public static final int BILL_BTYPE_NEWCARD = 3;

	/**
	 * 单据类型：充值
	 */
	public static final int BILL_BTYPE_CHARGE = 4;

	/**
	 * 单据类型：项目套餐
	 */
	public static final int BILL_BTYPE_PACK = 5;

	/**
	 * 消费类型：散客
	 */
	public static final int BILL_CTYPE_FIT = 1;

	/**
	 * 消费类型：用卡
	 */
	public static final int BILL_CTYPE_CARD = 2;

	/**
	 * 消费类型：项目套餐
	 */
	public static final int BILL_CTYPE_PACK = 3;

	/**
	 * 单据流水：总计
	 */
	public static final String PAY_DEPCODE_ALL = "ALL";

	/**
	 * 单据流水：跨店
	 */
	public static final String PAY_DEPCODE_CROSS = "CROSS";

	/**
	 * 性别：男
	 */
	public static final String SEX_MALE = "M";

	/**
	 * 性别：女
	 */
	public static final String SEX_FEMALE = "F";

	/**
	 * 指定类型：非指定
	 */
	public static final int POINT_TYPE_UNPOINT = 0;

	/**
	 * 指定类型：指定
	 */
	public static final int POINT_TYPE_POINT = 1;
	
	/**
	 * 卡金变动类型：项目消费
	 */
	public static final int CARDRECORD_TYPE_SERVICE = 1;
	
	/**
	 * 卡金变动类型：卖品消费
	 */
	public static final int CARDRECORD_TYPE_PRODUCT = 2;
	
	/**
	 * 卡金变动类型：开卡
	 */
	public static final int CARDRECORD_TYPE_NEWCARD = 3;
	
	/**
	 * 卡金变动类型：充值
	 */
	public static final int CARDRECORD_TYPE_CHARGE = 4;

	/**
	 * 卡金变动类型：购买套餐
	 */
	public static final int CARDRECORD_TYPE_PACK = 5;
	
	/**
	 * 卡金变动类型：恢复会员
	 */
	public static final int CARDRECORD_TYPE_RECOVER = 6;
	
	/**
	 * 卡金变动类型：删除会员卡
	 */
	public static final int CARDRECORD_TYPE_DEL = 7;
	
	/**
	 * 卡金变动类型：导入会员
	 */
	public static final int CARDRECORD_TYPE_IMPROT = 8;
	
	/**
	 * 参数配置：提成规则类型 指定、非指定
	 */
	public static final String GAIN_RULE_TYPE_PU = "PU";
	
	/**
	 * 参数配置：提成规则类型 小项现金、小项非现、大项现金、大项非现
	 */
	public static final String GAIN_RULE_TYPE_FP = "FP";
	
	/**
	 * 参数配置：提成规则类型 小项指定、小项非指定、大项指定、大项非指定
	 */
	public static final String GAIN_RULE_TYPE_FPU = "FPU";
	
	/**
	 * 参数配置：提成规则类型 指定现金、指定划卡、不指定现金、不指定划卡
	 */
	public static final String GAIN_RULE_TYPE_PPU = "PPU";
	
	/**
	 * 参数配置：提成规则类型 单个项目提成
	 */
	public static final String GAIN_RULE_TYPE_SINGLE = "SINGLE";

}
