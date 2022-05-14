package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import systems.boos.traindelays.model.Timetable;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

@RestController
public class TrainDelaysController {
    private final TimetablesService timetablesService;

    @Autowired
    public TrainDelaysController(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }

    @GetMapping("/nextdeparture")
    public ExpectedDepartureResponse getNextDeparture() {
        Timetable timetable = timetablesService.fetchChanges();
        Optional<Instant> nextDeparture = timetable.findFirstDepartureAfter(Instant.now());

        return new ExpectedDepartureResponse(nextDeparture.get().atZone(ZoneOffset.UTC));

        // TODO Handle edge cases: no next departure, exception from timetables service
    }
}
