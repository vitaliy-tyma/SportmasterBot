package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.service.ItemService;
import com.pioneersoft.sportmasterbot.util.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

	public Item findItemByItemId(String itemId) {

		Item item = null;

		Document document;
		try {
			document = Jsoup.connect("https://www.sportmaster.ru/catalog/product/search.do?text=" + itemId).get();

			if ( !document.getElementsByClass("sm-goods_main").isEmpty())
			{
				item = new Item();
				item.setItemId(itemId);
				item.setItemLink(document.baseUri());

				item.setItemBrand(extractBrand(document));
				item.setItemName(extractName(document));
				item.setColor(extractColor(document));
				item.setSize(extractSize(document, itemId));
				item.setInitPrice(extractInitPrice(document));
				item.setPrice(extractPrice(document));
			}
		} catch (IOException e) {
			LogManager.writeLogText(e.getMessage());
		}

		return item;
	}

	private Integer extractPrice(Document document) {
		try {
			Element element = document.getElementsByAttributeValueContaining("class", "sm-goods_main_details_prices_actual-price").first();
			if (element != null) {
				return Integer.parseInt(element.text().replaceAll("\\D", ""));
			}
		} catch (Exception e) {
			LogManager.writeLogText(e.getMessage());
		}
		return null;
	}

	private Integer extractInitPrice(Document document) {
		try {
			Element element = document.getElementsByAttributeValueContaining("class", "sm-goods_main_details_prices_old-price").first();
			if (element != null) {
				return Integer.parseInt(element.text().replaceAll("\\D", ""));
			}
		} catch (Exception e) {
			LogManager.writeLogText(e.getMessage());
		}
		return null;
	}

	private String extractSize(Document document, String itemId) {
		String size = "";

		try {
			Elements aElements = document.getElementsByAttributeValueContaining("class", "js-size-item");
			for (Element divElement : aElements){
				if ( divElement.hasAttr("data-id") && divElement.attr("data-id").equals(itemId)){
					return divElement.text();
				}
			}
		} catch (Exception e) {
			LogManager.writeLogText(e.getMessage());
		}
		return size;
	}

	private String extractColor(Document document) {
		String color = "";

		try {
			Element element = document.getElementsByClass("sm-goods_main_details_color clearfix").first();
			if (element != null) {
				Elements aElements = element.getElementsByTag("a");
				for (Element aElement : aElements){
					if ( !aElement.hasAttr("href") ){
						return aElement.attr("title");
					}
				}
			}
		} catch (Exception e) {
			LogManager.writeLogText(e.getMessage());
		}
		return color;
	}

	private String extractName(Document document) {
		String name = "";
		try {
			name = document.getElementsByAttributeValue("data-selenium", "product_name").first().text();
		} catch (Exception e) {
			LogManager.writeLogText(e.getMessage());
		}
		return name;
	}

	private String extractBrand(Document document) {

		String brand = "";

		try {
			Element element = document.getElementsByAttributeValue("itemprop", "brand").first();
			if (element != null && element.hasAttr("content")) {
				return element.attr("content");
			}

		} catch (Exception e) {
			LogManager.writeLogText(e.getMessage());
		}
		return brand;
	}
}
