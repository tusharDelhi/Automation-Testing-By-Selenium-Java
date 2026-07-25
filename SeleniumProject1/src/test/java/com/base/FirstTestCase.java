package com.base;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestCase {

    private static Logger testLogger = LogManager.getLogger(FirstTestCase.class.getName());
    private static WebDriver driver;

    public static void main(String[] args) {
        try {
            driver = new ChromeDriver();
            testLogger.info("Opening the UAT environment");
            driver.get("https://saucedemo.com");

            testLogger.info("Current URL : " + driver.getCurrentUrl());
            testLogger.info("Title       : " + driver.getTitle());
            driver.manage().window().maximize();

            // Actual operations
            driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");

            // Demo WebElement methods on password field BEFORE navigating away
            WebElementMethodsShow();

            // Login button called from main
            driver.findElement(By.xpath("//input[@id='login-button']")).click();
            testLogger.info("Logged into SauceDemo");

            List<WebElement> inventoryItems = driver.findElements(
                By.xpath("//div[@class='inventory_list']/div")
            );
            testLogger.info("Inventory item count: " + inventoryItems.size());

        } catch (Exception e) {
            testLogger.error("Exception caught: " + e.getMessage());
            testLogger.error(e.getStackTrace());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void WebElementMethodsShow() {

        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));

        System.out.println("========== WebElement Demo : Password Field ==========");

        // 1. getSize()
        Dimension size = passwordField.getSize();
        testLogger.info("getSize()              : Width=" + size.getWidth() + ", Height=" + size.getHeight());

        // 2. getLocation()
        Point location = passwordField.getLocation();
        testLogger.info("getLocation()          : X=" + location.getX() + ", Y=" + location.getY());

        // 3. getTagName()
        testLogger.info("getTagName()           : " + passwordField.getTagName());

        // 4. getAttribute()
        testLogger.info("getAttribute('id')     : " + passwordField.getAttribute("id"));
        testLogger.info("getAttribute('type')   : " + passwordField.getAttribute("type"));

        // 5. isDisplayed()
        testLogger.info("isDisplayed()          : " + passwordField.isDisplayed());

        // 6. isEnabled()
        testLogger.info("isEnabled()            : " + passwordField.isEnabled());

        // 7. isSelected()
        testLogger.info("isSelected()           : " + passwordField.isSelected());

        // 8. getCssValue()
        testLogger.info("getCssValue('color')   : " + passwordField.getCssValue("color"));

        // 9. getText()
        testLogger.info("getText()              : '" + passwordField.getText() + "'");

        System.out.println("======================================================");
    }
}