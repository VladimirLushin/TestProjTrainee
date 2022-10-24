package ru.bspb.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = "src/test/java/features",
        glue = "ru.bspb.cucumber.test.glue",
        snippets = CucumberOptions.SnippetType.UNDERSCORE
)
public class RunnerTest {
}
