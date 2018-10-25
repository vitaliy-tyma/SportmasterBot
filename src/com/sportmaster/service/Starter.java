package com.sportmaster.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.sportmaster.model.Account;
import com.sportmaster.model.Good;
import com.sportmaster.model.Order;

public class Starter {
	static Logger logger = Logger.getLogger(Starter.class.getName());

	public static Order process(Account account, String url, String goodID, String shopID, String user) {
	
	WebDriver driver = null;
	Order order = new Order();
	try {
		driver = chromeDriver.getChromeDriver();

		/*
		 * TBD From the main page get the URL of login.
		 * 
		 * Now - user must be registered in advance!
		 */
		
		driver = Service.login(driver, url, account);

		driver = Service.itemSearch(driver, goodID);

		if (!Service.happyPathCheck(driver)) {
			/*
			 * Deviation: incomplete articleID
			 * 
			 * Open page for the good selection (and do not forget to choose size and color)
			 */
			driver = Service.itemUrlExtractAndOpen(driver);
		}
		
		// Happy path: correct articleID - good's page has been opened
		// No need to extract URL
		Good good = new Good();
		driver = Service.setGood(driver, goodID, good);

		
		
		// Choose color?!!!!!!!!!!!!!

		driver = Service.pressBuyButton(driver, goodID, good);
		//
	
		
		
		//Make order
		order.setGoodID(goodID);
		order.setGood(good);
		//order.setOrderID(orderID);
		order.setShopID(shopID);
		order.setUser(user);
		
		
		
		driver.close();
	} catch (Exception e) {
		logger.log(Level.SEVERE, "**************** WebDriver error: " + e.getMessage());
	} finally {
		driver.quit();
	}
	
	return order;
}
}
