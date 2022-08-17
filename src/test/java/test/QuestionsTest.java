package test;
import praktikum.MainPage;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static praktikum.MainPage.url;

@RunWith(Parameterized.class)
public class QuestionsTest{
    private WebDriver driver;
    private final int number;

    public QuestionsTest(int number) {
        this.number = number;
    }
    // Подстовляем все 8 вопросов и ответов (через индексы массивов)
    @Parameterized.Parameters
    public static Object[][] getNumber () {
        return new Object[][] {
                {0},{1},{2},{3},
                {4},{5},{6},{7}
        };
    }

    @Before
    public void testSetup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookieButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(MainPage.url);

    }

    @Test
    public void testAccordionButton() {
        MainPage mainPage = new MainPage(driver);

        mainPage.scrollAndClickToAccordionButton(number);

        assertEquals("Text not found or doesn't match",mainPage.answersFAQ[number],mainPage.clickToAccordionButton(number));
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
