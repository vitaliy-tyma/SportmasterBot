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

                //Request URL: https://www.sportmaster.ru/basket/delivery/order.do
                //Request Method: POST
                //Status Code: 200
                //Remote Address: 62.76.79.245:443
                //Referrer Policy: no-referrer-when-downgrade
                //cache-control: no-cache, no-store, max-age=0, must-revalidate
                //content-encoding: gzip
                //content-type: application/json;charset=UTF-8
                //date: Wed, 12 Sep 2018 21:51:24 GMT
                //expires: 0
                //pragma: no-cache
                //server: nginx
                //status: 200
                //vary: Accept-Encoding
                //x-content-type-options: nosniff
                //x-frame-options: SAMEORIGIN
                //x-response-by: esm1.moscow.sportmaster.ru, prod
                //x-xss-protection: 1; mode=block
                //:authority: www.sportmaster.ru
                //:method: POST
                //:path: /basket/delivery/order.do
                //:scheme: https
                //accept: */*
                //accept-encoding: gzip, deflate, br
                //accept-language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5
                //content-length: 608
                //content-type: application/json
                //cookie: __storejs__=%22__storejs__%22; n29productList2=%5B%22%D0%A0%D1%8E%D0%BA%D0%B7%D0%B0%D0%BA%20Skechers%22%5D; n29priceList3=%5B%22299%22%5D; n29currency5=%22%E2%82%BD%22; BIGipServeresm1-4=79314954.20480.0000; regsrc=direct; SMDN_AB_VERT2018_1=DN; DNCRSLA=DN; BIGipServerwaf_br=29179914.20480.0000; apple=SME10EFC389FE34A9DB11EF329DCBA0C1D; noscript=0; digineticaSid=3d222d9323ddd682c1546b3092857426; scs=%7B%22t%22%3A1%7D; ins-gaSSId=055a81f5-3e73-4f05-8b91-d28cfbbd65ac_1536790715; dejaVu=exactly; LDSP=displayed; LDPS=displayed; 3one=displayed; __olapicU=n5c2bvUFAY2slToQJkWV1ku4hmaZq8pC; NDPD=displayed; cto_lwid=b0d86ecc-c46b-4bcd-b244-543b7f1e7e50; _ga=GA1.2.1622853482.1536787123; _gid=GA1.2.1878340220.1536787123; gdeslon.ru.user_id=c2a35346-3601-49da-b25d-a4513a8b4e69; ___dmpkit___=b72f5a92-15e9-41cd-a69e-24cd69b6ecae; scarab.visitor=%22783417D63EB6E225%22; _userGUID=0:jlzndia8:MtM4aIstXi2ZoLpEr_IoDpmMjNTluKV2; dSesn=1b31a768-cfe8-3136-96a0-4443766aa3f7; _dvs=0:jlzndia8:O5xEt17Rule5gNp8g9xYv8fcbAqJYDiZ; pmaid=1536787124984; __storejs__=%22__storejs__%22; userId=121000011759552661; universe=SM083E4A7D22B94ABB820EBA1BE908B771; ordersCount=4; lobster=372489B232A839390CB47B0942560819; insUserName=Andrey; spUID=1507320553877cea8b555da.039c7d84; rr_rcs=eF5j4cotK8lMETA0MjHRNdQ1ZClN9jAzM7FMNrcw1TW3MDHXNbE0T9E1TUwy0zU0M7U0N041MzVMNAQAfxUNZA; spUID=1507320553877cea8b555da.039c7d84; current-currency=RUB; scarab.mayAdd=%5B%7B%22i%22%3A%2210124933%22%7D%5D; scarab.profile=%2210124933%7C1536787290%22; insLastAddedProduct=%7B%22name%22%3A%22%D0%A0%D1%8E%D0%BA%D0%B7%D0%B0%D0%BA%20Skechers%22%2C%22id%22%3A%2210124933%22%2C%22img%22%3A%22https%3A%2F%2Fcdn.sptmr.ru%2Fupload%2Fresize_cache%2Fiblock%2F6f9%2F331_394_1%2F12819610299.jpg%22%2C%22price%22%3A299%2C%22originalPrice%22%3A299%2C%22url%22%3A%22https%3A%2F%2Fwww.sportmaster.ru%2Fproduct%2F10124933%2F%22%2C%22cats%22%3A%5B%22%D0%90%D0%BA%D1%81%D0%B5%D1%81%D1%81%D1%83%D0%B0%D1%80%D1%8B%22%2C%22%D0%A0%D1%8E%D0%BA%D0%B7%D0%B0%D0%BA%D0%B8%20%D0%B8%20%D1%81%D1%83%D0%BC%D0%BA%D0%B8%22%2C%22%D0%A0%D1%8E%D0%BA%D0%B7%D0%B0%D0%BA%D0%B8%22%5D%2C%22quantity%22%3A0%7D; insBasketBuyGoal=1; insCartCount=1; insTotalCartAmount=299; total-cart-amount=299; rcs=eF5jYSlN9jAzNU1LM00z1E1OszTTNUlKMtBNMbVM1jU2MbU0N061NLJMTOXKLSvJTBEwNDIx0TXUNQQAoPkOfQ; oauth_detect.place=checkout; insdrSV=9; last_visit=1536776541060::1536787341060
                //origin: https://www.sportmaster.ru
                //referer: https://www.sportmaster.ru/basket/delivery.do
                //user-agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36
                //x-requested-with: XMLHttpRequest
                //x-sm-basketversion: 10
                //{"pickupInfos":[{"groupId":"SHOP_159","paymentType":"CASH_STORE","comment":null,"customContacts":false,"contacts":{"name":null,"phone":null,"email":null}}],"oldDuplicateStatus":false,"oldPhone":"9217865476","cashlessInfo":{"organisation":null,"address":null,"inn":null,"kpp":null,"gendir":null,"glavbuh":null,"bank":null,"bankAddress":null,"bankBik":null,"account":null,"bankCorAccount":null},"contacts":{"name":"Andrey","phone":"9217865476","email":"melesayul@hurify1.com"},"forceContactsConfirmation":false,"sendSmsWithStoreInfo":false,"promotions":[{"groupId":"SHOP_159","promoIds":[],"usedPromoIds":[]}]}

//                makeOrderResponse(setPickupCookies, jsonRequest);

                order = new Order();
            }

        }

        return order;
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
        Connection addConnection = Jsoup.connect("https://www.sportmaster.ru/basket/delivery.do").ignoreContentType(true);
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
