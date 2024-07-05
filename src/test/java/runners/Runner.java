package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber.json",},
        features = "/Users/mesut/IdeaProjects/_Projeler/1_apiProjects/com.apiCucumber.restful-booker/src/test/resources/features",
        glue = "steps",
        tags = "@mesut",
        dryRun =false
)

public class Runner {

}