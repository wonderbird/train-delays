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

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        // GIVEN the timetables service returns a stop at the expected departure time
        String expectedDepartureTime = "08:00";
        String changedTimeString = String.format("2020-01-01T%s:00+01:00", expectedDepartureTime);
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
        sut.run();

        // THEN the expected departure time is logged
        List<ILoggingEvent> events = memoryAppender.search("Next train is scheduled to leave at ", Level.INFO);
        assertEquals(1, events.size(), "number of log events");

        String actualDepartureTime = (String) events.get(0).getArgumentArray()[0];
        assertEquals(expectedDepartureTime, actualDepartureTime);
    }
}
