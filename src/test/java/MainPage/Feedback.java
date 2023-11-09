package MainPage;

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
import java.util.Set;

public class Feedback {

    public WebDriver driver;
    private final String expectedSuccessTitle = "Сообщение отправлено!";
    private final String expectedFormTitle = "Мы всегда с вами на связи";


    public Feedback(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Локатор блока обратной связи
    @FindBy(xpath = "//*[@class='main-feedback']")
    private WebElement feedbackForm;

    // Инпут ввода имени
    @FindBy(xpath = "//*[@class='contacts']/*[@name='name']")
    private WebElement nameInput;

    // Инпут ввода телефона
    @FindBy(xpath = "//*[@class='contacts']/*[@name='tel']")
    private WebElement telInput;

    // Инпут ввода сообщения
    @FindBy(xpath = "//*[@class='message']/*")
    private WebElement textInput;

    // Кнопка отправки сообщения
    @FindBy(xpath = "//*[@class='send-btn']")
    private WebElement sendBtn;

    // Уведомление об успешной отправке сообщения
    @FindBy(xpath = "//*[@class='success-message']/*[@class='title']")
    private WebElement successMessage;

    // Кнопка "Отправить еще сообщение"
    @FindBy(xpath = "//*[@class='close-btn']")
    private WebElement oneMoreMessage;

    // Заголовок формы обратной связи
    @FindBy(xpath = "//*[@class='main-feedback-form']/*[@class='title']")
    private WebElement feedbackTitle;

    // Локатор закрытия окна с выбором города
    @FindBy(xpath = "//*[@id='closeSelectCityModal']")
    private WebElement cityBtnClose;

    // Ссылка на политику персональных данных
    @FindBy(xpath = "//*[@for='agreement-checkbox']/*[@href='/docs/personal.pdf?v3']")
    private WebElement personalLink;


    public void scrollToFeedbackForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView();", feedbackForm);
        wait.until(ExpectedConditions.visibilityOf(feedbackForm));
    }

    public void setName(String name) {
        nameInput.sendKeys(name);
    }

    public void setTel(String tel) {
        telInput.sendKeys(tel);
    }

    public void setMessage(String message) {
        textInput.sendKeys(message);
    }

    public void sendMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        sendBtn.click();
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        var actualSuccessTitle = successMessage.getText();
        Assert.assertEquals("Сообщение не отправлено", expectedSuccessTitle, actualSuccessTitle);
    }

    public void sendOneMoreMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        oneMoreMessage.click();
        wait.until(ExpectedConditions.visibilityOf(feedbackTitle));
        var actualFormTitle = feedbackTitle.getText();
        Assert.assertEquals("Не удалось отправить еще одно сообщение", expectedFormTitle, actualFormTitle);
    }

    public void fromFormToPersonalPolicy() {

        Set<String> oldWindowsSet = driver.getWindowHandles();
        String oldHandles = "";
        for (String s : oldWindowsSet) {
            oldHandles = s;
        }

        scrollToFeedbackForm();
        personalLink.click();

        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newHandle = "";
        for (String s : newWindowsSet) {
            newHandle = s;
        }
        driver.switchTo().window(newHandle);
        String expectedUrl = "https://allfdm.ru/docs/personal.pdf?v3";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к файлу политики обработки персональных данных не осуществлен",
                expectedUrl, actualUrl);
        driver.close();
        driver.switchTo().window(oldHandles);
    }
}
