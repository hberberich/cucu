package skeleton;

import Helper.Browsers;
import Helper.CarDataDE;
import Helper.Environment;
import Helper.WDUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class PriceEstimationStepdefs {

    @Given("^User opens price estimation tool on \"([^\"]*)\"$")
    public void userOpensPriceEstimationTool(String url) throws Throwable {
        WDUtil.startDriver(url.replace("<env>", Environment.Dev), Browsers.Safari);
    }

    @When("^User enters his car data \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and submits$")
    public void userEntersHisCarDataAndSubmits(String makeId, String month, String year, String model, String fuel, String power, String equipmentline, String mileage ) throws Throwable {

        new PriceEstimationPageObject().fillFormAndSubmit(makeId, month, year, model, fuel, power, equipmentline, mileage);
        new PriceEstimationDetailPageObject().fillFormAndSubmit();
    }

    @When("^User enters default \"([^\"]*)\"$")
    public void userEntersDefault(String carData) throws Throwable {
        switch (carData) {
            case "CarDataDE.DevProd":
                new PriceEstimationPageObject().fillFormAndSubmit(CarDataDE.DevProd.makeId, CarDataDE.DevProd.firstRegistrationMonth, CarDataDE.DevProd.firstRegistrationYear, CarDataDE.DevProd.vehicleGroupId,
                        CarDataDE.DevProd.fuelId, CarDataDE.DevProd.power, CarDataDE.DevProd.equipmentLine, CarDataDE.DevProd.mileage);
                break;
            case "CarDataDE.LocalHost":
                new PriceEstimationPageObject().fillFormAndSubmit(CarDataDE.LocalHost.makeId, CarDataDE.LocalHost.firstRegistrationMonth, CarDataDE.LocalHost.firstRegistrationYear, CarDataDE.LocalHost.vehicleGroupId,
                        CarDataDE.LocalHost.fuelId, CarDataDE.LocalHost.power, CarDataDE.LocalHost.equipmentLine, CarDataDE.LocalHost.mileage);
                break;
        }
    }

    @Then("^The price estimation is accurate$")
    public void thePriceEstimationIsAccurate() throws Throwable {

        new PriceEstimationResultPageObject().checkPriceEstimation();
    }

    @When("^User changes language to \"([^\"]*)\"$")
    public void userChangesLanguageTo(String lang) throws Throwable {
        new PriceEstimationPageObject().switchLanguage(lang);
    }

    @Then("^Page is shown with new \"([^\"]*)\"$")
    public void pageIsShownWithNew(String lang) throws Throwable {
        new PriceEstimationPageObject().checkLanguage(lang);
    }


    @And("^User clicks link Standardausstattung einblenden$")
    public void userClicksLinkStandardausstattungEinblenden() throws Throwable {
        new PriceEstimationDetailPageObject().openStandardEquipment();
    }

    @Then("^Standard Equipment is shown$")
    public void standardEquipmentIsShown() throws Throwable {
        new PriceEstimationDetailPageObject().checkStandardEquipmentIsShown();
    }

    @And("^Selects additional equipment$")
    public void selectsAdditionalEquipment() throws Throwable {
        new PriceEstimationDetailPageObject().loopColors();
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Boolean prevScenarioFailed = scenario.isFailed();
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) WDUtil.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); //stick it in the report
        }
        WDUtil.getWebDriver().close();
        // Close Safari
        WDUtil.getWebDriver().quit();
    }
}
