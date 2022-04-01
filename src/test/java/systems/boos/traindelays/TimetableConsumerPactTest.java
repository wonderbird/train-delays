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
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.HashMap;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PactConsumerTestExt.class)
public class TimetableConsumerPactTest {
    @Pact(consumer = "FrontendApplication", provider = "TimetableService")
    RequestResponsePact getStationEva(PactDslWithProvider builder) {
        return builder
                .given("station with name Rösrath-Stümpen exists")
                .uponReceiving("get eva id for station Rösrath-Stümpen")
                .method("GET")
                .path("/station/R%C3%B6srath-St%C3%BCmpen")
                .willRespondWith()
                .status(200)
                .headers(headers())
                .body(new PactXmlBuilder("stations").build(root -> {
                    root.eachLike("station", 2, mapOf(
                            "name", string("Rösrath-Stümpen"),
                            "eva", string("8005143"),
                            "ds100", string("KRST"),
                            "db", string("true"),
                            "creationts", timestamp("yy-MM-dd HH:mm:ss.SSS")
                    ));
                }))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getStationEvaId")
    void getStationEva_whenStationWithNameRoesrathStuempenExists(MockServer mockServer) {
        Station expected = new Station();
        expected.setEva("8005143");
        expected.setName("Rösrath-Stümpen");
        expected.setDs100("KRST");
        expected.setDb(true);
        expected.setCreationts("2020-01-01 00:00:00.000");

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Station actual = new StationService(restTemplate).getStation("Rösrath-Stümpen");

        assertEquals(expected, actual);
    }

    private <T> Map<String, T> mapOf(String key, T value) {
        return MapUtils.putAll(new HashMap<>(), new Object[]{key, value});
    }

    private Map<String, Object> mapOf(String key1, Object value1, String key2, Object value2, String key3, Object value3,
                                      String key4, Object value4, String key5, Object value5) {
        return MapUtils.putAll(new HashMap<>(), new Object[]{key1, value1, key2, value2, key3, value3, key4, value4, key5, value5});
    }

    private Map<String, String> headers() {
        return mapOf("Content-Type", "application/xml");
    }
}
