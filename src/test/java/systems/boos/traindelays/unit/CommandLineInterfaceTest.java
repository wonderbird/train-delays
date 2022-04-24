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
import systems.boos.traindelays.model.Timetable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
    void Run_NoFutureDepartureScheduled_LogsNoFutureDeparturesMessage() {
        Timetable response = mock(Timetable.class);
        when(response.findFirstDepartureAfter(any(Instant.class))).thenReturn(Optional.empty());

        TimetablesService serviceStub = mock(TimetablesService.class);
        when(serviceStub.fetchChanges()).thenReturn(response);

        // WHEN I run the command line interface
        CommandLineInterface sut = new CommandLineInterface(serviceStub);
        sut.run();

        // THEN a message about no future departures is logged
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

    private void assertSingleLogEntry(String substring, Level level) {
        List<ILoggingEvent> events = memoryAppender.search(substring, level);
        assertEquals(1, events.size(), "message should be logged once");
    }

}
