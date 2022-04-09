package systems.boos.traindelays.cucumber.steps;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.BeforeAll;
import org.mockserver.integration.ClientAndServer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import systems.boos.traindelays.CommandLineInterface;
import systems.boos.traindelays.common.MemoryAppender;
import systems.boos.traindelays.common.TimetableApiResponses;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class TrainDelaysCucumberStepDefinitions {

    private static final String LOGGER_NAME = "systems.boos.traindelays";
    private static ClientAndServer mockServer;
    private static MemoryAppender memoryAppender;
    // Cucumber's mechanism of wiring is not known to IntelliJ. Thus, we suppress the warning issued by IntelliJ.
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private CommandLineInterface cli;

    @Before
    public void setupLogger() {
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

    @BeforeAll
    public static void startMockServer() {
        mockServer = ClientAndServer.startClientAndServer(9000);

        // TODO Clarify why configureMockServer() needs to be called twice
        // If this is not done, then the cucumber BeforeAll hook will fail and report a status 404
        configureMockServerWithExpectedDepartureTime("00:00");
    }

    @AfterAll
    public static void stopMockServer() {
        mockServer.stop();
    }

    @Given("The next train is expected to leave at {string}")
    public void theNextTrainIsExpectedToLeaveAt(String expectedDepartureTime) {
        configureMockServerWithExpectedDepartureTime(expectedDepartureTime);
    }

    @Given("The mock service returns a recorded API response")
    public void theMockServiceReturnsARecordedAPIResponse() {
        configureMockServerWithResponse(TimetableApiResponses.getRecordedResponse());
    }

    @When("^I run the application$")
    public void i_run_the_application() {
        cli.run();
    }

    @Then("I should see {string} as scheduled departure time for the next train")
    public void i_should_see_the_train_delays(String expectedDepartureTime) {
        List<ILoggingEvent> events = memoryAppender.search("Next train is scheduled to leave at ", Level.INFO);

        assertEquals(1, events.size(), "Expected exactly one matching log message");

        String actualDepartureTime = (String) events.get(0).getArgumentArray()[0];
        assertEquals(expectedDepartureTime, actualDepartureTime);
    }

    private static void configureMockServerWithExpectedDepartureTime(String expectedDepartureTime) {
        String responseBody = TimetableApiResponses.createResponseWithDepartureTime(expectedDepartureTime);
        configureMockServerWithResponse(responseBody);
    }

    private static void configureMockServerWithResponse(String responseBody) {
        mockServer.reset();
        mockServer
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/timetables/v1/fchg/8005143")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/xml")
                                .withBody(responseBody)
                );
    }
}
