package systems.boos.traindelays.pact;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import systems.boos.traindelays.TimetablesService;
import systems.boos.traindelays.common.InstantBuilder;
import systems.boos.traindelays.common.TimetableStopTestDataBuilder;
import systems.boos.traindelays.model.Timetable;

import java.util.List;

import static org.mockito.Mockito.when;

@Provider("TrainDelaysService")
@PactFolder("pacts")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainDelaysProviderPactTest {
    @LocalServerPort
    private int port;

    @MockBean
    private TimetablesService timetablesService;

    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verifyPact(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("the next departure time is present")
    public void theNextDepartureTimeIsPresent() {
        Timetable timetable = new Timetable();
        timetable.setTimetableStops(List.of(TimetableStopTestDataBuilder.stopWithDeparture(InstantBuilder.inMinutes(10))));
        when(timetablesService.fetchChanges()).thenReturn(timetable);
    }

    @State("the next departure time is not present")
    public void theNextDepartureTimeIsNotPresent() {
        Timetable timetable = new Timetable();
        when(timetablesService.fetchChanges()).thenReturn(timetable);
    }
}
