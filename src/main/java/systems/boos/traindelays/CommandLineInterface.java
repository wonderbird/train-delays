package systems.boos.traindelays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import systems.boos.traindelays.model.Timetable;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class CommandLineInterface {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineInterface.class);
    private static final ZoneId berlin = ZoneId.of("Europe/Berlin");
    private static final DateTimeFormatter hourMinute = DateTimeFormatter.ofPattern("HH:mm");
    private final TimetablesService timetablesService;
    private Clock clock = Clock.systemDefaultZone();

    @Autowired
    public CommandLineInterface(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }

    public void run() {
        try {
            Timetable timetable = timetablesService.fetchChanges();
            Optional<Instant> nextDeparture = timetable.findFirstDepartureAfter(Instant.now(clock));
            logNextDeparture(nextDeparture);
        } catch (RestClientException e) {
            logger.error("The Deutsche Bahn OpenAPI Portal reported an error: {}", e.getMessage());
        }
    }

    private void logNextDeparture(Optional<Instant> nextDeparture) {
        if (nextDeparture.isPresent()) {
            String changedTimeString = nextDeparture.get().atZone(berlin).format(hourMinute);
            logger.info("Next train is scheduled to leave at {}", changedTimeString);
        } else {
            logger.warn("No future train departures available");
        }
    }

    /**
     * Allow testing this class by setting a fake clock, so that Instant.now(clock) returns a preconfigured time.
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
