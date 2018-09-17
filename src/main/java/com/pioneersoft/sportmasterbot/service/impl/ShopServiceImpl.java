package com.pioneersoft.sportmasterbot.service.impl;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pioneersoft.sportmasterbot.model.Shop;
import com.pioneersoft.sportmasterbot.service.ShopService;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ShopServiceImpl implements ShopService {

    private static Logger logger = Logger.getLogger(ShopServiceImpl.class.getName());

    @Override
    public Map<String, Shop> getAllShops() {
        Map<String, Shop> allShops = new HashMap<>();

        try {
            String url = "https://www.sportmaster.ru/rest/v1/store?_=" + System.currentTimeMillis();
            String content = Jsoup.connect(url).ignoreContentType(true).execute().body();

            if (content != null && (content.startsWith("{") || content.startsWith("[{") )) {
                DocumentContext context = JsonPath.parse(content);
                List<Map<String, Object>> shops = context.read("$.*");

                for (Map<String, Object> shop : shops) {
                    Shop shopBO = new Shop();
                    shopBO.setShopId(String.valueOf(shop.get("id")));
                    shopBO.setAddress(String.valueOf(shop.get("address")));
                    if (shop.containsKey("metro")) {
                        shopBO.setMetroStation(String.valueOf(shop.get("metro")));
                    } else {
                        shopBO.setMetroStation("");
                    }

                    allShops.put(shopBO.getShopId(), shopBO);
                }
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return allShops;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public Map<String, Shop> filterShopsByAvailableItem(Map<String, Shop> allShops, String itemId) {
        Map<String, Shop> filteredShops = new HashMap<>();

        if ( allShops.isEmpty()){
            return filteredShops;
        }

        try {
            String url = "https://www.sportmaster.ru/rest/v1/sku/" + itemId + "/store/region/stock/core?_=" + System.currentTimeMillis();
            String content = Jsoup.connect(url).ignoreContentType(true).execute().body();
            if (content != null && (content.startsWith("{") || content.startsWith("[{")) ) {
                DocumentContext context = JsonPath.parse(content);
                List<Map<String, Object>> filters = context.read("$.*");

                for (Map<String, Object> filter : filters) {

                    if (filter.containsKey("stockQuantity")){
                        Map<String, Object> stockQuantity = (Map<String, Object>)filter.get("stockQuantity");
                        if (stockQuantity.containsKey("inStoreIndicator") && !(String.valueOf(stockQuantity.get("inStoreIndicator")).equalsIgnoreCase("NONE"))){
                            filteredShops.put(String.valueOf(filter.get("storeId")), allShops.get(String.valueOf(filter.get("storeId"))));
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return filteredShops;
    }
}
