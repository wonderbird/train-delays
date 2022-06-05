package systems.boos.traindelays.cucumber.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import systems.boos.traindelays.ExpectedDepartureResponse;
import systems.boos.traindelays.common.TimetableApiResponses;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.Duration.between;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class TrainDelaysCucumberStepDefinitions {

    private static ClientAndServer mockServer;
    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;
    private ExpectedDepartureResponse response;

    @BeforeAll
    public static void startMockServer() {
        mockServer = ClientAndServer.startClientAndServer(9000);

        // If configureMockServer() is not called twice, the cucumber BeforeAll hook will fail and report a status 404
        configureMockServerWithExpectedDepartureTime(Instant.now());
    }

    @AfterAll
    public static void stopMockServer() {
        mockServer.stop();
    }

    private String apiEndpoint() {
        String serverUrl = "http://localhost";
        String apiEndpoint = "/nextdeparture";
        return serverUrl + ":" + port + apiEndpoint;
    }

    private static void configureMockServerWithExpectedDepartureTime(Instant expectedDepartureTime) {
        String responseBody = TimetableApiResponses.createResponseWithExpectedDeparture(expectedDepartureTime);
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

    @Given("the next train is expected to leave in {int} minutes")
    public void theNextTrainIsExpectedToLeaveAt(int expectedDepartureMinutes) {
        Instant expectedDepartureTime = Instant.now().plus(expectedDepartureMinutes, ChronoUnit.MINUTES);
        configureMockServerWithExpectedDepartureTime(expectedDepartureTime);
    }

    @When("^I call the API$")
    public void i_run_the_application() {
        response = restTemplate.getForEntity(apiEndpoint(), ExpectedDepartureResponse.class).getBody();
    }

    @Then("the expected departure is {int} minutes in the future")
    public void i_should_see_the_train_delays(int expectedDepartureMinutes) {
        ZonedDateTime expected = Instant.now().plus(expectedDepartureMinutes, ChronoUnit.MINUTES).atZone(ZoneOffset.UTC);
        ZonedDateTime actual = response.getExpectedDeparture();

        long differenceSeconds = Math.abs(between(expected, actual).getSeconds());

        // Usually the response takes one second.
        // If the test is set up 30 seconds after a full minute,
        // then the response will be converted to the next minute.
        // Thus, we have to tolerate one minute deviation.
        int toleranceSeconds = 60;

        assertTrue(differenceSeconds <= toleranceSeconds, "Expected departure time is " + differenceSeconds + " seconds off");
    }
}
