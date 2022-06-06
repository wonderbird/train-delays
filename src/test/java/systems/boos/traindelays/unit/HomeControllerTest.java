package systems.boos.traindelays.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import systems.boos.traindelays.HomeController;
import systems.boos.traindelays.TimetablesService;
import systems.boos.traindelays.common.InstantBuilder;
import systems.boos.traindelays.common.TimetableStopTestDataBuilder;
import systems.boos.traindelays.model.Timetable;

import java.util.Collections;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesRegex;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimetablesService timetablesService;

    @Test
    void someTest() throws Exception {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(Collections.singletonList(TimetableStopTestDataBuilder.stopWithDeparture(InstantBuilder.inMinutes(10))));

        when(timetablesService.fetchChanges()).thenReturn(timetable);

        MvcResult mvcResult = this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, matchesRegex(Pattern.compile(".*\\d{2}:\\d{2}.*", Pattern.DOTALL)));
    }
}
