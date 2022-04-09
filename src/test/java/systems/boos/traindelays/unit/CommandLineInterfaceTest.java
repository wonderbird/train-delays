package systems.boos.traindelays.unit;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.*;

class CommandLineInterfaceTest {
    private MemoryAppender memoryAppender;

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
        // GIVEN it is 7:00
        String currentTime = "07:00";
        String nowString = String.format("2022-04-09T%s:00+02:00", currentTime);
        Clock clock = Clock.fixed(Instant.parse(nowString), ZoneId.of("Europe/Berlin"));

        // AND the timetables service expects the next train to leave at 8:00
        String expectedDepartureTime = "08:00";
        String changedTimeString = String.format("2022-04-09T%s:00+02:00", expectedDepartureTime);
        Instant changedTime = Instant.parse(changedTimeString);

        Event departure = new Event();
        departure.setChangedTime(changedTime);

        TimetableStop stop = new TimetableStop();
        stop.setDepartures(List.of(departure));

        Timetable response = new Timetable();
        response.setTimetableStops(List.of(stop));

        TimetablesService serviceStub = mock(TimetablesService.class);
        when(serviceStub.fetchChanges()).thenReturn(response);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(serviceStub);
        sut.setClock(clock);
        sut.run();

        // THEN There is one log entry
        List<ILoggingEvent> events = memoryAppender.search("Next train is scheduled to leave at ", Level.INFO);
        assertEquals(1, events.size(), "number of log events");

        // AND the expected departure time is logged
        String actualDepartureTime = (String) events.get(0).getArgumentArray()[0];
        assertEquals(expectedDepartureTime, actualDepartureTime);
    }

    @Test
    void Run_SinglePastTimetableStopInResponse_DoesNotLogDepartureTime() {
        // GIVEN it is 10:00
        String currentTime = "10:00";
        String nowString = String.format("2022-04-09T%s:00+02:00", currentTime);
        Clock clock = Clock.fixed(Instant.parse(nowString), ZoneId.of("Europe/Berlin"));

        // AND the timetables service only returns a past departure at 8:00
        String expectedDepartureTime = "08:00";
        String changedTimeString = String.format("2022-04-09T%s:00+02:00", expectedDepartureTime);
        Instant changedTime = Instant.parse(changedTimeString);

        Event departure = new Event();
        departure.setChangedTime(changedTime);

        TimetableStop stop = new TimetableStop();
        stop.setDepartures(List.of(departure));

        Timetable response = new Timetable();
        response.setTimetableStops(List.of(stop));

        TimetablesService serviceStub = mock(TimetablesService.class);
        when(serviceStub.fetchChanges()).thenReturn(response);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(serviceStub);
        sut.setClock(clock);
        sut.run();

        // THEN no next departure is logged
        List<ILoggingEvent> events = memoryAppender.search("Next train is scheduled to leave at ", Level.INFO);
        assertEquals(0, events.size(), "number of scheduled departure log events");

        // AND a message about no future departures is logged
        events = memoryAppender.search("No future train departures available", Level.WARN);
        assertEquals(1, events.size(), "number of warning log events");
    }
}
