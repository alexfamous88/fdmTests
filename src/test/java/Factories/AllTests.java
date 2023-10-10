package Factories;

import Authorization.Authorization;
import Basket.Basket;
import Factories.Agt.LotosAgt;
import Factories.Akril.AdelkreisAkril;
import Factories.Akril.TmfAkril;
import Factories.Frames.MhFrames;
import Factories.Painted.*;
import Factories.Plastic.KedrPlastic;
import Factories.Plastic.LotosPlastic;
import Factories.Plastic.TmfPlastic;
import Factories.Pvh.*;
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

    public static WebDriver driver;
    public static String startPage = "https://dev.allfdm.ru/";
    public static Authorization authorization;
    public static Basket basket;
    public static KedrPvh kedrPvh;
    public static StandartPvh standartPvh;
    public static FeChePvh feChePvh;
    public static FabrichePvh fabrichePvh;
    public static TmfPvh tmfPvh;
    public static KedrPlastic kedrPlastic;
    public static TmfPlastic tmfPlastic;
    public static LotosPlastic lotosPlastic;
    public static TmfPainted tmfPainted;
    public static LotosPainted lotosPainted;
    public static HameleonPainted hameleonPainted;
    public static FasadelPainted fasadelPainted;
    public static DemfaPainted demfaPainted;
    public static TmfAkril tmfAkril;
    public static AdelkreisAkril adelkreisAkril;
    public static LotosAgt lotosAgt;
    public static MhFrames mhFrames;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        authorization = new Authorization(driver);
        basket = new Basket(driver);
        kedrPvh = new KedrPvh(driver);
        standartPvh = new StandartPvh(driver);
        feChePvh = new FeChePvh(driver);
        fabrichePvh = new FabrichePvh(driver);
        tmfPvh = new TmfPvh(driver);
        kedrPlastic = new KedrPlastic(driver);
        tmfPlastic = new TmfPlastic(driver);
        lotosPlastic = new LotosPlastic(driver);
        tmfPainted = new TmfPainted(driver);
        lotosPainted = new LotosPainted(driver);
        hameleonPainted = new HameleonPainted(driver);
        fasadelPainted = new FasadelPainted(driver);
        demfaPainted = new DemfaPainted(driver);
        tmfAkril = new TmfAkril(driver);
        adelkreisAkril = new AdelkreisAkril(driver);
        lotosAgt = new LotosAgt(driver);
        mhFrames = new MhFrames(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(startPage);
    }

    @Test
    public void kedrPvhAddToBasket() {
        authorization.authSimple();
        kedrPvh.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void standartPvhAddToBasket() {
        authorization.authSimple();
        standartPvh.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void feChePvhAddToBasket() {
        authorization.authSimple();
        feChePvh.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void fabrichePvhAddToBasket() {
        authorization.authSimple();
        fabrichePvh.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void tmfPvhAddToBasket() {
        authorization.authSimple();
        tmfPvh.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void kedrPlasticAddToBasket() {
        authorization.authSimple();
        kedrPlastic.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void tmfPlasticAddToBasket() {
        authorization.authSimple();
        tmfPlastic.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void lotosPlasticAddToBasket() {
        authorization.authSimple();
        lotosPlastic.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void tmfPaintedAddToBasket() {
        authorization.authSimple();
        tmfPainted.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void lotosPaintedAddToBasket() {
        authorization.authSimple();
        lotosPainted.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void hameleonPaintedAddToBasket() {
        authorization.authSimple();
        hameleonPainted.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void fasadelPaintedAddToBasket() {
        authorization.authSimple();
        fasadelPainted.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void demfaPaintedAddToBasket() {
        authorization.authSimple();
        demfaPainted.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void tmfAkrilAddToBasket() {
        authorization.authSimple();
        tmfAkril.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void adelkreisAkrilAddToBasket() {
        authorization.authSimple();
        adelkreisAkril.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void lotosAgtAddToBasket() {
        authorization.authSimple();
        lotosAgt.addToBasket();
        basket.clearBasket();
    }

    @Test
    public void mhFramesAddToBasket() {
        authorization.authSimple();
        mhFrames.addToBasket();
        basket.clearBasket();
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