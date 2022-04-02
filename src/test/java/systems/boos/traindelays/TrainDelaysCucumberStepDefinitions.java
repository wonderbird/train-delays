package systems.boos.traindelays;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainDelaysCucumberStepDefinitions {

    private static final String LOGGER_NAME = "systems.boos.traindelays";

    @Autowired
    private CommandLineInterface cli;

    private static MemoryAppender memoryAppender;

    @Before
    public static void setupLogger() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.start();
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }

    @After
    public void resetAndStopLogger() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    @When("^I run the application$")
    public void i_run_the_application() {
        cli.run();
    }

    @Then("^I should see scheduled arrival time for the next train$")
    public void i_should_see_the_train_delays() {
        List<ILoggingEvent> events = memoryAppender.search("Next train is scheduled to arrive at ", Level.INFO);

        assertEquals(1, events.size());

        String actualTime = (String) events.get(0).getArgumentArray()[0];
        assertThat(actualTime, matchesPattern("[0-9]{2}:[0-9]{2}$"));
    }
}
