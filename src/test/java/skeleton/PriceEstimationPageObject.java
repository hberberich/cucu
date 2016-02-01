package skeleton;

import Helper.UIUtil;
import Helper.WDUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PriceEstimationPageObject {
    //@FindBy(how = How.ID, using = "makeId")
    @FindBy(id="makeId")
    public WebElement make;

    @FindBy(id="firstRegistrationMonth")
    public WebElement firstRegistrationMonth;

    @FindBy(id="firstRegistrationYear")
    public WebElement firstRegistrationYear;

    @FindBy(id="vehicleGroupId")
    public WebElement vehicleGroupId;

    @FindBy(id="fuelId")
    public WebElement fuelId;

    @FindBy(id="power")
    public WebElement powerSel;

    @FindBy(id="equipmentLine")
    public WebElement equipmentLineSel;

    @FindBy(id="mileage")
    public WebElement mileageInp;

    @FindBy(id="priceEstimationFormButton")
    public WebElement priceEstimationFormButton;

    public PriceEstimationPageObject() {
        PageFactory.initElements(WDUtil.getWebDriver(), this);
    }

    public void fillFormAndSubmit(String makeId, String month, String year, String model, String fuel, String power, String equipmentLine, String mileage) throws InterruptedException {

        UIUtil.waitAndSelectByText(make, makeId);
        UIUtil.waitAndSelectByText(firstRegistrationMonth, month);
        UIUtil.waitAndSelectByText(firstRegistrationYear, year);
        UIUtil.waitAndSelectByText(vehicleGroupId, model);
        UIUtil.waitAndSelectByText(fuelId, fuel);
        UIUtil.waitAndSelectByText(powerSel, power);
        UIUtil.waitAndSelectByText(equipmentLineSel, equipmentLine);
        mileageInp.sendKeys(mileage);
        priceEstimationFormButton.click();

        //make.selectByValue("Alfa Romeo");
    }

}


    /*
    @FindBy(how = How.ID, using = "equipmentLine")
    private WebElement equipmentLine;

    @FindBy(how = How.CSS, using = "#fuelId:not([disabled])")
    private WebElement fuelTypeEnabled;
    */

//PageFactory.initElements(driver, this);
//new Select(driver.findElement(By.id("makeId"))).selectByVisibleText(makeId);
//Select m = new Select(make);
//m.selectByVisibleText(makeId);
        /*
        WebElement myDynamicElement2 =  (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id("firstRegistrationYear")));
        new Select(driver.findElement(By.id("firstRegistrationYear"))).selectByVisibleText(year);

        Thread.sleep(500);
        new Select(driver.findElement(By.id("vehicleGroupId"))).selectByVisibleText(model);
        Thread.sleep(500);
        new Select(driver.findElement(By.id("fuelId"))).selectByVisibleText(fuel);
        Thread.sleep(500);
        new Select(driver.findElement(By.id("power"))).selectByVisibleText(power);
        Thread.sleep(500);
        new Select(driver.findElement(By.id("equipmentLine"))).selectByVisibleText(equipmentline);
        Thread.sleep(500);
        */