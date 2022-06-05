package systems.boos.traindelays.unit;

import org.junit.jupiter.api.Test;
import systems.boos.traindelays.common.InstantBuilder;
import systems.boos.traindelays.common.TimetableStopTestDataBuilder;
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

    private final Instant time7am = InstantBuilder.atTime("07:00");

    @Test
    void findFirstDepartureAfter_SingleTimetableStop_ReturnsDeparture() {
        Instant expectedDeparture = InstantBuilder.atTime("08:00");

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(TimetableStopTestDataBuilder.stopWithDeparture(expectedDeparture)));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(time7am);

        assertEquals(expectedDeparture, actualDeparture.get());
    }

    @Test
    void findFirstDepartureAfter_UnsortedTimetableStops_ReturnsNearestNextDeparture() {
        Instant expectedDeparture = InstantBuilder.atTime("08:00");

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(
                TimetableStopTestDataBuilder.stopWithDeparture(InstantBuilder.atTime("09:30")),
                TimetableStopTestDataBuilder.stopWithDeparture(expectedDeparture)));

        Optional<Instant> actualDeparture = timetable.findFirstDepartureAfter(time7am);

        assertEquals(expectedDeparture, actualDeparture.get());
    }

    @Test
    void findFirstDepartureAfter_SinglePastTimetableStop_ReturnsEmptyOptional() {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(TimetableStopTestDataBuilder.stopWithDeparture(InstantBuilder.atTime("06:00"))));

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
