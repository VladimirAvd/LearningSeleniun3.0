package ru.stqa.tests;

/**
 * Created by Vladimir on 17.12.2016.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class MySecondTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void mySecondTest() throws Exception {
/* Уважаемые преподаватели, прошу сильно не ругаться.
Приложение уже имеет некоторую "конкретику", и следовало бы задействовать явные ожидания,
просто вместо того что бы обалдевать знаниями я тут с буком воюю (хотя конечно это мои личные проблемы)
Т.к. я уже в цейтноте, то пока решение на неявных (что по идее не противоречит букве задания:) )
 */

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath(".//*[@id=\"box-login\"]//tbody/tr[1]/td[2]/span/input")).sendKeys("admin");
        driver.findElement(By.xpath(".//*[@id=\"box-login\"]//tbody/tr[2]/td[2]/span/input")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"box-login\"]/form/div[2]/button")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        driver = null;
    }
}