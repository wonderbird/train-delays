package systems.boos.traindelays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommandLineInterface {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineInterface.class);

    public void run() {
        logger.info("Next train is scheduled to leave at {}", "00:00");
    }
}
