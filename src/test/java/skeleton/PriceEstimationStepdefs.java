package skeleton;

import Helper.WDUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PriceEstimationStepdefs {

    @Given("^User opens price estimation tool$")
    public void userOpensPriceEstimationTool() throws Throwable {
        startDriver();

    }

    public void startDriver() {
        WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();
        //Selenium selenium = new DefaultSelenium(“localhost”, 4444, “*firefox”, “http://www.google.com”);
/*
        DesiredCapabilities caps = DesiredCapabilities.chrome();

        WebDriver driver = null;
        try {
            //System.setProperty("webdriver.chrome.logfile", "/Users/hberberich/apps/chromedriver.log");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
*/
        //Selenium selenium = new DefaultSelenium(“localhost”, 4444, “*chrome”, “http://www.google.com”);
        //WebDriver driver = new HtmlUnitDriver();
        //WebDriver driver = new PhantomJSDriver();

        WDUtil.setWebDriver(driver);
        driver.navigate().to("http://localhost:9000/fahrzeugbewertung");
        driver.manage().window().maximize();
        driver.manage().window().setPosition(new Point(1921, 1));
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
