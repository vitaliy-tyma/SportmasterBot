package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.User;
import com.pioneersoft.sportmasterbot.service.ItemService;
import com.pioneersoft.sportmasterbot.service.OrderService;
import com.pioneersoft.sportmasterbot.service.UserService;
import com.pioneersoft.sportmasterbot.util.LogManager;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

                Connection.Response orderResponse = makeThanksResponse(setPickupCookies);

                if(orderResponse.statusCode() == 200){
                    try {
                        Document document = orderResponse.parse();

                        String orderInfo = document.getElementsByTag("sm-basket-thanks").attr("params");

                        order = new Order();

                        order.setAmount(1);
                        order.setItem(item);
                        order.setUser(user);
                        order.setOrderTime(System.currentTimeMillis());
                        order.setOrderId(extractOrderId(orderInfo));
                        order.setAddress(extractAddress(orderInfo));
                        order.setMetro(extractMetro(orderInfo));

                        LogManager.writeLogText("Order " + order.getOrderId() + "was made");

                        return order;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

        return order;
    }

    private String extractMetro(String orderInfo) {
        String metro = StringUtils.substringBetween(orderInfo, "shopMetro : ", "shopAddress : ");
        metro = metro.replaceAll("\"", "");
        metro = metro.replaceAll("&quot;", "");

        metro = StringUtils.removeEnd(metro, ",");

        return metro;
    }

    private String extractAddress(String orderInfo) {
        String address = StringUtils.substringBetween(orderInfo, "shopAddress : ", "workTime");
        address = address.replaceAll("\"", "");
        address = address.replaceAll("&quot;", "");

        address = StringUtils.removeEnd(address, ",");

        return address;
    }

    private String extractOrderId(String orderInfo) {
        String order = StringUtils.substringAfter(orderInfo, "number : ");
        order = StringUtils.substringBefore(order, ",");

        order = order.replaceAll("\"", "");
        order = order.replaceAll("&quot;", "");

        return order;
    }

    private Connection.Response makeThanksResponse(Map<String, String> setPickupCookies) {
        Connection orderConnection = Jsoup.connect("https://www.sportmaster.ru/basket/thanks.do");
        orderConnection.cookies(setPickupCookies);

        orderConnection.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        orderConnection.header("accept-encoding", "gzip, deflate, br");
        orderConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");

        return  orderConnection.ignoreContentType(true).method(Connection.Method.GET).response();
    }

    private void makeOrderResponse(Map<String, String> setPickupCookies, String jsonRequest) {
        Connection orderConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/submit.do").ignoreContentType(true);
        orderConnection.cookies(setPickupCookies);

        orderConnection.header("accept", "*/*");
        orderConnection.header("accept-encoding", "gzip, deflate, br");
        orderConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        orderConnection.header("x-requested-with", "XMLHttpRequest");
        orderConnection.header("x-sm-basketversion", "8");

        orderConnection.data(jsonRequest);

        try {
            orderConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createJsonRequest(User user, String shopId) {
        String text = "{\n" +
                "  \"pickupInfos\": [\n" +
                "    {\n" +
                "      \"groupId\": \"SHOP_%1$s\",\n" +
                "      \"paymentType\": \"CASH_STORE\",\n" +
                "      \"comment\": null,\n" +
                "      \"customContacts\": false,\n" +
                "      \"contacts\": {\n" +
                "        \"name\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"email\": null\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"oldDuplicateStatus\": false,\n" +
                "  \"oldPhone\": \"%2$s\",\n" +
                "  \"cashlessInfo\": {\n" +
                "    \"organisation\": null,\n" +
                "    \"address\": null,\n" +
                "    \"inn\": null,\n" +
                "    \"kpp\": null,\n" +
                "    \"gendir\": null,\n" +
                "    \"glavbuh\": null,\n" +
                "    \"bank\": null,\n" +
                "    \"bankAddress\": null,\n" +
                "    \"bankBik\": null,\n" +
                "    \"account\": null,\n" +
                "    \"bankCorAccount\": null\n" +
                "  },\n" +
                "  \"contacts\": {\n" +
                "    \"name\": \"%3$s\",\n" +
                "    \"phone\": \"%4$s\",\n" +
                "    \"email\": \"%5$s\"\n" +
                "  },\n" +
                "  \"forceContactsConfirmation\": false,\n" +
                "  \"sendSmsWithStoreInfo\": false,\n" +
                "  \"promotions\": [\n" +
                "    {\n" +
                "      \"groupId\": \"SHOP_%6$s\",\n" +
                "      \"promoIds\": [],\n" +
                "      \"usedPromoIds\": []\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return String.format(text, shopId, user.getPhone(), user.getName(), user.getPhone(), user.getEmail());
    }

    private Connection.Response makeDeliveryResponse(Map<String, String> setPickupCookies) {
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/delivery.do");
        addConnection.cookies(setPickupCookies);

        addConnection.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        addConnection.header("accept-encoding", "gzip, deflate, br");
        addConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        addConnection.header("referer", "https://www.sportmaster.ru/basket/checkout.do");
        addConnection.header("upgrade-insecure-requests", "1");

        return addConnection.ignoreContentType(true).method(Connection.Method.GET).response();
    }

    private void makeSubmitResponse(Map<String, String> setPickupCookies) {
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/submit.do").ignoreContentType(true);
        addConnection.cookies(setPickupCookies);

        addConnection.header("accept", "*/*");
        addConnection.header("accept-encoding", "gzip, deflate, br");
        addConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        addConnection.header("x-requested-with", "XMLHttpRequest");
        addConnection.header("x-sm-basketversion", "8");

        try {
            addConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        return addConnection.ignoreContentType(true).method(Connection.Method.POST).response();
    }

    private Connection.Response makeAddResponse(Map<String, String> userCookies, Item item) {
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/add.do");
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

	@Override
	public String getOrdrInfoBox(Order order) {
		String orderBox = 
				"<div id=\"order-info\">\n" + 
				"                    Item ID: " + order.getItem().getItemId() + "\n" + 
				"                    <br><br>\n" + 
				"                    Item name: " + order.getItem().getItemName() + "\n" + 
				"                    <br><br>\n" + 
				"                    Item price: " + order.getItem().getPrice() + "\n" + 
				"                    <br><br>\n" + 
				"                    User: " + order.getUser().getName() + "\n" + 
				"                    <br><br>\n" + 
				"                    User login: " + order.getUser().getLogin() + "\n" + 
				"                    <br><br>\n" + 
				"                    Store metro: " + order.getMetro() == null ? "" : order.getMetro()  + "\n" + 
				"                    <br><br>\n" + 
				"                    Store address: " + order.getAddress() + "\n" + 
				"                    <br><br>\n" + 
				"                    Order ID: " + order.getOrderId() + "\n" + 
				"                    <br><br><br><br>\n" + 
				"\n" + 
				"                </div>";
		
		return null;
	}
}
