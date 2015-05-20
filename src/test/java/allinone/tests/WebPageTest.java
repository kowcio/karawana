/*
 * 
 */
package allinone.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import allinone.AbstractBaseTest;
import allinone.BrowserEnum;
import allinone.TestFalsePositiveReRunner;

/**
 * Testy weryfkujące poprawnośc działania strony głównej / frontu.
 * 
 * @author pkowalski
 *
 */
public class WebPageTest extends AbstractBaseTest {

	private WebPageTest() {
	}

	@Factory ( dataProvider = "Test_all_5_browsers_x1")
	public WebPageTest(WebDriver driver, BrowserEnum browser) {

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
	@Test ( invocationCount = INVOCATION_COUNT, retryAnalyzer = TestFalsePositiveReRunner.class, enabled = false)
	public void checkMainPageLink() {

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
