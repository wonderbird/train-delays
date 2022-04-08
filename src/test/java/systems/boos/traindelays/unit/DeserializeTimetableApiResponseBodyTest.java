package systems.boos.traindelays.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import systems.boos.traindelays.Timetable;
import systems.boos.traindelays.common.TimetableApiResponses;

class DeserializeTimetableApiResponseBodyTest {
    @Test
    void DeserializeTimetableApiResponseBody() throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        String xmlAsString = TimetableApiResponses.getRecordedResponse();

        Timetable timetable = mapper.readValue(xmlAsString, Timetable.class);
        Assertions.assertNotNull(timetable);
    }
}
