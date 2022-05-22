package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import systems.boos.traindelays.model.Timetable;

import javax.servlet.http.HttpServletResponse;
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
    public ExpectedDepartureResponse getNextDeparture(HttpServletResponse response) {
        Timetable timetable = timetablesService.fetchChanges();
        Optional<Instant> nextDeparture = timetable.findFirstDepartureAfter(Instant.now());

        ExpectedDepartureResponse result;
        if (nextDeparture.isPresent()) {
            result = new ExpectedDepartureResponse(nextDeparture.get().atZone(ZoneOffset.UTC));
        } else {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            result = new ExpectedDepartureResponse(null);
        }

        return result;

        // TODO Handle edge cases: exception from timetables service (e.g. status 500, not authorized because wrong api key)
    }
}
