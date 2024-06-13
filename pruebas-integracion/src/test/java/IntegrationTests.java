import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;
import static io.cucumber.junit.CucumberOptions.SnippetType.UNDERSCORE;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
//        snippets = CAMELCASE,
        plugin = { "pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json" })
public class IntegrationTests {
}
