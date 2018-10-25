package com.sportmaster.model;

public class Shop {
	private String shopID;
	private String shopMetro;
	private String shopAddress;
	public String getShopID() {
		return shopID;
	}
	
	
	
	
	public Shop() {
		super();
		this.shopID = "";
		this.shopMetro = "";
		this.shopAddress = "";
	}




	public Shop(String shopID, String shopMetro, String shopAddress) {
		super();
		this.shopID = shopID;
		this.shopMetro = shopMetro;
		this.shopAddress = shopAddress;
	}




	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	public String getShopMetro() {
		return shopMetro;
	}
	public void setShopMetro(String shopMetro) {
		this.shopMetro = shopMetro;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	
}
