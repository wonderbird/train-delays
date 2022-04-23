package systems.boos.traindelays.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timetable {
    @Override
    public String toString() {
        return "Timetable{" +
                "timetableStops=" + timetableStops +
                '}';
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "s")
    private List<TimetableStop> timetableStops = new ArrayList<>();

    public List<TimetableStop> getTimetableStops() {
        return timetableStops;
    }

    public void setTimetableStops(List<TimetableStop> timetableStops) {
        this.timetableStops = timetableStops;
    }

    // TODO test boundary conditions: no timetable stop / departure etc., (done) service exceptions
    public Optional<Instant> findFirstDepartureAfter(Instant after) {
        return getTimetableStops().stream()
                .filter(stop -> !stop.getDepartures().isEmpty() && stop.getDepartures().get(0).getChangedTime() != null)
                .map(stop -> stop.getDepartures().get(0).getChangedTime())
                .sorted()
                .filter(departure -> departure.isAfter(after))
                .findFirst();
    }
}
