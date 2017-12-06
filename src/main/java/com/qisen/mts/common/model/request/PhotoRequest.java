package com.qisen.mts.common.model.request;

public class PhotoRequest {
	private String type;	//商城图片、卖品图片、。。。
	private String name;	//小类名称
	private String newName; //修改后的小类名字
	private String fileName;	//文件名称
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getNewName() {
		return newName;
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
