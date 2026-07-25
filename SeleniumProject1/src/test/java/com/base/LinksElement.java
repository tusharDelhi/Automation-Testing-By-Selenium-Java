package com.base;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LinksElement {
	private static Logger testLogger = LogManager.getLogger(FirstTestCase.class.getName());
	private static WebDriver driver;

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		testLogger.info("Opening the filecr.com");
		driver.get("https://filecr.com/home/");
		
		 List<WebElement> dropdowns = driver.findElements(By.tagName("option"));
		int size = dropdowns.size();
		testLogger.info("size  of he options"+ size);

		List<WebElement> links = driver.findElements(By.tagName("a"));
		testLogger.info("total tag are " + links.size());
		// TODO Auto-generated method stub
		for (int i = 0; i < links.size(); i++) {
			
			testLogger.info("links are  on page: " + links.get(i).getAccessibleName());
			testLogger.info("links are  on page: " + links.get(i).getText());
			testLogger.info("attribute is " + links.get(i).getAttribute("href"));
		
		

		}
		
		driver.quit();

	}

}
