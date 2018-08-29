package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.service.ItemService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

	public Item findItemByItemId(String itemId) {

		Item item = null;

		Document document = null;
		try {
			document = Jsoup.connect("https://www.sportmaster.ru/catalog/product/search.do?text=" + itemId).get();

			item = new Item();
			item.setItemId(itemId);
			item.setItemLink(document.baseUri());

			item.setItemBrand(extractBrand(document));
			item.setItemName(extractName(document));
			item.setColor(extractColor(document));
			item.setSize(extractSize(document));
			item.setInitPrice(extractInitPrice(document));
			item.setPrice(extractPrice(document));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return item;
	}

	private Integer extractPrice(Document document) {
		Integer price = null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}

	private Integer extractInitPrice(Document document) {
		Integer initPrice = null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return initPrice;
	}

	private String extractSize(Document document) {
		String size = "";

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	private String extractColor(Document document) {
		String color = "";

		try {
			Element element = document.getElementsByAttributeValue("itemprop", "brand").first();
			if (element.hasAttr("content")) {
				return element.attr("content");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return color;
	}

	private String extractName(Document document) {
		String name = "";
		try {
			name = document.getElementsByAttributeValue("data-selenium", "product_name").first().text();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	private String extractBrand(Document document) {

		String brand = "";

		try {
			Element element = document.getElementsByAttributeValue("itemprop", "brand").first();
			if (element.hasAttr("content")) {
				return element.attr("content");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return brand;
	}
}
