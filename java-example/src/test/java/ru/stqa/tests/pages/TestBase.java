package ru.stqa.tests.pages;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.tests.app.Application;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vladimir on 17.01.2017.
 */
public class TestBase {

    public WebDriver driver;
    public Application app;

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidElementStateException ex) {
            return false;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }

    public boolean isElementNotPresent(WebElement we, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
            return we.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }

    public boolean isElementNotPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }
    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void stop() {
//        driver.quit();
//        driver = null;
    }


}
