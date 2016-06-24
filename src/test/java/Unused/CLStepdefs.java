package Unused;

import Helper.WDUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class CLStepdefs {


    @Given("^User opens classified-list page$")
    public void userOpensClassifiedListPage() throws Throwable {
        WDUtil.startDriver("https://www.autoscout24.com/classified-list", "firefox");
    }

    @When("^User selects a filter results are updated$")
    public void userSelectsAFilterResultsAreUpdated() throws Throwable {


        new CLPageObject().fillFormAndSubmit();


        WDUtil.getWebDriver().close();

    }
}
