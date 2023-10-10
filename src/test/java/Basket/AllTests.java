package Basket;

import Authorization.Authorization;
import Factories.Pvh.FabrichePvh;
import Factories.Pvh.KedrPvh;
import Ordering.Checkout;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AllTests {

    public static Authorization authorization;
    public static FabrichePvh fabrichePvh;
    public static Checkout checkout;
    public static Basket basket;
    public static WebDriver driver;
    public static String startPage = "https://dev.allfdm.ru/";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        fabrichePvh = new FabrichePvh(driver);
        authorization = new Authorization(driver);
        checkout = new Checkout(driver);
        basket = new Basket(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(startPage);
    }


    @Test
    public void showOrder() {
        authorization.authSimple();
        fabrichePvh.addToBasket();
        basket.showBlank();
        basket.clearBasket();
    }

    @Test
    public void editOrder() {
        authorization.authSimple();
        fabrichePvh.addToBasket();
        basket.editBlank();
        basket.clearBasket();
    }

    @Test
    public void removeOrder() {
        authorization.authSimple();
        fabrichePvh.addToBasket();
        basket.removeOrder();
    }

    @Test
    public void navToOrders() {
        authorization.authSimple();
        basket.fromBasketToOrders();
    }

    @Test
    public void navToPaidOrders() {
        authorization.authSimple();
        basket.fromBasketToPaidOrders();
    }

    @Test
    public void navToSamples() {
        authorization.authSimple();
        basket.fromBasketToSamples();
    }


    @After
    public void tearDown() throws IOException {

        // Для снятия скриншота
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(
                "./screenshots/screenshot.png"));

        driver.quit();
    }
}