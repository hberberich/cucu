package skeleton;

import Helper.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PriceEstimationInputPageObject {

    @FindBy(id="languageSwitch")
    public WebElement languageSwitch;

    //@FindBy(how = How.ID, using = "makeId")
    @FindBy(id="makeId")
    public WebElement make;

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

    @FindBy(xpath="//input[@name='natCode']/../label")
    public WebElement natCodeLabel;
    @FindBy(xpath="//ul[@class='pe-input-form__vehicle-details']/li[1]")
    public WebElement vehicleDetails1;
    @FindBy(xpath="//ul[@class='pe-input-form__vehicle-details']/li[2]")
    public WebElement vehicleDetails2;
    @FindBy(xpath="//ul[@class='pe-input-form__vehicle-details']/li[3]")
    public WebElement vehicleDetails3;

    @FindBy(id="priceEstimationFormButton")
    public WebElement priceEstimationFormButton;

    public PriceEstimationInputPageObject() {
        PageFactory.initElements(WDUtil.getWebDriver(), this);
    }

    public void fillFormAndSubmit(String makeId, String month, String year, String model, String fuel, String power, String equipmentLine, String mileage) throws InterruptedException {

        UIUtil.waitAndSelectByText(make, makeId);
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

    public void fillFormAndCheckModellList() {

        WebDriver wd =  WDUtil.getWebDriver();
        List<WebElement> we = new Select(make).getOptions();
        System.out.println("**************************************************************");

        String url = "http://<env>.autoscout24.de/fahrzeugbewertung"; //http://<env>.autoscout24.be/evaluationvoiture

        for (int i = 13;  i<=13 /*we.size()-1*/; i ++) { //8 = Alpina, 10 = Bentley
            try {
                UIUtil.waitAndSelectByIndex(make, i);
            } catch (Exception ex) {
                System.out.print("");
            }
            //UIUtil.waitAndSelectByText(firstRegistrationMonth, "12");
            int years = new Select(firstRegistrationYear).getOptions().size();
            for (int j = 1; j < years; j++) { //2010
                UIUtil.waitAndSelectByIndex(firstRegistrationYear, j);
                try {
                    for (int k = 1; k<(new Select(vehicleGroupId).getOptions().size()); k++) {
                        UIUtil.waitAndSelectByIndex(vehicleGroupId, k);
                        for (int l = 1; l< (new Select(fuelId).getOptions().size()); l++) {
                            UIUtil.waitAndSelectByIndex(fuelId, l);
                            for (int m = 1; m < (new Select(powerSel).getOptions().size()); m++) {
                                UIUtil.waitAndSelectByIndex(powerSel, m);
                                UIUtil.waitAndSelectByIndex(equipmentLineSel, 1); // Synchronization
                                for (int n = 1; n< (new Select(equipmentLineSel).getOptions().size()); n++) {
                                    UIUtil.waitAndSelectByIndex(equipmentLineSel, n);
                                    List<WebElement> nCodes = WDUtil.getWebDriver().findElements(By.xpath("//input[@name='natCode']/../label"));
                                    for (int o = 0; o<1 /*(nCodes.size())*/; o++) {
                                        //if (nCodes.size() > 1) {
                                        UIUtil.waitAndClick(nCodes.get(o));
                                        //}
                                        System.out.print(new Select(make).getFirstSelectedOption().getText() + " | " +
                                                //(new Select(firstRegistrationMonth).getFirstSelectedOption().getText())  + " | " +
                                                (new Select(firstRegistrationYear).getFirstSelectedOption().getText())  + " | " +
                                                (new Select(vehicleGroupId).getFirstSelectedOption().getText())  + " | " +
                                                (new Select(fuelId).getFirstSelectedOption().getText())  + " | " +
                                                (new Select(powerSel).getFirstSelectedOption().getText())  + " | " +
                                                (new Select(equipmentLineSel).getFirstSelectedOption().getText())  + " | " );
                                        //(vehicleDetails1.getText())  + " | " +
                                        //(vehicleDetails2.getText())  + " | " +
                                        //(vehicleDetails3.getText())  + " | "
                                        String ncLabel = nCodes.get(o).getText();
                                        System.out.print(ncLabel.replaceAll("\n", " | ")  + " | ");
                                        UIUtil.waitAndEnterText(mileageInp, "100000");
                                        System.out.print("100000" + " | ");

                                        priceEstimationFormButton.click();
                                        new PriceEstimationDetailPageObject().fillFormAndSubmit();
                                        new PriceEstimationResultPageObject().checkPriceEstimation();
                                        //* compare private - mixed model 997
                                        String curl = wd.getCurrentUrl();
                                        curl = curl.replace("%3D", "=");
                                        curl = curl.replace("TATSU-997-mixed-models=true", "TATSU-997-mixed-models=false");
                                        wd.get(curl);
                                        new PriceEstimationResultPageObject().checkPriceEstimation();
                                        wd.navigate().back();
                                        //*/
                                        System.out.print(wd.getCurrentUrl() + " | ");

                                        wd.navigate().back();
                                        wd.navigate().back();
                                        System.out.println("");
                                        //System.out.println(System.currentTimeMillis()/1000);
                                        //wd.get(url.replace("<env>", Environment.Prod)  + "?featurebee=price-range-with-medium=true|with-new-result-page=false");


                                    }

                                }
                            }
                        }
                    }
                    //priceEstimationFormButton.click();

                } catch (Exception ex) {
                    //if ((new Select(vehicleGroupId).getOptions().size() <= 1)) {
                    System.out.println(new Select(make).getFirstSelectedOption().getText() + " | 12 | "+ (new Select(firstRegistrationYear).getFirstSelectedOption().getText()) + " | " + "Modell data not available");
                    //System.out.println(ex.getMessage()+ex.getStackTrace());
                }
                //UIUtil.waitAndSelectByIndex(vehicleGroupId, 1);
            }
        }
        System.out.println("**************************************************************");

    }
}
