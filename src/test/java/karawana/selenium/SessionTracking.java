package karawana.selenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SessionTracking {

    @Test
    public void userCanLoginByUsername() {

        open("http://127.0.0.1:8080/");
        String groupName = $("#groupName").getValue();
        String userID = $("#userId").getValue();
        String userName = $("#userName").getValue();
        String location = $("#location").getValue();

        System.out.println(groupName);
        System.out.println(userID);
        System.out.println(userName);
        System.out.println(location);


    }


}
