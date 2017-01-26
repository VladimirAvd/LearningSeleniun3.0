package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;

/**
 * Created by Vladimir on 26.01.2017.
 */
public class MySeventhTask extends TestBase {
    private void  regAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(cssSelector("div#box-login [name=username]")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login [name=password")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login button[name=login]")).click();
    }

    @Test
    public void SeventhTask() throws Exception {

        String locatorMainMenu ="#box-apps-menu-wrapper #app-";
        String locatorSubMenu ="ul.docs [href^=http]";
        List<WebElement> manuRows;

        // регистрация
        regAdmin();

        manuRows = driver.findElements(By.cssSelector(locatorMainMenu));
        int index = manuRows.size();
        for (int i = 0; i < index; i++) {
            // проход основного меню
            manuRows.get(i).click();
            assertTrue(isElementPresent(By.cssSelector("h1")));
            if (!isElementNotPresent(By.cssSelector(locatorSubMenu))){
                manuRows = driver.findElements(By.cssSelector(locatorSubMenu));
                int subind = manuRows.size();
                for (int j = 0; j < subind; j++) {
                    // проход подменю
                    manuRows.get(j).click();
                    assertTrue(isElementPresent(By.cssSelector("h1")));
                    manuRows = driver.findElements(By.cssSelector(locatorSubMenu));
                }
            }
            manuRows = driver.findElements(By.cssSelector(locatorMainMenu));
        }
    }
}
