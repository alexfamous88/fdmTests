package Factories.Akril;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdelkreisAkril {

    public WebDriver driver;

    public AdelkreisAkril(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Переход в Каталог
    @FindBy(xpath = "//a[contains(text(), 'Каталог')]")
    private WebElement toCatalog;

    // Фасады акрил
    @FindBy(xpath = "(//*[@class='factory-list open']//a)[4]")
    private WebElement fasadyAkril;

    // ТМФ в Каталоге
    @FindBy(xpath = "//*[@class='factory-data']//*[@alt='ADELKREIS']//parent::a")
    private WebElement adelkreisInCatalog;

    // Фасады акриловые в Каталоге
    @FindBy(xpath = "//*[@href='/select-facade/akril-adelkreis/']")
    private WebElement adelkreisAkril;

    // Фасады Lite кромка в цвет
    @FindBy(xpath = "(//*[@class='row']//a)[1]")
    private WebElement fasadyAkrilLite;

    // Первый декор из списка (Шаг 1)
    @FindBy(xpath = "(//*[@class='card d-block m-0 decor-item-button'])[1]")
    private WebElement decor;

    // Кнопка "Продолжить" в диалоговом окне с характеристиками
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    private WebElement characteristicsContinueBtn;

    // Первая фрезеровка из списка (Шаг 2)
    @FindBy(xpath = "(//*[contains(@class, 'row-cards')]//a[contains(@class, 'card')])[1]")
    private WebElement milling;

    // Ввод высоты детали (Шаг 3)
    @FindBy(xpath = "//*[contains(@name, 'height')]")
    private WebElement height;

    // Ввод ширины детали (Шаг 3)
    @FindBy(xpath = "//*[contains(@name, 'width')]")
    private WebElement width;

    // Кнопка "Рассчитать стоимость заказа"
    @FindBy(xpath = "//*[@id='submitButton']")
    private WebElement calculateBtn;

    // Кнопка "Добавить в корзину"
    @FindBy(xpath = "//*[contains(@class, 'add-to-basket-js')]")
    private WebElement addToBasketBtn;

    // Кнопка "Перейти в корзину"
    @FindBy(xpath = "//*[contains(@class, 'in-basket')]")
    private WebElement toBasketBtn;

    // Таблица с бланками в Корзине
    @FindBy(xpath = "//*[@role='tablist']")
    private WebElement basketList;

    // Уведомление о смене фасада
    @FindBy(xpath = "//*[@id='changeFacadeType']")
    private WebElement changeFacadeType;


    public void addToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Переход в Каталог
        toCatalog.click();

        // Выбор типа фасада: "Фасады в пленке ПВХ"
        fasadyAkril.click();

        // Выбор фабрики Кедр
        adelkreisInCatalog.click();

        // Выбор фасадов ПВХ фабрики Кедр
        adelkreisAkril.click();

        // Выбор прямых фасадов
        fasadyAkrilLite.click();

        try {
            wait.until(ExpectedConditions.visibilityOf(changeFacadeType));
            changeFacadeType.click();
        } catch (TimeoutException e) {
        } finally {
            // Выбор декора
            wait.until(ExpectedConditions.visibilityOf(decor));
            decor.click();
            characteristicsContinueBtn.click();

            // Выбор фрезеровки
            milling.click();
            characteristicsContinueBtn.click();

            // Установка размерности фасада
            wait.until(ExpectedConditions.visibilityOf(calculateBtn));
            height.sendKeys("1000");
            width.sendKeys("950");
            calculateBtn.click();

            js.executeScript("arguments[0].scrollIntoView();",
                    driver.findElement(By.xpath("//*[@id='found-cheaper']")));

            // Кликаем "Добавить в корзину"
            addToBasketBtn.click();

            // Перейти в корзину
            toBasketBtn.click();

            // Проверяем, действительно ли перешли в корзину
            String expectedUrl = "https://dev.allfdm.ru/main/basket/";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals("Переход в Корзину не осуществлен", expectedUrl, actualUrl);

            // Ждем загрузки БЗ в корзине
            wait.until(ExpectedConditions.visibilityOf(basketList));
        }
    }
}
