package systems.boos.traindelays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineInterface {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineInterface.class);

    private final TimetablesService timetablesService;

    @Autowired
    public CommandLineInterface(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }

    public void run() {
        // TODO Timetable timetable = timetablesService.fetchChanges();
        logger.info("Next train is scheduled to leave at {}", "00:00");
    }
}
