package com.sportmaster.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sportmaster.model.Account;
import com.sportmaster.model.Good;
import com.sportmaster.model.Shop;
import com.sportmaster.util.Timer;
import com.sportmaster.service.NoShopsWithGoodException;

public class Service {
	/*
	 * TBD
	 * 
	 * Before each selector usage it is necessary to search through the whole HTML
	 * if this locator is unique!
	 */

	private static Logger logger = Logger.getLogger(Service.class.getName());

	//////////////////////////////////////////////////////////////////////////////
	public static WebDriver login(WebDriver driver, String url, Account account) {
		driver.get(url);
		Timer.delay(1);

		// Check connection

		try {
			WebElement inputNameElement = driver.findElement(By.name("email"));
			inputNameElement.sendKeys(account.getLogin());

			WebElement inputPasswordElement = driver.findElement(By.name("password"));
			inputPasswordElement.sendKeys(account.getPassword());

			WebElement inputSubmitElement = driver.findElement(By.id("submitButton"));
			inputSubmitElement.submit();
			logger.log(Level.INFO, "Login button has been pressed.");

			String currentUrl = driver.getCurrentUrl();
			driver.get(currentUrl);
			Timer.delay(6);
			logger.log(Level.INFO, "Checking user.");

			/* Check if proper user has been found. */
			WebElement userNameElement = driver.findElement(By.className("headerBar__username"));
			if (!userNameElement.getText().equals(account.getUserName())) {
				logger.log(Level.SEVERE, "User " + account.getUserName() + " was not found.");
				return null;
			}
			logger.log(Level.INFO, "User " + account.getUserName() + " was succesfully found.");

		} catch (NoSuchElementException e) {
			logger.log(Level.SEVERE, "********** No Element has been found while login procedure.");
		}

		Timer.delay(6);
		return driver;
	}

	////////////////////////////////////////////////////////////////////////
	public static WebDriver itemSearch(WebDriver driver, String goodID) {
		try {

			/* Need to wait enough time. */
			Timer.delay(6);
			/* Better to implement wait sequence. */

			WebElement inputSearchElement = driver.findElement(By.className("smSearch__text"));
			inputSearchElement.clear();
			inputSearchElement.sendKeys(goodID);
			logger.log(Level.INFO, "Search field was detected.");

			WebElement inputSearchSubmitElement = driver.findElement(By.cssSelector("input.smSearch__submit"));
			inputSearchSubmitElement.click();

			logger.log(Level.INFO, "Search of [" + goodID + "] item was submitted.");
		} catch (NoSuchElementException e) {
			logger.log(Level.SEVERE, "**********No Element has been found while item search procedure.");
		}
		Timer.delay(3);
		return driver;
	}

	////////////////////////////////////////////////////////////////////////
	public static Boolean happyPathCheck(WebDriver driver) {
		/** If we do not see any "sm-category__main" Element - we use happy path. */
		Timer.delay(3);
		if (driver.findElements(By.className("sm-category__main")).size() == 0) {
			logger.log(Level.INFO, "********** HappyPath **********");
			return Boolean.TRUE;
		} else {
			logger.log(Level.INFO, "********** No HappyPath **********");
			return Boolean.FALSE;
		}
	}

	////////////////////////////////////////////////////////////////////////
	public static WebDriver itemUrlExtractAndOpen(WebDriver driver) {
		try {
			WebElement itemUrlElement = driver.findElement(By.className("sm-category__main"));
			WebElement itemUrlElement1 = itemUrlElement.findElement(By.tagName("a"));
			String itemUrl = itemUrlElement1.getAttribute("href");

			logger.log(Level.INFO, "**************** itemUrl = " + itemUrl);
			driver.get(itemUrl);

			logger.log(Level.INFO, "**************** Item was opened.");

		} catch (NoSuchElementException e) {
			logger.log(Level.SEVERE, "**********No Element has been found while item url extraction procedure.");
		}
		Timer.delay(3);
		return driver;
	}

	////////////////////////////////////////////////////////////////////////
	public static WebDriver setGood(WebDriver driver, String goodID, Good good) {
		try {

			WebElement itemNameElement = driver.findElement(By.className("sm-goods_main_details"));
			WebElement itemName = itemNameElement.findElement(By.cssSelector("h1[data-selenium='product_name'"));
			String goodName = itemName.getText();
			good.setName(goodName);

			WebElement itemPriceElement = driver.findElement(By.className("sm-goods__mainprice-wrap"));
			WebElement itemPrice = itemPriceElement.findElement(By.cssSelector("div[data-selenium='product_price'"));
			String goodPrice = itemPrice.getText().replaceAll("[^0-9]", "");
			good.setPrice(goodPrice);

			/*
			 * TBD
			 * 
			 * Set other fields of the good!!!
			 * 
			 * 
			 */

			good.setGoodID(goodID);

			logger.log(Level.INFO, "Good Name (POJO) has been set = " + goodName);
			logger.log(Level.INFO, "Good Price (POJO) has been set = " + goodPrice);
			logger.log(Level.INFO, "Good ID (POJO) has been set = " + goodID);
		} catch (NoSuchElementException e) {
			logger.log(Level.SEVERE, "**********No Element has been found while good setting procedure.");
		}
		Timer.delay(3);
		return driver;
	}

	////////////////////////////////////////////////////////////////////////
	public static WebDriver pressBuyButton(WebDriver driver, String goodID, Good good) {
		try {

			// .Service.css=.sm-goods_main_details_buy-straight
			WebElement itemBuyElement = driver.findElement(By.className("sm-goods_main_details_buttons"));
			WebElement itemBuy = itemBuyElement.findElement(By.className("sm-goods_main_details_buy-straight"));
			itemBuy.click();
			logger.log(Level.INFO, "Buy Button has been pressed.");

			// div id="basketMessage"
			// data-selenium="go_to_basket"
			WebElement itemUrlElement = driver.findElement(By.id("basketMessage"));
			WebElement itemUrlElement1 = itemUrlElement.findElement(By.cssSelector("a[data-selenium='go_to_basket'"));
			String itemUrl = itemUrlElement1.getAttribute("href");
			logger.log(Level.INFO, "itemUrl1 = " + itemUrl);

			// Press go to basket
			logger.log(Level.INFO, "Go to basket has been pressed.");
			driver.get(itemUrl);

			//
			// WHAT TO DO NEXT??????
			//

		} catch (NoSuchElementException e) {
			logger.log(Level.SEVERE, "**********No Buy Button has been found while buying procedure.");
		}
		Timer.delay(3);
		return driver;
	}

	////////////////////////////////////////////////////////////////////////
	// GET FIRST SHOP WHERE GOOD IS PRESENT
	//OUTDATED
	public static Shop clarifyGoodPresence(WebDriver driver, Good good) throws NoShopsWithGoodException {
		Shop shopFirst = new Shop();
		try {

			// class="sm-goods_tabs_block"
			// li data-selenium="product_tab"
			WebElement itemClarifyElement = driver.findElement(By.className("sm-goods_tabs_block"));
			List<WebElement> itemClarifyList = itemClarifyElement
					.findElements(By.cssSelector("li[data-selenium='product_tab'"));

			for (WebElement element : itemClarifyList) {
				if (element.getAttribute("data-bind").contains("availability")) {
					element.click();
					logger.log(Level.INFO, "Check availability Button has been pressed.");
					break;
				}
			}
			Timer.delay(10);

			// class="sm-goods_tabs_presence-availability clearfix"
			// value="available"
			WebElement itemTabsPesence = driver.findElement(By.className("sm-goods_tabs_presence-availability"));
			List<WebElement> itemTabsList = itemTabsPesence.findElements(By.cssSelector("input"));

			for (WebElement element : itemTabsList) {
				if (element.getAttribute("value").contains("available")) {
					element.click();
					logger.log(Level.INFO, "Available radio Button has been pressed.");
					break;
				}
			}
			Timer.delay(5);

			// table
			// class="ccrests__table"
			WebElement itemTableElement = driver.findElement(By.className("ccrests__table"));
			// logger.log(Level.INFO, "!!!! itemTableElement = " +
			// itemTableElement.getAttribute("innerHTML"));

			List<WebElement> itemTableRowsList = itemTableElement.findElements(By.cssSelector("tr"));

			// Check if list is not empty - otherwise fail with the shop!!!!
			if (itemTableRowsList.size() == 0) {
				throw new NoShopsWithGoodException();
			}
			
			/**
			 * FURTHER NOT WORK!!! PLEASE STOP HERE!!!
			 */

			Timer.delay(5);
			for (WebElement element : itemTableRowsList) {
				List<WebElement> itemTDList = element.findElements(By.tagName("td"));
				logger.log(Level.INFO, "!!!! element innerHTML = " + element.getAttribute("innerHTML"));

				logger.log(Level.INFO, "!!!! element outerHTML = " + element.getAttribute("outerHTML"));

				for (WebElement el : itemTDList) {

					logger.log(Level.INFO, "!!!! element = " + el.getAttribute("innerHTML"));
					logger.log(Level.INFO, "!!!! element = " + el.getAttribute("innerHTML"));

					WebElement shopAddressElement = el.findElement(By.className("ccrests__table__column-address"));
					WebElement shopAddressElement1 = el.findElement(By.className("ccrests__store-address"));

					logger.log(Level.INFO, "Element2 = " + shopAddressElement.toString());
					logger.log(Level.INFO, "Element2 = " + shopAddressElement1.getText());

				}
				// class="ccrests__table__column-address"
				// a
				// class="ccrests__store-address"

				// class="ccrests__table__column-metro ccrests__store-metro"
				// span
				WebElement shopMetroElement = element.findElement(By.className("ccrests__table__column-metro"));
				logger.log(Level.INFO,
						"!!!! shopMetroElement innerHTML = " + shopMetroElement.getAttribute("innerHTML"));
				logger.log(Level.INFO,
						"!!!! shopMetroElement outerHTML = " + shopMetroElement.getAttribute("outerHTML"));

				WebElement shopMetro = shopMetroElement.findElement(By.cssSelector("span"));

				// WebElement shopAddressElement1 =
				// shopAddressElement.findElement(By.cssSelector("a"));

				// shopFirst.setShopAddress(shopAddressElement1.getText());
				shopFirst.setShopMetro(shopMetro.getText());
				////////////////////////////////////////////////////////////////
				shopFirst.setShopID("&&&&&ID");/////////////////////////////////

				logger.log(Level.INFO, "----------------Shop (First in row) has been created.");
			}

		} catch (NoSuchElementException e) {
			logger.log(Level.SEVERE, "**********No Shop has been found while buying procedure.");
		}
		Timer.delay(3);
		return shopFirst;
	}

	// TO BE DELETED!!!!
	//GARBAGE
	///////////////////////////////////////////////////////
	// logger.log(Level.INFO, "input_name_element = " +
	// input_name_element.getAttribute("innerHTML"));

	// Better to search through whole page
	// WebElement choiceElement =
	// driver.findElement(By.className("sm-category__main"));
	// Now we use exception call to search for the element presence...
	// try {
	// } catch (NoSuchElementException e) {
	// logger.log(Level.INFO,
	// "********** No sm-category__main Element has been found while happyPath
	// checking procedure.");

}
