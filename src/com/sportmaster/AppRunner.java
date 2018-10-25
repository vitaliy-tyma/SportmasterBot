package com.sportmaster;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.sportmaster.model.Account;
import com.sportmaster.model.Good;
import com.sportmaster.model.Order;
import com.sportmaster.service.Service;
import com.sportmaster.service.Starter;
import com.sportmaster.service.chromeDriver;
import com.sportmaster.util.CheckInternetConnection;
import com.sportmaster.util.Timer;

public class AppRunner {
	//private static String url = "https://www.sportmaster.ru";
	private static String url = "https://www.sportmaster.ru/user/session/login.do";
	private static String login = "1955aa@inbox.ru";
	private static String password = "123qwe";
	private static String userName = "Andrey";
	
	private static String goodID = "01371300S";//Good with size
	//private static String goodID = "10295934";//List of goods - few - boundary condition 1
	//private static String goodID = "01371300S1";//Not found good - boundary condition 2
	private static String shopID = "????????";

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(AppRunner.class.getName());
		logger.log(Level.INFO, "********************Start********************");

		
		
		if (!CheckInternetConnection.isInternetReachable(url)) {
			return;
		}
		
		Account account = new Account(login, password, userName);

		Order order = Starter.process(account, url, goodID, shopID, login);
		
		logger.log(Level.INFO, "********************Result********************");
		logger.log(Level.INFO, "*[" + order.toString() + "]*");
		
		
		logger.log(Level.INFO, "********************Finish********************");
	}
}
