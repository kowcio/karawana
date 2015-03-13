/*
 * 
 */
package allinone.pages.other;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import allinone.AbstractBaseTest;

public class GmailPage extends AbstractBaseTest {
    private org.slf4j.Logger log                       = LoggerFactory.getLogger(getClass());
    
    private static final int NUMBER_OF_EMAILS_TO_CHECK = 10;
    
    public GmailPage() {
    }
    
    public GmailPage(String url) {
    }
    
    @Test
    public void test() {
        
        String str = "Kodweryfikacyjny:8490.DziŕkujemyzaskorzystanieHaloDoktorze.pl";
        String reg = "[0-9]{4}";
        String reg2 = "/^(\\d{4}(,\\d{4})*)$/";
        
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        
        log.info(" FIND = " + matcher.find());
        
        if (matcher.find()) {
            log.info("Match = " + matcher.group());
        }
        
    }
    
    public static GmailPage login() {
        open("http://gmail.com");
        $(By.id("Email")).sendKeys("log");
        $(By.id("Passwd")).sendKeys("pass");
        $(By.id("signIn")).click();
        return new GmailPage();
    }
    
    public String getSMS() {
        String regexp = "[0-9]{4}";
        Pattern pattern = Pattern.compile(regexp);
        String msg = getLatestEmailWithSMSCode();
        Matcher matcher = pattern.matcher(msg);
        
        log.info("Searching for [0-9]{4} in  = " + msg);
        
        if (matcher.find()) {
            log.info("Match = " + matcher.group());
            return matcher.group();
        }
        Assert.fail("Didn`t found the sms in the mail.");
        
        return null;
    }
    
    public String getLatestEmailWithSMSCode() {
        
        String msg = "";
        String user = "rankinglekarzy.dev@gmail.com";
        String password = "RL150!rl";// change accordingly
        Properties props = setEmailPropertiesForGmailImapSSL(user, password);
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store emailStore = session.getStore("imaps");
            emailStore.connect("imap.gmail.com", user, password);
            Folder emailFolder = emailStore.getFolder("SMS");
            emailFolder.open(Folder.READ_ONLY);
            
            List<Folder> ls = Arrays.asList(emailStore.getDefaultFolder().list());
            
            int timeout = 30; // [s]
            
            while (timeout >= 0) {
                int last = emailFolder.getMessageCount();
                log.info("We got " + last + " messages.");
                Message[] messages = emailFolder.getMessages(last - NUMBER_OF_EMAILS_TO_CHECK, last);
                
                Thread.sleep(3000);
                timeout -= 3;
                log.info("We got " + messages.length + " messages. First mesage should have the code.");
                for (int i = messages.length - 1; i >= 0; i--) {
                    Message message = messages[i];
                    log.info("Message no = " + i + " title = " + message.getSubject());
                    // TODO add 2 min varation ?
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh");
                    String reportDate = df.format(new Date());
                    String receivedDate = df.format(message.getSentDate());
                    log.info("Today = " + reportDate + " Email = " + receivedDate);
                    
                    if (message.getContent().toString().contains("weryfikacyjny") && reportDate.equals(receivedDate)) {
                        
                        log.info("---------------------------------");
                        log.info("Email Number " + (i + 1));
                        log.info("Subject: " + message.getSubject());
                        log.info("From: " + message.getFrom()[0]);
                        log.info("Text: " + message.getContent().toString());
                        log.info("Date: " + receivedDate);
                        timeout = -1;
                        msg = message.getContent().toString().trim().replace(" ", "");
                        return msg;
                    }
                }
            }
            // 5) close the store and folder objects
            emailFolder.close(false);
            emailStore.close();
            
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return msg;
        
    }
    
    public String checkEmailForEmailReceipmentAndTitleString(String emailReceiver, String inTitle) {
        
        String user = "testmija@gmail.com";
        String password = "!@#qweasd";// change accordingly
        Properties props = setEmailPropertiesForGmailImapSSL(user, password);
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store emailStore = session.getStore("imaps");
            emailStore.connect("imap.gmail.com", user, password);
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            int timeout = 30; // [s]
            
            while (timeout >= 0) {
                int last = emailFolder.getMessageCount();
                log.info("We got " + last + " messages.");
                Message[] messages = emailFolder.getMessages(last - NUMBER_OF_EMAILS_TO_CHECK, last);
                
                Thread.sleep(3000);
                timeout -= 3;
                log.info("We got " + messages.length + " messages. First mesage should have the code.");
                for (Message msg : messages) {
                    for (Address adr : Arrays.asList(msg.getAllRecipients())) {
                        
                        log.info("To find = " + emailReceiver + "Email to search = " + adr.toString());
                        log.info("Title   = " + inTitle + "\n" + "Subject = " + msg.getSubject());
                        if (adr.toString().toLowerCase().contains(emailReceiver.toLowerCase())
                                && msg.getSubject().toLowerCase().contains(inTitle.toLowerCase())) {
                            log.info("FOUND the  email ! All ok ! ");
                            return emailReceiver;
                        }
                        
                    }
                    
                }
            }
            Assert.fail("Didn`t found email with the given content.");
            // 5) close the store and folder objects
            emailFolder.close(false);
            emailStore.close();
            
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.fail();
        return "";
        
    }
    
    private Properties setEmailPropertiesForGmailImapSSL(String user, String password) {
        Properties props = new Properties();
        props.setProperty("mail.imaps.user", user);
        props.setProperty("mail.imaps.password", password);
        props.setProperty("mail.imaps.auth", "true");
        props.setProperty("mail.imaps.starttls.enable", "true");
        props.setProperty("mail.imaps.socketFactory.port", "993");
        props.setProperty("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.imaps.socketFactory.fallback", "false");
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imaps.host", "imap.gmail.com");
        props.setProperty("mail.imaps.port", "993");
        return props;
    }
    
    public void checkMultipartMessageBody(Object msgContentInput) {
        
        try {
            Object msgContent = msgContentInput;
            String content = "";
            /* Check if content is pure text/html or in parts */
            if (msgContent instanceof Multipart) {
                
                Multipart multipart = (Multipart) msgContent;
                
                for (int j = 0; j < multipart.getCount(); j++) {
                    
                    BodyPart bodyPart = multipart.getBodyPart(j);
                    
                    String disposition = bodyPart.getDisposition();
                    
                    if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                        log.info("Mail have some attachment");
                        DataHandler handler = bodyPart.getDataHandler();
                        log.info("file name : " + handler.getName());
                    } else if (bodyPart.isMimeType("text/plain")) {
                        log.info("TExT");
                    } else {
                        content = bodyPart.getContentType(); // the changed code
                        log.info("TExT" + content);
                        
                    }
                }
            } else {
                content = msgContent.toString();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    }
    
}
