package systems.boos.traindelays.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.xml.PactXmlBuilder;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import systems.boos.traindelays.Event;
import systems.boos.traindelays.Timetable;
import systems.boos.traindelays.TimetablesService;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.Matchers.timestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
class TimetableConsumerPactTest {
    Instant arbitraryInstant = Instant.parse("2022-04-01T19:49:00Z");

    @Pact(consumer = "FrontendApplication", provider = "TimetableService")
    RequestResponsePact findChanges(PactDslWithProvider builder) {
        String apiDateTimePattern = "yyMMddHHmm";
        String apiResponseChangedTime = arbitraryInstant.atZone(ZoneId.of("Europe/Berlin"))
                .format(DateTimeFormatter.ofPattern(apiDateTimePattern));

        return builder
                .given("station with eva 8005143 exists")
                .uponReceiving("fetch changes for station with eva 8005143")
                .method("GET")
                .path("/fchg/8005143")
                .willRespondWith()
                .status(200)
                .headers(headers())
                .body(new PactXmlBuilder("timetable")
                        .build(root ->
                                root.eachLike("s", 1, Collections.emptyMap(), timetableStop ->
                                        timetableStop.appendElement("dp", mapOf("ct", timestamp(apiDateTimePattern, apiResponseChangedTime)))
                                )
                        ))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "findChanges")
    void findChanges_whenStationWithEvaExists(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Timetable actual = new TimetablesService(restTemplate).fetchChanges();

        Event expected = new Event();
        expected.setChangedTime(arbitraryInstant);

        assertEquals(1, actual.getTimetableStops().size());
        assertEquals(expected, actual.getTimetableStops().get(0).getDepartures().get(0));
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

    private Map<String, String> headers() {
        return mapOf("Content-Type", "application/xml");
    }
}
