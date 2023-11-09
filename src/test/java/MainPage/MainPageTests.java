package MainPage;

import jdk.jfr.Description;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainPageTests {

    public static Modal modal;
    public static Authorization authorization;
    public static MainHeader mainHeader;
    public static MainPage mainPage;
    public static MainFooter mainFooter;
    public static Feedback feedback;
    public static WebDriver driver;
    public static String startPage = "https://allfdm.ru/";


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        modal = new Modal(driver);
        authorization = new Authorization(driver);
        mainHeader = new MainHeader(driver);
        mainPage = new MainPage(driver);
        mainFooter = new MainFooter(driver);
        feedback = new Feedback(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(startPage);
    }


    @Test
    public void login_isSuccess_Test() {
        modal.closeModalCity();
        authorization.openAuth();
        authorization.inputNumber();
        authorization.confirmNumber();
        authorization.inputCode();
        authorization.clickToLogin();
        authorization.isLoginSuccess();
    }

    @Test
    public void mainHeader_Navigation_withoutAuthorization() {
        // Проверка работы навигации в хэдере
        modal.closeModalCity();
        mainHeader.fromNavToCatalog();
        mainHeader.fromNavToDelivery();
        mainHeader.fromNavToPayment();
        mainHeader.fromNavToReturning();
        mainHeader.fromNavToLogin();
        mainHeader.fromNavToRegistration();
    }

    @Test
    public void mainHeader_Navigation_withAuthorization() {
        // Авторизация на сайте
        modal.closeModalCity();
        authorization.openAuth();
        authorization.inputNumber();
        authorization.confirmNumber();
        authorization.inputCode();
        authorization.clickToLogin();

        // Проверка работы навигации в хэдере
        mainHeader.fromNavToCatalog();
        mainHeader.fromNavToDelivery();
        mainHeader.fromNavToPayment();
        mainHeader.fromNavToReturning();
        mainHeader.fromNavToBasket();
        mainHeader.fromNavToOrders();
        mainHeader.logOutFromNav();
    }

    @Test
    public void mainPage_Promo_ActualLink() {
        modal.closeModalCity();
        mainPage.fromPromoToCatalog();
    }

    @Test
    public void mainPage_Producers_Links() {
        modal.closeModalCity();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        mainPage.fromMainPageToFasadel();
        mainPage.fromMainPageToDeskor();
        mainPage.fromMainPageToMebelHolding();
        mainPage.fromMainPageToLotosYug();
        mainPage.fromMainPageToHameleon();
        mainPage.fromMainPageToFabriche();
        mainPage.fromMainPageToOrwood();
        mainPage.fromMainPageToAdelkreis();
        mainPage.fromMainPageToDemfa();
        mainPage.fromMainPageToStandart();
        mainPage.fromMainPageToFasadyChernozemya();
        mainPage.fromMainPageToTmf();
        mainPage.fromMainPageToKedr();
    }

    @Test
    public void mainPage_Crafters_Link() {
        modal.closeModalCity();
        mainPage.registrationFromCrafts();
    }

    @Test
    public void mainPage_3steps_Links() {
        modal.closeModalCity();
        mainPage.from3stepsToCatalog();
    }

    @Test
    public void mainPage_Footer_Links() {
        modal.closeModalCity();
        mainFooter.fromFooterToPersonalPolicy();
        mainFooter.fromFooterToPrivacyPolicy();
        mainFooter.fromFooterToOfferLegalEntity();
        mainFooter.fromFooterToOfferIndividual();
        mainFooter.fromFooterToRequisites();
        mainFooter.fromFooterToAboutUs();
        mainFooter.footerCopyright();
    }

    @Test
    public void mainPage_Feedback_Success() {
        modal.closeModalCity();
        feedback.scrollToFeedbackForm();
        feedback.setName("Тестер");
        feedback.setTel("89991234569");
        feedback.setMessage("Тестовое сообщение");
        feedback.sendMessage();
        feedback.sendOneMoreMessage();
        feedback.fromFormToPersonalPolicy();
    }


    @After
    public void tearDown() throws IOException {

        // Для снятия скриншота
        try {
            var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(
                    "./screenshots/screenshot.png"));
        } catch (UnknownError e) {
            var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(
                    "./screenshots/screenshot.png"));
        } finally {
            driver.quit();
        }
    }
}