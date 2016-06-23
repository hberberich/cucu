package Unused;

import Helper.WDUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CLPageObject {

    @FindBy(id="make-model")
    public WebElement makemodelArrow;

    @FindBy(xpath="//div[@id='make-model']/as24-icon[@class='arrow-rotation']")
    public WebElement makemodelCollapsed;

    @FindBy(xpath="//div[@id='make-model']/as24-icon[@class='arrow-rotation open']")
    public WebElement makemodelExpanded;

    public CLPageObject() {
        PageFactory.initElements(WDUtil.getWebDriver(), this);
    }

    public void fillFormAndSubmit() throws InterruptedException {

        System.out.println(checkExpanded());
        makemodelArrow.click();
        System.out.println(checkExpanded());
        makemodelArrow.click();
        System.out.println(checkExpanded());
    }

    private boolean checkExpanded() {
        try {
            makemodelExpanded.isDisplayed();
        } catch (NoSuchElementException no) {
            return false;
        }
        return true;
    }
}


