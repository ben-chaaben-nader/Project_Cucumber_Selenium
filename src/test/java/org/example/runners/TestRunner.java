package org.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",       // ✅ Correct path
        glue = {
                "org.example.StepDefinitions.Login",
                "org.example.StepDefinitions.Menu",
                "org.example.StepDefinitions.SocialMedia"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
        monochrome = true,
        tags = ""
)
public class TestRunner {
}
