package com.sportmaster.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CheckInternetConnection {
	public static boolean isInternetReachable(String urlToCheck) {
		Logger logger = Logger.getLogger(CheckInternetConnection.class.getName());
		
		try {
			URL url = new URL(urlToCheck);
			HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
			Object objData = urlConnect.getContent();
		} catch (IOException e) {
			//e.printStackTrace();
			logger.log(Level.SEVERE, "********************NO INTERNET CONNECTION********************");
			logger.log(Level.SEVERE, "*************************TERMINATING**************************");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
}
