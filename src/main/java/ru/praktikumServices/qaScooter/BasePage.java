package ru.praktikumServices.qaScooter;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    protected WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}