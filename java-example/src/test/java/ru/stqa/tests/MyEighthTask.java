package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;

/**
 * Created by Vladimir on 26.01.2017.
 */
public class MyEighthTask extends TestBase {
    @Test
    public void myEighthTask() throws Exception {
        String locatorProduct ="[class=\"product column shadow hover-light\"]";
        String locatorStiker ="[class^=\"sticker\"]";
        List<WebElement> listProduct;

        // "регистрация"
        driver.get("http://localhost/litecart/");

        listProduct = driver.findElements(By.cssSelector(locatorProduct));

        int index = listProduct.size();
        for (int i = 0; i < index; i++) {
            boolean isNotStiker = isElementNotPresent(listProduct.get(i),cssSelector(locatorStiker));
            // Если стикера нет то
            assertFalse(isNotStiker);
                    if (!isNotStiker){
                        //проверка условия "...имеется ровно один стикер."
                        int oneStiker = listProduct.get(i).findElements(By.cssSelector(locatorStiker)).size();
                        assertTrue(oneStiker==1);
                    };

        }
    }

}
