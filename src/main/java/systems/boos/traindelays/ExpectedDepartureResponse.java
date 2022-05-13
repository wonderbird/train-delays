package systems.boos.traindelays;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class ExpectedDepartureResponse {
    private final ZonedDateTime expectedDeparture;

    public ExpectedDepartureResponse(ZonedDateTime nextDeparture) {
        this.expectedDeparture = nextDeparture;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    public ZonedDateTime getExpectedDeparture() {
        return expectedDeparture;
    }
}
