package com.sportmaster.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sportmaster.model.Account;
import com.sportmaster.util.Timer;

public class Service {

	private static Logger logger = Logger.getLogger(Service.class.getName());



	//////////////////////////////////////////////////////////////////////////////
	public static WebDriver login(WebDriver driver, String url, Account account) {
		driver.get(url);
		Timer.delay(1);

		try {
			WebElement input_name_element = 
					driver.findElement(By.name("email"));
			input_name_element.sendKeys(account.getLogin());

			WebElement input_password_element = 
					driver.findElement(By.name("password"));
			input_password_element.sendKeys(account.getPassword());

			WebElement input_submit_element = 
					driver.findElement(By.id("submitButton"));
			input_submit_element.submit();
			
			logger.log(Level.INFO, "Submit button has been pressed.");

			String currentUrl = driver.getCurrentUrl();
			driver.get(currentUrl);
			Timer.delay(3);
			
			/* Check if proper user has been found.*/
			WebElement user_name_element = 
					driver.findElement(By.className("headerBar__username"));
			if (!user_name_element.getText().equals(account.getUser_name())) {
				return null;
			}
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, 
					"**********No Element has been found while login procedure.");
		}
		
		Timer.delay(6);
		return driver;
	}
	
	////////////////////////////////////////////////////////////////////////
	public static WebDriver itemSearch(WebDriver driver, String goodID) {
		try {

			/* Need to wait enough time.*/
			Timer.delay(6);
			WebElement input_search_element = 
					driver.findElement(By.className("smSearch__text"));
			input_search_element.clear();
			input_search_element.sendKeys(goodID);

			WebElement input_search_submit_element = 
					driver.findElement(By.cssSelector("input.smSearch__submit"));
			input_search_submit_element.click();
			
			logger.log(Level.INFO, "Search of " + goodID + " item was submitted.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "**********No Element has been found"
					+ " while item search procedure.");
		}
		Timer.delay(3);
		return driver;
	}

	////////////////////////////////////////////////////////////////////////
	public static WebDriver itemUrlExtractAndOpen(WebDriver driver) {
		try {

			WebElement item_url_element = 
					driver.findElement(By.className("sm-category__item"));
			WebElement item_url_element1 = 
					item_url_element.findElement(By.tagName("a"));
			String itemUrl = item_url_element1.getAttribute("href");

			logger.log(Level.INFO, "item_url = " + itemUrl);
			driver.get(itemUrl);
			
			logger.log(Level.INFO, "Item was opened.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "**********No Element has been found "
					+ "while item url extraction procedure.");
		}
		Timer.delay(3);
		return driver;
	}

}
