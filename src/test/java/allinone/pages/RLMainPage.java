/*
 * 
 */
package rlhd.rl.pages.front;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.fest.assertions.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import rlhd.hd.base.AbstractBaseTest;
import rlhd.hd.base.GeneratorPeselDowod;

public class RLMainPage extends AbstractBaseTest {
    
    public RLMainPage() {
    }
    
    public RLMainPage(String url) {
        open(url);
    }
    
    public RLSearchPage searchDoctor(String docName) {
        $(By.name("q")).sendKeys(docName + Keys.ENTER);
        log.info("Searched for doc = {}", docName);
        return new RLSearchPage();
    }
    
    public RLMainPage registerPatient() {
        $(By.xpath(".//*[@id='user-links-2']/a[1]")).click();
        String userName = utils.getRandomString("PatientNameTest_", 3);
        $("#id_patient-first_name").sendKeys(userName);
        $("#id_patient-email").sendKeys(getRandomTestEmail());
        $(By.xpath(".//label[@for='id_patient-general_terms_accepted']")).find(By.tagName("span")).shouldBe(visible)
                .click();
        
        $(By.xpath(".//*[@id='form-patient']/form/div[8]/input")).shouldBe(visible).click();
        
        assertThat($(By.xpath(".//*[@id='message-top-wrapper']/div/h5")).text().contains("Dziekujemy za rejestrację!"));
        return this;
        
    }
    
    public String registerPatientGetEmail() {
        $(By.xpath(".//*[@id='user-links-2']/a[1]")).click();
        String userName = utils.getRandomString("PatientNameTest_", 3);
        $("#id_patient-first_name").sendKeys(userName);
        String email = getRandomTestEmail();
        $("#id_patient-email").sendKeys(email);
        $(By.xpath(".//label[@for='id_patient-general_terms_accepted']")).find(By.tagName("span")).shouldBe(visible)
                .click();
        
        $(By.xpath(".//*[@id='form-patient']/form/div[8]/input")).shouldBe(visible).click();
        
        assertThat($(By.xpath(".//*[@id='message-top-wrapper']/div/h5")).text().contains("Dziekujemy za rejestrację!"));
        return email;
        
    }
    
    public RLMainPage registerDoc() {
        
        $(By.xpath(".//*[@id='user-links-2']/a[1]")).click();
        $(By.xpath(".//li[@data-show='form-doctor']")).click();
        sleep(5000);
        $("#id_doctor_title_chosen").click();
        $(By.xpath(".//*[@id='id_doctor_title_chosen']/div/div/input")).sendKeys("mgr" + Keys.ENTER);
        
        $("#id_doctor-first_name").sendKeys("mg qwe 12r");
        $("#id_doctor-last_name").sendKeys("mg qwe 12r");
        $("#id_doctor_main_specialization_chosen").click();
        $(By.xpath(".//*[@id='id_doctor_main_specialization_chosen']/div/div/input")).sendKeys("Alergolog");
        
        $("#id_doctor-email").sendKeys(getRandomTestEmail());
        $("#id_doctor-contact_phone").sendKeys("512512512");
        $("#id_doctor-pesel").sendKeys(GeneratorPeselDowod.getPesel(1));
        
        // FUN WITH the photo
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = $(".file-name");
        String file = "C:\\Users\\pkowalski\\Desktop\\Stuff\\TestData\\images1.jpg";
        js.executeScript("arguments[0].setAttribute('" + file + "', '10')", element);
        
        // terms
        $(".label_check").click();
        // submit
        log.info("Submitting the form.");
        sleep(20000);
        
        $(By.xpath(".//input[@type='submit']")).click();
        
        return this;
        
    }
    
}
