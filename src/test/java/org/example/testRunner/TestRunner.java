package org.example.testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
features ="./Features",
        glue = {"org.example.stepDefinition"},
        dryRun = false,
        tags = "@PositiveScenario",
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report/cucumber.html"},
        monochrome = true


)
public class TestRunner {
}
