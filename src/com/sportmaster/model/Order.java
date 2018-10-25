package com.sportmaster.model;

public class Order {
	private String goodID;
	private Good good;
	private String orderID;
	private String shopID;
	private String user;

	public Order(String goodID, Good good, String orderID, String shopID, String user) {
		super();
		this.goodID = goodID;
		this.good = good;
		this.orderID = orderID;
		this.shopID = shopID;
		this.user = user;
	}

	public Order() {
		super();
		this.goodID = "";
		this.good = null;//How to initialize?
		this.orderID = "";
		this.shopID = "";
		this.user = "";
	}

	@Override
	public String toString() {
		return "Order [goodID=" + goodID + ", good=" + good.toString() + ", orderID=" + orderID + ", shopID=" + shopID
				+ ", user=" + user + "]";
	}

	public String getGoodID() {
		return goodID;
	}

	public void setGoodID(String goodID) {
		this.goodID = goodID;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
