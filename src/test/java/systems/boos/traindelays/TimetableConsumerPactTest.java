package systems.boos.traindelays;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.Matchers.timestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
class TimetableConsumerPactTest {
    @Pact(consumer = "FrontendApplication", provider = "TimetableService")
    RequestResponsePact findChanges(PactDslWithProvider builder) {
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
                                        timetableStop.appendElement("dp", mapOf("ct", timestamp("yyMMddHHmm", "2204012149")))
                                )
                        ))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "findChanges")
    void findChanges_whenStationWithEvaExists(MockServer mockServer) throws ParseException {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Timetable actual = new TimetablesService(restTemplate).fetchChanges();

        TimetableStop expected = new TimetableStop();
        expected.setChangedTime(new SimpleDateFormat("yyMMddHHmm").parse("2204012149"));

        assertEquals(1, actual.getTimetableStops().size());
        assertEquals(expected, actual.getTimetableStops().get(0));
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
