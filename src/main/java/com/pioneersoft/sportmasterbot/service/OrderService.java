package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Order;

import java.util.Map;

public interface OrderService {

    Order makeOrder(String itemId, String shopId, String login, String password);

}
