package com.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Handling_Slider {
	private static Logger testLogger = LogManager.getLogger(Handling_Slider.class.getName());
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		try
		{
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
		driver.switchTo().frame(frame);
		testLogger.info("Switched to the frame");
		testLogger.info("---------------------------------");
		
		testLogger.info("Finding the sliders");
		WebElement slider_red = driver.findElement(By.id("red"));
		WebElement slider_green = driver.findElement(By.id("green"));
		WebElement slider_blue = driver.findElement(By.id("blue"));
		testLogger.info("Sliders found");
		
		Actions action = new Actions(driver);
		
		testLogger.info("Moving the red slider to the right by 50 pixels");
		action.dragAndDropBy(slider_red, 50, 0).perform();
		
		testLogger.info("Moving the green slider to the right by 100 pixels");
		action.dragAndDropBy(slider_green, 100, 0).perform();
		
		testLogger.info("Moving the blue slider to the left by 150 pixels");
		action.dragAndDropBy(slider_blue, 0, 150).perform();
	}

		 catch (Exception e) {
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

