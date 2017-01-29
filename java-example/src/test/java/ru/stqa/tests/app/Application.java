package ru.stqa.tests.app;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.tests.pages.MyBasketPage;
import ru.stqa.tests.pages.MyProdPage;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

/**
 * Created by Vladimir on 29.01.2017.
 */
public class Application
{
    private WebDriver driver;
    private WebDriverWait wait;
    private MyProdPage myProdPage;
    private MyBasketPage myBasketPage;



    public Application (){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        myProdPage = new MyProdPage(driver);
        myBasketPage = new MyBasketPage(driver);
    }
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidElementStateException ex) {
            return false;
        } catch (java.util.NoSuchElementException ex) {
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

    public void  addProd(){
        List<WebElement> menuRows;

        WebElement menuRow;
        //#box-campaigns img
        //#box-most-popular img

        myProdPage.setProd().click();
        //Если товар имеет размер
        if (!isElementNotPresent(myProdPage.sizeProd())) {
            myProdPage.setSize(1);
        }
        // На всяк пожарный
        if (0 == myProdPage.getNumberCountProd()){
            myProdPage.setNumberCountProd(1);
        }

        myProdPage.addButton().click();
        // Получить текущее количество товаров в корзине
        int i = Integer.parseInt(myProdPage.getNumberProdInBasket());
        i++;
        String str = Integer.toString(i);
        // Подождать пока количество товара не изменится на искомое
        wait.until(textToBe(myProdPage.countProdBasket(),str));
        // вернуться в основное меню
        myProdPage.logotype().click();
    }

    public void  specialDeleteProd() {
        List<WebElement> listProd;
        boolean flag = false;
        // isElementPresent(myBasketPage.shortcuts());
        flag = areElementsPresent(myBasketPage.shortcuts());
        if (flag) {
            do {
                myBasketPage.elementShortcut().click();
                myBasketPage.removeButton().click();
                wait.until(ExpectedConditions.stalenessOf(myBasketPage.elementShortcut()));
                flag = isElementNotPresent(myBasketPage.shortcuts());

            } while (!flag);
        }
            myBasketPage.removeButton().click();
            myBasketPage.logotype().click();
    }

    public void klickBasketButton(){
        driver.findElement(By.cssSelector("#cart-wrapper .link")).click();
    }

    public void openApp(){
        driver.get("http://localhost/litecart/");
 //       driver.findElement(By.cssSelector("#cart-wrapper .link")).click();
    }
    public boolean basketIsEmpty (){
        return 0==myProdPage.getNumberProdInBasket().compareTo("0");
    }
    public void quit() {
        driver.quit();
    }

}
