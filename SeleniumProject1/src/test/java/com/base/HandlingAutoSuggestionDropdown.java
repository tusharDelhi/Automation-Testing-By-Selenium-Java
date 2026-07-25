package com.base;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingAutoSuggestionDropdown {

	private static Logger testLogger = LogManager.getLogger(HandlingAutoSuggestionDropdown.class);

	private static WebDriver driver;
	private static WebDriverWait wait;

	public static void main(String[] args) {

		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");

			testLogger.info("Launching Chrome in Incognito Mode");
			driver = new ChromeDriver(options);

			driver.manage().window().maximize();
			driver.get("https://www.flipkart.com/");

			wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Close login popup
			WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='✕']")));

			closePopup.click();
			testLogger.info("Login popup closed");

			// Search product
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("macbook pro");

			// Wait for suggestions
			By suggestionLocator = By.xpath("//input[@name='q']/ancestor::form//ul//a");

			wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionLocator));

			List<WebElement> suggestions = driver.findElements(suggestionLocator);

			testLogger.info("Total Suggestions: " + suggestions.size());

			for (WebElement suggestion : suggestions) {

				String productName = suggestion.getText().trim();
				testLogger.info("Suggestion: " + productName);

				if (productName.equalsIgnoreCase("macbook pro m4 pro")) {
					suggestion.click();
					testLogger.info("Clicked on: " + productName);
					break;
				}
			}

		} catch (Exception e) {
			testLogger.error("Test Failed", e);
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
}