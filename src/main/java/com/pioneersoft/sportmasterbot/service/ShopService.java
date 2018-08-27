package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Shop;
import org.jsoup.nodes.Document;

import java.util.List;

public interface ShopService {

    List<Shop> getShopLoistWithItemToBuy(Document document);
}
