package allinone.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.fest.assertions.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import allinone.AbstractBaseTest;
import allinone.GeneratorPeselDowod;

public class MainTestPage extends AbstractBaseTest {

	public MainTestPage() {
	}

	public MainTestPage(String url) {
		open(url);
	}

	public MainTestPage registerPatient() {
		$(By.xpath(".//*[@id='user-links-2']/a[1]")).click();
		String userName = utils.getRandomString("log", 3);
		$("#id_first_name").sendKeys(userName);
		$("#id_email").sendKeys(getRandomTestEmail());

		$(By.xpath(".//*[@id='form-patient']/form/div[8]/input")).shouldBe(visible).click();

		assertThat($(By.xpath(".//*[@id='message-top-wrapper']/div/h5")).text().contains("Dziekujemy !"));
		return this;

	}

}
