package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.User;
import org.jsoup.Connection;

public interface OrderService {

    Order makeOrder(String itemId, String shopId, String login, String password);

}
