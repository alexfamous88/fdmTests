package MainPage;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

public class MainFooter {

    public WebDriver driver;

    public MainFooter(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Закрыть окно выбора города
    @FindBy(xpath = "//*[@id='closeSelectCityModal']")
    private WebElement cityBtnClose;

    // Политика обработки персональных данных
    @FindBy(xpath = "(//*[@class='footer__politic']//a)[1]")
    private WebElement personalPolicy;

    // Политика конфиденциальности
    @FindBy(xpath = "(//*[@class='footer__politic']//a)[4]")
    private WebElement privacyPolicy;

    // Публичная оферта для юр. лиц
    @FindBy(xpath = "(//*[@class='footer__politic']//a)[2]")
    private WebElement offerLegalEntity;

    // Публичная оферта для физ. лиц
    @FindBy(xpath = "(//*[@class='footer__politic']//a)[5]")
    private WebElement offerIndividual;

    // Реквизиты
    @FindBy(xpath = "(//*[@class='footer__politic']//a)[3]")
    private WebElement requisites;

    // Страница с реквизитами
    @FindBy(xpath = "//*[@class='content-page']//h1")
    private WebElement requisitesTitle;

    // О нас
    @FindBy(xpath = "(//*[@class='footer__politic']//a)[6]")
    private WebElement aboutUs;

    // Страница "О нас"
    @FindBy(xpath = "//*[@class='content-header']//h1")
    private WebElement aboutUsTitle;

    // Авторские права
    @FindBy(xpath = "//*[@class='footer__copyright']")
    private WebElement copyright;


    public void closeModalCity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(cityBtnClose));
        cityBtnClose.click();
    }

    public void fromFooterToPersonalPolicy() {
        Set<String> oldWindowsSet = driver.getWindowHandles();
        String oldHandles = "";
        for (String s : oldWindowsSet) {
            oldHandles = s;
        }
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        personalPolicy.click();
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newHandle = "";
        for (String s : newWindowsSet) {
            newHandle = s;
        }
        driver.switchTo().window(newHandle);
        String expectedUrl = "https://dev.allfdm.ru/docs/personal.pdf?v3";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к файлу политики обработки персональных данных не осуществлен",
                expectedUrl, actualUrl);
        driver.close();
        driver.switchTo().window(oldHandles);
    }

    public void fromFooterToPrivacyPolicy() {
        Set<String> oldWindowsSet = driver.getWindowHandles();
        String oldHandles = "";
        for (String s : oldWindowsSet) {
            oldHandles = s;
        }
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        privacyPolicy.click();
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newHandle = "";
        for (String s : newWindowsSet) {
            newHandle = s;
        }
        driver.switchTo().window(newHandle);
        String expectedUrl = "https://dev.allfdm.ru/docs/privacy.pdf?v3";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к файлу политики конфиденциальности не осуществлен",
                expectedUrl, actualUrl);
        driver.close();
        driver.switchTo().window(oldHandles);
    }

    public void fromFooterToOfferLegalEntity() {
        Set<String> oldWindowsSet = driver.getWindowHandles();
        String oldHandles = "";
        for (String s : oldWindowsSet) {
            oldHandles = s;
        }
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        offerLegalEntity.click();
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newHandle = "";
        for (String s : newWindowsSet) {
            newHandle = s;
        }
        driver.switchTo().window(newHandle);
        String expectedUrl = "https://dev.allfdm.ru/docs/offer_legal_entity.pdf";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к файлу публичной оферты для юр. лиц не осуществлен",
                expectedUrl, actualUrl);
        driver.close();
        driver.switchTo().window(oldHandles);
    }

    public void fromFooterToOfferIndividual() {
        Set<String> oldWindowsSet = driver.getWindowHandles();
        String oldHandles = "";
        for (String s : oldWindowsSet) {
            oldHandles = s;
        }
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        offerIndividual.click();
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newHandle = "";
        for (String s : newWindowsSet) {
            newHandle = s;
        }
        driver.switchTo().window(newHandle);
        String expectedUrl = "https://dev.allfdm.ru/offer.pdf";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход к файлу публичной оферты для физ. лиц не осуществлен",
                expectedUrl, actualUrl);
        driver.close();
        driver.switchTo().window(oldHandles);
    }

    public void fromFooterToRequisites() {
        requisites.click();
        String expectedTitle = "Реквизиты";
        String actualTitle = requisitesTitle.getText().strip();
        Assert.assertEquals("Переход к реквизитам не осуществлен", expectedTitle, actualTitle);
        driver.navigate().back();
    }

    public void fromFooterToAboutUs() {
        aboutUs.click();
        String expectedTitle = "О нас";
        String actualTitle = aboutUsTitle.getText().strip();
        Assert.assertEquals("Переход к странице \"О нас\" не осуществлен", expectedTitle, actualTitle);
        driver.navigate().back();
    }

    public void footerCopyright() {
        String expectedCopyright = "© ООО «Все Фасады», " + LocalDate.now().getYear();
        String actualCopyright = copyright.getText();
        Assert.assertEquals("Текст авторских прав не соответствует ожидаемому",
                expectedCopyright, actualCopyright);
    }
}
