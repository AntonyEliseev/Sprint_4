package ru.praktikumServices.qaScooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }
    //Блок с вопросами
    private final By questionSection = By.className("accordion");
    //Вопросы
    private final By questionList = By.className("accordion__item");
    //Ответы
    private final By answerList = By.xpath(".//div[@class='accordion__panel']/p");
    //Кнопка "Заказать" в хэдере
    private final By orderButtonHeader = By.className("Button_Button__ra12g");
    //Кнопка "Заказать" в роадмап
    private final By orderButtonRoadmap = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    //Переход к блоку с вопросами
    public void scrollToQuestionList() {
        WebElement element = driver.findElement(questionSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //Получить список вопросов
    public List<WebElement> questionElements() {
        return driver.findElements(questionList);
    }
    //Получить список ответов
    public List<WebElement> answerElements() {
        return driver.findElements(answerList);
    }
    //Нажать на первую или вторую кнопку "Заказать" в зависимости от параметра
    public void clickOrderButton(int indexButton) {
        if (indexButton == 0) {
            driver.findElement(orderButtonHeader).click();
        } else {
            WebElement element = driver.findElement(orderButtonRoadmap);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(orderButtonRoadmap).click();
        }
    }
    //Ожидание элемента на странице
    public WebElement waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}