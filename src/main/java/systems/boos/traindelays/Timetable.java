package systems.boos.traindelays;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class Timetable {

    @JacksonXmlProperty(localName = "s")
    private List<TimetableStop> timetableStops = new ArrayList<>();

    public List<TimetableStop> getTimetableStops() {
        return timetableStops;
    }

    public void setTimetableStops(List<TimetableStop> timetableStops) {
        this.timetableStops = timetableStops;
    }
}
