package com.qisen.mts.gateway;

import com.qisen.mts.common.model.MsgCode;

public class ExceptionWithCode extends Exception {

	private static final long serialVersionUID = 249431485228610731L;

	private MsgCode msgCode;

	public ExceptionWithCode(MsgCode msgCode) {
		super(msgCode.toString());
		this.setMsgCode(msgCode);
	}

	public MsgCode getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(MsgCode msgCode) {
		this.msgCode = msgCode;
	}

}
