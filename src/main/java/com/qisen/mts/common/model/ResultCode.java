package com.qisen.mts.common.model;

public enum ResultCode {
	
	SUCCESS(0, "请求成功"),
	FAILED(1, "请求失败"),
	INVALID_TOKEN(2, "TOKEN无效"),
	INVALID_TOKEN_SCOPE(3, "SCOPE无效"),
	INVALID_PARAMETERS(4, "非法参数"),
	NO_RIGHTS(5, "无权限"),
	UPDATE_METADATA(6, "更新metadata");

	private final Integer code;
	private final String msg;

	ResultCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
