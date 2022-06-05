package systems.boos.traindelays;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class ExpectedDepartureResponse {
    private ZonedDateTime expectedDeparture;

    public ExpectedDepartureResponse() {
    }

    public ExpectedDepartureResponse(ZonedDateTime nextDeparture) {
        this.setExpectedDeparture(nextDeparture);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    public ZonedDateTime getExpectedDeparture() {
        return expectedDeparture;
    }

    public void setExpectedDeparture(ZonedDateTime value) {
        this.expectedDeparture = value;
    }
}
