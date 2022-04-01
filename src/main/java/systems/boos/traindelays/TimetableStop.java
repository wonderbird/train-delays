package systems.boos.traindelays;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TimetableStop {

    @JacksonXmlProperty(isAttribute = true, localName = "ct")
    private String changedTime;

    public String getChangedTime() {
        return changedTime;
    }

    public void setChangedTime(String changedTime) {
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

        return changedTime != null ? changedTime.equals(that.changedTime) : that.changedTime == null;
    }

    @Override
    public int hashCode() {
        return changedTime != null ? changedTime.hashCode() : 0;
    }
}
