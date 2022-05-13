package systems.boos.traindelays.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@ExtendWith(PactConsumerTestExt.class)
class TrainDelaysConsumerPactTest {
    @Pact(consumer = "ClientApplication", provider = "TrainDelaysService")
    V4Pact nextDeparture(PactBuilder builder) {
        return builder
                .usingLegacyDsl()
                .given("the next departure time is present")
                .uponReceiving("fetch next departure")
                .method("GET")
                .path("/nextdeparture")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody().datetime("expectedDeparture", "yyyy-MM-dd'T'HH:mm:ss'Z'"))
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "nextDeparture")
    @SuppressWarnings("java:S2699")
        // Suppress SonarLint warning about missing assert. This test works if no exception is thrown.
    void departures_whenDepartureExists(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        new TrainDelaysService(restTemplate).nextDeparture();
    }

    private static class TrainDelaysService {
        private final RestTemplate restTemplate;

        public TrainDelaysService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public void nextDeparture() {
            Departure ignoredDeparture = restTemplate.<Departure>exchange("/nextdeparture",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }).getBody();
        }
    }

    private static class Departure {
        @JsonProperty("expecteddeparture")
        public Instant expectedDeparture;
    }
}
