package ru.bspb.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "ru.bspb.cucumber.test.glue",
        tags = "@MoveToLoginPage",
        snippets = CucumberOptions.SnippetType.UNDERSCORE
)
public class RunnerTest {
}
