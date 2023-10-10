package Ordering;

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

public class Checkout {

    public WebDriver driver;

    public Checkout(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Стоимость корзины
    @FindBy(xpath = "//*[@class='order-full-price']")
    private WebElement basketTotalPrice;

    // Кнопка "Перейти к оформлению"
    @FindBy(xpath = "//*[@data-type='go-to-checkout']")
    private WebElement goToCheckout;

    // Форма с личными данными клиента
    @FindBy(xpath = "//*[@class='personal-data personal-data-item personal']")
    private WebElement personalData;

    // Чекбокс с подтверждением города
    @FindBy(xpath = "//*[@for='cityDelivery']")
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

    // Кнопка "Оплатить картой" для ввода данных карты
    @FindBy(xpath = "//*[@data-test-id='payByCard']")
    private WebElement payButtonInEquirungForData;

    // Инпут для ввода номера карты
    @FindBy(xpath = "//*[@placeholder='0000 0000 0000 0000']")
    private WebElement inputCardNumber;

    // Инпут для ввода срока действия карты
    @FindBy(xpath = "//*[@placeholder='00/00']")
    private WebElement inputCardTerm;

    // Инпут для ввода CVV-кода карты
    @FindBy(xpath = "//*[@placeholder='000']")
    private WebElement inputCardCvv;

    // Кнопка "Оплатить картой" для оплаты
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement payButtonInEquirung;

    // Пароль для подтверждения оплаты
    @FindBy(xpath = "//*[@id='password']")
    private WebElement passForConfirm;

    // Уведомление об успешной оплате
    @FindBy(xpath = "//*[@class='card-body text-wrap']/h1")
    private WebElement success;

    String cardNumber = "4111111111111111";
    String cardTerm = "1224";
    String cardCvv = "123";


    public void checkOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String totalPriceInBasket = basketTotalPrice.getText()
                .replace(",00 руб.", "").strip().replace(" ", "");
        js.executeScript("arguments[0].scrollIntoView();", goToCheckout);
        goToCheckout.click();

        wait.until(ExpectedConditions.visibilityOf(personalData));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String totalPriceInDetails = totalPriceInCheckout.getText()
                .replace("Стоимость заказа: ", "").replace(",00 руб.", "")
                .replace(" ", "").strip();
        Assert.assertEquals("Некорректная стоимость заказа в Доставке",
                totalPriceInBasket, totalPriceInDetails);

        String currentCityAtPage = cityAtPage.getText().strip();
        String cityAtDelivery = cityInCheckbox.getText().strip();
        Assert.assertEquals("Город доставки не совпадает с городом на сайте",
                currentCityAtPage, cityAtDelivery);

        confirmCityCheckbox.click();

        payInCheckoutBtn.click();
        wait.until(ExpectedConditions.visibilityOf(totalPriceInEquiring));

        String totalPriceEquiring = totalPriceInEquiring.getText().strip().replace(" ", "");
        Assert.assertEquals("Стоимость заказа на странице эквайринга отображается некорректно",
                totalPriceInDetails, totalPriceEquiring);

        payButtonInEquirungForData.click();
        inputCardNumber.sendKeys(cardNumber);
        inputCardTerm.sendKeys(cardTerm);
        inputCardCvv.sendKeys(cardCvv);

        payButtonInEquirung.click();
        wait.until(ExpectedConditions.visibilityOf(passForConfirm));

        passForConfirm.sendKeys("12345678");
        wait.until(ExpectedConditions.visibilityOf(success));

        int successCode = 0;
        String actualText = success.getText().strip();
        String regex = "Заказ №К-" + "[0-9]*" + " успешно оплачен";
        if (actualText.matches(regex)) {
            successCode = 1;
        }
        Assert.assertEquals("Заказ не оплачен", 1, successCode);
    }
}
