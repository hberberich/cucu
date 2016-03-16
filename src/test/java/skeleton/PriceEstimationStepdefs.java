package skeleton;

import Helper.Browsers;
import Helper.CarDataDE;
import Helper.Environment;
import Helper.WDUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class PriceEstimationStepdefs {

    @Given("^User opens price estimation tool on \"([^\"]*)\"$")
    public void userOpensPriceEstimationTool(String url) throws Throwable {
        //WDUtil.startDriver(url.replace("<env>", Environment.Dev), Browsers.Firefox);
        WDUtil.getWebDriver().get(url.replace("<env>", Environment.Dev));
        WDUtil.getWebDriver().manage().addCookie(new Cookie("as24test", "true"));
//        Set<Cookie> cookiesList =  WDUtil.getWebDriver().manage().getCookies();
//        for(Cookie getcookies :cookiesList) {
//            System.out.println(getcookies );
//        }
    }

    @When("^User enters his car data \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and submits$")
    public void userEntersHisCarDataAndSubmits(String makeId, String month, String year, String model, String fuel, String power, String equipmentline, String mileage ) throws Throwable {

        new PriceEstimationPageObject().fillFormAndSubmit(makeId, month, year, model, fuel, power, equipmentline, mileage);
        new PriceEstimationDetailPageObject().fillFormAndSubmit();
    }

    @And("^User selects all additional equipment$")
    public void userSelectsAllAdditionalEquipment() throws Throwable {
        new PriceEstimationDetailPageObject().fillFormAndSubmitAllEquip();
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
            case "CarDataDE.LocalDocker":
                new PriceEstimationPageObject().fillFormAndSubmit(CarDataDE.LocalDocker.makeId, CarDataDE.LocalDocker.firstRegistrationMonth, CarDataDE.LocalDocker.firstRegistrationYear, CarDataDE.LocalDocker.vehicleGroupId,
                        CarDataDE.LocalDocker.fuelId, CarDataDE.LocalDocker.power, CarDataDE.LocalDocker.equipmentLine, CarDataDE.LocalDocker.mileage);
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

    @Before
    public void setup(Scenario scenario) throws Exception {
        WDUtil.startDriver(Browsers.Firefox);
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


    @Then("^The right error page is shown with error messages \"([^\"]*)\" \"([^\"]*)\"$")
    public void theRightErrorPageIsShownWithErrorMessages(String errText, String errButtonText) throws Throwable {
        new ErrorPageObject().checkErrorPage(errText, errButtonText);
    }
}
