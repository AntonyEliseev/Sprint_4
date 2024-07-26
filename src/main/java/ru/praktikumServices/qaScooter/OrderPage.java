package ru.praktikumServices.qaScooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage extends BasePage {
    public OrderPage(WebDriver driver) {
        super(driver);
    }
    //Имя
    private final By name = By.xpath("//input[@placeholder='* Имя']");
    //Фамилия
    private final By surname = By.xpath("//input[@placeholder='* Фамилия']");
    //Адрес
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Метро
    private final By station = By.xpath("//input[@placeholder='* Станция метро']");
    //Список всех станций метро
    private final By listStation = By.xpath("//li[@class='select-search__row']");
    //Номер телефона
    private final By phoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private final By buttonNext = By.xpath("//button[text()='Далее']");
    //Выбор даты доставки
    private final By deliveryDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Список со сроками аренды
    private final By listDuration = By.xpath("//div[@class='Dropdown-option']");
    //Выбор срока аренды
    private final By inputDuration = By.xpath("//span[@class='Dropdown-arrow']");
    //Цвет самоката "Черный"
    private final By colorBlack= By.id("black");
    //Цвет самоката "Серый"
    private final By colorGrey = By.id("grey");
    //Поле с комментарием
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Кнопка "Хотите оформить заказ?"
    private final By confirmOrderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //Заголовок с номером заказа
    private final By headerOrderResult = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");
    //Заполнить имя
    public void fillName(String name) {
        driver.findElement(this.name).clear();
        driver.findElement(this.name).sendKeys(name);
    }
    //Заполнить фамилию
    public void fillSurname(String surname) {
        driver.findElement(this.surname).clear();
        driver.findElement(this.surname).sendKeys(surname);
    }
    //Заполнить адрес
    public void fillAddress(String address) {
        driver.findElement(this.address).clear();
        driver.findElement(this.address).sendKeys(address);
    }
    //Выбрать станцию метро
    public void chooseStation(int stationNumber) {
        driver.findElement(station).click();
        List<WebElement> stationList = driver.findElements(listStation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stationList.get(stationNumber));
        stationList.get(stationNumber).click();
    }
    //Заполнить телефон
    public void fillPhone(String phone) {
        driver.findElement(phoneNumber).clear();
        driver.findElement(phoneNumber).sendKeys(phone);
    }
    //Кликнуть на кнопку "Далее"
    public void clickOnNextButton() {
        driver.findElement(buttonNext).click();
    }
    //Выбрать дату доставки
    public void chooseDate(String date) {
        driver.findElement(deliveryDate).clear();
        driver.findElement(deliveryDate).sendKeys(date);
    }
    //Выбрать срок аренды
    public void chooseDuration(int index) {
        driver.findElement(inputDuration).click();
        List<WebElement> durationList = driver.findElements(listDuration);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", durationList.get(index));
        durationList.get(index).click();
    }
    // Выбор цвета самоката
    public void setColor(String color){
        if (color.equals("черный")){
            driver.findElement(colorBlack).click();;
        } else {
            driver.findElement(colorGrey).click();
        }
    }
    //Написать комментарий
    public void fillComment(String comment) {
        driver.findElement(this.comment).clear();
        driver.findElement(this.comment).sendKeys(comment);
    }
    //Нажать кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //Общий метод для заполнения первой страницы заказа
    public void fillOrderFormFirstPage(String name, String surname, String address, int stationIndex, String phone) {
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        chooseStation(stationIndex);
        fillPhone(phone);
        clickOnNextButton();
    }
    //Общий метод для заполнения второй страницы заказа
    public void fillOrderFormSecondPage(String date, int durationIndex, String color, String comment) {
        chooseDate(date);
        chooseDuration(durationIndex);
        setColor(color);
        fillComment(comment);
        clickOrderButton();
    }
    //Нажать кнопку "Подтвердить заказ"
    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }
    //Получить текст с результатами заказа
    public String getTextAboutOrderResult() {
        return driver.findElement(headerOrderResult).getText();
    }
}