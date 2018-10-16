package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.service.ItemService;
import com.pioneersoft.sportmasterbot.service.ShopService;
import com.pioneersoft.sportmasterbot.service.UserService;
import com.pioneersoft.sportmasterbot.util.HtmlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/check")
//@RequestMapping(path = "/api/check")
public class CheckController {

    private static Logger logger = Logger.getLogger(CheckController.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    ItemService itemService;

    @Autowired
    HtmlManager htmlManager;

    @PostMapping(path = "/user")
    public ResponseEntity<String> checkUserAsRegistered(@RequestParam String login, @RequestParam String password) {
        logger.info("Request to ItemCheckController method POST. Try to check user " + login + " with password " + password);

        String itemBox =  htmlManager.getItemCheckBox("", login, password);

        String userBox = "";

        String tableRows = "";

        String html = htmlManager.getItemSelectionPage(itemBox, userBox, tableRows);
        return new ResponseEntity<String>(html, HttpStatus.OK);
    }

    @PostMapping(path = "/item")
    public ResponseEntity<String> findItem(@RequestParam String itemId, @RequestParam String login, @RequestParam String password) {
        logger.info("Request to UserController method POST. Try to get item info with id " + itemId);

        Item item = itemService.findItemByItemId(itemId);
        String itemBox = "";
        if (item != null) {
            itemBox = htmlManager.getItemCheckBox(item.getItemId(), login, password);
        } else {
            itemBox = htmlManager.getItemCheckBox(" ", login, password);

        }

        String userBox = "";
        if (userService.tryToLogin(login, password) == null) {
            userBox = htmlManager.getUnauthorizedUserHtml();
        }

        String tableRows = htmlManager.getShopSelectionRows(
                shopService.filterShopsByAvailableItem(shopService.getAllShops(), itemId), itemId, login, password);


       String html = htmlManager.getItemSelectionPage(itemBox, userBox, tableRows);
       return new ResponseEntity<>(html, HttpStatus.OK);
    }
}
