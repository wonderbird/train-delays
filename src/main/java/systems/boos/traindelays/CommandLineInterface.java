package systems.boos.traindelays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import systems.boos.traindelays.model.Timetable;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineInterface {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineInterface.class);

    private final TimetablesService timetablesService;
    private static final ZoneId berlin = ZoneId.of("Europe/Berlin");
    private static final DateTimeFormatter hourMinute = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    public CommandLineInterface(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }

    // TODO test boundary conditions: service exceptions, no timetable stop / departure etc.
    public void run() {
        Timetable timetable = timetablesService.fetchChanges();
        String changedTimeString = timetable.getTimetableStops().get(0).getDepartures().get(0).getChangedTime().atZone(berlin).format(hourMinute);
        logger.info("Next train is scheduled to leave at {}", changedTimeString);
    }
}
