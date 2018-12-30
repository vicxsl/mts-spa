package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;
/**
 * 出入库明细
 * @author Administrator
 *
 */
public class SpaInoutDepotDetail extends BaseEntity{
	
	 private Integer id;

	    private String name;

	    private String no;

	    private String barcode;//产品条码

	    private String unit;

	    private String spec;

	    private Double num;

	    private Double price;

	    private Double totalmoney;

	    private String remark;

	    private String status;

	    private Integer sid;

	    private Integer eid;

	    private static final long serialVersionUID = 1L;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String getBarcode() {
			return barcode;
		}

		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getSpec() {
			return spec;
		}

		public void setSpec(String spec) {
			this.spec = spec;
		}

		public Double getNum() {
			return num;
		}

		public void setNum(Double num) {
			this.num = num;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Double getTotalmoney() {
			return totalmoney;
		}

		public void setTotalmoney(Double totalmoney) {
			this.totalmoney = totalmoney;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Integer getSid() {
			return sid;
		}

		public void setSid(Integer sid) {
			this.sid = sid;
		}

		public Integer getEid() {
			return eid;
		}

		public void setEid(Integer eid) {
			this.eid = eid;
		}
	    
	    

}
