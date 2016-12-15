package ru.stqa.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Vladimir on 16.12.2016.
 */
public class MyFirstTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @Test
    public void myFirstTest() throws Exception {
        driver.get("https://www.google.ru/");
        driver.findElement(By.id("lst-ib")).sendKeys("software testing");
        driver.findElement(By.id("_fZl")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        driver = null;
    }
}