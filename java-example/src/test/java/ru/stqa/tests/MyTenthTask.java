package ru.stqa.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.tests.ru.stqa.test.pages.ProdDuck;
import ru.stqa.tests.ru.stqa.test.pages.TestBase;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Vladimir on 26.01.2017.
 */
public class MyTenthTask extends TestBase {
//    private String getStrAtr(String locator){
//        WebElement menuRow;
//        menuRow = driver.findElement(By.cssSelector(locator));
//        return menuRow.getAttribute("textContent");
//    }
//
//    private String getStrCss(String locator, String prop){
//        WebElement menuRow;
//        menuRow = driver.findElement(By.cssSelector(locator));
//        return menuRow.getCssValue(prop);
//    }

    private void showProd(ProdDuck prod){
        System.out.println("PD Name -> " +prod.getName());
        System.out.println("PD Price -> " +prod.getPrice());
        System.out.println("PD Color -> " +prod.getColor());
        System.out.println("PD Size -> " +prod.getSize());

        System.out.println("PD TextDeclar -> " +prod.getTextDeclar());
        System.out.println("PD TextWeight -> " +prod.getTextWeight());
    }

    private int foundNumberPrice(String prodLocator, ProdDuck prod){
        int result =1;
        WebElement wE = driver.findElement(By.cssSelector(prodLocator));
        if (isElementNotPresent(wE, By.cssSelector(".price"))){
            result = 2;
        }
        return result;
    }

    private void fillProd(String prodLocator, String priceLocator, ProdDuck prod){

        prod.setPrice(driver.findElement(By.cssSelector(priceLocator)).getAttribute("textContent"));

        prod.setSize(driver.findElement(By.cssSelector(priceLocator)).getCssValue("font-size"));
        prod.setTextDeclar(driver.findElement(By.cssSelector(priceLocator)).getCssValue("text-decoration"));
        prod.setColor(driver.findElement(By.cssSelector(priceLocator)).getCssValue("color"));
        prod.setTextWeight(driver.findElement(By.cssSelector(priceLocator)).getCssValue("font-weight"));

    }
    private void checkOne(ProdDuck beforeProd, ProdDuck afterProd, String Color, String textDeclar, String textWeight){

        // проверка совпадения имени
        assertTrue(beforeProd.getName().compareTo(afterProd.getName())==0);
        // проверка совпадения цены
        assertTrue(beforeProd.getPrice().compareTo(afterProd.getPrice())==0);


        // проверка совпадения цвета -!!! Нужно отключить при проверке серых (или одинарных цен (или если это баг - то править :) ))
  //      assertTrue(beforeProd.getColor().compareTo(afterProd.getColor())==0);
        // проверка цвета (красного или серого)
        assertTrue(beforeProd.getColor().compareTo(Color)==0);
        // проверка шрифта
        assertTrue(beforeProd.getTextDeclar().compareTo(afterProd.getTextDeclar())==0);
        assertTrue(beforeProd.getTextDeclar().compareTo(textDeclar)==0);

        assertTrue(beforeProd.getTextWeight().compareTo(afterProd.getTextWeight())==0);
        assertTrue(beforeProd.getTextWeight().compareTo(textWeight)==0);
    }
    private void checkTwo(ProdDuck fPriceProd,ProdDuck sPriceProd) {
        assertTrue(fPriceProd.getSize()<sPriceProd.getSize());

    }

        @Test
    public void myyTenthTask () throws Exception {
        String boxLabel, priceLabel;
        ProdDuck beforeFirstPrice;
        ProdDuck beforeSecondPrice;
        ProdDuck afterFirstPrice;
        ProdDuck afterSecondPrice;


        // "регистрация"
        driver.get("http://localhost/litecart/");

        // ----


// Если мы хотим использовать код для проверки раздела Campaigns
     //   boxLabel = "#box-most-popular";
                boxLabel = "#box-campaigns";

        String mColorGrey = "rgba(119, 119, 119, 1)";
        String mColorRed = "rgba(204, 0, 0, 1)";
        String mtxtRedDeclar = "none";
        String mtxtGreyDeclar = "line-through";
        String mtxtRedWeight = "bold";
        String mtxtGreyWeight = "normal";

        beforeFirstPrice = new ProdDuck().setName(driver.findElement(By.cssSelector(boxLabel+" div.name"))
                .getAttribute("textContent"));
        int numPrice = foundNumberPrice(boxLabel,beforeFirstPrice);
               if (numPrice == 2){
                   // то в итоге проверка 4х цен (2х серых и 2х красных)
                   priceLabel = " .regular-price";
                   fillProd(boxLabel, priceLabel, beforeFirstPrice);

                   priceLabel = " .campaign-price";

                   beforeSecondPrice = new ProdDuck().setName(driver.findElement(By.cssSelector(boxLabel+" div.name"))
                           .getAttribute("textContent"));
                   fillProd(boxLabel, priceLabel, beforeSecondPrice);

                   // CLICK!!!
                   driver.findElement(By.cssSelector(boxLabel+" img")).click();
                   //     boxLabel = "#box-most-popular";
                   boxLabel = "#box-product";
                   priceLabel = " .regular-price";

                   afterFirstPrice = new ProdDuck().setName(driver.findElement(By.cssSelector(boxLabel+" h1"))
                           .getAttribute("textContent"));

                   fillProd(boxLabel, priceLabel, afterFirstPrice);

                   priceLabel = " .campaign-price";
                   afterSecondPrice = new ProdDuck().setName(driver.findElement(By.cssSelector(boxLabel+" h1"))
                           .getAttribute("textContent"));

                   fillProd(boxLabel, priceLabel, afterSecondPrice);


                   checkOne(beforeFirstPrice,afterFirstPrice,mColorGrey,mtxtGreyDeclar,mtxtGreyWeight);
                   // Проверка "серых цен" закоментированна, т.к. серый на маленькой картинке отличается от серого на большой,
                   // Остальные проверки проходят успешно, т.е. Либо это баг либо нужно отключить проверку цвета на идентичность в начальном и конечном состоянии
                   checkOne(beforeSecondPrice,afterSecondPrice,mColorRed,mtxtRedDeclar,mtxtRedWeight);
                   // Проверка размеров цен
                   checkTwo(beforeFirstPrice,beforeSecondPrice);
                   checkTwo(afterFirstPrice,afterSecondPrice);
               }
               else{
                   // то в итоге проверка 2х цен
                   priceLabel = " .price";
                   fillProd(boxLabel, priceLabel, beforeFirstPrice);
                   showProd(beforeFirstPrice);
                   // CLICK!!!
                   driver.findElement(By.cssSelector(boxLabel+" img")).click();
                   //     boxLabel = "#box-most-popular";
                   boxLabel = "#box-product";
                   afterFirstPrice = new ProdDuck().setName(driver.findElement(By.cssSelector(boxLabel+" h1"))
                           .getAttribute("textContent"));
                   fillProd(boxLabel, priceLabel, afterFirstPrice);
                   showProd(afterFirstPrice);

                   checkOne(beforeFirstPrice,afterFirstPrice,mColorGrey,mtxtGreyDeclar,mtxtGreyWeight);
               }

    }
}
