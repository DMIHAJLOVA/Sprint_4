package ru.yandex.praktikum.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver webDriver;
    //поле ввода Имя
    private final By inputNameLocator = By.xpath("//input[@placeholder='* Имя']");
    //поле ввода фамилия
    private final By inputFamilyLocator = By.xpath("//input[@placeholder='* Фамилия']");
    //поле ввода Адрес
    private final By inputAddressLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле ввода Станция метро
    private final By inputMetroStationLocator = By.xpath("//input[@placeholder='* Станция метро']");
    //поле ввода Телефон
    private final By inputNumberPhoneLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //поле ввода Название станции метро
    private final String choiceMetroStationLocator = "//div[text()='%s']";
    //кнопка далее после первой части ввода данных пользователя
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']");
    //поле ввода Куда привезти самокат
    private final By inputDateLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //поле ввода Срок аренды
    private final By inputPeriodRentLocator = By.xpath("//div[text()='* Срок аренды']");
    //поле ввода Срок аренды
    private final String inputPeriodRentMenuLocator = "//div[text()='%s']";
    //поле ввода Цвет самоката
    private final By inputColorLocator = By.xpath("//input[@id='grey']");
    //поле ввода Комментарий для курьера
    private final By inputCommentForCourierLocator = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать после заполнения всех данных пользователя
    private final By clickOrderButtonLocator = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //поп-ап Хотите оформить заказ
    private final By popupQuestionLocator = By.xpath("//div[text()='Хотите оформить заказ?']");
    //Кнопка Да, для подтвеждения заказа
    private final By clickButtonOnYesLocator = By.xpath("//button[text()='Да']");
    //поп-ап Заказ оформлен
    private final By popupOrderMakeLocator = By.xpath("//div[@class='Order_Modal__YZ-d3']//div[text()='Заказ оформлен']");

    public OrderPage (WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public void fillCustomerInfo(String firstname, String lastname, String address, String station, String number) {
        //заполнение полей
        WebElement inputName = webDriver.findElement(inputNameLocator);
        inputName.sendKeys(firstname);
        WebElement inputFamily = webDriver.findElement(inputFamilyLocator);
        inputFamily.sendKeys(lastname);
        WebElement inputAddress = webDriver.findElement(inputAddressLocator);
        inputAddress.sendKeys(address);
        WebElement inputMetroStation = webDriver.findElement(inputMetroStationLocator);
        inputMetroStation.click();
        WebElement choiceStation= webDriver.findElement(By.xpath(String.format(choiceMetroStationLocator, station)));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", choiceStation);
        choiceStation.click();
        WebElement inputNumberPhone = webDriver.findElement(inputNumberPhoneLocator);
        inputNumberPhone.sendKeys(number);
    }

    public void clickNextButton() {
        //кнопка Далее

        WebElement firstNextButton = webDriver.findElement(nextButtonLocator);
        firstNextButton.click();
    }

    public void fillRentInfo(String daterent, String timerent, String commentrent) {
        //вторая форма заказа по первой кнопке


        WebElement inputDate = webDriver.findElement(inputDateLocator);
        inputDate.sendKeys(daterent, Keys.ENTER);
        WebElement inputPeriodRent = webDriver.findElement(inputPeriodRentLocator);
        inputPeriodRent.click();
        WebElement inputPeriodRentMenu = webDriver.findElement(By.xpath(String.format(inputPeriodRentMenuLocator, timerent)));
        inputPeriodRentMenu.click();
        WebElement inputColor = webDriver.findElement(inputColorLocator);
        inputColor.click();
        WebElement inputCommentForCourier = webDriver.findElement(inputCommentForCourierLocator);
        inputCommentForCourier.sendKeys(commentrent);


    }

    public void clickOrderButton() {

        WebElement clickOrderButton = webDriver.findElement(clickOrderButtonLocator);
        clickOrderButton.click();
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(clickOrderButton));
    }

    public boolean popupWithQuestion() {
        return webDriver.findElement(popupQuestionLocator).isDisplayed();

    }

    public void clickButtonOnYes() {

        WebElement clickButtonOnYes = webDriver.findElement(clickButtonOnYesLocator);
        clickButtonOnYes.click();
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(clickButtonOnYes));
    }

    public boolean popupOrderMake() {
        return webDriver.findElement(popupOrderMakeLocator).isDisplayed();

    }
}
