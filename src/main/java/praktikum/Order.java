package praktikum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;


public class Order {
    private final WebDriver driver;

    //Локатор

    // Локаторы первой страницы заказа
    private final By inputName = By.xpath(".//input[@placeholder='* Имя']");
    private final By inputSurName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    private final By inputStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By inputNameStation = By.xpath(".//ul[contains(@class,'select-search__options')]/li");

    private final By inputPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локаторы второй страницы заказа
    private final By inputDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By inputPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");
    private final By chooseColorBlack = By.id("black");
    private final By chooseColorGrey = By.id("grey");

    private final By inputComment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By buttonOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //Локаторы окна "Хотите оформить заказ?"
    private final By buttonOrderYes = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    private final By orderCompleted = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ'][text()='Заказ оформлен']");


    public Order(WebDriver driver){
        this.driver=driver;
    }

    // Вводим данные первой страницы заказа
    private void fillName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    private void fillSurName(String surName) {
        driver.findElement(inputSurName).sendKeys(surName);
    }

    private void fillAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void fillStation(String station) {
        driver.findElement(inputStation).click();
        driver.findElement(inputNameStation).click();
    }
    private void fillPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    // кликаем по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }

    // Объединяем все методы для первой страницы заказа
    public void fillFirstPage(String name, String surName, String address, String phone, String station) {
        fillName(name);
        fillSurName(surName);
        fillAddress(address);
        fillStation(station);
        fillPhone(phone);
        clickNextButton();

    }

    // вводим данные второй страницы
    private void fillDate(String date) {
        driver.findElement(inputDate).sendKeys(date + Keys.ENTER);
    }

    private void fillPeriod(String time) {
        driver.findElement(inputPeriod).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" + time + "']")).click();
    }
    public void clickOnCheckBox() {
        driver.findElement(chooseColorBlack).click();
    }

    public void fillComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(buttonOrder).click();
    }
    public void clickButtonYes() { driver.findElement(buttonOrderYes).click();
    }

    // Объединяем все методы для второй страницы заказа с кликом по кнопке "Заказать"
    public void fillSecondPage(String date, String period, String comment) {
        fillDate(date);
        fillPeriod(period);
        fillComment(comment);
        clickOnCheckBox();
        clickOrderButton();
        clickButtonYes();
    }
    public boolean getNotificationOfOrderCreation() {
        return driver.findElement(orderCompleted).getText().contains("Заказ оформлен");
    }
}
