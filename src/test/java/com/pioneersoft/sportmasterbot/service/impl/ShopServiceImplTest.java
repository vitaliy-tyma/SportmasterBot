package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Shop;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ShopServiceImplTest {

    @Test
    public void getAllShops() {
        ShopServiceImpl shopService = new ShopServiceImpl();
        Map<String, Shop> allShops = shopService.getAllShops();

        assertNotNull(allShops);
        assertTrue(!allShops.isEmpty());

        System.out.println("------ ALL SHOPS ------");
        for (String key : allShops.keySet()) {
            System.out.println(allShops.get(key));
        }

    }

//    @Ignore
    @Test
    public void filterShopsByAvailableItem() {
        // 55891317M
        // https://www.sportmaster.ru/product/10198205/?skuId=55891317M&text=55891317M

        ShopServiceImpl shopService = new ShopServiceImpl();
        Map<String, Shop> allShops = shopService.getAllShops();

        Map<String, Shop> filteredShops = shopService.filterShopsByAvailableItem(allShops, "55891317M");

        assertNotNull(filteredShops);
        assertTrue(!filteredShops.isEmpty());
        assertTrue(allShops.size() >= filteredShops.size());

        System.out.println("------ FILTERED SHOPS ------");
        for (String key : filteredShops.keySet()) {
            System.out.println( filteredShops.get(key));
        }
    }
}