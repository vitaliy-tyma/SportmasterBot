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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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

        driver = getCartSubmitDriver(driver);

        if (driver == null) {
            return order;
        }

        driver = getDeliverySubmitDriver(driver);

        order = new Order();

        order.setOrderId(extractOrderId(driver));
        if (StringUtils.isBlank(order.getOrderId())){
            return null;
        }

        order.setAddress(shop.getAddress());
        order.setMetro(shop.getMetroStation());

        User user = getUserFromPage(driver);

        if (user == null) {
            return order;
        }

        user.setLogin(login);
        user.setPassword(password);

        Item item = itemService.findItemByItemId(itemId);

        order.setAmount(1);
        order.setItem(item);
        order.setUser(user);
        order.setOrderTime(System.currentTimeMillis());


        logger.info("Order " + order.getOrderId() + "was made");

        driver.quit();

        return order;

    }

    private String extractOrderId(WebDriver driver) {
        WebElement element = driver.findElement(By.tagName("sm-basket-thanks"));
        String jsonContent = element.getAttribute("params");

        if (StringUtils.isNotBlank(jsonContent)){
            return StringUtils.substringBetween(jsonContent, "number : \"", "\",");
        }

        return StringUtils.EMPTY;
    }

    private WebDriver getDeliverySubmitDriver(WebDriver driver) {
        WebElement submitElement = driver.findElement(By.id("gtm-shipping-continue"));
        submitElement.submit();

        Timer.waitSeconds(1);

        driver.get(driver.getCurrentUrl());
        Timer.waitSeconds(1);

        return driver;
    }

    private User getUserFromPage(WebDriver driver) {
        WebElement element = driver.findElement(By.tagName("sm-delivery-page"));
        String jsonContent = element.getAttribute("params");

        User user = userService.getUserInfo(jsonContent);

        return user;
    }

    private WebDriver getCartSubmitDriver(WebDriver driver) {
        WebElement submitElement = driver.findElement(By.id("gtm-cart-continue"));
        submitElement.submit();

        Timer.waitSeconds(1);

        driver.get(driver.getCurrentUrl());
        Timer.waitSeconds(1);

        return driver;
    }

    private WebDriver getShopSelectedDriver(WebDriver driver, String shopAddress) {

        WebElement selectBtnElement = driver.findElement(By.id("gtm-cart-store-select"));
        selectBtnElement.click();

        Timer.waitSeconds(1);
        String currentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows){
            if ( !window.equals(currentWindow) ){
                driver.switchTo().window(window);
            }
        }

        List<WebElement> trElements = driver.findElements(By.tagName("tr"));
        for (WebElement trElement : trElements) {
            if (StringUtils.containsIgnoreCase(trElement.getText(), shopAddress)) {
                WebElement submitElement = trElement.findElement(By.tagName("input"));
                submitElement.submit();
                Timer.waitSeconds(1);

                driver.get("https://www.sportmaster.ru/basket/checkout.do");
                Timer.waitSeconds(1);

                return driver;
            }
        }
        return null;
    }

    private WebDriver getAddToCartDriver(WebDriver driver) {

        List<WebElement> aElements = driver.findElements(By.tagName("a"));
        for (WebElement aElement : aElements) {
            if (StringUtils.equals(aElement.getAttribute("data-selenium"), "add_to_basket")) {
                aElement.click();
                Timer.waitSeconds(1);

                driver.get("https://www.sportmaster.ru/basket/checkout.do");
                Timer.waitSeconds(1);

                return driver;
            }
        }

        return null;
    }

    private WebDriver getProductDriver(WebDriver driver, String itemId) {

        driver.get("https://www.sportmaster.ru/catalog/product/search.do?text=" + itemId);

        Timer.waitSeconds(1);

        if (driver.getCurrentUrl().contains("www.sportmaster.ru/product")) {
            driver.get(driver.getCurrentUrl());
            Timer.waitSeconds(1);

            return driver;
        }

        return null;
    }

    private WebDriver getAuthorizedDriver(String login, String password) {

        WebDriver driver = WebDriverUtil.getWebDriver();
        driver.get("https://www.sportmaster.ru/user/session/login.do");
        Timer.waitSeconds(1);

        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
        for (WebElement element : inputElements) {
            if (StringUtils.equals(element.getAttribute("name"), "email")) {
                element.sendKeys(login);
            }

            if (StringUtils.equals(element.getAttribute("name"), "password")) {
                element.sendKeys(password);
            }
        }

        WebElement submitElement = driver.findElement(By.id("submitButton"));
        submitElement.submit();
        Timer.waitSeconds(1);

        driver.get("https://www.sportmaster.ru/");
        Timer.waitSeconds(1);

        if (!StringUtils.contains(driver.getPageSource(), "<a href=\"/user/profile/home.do\" data-bind=\"text: window.globals.username\"></a>")) {
            return driver;
        }

        return null;
    }

}
