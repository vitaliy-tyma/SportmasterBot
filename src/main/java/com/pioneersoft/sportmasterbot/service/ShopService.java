package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.model.Shop;

import java.util.List;
import java.util.Map;

public interface ShopService {

    Map<String, Shop> getAllShops();

    Map<String, Shop> filterShopsByAvailableItem(Map<String, Shop> allShops, String itemId);
}
