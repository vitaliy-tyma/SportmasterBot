package com.pioneersoft.sportmasterbot.service.impl;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.User;
import com.pioneersoft.sportmasterbot.service.ItemService;
import com.pioneersoft.sportmasterbot.service.OrderService;
import com.pioneersoft.sportmasterbot.service.UserService;
import com.pioneersoft.sportmasterbot.util.Timer;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Override
    public Order makeOrder(String itemId, String shopId, String login, String password) {

        Connection.Response userResponse = userService.tryToLogin(login, password);

        Map<String, String> userCookies = userResponse.cookies();

        removeItemsFromCart(login, password);

        Order order;

        Item item = itemService.findItemByItemId(itemId);


        makeAddResponse(userCookies, item);

        makeSetPickupResponse(userCookies, itemId, shopId);

        makeSubmitResponse(userCookies);

        Document deliveryResponce = makeDeliveryResponse(userCookies);

        String basketVersion = getBasketVersion(deliveryResponce);

        if (deliveryResponce != null) {
            User user = userService.getUserInfo(deliveryResponce);
            if (user == null) {
                return null;
            }

            user.setLogin(login);
            user.setPassword(password);


            String jsonRequest = createJsonRequest(user, shopId);

            makeOrderResponse(userCookies, jsonRequest, basketVersion);

            Document orderResponse = makeThanksResponse(userCookies);

            if (orderResponse != null) {
                String orderInfo = orderResponse.getElementsByTag("sm-basket-thanks").attr("params");

                order = new Order();

                order.setAmount(1);
                order.setItem(item);
                order.setUser(user);
                order.setOrderTime(System.currentTimeMillis());
                order.setOrderId(extractOrderId(orderInfo));
                order.setAddress(extractAddress(orderInfo));
                order.setMetro(extractMetro(orderInfo));

                logger.info("Order " + order.getOrderId() + "was made");

                removeItemsFromCart(login, password);
                return order;

            }

        }

        return null;
    }

    @Override
    public void removeItemsFromCart(String login, String password) {
        Connection cartConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout.do");

        Connection.Response userResponse = userService.tryToLogin(login, password);

        Map<String, String> userCookies = userResponse.cookies();

        cartConnection.cookies(userCookies);

        cartConnection.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        cartConnection.header("accept-encoding", "gzip, deflate, br");
        cartConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");

        try {
            Document cartDocument = cartConnection.ignoreContentType(true).get();

            String jsonContent = StringUtils.substringBetween(cartDocument.toString(), "quickCart = ko.observable(", ");");

            DocumentContext jsonContext = JsonPath.parse(jsonContent);

            List<String> itemIdList = jsonContext.read("$..items..skuId");

            Set<String> itemIdSet = new HashSet<>(itemIdList);

            String version = String.valueOf(jsonContext.read("$.version", Integer.class));

            for (String itemId : itemIdSet) {
                removeItemFromCart(itemId, version, userCookies);

                Timer.waitSeconds(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void removeItemFromCart(String itemId, String version, Map<String, String> userCookies) {
        Connection removeConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/submit.do").ignoreContentType(true);
        removeConnection.cookies(userCookies);

        removeConnection.header("accept", "*/*");
        removeConnection.header("accept-encoding", "gzip, deflate, br");
        removeConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        removeConnection.header("x-requested-with", "XMLHttpRequest");
        removeConnection.header("x-sm-basketversion", version);

        removeConnection.data("skuIds", itemId);

        try {
            removeConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getBasketVersion(Document deliveryResponce) {
        if (deliveryResponce != null) {
            String jsonInHtml = deliveryResponce.getElementsByTag("sm-delivery-page").first().attr("params");

            String version = StringUtils.substringAfterLast(jsonInHtml, "\"contentVersion\":");
            version = StringUtils.substringBefore(version, "},\"");
            return version;
        }
        return StringUtils.EMPTY;
    }

    private String extractMetro(String orderInfo) {
        String metro = StringUtils.substringBetween(orderInfo, "shopMetro : ", "shopAddress : ");
        if(StringUtils.isBlank(metro))
        {
            return StringUtils.EMPTY;
        }
        metro = metro.replaceAll("\"", "");
        metro = metro.replaceAll("&quot;", "");

        metro = StringUtils.removeEnd(metro, ",");

        return metro;
    }

    private String extractAddress(String orderInfo) {
        String address = StringUtils.substringBetween(orderInfo, "shopAddress : ", "workTime");
        if(StringUtils.isBlank(address))
        {
            return StringUtils.EMPTY;
        }
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

    private Document makeThanksResponse(Map<String, String> setPickupCookies) {
        Connection orderConnection = Jsoup.connect("https://www.sportmaster.ru/basket/thanks.do");
        orderConnection.cookies(setPickupCookies);

        orderConnection.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        orderConnection.header("accept-encoding", "gzip, deflate, br");
        orderConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");

        try {
            return orderConnection.ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void makeOrderResponse(Map<String, String> setPickupCookies, String jsonRequest, String basketVersion) {
        Connection orderConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/submit.do").ignoreContentType(true);
        orderConnection.cookies(setPickupCookies);

        orderConnection.header("accept", "*/*");
        orderConnection.header("accept-encoding", "gzip, deflate, br");
        orderConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        orderConnection.header("x-requested-with", "XMLHttpRequest");
        orderConnection.header("x-sm-basketversion", basketVersion);

        orderConnection.data(jsonRequest);

        try {
            orderConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createJsonRequest(User user, String shopId) {
        return "{\n" +
                "  \"pickupInfos\": [" +
                "    {" +
                "      \"groupId\": \"SHOP_" + shopId + "\"," +
                "      \"paymentType\": \"CASH_STORE\"," +
                "      \"comment\": null," +
                "      \"customContacts\": false," +
                "      \"contacts\": {" +
                "        \"name\": null," +
                "        \"phone\": null," +
                "        \"email\": null" +
                "      }" +
                "    }" +
                "  ]," +
                "  \"oldDuplicateStatus\": false," +
                "  \"oldPhone\": \"" + user.getPhone() + "\"," +
                "  \"cashlessInfo\": {" +
                "    \"organisation\": null," +
                "    \"address\": null," +
                "    \"inn\": null," +
                "    \"kpp\": null," +
                "    \"gendir\": null," +
                "    \"glavbuh\": null," +
                "    \"bank\": null," +
                "    \"bankAddress\": null," +
                "    \"bankBik\": null," +
                "    \"account\": null," +
                "    \"bankCorAccount\": null" +
                "  }," +
                "  \"contacts\": {" +
                "    \"name\": \"" + user.getName() + "\"," +
                "    \"phone\": \"" + user.getPhone() + "\"," +
                "    \"email\": \"" + user.getEmail() + "\"" +
                "  }," +
                "  \"forceContactsConfirmation\": false," +
                "  \"sendSmsWithStoreInfo\": false," +
                "  \"promotions\": [" +
                "    {" +
                "      \"groupId\": \"SHOP_" + shopId + "\"," +
                "      \"promoIds\": [],\n" +
                "      \"usedPromoIds\": []" +
                "    }" +
                "  ]" +
                "}";
    }

    private Document makeDeliveryResponse(Map<String, String> setPickupCookies) {
        Connection deliveryConnection = Jsoup.connect("https://www.sportmaster.ru/basket/delivery.do");
        deliveryConnection.cookies(setPickupCookies);

        deliveryConnection.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        deliveryConnection.header("accept-encoding", "gzip, deflate, br");
        deliveryConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        deliveryConnection.header("referer", "https://www.sportmaster.ru/basket/checkout.do");
        deliveryConnection.header("upgrade-insecure-requests", "1");

        try {
            return deliveryConnection.ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void makeSubmitResponse(Map<String, String> setPickupCookies) {
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/submit.do");
        addConnection.cookies(setPickupCookies);

        addConnection.header("accept", "*/*");
        addConnection.header("accept-encoding", "gzip, deflate, br");
        addConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        addConnection.header("x-requested-with", "XMLHttpRequest");
        addConnection.header("x-sm-basketversion", "10");

        try {
            addConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeSetPickupResponse(Map<String, String> addCookies, String itemId, String shopId) {
        Connection setPickupConnection = Jsoup.connect("https://www.sportmaster.ru/basket/checkout/setPickupStoreId.do");
        setPickupConnection.cookies(addCookies);

        setPickupConnection.header("accept", "*/*");
        setPickupConnection.header("accept-encoding", "gzip, deflate, br");
        setPickupConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        setPickupConnection.header("x-requested-with", "XMLHttpRequest");

        setPickupConnection.data("storeId", shopId);
        setPickupConnection.data("skuId", itemId);
        setPickupConnection.data("force", "false");

        try {
            setPickupConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeAddResponse(Map<String, String> userCookies, Item item) {
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/add.do");
        addConnection.cookies(userCookies);

        addConnection.header("accept", "*/*");
        addConnection.header("accept-encoding", "gzip, deflate, br");
        addConnection.header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        addConnection.header("x-requested-with", "XMLHttpRequest");

        addConnection.data("itemId", item.getProductId());
        addConnection.data("skuId", item.getItemId());
        addConnection.data("quantity", "1");

        try {
            addConnection.ignoreContentType(true).method(Connection.Method.POST).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
