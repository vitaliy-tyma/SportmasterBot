package com.sportmaster;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.sportmaster.model.Account;
import com.sportmaster.service.Service;

public class AppRunner {
	private static final String URL = "https://www.sportmaster.ru";
	private static String login = "1955aa@inbox.ru";
	private static String password = "123qwe";
	private static String good_id = "10295934";
	private static String shop_id = "????????";
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(AppRunner.class.getName());
		logger.log(Level.INFO, "********************Start********************");

		Account account = new Account(login, password);
		
		WebDriver driver = null;
		try {
			driver = Service.getChromeDriver();
			driver = Service.login(driver, URL, account);
			driver = Service.itemSearch(driver, good_id);
			
			
			
			driver.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "WebDriver error: " + e.getMessage());
		} finally {
			driver.quit();
		}

		logger.log(Level.INFO, "********************Finish********************");
	}
}
