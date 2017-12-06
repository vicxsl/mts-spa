package com.qisen.mts.common.model.entity.inte;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;
/**
 * 员工休假类
 * @author wangwl
 *
 */
public class Vacation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8535738352817563162L;
	private Integer id;
	private Integer eid;
	private Integer sid;
	private Integer  empId;
	private String  empName;
	private Date  vacationDate;
	
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
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
	public Date getVacationDate() {
		return vacationDate;
	}
	public void setVacationDate(Date vacationDate) {
		this.vacationDate = vacationDate;
	}
}
