package stepdefs;

import Helper.UIUtil;
import Helper.WDUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import stepdefs.PriceEstimationDetailPageObject;
import stepdefs.PriceEstimationPageObject;
import stepdefs.PriceEstimationResultPageObject;

public class PriceEstimationStepdefs {

    @Given("^User opens price estimation tool on \"([^\"]*)\"$")
    public void userOpensPriceEstimationTool(String url) throws Throwable {
        UIUtil.startDriver(url);
        //UIUtil.startDriver("http://localhost:9000/fahrzeugbewertung");
    }

    ;

    @When("^User enters his car data \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and submits$")
    public void userEntersHisCarDataAndSubmits(String makeId, String month, String year, String model, String fuel, String power, String equipmentline, String mileage ) throws Throwable {

        new PriceEstimationPageObject().fillFormAndSubmit(makeId, month, year, model, fuel, power, equipmentline, mileage);
        new PriceEstimationDetailPageObject().fillFormAndSubmit();

    }

    @Then("^The price estimation is accurate$")
    public void thePriceEstimationIsAccurate() throws Throwable {

        new PriceEstimationResultPageObject().checkPriceEstimation();
        WDUtil.getWebDriver().close();

    }


}
