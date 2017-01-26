package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;

/**
 * Created by Vladimir on 26.01.2017.
 */
public class MyNinthTask extends TestBase {
    private void  regAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(cssSelector("div#box-login [name=username]")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login [name=password")).sendKeys("admin");
        driver.findElement(cssSelector("div#box-login button[name=login]")).click();
    }
    private String  getSelected(List<WebElement> wE){
        String result ="";
        for (int i = 0; i < wE.size(); i++) {
            String ss = wE.get(i).getAttribute("selected");
            if (ss!=null) {
                result = wE.get(i).getAttribute("textContent");
                break;
            }
        }
        return result;
    }

    @Test
    public void myNinthTaskPartOne() throws Exception {

        List<WebElement> menuRows;
        List<WebElement> subMenuRows;

        ArrayList<String> namesCountry = new ArrayList <String>();
        ArrayList<Integer> listCountryWithZone = new ArrayList<Integer>();
        String[] myArray = {};
        int numb = 0;


        // регистрация
        regAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        menuRows = driver.findElements(By.xpath(".//*[@class='dataTable']//tr"));

        // Не забыть!!! тут заголовок и ИТОГО
        int index = menuRows.size();

        int ii = 0;
        // Получение списка стран и списка "номеров" стран у которых зоны>0
        for (int i = 1; i < index-1; i++) {
          //  System.out.println(menuRows.get(i).findElement(By.xpath("./td[6]")).getAttribute("textContent"));
            namesCountry.add(i-1, menuRows.get(i).findElement(By.xpath("./td[5]")).getAttribute("textContent"));
            numb = Integer.parseInt(menuRows.get(i).findElement(By.xpath("./td[6]")).getAttribute("textContent"));
            if (numb!=0) {
                listCountryWithZone.add(ii, i-1);
                ii++;
            }
        }
        // Проверка алфавитного порядка
        myArray = namesCountry.toArray(new String[namesCountry.size()]);
        Arrays.sort(myArray);

        for (int i = 0; i < index-2; i++) {
            assertTrue(myArray[i].compareTo(namesCountry.get(i))==0);
        }

        // Проверка зон
        for (int j = 0; j < listCountryWithZone.size(); j++) {
            menuRows.get(listCountryWithZone.get(j)+1).findElement(By.xpath("./td[5]/a")).click();
            subMenuRows = driver.findElements(By.xpath(".//*[@id='table-zones']//td[3]"));

            int insb = subMenuRows.size();
            namesCountry.clear();
            for (int i = 0; i < insb-1; i++) {
                namesCountry.add(i, subMenuRows.get(i).getAttribute("textContent"));
            }
            // Проверка алфавитного порядка
            myArray = namesCountry.toArray(new String[namesCountry.size()]);
            Arrays.sort(myArray);

            for (int i = 0; i < insb-1; i++) {
                assertTrue(myArray[i].compareTo(namesCountry.get(i))==0);
            }
            driver.navigate().back();
            menuRows = driver.findElements(By.xpath(".//*[@class='dataTable']//tr"));
       }

    }

    @Test
    public void myNinthTaskPartTwo() throws Exception {
        // регистрация
        List<WebElement> menuRows;
        List<WebElement> subMenuRows;
        List<WebElement> menuElements;

        ArrayList<String> nameZone = new ArrayList <String>();
        String[] myArray = {};

        regAdmin();
        driver.get(" http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        // Список стран
        menuRows = driver.findElements(By.xpath(".//*[@id='content']//td[3]/a"));
        for (int i = 0; i < menuRows.size(); i++) {
            menuRows.get(i).click();
            nameZone.clear();
            // Внутри страницы(страны)

            subMenuRows = driver.findElements(By.xpath(".//*[@id='table-zones']//td[3]/select"));
            int cZon = subMenuRows.size();

            for (int j = 0; j < cZon; j++) {
            //    System.out.println("===" + ee);
                nameZone.add(j, getSelected(subMenuRows.get(j).findElements(By.xpath("./option"))));
            }
            // проверка алфавитного порядка
            myArray = nameZone.toArray(new String[nameZone.size()]);
            Arrays.sort(myArray);

            for (int k = 0; k < nameZone.size(); k++) {
                assertTrue(myArray[k].compareTo(nameZone.get(k))==0);
            }
         //  Переход к следующей стране
            driver.findElement(By.cssSelector(".button-set>button[name=cancel]")).click();
            menuRows = driver.findElements(By.xpath(".//*[@id='content']//td[3]/a"));
       }

    }


}
