package systems.boos.traindelays.unit;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;
import systems.boos.traindelays.HomeController;
import systems.boos.traindelays.TimetablesService;
import systems.boos.traindelays.common.InstantBuilder;
import systems.boos.traindelays.common.TimetableStopTestDataBuilder;
import systems.boos.traindelays.model.Timetable;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesRegex;
import static org.mockito.Mockito.when;

@WebMvcTest(HomeController.class)
class HomeControllerTest {
    @MockBean
    private TimetablesService timetablesService;

    private WebClient webClient;

    @BeforeEach
    void setup(WebApplicationContext context) {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context)
                .build();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
    }

    @Test
    void homePage_whenDepartureIsPresent() throws Exception {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(Collections.singletonList(TimetableStopTestDataBuilder.stopWithDeparture(InstantBuilder.inMinutes(10))));

        when(timetablesService.fetchChanges()).thenReturn(timetable);

        HtmlPage page = webClient.getPage("http://localhost/");

        DomElement departureElement = page.getElementById("departure");
        String departure = departureElement.getTextContent();

        assertThat(departure, matchesRegex("\\d{2}:\\d{2}"));
    }
}
