package com.qisen.mts.spa.model.request;

import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.spa.model.SessionSpa;

public class SpaRequest<T> extends BaseRequest<T> {

	/**
	 * 包含spa賬號對象在内的請求對象
	 */
	private static final long serialVersionUID = 310740954665044391L;

	private SessionSpa sessionSpa;

	public SessionSpa getSessionSpa() {
		return sessionSpa;
	}

	public void setSessionSpa(SessionSpa sessionSpa) {
		this.sessionSpa = sessionSpa;
	}

}
