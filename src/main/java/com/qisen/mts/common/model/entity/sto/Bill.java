package com.qisen.mts.common.model.entity.sto;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Bill extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5184872008989521681L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String no;
	private Date day;
	private String handler;
	private Integer empId;
	private Integer outSid;
	private Integer inSid;
	private Integer num;
	private Double money;
	/**
	 * 单据大类 :1入库单,2出库单,3调拨单
	 */
	private Integer bType;
	/**
	 * 单据小类:1商品销售,2采购进货,3盘盈入库,4顾客退货,5获赠入库,6其他入库,7员工领用,8员工自购,9盘亏出库,10赠送出库,11损坏出库,12退货出库,13其他出库,14调拨
	 */
	private Integer sType;
	private String remark;
	private Integer optId;
	private String optName;
	private Integer examId;
	private String examName;
	private Integer status;
	private Long billId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getOutSid() {
		return outSid;
	}

	public void setOutSid(Integer outSid) {
		this.outSid = outSid;
	}

	public Integer getInSid() {
		return inSid;
	}

	public void setInSid(Integer inSid) {
		this.inSid = inSid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getbType() {
		return bType;
	}

	public void setbType(Integer bType) {
		this.bType = bType;
	}

	public Integer getsType() {
		return sType;
	}

	public void setsType(Integer sType) {
		this.sType = sType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	/**
	 * 1未审核 2已审核 0撤销
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

}
