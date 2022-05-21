package systems.boos.traindelays.common;

import systems.boos.traindelays.model.Event;
import systems.boos.traindelays.model.TimetableStop;

import java.time.Instant;
import java.util.List;

public class TimetableStopTestDataBuilder {
    public static TimetableStop stopWithDeparture(Instant changedTime) {
        Event departure = new Event();
        departure.setChangedTime(changedTime);

        TimetableStop stop = new TimetableStop();
        stop.setDepartures(List.of(departure));
        return stop;
    }
}
