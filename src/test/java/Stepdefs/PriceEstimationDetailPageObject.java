package stepdefs;

import Helper.WDUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PriceEstimationDetailPageObject {

    @FindBy(name="modelSize")
    public WebElement modelSizeButton;

    public PriceEstimationDetailPageObject() {
        PageFactory.initElements(WDUtil.getWebDriver(), this);
    }

    public void fillFormAndSubmit() throws InterruptedException {

        modelSizeButton.click();

    }
}


