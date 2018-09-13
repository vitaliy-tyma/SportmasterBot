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

        Order order = null;
        if (userResponse != null) {
            Item item = itemService.findItemByItemId(itemId);

            Map<String, String> userCookies = userResponse.cookies();

            Connection.Response addResponce = makeAddResponse(userCookies, item);

            Map<String, String> addCookies = addResponce.cookies();

            Connection.Response setPickupResponce = makeSetPickupResponse(addCookies, itemId, shopId);

            Map<String, String> setPickupCookies = setPickupResponce.cookies();

            makeSubmitResponse(setPickupCookies);

            Connection.Response delyveryResponce = makeDeliveryResponse(setPickupCookies);

            if (delyveryResponce.statusCode() == 200) {
                User user = userService.getUserInfo(delyveryResponce);
                user.setLogin(login);
                user.setPassword(password);

                String jsonRequest = createJsonRequest(user, shopId);

                makeOrderResponse(setPickupCookies, jsonRequest);

//                order = getOrderFromLastResponse();                
                
            }

        }

        return order;
    }

    private void makeOrderResponse(Map<String, String> setPickupCookies, String jsonRequest) {
    	Connection orderConnection = Jsoup.connect("https://www.sportmaster.ru/basket/delivery/order.do").ignoreContentType(true);
        orderConnection.cookies(setPickupCookies);

        orderConnection.header("accept", "*/*");
        orderConnection.header("accept-encoding", "gzip, deflate, br");
        orderConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        orderConnection.header("x-requested-with", "XMLHttpRequest");
        orderConnection.header("x-sm-basketversion", "6");
        
        orderConnection.data(jsonRequest);

        try {
            orderConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private String createJsonRequest(User user, String shopId) {
        String text = "{" +
                "\"pickupInfos\": [" +
                "{" +
                "\"groupId\": \"SHOP_%1$s\"," +
                "\"paymentType\": \"CASH_STORE\"," +
                "\"comment\": null," +
                "\"customContacts\": false," +
                "\"contacts\": {" +
                "\"name\": null," +
                "\"phone\": null," +
                "\"email\": null" +
                "}" +
                "}" +
                "]," +
                "\"oldDuplicateStatus\": false," +
                "\"oldPhone\": \"%2$s\"," +
                "\"cashlessInfo\": {" +
                "\"organisation\": null," +
                "\"address\": null," +
                "\"inn\": null," +
                "\"kpp\": null," +
                "\"gendir\": null," +
                "\"glavbuh\": null," +
                "\"bank\": null," +
                "\"bankAddress\": null," +
                "\"bankBik\": null,n" +
                "\"account\": null," +
                "\"bankCorAccount\": null" +
                "}," +
                "\"contacts\": {" +
                "\"name\": \"%3$s\"," +
                "\"phone\": \"%4$s\"," +
                "\"email\": \"%5$s\"" +
                "}," +
                "\"forceContactsConfirmation\": false," +
                "\"sendSmsWithStoreInfo\": false," +
                "\"promotions\": [" +
                "{" +
                "\"groupId\": \"SHOP_%6$s\"," +
                "\"promoIds\": []," +
                "\"usedPromoIds\": []" +
                "}" +
                "]" +
                "}";
        return String.format(text, shopId, user.getPhone(), user.getName(), user.getPhone(), user.getEmail());
    }

    private Connection.Response makeDeliveryResponse(Map<String, String> setPickupCookies) {
        Connection deliveryConnection = Jsoup.connect("https://www.sportmaster.ru/basket/delivery.do").ignoreContentType(true);
        deliveryConnection.cookies(setPickupCookies);

        deliveryConnection.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        deliveryConnection.header("accept-encoding", "gzip, deflate, br");
        deliveryConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        deliveryConnection.header("referer", "https://www.sportmaster.ru/basket/checkout.do");
        deliveryConnection.header("upgrade-insecure-requests", "1");

        return deliveryConnection.ignoreContentType(true).method(Connection.Method.GET).response();
    }

    private void makeSubmitResponse(Map<String, String> setPickupCookies) {
        Connection submitConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/submit.do").ignoreContentType(true);
        submitConnection.cookies(setPickupCookies);

        submitConnection.header("accept", "*/*");
        submitConnection.header("accept-encoding", "gzip, deflate, br");
        submitConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        submitConnection.header("x-requested-with", "XMLHttpRequest");
        submitConnection.header("x-sm-basketversion", "6");

        try {
            submitConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection.Response makeSetPickupResponse(Map<String, String> addCookies, String itemId, String shopId) {
        Connection setPickupConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/setPickupStoreId.do").ignoreContentType(true);
        setPickupConnection.cookies(addCookies);

        setPickupConnection.header("accept", "*/*");
        setPickupConnection.header("accept-encoding", "gzip, deflate, br");
        setPickupConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        setPickupConnection.header("x-requested-with", "XMLHttpRequest");

        setPickupConnection.data("storeId", shopId);
        setPickupConnection.data("skuId", itemId);
        setPickupConnection.data("force", "false");

        return setPickupConnection.ignoreContentType(true).method(Connection.Method.POST).response();
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

        return addConnection.ignoreContentType(true).method(Connection.Method.POST).response();
    }


    // https://www.sportmaster.ru/basket/add.do

    // https://www.sportmaster.ru/basket/checkout/setPickupStoreId.do

    // https://www.sportmaster.ru/basket/checkout/submit.do

    // https://www.sportmaster.ru/basket/delivery.do

    // https://www.sportmaster.ru/basket/delivery/order.do

    // https://www.sportmaster.ru/basket/thanks.do
}
