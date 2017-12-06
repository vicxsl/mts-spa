package com.qisen.mts.common.model.constant;

public class CommonDoConsts {

	/**
	 * 基础类型：服务项目
	 */
	public static final int BASE_TYPE_SERVICE = 1;
	
	/**
	 * 基础类型：卖品
	 */
	public static final int BASE_TYPE_PRODUCT = 2;
	
	/**
	 * 基础类型：会员
	 */
	public static final int BASE_TYPE_MEMBER = 3;
	
	/**
	 * 基础类型：日常支出
	 */
	public static final int BASE_TYPE_DAILY_OUT = 4;
	
	/**
	 * 基础类型：日常收入
	 */
	public static final int BASE_TYPE_DAILY_IN = 5;
	
	/**
	 * 基础类型：自定义支付方式 现金类
	 */
	public static final int BASE_TYPE_PAYMENT_CASH = 6;
	
	/**
	 * 基础类型：自定义支付方式 非现类
	 */
	public static final int BASE_TYPE_PAYMENT_NONCASH = 7;
	
	/**
	 * 商品类型：服务项目
	 */
	public static final int ITEM_TYPE_SERVICE = 1;
	
	/**
	 * 商品类型：卖品
	 */
	public static final int ITEM_TYPE_PRODUCT = 2;
	
	/**
	 * 商品类型：会员卡类型
	 */
	public static final int ITEM_TYPE_CARDTYPE = 3;
	
	/**
	 * 库存单据类型：入库单
	 */
	public static final int STO_BILL_BTYPE_IN = 1;
	
	/**
	 * 库存单据类型：出库单
	 */
	public static final int STO_BILL_BTYPE_OUT = 2;
	
	/**
	 * 库存单据类型：调拨单
	 */
	public static final int STO_BILL_BTYPE_ALLOCATE = 3;
	
	/**
	 * 库存单据类型：商品销售
	 */
	public static final int STO_BILL_STYPE_SALE_OUT = 1;
	
	/**
	 * 库存单据类型：采购进货
	 */
	public static final int STO_BILL_STYPE_PURCH_IN = 2;
	
	/**
	 * 库存单据类型：盘盈入库
	 */
	public static final int STO_BILL_STYPE_CHECK_IN = 3;
	
	/**
	 * 库存单据类型：顾客退货
	 */
	public static final int STO_BILL_STYPE_RETURN_IN = 4;
	
	/**
	 * 库存单据类型：获赠入库
	 */
	public static final int STO_BILL_STYPE_PRESENT_IN = 5;
	
	/**
	 * 库存单据类型：其它入库
	 */
	public static final int STO_BILL_STYPE_OTHER_IN = 6;
	
	/**
	 * 库存单据类型：员工领用
	 */
	public static final int STO_BILL_STYPE_SELFUSE_OUT = 7;
	
	/**
	 * 库存单据类型：员工自购
	 */
	public static final int STO_BILL_STYPE_SELFBUY_OUT = 8;
	
	/**
	 * 库存单据类型：盘亏出库
	 */
	public static final int STO_BILL_STYPE_CHECK_OUT = 9;
	
	/**
	 * 库存单据类型：赠送出库
	 */
	public static final int STO_BILL_STYPE_PRESENT_OUT = 10;
	
	/**
	 * 库存单据类型：损坏出库
	 */
	public static final int STO_BILL_STYPE_BROKEN_OUT = 11;
	
	/**
	 * 库存单据类型：退货出库
	 */
	public static final int STO_BILL_STYPE_RETURN_OUT = 12;
	
	/**
	 * 库存单据类型：其它出库
	 */
	public static final int STO_BILL_STYPE_OTHER_OUT = 13;
	
	/**
	 * 库存单据类型：调拨
	 */
	public static final int STO_BILL_STYPE_ALLOCATE = 14;
}
