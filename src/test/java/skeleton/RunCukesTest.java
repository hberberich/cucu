package skeleton;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"}, plugin = {"pretty"},
        tags={"@local"}
)
public class RunCukesTest {

    RunCukesTest() {
        System.out.println("Hello");
    }
}
