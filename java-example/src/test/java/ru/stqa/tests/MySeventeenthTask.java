package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

/**
 * Created by Vladimir on 27.01.2017.
 */
public class MySeventeenthTask extends TestBase {
    private void  regAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(cssSelector("div#box-login [name=username]")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login [name=password")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login button[name=login]")).click();
    }

    @Test
    public void mySeventeenthTask() throws Exception {
        List<WebElement> menuRows;
        regAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        int countMessage;

        menuRows = driver.findElements(xpath(".//*[@class='dataTable']//*[@class='row']"));

        for (int j = 1; j <menuRows.size() ;j++){
            // Прокликиваем ТОЛЬКО  товары
                if (isElementNotPresent(menuRows.get(j), By.cssSelector("[class^=\"fa fa-f\"]"))) {
                menuRows.get(j).findElement(xpath(".//td[3]/a")).click();
                // подождем загрузки страницы товара
                isElementPresent(By.cssSelector("h1"));

                driver.navigate().back();
                menuRows = driver.findElements(xpath(".//*[@class='dataTable']//*[@class='row']"));
            }
        }
        countMessage =0;

        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            // тут можно фильтровать любые "фильтры" для сообщений
            // например наличие в тексте сообщения [SEVERE] (или иных условий)
            countMessage++;
        }
        // Допустим у нас не должно быть сообщений в логе
       assertTrue(countMessage ==0);

    }
}
