package systems.boos.traindelays;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;
import java.util.Objects;

public class TimetableStop {

    @JacksonXmlProperty(isAttribute = true, localName = "ct")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyMMddHHmm", timezone = "Europe/Berlin")
    private Date changedTime;

    public Date getChangedTime() {
        return changedTime;
    }

    public void setChangedTime(Date changedTime) {
        this.changedTime = changedTime;
    }

    @Override
    public String toString() {
        return "TimetableStop{" +
                "changedTime='" + changedTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimetableStop that = (TimetableStop) o;

        return Objects.equals(changedTime, that.changedTime);
    }

    @Override
    public int hashCode() {
        return changedTime != null ? changedTime.hashCode() : 0;
    }
}
