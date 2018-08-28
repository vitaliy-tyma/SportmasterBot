package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.model.Shop;
import com.pioneersoft.sportmasterbot.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/item-avialability")
public class ItemAvialability {

    @Autowired
    ShopService shopService;

    @GetMapping
    ResponseEntity<String> getShopsWithItemToBuy(@RequestHeader String itemId){

        String html = null;



        return new ResponseEntity<String>(html, HttpStatus.OK);
    }

}
