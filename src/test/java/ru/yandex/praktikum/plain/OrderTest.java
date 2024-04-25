package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.junit.Assert;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;

public class OrderTest {
    private static final String BROWSER = "chrome";
    private WebDriver webDriver;

    @Before
    public void setup() {

        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }


    @Test
    public void createOrder(){

        MainPage mainPage = new MainPage(webDriver);
        mainPage.closeCookiesWindows();
        mainPage.clickOrderButtonOne();

        OrderPage orderPage  = new OrderPage(webDriver);
        //заполнение первоц части формы заказа

        orderPage.fillCustomerInfo("Дарья","Шевцова","Станкевича 36","Арбатская","89507744227");
        //кнопка Далее
        orderPage.clickNextButton();
        //заполнение второй части формы заказа
        orderPage.fillRentInfo("01.06.2024","сутки","Просьба позвонить заранее");
        //кнопка Заказать
        orderPage.clickOrderButton();

        // поп-ап Хотите оформить заказ?
         Assert.assertTrue(orderPage.popupWithQuestion());

         //нажать Да на форме Хотите оформить заказ
          orderPage.clickButtonOnYes();
          //проверка, что кнопка Да отработала и отображается сообщение о том, что заказ оформлен
          orderPage.popupOrderMake();
          Assert.assertTrue(orderPage.popupOrderMake());


       }

    @Test
    public void createOrderBottomButton(){
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderButtonTwo();

        OrderPage orderPage  = new OrderPage(webDriver);
        //заполнение первоц части формы заказа
        orderPage.fillCustomerInfo("Иван","Петров","Семилуки Донская 28","Киевская","89507744227");
        //кнопка Далее
        orderPage.clickNextButton();
        //заполнение второй части формы заказа
        orderPage.fillRentInfo("12.01.2024","двое суток","Просьба позвонить заранее");
        //кнопка Заказать
        orderPage.clickOrderButton();

        // поп-ап Хотите оформить заказ?
        Assert.assertTrue(orderPage.popupWithQuestion());

        //нажать Да на форме Хотите оформить заказ
        orderPage.clickButtonOnYes();
        //проверка, что кнопка Да отработала и отображается сообщение о том, что заказ оформлен
        orderPage.popupOrderMake();
        Assert.assertTrue(orderPage.popupOrderMake());


    }

    @After
            public void tearDown(){
            webDriver.quit();
    }
}
