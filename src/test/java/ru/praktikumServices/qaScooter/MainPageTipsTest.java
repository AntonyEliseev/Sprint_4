package ru.praktikumServices.qaScooter;

import ru.praktikumServices.qaScooter.BasePage;
import ru.praktikumServices.qaScooter.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainPageTipsTest{
    private final String expected;
    private final int index;
    private WebDriver driver;
    public MainPageTipsTest(String expectedText, int index) {
        this.expected = expectedText;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[] getTextData() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(BasePage.URL);
        setCookie(new Cookie("Cartoshka","true"));
        setCookie(new Cookie("Cartoshka-legacy","true"));
    }
    private void setCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    @Test
    public void checkAnswers() {
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToQuestionList();
        WebElement faqItem =  mainPage.questionElements().get(index);
        faqItem.click();
        WebElement answerList =  mainPage.answerElements().get(index);
        WebElement answer = mainPage.waitForElementToBeVisible(answerList);
        assertEquals("Текст ответа не совпадает с ожидаемым",
                expected,answer.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}