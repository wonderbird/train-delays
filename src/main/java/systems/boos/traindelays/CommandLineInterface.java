package systems.boos.traindelays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import systems.boos.traindelays.model.Timetable;
import systems.boos.traindelays.model.TimetableStop;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

@Component
public class CommandLineInterface {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineInterface.class);
    private final TimetablesService timetablesService;
    private static final ZoneId berlin = ZoneId.of("Europe/Berlin");
    private static final DateTimeFormatter hourMinute = DateTimeFormatter.ofPattern("HH:mm");
    private Clock clock = Clock.systemDefaultZone();

    @Autowired
    public CommandLineInterface(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }

    // TODO test boundary conditions: no timetable stop / departure etc., (done) service exceptions
    // TODO simplify CommandLineInterface.Run()
    public void run() {
        try {
            Timetable timetable = timetablesService.fetchChanges();
            Instant now = Instant.now(clock);
            Optional<TimetableStop> first = timetable.getTimetableStops().stream()
                    .filter(stop -> !stop.getDepartures().isEmpty() && stop.getDepartures().get(0).getChangedTime() != null)
                    .sorted(Comparator.comparing(s -> s.getDepartures().get(0).getChangedTime()))
                    .filter(stop -> stop.getDepartures().get(0).getChangedTime().isAfter(now))
                    .findFirst();
            if (first.isPresent()) {
                String changedTimeString = first.get().getDepartures().get(0).getChangedTime().atZone(berlin).format(hourMinute);
                logger.info("Next train is scheduled to leave at {}", changedTimeString);
            } else {
                logger.warn("No future train departures available");
            }
        } catch (RestClientException e) {
            logger.error("The Deutsche Bahn OpenAPI Portal reported an error: {}", e.getMessage());
        }
    }

    /**
     * Allow testing this class by setting a fake clock, so that Instant.now(clock) returns a preconfigured time.
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
