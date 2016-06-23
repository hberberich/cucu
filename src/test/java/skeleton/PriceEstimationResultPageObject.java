package skeleton;

import Helper.UIUtil;
import Helper.WDUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;


public class PriceEstimationResultPageObject {

    @FindBy(xpath = "//h3[@id='priceRangeLow']")
    public WebElement priceRangeLowDiv;

    @FindBy(xpath = "//h3[@id='priceRangeMid']")
    public WebElement priceRangeAvgDiv;

    @FindBy(xpath = "//h3[@id='priceRangeHigh']")
    public WebElement priceRangeHighDiv;

    @FindBy(id="adPlacementLink")
    public WebElement adPlacementLink;

    @FindBy(id="adPlacementDraftLink")
    public WebElement adPlacementDraftLink;

    public PriceEstimationResultPageObject() {
        PageFactory.initElements(WDUtil.getWebDriver(), this);
    }

    public void checkPriceEstimation() {

        UIUtil.waitForElementToBeClickable(adPlacementLink);
        String priceRangeLow = priceRangeLowDiv.getText();
        String priceRangeAvg = priceRangeAvgDiv.getText();
        String priceRangeHigh = priceRangeHighDiv.getText();

        priceRangeLow = priceRangeLow.replace("€ ", "").replace(".", "");
        priceRangeAvg = priceRangeAvg.replace("€ ", "").replace(".", "");
        priceRangeHigh = priceRangeHigh.replace("€ ", "").replace(".", "");

        assertTrue(Integer.parseInt(priceRangeHigh) > Integer.parseInt(priceRangeLow));
        assertTrue(Integer.parseInt(priceRangeAvg) > Integer.parseInt(priceRangeLow));
        System.out.print(priceRangeLow + " | " + priceRangeAvg  + " | " + priceRangeHigh  + " | ");

    }

    public void printPrices() {
        String priceRangeLow = priceRangeLowDiv.getText();
        String priceRangeAvg = priceRangeAvgDiv.getText();
        String priceRangeHigh = priceRangeHighDiv.getText();
        System.out.println(priceRangeLow + "," + priceRangeAvg + "," + priceRangeHigh);
        WDUtil.getWebDriver().navigate().back();
    }

}

