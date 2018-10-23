package com.sportmaster;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.sportmaster.model.Account;
import com.sportmaster.model.Good;
import com.sportmaster.service.Service;
import com.sportmaster.service.chromeDriver;
import com.sportmaster.util.Timer;

public class AppRunner {
	private static String url = "https://www.sportmaster.ru";
	private static String login = "1955aa@inbox.ru";
	private static String password = "123qwe";
	private static String userName = "Andrey";
	private static String goodID = "01371300S";
	private static String shopID = "????????";
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(AppRunner.class.getName());
		logger.log(Level.INFO, "********************Start********************");
		
		Account account = new Account(login, password, userName);
		
		WebDriver driver = null;
		try {
			driver = chromeDriver.getChromeDriver();
			
			url = "https://www.sportmaster.ru/user/session/login.do";
			driver = Service.login(driver, url, account);
			
			driver = Service.itemSearch(driver, goodID);
			
			driver = Service.itemUrlExtractAndOpen(driver);
	
			Good good = new Good();
			driver = Service.setGood(driver, good);
			
			driver.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "WebDriver error: " + e.getMessage());
		} finally {
			driver.quit();
		}

		logger.log(Level.INFO, "********************Finish********************");
	}
}
