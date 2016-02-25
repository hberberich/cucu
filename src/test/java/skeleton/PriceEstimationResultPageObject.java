package skeleton;

import Helper.UIUtil;
import Helper.WDUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;


public class PriceEstimationResultPageObject {

    @FindBy(xpath = "//div[@id='priceRangeLow']/p")
    public WebElement priceRangeLowDiv;

    @FindBy(xpath = "//div[@id='priceRangeAvg']/p")
    public WebElement priceRangeAvgDiv;

    @FindBy(xpath = "//div[@id='priceRangeHigh']/p")
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

        priceRangeLow = priceRangeLow.substring(2, priceRangeLow.length()-2).replace(".", "");
        priceRangeAvg = priceRangeAvg.substring(2, priceRangeAvg.length()-2).replace(".", "");
        priceRangeHigh = priceRangeHigh.substring(2, priceRangeHigh.length()-2).replace(".", "");

        assertTrue(Integer.parseInt(priceRangeHigh) > Integer.parseInt(priceRangeAvg));
        assertTrue(Integer.parseInt(priceRangeAvg) > Integer.parseInt(priceRangeLow));

    }

    public void printPrices() {
        String priceRangeLow = priceRangeLowDiv.getText();
        String priceRangeAvg = priceRangeAvgDiv.getText();
        String priceRangeHigh = priceRangeHighDiv.getText();
        System.out.println(priceRangeLow + "," + priceRangeAvg + "," + priceRangeHigh);
        WDUtil.getWebDriver().navigate().back();
    }

}
