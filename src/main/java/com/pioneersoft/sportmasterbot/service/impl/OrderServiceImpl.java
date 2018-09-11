package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.User;
import com.pioneersoft.sportmasterbot.service.ItemService;
import com.pioneersoft.sportmasterbot.service.OrderService;
import com.pioneersoft.sportmasterbot.service.UserService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Override
    public Order makeOrder(String itemId, String shopId, String login, String password) {

        Connection.Response userResponse = userService.tryToLogin(login, password);

        if (userResponse != null) {
            Item item = itemService.findItemByItemId(itemId);
            User user = userService.getUserInfo(userResponse);

            Map<String, String> userCookies = userResponse.cookies();

            Connection.Response addResponce = makeAddResponse(userCookies, item);

            Map<String, String> addCookies = addResponce.cookies();

            Connection.Response setPickupResponce = makeSetPickupResponse(addCookies, itemId, shopId);

            Map<String, String> setPickupCookies = setPickupResponce.cookies();

//            Connection.Response deliveryResponce = makeDeliveryResponse(setPickupCookies, itemId, shopId);

//            Map<String, String> deliverypCookies = deliveryResponce.cookies();
        }

        return null;
    }

    private Connection.Response makeSetPickupResponse(Map<String, String> addCookies, String itemId, String shopId) {
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/setPickupStoreId.do").ignoreContentType(true);
        addConnection.cookies(addCookies);

        addConnection.header("accept", "*/*");
        addConnection.header("accept-encoding", "gzip, deflate, br");
        addConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        addConnection.header("x-requested-with", "XMLHttpRequest");

        addConnection.data("storeId", shopId);
        addConnection.data("skuId", itemId);
        addConnection.data("force", "false");

        return addConnection.ignoreContentType(true).response();
    }

    private Connection.Response makeAddResponse(Map<String, String> userCookies, Item item) {
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/add.do").ignoreContentType(true);
        addConnection.cookies(userCookies);

        addConnection.header("accept", "*/*");
        addConnection.header("accept-encoding", "gzip, deflate, br");
        addConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        addConnection.header("x-requested-with", "XMLHttpRequest");

        addConnection.data("itemId", item.getProductId());
        addConnection.data("skuId", item.getItemId());
        addConnection.data("quantity", "1");

        return addConnection.ignoreContentType(true).response();
    }


    // https://www.sportmaster.ru/basket/add.do

    // https://www.sportmaster.ru/basket/checkout/setPickupStoreId.do

    // https://www.sportmaster.ru/basket/delivery.do

    // https://www.sportmaster.ru/basket/delivery/order.do

    // https://www.sportmaster.ru/basket/thanks.do
}
