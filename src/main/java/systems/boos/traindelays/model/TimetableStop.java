package systems.boos.traindelays.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimetableStop {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "dp")
    private List<Event> departures = new ArrayList<>();

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    public List<Event> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Event> departures) {
        this.departures = departures;
    }

    @Override
    public String toString() {
        return "TimetableStop{" +
                "departures=" + departures +
                ", id='" + id + '\'' +
                '}';
    }
}
