package com.pioneersoft.sportmasterbot.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtil {

    public static WebDriver getWebDriver() {

//        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");

        String mainDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", mainDir + "\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Timer.waitSeconds(1);

        return driver;
    }
}
