package com.bars;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        String browser = new File(LoginTest.class.getResource("/IEDriverServer.exe").getFile()).getPath();
        System.setProperty("webdriver.ie.driver", browser);
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://10.10.17.22:8080/barsroot/account/login/");
//        driver.get("http://10.10.17.50:8080/barsroot/account/login/");
//        driver.get("http://10.10.17.40:8080/barsroot/account/login/");
//        driver.get("http://10.10.17.40:8082/barsroot/account/login/");
    }
    private void userDelay(int time) {
        try {Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace(); }
    }

    @Test
    public void userLogin() {
        WebElement loginField = driver.findElement(By.id("txtUserName"));
        loginField.clear();
        loginField.sendKeys("absadm01");
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("qwerty");
        WebElement loginButton = driver.findElement(By.id("btLogIn"));
        loginButton.click();
        WebElement ProdovjButton = driver.findElement(By.xpath("//input[@value = 'Продовжити']"));
        (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(ProdovjButton));
        ProdovjButton.click();

        driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
        userDelay(1000);
        WebElement H1 = driver.findElement(By.xpath(".//*[text()='Оголошення']"));
        (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(H1));
        driver.switchTo().defaultContent();

                        //!!!!Довідник спецпараметрів!!!!
        userDelay(2000);
        WebElement findOpers = driver.findElement(By.id("findOpersText"));
        findOpers.clear();
        findOpers.sendKeys("спецпарам");
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ENTER).perform();
        WebElement ForexSpPar = driver.findElement(By.xpath("//div[@id ='oper-1679']/div[2]/span"));
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(ForexSpPar));
        ForexSpPar.click();
        userDelay(1000);

                        //!!!! Перехід на фрейм!!!!
        driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
        userDelay(1000);
        WebElement FilterWindow = driver.findElement(By.xpath(".//*[text()='Фільтр перед населенням таблиці']"));
        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.visibilityOf(FilterWindow));

        WebElement FurtherButton = driver.findElement(By.xpath("(//*[@class='x-btn-inner x-btn-inner-center'])[text()='Далі']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(FurtherButton));
        FurtherButton.click();
        userDelay(4000);

                        // !!!!Довідник спецпараметрів!!!!-!!!!Додавання рядка!!!!!
        WebElement AddRow = driver.findElement(By.cssSelector("a[data-qtip='Додати новий рядок']"));
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.visibilityOf(AddRow));
        AddRow.click();
        userDelay(1000);
        WebElement CelectNewRow = driver.findElement(By.xpath("(//*[@class='x-grid-cell-inner x-grid-cell-inner-row-numberer'])[text()='*']"));
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.visibilityOf(CelectNewRow));
        builder.doubleClick(CelectNewRow).perform();

        WebElement DropPar = driver.findElement(By.xpath("(//*[@class = 'x-trigger-index-0 x-form-trigger x-form-arrow-trigger x-form-trigger-first'])[1]"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(DropPar));
        DropPar.click();
        WebElement CoosePar = driver.findElement(By.className("x-boundlist-item"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(CoosePar));
        CoosePar.click();

        WebElement DropBalrah = driver.findElement(By.xpath("(//*[@class = 'x-trigger-index-0 x-form-trigger x-form-arrow-trigger x-form-trigger-first'])[2]"));
        DropBalrah.click();
        WebElement CooseRah = driver.findElement(By.xpath("(//*[@class='x-boundlist-list-ct x-unselectable'])[2]/div[4]"));
        CooseRah.click();
        WebElement SpecPar = driver.findElement(By.name("VALUE"));
        SpecPar.clear();
        SpecPar.sendKeys("99A");

        WebElement SaveSPbut = driver.findElement(By.xpath("//*[@class = 'x-btn x-row-editor-update-button x-unselectable x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"));
        SaveSPbut.click();

        WebElement OnovWindow = driver.findElement(By.xpath(".//*[text()='Оновлення даних']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(OnovWindow));
        WebElement OnovOK = driver.findElement(By.xpath("(//*[@class='x-btn-inner x-btn-inner-center'])[text()='OK']"));
        OnovOK.click();
        userDelay(4000);

                            // !!!!Довідник спецпараметрів!!!!-!!!!!Редагування рядка!!!!!
        WebElement CelectRow = driver.findElement(By.xpath("(//*[@class='x-grid-cell-inner '])[text()='99A']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(CelectRow));
        builder.doubleClick(CelectRow).perform();
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(CelectRow));
       DropPar.click();
       CoosePar.click();
       DropBalrah.click();
       CooseRah.click();
       SpecPar.clear();
       SpecPar.sendKeys("98A");
       SaveSPbut.click();
       (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(OnovWindow));
        OnovWindow.click();
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(OnovOK));
        OnovOK.click();
        userDelay(2000);

                            //!!!!Довідник спецпараметрів!!!!-!!!!Видалення рядка!!!!
        WebElement CelectRow1 = driver.findElement(By.xpath("(//*[@class='x-grid-cell-inner '])[text()='98A']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(CelectRow1));
        CelectRow1.click();
        WebElement DelRow = driver.findElement(By.cssSelector("a[data-qtip='Видалити вибраний рядок']"));
        DelRow.click();
        userDelay(1000);

        WebElement DelRowWindow = driver.findElement(By.xpath(".//*[text()='Видалення рядка']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(DelRowWindow));
        DelRowWindow.click();
        WebElement TakButton = driver.findElement(By.xpath("(//*[@class='x-btn-inner x-btn-inner-center'])[text()='Так']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(TakButton));
        TakButton.click();
        WebElement DelConf = driver.findElement(By.xpath("(//*[@class='x-header-text x-window-header-text x-window-header-text-default'])[text()='Видалення']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(DelConf));
        DelConf.click();
        OnovOK.click();
    }
    @AfterClass
    public static void tearDown() {
        driver.switchTo().defaultContent();
        WebElement profileButton = driver.findElement(By.id("btnProfile"));
        profileButton.click();
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id='userProfile']/div[2]/a[2]"));
        logoutButton.click();
        driver.quit();
    }
}
