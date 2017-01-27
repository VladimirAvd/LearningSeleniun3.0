package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

/**
 * Created by Vladimir on 27.01.2017.
 */
public class MyTwelfthTask extends TestBase {

    private void  regAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(cssSelector("div#box-login [name=username]")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login [name=password")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login button[name=login]")).click();
    }

    private String  insertGeneral(){
        List<WebElement> menuRows;
        String namePD = "NewTestProd7";
// Заполнение General
        driver.findElements(By.cssSelector("#tab-general label>input")).get(0).click();
        driver.findElements(By.cssSelector(".input-wrapper>input")).get(0).sendKeys(namePD);
        driver.findElement(By.name("code")).sendKeys("911");
        menuRows = driver.findElements(By.name("categories[]"));
        //Обход бага
        menuRows.get(0).click();
        for (int j = 0; j <menuRows.size() ;j++){ menuRows.get(j).click();
        }
        Select selectSize = new Select(driver.findElement(By.name("default_category_id")));
        selectSize.selectByIndex(0);

        menuRows = driver.findElements(By.name("product_groups[]"));
        //   menuRows.get(0).click();
        for (int j = 0; j <menuRows.size() ;j++){ menuRows.get(j).click();
        }
        for (int j = 0; j <4 ;j++){driver.findElement(By.name("quantity")).sendKeys(Keys.ARROW_UP);
        }
        for (int j = 0; j <3 ;j++){driver.findElement(By.name("quantity")).sendKeys(Keys.ARROW_DOWN);
        }

        selectSize = new Select(driver.findElement(By.name("quantity_unit_id")));
        selectSize.selectByIndex(1);

        selectSize = new Select(driver.findElement(By.name("delivery_status_id")));
        selectSize.selectByIndex(1);
        //sold_out_status_id

        selectSize = new Select(driver.findElement(By.name("sold_out_status_id")));
        selectSize.selectByIndex(2);

        driver.findElement(By.name("new_images[]")).sendKeys("C:\\Temp\\zag1.JPG");
        driver.findElement(By.cssSelector("#add-new-image")).click();
        isElementPresent(By.name("new_images[]"));
        driver.findElements(By.name("new_images[]")).get(1).sendKeys("C:\\Temp\\zag2.JPG");

        driver.findElement(By.name("date_valid_from")).sendKeys("11112011"+Keys.ARROW_UP);
        driver.findElement(By.name("date_valid_to")).sendKeys("11112016"+Keys.ARROW_UP);
 //       driver.findElement(By.name("save")).click();
        return namePD;
    }
    private void  insertInformation(){
        List<WebElement> menuRows;
        Select selectSize = new Select(driver.findElement(By.name("manufacturer_id")));
        selectSize.selectByIndex(1);

        selectSize = new Select(driver.findElement(By.name("supplier_id")));
        selectSize.selectByIndex(0);
        driver.findElement(By.name("keywords")).sendKeys("Testgbr-1");
        driver.findElement(By.name("short_description[en]")).sendKeys("Testgbr-2");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Testgbr-3");
        driver.findElement(By.name("head_title[en]")).sendKeys("Testgbr-4");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Testgbr-5");
    }

    private void  insertPrices(){
        driver.findElement(By.name("purchase_price")).sendKeys("2");
        Select  selectSize = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        selectSize.selectByIndex(1);

        selectSize = new Select(driver.findElement(By.name("tax_class_id")));
        selectSize.selectByIndex(0);

        driver.findElement(By.name("prices[USD]")).sendKeys("21");

        driver.findElement(By.name("prices[EUR]")).sendKeys("13");


        driver.findElement(By.cssSelector("#add-campaign")).click();

        driver.findElement(By.name("campaigns[new_1][start_date]")).sendKeys("11112016"+Keys.ARROW_RIGHT+ "0815");
        driver.findElement(By.name("campaigns[new_1][end_date]")).sendKeys("11112017"+Keys.ARROW_RIGHT+ "1315");
        driver.findElement(By.name("campaigns[new_1][percentage]")).click();
        driver.findElement(By.name("campaigns[new_1][percentage]")).sendKeys("2");
        //Хотя даты почему то не сохраняет в принципе даже при вводе вручную - м.б. это и баг ...
   }

    @Test
    public void myTwelfthTask() throws Exception {
        List<WebElement> menuRows;
        regAdmin();

        driver.get("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product");
// Заполнение General
        String nameProd = insertGeneral();

        driver.findElements(By.cssSelector(".index li")).get(1).click();
        insertInformation();

        driver.findElements(By.cssSelector(".index li")).get(3).click();
        insertPrices();

    driver.findElement(By.name("save")).click();

        menuRows = driver.findElements(xpath(".//*[@class='dataTable']//*[@class='row']"));

String str;
 boolean flag = false;
// Упрощенная проверка того что созданный товар присутствует в списке
        for (int j = 1; j <menuRows.size() ;j++){
            // Прокликиваем ТОЛЬКО активные товары
            if (isElementNotPresent(menuRows.get(j), By.cssSelector("[class^=\"fa fa-f\"]"))) {
                str= menuRows.get(j).findElement(xpath(".//td[3]/a")).getAttribute("textContent");
 //               System.out.println("=====  " + str);
                if (str.compareTo(nameProd)==0){
                    flag = true;
                    break;
                }
            }
        }
        assertTrue(flag);
        int countMessage;
    }
}
