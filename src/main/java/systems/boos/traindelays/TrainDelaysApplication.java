package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainDelaysApplication implements CommandLineRunner {

    private final CommandLineInterface commandLineInterface;

    @Autowired
    public TrainDelaysApplication(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    public static void main(String[] args) {
        SpringApplication.run(TrainDelaysApplication.class, args);
    }

    @Override
    public void run(String... args) {
        commandLineInterface.run();
    }
}
