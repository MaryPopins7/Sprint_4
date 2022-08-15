package praktikum;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    public final static String url = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    // Массив локаторов для FAQ
    private final By[] locatorFAQ = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };

    // Массив локаторов для ответов на FAQ
    private final By[] locatorAnswersFAQ = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    // Массив с ответами на FAQ
    public final String[] answersFAQ = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };


    // Кнопка "Заказать" в верхней части страницы
    private By topButtonOrder = By.className("Button_Button__ra12g");
    // Кнопка "Заказать" в нижней части страницы
    private By lowerButtonOrder = By.xpath("(//button [text()='Заказать'])[2]");

    private By cookie = By.className("App_CookieButton__3cvqF");
    public void closeCookieButton() {
        driver.findElement(cookie).click();
    }

    // Скрол к указанному вопросу из столбца "Вопросы о важном"
    public void scrollToAccordionButtons(int locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locatorFAQ[locator]));
    }

    // Клик по раскрывающей кнопке из столбца "Вопросы о важном" и метод возвращающий текст ответа
    public String clickToAccordionButton(int locator) {
        driver.findElement(locatorFAQ[locator]).click();
        return driver.findElement(locatorAnswersFAQ[locator]).getText();
    }

    // Объединение методов скрола и клика
    public void scrollAndClickToAccordionButton(int locator) {
        scrollToAccordionButtons(locator);
        clickToAccordionButton(locator);
    }

    // Клик на кнопку "Заказать" в верхней части страницы
    public void clickToOrderButtonUp() {
        driver.findElement(topButtonOrder).click();
    }

    // Скрол к кнопке "Заказать" в нижней части страницы
    public void scrollToLowerButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(lowerButtonOrder));
    }

    // Клик на кнопку "Заказать" в нижней части страницы
    public void clickToOrderButtonLow() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(lowerButtonOrder));
        driver.findElement(lowerButtonOrder).click();
    }
}