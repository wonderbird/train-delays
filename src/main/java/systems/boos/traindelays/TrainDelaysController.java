package systems.boos.traindelays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO Fix the PACT provider test
@RestController
public class TrainDelaysController {
    @GetMapping("/nextdeparture")
    public String getNextDeparture() {
        return "Next departure is in 5 minutes";
    }
}
