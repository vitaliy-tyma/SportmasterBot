package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.model.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getShopListWithItemToBuy(Item item);
}
