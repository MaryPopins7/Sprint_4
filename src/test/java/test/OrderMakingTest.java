package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.MainPage;
import praktikum.Order;

import java.time.Duration;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)

public class OrderMakingTest {
    private WebDriver driver;
    private final String name;
    private final String surName;
    private  final String address;
    private final String station ;
    private final String phone;
    private  final String date;
    private final   String period;
    private final String comment;

    public OrderMakingTest(String name, String surName, String address, String station, String phone, String date, String period, String comment) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] getNumber () {
        return new Object[][] {
                {"Мария", "Украинская", "Открытое шоссе д.28 кв.2", "Бульвар Рокосcовского", "89037544196","08.08.2022", "двое суток", "Позвоните за час до приезда"},

        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // открываем страницу яндексСамоката в барузере
        driver.get(MainPage.yandexServise_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @After
    public void teatDown() {
        driver.quit();
    }

    @Test
    public void testFillOrderInformWithOrderButtonLow() throws InterruptedException {
        // нажимаем на кнопку заказать внизу экрана

        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookieButton();
        mainPage.clickToOrderButtonLow();
        // заполняем первую страницу заказа, нажимаем кнопку "Далее" с помощью объединенного метода fillFirstPage
        Order order = new Order(driver);
        order.fillFirstPage(name, surName, address, phone, station);
        Thread.sleep(5000);



        // заполняем вторую страницу заказа, с помощью объединенного метода fillSecondPage
        order.fillSecondPage(date, period, comment);
        assertTrue("Window Order Completed - not found!",order.getNotificationOfOrderCreation());
    }

}
