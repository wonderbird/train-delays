package systems.boos.traindelays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class TrainDelaysController {
    @GetMapping("/nextdeparture")
    public ExpectedDepartureResponse getNextDeparture() {
        return new ExpectedDepartureResponse(ZonedDateTime.now());
    }
}
