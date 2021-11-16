package StepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",glue={"StepDefinitions"},
monochrome = true,
  plugin = {"pretty","json:target/cucumber.json",
          "junit:target/JUnitReports/report.xml",
          "html:target/cucumber-reports"
  },

  tags="@test2"
        )
public class TestRunner {

}
