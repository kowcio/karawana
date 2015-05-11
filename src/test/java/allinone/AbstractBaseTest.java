/*
 * 
 */
package allinone;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;



import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

/**
 * Klasa główna do budowania klas obiektów stron oraz testów.
 * 
 * @author pkowalski
 *
 */
public abstract class AbstractBaseTest {
    
    private static final String DOMAIN_NAME = "dimain_name";
	// FOR ALL
    public static final String       PASSWORD                    = "TODO";
    public static final int          CODE_FAIL                   = 400;
    public static final int          CODE_SUCCESS                = 200;
    public static final List<String> CITIES                      = Arrays.asList("Sopot", "Gdynia", "Warszawa",
                                                                         "Olsztyn", "Gdańsk", "Wrocław", "Łódź",
                                                                         "Elbląg", "Bydgoszcz");
    public static final List<String> NAMES                       = Arrays.asList("Jan-Kowalski", "Marek", "Jan",
                                                                         "Nowak", "Przemysław", "Jarek");

    
    public static final List<String> PESELS                      = Arrays.asList("79060804378", "55021562501");
    
    public static final List<String> NIPS                        = Arrays.asList("8779458829", "3551368335",
                                                                         "5298454644", "9519767646", "5218992872",
                                                                         "1754767418", "4974399711", "4536776361",
                                                                         "6124517237", "4987378183");
    
    public static final String       TEST_CARD_NUMBER            = "4242424242424242";
    public static final String       LOGIN            = "4242424242424242";
    public static final String       URL_MAIN            = "4242424242424242";
    
    
    // webdrivery
    public WebDriver                 driver;
    
    public static org.slf4j.Logger   log                         = LoggerFactory.getLogger(AbstractBaseTest.class);
    public final static int          TEST_RERUN_NUMBER           = 1;
    public final static int          MAX_RETRY_COUNT             = 1;
    public final static Long         BROWSER_TIMEOUT             = 45000l;
    public final static int          INVOCATION_COUNT            = 1;
    
    public final static int          WAIT                        = 10000;
    // Zmienne opisu systemu
    public String                    browserName;
    public String                    userName;
    
    public Utils                     utils                       = new Utils();
    public DBUtils                   dbUtils                     = new DBUtils();
    
    public UsersENUM                 user;
    public BrowserEnum               browser;
    
    // cloud03
    //public String                    HUB_IP                      = "137.116.245.246";
     public String HUB_IP = "localhost";
    
    // performance
    public long                      startTime;
    public long                      endTime;
    
    // help vars
    public String                    defaultString               = "";
    public static final String       SERVICE_NAME_TEST           = "ServiceNameTest_";
    
    public AbstractBaseTest() {
    };
    
    /**
     * Główny data provider zwracający konfigurację dla 5ciu przegladarek
     * pobieranych z klasy BrowserEnnum ie8,ie9,ie10,ff,ch (wszystkie na
     * windows)
     * 
     * Zakomentowany kod jest dla wykorzystania w przyszłości, dodatkowa pętla
     * dla UsersENUM,
     * 
     * @return return Iterator with elements for factory object in test
     */
    @DataProvider(name = "Test_all_5_browsers_x1")
    // , parallel = true)
    public Iterator<Object[]> Test_all_browsers_x1() {
        
        try {
            List<Object[]> al = new ArrayList<Object[]>();
            int testNo = BrowserEnum.size();// run test for each browser
            for (int i = 0; i < testNo; i++) {
                al.add(new Object[] { null, BrowserEnum.getBrowserByID(i) });
            }// browser loop
            return al.iterator();
        } catch (Exception e) {
            log.info("ERROR " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public void openAndCheckPopover(String url) {
        
        open(url);
        
        closePopover();
    }
    
    /**
     * Login with 
     * 
     * @param user - user type
     * @param args - args[0] - url
     */
    
    public void loginWith(String user, String... args) {
        if (args.length != 0) {
            log.info("Opening specified url = {}", args[0]);
            open(args[0]);
        } else {
            log.info("Opening specified url = {}", LOGIN);
            open(PASSWORD);
        }
        
        log.info("Inputting credentials.");
        
        if (user.equals("patient")) {
            $(By.id("id_identification")).sendKeys(LOGIN);
            $(By.id("id_password")).sendKeys(PASSWORD);
        }
       
        
        log.info("Clicking input. URL = {}", getUrl());
        
        if (getUrl().contains("h")) {
            $(By.id("log-in-button")).should(Condition.visible).click();
            assertThat($(By.id("logout")).text().toString().contains("Wyloguj"));
        } else {
            $(By.id("login_button")).should(Condition.visible).click();
            assertThat($(By.linkText("Wyloguj")).text().toString().contains("Wyloguj"));
            
        }
        
        if (getUrl().contains("rl") && getUrl().contains("test."))
            setCookiesPopoverOn1ForRLTest("rl.test.mijasoftware.com");
        if (getUrl().contains("rl") && getUrl().contains("test2"))
            setCookiesPopoverOn1ForRLTest("rl.test2.mijasoftware.com");
        
        log.info("Logged in as " + user.toString());
    }
    
   
    public void clickSubmitBtnByTypeEqSubmit() {
        $(By.xpath(".//button[@type='submit']")).shouldBe(visible).click();
    }
    
    public boolean isAdPresent() {
        // search page info
        List<SelenideElement> blockWrappers = $$(By.className("block-wrap")).shouldBe(CollectionCondition.size(2));
        Assert.assertTrue(blockWrappers.size() == 2);
        String adSearchPage = blockWrappers.get(0).getAttribute("innerHTML").toString();
        log.info("Found add = " + adSearchPage);
        Assert.assertTrue(!adSearchPage.isEmpty(), "Ad in top banner is empty !");
        return true;
    }
    
    public void waitForPageLoaded(WebDriver driver) {
        
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver, BROWSER_TIMEOUT);
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.assertTrue(false, "Timeout waiting for Page Load Request to complete.");
        }
    }
    
    public void closePopover() {
        SelenideElement popover = $("#popover").waitUntil(visible, 5000);
        log.info("Trying to find popover.");
        if ($("#popover").isDisplayed()) {
            log.info("Found popover, trying to close it.");
            // popover.find(By.className("close-dialog")).sendKeys(Keys.ENTER);
            popover.find(By.className("close-dialog")).click();
            log.info("Clicked X");
        }
    }
    
    public void logout() {
        log.info("Trying to logout.");
        if ($(By.linkText("Wyloguj")).isDisplayed()) {
            $(By.linkText("Wyloguj")).click();
        }
        if (getUrl().contains("halodoktorze") || getUrl().contains("hd"))
            Assert.assertTrue($(By.linkText("Logowanie")).waitUntil(visible, 5000).isDisplayed());
        else
            Assert.assertTrue($(By.linkText("Zaloguj")).waitUntil(visible, 5000).isDisplayed());
        
    }
    
    public String getUrl() {
        return getWebDriver().getCurrentUrl();
    }
    
    public void searchFor(String something) {
        $(By.name("q")).clear();
        $(By.name("q")).sendKeys(something);
        $(By.name("q")).sendKeys(Keys.ENTER);
        
    }
    
    public List<String> testLinksOnCurrentPageAndAddPagesUrlsAsList(List<String> urlToTestLinksOn) {
        List<String> hrefs = new ArrayList<String>();
        
        log.info("Current url = " + getWebDriver().getCurrentUrl());
        List<String> urlsToCheck = Arrays.asList(getWebDriver().getCurrentUrl());
        urlsToCheck.addAll(urlToTestLinksOn);
        
        for (int i = 0; i < urlsToCheck.size(); i++) {
            
            open(urlsToCheck.get(i));
            log.info("Checking url for links - " + urlsToCheck.get(i));
            
            List<SelenideElement> anchors = new ArrayList<SelenideElement>($$(By.tagName("a")));
            log.info("A elements = " + anchors.size());
            // if no link return info
            if (anchors.size() == 0)
                return Arrays.asList("No urls to check");
            
            // removing # elements - no link - null
            for (Iterator<SelenideElement> iter = anchors.listIterator(); iter.hasNext();) {// #
                                                                                            // =
                                                                                            // null
                SelenideElement anchor = iter.next();
                if (anchor.getAttribute("href") == null || anchor.getAttribute("href").equals("")
                // || anchor.getAttribute("href").contains("/#")
                ) {
                    iter.remove();
                    log.info("Removed null / empty link : " + anchor);
                }
            }
            log.info("Links left to check out = " + anchors.size());
            
            for (SelenideElement anchor : anchors) {
                hrefs.add(anchor.getAttribute("href"));
                // .replace(URL_PROD_HD, URL_TEST_HD)
                // .replace(URL_PROD_RL, URL_TEST_RL));
            }
            
            int j = 0;
            for (String href : hrefs) {
                j++;
                int code = Utils.linkExists(href);
                if (code > CODE_SUCCESS)
                    log.info("Code : " + Utils.linkExists(href) + " -- " + href);
                if (code == 200)
                    log.info("Code : " + Utils.linkExists(href) + " -- " + j + "/" + hrefs.size() + " links ");
                if (href.matches(".*[ąęśłóźżćłó].*"))
                    
                    Assert.assertEquals((Utils.linkExists(href) <= CODE_FAIL), true, "We have " + CODE_FAIL
                            + "+ code to see in Jenkins");
                
            }// end links loop
            
        }// end main urls loop
        
        return hrefs;
        
    }
    
    public List<String> testLinksOnCurrentPage() {
        return testLinksOnCurrentPageAndAddPagesUrlsAsList(Collections.<String> emptyList());
    }
    
    public String getRandomCity() {
        int idx = new Random().nextInt(CITIES.size());
        return CITIES.get(idx);
    }
    
    
    public String getRandomDoctorName() {
        int idx = new Random().nextInt(NAMES.size());
        return NAMES.get(idx);
    }
    
    public String getRandomTestEmail() {
        return utils.getRandomString("testmija+", 4) + "+hdtest@gmail.com";
    }
    
    public String getRandomServiceName() {
        return utils.getRandomString("Service Test_", 4);
    }
    
    
    public String getRandomNIP() {
        int idx = new Random().nextInt(NIPS.size());
        return NIPS.get(idx);
    }
    
    public void displayAllCookies() {
        displayAllCookiesFromURL(null);
    }
    
    /**
     * gets the string between start and end with both of the string parts
     * 
     * @return String - domena
     */
    public String getUrlDomain() {
        String domain = getUrl();
        String start = "";
        String end = "";
        if (domain.contains(DOMAIN_NAME))
            end = ".com";
        else
            end = ".pl";
        if (domain.contains("panel."))
            domain = domain.replace("panel.", "");
        log.info("Domain name = {}", domain);
        // return domain.substring(domain.indexOf(start) + start.length(),
        // domain.indexOf(end) - end.length());
        return domain.substring(0, domain.indexOf(end) + end.length());
    }
    
   
    public boolean isLoggedIn() {
        assertThat($(By.id("logout")).text().toString().contains("Wyloguj"));
        return true;
    }
    
    public List<SelenideElement> getSortableItems() {
        return $$(".sortable-item");
    }
    
    public boolean findTextInFirstElementInAnySortableItem(String textToFind) {
        for (SelenideElement se : $$(".sortable-item")) {
            log.info("Searching for = {} in element = {}", textToFind, se.toString());
            if (se.find(By.className("item-text")).text().contains(textToFind)) {
                return true;
            }
            
        }
        return false;
    }
    
    public void displayAllCookiesFromURL(String urlPart) {
        Set<Cookie> cookies = getWebDriver().manage().getCookies();
        
        if (urlPart != null) {
            for (Cookie cookie : cookies) {
                log.info("Displaying all cookies for given domain.");
                
                if (cookie.getDomain().contains(urlPart)) {
                    log.info("Domain = {} , Var = {} Value = {} ", cookie.getDomain(), cookie.getName(),
                            cookie.getValue());
                }
            }
        } else {
            log.info("Displaying all cookies.");
            for (Cookie cookie : cookies) {
                log.info("Domain = {} , Var = {} Value = {} ", cookie.getDomain(), cookie.getName(), cookie.getValue());
            }
        }
    }
    
    public void setCookiesPopoverOn1ForRLTest(String domain) {
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 15); // Adds 15 days
        
        Cookie q = new Cookie("pophd", "1", domain, "/", c.getTime());
        
        getWebDriver().manage().addCookie(q);
    }
    
    public boolean clickUsunOnFirstElementWithTextInAnySortableItem(String textToFind) {
        List<SelenideElement> list = $$(".sortable-item");
        for (SelenideElement se : list) {
            if (se.find(By.className("item-text")).waitUntil(visible, WAIT).text().contains(textToFind)) {
                
                log.info("Found se = {}", se.innerHtml());
                
                // se.find(By.linkText("Usuń")).shouldBe(visible).click();
                clickElementWithJS(se.find(By.linkText("Usuń")));
                
                log.info("Clicked usun");
                $$(By.className("ui-button-text")).findBy(Condition.hasText("Tak")).waitUntil(visible, WAIT).click();
                log.info("Confirmed usun on link for service = {}", textToFind);
                return true;
            }
        }
        return false;
    }
    
    public void clickElementWithJS(SelenideElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
        executor.executeScript("arguments[0].click();", element);
    }
    
    
    public String getRandomPesel() {
        int idx = new Random().nextInt(PESELS.size());
        return PESELS.get(idx);
    }
    
    public String getPeselBad() {
        return "55021562501";
    }
    
    public String getPeselGood() {
        return "01010153201";
    }
    
    public void clickSubmitInputByTypeEqSubmit() {
        $(By.xpath(".//input[@type='submit']")).shouldBe(visible).click();
    }
    
    public void clickSubmitButtonByTypeEqSubmit() {
        $(By.xpath(".//button[@type='submit']")).shouldBe(visible).click();
    }
    
    public void clickSubmitByTypeEqSubmit() {
        $(by("type", "submit")).waitUntil(visible, WAIT).shouldBe(visible).click();
    }
    
    public boolean isTopWrapperMessageContaining(String msg) {
        Assert.assertTrue($("#message-top-wrapper").waitUntil(Condition.hasText(msg), WAIT).is(Condition.hasText(msg)),
                "Message does not contain =" + msg);
        return true;
    }
    
    public boolean isMessagesClassContaining(String msg) {
        Assert.assertTrue($(".messages").waitUntil(Condition.hasText(msg), WAIT).is(Condition.hasText(msg)),
                "Message does not contain =" + msg);
        return true;
    }
    
    public String getProfileName() {
        
        return $("#profil-name").innerHtml().replaceAll("\\s+", "").replace("<br>", " ");
        
    }
    
    public String getProfileFirstName() {
        return getProfileName().split(" ")[0];
        
    }
    
    @BeforeMethod
    public void before() {
        
        Configuration.browser = browser.getBrowserName();
        log.info("BEFORE method browserName = " + browser.getBrowserName());
        Configuration.remote = "http://" + HUB_IP + ":4444/wd/hub";
        Configuration.timeout = BROWSER_TIMEOUT;
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().timeouts().pageLoadTimeout(BROWSER_TIMEOUT, TimeUnit.MILLISECONDS);
        getWebDriver().manage().window().maximize();
        log.info("Finished before method.");
    }
    
    @AfterMethod
    public void after() {
        close();
    }
}
