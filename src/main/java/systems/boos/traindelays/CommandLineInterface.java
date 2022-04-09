package systems.boos.traindelays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import systems.boos.traindelays.model.Timetable;
import systems.boos.traindelays.model.TimetableStop;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    // TODO test boundary conditions: service exceptions, no timetable stop / departure etc.
    public void run() {
        Timetable timetable = timetablesService.fetchChanges();
        Instant now = Instant.now(clock);
        Optional<TimetableStop> first = timetable.getTimetableStops().stream().filter(stop -> stop.getDepartures().get(0).getChangedTime().isAfter(now)).findFirst();
        if (first.isPresent()) {
            String changedTimeString = first.get().getDepartures().get(0).getChangedTime().atZone(berlin).format(hourMinute);
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
