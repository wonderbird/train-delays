package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import systems.boos.traindelays.model.Timetable;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class HomeController {
    private final TimetablesService timetablesService;

    @Autowired
    public HomeController(TimetablesService timetablesService) {
        this.timetablesService = timetablesService;
    }

    @GetMapping("/")
    public String nextDeparture(Model model) {

        Timetable timetable = timetablesService.fetchChanges();
        Optional<Instant> nextDeparture = timetable.findFirstDepartureAfter(Instant.now());

        String departure = "es liegen keine Daten vor";
        if (nextDeparture.isPresent()) {
            departure = nextDeparture.get().atZone(ZoneId.of("Europe/Berlin")).format(DateTimeFormatter.ofPattern("HH:mm"));
        }

        model.addAttribute("departure", departure);
        return "nextDeparture";
    }
}
