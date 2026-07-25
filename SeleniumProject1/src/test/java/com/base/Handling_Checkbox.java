package com.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Handling_Checkbox {

	// Use this class' name for logging
	private static Logger testLogger = LogManager.getLogger(Handling_Checkbox.class.getName());
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		String url = "https://demoqa.com/automation-practice-form";
		try {
			testLogger.info("Launching Chrome in incognito mode");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			testLogger.info("Navigating to the URL");
			driver.get(url);
			testLogger.info("Current URL : " + driver.getCurrentUrl());

			// Use an explicit wait to ensure the checkbox/label is present and clickable
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Corrected XPath: the element is a <label> (not "lab"). We target the label for the 'Sports' checkbox.
			By sportsLabel = By.xpath("//label[@for='hobbies-checkbox-1']");
			WebElement sportLabelElem = wait.until(ExpectedConditions.elementToBeClickable(sportsLabel));
			sportLabelElem.click();

			// Verify the underlying input is selected
			WebElement sportInput = driver.findElement(By.id("hobbies-checkbox-1"));
			testLogger.info("Checkbox 'Sports' selected: " + sportInput.isSelected());

		} catch (Exception e) {
			testLogger.error("exception occurred: " + e.getMessage(), e);
		} finally {
			if (driver != null) {
				Thread.sleep(3000);
				driver.quit();
				testLogger.info("Browser closed successfully.");
			}
		}

	}
}
