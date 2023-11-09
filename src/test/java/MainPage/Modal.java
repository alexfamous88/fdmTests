package MainPage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Modal {

    public WebDriver driver;
    public Modal(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='closeSelectCityModal']")
    private WebElement cityBtnClose;

    @FindBy(xpath = "//*[@id='select-city-modal']")
    private WebElement modalLocator;

    public void closeModalCity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(cityBtnClose));
        try {
            cityBtnClose.click();
            wait.until(ExpectedConditions.invisibilityOf(modalLocator));
        } catch (TimeoutException e) {
            cityBtnClose.click();
            wait.until(ExpectedConditions.invisibilityOf(modalLocator));
        }
    }
}
