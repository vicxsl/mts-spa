package com.qisen.mts.common.model.response;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.ConfigConsts;

public class BaseResponse {

	private Integer result;
	private String code;
	private String msg;
	private Long lastUpdateTs4e;
	private Long lastUpdateTs4s;
	
	public BaseResponse() {
		this.result = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getMsg();
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(ResultCode resultCode) {
		this.result = resultCode.getCode();
		if (StringUtils.isBlank(this.code))
			this.msg = resultCode.getMsg();
	}

	public String getCode() {
		return code;
	}

	public void setCode(MsgCode msgCode) {
		this.code = msgCode.getCode();
		this.result = ResultCode.FAILED.getCode();
		this.msg = ResultCode.FAILED.getMsg();
		if (StringUtils.isNotBlank(msgCode.getMsg()))
			this.msg = msgCode.getMsg();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Long getLastUpdateTs4e() {
		return lastUpdateTs4e;
	}

	public void setLastUpdateTs4e(Long lastUpdateTs4e) {
		this.lastUpdateTs4e = lastUpdateTs4e;
	}

	public Long getLastUpdateTs4s() {
		return lastUpdateTs4s;
	}

	public void setLastUpdateTs4s(Long lastUpdateTs4s) {
		this.lastUpdateTs4s = lastUpdateTs4s;
	}

	public void notify4Metadata(Integer eid, String appid) {
		this.lastUpdateTs4e = eid != null ? eid.longValue() : null;
		this.lastUpdateTs4s = appid != null ? eid.longValue() : null;
		this.setResult(ResultCode.UPDATE_METADATA);
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, ConfigConsts.JSON_SERIALIZER_FEATURE);
	}

}
