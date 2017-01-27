package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.cssSelector;

/**
 * Created by Vladimir on 27.01.2017.
 */
public class MyFourteenthTask extends TestBase {
    private void  regAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(cssSelector("div#box-login [name=username]")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login [name=password")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login button[name=login]")).click();
    }

    @Test
    public void myFourteenthTask() throws Exception {
        List<WebElement> menuRows;
        regAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        // Вызываем первую попавшуюся страну для редактирования
        driver.findElement(cssSelector(".fa.fa-pencil")).click();

        menuRows = driver.findElements(cssSelector(".fa.fa-external-link"));
        WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
        String mainWindow = driver.getWindowHandle();

        for (int j = 0; j <menuRows.size() ;j++){
            Set<String> oldWindows = driver.getWindowHandles();
            menuRows.get(j).click();

            String newWindow = wait.until(new ExpectedCondition<String>() {
                                              public String apply(WebDriver driver) {
                                                  Set<String> newWindowsSet = driver.getWindowHandles();
                                                  newWindowsSet.removeAll(oldWindows);
                                                  return newWindowsSet.size() > 0 ?
                                                          newWindowsSet.iterator().next() : null;
                                              }
                                          }
            );

            driver.switchTo().window(newWindow);
            // подождем загрузки страницы
            isElementPresent(By.cssSelector("title"));
            driver.close();
            driver.switchTo().window(mainWindow);
            //На всякий пожарный (хотя вроде работает и без "обновления")
            menuRows = driver.findElements(cssSelector(".fa.fa-external-link"));
        }
    }
}
