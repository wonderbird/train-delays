package systems.boos.traindelays.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactBuilder;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.xml.PactXmlBuilder;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import systems.boos.traindelays.TimetablesService;
import systems.boos.traindelays.model.Event;
import systems.boos.traindelays.model.Timetable;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static au.com.dius.pact.consumer.dsl.Matchers.timestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
class TimetableConsumerPactTest {
    Instant arbitraryInstant = Instant.parse("2022-04-01T19:49:00Z");

    @Pact(consumer = "FrontendApplication", provider = "TimetableService")
    V4Pact invalidTimetableApiToken(PactBuilder builder) {
        return builder
                .usingLegacyDsl()
                .given("the timetable api token is invalid")
                .uponReceiving("fetch changes for station with eva 8005143")
                .method("GET")
                .path("/fchg/8005143")
                .willRespondWith()
                .status(HttpStatus.UNAUTHORIZED.value())
                .headers(jsonContentTypeHeader())
                .body(newJsonBody(root -> {
                    root.object("error", error -> {
                       error.integerType("code", 900901);
                       error.stringType("message", "Invalid Credentials");
                       error.stringType("description", "Access failure. Unauthorized.");
                    });
                }).build())
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "invalidTimetableApiToken")
    void findChanges_whenApiTokenIsInvalid(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Timetable actual = new TimetablesService(restTemplate).fetchChanges();

        assertEquals(0, actual.getTimetableStops().size());
    }

    @Pact(consumer = "FrontendApplication", provider = "TimetableService")
    V4Pact departureIsPresent(PactBuilder builder) {
        String apiDateTimePattern = "yyMMddHHmm";
        String apiResponseChangedTime = arbitraryInstant.atZone(ZoneId.of("Europe/Berlin"))
                .format(DateTimeFormatter.ofPattern(apiDateTimePattern));

        return builder
                .usingLegacyDsl()
                .given("departure for station with eva 8005143 present")
                .uponReceiving("fetch changes for station with eva 8005143")
                .method("GET")
                .path("/fchg/8005143")
                .willRespondWith()
                .status(200)
                .headers(xmlContentTypeHeader())
                .body(new PactXmlBuilder("timetable")
                        .build(root ->
                                root.eachLike("s", 1, Collections.emptyMap(), timetableStop ->
                                        timetableStop.appendElement("dp", mapOf("ct", timestamp(apiDateTimePattern, apiResponseChangedTime)))
                                )
                        ))
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "departureIsPresent")
    void findChanges_whenDepartureIsPresent(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Timetable actual = new TimetablesService(restTemplate).fetchChanges();

        Event expected = new Event();
        expected.setChangedTime(arbitraryInstant);

        assertEquals(1, actual.getTimetableStops().size());
        assertEquals(expected, actual.getTimetableStops().get(0).getDepartures().get(0));
    }

    @Pact(consumer = "FrontendApplication", provider = "TimetableService")
    V4Pact departureIsNotPresent(PactBuilder builder) {
        return builder
                .usingLegacyDsl()
                .given("no departures for station with eva 8005143 present")
                .uponReceiving("fetch changes for station with eva 8005143")
                .method("GET")
                .path("/fchg/8005143")
                .willRespondWith()
                .status(200)
                .headers(xmlContentTypeHeader())
                .body(new PactXmlBuilder("timetable")
                        .build(root -> {}))
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "departureIsNotPresent")
    void findChanges_whenDepartureIsNotPresent(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Timetable actual = new TimetablesService(restTemplate).fetchChanges();

        assertEquals(0, actual.getTimetableStops().size());
    }

    private Map<String, String> xmlContentTypeHeader() {
        return mapOf("Content-Type", "application/xml");
    }

    private Map<String, String> jsonContentTypeHeader() {
        return mapOf("Content-Type", "application/json");
    }

    private <T> Map<String, T> mapOf(String key, T value) {
        return MapUtils.putAll(new HashMap<>(), new Object[]{key, value});
    }

    private Map<String, Object> mapOf(String key1, Object value1, String key2, Object value2) {
        return MapUtils.putAll(new HashMap<>(), new Object[]{key1, value1, key2, value2});
    }

    private Map<String, Object> mapOf(String key1, Object value1, String key2, Object value2, String key3, Object value3,
                                      String key4, Object value4, String key5, Object value5) {
        return MapUtils.putAll(new HashMap<>(), new Object[]{key1, value1, key2, value2, key3, value3, key4, value4, key5, value5});
    }
}
