package com.sportmaster.service;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sportmaster.util.Timer;

public class chromeDriver {
	private static String user_dir = System.getProperty("user.dir");
	private static final String SEP = System.getProperty("file.separator");
	
	public static WebDriver getChromeDriver() {

		String driverPath = user_dir + SEP + "lib" + SEP + "chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		Timer.delay(3);
		return driver;
	}
}
