package Basket;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Basket {

    public WebDriver driver;

    public Basket(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // Бланк заказа в списке
    @FindBy(xpath = "//*[@role='tablist']/tr[1]")
    private WebElement blankInTable;

    // Кнопка детали бланка заказа
    @FindBy(xpath = "(//*[@class='basket-edit-block']/a)[1]")
    private WebElement blankDetailsBtn;

    // Детали БЗ
    @FindBy(xpath = "//*[@class='order-detail-list table-wrap']")
    private WebElement blankDetails;

    // Кнопка редактирования бланка заказа
    @FindBy(xpath = "(//*[@class='basket-edit-block']/a)[2]")
    private WebElement blankEditBtn;

    // Удалить бланк заказа из корзины
    @FindBy(xpath = "(//*[@class='basket-edit-block']/a)[3]")
    private WebElement blankRemove;

    // Кнопка "Очистить корзину"
    @FindBy(xpath = "//*[contains(@class, 'btn-clear')]")
    private WebElement clearBasketBtn;

    // Кнопка "Перейти к оформлению"
    @FindBy(xpath = "//*[@data-type='go-to-checkout']")
    private WebElement goToCheckout;

    // Сообщение о пустой корзине
    @FindBy(xpath = "//*[@id='basketTotal']//h4")
    private WebElement basketIsEmpty;

    // Кнопка "Очистить корзину"
    @FindBy(xpath = "//*[contains(@class, 'cart-clear')]")
    private WebElement clearBasket;

    // Уведомление о пустой корзине
    @FindBy(xpath = "//*[@id='basketTotal']//h4")
    private WebElement emptyBasketMessage;

    // Таблица с бланками в Корзине
    @FindBy(xpath = "//*[@role='tablist']")
    private WebElement basketList;

    // Переход к бланкам заказов из корзины
    @FindBy(xpath = "//*[@class='personal-block-nav']//*[@href='/orders']")
    private WebElement ordersFromNavInBasket;

    // Переход к оплаченным заказам из корзины
    @FindBy(xpath = "//*[@class='personal-block-nav']//*[@href='/orders/paid']")
    private WebElement paidOrdersFromNavInBasket;

    // Переход к образцам декоров из корзины
    @FindBy(xpath = "//*[@class='personal-block-nav']//*[@href='/shop/sample-orders']")
    private WebElement samplesFromNavInBasket;

    // Переход в Корзину из хэдера
    @FindBy(xpath = "//*[@class='basket-header-link']")
    private WebElement toBasketFromHeader;


    // Посмотреть бланк заказа
    public void showBlank() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        blankDetailsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(blankDetails));
        Assert.assertTrue("Детали БЗ не отображены", blankDetails.isDisplayed());
    }

    // Редактирование бланка заказа
    public void editBlank() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        blankEditBtn.click();

        String regexUrl = "https://dev.allfdm.ru/order/basket/[0-9]*";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue("Переход на страницу редактирования БЗ не осуществлен", actualUrl.matches(regexUrl));

        driver.navigate().to("https://dev.allfdm.ru/main/basket/");

        // Ждем загрузки БЗ в корзине
        wait.until(ExpectedConditions.visibilityOf(basketList));
    }


    // Удаление бланка заказа
    public void removeOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

        wait.until(ExpectedConditions.elementToBeClickable(blankRemove));
        blankRemove.click();
        String expectedMessageAfterRemove = "Ваша корзина пуста!";
        String actualMessageAfterRemove = basketIsEmpty.getText();
        Assert.assertEquals("Товар не удален из корзины", expectedMessageAfterRemove, actualMessageAfterRemove);
    }

    // Удаление всех БЗ из Корзины
    public void clearBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//*[@class='card found-cheaper']")));

        // Чистим корзину
        clearBasket.click();
        wait.until(ExpectedConditions.visibilityOf(emptyBasketMessage));

        String expectedMessageAfterRemove = "Ваша корзина пуста!";
        String actualMessageAfterRemove = basketIsEmpty.getText();
        Assert.assertEquals("Товар не удален из корзины", expectedMessageAfterRemove, actualMessageAfterRemove);
    }

    // Переход из корзины к бланкам заказов
    public void fromBasketToOrders() {
        toBasketFromHeader.click();
        ordersFromNavInBasket.click();
        var expectedUrl = "https://dev.allfdm.ru/orders";
        var actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к бланкам заказов не осуществлен", expectedUrl, actualUrl);
    }

    // Переход из корзины к оплаченным заказам
    public void fromBasketToPaidOrders() {
        toBasketFromHeader.click();
        paidOrdersFromNavInBasket.click();
        var expectedUrl = "https://dev.allfdm.ru/orders/paid";
        var actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к бланкам заказов не осуществлен", expectedUrl, actualUrl);
    }

    // Переход из корзины к образцам декоров
    public void fromBasketToSamples() {
        toBasketFromHeader.click();
        samplesFromNavInBasket.click();
        var expectedUrl = "https://dev.allfdm.ru/shop/sample-orders";
        var actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к бланкам заказов не осуществлен", expectedUrl, actualUrl);
    }


}
