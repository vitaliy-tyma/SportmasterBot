package com.sportmaster.model;

public class Good {
	private String goodID;
	private String articleID;
	private String name;
	private String price;
	private String color;
	private String description;
	private String url;
	
	
	
	public Good() {
		super();
	}

	public Good(String goodID, String articleID, String name,
			String price, String color, String description,
			String url) {
		super();
		this.goodID = goodID;
		this.articleID = articleID;
		this.name = name;
		this.price = price;
		this.color = color;
		this.description = description;
		this.url = url;
	}

	
	@Override
	public String toString() {
		return "Good [goodID=" + goodID + ", articleID=" + articleID + ", name=" + name + ", price=" + price
				+ ", color=" + color + ", description=" + description + ", url=" + url + "]";
	}

	public String getGoodID() {
		return goodID;
	}

	public void setGoodID(String goodID) {
		this.goodID = goodID;
	}

	public String getArticleID() {
		return articleID;
	}

	public void setArticleID(String articleID) {
		this.articleID = articleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
