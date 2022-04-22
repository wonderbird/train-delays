package systems.boos.traindelays.unit;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClientException;
import systems.boos.traindelays.CommandLineInterface;
import systems.boos.traindelays.TimetablesService;
import systems.boos.traindelays.common.MemoryAppender;
import systems.boos.traindelays.model.Event;
import systems.boos.traindelays.model.Timetable;
import systems.boos.traindelays.model.TimetableStop;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommandLineInterfaceTest {
    private MemoryAppender memoryAppender;
    private final String scheduleMessagePrefix = "Next train is scheduled to leave at ";


    @BeforeEach
    public void setupLogger() {
        memoryAppender = MemoryAppender.startMemoryAppender();
    }

    @AfterEach
    public void resetAndStopLogger() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    @Test
    void Run_SingleTimetableStopInResponse_LogsCorrespondingDepartureTime() {
        Clock clock = givenItIs("07:00");
        String expectedDeparture = "08:00";

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(stopWithDeparture(expectedDeparture)));

        TimetablesService service = mock(TimetablesService.class);
        when(service.fetchChanges()).thenReturn(timetable);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(service);
        sut.setClock(clock);
        sut.run();

        // THEN There is one log entry with the expected departure time
        assertLogEntryWithDeparture(expectedDeparture);
    }

    @Test
    void Run_UnsortedTimetableStopsInResponse_LogsNearestNextDepartureTime() {
        Clock clock = givenItIs("07:00");
        String expectedDeparture = "08:00";

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(
                stopWithDeparture("09:30"),
                stopWithDeparture(expectedDeparture)));

        TimetablesService service = mock(TimetablesService.class);
        when(service.fetchChanges()).thenReturn(timetable);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(service);
        sut.setClock(clock);
        sut.run();

        // THEN There is one log entry with the expected departure time
        assertLogEntryWithDeparture(expectedDeparture);
    }

    // SonarLint complains about a missing assert statement.
    // However, this test is intended to ensure that no exception is thrown.
    // Thus, an assert statement is not required.
    @SuppressWarnings("java:S2699")
    @Test
    void Run_TimetableStopWithoutDepartureEventInResponse_DoesNotThrow() {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(new TimetableStop()));

        TimetablesService service = mock(TimetablesService.class);
        when(service.fetchChanges()).thenReturn(timetable);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(service);
        sut.run();

        // THEN No exception is thrown
    }

    // SonarLint complains about a missing assert statement.
    // However, this test is intended to ensure that no exception is thrown.
    // Thus, an assert statement is not required.
    @SuppressWarnings("java:S2699")
    @Test
    void Run_TimetableStopWithDepartureEventWithoutChangedTimeInResponse_DoesNotThrow() {
        TimetableStop timetableStop = new TimetableStop();
        timetableStop.setDepartures(List.of(new Event()));

        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(timetableStop));

        TimetablesService service = mock(TimetablesService.class);
        when(service.fetchChanges()).thenReturn(timetable);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(service);
        sut.run();

        // THEN No exception is thrown
    }

    @Test
    void Run_SinglePastTimetableStopInResponse_DoesNotLogDepartureTime() {
        Clock clock = givenItIs("10:00");
        String expectedDeparture = "08:00";

        Timetable response = new Timetable();
        response.setTimetableStops(List.of(stopWithDeparture(expectedDeparture)));

        TimetablesService serviceStub = mock(TimetablesService.class);
        when(serviceStub.fetchChanges()).thenReturn(response);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(serviceStub);
        sut.setClock(clock);
        sut.run();

        // THEN no next departure is logged
        assertNoLogEntry(scheduleMessagePrefix, Level.INFO);

        // AND a message about no future departures is logged
        assertSingleLogEntry("No future train departures available", Level.WARN);
    }

    @Test
    void Run_TimeTableServiceThrowsException_ReportsServerSideError() {
        TimetablesService service = mock(TimetablesService.class);
        when(service.fetchChanges()).thenThrow(new RestClientException("Test exception"));

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(service);
        sut.run();

        // THEN a message about a server side error is logged
        assertSingleLogEntry("The Deutsche Bahn OpenAPI Portal reported an error", Level.ERROR);
    }

    private Clock givenItIs(String time) {
        String nowString = String.format("2022-04-09T%s:00+02:00", time);
        return Clock.fixed(Instant.parse(nowString), ZoneId.of("Europe/Berlin"));
    }

    private TimetableStop stopWithDeparture(String expectedDepartureTime) {
        String changedTimeString = String.format("2022-04-09T%s:00+02:00", expectedDepartureTime);
        Instant changedTime = Instant.parse(changedTimeString);

        Event departure = new Event();
        departure.setChangedTime(changedTime);

        TimetableStop stop = new TimetableStop();
        stop.setDepartures(List.of(departure));
        return stop;
    }

    private void assertLogEntryWithDeparture(String expectedDepartureTime) {
        List<ILoggingEvent> events = memoryAppender.search(scheduleMessagePrefix, Level.INFO);
        assertEquals(1, events.size(), "departure time should be logged once");

        // AND the expected departure time is logged
        String actualDepartureTime = (String) events.get(0).getArgumentArray()[0];
        assertEquals(expectedDepartureTime, actualDepartureTime);
    }

    private void assertNumberOfLogEntries(String substring, Level level, int expectedOccurrences, String message) {
        List<ILoggingEvent> events = memoryAppender.search(substring, level);
        assertEquals(expectedOccurrences, events.size(), message);
    }

    private void assertNoLogEntry(String substring, Level level) {
        assertNumberOfLogEntries(substring, level, 0, "message should not be logged");
    }

    private void assertSingleLogEntry(String substring, Level level) {
        assertNumberOfLogEntries(substring, level, 1, "message should be logged once");
    }
}
