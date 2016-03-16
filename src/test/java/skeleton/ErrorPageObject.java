package skeleton;

import Helper.WDUtil;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ErrorPageObject {

    @FindBy(id = "head") ////div[@class='logo']
    public WebElement errTextDiv;

    @FindBy(id = "home-btn")
    public WebElement errButton;

    public ErrorPageObject() {
        PageFactory.initElements(WDUtil.getWebDriver(), this);
    }

    public void checkErrorPage(String errText, String errButtonText) {

        Assert.assertTrue(errTextDiv.getText().equals(errText));
        Assert.assertTrue(errButton.getText().equals(errButtonText));
        //Assert.assertTrue(errButton.getAttr equals(errButton));


    }


}

