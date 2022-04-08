package systems.boos.traindelays;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.Instant;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    @JacksonXmlProperty(isAttribute = true, localName = "ct")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyMMddHHmm", timezone = "Europe/Berlin")
    private Instant changedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return Objects.equals(changedTime, event.changedTime);
    }

    @Override
    public int hashCode() {
        return changedTime != null ? changedTime.hashCode() : 0;
    }

    public Instant getChangedTime() {
        return changedTime;
    }

    public void setChangedTime(Instant changedTime) {
        this.changedTime = changedTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "changedTime=" + changedTime +
                '}';
    }
}
