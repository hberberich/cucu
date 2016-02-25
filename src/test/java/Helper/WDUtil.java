package Helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 *
 */
public class WDUtil {

    private static WebDriver webDriver;
    private static String browser;

    public static void setWebDriver(WebDriver wd) {
        webDriver = wd;

    }
    public static WebDriver getWebDriver() {
        return webDriver;

    }

    public static void startDriver(String url, String browser) {

        browser = System.getProperty("BROWSER");
        if (browser == null) browser = Browsers.Safari;

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                ((JavascriptExecutor) driver).executeScript(
                    "window.focus();");
                break;
            case "phantomjs":
                driver = new PhantomJSDriver();
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }
        //WebDriver driver = new ChromeDriver();
        //Selenium selenium = new DefaultSelenium(“localhost”, 4444, “*firefox”, “http://www.google.com”);
/*
        DesiredCapabilities caps = DesiredCapabilities.chrome();

        WebDriver driver = null;
        try {
            //System.setProperty("webdriver.chrome.logfile", "/Users/hberberich/apps/chromedriver.log");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
*/
        //Selenium selenium = new DefaultSelenium(“localhost”, 4444, “*chrome”, “http://www.google.com”);
        //WebDriver driver = new HtmlUnitDriver();
        //WebDriver driver = new PhantomJSDriver();

        setWebDriver(driver);
        setBrowser(browser);
        driver.manage().window().maximize();
        driver.manage().window().setPosition(new Point(1921, 1));
        driver.navigate().to(url);
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        WDUtil.browser = browser;
    }
}
