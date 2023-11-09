package Authorization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Authorization {

    private static String code = "4836";
    private static String[] numbers = {"9", "9", "9", "9", "9", "9", "9", "9", "9", "9"};

    public WebDriver driver;
    public Authorization(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Локаторы
    // Инпут с вводом города
    @FindBy(xpath = "//*[@class='form-control select-city-input-js']")
    private WebElement cityInput;

    // Город Белгород
    @FindBy(xpath = "//*[.='Белгород']")
    private WebElement cityBelgorod;

    // Локатор выбора города
    @FindBy(xpath = "//*[@id='closeSelectCityModal']")
    private WebElement cityBtnClose;

    // Кнопка Вход
    @FindBy(xpath = "//*[@class='header__user-links']")
    private WebElement loginBtn;

    // Инпут для ввода номера телефона
    @FindBy(xpath = "//*[@id='phoneInput']")
    private WebElement numberInput;

    // Кнопка перехода к вводу кода
    @FindBy(xpath = "//*[@class='col center']//button")
    private WebElement loginBtnAfterNum;

    // Инпут для ввода кода
    @FindBy(xpath = "//*[@id='codeInput']")
    private WebElement codeInput;

    // Кнопка подтверждения кода
    @FindBy(xpath = "//*[@class='center']//button")
    private WebElement loginBtnAfterCode;

    // Каталог
    @FindBy(xpath = "//*[@class='header__list nav__list']//a[contains(text(),'Каталог')]")
    private WebElement catalogNav;


    // Шаблонная авторизация для прохождения сценариев
    public void authSimple() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Ждем появления окна с выбором города
        wait.until(ExpectedConditions.visibilityOf(cityBtnClose));

        // Вводим город
        cityInput.sendKeys("Белгород");

        // Подтверждаем город
        wait.until(ExpectedConditions.visibilityOf(cityBelgorod));
        cityBelgorod.click();

        // Кликаем на кнопку "Вход"
        wait.until(ExpectedConditions.invisibilityOf(cityInput));
        loginBtn.click();

        // Ввод номера телефона
        wait.until(ExpectedConditions.visibilityOf(numberInput));
        int i = 0;
        do {
            numberInput.sendKeys(numbers[i]);
            i++;
        } while (i < numbers.length);

        // Клик для перехода к вводу кода
        loginBtnAfterNum.click();

        // Ввод кода
        wait.until(ExpectedConditions.visibilityOf(codeInput));
        codeInput.sendKeys(code);

        // Кликаем по кнопке подтверждения кода
        loginBtnAfterCode.click();
    }
}