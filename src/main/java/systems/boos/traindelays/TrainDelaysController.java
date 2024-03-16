package systems.boos.traindelays;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import systems.boos.traindelays.model.Timetable;

import jakarta.servlet.http.HttpServletResponse;
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

    @Operation(summary = "Time of next train leaving Rösrath Stümpen", description = "Get the time when the next train leaves the station Rösrath-Stümpen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time of next departure"),
            @ApiResponse(responseCode = "204", description = "No trains received from DB Timetables API"),
    })
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
    }
}
