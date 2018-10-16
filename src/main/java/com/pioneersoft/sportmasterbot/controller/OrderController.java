package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.service.OrderService;
import com.pioneersoft.sportmasterbot.util.HtmlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/order")
//@RequestMapping(path = "/api/order")
public class OrderController {

    private static Logger logger = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private HtmlManager htmlManager;
    
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> makeOrder
            (@RequestParam String itemId, @RequestParam String shopId, @RequestParam String login,@RequestParam String password) {
        logger.info("Request to UserController method POST. Try to order item " + itemId + " in shop " + shopId + " for account " + login);

        Order order = orderService.makeOrder(itemId, shopId, login, password);

        String html = htmlManager.getOrderPage(order, login, password);

        return new ResponseEntity<>(html, HttpStatus.OK);
    }

}
