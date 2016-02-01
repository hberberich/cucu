package Helper;

import org.openqa.selenium.WebDriver;

/**
 * Created by hberberich on 27.01.16.
 */
public class WDUtil {

    private static WebDriver webDriver;

    public static void setWebDriver(WebDriver wd) {
        webDriver = wd;

    }
    public static WebDriver getWebDriver() {
        return webDriver;

    }
}
