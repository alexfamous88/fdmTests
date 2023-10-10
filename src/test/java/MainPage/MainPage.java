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

public class MainPage {

    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Локаторы

    // Модальное окно выбора города
    @FindBy(xpath = "//*[@id='closeSelectCityModal']")
    private WebElement cityBtnClose;


    // Промо-раздел
    @FindBy(xpath = "//*[@class='item promo-1']")
    private WebElement catalogFromPromo;


    // "12 000 мастеров уже выбирают фасады на нашем сервисе"
    @FindBy(xpath = "//*[@class='main-crafts']//a")
    private WebElement regFromCrafts;


    // "Рассчитайте заказ в 3 простых шага"

    // Кнопки "Рассчитать заказ"
    @FindBy(xpath = "(//*[@class='main-steps']//a)[1]")
    private WebElement catalogFromStep1;

    @FindBy(xpath = "(//*[@class='main-steps']//a)[2]")
    private WebElement catalogFromStep2;

    @FindBy(xpath = "(//*[@class='main-steps']//a)[3]")
    private WebElement catalogFromStep3;

    // Переключатель шагов
    @FindBy(xpath = "//span[@class='num'][contains(text(),'1')]")
    private WebElement step1;

    @FindBy(xpath = "//span[@class='num'][contains(text(),'2')]")
    private WebElement step2;

    @FindBy(xpath = "//span[@class='num'][contains(text(),'3')]")
    private WebElement step3;


    //Блок производителей

    // Список на Главной
    @FindBy(xpath = "//a[@title='ФАСАДЕЛЬ']")
    private WebElement fasadel;

    @FindBy(xpath = "//a[@title='ДЕСКОР']")
    private WebElement deskor;

    @FindBy(xpath = "//a[@title='Мебель Холдинг']")
    private WebElement mebelHolding;

    @FindBy(xpath = "//a[@title='ЛОТОС-ЮГ']")
    private WebElement lotosYug;

    @FindBy(xpath = "//a[@title='ХАМЕЛЕОН']")
    private WebElement hameleon;

    @FindBy(xpath = "//a[@title='FABRICHE']")
    private WebElement fabriche;

    @FindBy(xpath = "//a[@title='ORWOOD']")
    private WebElement orwood;

    @FindBy(xpath = "//a[@title='ADELKREIS']")
    private WebElement adelkreis;

    @FindBy(xpath = "//a[@title='DEMFA']")
    private WebElement demfa;

    @FindBy(xpath = "//a[@title='СТАНДАРТ']")
    private WebElement standart;

    @FindBy(xpath = "//a[@title='Фасады Черноземья']")
    private WebElement fasadyChernozemya;

    @FindBy(xpath = "//a[@title='Томские мебельные фасады']")
    private WebElement tmf;

    @FindBy(xpath = "//a[@title='Кедр']")
    private WebElement kedr;

    // В каталоге
    @FindBy(xpath = "//a[contains(@class, 'active')]")
    private WebElement activeFactory;


    public void closeModalCity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(cityBtnClose));
        cityBtnClose.click();
    }

    public void fromPromoToCatalog() {
        String expectedUrl = "https://dev.allfdm.ru/material/select";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(catalogFromPromo));
        catalogFromPromo.click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход в Каталог из промо не осуществлен", expectedUrl, actualUrl);
    }

    public void fromMainPageToFasadel() {
        String expectedResult = fasadel.getAttribute("title").strip().toLowerCase();
        fasadel.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToDeskor() {
        String expectedResult = deskor.getAttribute("title").strip().toLowerCase();
        deskor.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToMebelHolding() {
        String expectedResult = mebelHolding.getAttribute("title").strip().toLowerCase();
        mebelHolding.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToLotosYug() {
        String expectedResult = lotosYug.getAttribute("title").strip().toLowerCase();
        lotosYug.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToHameleon() {
        String expectedResult = hameleon.getAttribute("title").strip().toLowerCase();
        hameleon.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToFabriche() {
        String expectedResult = fabriche.getAttribute("title").strip().toLowerCase();
        fabriche.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToOrwood() {
        String expectedResult = orwood.getAttribute("title").strip().toLowerCase();
        orwood.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToAdelkreis() {
        String expectedResult = adelkreis.getAttribute("title").strip().toLowerCase();
        adelkreis.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToDemfa() {
        String expectedResult = demfa.getAttribute("title").strip().toLowerCase();
        demfa.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToStandart() {
        String expectedResult = standart.getAttribute("title").strip().toLowerCase();
        standart.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToFasadyChernozemya() {
        String expectedResult = fasadyChernozemya.getAttribute("title").strip().toLowerCase();
        fasadyChernozemya.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToTmf() {
        String expectedResult = tmf.getAttribute("title").strip().toLowerCase();
        tmf.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
        driver.navigate().back();
    }

    public void fromMainPageToKedr() {
        String expectedResult = kedr.getAttribute("title").strip().toLowerCase();
        kedr.click();
        String actualResult = activeFactory.getText().strip().toLowerCase();
        Assert.assertEquals("Переход к фабрике " + expectedResult + " не осуществлен", expectedResult, actualResult);
    }

    public void registrationFromCrafts() {
        String expectedUrl = "https://dev.allfdm.ru/personal/registration";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@class='main-crafts']")));
        regFromCrafts.click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Переход на страницу регистрации из блока \"12 000 мастеров\" не осуществлен",
                expectedUrl, actualUrl);
    }

    public void from3stepsToCatalog() {
        String expectedUrl = "https://dev.allfdm.ru/material/select";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@class='main-steps']")));

        catalogFromStep1.click();
        Assert.assertEquals("Переход в каталог из блока \"Рассчитайте заказ в 3 простых шага\" не осуществлен",
                expectedUrl, driver.getCurrentUrl());
        driver.navigate().back();

        step2.click();
        catalogFromStep2.click();
        Assert.assertEquals("Переход в каталог из блока \"Рассчитайте заказ в 3 простых шага\" не осуществлен",
                expectedUrl, driver.getCurrentUrl());
        driver.navigate().back();

        step3.click();
        catalogFromStep3.click();
        Assert.assertEquals("Переход в каталог из блока \"Рассчитайте заказ в 3 простых шага\" не осуществлен",
                expectedUrl, driver.getCurrentUrl());
    }
}