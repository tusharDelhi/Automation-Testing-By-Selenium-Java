package com.base;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class HandlingDropdowns {

	private static Logger testLogger = LogManager.getLogger(FirstTestCase.class.getName());
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// TODO Aut
		try {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);

			testLogger.info("Opening the Saucedemo");
			driver.get("https://saucedemo.com");

			testLogger.info("Current URL : " + driver.getCurrentUrl());
			testLogger.info("Title       : " + driver.getTitle());
			driver.manage().window().maximize();

			// Actual operations
			driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");

			// Demo WebElement methods on password field BEFORE navigating away

			// Login button called from main
			driver.findElement(By.xpath("//input[@id='login-button']")).click();
			testLogger.info("Logged into SauceDemo");

			List<WebElement> inventoryItems = driver.findElements(By.xpath("//div[@class='inventory_list']/div"));
			testLogger.info("Inventory item count: " + inventoryItems.size());

			// Handling Dropdowns
			testLogger.info("going to dropdown");
			// Select select = new Select(dropdown);

			// Getting all the options of the dropdown
			List<WebElement> alloptions = getSelectedValue().getOptions();
			for (WebElement option : alloptions) {
				testLogger.info("options are :" + option.getText());
				// testLogger.info("Shadow root :" +option.getShadowRoot());
			}

			List<WebElement> getAlloption = getSelectedValue().getAllSelectedOptions();
			testLogger.info("All selected options are :" + getAlloption);

			String firstoption = getSelectedValue().getFirstSelectedOption().getAccessibleName();
			testLogger.info("First  option is :" + firstoption);

			int itemsSize = alloptions.size();
			testLogger.info("Size of the options :" + itemsSize);

			testLogger.info("---------------------Clicking the dropdown-----------");
			Select s = getSelectedValue();
			s.getWrappedElement().click();
			s.selectByValue("az");
			String selectedFirstOption = getSelectedValue().getFirstSelectedOption().getText();
			testLogger.info("Selected value is " + selectedFirstOption);

			Thread.sleep(1000);

			s = getSelectedValue();
			s.selectByValue("za");
			String selectedsecondOption = getSelectedValue().getFirstSelectedOption().getText();
			testLogger.info("Selected value is " + selectedsecondOption);

		} catch (Exception e) {
			testLogger.error("Exception caught: " + e.getMessage());
			testLogger.error("stack trace", e);

		} finally {
			if (driver != null) {
				Thread.sleep(5000);
				testLogger.info("-----Browser is closing---------");
				driver.quit();
				testLogger.info("Browser closed ");
			}
		}
	}

	public static Select getSelectedValue() {
		return new Select(driver.findElement(By.className("product_sort_container")));

	}
}
