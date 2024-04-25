package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class MainPage {
    private final WebDriver webDriver;
    //скрол до аккордеона
    private final By scrollToQuestionLocator = By.xpath("//div[text()='Вопросы о важном']");
    //Кнлпка закрытия куки
    private final By buttonCookiesLocator = By.xpath("//button[text()='да все привыкли']");
    //Верхняя кнопка Заказать
    private final By clickFirstOrderButtonLocator = By.xpath("//div[contains(@class,'Header')]//button[text()='Заказать']");
    //нижняя кнопка заказать
    private final By clickFirstOrderButtonTwoLocator = By.xpath("//div[contains(@class,'Home_FinishButton__1_cWm')]//button[text()='Заказать']");
    //вопросы из аккордеона
    private final String questionLocator = "accordion__heading-%s";
    //ответы из аккордеона
    private final String answerLocator = "//div[contains(@id, 'accordion__panel-')][.='%s']";

    public MainPage (WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public void scrollToQuestion() {
        // вопросы о важном xpath //div[text()='Вопросы о важном']
        WebElement choiceQuestionsAboutImportant = webDriver.findElement(scrollToQuestionLocator);
        //скрол до блока Вопросы о важном
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", choiceQuestionsAboutImportant);
    }

    public void clickOrderButtonOne() {
        // первая кнопка заказать    вторая кнопка заказать //div[contains(@class,'Home_RoadMap__2tal_')]//button[text()='Заказать']

        WebElement clickFirstOrderButton = webDriver.findElement(clickFirstOrderButtonLocator);
        clickFirstOrderButton.click();
    }

    public void clickOrderButtonTwo() {
        WebElement clickSecondOrderButton = webDriver.findElement(clickFirstOrderButtonTwoLocator);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", clickSecondOrderButton);
        clickSecondOrderButton.click();
    }

    public void closeCookiesWindows() {
        WebElement element = webDriver.findElement(buttonCookiesLocator);
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void expandQuestion(int index) {
        WebElement element = webDriver.findElement(By.id(String.format(questionLocator,index)));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(webDriver, ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();

    }

    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}
