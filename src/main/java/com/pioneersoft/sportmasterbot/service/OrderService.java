package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.User;

public interface OrderService {

    Order makeOrder(String itemId, String shopId, User user);
}
