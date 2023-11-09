package Factories.Pvh;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class FabrichePvh {

    public WebDriver driver;

    public FabrichePvh(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Переход в Каталог
    @FindBy(xpath = "//a[contains(text(), 'Каталог')]")
    private WebElement toCatalog;

    // Фасады в пленке ПВХ
    @FindBy(xpath = "(//*[@class='factory-list open']//a)[1]")
    private WebElement fasadyPvh;

    // Fabriche в Каталоге
    @FindBy(xpath = "//*[@class='factory-data']//*[@alt='FABRICHE']//parent::a")
    private WebElement fabricheInCatalog;

    // Фасады ПВХ в Каталоге
    @FindBy(xpath = "//*[@href='/select-facade/fs-pvh-fabriche/']")
    private WebElement fabrichePvh;

    // Фасады прямые
    @FindBy(xpath = "(//*[@class='row']//a)[1]")
    private WebElement fasadyPryamye;

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

    // Локатор похожих предложений
    @FindBy(xpath = "(//*[@class='bold'])[4]")
    private WebElement analogsLocator;

    // Кнопка закрытия окна аналогов
    @FindBy(xpath = "//*[@class='modal-dialog default-modal-form comparison-modal']//*[@class='close']")
    private WebElement closeAnalogs;

    // Локатор отсутствия похожих предложений
    @FindBy(xpath = "//*[contains(text(), 'Похожих предложений не найдено!')]")
    private WebElement analogsIsMissing;


    public void addToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Переход в Каталог
        toCatalog.click();

        // Выбор типа фасада: "Фасады в пленке ПВХ"
        fasadyPvh.click();

        // Выбор фабрики Кедр
        fabricheInCatalog.click();

        // Выбор фасадов ПВХ фабрики Кедр
        fabrichePvh.click();

        // Выбор прямых фасадов
        fasadyPryamye.click();

        // Выбор декора
        try {
            decor.click();
            characteristicsContinueBtn.click();
        } catch (NoSuchElementException e) {
            changeFacadeType.click();
            decor.click();
            characteristicsContinueBtn.click();
        }

        // Выбор фрезеровки
        milling.click();
        characteristicsContinueBtn.click();

        // Установка размерности фасада
        wait.until(ExpectedConditions.visibilityOf(calculateBtn));
        height.sendKeys("1000");
        width.sendKeys("100");
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
