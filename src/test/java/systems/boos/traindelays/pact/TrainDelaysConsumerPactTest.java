package systems.boos.traindelays.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@ExtendWith(PactConsumerTestExt.class)
class TrainDelaysConsumerPactTest {
    @Pact(consumer = "ClientApplication", provider = "TrainDelaysService")
    V4Pact nextDepartureIsPresent(PactBuilder builder) {
        return builder
                .usingLegacyDsl()
                .given("the next departure time is present")
                .uponReceiving("fetch next departure")
                .method("GET")
                .path("/nextdeparture")
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .body(new PactDslJsonBody().datetime("expectedDeparture", "yyyy-MM-dd'T'HH:mm:ss'Z'"))
                .toPact(V4Pact.class);
    }

    @Pact(consumer = "ClientApplication", provider = "TrainDelaysService")
    V4Pact nextDepartureIsNotPresent(PactBuilder builder) {
        return builder
                .usingLegacyDsl()
                .given("the next departure time is not present")
                .uponReceiving("fetch next departure")
                .method("GET")
                .path("/nextdeparture")
                .willRespondWith()
                .status(HttpStatus.NO_CONTENT.value())
                .body("")
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "nextDepartureIsPresent")
    void departures_whenDepartureIsPresent(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Departure departure = new TrainDelaysService(restTemplate).nextDeparture();

        Assertions.assertNotNull(departure.expectedDeparture);
    }

    @Test
    @PactTestFor(pactMethod = "nextDepartureIsNotPresent")
    void departures_whenDepartureIsNotPresent(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Departure departure = new TrainDelaysService(restTemplate).nextDeparture();

        Assertions.assertNull(departure);
    }

    private static class TrainDelaysService {
        private final RestTemplate restTemplate;

        public TrainDelaysService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public Departure nextDeparture() {
            return restTemplate.<Departure>exchange("/nextdeparture",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }).getBody();
        }
    }

    private static class Departure {
        public Instant expectedDeparture;
    }
}
