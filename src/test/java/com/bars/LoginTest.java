package com.bars;

import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit.TextReport;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginTest {
    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests().to("test-results/reports");
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(false);

    @BeforeClass
    public static void setup() {
        timeout = 10000;
        browser = "ie";
        startMaximized = true;
//        System.setProperty("webdriver.ie.driver", ".\\IEDriverServer.exe");
        InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER).arch32().setup();
//        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        open("/");

    }

    @Test
    public void userLogin() {
        ActionsWithRow actionsWithRow = page(ActionsWithRow.class);
        $("#txtUserName").setValue("absadm01");
        $("#txtPassword").setValue("qwerty").pressEnter();
//        $("#btLogIn").click();
        $("#btChangDate").shouldBe(visible).click();
        switchTo().frame($("#mainFrame"));
        $(By.tagName("h1")).shouldHave(text("Оголошення"));
        switchTo().defaultContent();
        $(".btn_branches").shouldBe(visible).click();
        $(byXpath("//div[@id='treeview']/ul/li/ul/li/div/span[2]/span")).shouldBe(visible).shouldHave(text("300465")).click();
        getWebDriver().navigate().refresh();

                        //!!!!Довідник спецпараметрів!!!!
        $("#findOpersText").setValue("спецпарам").pressEnter();
        $x("//div[@id ='oper-1679']/div[2]/span").shouldBe(visible).click();


                        //!!!! Перехід на фрейм!!!!+ фільтр перед населенням
        switchTo().frame($("#mainFrame"));
        $(byXpath("(//*[@class='x-btn-inner x-btn-inner-center'])[text()='Скасувати фільтри']")).shouldBe(visible).click();
        $(byXpath("(//*[@class='x-btn-inner x-btn-inner-center'])[text()='Далі']")).shouldBe(visible).click();

                               // !!!!Довідник спецпараметрів!!!!-!!!!Додавання рядка!!!!!

        actionsWithRow.addRow();
        actionsWithRow.changeRow("99A");

                            // !!!!Довідник спецпараметрів!!!!-!!!!!Редагування рядка!!!!!
        actionsWithRow.chooseRow("99A");
        actionsWithRow.changeRow("98A");

                            //!!!!Довідник спецпараметрів!!!!-!!!!Видалення рядка!!!!
        actionsWithRow.DelRow("98A");
    }

}
