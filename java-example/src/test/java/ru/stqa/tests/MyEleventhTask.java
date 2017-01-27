package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vladimir on 27.01.2017.
 */
public class MyEleventhTask extends TestBase {
    private  void logout(){
        List<WebElement> menuRows;
        // ===== Logout=========
        menuRows = driver.findElements(By.cssSelector("#box-account [class=list-vertical]>li"));
        String link = menuRows.get(3).findElement(By.cssSelector("a")).getAttribute("textContent");
        // На случай если "кнопка" "переместится"(или переименуется и переместся) и т.д. -нет надежного локатора
        assertTrue(link.compareTo("Logout") ==0);
        menuRows.get(3).findElement(By.cssSelector("a")).click();
    }
    @Test
    public void myEleventhTask() throws Exception {
        List<WebElement> formBox;
        List<WebElement> menuRows;
        WebElement menuRow;
        String label = "7654321";
        String mail =  "avd"+label+"@mailtest";

        driver.get("http://localhost/litecart/");
        // Открыть форму регистрации
        driver.findElement(By.cssSelector("#box-account-login a")).click();

        // -------Стандартное заполнение хламом
        formBox = driver.findElements(By.cssSelector(".content>form td>input"));
        int index = formBox.size();
        for (int i = 1; i < 8; i++) {
            formBox.get(i).sendKeys("test"+i);
        }
        for (int i = 10; i < index; i++) {
            formBox.get(i).sendKeys(label);

        }
        formBox.get(9).sendKeys(mail);
        formBox.get(6).clear();
        formBox.get(6).sendKeys("2110");

        // Заполнение скрытого элемента с выбором Portugal
        menuRow = driver.findElement(By.xpath(".//*[@id='create-account']//tr[5]/td[1]"));
        Actions builder = new Actions(driver);
        builder
                .moveToElement(menuRow)
                .click()
                .sendKeys("Portu"+ Keys.ENTER)
                .perform();

        menuRow = driver.findElement(By.cssSelector(".content>form button"));
        menuRow.click();
 //  ===== Logout=========
       logout();
// ============== login ======================

        driver.findElement(By.cssSelector("#box-account-login [name=email]")).sendKeys(mail);;
        driver.findElement(By.cssSelector("#box-account-login [name=password]")).sendKeys(label+ Keys.ENTER);

        // Проверка успешного логина
        assertFalse(isElementNotPresent(By.cssSelector("#box-account")));


        // ===== Logout=========
        logout();



    }

}
