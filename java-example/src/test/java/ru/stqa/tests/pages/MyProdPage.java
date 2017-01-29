package ru.stqa.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Vladimir on 29.01.2017.
 */
public class MyProdPage extends MyPage {
    public MyProdPage(WebDriver driver) {super(driver);}
    public void open (){driver.get("http://localhost/litecart/");}
    public WebElement setProd (){
        return driver.findElement(By.cssSelector("#box-most-popular img"));
    }

    public By sizeProd (){
        return By.cssSelector(".information .options");
    }
    public By countProdBasket (){
        return By.cssSelector("#cart-wrapper .content .quantity");
        //By.cssSelector("#cart-wrapper .content .quantity"),str ));
    }

    public void setSize(int sizeProd){
            Select selectSize = new Select(driver.findElement(By.cssSelector(".information select")));
            selectSize.selectByIndex(1);
    }
    public int getNumberCountProd(){
        return driver.findElement(By.cssSelector(".quantity>input")).getAttribute("value").compareTo("0");
    }
    public void setNumberCountProd(int number){
        for (int i = 0; i < number; i++) {
            driver.findElement(By.cssSelector(".quantity>input")).sendKeys(Keys.ARROW_UP);
        }
    }

    public WebElement addButton (){
        return driver.findElement(By.cssSelector(".quantity>button"));
    }

    public WebElement logotype (){
        return driver.findElement(By.cssSelector("#logotype-wrapper img"));
    }

    public String getNumberProdInBasket(){
        return driver.findElement(By.cssSelector("#cart-wrapper .content .quantity")).getAttribute("textContent");
    }




}
