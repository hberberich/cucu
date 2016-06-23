package Unused;

import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertTrue;

public class Stepdefs {
    @Given("^I have (\\d+) cukes in my belly$")
    public void I_have_cukes_in_my_belly(int cukes) throws Throwable {
        Belly belly = new Belly();
        belly.eat(cukes);
        firefox();

    }


        private static void firefox() {
            WebDriver driver = new FirefoxDriver();
            driver.navigate().to("http://www.autoscout24.de");

            driver.findElement(By.id("blockSearchButton")).click();

            assertTrue(driver.getPageSource().contains("Suche verfeinern"));

            driver.close();
        }
        private static void htmlunit() {
            WebDriver driver = new HtmlUnitDriver();
            driver.navigate().to("http://www.autoscout24.de");

            driver.findElement(By.id("blockSearchButton")).click();


            assertTrue(driver.getPageSource().contains("Suche verfeinern"));

            driver.close();
        }

}
