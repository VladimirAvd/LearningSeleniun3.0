package ru.stqa.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.tests.pages.MyPage;

/**
 * Created by Vladimir on 29.01.2017.
 */
public class MyBasketPage extends MyPage {

    public MyBasketPage(WebDriver driver) {super(driver);}
   // public void open (){driver.get("http://localhost/litecart/");}

    public By shortcuts (){
        return By.cssSelector("#box-checkout-cart .shortcuts");
    }

    public WebElement elementShortcut(){
        return  driver.findElements(By.cssSelector("#box-checkout-cart .shortcuts>li>a")).get(0);
    }
    public WebElement removeButton(){
        return driver.findElements(By.cssSelector(".item button[name=remove_cart_item]")).get(0);
    }

    public WebElement logotype(){
        return driver.findElement(By.cssSelector("#logotype-wrapper>a>img"));
        }

}
