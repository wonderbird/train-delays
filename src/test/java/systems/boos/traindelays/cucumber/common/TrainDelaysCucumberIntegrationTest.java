package systems.boos.traindelays.cucumber.common;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        glue = "systems.boos.traindelays",
        plugin = {"pretty", "html:target/cucumber"})
public class TrainDelaysCucumberIntegrationTest {
}
