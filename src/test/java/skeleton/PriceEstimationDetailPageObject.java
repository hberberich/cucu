package skeleton;

import Helper.UIUtil;
import Helper.WDUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class PriceEstimationDetailPageObject {


    @FindBy(xpath="//a[@href='#makeModelSummary']")
    public WebElement standardEquipLink;


    @FindBy(xpath="//div[@id='standardEquipmentToggle']/a/span[@class='collapse1 expander-icon in']")
    public WebElement standardEquipmentExpanded;

    // Submit button at the bottom light model
    @FindBy(name="withLightModel")
    public WebElement withLightModelButton;

    // Submit button at the bottom full model
    @FindBy(id="withFullModel")
    public WebElement withFullModelButton;

    // Submit button on top (without Equipment)
    @FindBy(name="modelSize")
    public WebElement modelSizeTopButton;

    public PriceEstimationDetailPageObject() {
        PageFactory.initElements(WDUtil.getWebDriver(), this);
    }

    public void openStandardEquipment() {
        UIUtil.waitAndClick(standardEquipLink);
    }

    public void checkStandardEquipmentIsShown() {
        try {
            assert(standardEquipmentExpanded.isDisplayed());
        } catch (NoSuchElementException no) {
            Assert.assertTrue("Standard Equipment not displayed", false);
            System.out.println(no.getMessage());
        }
    }

    public void fillFormAndSubmit() throws InterruptedException {
        UIUtil.waitAndClick(modelSizeTopButton);
        //modelSizeTopButton.click();

    }

    public void loopEquip() {

        System.out.print("Without Equipment, ");
        WDUtil.getWebDriver().findElement(By.xpath("//label[@for='ep.bodyColorId-2']")).click();
        withFullModelButton.click();
        new PriceEstimationResultPageObject().printPrices();

        List<WebElement> we = WDUtil.getWebDriver().findElements(By.xpath("//input[@type='checkbox']"));
        String[] names = new String[we.size()];
        for (int i = 0; i < we.size(); i++) {
             names[i] =  we.get(i).getAttribute("name").toString();
        }
        for (int i = 0; i < we.size(); i++) {

            //System.out.println(we.get(i).getAttribute("name"));
            WebElement label = WDUtil.getWebDriver().findElement(By.xpath("//input[@name='" + names[i] + "']/../label"));
            System.out.print(names[i] + "  " + label.getText() + ", ");
            label.click();
            withFullModelButton.click();
            new PriceEstimationResultPageObject().printPrices();
            label = WDUtil.getWebDriver().findElement(By.xpath("//input[@name='" + names[i] + "']/../label"));
            label.click();
        }

    }

    public void loopColors() {
        /*
        System.out.print("Without Equipment, ");
        WDUtil.getWebDriver().findElement(By.xpath("//label[@for='ep.bodyColorId-2']")).click();
        withFullModelButton.click();
        new PriceEstimationResultPageObject().printPrices();
        */

        List<WebElement> we = WDUtil.getWebDriver().findElements(By.xpath("//input[@type='radio']"));
        String[] names = new String[we.size()];
        for (int i = 0; i < we.size(); i++) {
            names[i] =  we.get(i).getAttribute("id").toString();
        }
        for (int i = 0; i < we.size(); i++) {
            WebElement label = WDUtil.getWebDriver().findElement(By.xpath("//input[@id='" + names[i] + "']/../label"));
            System.out.print(names[i] + "  " + label.getText() + ", ");
            label.click();
            withFullModelButton.click();
            new PriceEstimationResultPageObject().printPrices();
            //label = WDUtil.getWebDriver().findElement(By.xpath("//input[@name='" + names[i] + "']/../label"));
            //label.click();
        }

    }

}


