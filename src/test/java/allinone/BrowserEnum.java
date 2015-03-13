/*
 * 
 */
package rlhd.hd.base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Klasa określająca konfigurację przeglądarek na jakich powinien być
 * uruchomiony test
 * 
 * @author p.kowalski
 *
 */
public enum BrowserEnum {
    
    // WINDOWS_XP_IE8 (0,"WINDOWS","XP","ie","8", getIEDesiredCapabilites() ),
    // WINDOWS_7_IE9 (1,"WINDOWS","7","ie","9", getIEDesiredCapabilites() ),
    // WINDOWS_8_IE10 (2,"WINDOWS","8","ie","10",
    // DesiredCapabilities.internetExplorer() ),
    // WINDOWS_FF (3,"WINDOWS","ANY","firefox","ANY",
    // DesiredCapabilities.firefox()),
    WINDOWS_CH(4, "WINDOWS", "ANY", "chrome", "ANY", DesiredCapabilities.chrome());
    
    private int                 id;
    private String              systemName;         // windows,linux etc
    private String              systemVersion;      // xp,7,8, etc
    private String              browserName;        // ie, ch, ff
    private String              browserVersion;     // 8,9,10,12,3123
    private DesiredCapabilities browserCapabilities;
    
    // TO DO - move it out of here to make dynamically for each IE !!
    public static DesiredCapabilities getIEDesiredCapabilites() {
        DesiredCapabilities ie = new DesiredCapabilities(BrowserType.IE, "", Platform.WINDOWS);
        ie.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
        ie.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        ie.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        // ie.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
        // true);
        // ie.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        ie.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
        return ie;
    }
    
    private BrowserEnum(int id, String systemName, String systemVersion, String browserName, String browserVersion,
            DesiredCapabilities browserCapabilities) {
        this.id = id;
        this.systemName = systemName;
        this.systemVersion = systemVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.browserCapabilities = browserCapabilities;
    }
    
    // methods
    
    public static BrowserEnum getBrowserByID(int id) {
        return BrowserEnum.values()[id];
    }
    
    public static int size() {
        return BrowserEnum.values().length;
    }
    
    @Override
    public String toString() {
        return "BrowserEnum [systemName=" + systemName + ", systemVersion=" + systemVersion + ", browserName="
                + browserName + ", browserVersion=" + browserVersion + "]";
    }
    
    public String getSystemName() {
        return systemName;
    }
    
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
    
    public String getSystemVersion() {
        return systemVersion;
    }
    
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }
    
    public String getBrowserName() {
        return browserName;
    }
    
    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }
    
    public String getBrowserVersion() {
        return browserVersion;
    }
    
    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public DesiredCapabilities getBrowserCapabilities() {
        return browserCapabilities;
    }
    
    public void setBrowserCapabilities(DesiredCapabilities browserCapabilities) {
        this.browserCapabilities = browserCapabilities;
    }
    
}
