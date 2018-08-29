package com.pioneersoft.sportmasterbot.service.impl;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pioneersoft.sportmasterbot.model.Shop;
import com.pioneersoft.sportmasterbot.service.ShopService;
import com.pioneersoft.sportmasterbot.util.LogManager;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {


    @Override
    public Map<String, Shop> getAllShops() {
        Map<String, Shop> allShops = new HashMap<>();

        try {
            String content = Jsoup.connect("https://www.sportmaster.ru/rest/v1/store?_=" + System.currentTimeMillis()).get().text();

            if (content != null && content.startsWith("{")) {
                DocumentContext context = JsonPath.parse(content);
                List<Map<String, Object>> shops = context.read("$.*");

                for ( Map<String, Object> shop : shops ){

                }

            }


        } catch (IOException e) {
            LogManager.writeLogText(e.getMessage());
        }


        return null;
    }

    @Override
    public Map<String, Shop> filterShopsByAvailableItem(Map<String, Shop> allShops, String itemId) {
        return null;
    }
}
