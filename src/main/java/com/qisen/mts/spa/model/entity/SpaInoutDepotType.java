package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;
/**
 * 出入库类型
 * @author Administrator
 *
 */
public class SpaInoutDepotType extends BaseEntity{
	
	 	private Integer id;

	    private String name;

	    private String no;

	    private String type;

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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
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
