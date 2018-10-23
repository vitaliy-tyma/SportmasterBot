package com.sportmaster.service;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sportmaster.model.Account;
import com.sportmaster.util.Timer;

public class Service {
	private static String user_dir = System.getProperty("user.dir");
	private static final String SEP = System.getProperty("file.separator");
	private static Logger logger = Logger.getLogger(Service.class.getName());

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

	public static WebDriver login(WebDriver driver, String url, Account account) {

		url = "https://www.sportmaster.ru/user/session/login.do";
		driver.get(url);
		Timer.delay(1);

		/*
		 * boolean signed_in =
		 * driver.getPageSource().contains("user/session/logout.do"); if (signed_in) {
		 * logger.log(Level.SEVERE,
		 * "User is logged in! Need to check who am I and relogin if necessary"); }
		 * logger.log(Level.INFO, "No logged user - need to login.");
		 * 
		 * WebElement login_div_element =
		 * driver.findElement(By.className("pdAll-10"));//pressMe__button
		 * logger.log(Level.INFO, "login_div_element = " + login_div_element.getText());
		 * 
		 * //data-bind="visible: !isAuthenticated()" //WebElement
		 * login_data_bind_element =
		 * login_div_element.findElement(By.tagName("data-bind")); //String
		 * login_is_auth = login_div_element.getAttribute("data-bind");
		 * 
		 * WebElement element1 = login_div_element.findElement(By.
		 * cssSelector("div.data-bind=visible: !isAuthenticated()"));
		 * 
		 * 
		 * logger.log(Level.INFO, "element1 = " + element1); WebElement
		 * login_url_element = element1.findElement(By.tagName("a")); String login_url =
		 * login_url_element.getAttribute("href");
		 * 
		 * // logger.log(Level.INFO, "register_url = " + login_url);
		 * driver.get(login_url);
		 * 
		 */

		try {
			WebElement input_name_element = driver.findElement(By.name("email"));
			input_name_element.sendKeys(account.getLogin());

			WebElement input_password_element = driver.findElement(By.name("password"));
			input_password_element.sendKeys(account.getPassword());

			WebElement input_submit_element = driver.findElement(By.id("submitButton"));
			input_submit_element.submit();
			logger.log(Level.INFO, "submit button has been pressed.");

		} catch (Exception e) {
			logger.log(Level.SEVERE, "No Element - " + e.getMessage());
		}
		return driver;
	}

	public static WebDriver itemSearch(WebDriver driver, String good_id) {
		try {
			String currentUrl = driver.getCurrentUrl();
			driver.get(currentUrl);

			WebElement input_search_element = driver.findElement(By.className("smSearch__text"));
			input_search_element.clear();
			input_search_element.sendKeys(good_id);

			WebElement input_search_submit_element = driver.findElement(By.cssSelector("input.smSearch__submit"));
			input_search_submit_element.click();
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "No Element - " + e.getMessage());
		}
		return driver;
	}

}
