package MainPage;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainHeader {

    public WebDriver driver;

    public MainHeader(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Модальное окно Входа с Главной
    @FindBy(xpath = "//*[@class='modal-dialog']")
    private WebElement loginModalWindow;

    // Заголовок модального окна Входа с Главной
    @FindBy(xpath = "//*[@class='modal-dialog']//h6")
    private WebElement loginModalTitle;

    // Закрыть модальное окно Входа с Главной
    @FindBy(xpath = "//*[@class='modal-content']//button[@class='close']")
    private WebElement closeBtnModal;

    // Каталог
    @FindBy(xpath = "//*[@class='header__list nav__list']//a[contains(text(),'Каталог')]")
    private WebElement catalogNav;

    // Доставка
    @FindBy(xpath = "//*[@class='header__list nav__list']//a[contains(text(),'Доставка')]")
    private WebElement deliveryNav;

    // Оплата
    @FindBy(xpath = "//*[@class='header__list nav__list']//a[contains(text(),'Оплата')]")
    private WebElement paymentNav;

    // Возврат
    @FindBy(xpath = "//*[@class='header__list nav__list']//a[contains(text(),'Возврат')]")
    private WebElement returningNav;

    // Вход
    @FindBy(xpath = "//*[@class='g-icon-text']//a[contains(text(),'Вход')]")
    private WebElement loginNav;

    // Регистрация
    @FindBy(xpath = "//*[@class='g-icon-text']//a[contains(text(),'Регистрация')]")
    private WebElement regNav;

    // Корзина
    @FindBy(xpath = "//*[@class='basket-header-link']")
    private WebElement basket;

    // Заказы
    @FindBy(xpath = "//*[@class='g-icon-text']//a[contains(text(),'Заказы')]")
    private WebElement orders;

    // Выход
    @FindBy(xpath = "//*[@class='g-icon-text']//a[contains(text(),'Выход')]")
    private WebElement logOut;


    // Перейти в Каталог
    public void fromNavToCatalog() {
        String expectedUrl = "https://allfdm.ru/material/select";
        catalogNav.click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход в раздел \"Каталог\" не осуществлен", expectedUrl, actualUrl);
        driver.navigate().back();
    }

    // Перейти в Доставка
    public void fromNavToDelivery() {
        String expectedUrl = "https://allfdm.ru/dostavka-i-oplata";
        deliveryNav.click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход в раздел \"Доставка\" не осуществлен", expectedUrl, actualUrl);
        driver.navigate().back();
    }

    // Перейти в Оплата
    public void fromNavToPayment() {
        String expectedUrl = "https://allfdm.ru/oplata";
        paymentNav.click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход в раздел \"Оплата\" не осуществлен", expectedUrl, actualUrl);
        driver.navigate().back();
    }

    // Перейти в Возврат
    public void fromNavToReturning() {
        String expectedUrl = "https://allfdm.ru/vozvrat";
        returningNav.click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход в раздел \"Возврат\" не осуществлен", expectedUrl, actualUrl);
        driver.navigate().back();
    }

    // Открыть окно авторизации
    public void fromNavToLogin() {
        loginNav.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginModalWindow));
        String expectedResult = "Вход";
        String actualResult = loginModalTitle.getText().strip();
        Assert.assertEquals("Переход к авторизации из хэдера не осуществлен", expectedResult, actualResult);
        closeBtnModal.click();
    }

    // Перейти на страницу регистрации
    public void fromNavToRegistration() {
        regNav.click();
        String expectedUrl = "https://allfdm.ru/personal/registration";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход на страницу регистрации из хэдера не осуществлен", expectedUrl, actualUrl);
    }

    // Перейти в корзину
    public void fromNavToBasket() {
        basket.click();
        String expectedUrl = "https://allfdm.ru/main/basket/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход в Корзину из хэдера не осуществлен", expectedUrl, actualUrl);
        driver.navigate().back();
    }

    // Перейти к бланкам заказов
    public void fromNavToOrders() {
        orders.click();
        String expectedUrl = "https://allfdm.ru/orders";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход в Корзину из хэдера не осуществлен", expectedUrl, actualUrl);
        driver.navigate().back();
    }

    // Разлогиниться
    public void logOutFromNav() {
        logOut.click();
        Assert.assertTrue(loginNav.isDisplayed());
    }
}