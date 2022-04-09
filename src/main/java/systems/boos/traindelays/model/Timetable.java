package systems.boos.traindelays.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timetable {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "s")
    private List<TimetableStop> timetableStops = new ArrayList<>();

    public List<TimetableStop> getTimetableStops() {
        return timetableStops;
    }

    public void setTimetableStops(List<TimetableStop> timetableStops) {
        this.timetableStops = timetableStops;
    }
}
