package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.Shop;
import com.pioneersoft.sportmasterbot.model.User;
import com.pioneersoft.sportmasterbot.service.ItemService;
import com.pioneersoft.sportmasterbot.service.OrderService;
import com.pioneersoft.sportmasterbot.service.ShopService;
import com.pioneersoft.sportmasterbot.service.UserService;
import com.pioneersoft.sportmasterbot.util.Timer;
import com.pioneersoft.sportmasterbot.util.WebDriverUtil;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
    public class OrderServiceImpl implements OrderService {

    private static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ShopService shopService;








    @Override
    public Order makeOrder(String itemId, String shopId, String login, String password) {
        //+
        logger.info("OrderServiceImpl.makeOrder - START (!!!!!!!!!!!!!!!)");

        Order order = null;

        WebDriver driver = getAuthorizedDriver(login, password);

        if (driver == null) {
            return order;
        }

        driver = getProductDriver(driver, itemId);

        if (driver == null) {
            return order;
        }

        driver = getAddToCartDriver(driver);

        if (driver == null) {
            return order;
        }

        Shop shop = shopService.getAllShops().get(shopId);

        driver = getShopSelectedDriver(driver, shop.getAddress());

        if (driver == null) {
            return order;
        }
        //+
        logger.info("OrderServiceImpl.makeOrder - after getShopSelectedDriver");



        driver = getCartSubmitDriver(driver);
        //+
        logger.info("OrderServiceImpl.makeOrder - after getCartSubmitDriver");


        User user = getUserFromPage(driver);

        if (user == null) {
            return order;
        }

        user.setLogin(login);
        user.setPassword(password);

        driver = getDeliverySubmitDriver(driver);
        //+
        logger.info("OrderServiceImpl.makeOrder - after getDeliverySubmitDriver");


        order = new Order();

        order.setOrderId(extractOrderId(driver));
        //+
        logger.info("OrderServiceImpl.makeOrder - after setOrderId");


        if (StringUtils.isBlank(order.getOrderId())) {
            driver.quit();
            return null;
        }

        order.setAddress(shop.getAddress());
        //+
        logger.info("OrderServiceImpl.makeOrder - after setAddress");


        order.setMetro(shop.getMetroStation());
        //+
        logger.info("OrderServiceImpl.makeOrder - after setMetro");


        Item item = itemService.findItemByItemId(itemId);
        //+
        logger.info("OrderServiceImpl.makeOrder - after itemService.findItemByItemId");


        order.setAmount(1);
        //+
        logger.info("OrderServiceImpl.makeOrder - after setAmount");

        order.setItem(item);
        //+
        logger.info("OrderServiceImpl.makeOrder - after setItem");

        order.setUser(user);
        //+
        logger.info("OrderServiceImpl.makeOrder - after setUser");

        order.setOrderTime(System.currentTimeMillis());
        //+
        logger.info("OrderServiceImpl.makeOrder - after setOrderTime");




//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        logger.info("Order " + order.getOrderId() + " was made!");

        driver.quit();

        //+
        logger.info("OrderServiceImpl.makeOrder - return order");

        return order;

    }









    private String extractOrderId(WebDriver driver) {

        //+
        logger.info("OrderServiceImpl.extractOrderId - START");




        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //CHECK dm-basket-thanks
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        WebElement element = driver.findElement(By.tagName("sm-basket-thanks"));
        String jsonContent = element.getAttribute("params");











        if (StringUtils.isNotBlank(jsonContent)) {
            //+
            logger.info("OrderServiceImpl.extractOrderId - ready to return");

            return StringUtils.substringBetween(jsonContent, "number : \"", "\",");
        }

        return StringUtils.EMPTY;
    }





    private WebDriver getDeliverySubmitDriver(WebDriver driver) {
        WebElement submitElement = driver.findElement(By.id("gtm-shipping-continue"));
        submitElement.click();

        Timer.waitSeconds(1);

        driver.get(driver.getCurrentUrl());
        Timer.waitSeconds(1);

        return driver;
    }




    private User getUserFromPage(WebDriver driver) {
        WebElement element = driver.findElement(By.tagName("sm-delivery-page"));
        String jsonContent = element.getAttribute("params");

        return userService.getUserInfo(jsonContent);
    }




    private WebDriver getCartSubmitDriver(WebDriver driver) {
        WebElement submitElement = driver.findElement(By.id("gtm-cart-continue"));
        submitElement.click();

        Timer.waitSeconds(1);

        driver.get(driver.getCurrentUrl());
        Timer.waitSeconds(1);

        return driver;
    }





    private WebDriver getShopSelectedDriver(WebDriver driver, String shopAddress) {
        //+
        logger.info("OrderServiceImpl.getShopSelectedDriver - START");

        try {
            WebElement selectBtnElement = driver.findElement(By.id("gtm-cart-store-select"));
            Timer.waitSeconds(1);
            Actions builder = new Actions(driver);
            builder.moveToElement(selectBtnElement)
                    .click(selectBtnElement).build().perform();

            Timer.waitSeconds(1);

            List<WebElement> trElements = driver.findElements(By.tagName("tr"));
            Timer.waitSeconds(1);
            for (WebElement trElement : trElements) {

                //+
                logger.info("OrderServiceImpl.getShopSelectedDriver - find our SHOP and click INPUT ELEMENT = " + trElement.getText());

                if (StringUtils.containsIgnoreCase(trElement.getText(), shopAddress)) {
                    ////////////////////////////////////////////////////
                    //WHY IS IT NOT FIND?
                    ////////////////////////////////////////////////////
                    WebElement submitElement = trElement.findElement(By.tagName("input"));
                    submitElement.click();
                    Timer.waitSeconds(1);

                    driver.get("https://www.sportmaster.ru/basket/checkout.do");
                    Timer.waitSeconds(1+4);

                    //+
                    logger.info("OrderServiceImpl.getShopSelectedDriver - ready to return driver");

                    return driver;
                }
            }
        } catch (Exception e){

            //+
            logger.info("OrderServiceImpl.getShopSelectedDriver - exception has been raised!!!!!!");

            driver.quit();
            return null;
        }

        driver.quit();
        return null;
    }





    private WebDriver getAddToCartDriver(WebDriver driver) {
        //+
        logger.info("OrderServiceImpl.getAddToCartDriver - START");

        List<WebElement> aElements = driver.findElements(By.tagName("a"));
        Timer.waitSeconds(1);
        for (WebElement aElement : aElements) {
            if (StringUtils.equals(aElement.getAttribute("data-selenium"), "add_to_basket")) {
                aElement.click();
                Timer.waitSeconds(1);

                driver.get("https://www.sportmaster.ru/basket/checkout.do");
                Timer.waitSeconds(1);

                //+
                logger.info("OrderServiceImpl.getAddToCartDriver - ready to return");

                return driver;
            }
        }
        driver.quit();
        return null;
    }





    private WebDriver getProductDriver(WebDriver driver, String itemId) {

        driver.get("https://www.sportmaster.ru/catalog/product/search.do?text=" + itemId);
        //+5
        Timer.waitSeconds(5);

        if (driver.getCurrentUrl().contains("www.sportmaster.ru/product")) {
//            ((HtmlUnitDriver)driver).setJavascriptEnabled(true);
            driver.get(driver.getCurrentUrl());
            Timer.waitSeconds(1);

            return driver;
        }

        driver.quit();
        return null;
    }




    private WebDriver getAuthorizedDriver(String login, String password) {

        WebDriver driver = WebDriverUtil.getWebDriver();

        driver.get("https://www.sportmaster.ru/user/session/login.do");
        Timer.waitSeconds(1);

        WebElement inputNameElement = driver.findElement(By.name("email"));
        WebElement inputPasswordElement = driver.findElement(By.name("password"));

        if (inputNameElement != null) {
            inputNameElement.sendKeys(login);
        }

        if (inputPasswordElement != null) {
            inputPasswordElement.sendKeys(password);
        }


        WebElement submitElement = driver.findElement(By.id("submitButton"));
        submitElement.click();
        Timer.waitSeconds(1);

        driver.get("https://www.sportmaster.ru/");
        Timer.waitSeconds(1);

        if (!StringUtils.contains(driver.getPageSource(), "<a href=\"/user/profile/home.do\" data-bind=\"text: window.globals.username\"></a>")) {
            return driver;
        }
        driver.quit();
        return null;
    }

}
