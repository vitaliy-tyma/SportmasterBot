package com.sportmaster.model;

public class Order {
	private String good;
	private String order_id;
	private String shop_id;
	private String user;
	
	
	public Order(String good, String order_id, String shop_id, String user) {
		super();
		this.good = good;
		this.order_id = order_id;
		this.shop_id = shop_id;
		this.user = user;
	}
	
	
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
