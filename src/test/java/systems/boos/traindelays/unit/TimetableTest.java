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

// For this test class, the actualDeparture optional does not need to be checked. All tests accessing
// actualDeparture.get() rely on the Optional having a value. If the Optional is empty, the test should
// fail.
@SuppressWarnings("OptionalGetWithoutIsPresent")
class TimetableTest {

    private final Instant time7am = timeToInstant("07:00");

    private static TimetableStop stopWithDeparture(Instant changedTime) {
        Event departure = new Event();
        departure.setChangedTime(changedTime);

        TimetableStop stop = new TimetableStop();
        stop.setDepartures(List.of(departure));
        return stop;
    }

    private static Instant timeToInstant(String timeString) {
        String changedTimeString = String.format("2022-04-09T%s:00+02:00", timeString);
        return Instant.parse(changedTimeString);
    }

    @Test
    void findFirstDepartureAfter_SingleTimetableStop_ReturnsDeparture() {
        Instant expectedDeparture = timeToInstant("08:00");

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(stopWithDeparture(expectedDeparture)));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(time7am);

        assertEquals(expectedDeparture, actualDeparture.get());
    }

    @Test
    void findFirstDepartureAfter_UnsortedTimetableStops_ReturnsNearestNextDeparture() {
        Instant expectedDeparture = timeToInstant("08:00");

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(
                stopWithDeparture(timeToInstant("09:30")),
                stopWithDeparture(expectedDeparture)));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(time7am);

        assertEquals(expectedDeparture, actualDeparture.get());
    }

    @Test
    void findFirstDepartureAfter_SinglePastTimetableStop_ReturnsEmptyOptional() {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(stopWithDeparture(timeToInstant("06:00"))));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(time7am);

        assertTrue(actualDeparture.isEmpty());
    }

    @Test
    void findFirstDepartureAfter_TimetableStopWithoutDepartureEvent_ReturnsEmptyOptional() {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(new TimetableStop()));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(time7am);

        assertTrue(actualDeparture.isEmpty());
    }

    @Test
    void findFirstDepartureAfter_TimetableStopWithDepartureEventWithoutChangedTime_ReturnsEmptyOptional() {
        TimetableStop timetableStop = new TimetableStop();
        timetableStop.setDepartures(List.of(new Event()));

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(timetableStop));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(time7am);

        assertTrue(actualDeparture.isEmpty());
    }
}
