package Delivery;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Terms {

    public WebDriver driver;

    public Terms(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Переход в Каталог
    @FindBy(xpath = "//a[contains(text(), 'Каталог')]")
    private WebElement toCatalog;

    // Фасады в пленке ПВХ
    @FindBy(xpath = "(//*[@class='factory-list open']//a)[1]")
    private WebElement fasadyPvh;

    // Кедр в Каталоге
    @FindBy(xpath = "//*[@class='factory-data']//*[@alt='Кедр']//parent::a")
    private WebElement kedrInCatalog;

    // Фасады ПВХ в Каталоге
    @FindBy(xpath = "//*[@href='/select-facade/pvh/']")
    private WebElement kedrPvh;

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

    // Уведомление о смене фасада
    @FindBy(xpath = "//*[@id='changeFacadeType']")
    private WebElement changeFacadeType;

    // Локатор похожих предложений
    @FindBy(xpath = "(//*[@class='bold'])[4]")
    private WebElement analogsLocator;

    // Наименование фабрики в похожих предложениях
    @FindBy(xpath = "(//*[@class='comparison-table-wrap']//tr/td)[1]")
    private WebElement analogFactoryName;

    // Наименование декора в похожих предложениях
    @FindBy(xpath = "(//*[@class='comparison-table-wrap']//tr/td)[2]//*[@class='decor-image-description']")
    private WebElement analogDecorName;

    // Дата доставки аналога
    @FindBy(xpath = "(//td//div[contains(text(), '.20')])[1]")
    private WebElement analogDeliveryDate;

    // Кнопка "Все предложения"
    @FindBy(xpath = "//*[@data-target='#comparison-modal']")
    private WebElement allAnalogs;

    // Кнопка закрытия окна аналогов
    @FindBy(xpath = "//*[@class='modal-dialog default-modal-form comparison-modal']//*[@class='close']")
    private WebElement closeAnalogs;

    // Выбор ПВХ аналог
    @FindBy(xpath = "//*[contains(@href, '/select-facade/pvh')]")
    private WebElement analogPvh;

    // Поле ввода наименования декора для поиска
    @FindBy(xpath = "//*[@id='decor_filter_name']")
    private WebElement decorInput;

    // Кнопка "Найти"
    @FindBy(xpath = "//*[@class='btn btn-secondary d-block w-100']")
    private WebElement searchDecorBtn;

    // Декор аналога после поиска
    @FindBy(xpath = "//*[@class='card-body-char-block']//h3")
    private WebElement chooseDecor;

    // Срок доставки аналога в БЗ
    @FindBy(xpath = "(//*[@class='order-price-table']//td)[last()]//*")
    private WebElement analogDeliveryTerm;
    
    private final static String DATE = "[0-9]{2}.[0-9]{2}.[0-9]{4}";

    public void checkDeliveryTerms() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Переход в Каталог
        toCatalog.click();

        // Выбор типа фасада: "Фасады в пленке ПВХ"
        fasadyPvh.click();

        // Выбор фабрики Кедр
        kedrInCatalog.click();

        // Выбор фасадов ПВХ фабрики Кедр
        kedrPvh.click();

        // Выбор прямых фасадов
        fasadyPryamye.click();

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
        width.sendKeys("1000");
        calculateBtn.click();

        js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//*[@id='found-cheaper']")));

        // Открываем таблицу с похожими предложениями
        wait.until(ExpectedConditions.visibilityOf(analogsLocator));
        allAnalogs.click();

        // Идентифицируем аналогичный фасад
        wait.until(ExpectedConditions.visibilityOf(analogDeliveryDate));
        String analogFactory = analogFactoryName.getText().strip();
        String analogDelivery = analogDeliveryDate.getText().strip();
        String analogDecor = analogDecorName.getText().strip();

        // Закрываем окно с аналогами
        closeAnalogs.click();

        // Переход в Каталог для поиска аналога
        toCatalog.click();

        // Выбор типа фасада: "Фасады в пленке ПВХ"
        fasadyPvh.click();

        // Выбор фабрики аналога
        driver.findElement(By.xpath(
                "//*[@class='factory-data']//*[@alt='" + analogFactory + "']//parent::a")).click();

        // Выбор фасада ПВХ аналога
        analogPvh.click();

        // Выбор прямых фасадов
        fasadyPryamye.click();

        try {
            wait.until(ExpectedConditions.visibilityOf(changeFacadeType));
            changeFacadeType.click();
        } catch (TimeoutException e) {
        }

        // Поиск декора аналога
        decorInput.sendKeys(analogDecor);
        searchDecorBtn.click();

        // Формирование БЗ
        chooseDecor.click();
        characteristicsContinueBtn.click();

        // Выбор фрезеровки
        milling.click();
        characteristicsContinueBtn.click();

        // Установка размерности фасада
        wait.until(ExpectedConditions.visibilityOf(calculateBtn));
        height.sendKeys("1000");
        width.sendKeys("1000");
        calculateBtn.click();

        // Установка соответствия сроков доставки
        wait.until(ExpectedConditions.visibilityOf(analogDeliveryTerm));
        String term = analogDeliveryTerm.getText().strip();
        Assert.assertEquals("Сроки доставки не соответствуют", analogDelivery, term);
    }
}