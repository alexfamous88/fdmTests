package Factories.Plastic;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LotosPlastic {

    public WebDriver driver;

    public LotosPlastic(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Переход в Каталог
    @FindBy(xpath = "//a[contains(text(), 'Каталог')]")
    private WebElement toCatalog;

    // Фасады пластик
    @FindBy(xpath = "(//*[@class='factory-list open']//a)[2]")
    private WebElement fasadyPlastic;

    // Лотос в Каталоге
    @FindBy(xpath = "//*[@class='factory-data']//*[@alt='ЛОТОС-ЮГ']//parent::a")
    private WebElement lotosInCatalog;

    // Фасады из пластика в ал. профиле в Каталоге
    @FindBy(xpath = "//*[@href='/select-facade/fasady-plastik-profil-lotos-ug/']")
    private WebElement lotosPlastic;

    // Фасады пластик FENIX в ал. профиле узкий
    @FindBy(xpath = "(//*[@class='row']//a)[1]")
    private WebElement fasadyFenixVAlProfileUzkiy;

    // Первый декор из списка (Шаг 1)
    @FindBy(xpath = "(//*[@class='card d-block m-0 decor-item-button'])[1]")
    private WebElement decor;

    // Кнопка "Продолжить" в диалоговом окне с характеристиками
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    private WebElement characteristicsContinueBtn;

    // Ввод высоты детали (Шаг 2)
    @FindBy(xpath = "//*[contains(@name, 'height')]")
    private WebElement height;

    // Ввод ширины детали (Шаг 2)
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

    // Кнопка закрытия окна аналогов
    @FindBy(xpath = "//*[@class='modal-dialog default-modal-form comparison-modal']//*[@class='close']")
    private WebElement closeAnalogs;

    public void addToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Переход в Каталог
        toCatalog.click();

        // Выбор типа фасада: "Фасады в пленке ПВХ"
        fasadyPlastic.click();

        // Выбор фабрики Кедр
        lotosInCatalog.click();

        // Выбор фасадов ПВХ фабрики Кедр
        lotosPlastic.click();

        // Выбор прямых фасадов
        fasadyFenixVAlProfileUzkiy.click();

        // Выбор декора
        try {
            decor.click();
            characteristicsContinueBtn.click();
        } catch (NoSuchElementException e) {
            changeFacadeType.click();
            decor.click();
            characteristicsContinueBtn.click();
        }

        // Установка размерности фасада
        wait.until(ExpectedConditions.visibilityOf(calculateBtn));
        height.sendKeys("1000");
        width.sendKeys("1000");
        calculateBtn.click();

        js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//*[@id='found-cheaper']")));

        // Ждем прогрузки похожих предложений
        try {
            // Кликаем "Добавить в корзину"
            addToBasketBtn.click();

            // Перейти в корзину
            wait.until(ExpectedConditions.elementToBeClickable(toBasketBtn));
            toBasketBtn.click();

        } catch (ElementClickInterceptedException e) {
            // Закрываем окно с аналогами
            wait.until(ExpectedConditions.visibilityOf(closeAnalogs));
            closeAnalogs.click();

            // Перейти в корзину
            wait.until(ExpectedConditions.visibilityOf(toBasketBtn));
            toBasketBtn.click();
        }

            // Проверяем, действительно ли перешли в корзину
            String expectedUrl = "https://allfdm.ru/main/basket/";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals("Переход в Корзину не осуществлен", expectedUrl, actualUrl);

            // Ждем загрузки БЗ в корзине
            wait.until(ExpectedConditions.visibilityOf(basketList));
        }
    }