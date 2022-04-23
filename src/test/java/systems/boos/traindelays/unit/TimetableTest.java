package systems.boos.traindelays.unit;

import org.junit.jupiter.api.Test;
import systems.boos.traindelays.model.Event;
import systems.boos.traindelays.model.Timetable;
import systems.boos.traindelays.model.TimetableStop;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimetableTest {
    @Test
    void findFirstDepartureAfter_SingleTimetableStop_ReturnsDeparture() {
        Instant expectedDeparture = timeToInstant("08:00");

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(stopWithDeparture(expectedDeparture)));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(timeToInstant("07:00"));

        assertEquals(expectedDeparture, actualDeparture.get());
    }

    @Test
    void findFirstDepartureAfter_UnsortedTimetableStops_ReturnsNearestNextDeparture() {
        Instant expectedDeparture = timeToInstant("08:00");

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(
                stopWithDeparture(timeToInstant("09:30")),
                stopWithDeparture(expectedDeparture)));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(timeToInstant("07:00"));

        assertEquals(expectedDeparture, actualDeparture.get());
    }

    @Test
    void findFirstDepartureAfter_SinglePastTimetableStop_ReturnsEmptyOptional() {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(stopWithDeparture(timeToInstant("06:00"))));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(timeToInstant("07:00"));

        assertTrue(actualDeparture.isEmpty());
    }

    @Test
    void findFirstDepartureAfter_TimetableStopWithoutDepartureEvent_ReturnsEmptyOptional() {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(new TimetableStop()));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(timeToInstant("07:00"));

        assertTrue(actualDeparture.isEmpty());
    }

    @Test
    void findFirstDepartureAfter_TimetableStopWithDepartureEventWithoutChangedTime_ReturnsEmptyOptional() {
        TimetableStop timetableStop = new TimetableStop();
        timetableStop.setDepartures(List.of(new Event()));

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(timetableStop));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(timeToInstant("07:00"));

        assertTrue(actualDeparture.isEmpty());
    }

    private TimetableStop stopWithDeparture(Instant changedTime) {

        Event departure = new Event();
        departure.setChangedTime(changedTime);

        TimetableStop stop = new TimetableStop();
        stop.setDepartures(List.of(departure));
        return stop;
    }

    private Instant timeToInstant(String timeString) {
        String changedTimeString = String.format("2022-04-09T%s:00+02:00", timeString);
        return Instant.parse(changedTimeString);
    }
}
