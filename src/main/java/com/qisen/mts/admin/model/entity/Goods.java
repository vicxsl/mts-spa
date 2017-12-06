package com.qisen.mts.admin.model.entity;

import java.io.Serializable;

/**
 * 商品 table_name: com_products
 * 
 */
public class Goods implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private int no;//产品编号
	private String name;//产品名称
	private double price;//价格
	private double cost;//成本
	private int classNo;//类型编号
	private String className;//类型
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getClassNo() {
		return classNo;
	}
	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
}
