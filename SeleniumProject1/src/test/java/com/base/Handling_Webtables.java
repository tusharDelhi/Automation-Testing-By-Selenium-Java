package com.base;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Handling_Webtables {
	private static Logger testLogger = LogManager.getLogger(Handling_Webtables.class.getName());
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		try {
			testLogger.info("Starting the test for Handling Webtables");
			// TODO Auto-generated method stub try {
			String url = "https://seleniumpractise.blogspot.com/2021/08/webtable-in-html.html";
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			testLogger.info("Opening the URL : " + url);
			driver.get(url);

			testLogger.info("Current URL : " + driver.getCurrentUrl());
			testLogger.info("Title       : " + driver.getTitle());
			
			testLogger.info("Finding all the headers in the webtable");
			List<WebElement> allHeaders = driver.findElements(By.xpath("//table[@name='cust_123']//th")); 			
			
			testLogger.info("Total number of headers found: " + allHeaders.size());
			
			testLogger.info("Printing all the headers:");
			for (WebElement header : allHeaders) {
				testLogger.info("Header: " + header.getText());
			}
			
			testLogger.info("Finding all the rows in the webtable");
			List<WebElement> allrows = driver.findElements(By.xpath("//table[@name='cust_123']//tr")); 
			
			testLogger.info("Total number of rows found: " + allrows.size());
			
			testLogger.info("Printing all the rows:");
			for(WebElement rows : allrows) {
				testLogger.info("Row: \t" + rows.getText());
			}
			
			
			
			
		} catch (Exception e) {
			testLogger.error("Test Failed", e);
		} finally {
			if (driver != null) {
				Thread.sleep(6000); // Wait for 2 seconds before closing the browser
				driver.quit();
				testLogger.info("Browser closed....");
			}
		}
	}
}


