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

    public static void waitAndSelectByIndex(WebElement we, int index) {

        WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(we));
        try {
            Select sel = new Select(we);
            sel.selectByIndex(index);
            //System.out.print(sel.getFirstSelectedOption().getText() + " | ");
        } catch (Exception ex) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Select(we).selectByIndex(index);

        }

    }

    public static void waitAndClick(WebElement we) {

        WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(we));
        myDynamicElement.click();

    }

    public static void waitAndEnterText(WebElement we, String text) {

        try {
            WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                    .until(ExpectedConditions.elementToBeClickable(we));
            myDynamicElement.sendKeys(text);
        } catch (Exception ex) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                    .until(ExpectedConditions.elementToBeClickable(we));
            myDynamicElement.sendKeys(text);
        }

    }

    public static void waitForElementToBeClickable(WebElement we) {

        WebElement myDynamicElement = (new WebDriverWait(WDUtil.getWebDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(we));

    }
}
