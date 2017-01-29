package ru.stqa.tests.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Vladimir on 29.01.2017.
 */
public class MyPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public MyPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
}
