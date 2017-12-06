package com.qisen.mts.myk.model.request;

import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.myk.model.SessionMember;

public class MykRequest<T> extends BaseRequest<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -116737125994152304L;
	
	public SessionMember getSessionMember() {
		return (SessionMember) this.getSessionAccount();
	}

	public void setSessionMember(SessionMember sessionMember) {
		this.setSessionAccount(sessionMember);
	}
	
}
