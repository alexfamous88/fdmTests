package Ordering;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkout {

    public WebDriver driver;

    public Checkout(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Стоимость корзины
    @FindBy(xpath = "//*[@class='order-full-price']")
    private WebElement basketTotalPrice;

    // Стоимость корзины со скидкой
    @FindBy(xpath = "//*[@class='order-promo-price text-orange']")
    private WebElement basketDiscountTotalPrice;

    // Кнопка "Перейти к оформлению"
    @FindBy(xpath = "//*[@data-type='go-to-checkout']")
    private WebElement goToCheckout;

    // Чекбокс с подтверждением города
    @FindBy(xpath = "//*[@class='custom-control custom-checkbox']//label")
    private WebElement confirmCityCheckbox;

    // Город в чекбоксе
    @FindBy(xpath = "//*[contains(@class, 'custom-checkbox')]//strong")
    private WebElement cityInCheckbox;

    // Город на сайте
    @FindBy(xpath = "//*[@id='select-city']/a")
    private WebElement cityAtPage;

    // Стоимость заказа на этапе деталей доставки
    @FindBy(xpath = "//*[@class='card-footer']/*[@class='h4']")
    private WebElement totalPriceInCheckout;

    // Кнопка "Оплатить"
    @FindBy(xpath = "//*[@class='btn btn-lg btn-success w-100']")
    private WebElement payInCheckoutBtn;

    // Стоимость заказа на этапе оплаты
    @FindBy(xpath = "//*[@class='amount__major']")
    private WebElement totalPriceInEquiring;


    public void checkOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        /**
         * Фиксируем стоимость корзины
          */
        String totalPriceInBasket;
        try {
            totalPriceInBasket = basketTotalPrice.getText()
                    .replace(",00 руб.", "").strip().replace(" ", "");
            js.executeScript("arguments[0].scrollIntoView();", goToCheckout);

            /**
             * Если была применена акция
             */
        } catch (NoSuchElementException e) {
            totalPriceInBasket = basketDiscountTotalPrice.getText()
                    .replace(",00 руб.", "").strip().replace(" ", "");
            js.executeScript("arguments[0].scrollIntoView();", goToCheckout);
        }

        /**
         * Переходим к оформлению заказа
         */
        goToCheckout.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /**
         * Фиксируем стоимость заказа на этапе оформления
         */
        String totalPriceInDetails = totalPriceInCheckout.getText()
                    .replace("Стоимость заказа: ", "").replace(",00 руб.", "")
                    .replace(" ", "").strip();

        /**
         * Сравниваем стоимость корзины и стоимость заказа на этапе оформления: значения должны совпадать
         */
        Assert.assertEquals("Некорректная стоимость заказа в Доставке",
                    totalPriceInBasket, totalPriceInDetails);

        /**
         * Сравниваем город, указанный пользователем на сайте с городом, указанным в деталях заказа: значения должны совпадать
         */
        String currentCityAtPage = cityAtPage.getText().strip();
        String cityAtDelivery = cityInCheckbox.getText().strip();
        Assert.assertEquals("Город доставки не совпадает с городом на сайте",
                currentCityAtPage, cityAtDelivery);

        /**
         * Подтверждаем город доставки
         */
        confirmCityCheckbox.click();

        /**
         * Переходим на страницу оплаты заказа
         */
        payInCheckoutBtn.click();
        wait.until(ExpectedConditions.visibilityOf(totalPriceInEquiring));

        /**
         * Сравниваем стоимость заказа на странице оплаты с корректной стоимостью заказа
         */
        String totalPriceEquiring = totalPriceInEquiring.getText().strip().replace(" ", "");
        Assert.assertEquals("Стоимость заказа на странице эквайринга отображается некорректно",
                totalPriceInDetails, totalPriceEquiring);
    }
}