package systems.boos.traindelays.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import systems.boos.traindelays.common.TimetableApiResponses;
import systems.boos.traindelays.model.Timetable;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeserializeTimetableApiResponseBodyTest {

    /**
     * Regression test to ensure that unprocessed XML properties do not cause exceptions.
     * <p>
     * The model classes do not map every XML element to fields. As a consequence, the
     * model classes specify `@JsonIgnoreProperties(ignoreUnknown = true)`. Otherwise
     * Jackson would throw a JsonProcessingException while parsing an API response body.
     */
    @Test
    void DeserializeTimetableApiResponseBody() throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        String xmlAsString = TimetableApiResponses.createResponseWithDepartureTime("00:00", Clock.systemDefaultZone());

        Timetable timetable = mapper.readValue(xmlAsString, Timetable.class);

        assertNotNull(timetable, "timetable");
        assertEquals(1, timetable.getTimetableStops().size(), "number of timetable stops");
        assertEquals(1, timetable.getTimetableStops().get(0).getDepartures().size(), "number of departure events");
    }
}
