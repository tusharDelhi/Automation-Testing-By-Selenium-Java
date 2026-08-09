package com.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class RightClick_handling {
	private static Logger testLogger = LogManager.getLogger(RightClick_handling.class.getName());
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		try {
			// TODO Auto-generated method stub try {
			String url = "https://jqueryui.com/slider/#colorpicker";
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			testLogger.info("Opening the URL : " + url);
			driver.get(url);

			testLogger.info("Current URL : " + driver.getCurrentUrl());
			testLogger.info("Title       : " + driver.getTitle());

			WebElement frame = driver.findElement(By.className("demo-frame"));
			testLogger.info("Switching to the frame");
			testLogger.info("---------------------------------");

			driver.switchTo().frame(frame);
			testLogger.info("Switched to the frame");
			testLogger.info("---------------------------------");

			WebElement Swatch = driver.findElement(By.id("swatch"));

			Actions action = new Actions(driver);

			testLogger.info("Right clicking on the Swatch element");
			testLogger.info("---------------------------------");
			action.contextClick(Swatch).perform();
			driver.navigate().refresh();

			testLogger.info("Performed Right click on the Swatch element");

		} catch (Exception e) {
			testLogger.error("Test Failed", e);
		} finally {
			if (driver != null) {
				Thread.sleep(6000); // Wait for 2 seconds before closing the browser
				driver.quit();
				testLogger.info("Browser closed");
			}
		}
	}
}
