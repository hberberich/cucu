package skeleton;

import Helper.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PriceEstimationPageObject {

    @FindBy(id="languageSwitch")
    public WebElement languageSwitch;

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
        UIUtil.waitAndEnterText(mileageInp, mileage);
        //mileageInp.sendKeys(mileage);
        priceEstimationFormButton.click();

        //make.selectByValue("Alfa Romeo");
    }

    public void switchLanguage(String lang) {

        UIUtil.waitAndSelectByText(languageSwitch, lang);
        UIUtil.waitForElementToBeClickable(languageSwitch);
    }

    // Check selected language is used
    public void checkLanguage(String lang) {
        // Check selected language
        Assert.assertTrue(new Select(languageSwitch).getFirstSelectedOption().getText().equals(lang));
        // Check Text in make list and culture Cookie
        if (WDUtil.getBrowser().equals(Browsers.Safari)) {
            //UIUtil.waitForElementToBeClickable(make); // Does not work. To be improved.
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        switch (lang) {
            case "NL":
                Assert.assertTrue(CountrynlBE.makeText.equals(new Select(make).getFirstSelectedOption().getText()));
                Assert.assertTrue(CountrynlBE.culture.equals(WDUtil.getWebDriver().manage().getCookieNamed("culture").getValue()));
                break;
            case "FR":
                Assert.assertTrue(CountryfrBE.makeText.equals(new Select(make).getFirstSelectedOption().getText()));
                Assert.assertTrue(CountryfrBE.culture.equals(WDUtil.getWebDriver().manage().getCookieNamed("culture").getValue()));
                break;
        }
    }
}
    /*
        Set<Cookie> cookiesList =  WDUtil.getWebDriver().manage().getCookies();
    for(Cookie getcookies :cookiesList) {
            System.out.println(getcookies );
            }
    */

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