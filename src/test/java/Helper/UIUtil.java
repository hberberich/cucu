package Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by hberberich on 27.01.16.
 */
public class UIUtil {

    public static void  waitAndSelectByText(WebElement we, String text) {

        WebElement myDynamicElement =  (new WebDriverWait(WDUtil.getWebDriver(), 10))
            .until(ExpectedConditions.elementToBeClickable(we));
    new Select(we).selectByVisibleText(text);

    }
}
