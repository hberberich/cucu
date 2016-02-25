package Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 */
public class UIUtil {

    public static void waitAndSelectByText(WebElement we, String text) {

        WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
            .until(ExpectedConditions.elementToBeClickable(we));
        new Select(we).selectByVisibleText(text);

    }

    public static void waitAndClick(WebElement we) {

        WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(we));
        myDynamicElement.click();

    }

    public static void waitAndEnterText(WebElement we, String text) {

        WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(we));
        myDynamicElement.sendKeys(text);

    }

    public static void waitForElementToBeClickable(WebElement we) {

        WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(we));

    }
}
