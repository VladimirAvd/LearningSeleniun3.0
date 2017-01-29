package ru.stqa.tests.task;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Vladimir on 29.01.2017.
 */
public class MyNewThirteenthTask extends NewTestBase {
    @Test
    public void MyThirteenthTask() throws Exception{
        // Вход
        app.openApp();
// заполнение корзины

        for (int i = 0; i < 3; i++) {
            app.addProd();
        }

        // Клик в корзну
        app.klickBasketButton();

        // Очистка
        app.specialDeleteProd();
// проверка что корзина пуста
        assertTrue(app.basketIsEmpty());
    }
}
