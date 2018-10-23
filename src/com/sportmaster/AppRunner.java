package com.sportmaster;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.sportmaster.model.Account;
import com.sportmaster.service.Service;
import com.sportmaster.util.Timer;

public class AppRunner {
	private static String url = "https://www.sportmaster.ru";
	private static String login = "1955aa@inbox.ru";
	private static String password = "123qwe";
	private static String user_name = "Andrey";
	private static String good_id = "10295934";
	private static String shop_id = "????????";
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(AppRunner.class.getName());
		logger.log(Level.INFO, "********************Start********************");
		
		Account account = new Account(login, password, user_name);
		
		WebDriver driver = null;
		try {
			driver = Service.getChromeDriver();
			
			url = "https://www.sportmaster.ru/user/session/login.do";
			driver = Service.login(driver, url, account);
			
			driver = Service.itemSearch(driver, good_id);
			
			driver = Service.itemUrlExtractAndOpen(driver);
	
			
			
			driver.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "WebDriver error: " + e.getMessage());
		} finally {
			driver.quit();
		}

		logger.log(Level.INFO, "********************Finish********************");
	}
}
