package com.pioneersoft.sportmasterbot.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverUtil {

    public static WebDriver getWebDriver() {

//        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");

        String mainDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", mainDir + "\\src\\main\\resources\\chromedriver.exe");
        //HEADLESS
        // WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);


        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        Timer.waitSeconds(1);

        return driver;
    }
}
