package MainPage;

import org.junit.Assert;
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
    private static String expectedNumberInAccount = "+7 (" + numbers[0] + numbers[1] + numbers[2] + ") " +
            numbers[3] + numbers[4] + numbers[5] + "-" + numbers[6] + numbers[7] + "-" + numbers[8] + numbers[9];

    public WebDriver driver;
    public Authorization(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Локаторы
    @FindBy(xpath = "//*[@class='header__user-links']")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id='phoneInput']")
    private WebElement numberInput;

    @FindBy(xpath = "//*[@class='col center']//button")
    private WebElement loginBtnAfterNum;

    @FindBy(xpath = "//*[@id='codeInput']")
    private WebElement codeInput;

    @FindBy(xpath = "//*[@class='center']//button")
    private WebElement loginBtnAfterCode;

    @FindBy(xpath = "//*[@class='g-icon-text']/a[1]")
    private WebElement myAccount;

    @FindBy(xpath = "(//*[@class='table-wrap'])[2]//td[2]")
    private WebElement numberInAccount;

    @FindBy(xpath = "(//*[@class='show-button card-button'])[1]//a")
    private WebElement showHidden;

    public void openAuth() {
        loginBtn.click();
    }

    public void inputNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(numberInput));
        int i = 0;
        do {
            numberInput.sendKeys(numbers[i]);
            i++;
        } while (i < numbers.length);
    }

    public void confirmNumber() {
        loginBtnAfterNum.click();
    }

    public void inputCode() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(codeInput));
        codeInput.sendKeys(code);
    }

    public void clickToLogin() {
        loginBtnAfterCode.click();
    }

    public void isLoginSuccess() {
        myAccount.click();
        showHidden.click();
        Assert.assertEquals("Авторизация не успешна", expectedNumberInAccount, numberInAccount.getText());
    }
}