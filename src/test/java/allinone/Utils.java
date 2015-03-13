/*
 * 
 */
package allinone;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codeborne.selenide.SelenideElement;

/**
 * Klasa z różnymi metodami ułatwiającymi życie
 * 
 * @author p.kowalski
 *
 */
public class Utils {
    private static final long WAIT = AbstractBaseTest.WAIT;
    public static Logger      log  = Logger.getLogger("log4j.logger.TestLogger");
    
    /**
     * Zwraca ścieżkę do przykładowego załacznika z folderu resources/zalacznik
     * w roocie /resources wrzucamy "/" przed nazwa jest dodawany automatycznie
     * dla folderów dodajemy normalnie nazwe folderu przed
     * wnioskiEBTE/wniosekxyz.xml - slash prowadzacy sie doda
     * 
     * Zalacznik pusty, null - dodajemy zalacznik.pdf
     * 
     * @param zalacznik - zalacznik
     * @return String pełna ścieżka do załącznika
     */
    public String getZalacznikPath(String zalacznik) {
        String file = "";
        
        try {
            zalacznik = "/" + zalacznik;
        } catch (Exception e) {
            zalacznik = "/zalacznik.pdf";
        }
        log.info("Zalacznik to " + zalacznik);
        try {
            
            URL resourceUrl = getClass().getResource(zalacznik); // sample.txt
                                                                 // -> NPE
            Path resourcePath = Paths.get(resourceUrl.toURI());
            // log.info("Resource url = "+resourceUrl);
            log.info("resourcePath = " + resourcePath);
            file = resourcePath.toString();
            
        } catch (Exception e) {
            log.info("Wyjątek podczas pozyskiwania absolutnej ścieżki do pliku");
            e.printStackTrace();
        }
        
        return file;
    }
    
    public boolean getRandomBoolean() {
        Random rn = new Random();
        if (rn.nextInt() % 2 == 0)
            return true;
        else
            return false;
    }
    
    public String getRandomString(String prefix, int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer buffer = new StringBuffer();
        int charactersLength = characters.length();
        buffer.append(prefix);
        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
            
        }
        return buffer.toString();
    }
    
    public String getRandomNumbersString(String prefix, int length) {
        String characters = "0123456789";
        StringBuffer buffer = new StringBuffer();
        int charactersLength = characters.length();
        buffer.append(prefix);
        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
            
        }
        return buffer.toString();
    }
    
    public int getRandomInt(int maxVal) {
        Random rand = new Random();
        return rand.nextInt(maxVal);
    }
    
    public void ThreadID() {
        long threadId = Thread.currentThread().getId();
        log.info("### Thread # " + threadId + "  is doing this task ");
        
    }
    
    /**
     * Return request code - 200/400 etc
     * 
     * @param href - url
     * @return req code
     */
    public static int linkExists(String href) {
        try {
            
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            HttpURLConnection con = (HttpURLConnection) new URL(href).openConnection();
            con.setInstanceFollowRedirects(true);
            con.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            
            if (400 > responseCode && responseCode >= 302) {
                log.info("=== 3XX code discovered !! : ");
                con.connect();
                log.info("=== Resp Code =" + responseCode);
                log.info("=== BaseUrl  = " + con.getURL());
                String location = con.getHeaderField("Location");
                log.info("=== Location =" + location + "\n");
            }
            
            return responseCode;
        } catch (NullPointerException e) {
            // e.printStackTrace();
            log.info(e.getCause() + " no href attribute found on tag <a ..>");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * [0-9]{4} - 4 digit string
     * 
     * @param reg
     * @param str
     * @return
     */
    public String getNumberMatchInString(String reg, String str) {
        
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
    
    
    @Test
    public void testGetRegexp(){
        
        
        String string = "http://b2b.test.mijasoftware.com/adserver/kampanie/120/";
        System.out.println("String = " + string);
        String reg = "[0-9]+";
        System.out.println("1 = " + getNumberMatchInString(reg, string));
         reg = "\\D+";
        System.out.println("1 = " + getNumberMatchInString(reg, string));
         reg = "\\d+";
        System.out.println("1 = " + getNumberMatchInString(reg, string));
         reg = "^[0-9]+";
        System.out.println("1 = " + getNumberMatchInString(reg, string));
        
        reg = "\\d{2,}";
        System.out.println("1 = " + getNumberMatchInString(reg, string));
        reg = "^[0-9]+";
        System.out.println("1 = " + getNumberMatchInString(reg, string));
        reg = "^[0-9]+";
        System.out.println("1 = " + getNumberMatchInString(reg, string));
       
        
        
    }
    
    
    
    
    public boolean uploadFileToField(SelenideElement input) {
        String file = "C:\\Users\\pkowalski\\Desktop\\Stuff\\TestData\\images1.jpg";
        input.sendKeys(file);
        Assert.assertTrue($(".delete").waitUntil(visible, WAIT).isDisplayed());
        return true;
        
    }
    
}
