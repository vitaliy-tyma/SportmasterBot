package com.pioneersoft.sportmasterbot.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverUtil {
    private static Logger logger = Logger.getLogger(WebDriverUtil.class.getName());

    public static WebDriver getWebDriver() {
        //+
        logger.info("WebDriverUtil.getWebDriver - START (headless mode)");

//        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");

        String mainDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", mainDir + "\\src\\main\\resources\\chromedriver.exe");
        //-
        WebDriver driver = new ChromeDriver();

        //+ HEADLES MODE!!!
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        options.addArguments("window-size=800x600");
//        WebDriver driver = new ChromeDriver(options);





//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        Timer.waitSeconds(5);
        //+
        logger.info("WebDriverUtil.getWebDriver - RETURN DRIVER");

        return driver;
    }
}
