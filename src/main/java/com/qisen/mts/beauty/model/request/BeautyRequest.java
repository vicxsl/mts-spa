package com.qisen.mts.beauty.model.request;

import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.common.model.request.BaseRequest;

public class BeautyRequest<T> extends BaseRequest<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -116737125994152304L;
	
	public SessionUser getSessionUser() {
		return (SessionUser) this.getSessionAccount();
	}

	public void setSessionUser(SessionUser sessionUser) {
		this.setSessionAccount(sessionUser);
	}

}
