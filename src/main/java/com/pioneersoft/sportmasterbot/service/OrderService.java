package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Order;

public interface OrderService {

    Order makeOrder(String itemId, String shopId, String login, String password);
    
    String getOrdrInfoBox(Order order);

}
