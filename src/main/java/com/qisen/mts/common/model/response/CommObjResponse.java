package com.qisen.mts.common.model.response;

public class CommObjResponse<T> extends BaseResponse {
	public CommObjResponse(){
		super();
	}
	public CommObjResponse(T obj){
		super();
		this.body = obj;
	}

	private T body;

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

}
