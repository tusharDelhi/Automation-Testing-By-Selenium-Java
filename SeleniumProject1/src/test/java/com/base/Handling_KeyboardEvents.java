package com.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Handling_KeyboardEvents {
	private static Logger testLogger = LogManager.getLogger(Handling_KeyboardEvents.class.getName());
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		try {
			// TODO Auto-generated method stub try {
			String url = "https://extendsclass.com/text-compare.html#google_vignette";
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			testLogger.info("Opening the URL : " + url);
			driver.get(url);

			testLogger.info("Current URL : " + driver.getCurrentUrl());
			testLogger.info("Title       : " + driver.getTitle());

			testLogger.info("Finding the source text area element");
			WebElement Source_textArea = driver.findElement(
					By.xpath("//*[@id=\"dropZone\"]/div[2]/div/div[6]/div[1]/div/div/div/div[5]/div[7]/pre/span"));

			Actions action = new Actions(driver);
			Thread.sleep(2000); // Wait for 2 seconds before performing the actions

			testLogger.info("Performing Ctrl+A and Ctrl+C on the source text area");
			action.keyDown(Source_textArea, Keys.CONTROL).sendKeys("a").sendKeys("c").build().perform();

			testLogger.info("Finding the destination text area element");
			WebElement Destination_textArea = driver.findElement(
					By.xpath("//*[@id=\"dropZone2\"]/div[2]/div/div[6]/div[1]/div/div/div/div[5]/div[4]/pre/span"));

			testLogger.info("Performed Ctrl+A and Ctrl+V on the source text area");

			action.keyDown(Destination_textArea, Keys.CONTROL).sendKeys("a").sendKeys("v").build().perform();

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
