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
import allinone.pages.MainTestPage;

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
        
        MainTestPage mainPage = new MainTestPage(URL_MAIN);
        closePopover();
        testLinksOnCurrentPage();
        
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
