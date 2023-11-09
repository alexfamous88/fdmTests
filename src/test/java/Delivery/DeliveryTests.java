package Delivery;

import Authorization.Authorization;
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

public class DeliveryTests {

    public static Authorization authorization;
    public static Terms terms;
    public static WebDriver driver;
    public static String startPage = "https://allfdm.ru/";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        authorization = new Authorization(driver);
        terms = new Terms(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(startPage);
    }


    @Test
    public void checkTerms() {
        authorization.authSimple();
        terms.checkDeliveryTerms();
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
