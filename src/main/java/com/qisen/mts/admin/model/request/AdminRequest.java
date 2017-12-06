package com.qisen.mts.admin.model.request;

import com.qisen.mts.admin.model.SessionAdmin;
import com.qisen.mts.common.model.request.BaseRequest;

public class AdminRequest<T> extends BaseRequest<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 310740954665044391L;
	
	private SessionAdmin sessionAdmin;

	public SessionAdmin getSessionAdmin() {
		return sessionAdmin;
	}

	public void setSessionAdmin(SessionAdmin sessionAdmin) {
		this.sessionAdmin = sessionAdmin;
	}
	
}
