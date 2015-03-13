
package allinone.pages.other;

import static com.codeborne.selenide.Selenide.open;
import static org.fest.assertions.Assertions.assertThat;
import allinone.AbstractBaseTest;

import com.codeborne.selenide.WebDriverRunner;

public class PayLanePaymentPage extends AbstractBaseTest {
    
    public PayLanePaymentPage() {
    }
    
    public PayLanePaymentPage(String url) {
        open(url);
    }
    
    private void isItPaylanePage() {
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl().contains("paylane"));
    }
    
}
