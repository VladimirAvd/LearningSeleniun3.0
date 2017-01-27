package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

/**
 * Created by Vladimir on 27.01.2017.
 */
public class MyThirteenthTask extends TestBase {
    private void  addProd(){
        List<WebElement> menuRows;

        WebElement menuRow;
        //#box-campaigns img
        //#box-most-popular img
        driver.findElement(By.cssSelector("#box-most-popular img")).click();
        if (!isElementNotPresent(By.cssSelector(".information .options"))) {
            // Select (новый вариант)
            Select selectSize = new Select(driver.findElement(By.cssSelector(".information select")));
            selectSize.selectByIndex(1);
            // Select (Старый вариант)
//            driver.findElement(By.cssSelector(".information .options")).click();
//            menuRows = driver.findElements(By.cssSelector("[class=buy_now] option"));
//            // Выбор Small
//            menuRows.get(1).click();
//            driver.findElement(By.cssSelector(".information .options")).click();
        }
        // На всяк пожарный
        if (0 == driver.findElement(By.cssSelector(".quantity>input")).getAttribute("value").compareTo("0")){
            driver.findElement(By.cssSelector(".quantity>input")).sendKeys(Keys.ARROW_UP);
        }
        driver.findElement(By.cssSelector(".quantity>button")).click();
        String str = driver.findElement(By.cssSelector("#cart-wrapper .content .quantity")).getAttribute("textContent");
        int i = Integer.parseInt(str);
        i++;
        str = Integer.toString(i);
        WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
        wait.until(textToBe(By.cssSelector("#cart-wrapper .content .quantity"),str ));

        driver.findElement(By.cssSelector("#logotype-wrapper img")).click();
    }

    private void  specialDeleteProd(){
        List<WebElement> prodList;
        boolean flag;

        flag = isElementNotPresent(By.cssSelector("#box-checkout-cart .shortcuts"));

        do {
            prodList = driver.findElements(By.cssSelector("#box-checkout-cart .shortcuts>li>a"));
 //           System.out.println("G ->" + prodList.size());
            prodList.get(0).click();
            driver.findElements(By.cssSelector(".item button[name=remove_cart_item]")).get(0).click();

            WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
            wait.until(ExpectedConditions.stalenessOf(prodList.get(0)));
            flag = isElementNotPresent(By.cssSelector("#box-checkout-cart .shortcuts"));

        }while (!flag);

        driver.findElements(cssSelector(".item button[name=remove_cart_item]")).get(0).click();
        driver.findElement(cssSelector("#logotype-wrapper>a>img")).click();
    }


    @Test
    public void MyThirteenthTask() throws Exception{
        // Вход
        driver.get("http://localhost/litecart/");
        for (int i = 0; i < 3; i++) {
            addProd();
        }
    // Клик в корзну
    driver.findElement(By.cssSelector("#cart-wrapper .link")).click();
    // Очистка
    specialDeleteProd();
    }
}
