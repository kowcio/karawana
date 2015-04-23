/*
 * 
 */
package allinone.tests;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import allinone.AbstractBaseTest;
import allinone.BrowserEnum;
import allinone.GeneratorPeselDowod;
import allinone.TestFalsePositiveReRunner;
import allinone.pages.RLMainPage;

/**
 * Testy weryfkujące poprawnośc działania strony głównej / frontu.
 * 
 * @author pkowalski
 *
 */
public class RLMainPageTest extends AbstractBaseTest {
    
    private RLMainPageTest() {
    }
    
    @Factory(dataProvider = "Test_all_5_browsers_x1")
    public RLMainPageTest(WebDriver driver, BrowserEnum browser) {
        
        try {
            log.info("FACTORY Browser =  => " + browser.toString());
            this.browser = browser;
            this.browserName = browser.getBrowserName() + "_" + browser.getBrowserVersion();
            this.browser.getBrowserCapabilities().setVersion(browser.getBrowserVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sprawdzenie kodu linków na stronie głównej (200 OK)
     */
    @Test(invocationCount = INVOCATION_COUNT, retryAnalyzer = TestFalsePositiveReRunner.class, enabled = false)
    public void checkMainPageLink() {
        
        new RLMainPage("qwe");
        closePopover();
        testLinksOnCurrentPage();
        
    }
    
   
    
    /**
     * Test rejestracji lekarza. TODO - test wyłączony
     */
    @Test(invocationCount = INVOCATION_COUNT, retryAnalyzer = TestFalsePositiveReRunner.class, enabled = true)
    public void registerRLDoctorTest() {
        
        new RLMainPage("qwe" + "/konto/rejestracja/lekarz/");
        setCookiesPopoverOn1ForRLTest("rl.test.mijasoftware.com");
        closePopover();
        new RLMainPage("qwe" + "/konto/rejestracja/lekarz/");
        
        // FILL in da form
        $("#id_doctor_title_chosen").click();
        $(By.xpath(".//*[@id='id_doctor_title_chosen']/div/div/input")).sendKeys("mgr" + Keys.ENTER);
        
        $("#id_doctor-first_name").sendKeys("mg qwe 12r");
        $("#id_doctor-last_name").sendKeys("mg qwe 12r");
        $("#id_doctor_main_specialization_chosen").click();
        $(By.xpath(".//*[@id='id_doctor_main_specialization_chosen']/div/div/input"))
                .sendKeys("Alergolog" + Keys.ENTER);
        String mail = getRandomTestEmail();
        $("#id_doctor-email").sendKeys(mail);
        $("#id_doctor-contact_phone").sendKeys("512512512");
        $("#id_doctor-pesel").sendKeys(GeneratorPeselDowod.getPesel(1));
        
        // UPLOAD FILE
        // String file = "/src/test/resources/testData/" + (new
        // Random().nextInt(5) + 1) + ".jpg";
        String file = "C:\\Users\\pkowalski\\Downloads\\" + (new Random().nextInt(5) + 1) + ".jpg";
        log.info("File we want to upload = " + file);
        
        $("#id_doctor-license").sendKeys(file);
        $(".delete").waitUntil(visible, WAIT);
        $(".label_check").click();
        $(by("value", "Utwórz konto lekarza")).waitUntil(visible, WAIT).shouldBe(visible).click();
        log.info("Registered on = {} ", mail);
        Assert.assertTrue($(".dialog-content").waitUntil(visible, WAIT).text().contains("Dziękujemy za rejestrację!"));
        
        
        
        
        
        
        
        
        
        
        
    }
    
    /**
     * before
     */
    @BeforeMethod
    public void beforeClass() {
        super.before();
    }
    
    /**
     * after
     */
    @AfterMethod
    public void afterClass() {
        super.after();
    }
    
}
