package systems.boos.traindelays.common;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Recorded timetable API responses.
 */
public class TimetableApiResponses {
    /**
     * Customize the departure time of a recorded response.
     * <p>
     * The encoded date will be the current day.
     *
     * @param expectedDeparture expected departure as Instant
     * @return API response body in XML format.
     */
    public static String createResponseWithExpectedDeparture(Instant expectedDeparture) {
        String formattedDepartureTime = expectedDeparture
                .atZone(ZoneId.of("Europe/Berlin"))
                .format(DateTimeFormatter.ofPattern("yyMMddHHmm"));

        return String.format("""
                        <timetable station="R&#246;srath-St&#252;mpen" eva="8005143">

                            <s id="9185143715788257673-2204022151-6" eva="8005143">
                                <dp ct="%s" l="25"/>
                            </s>
                        </timetable>""",
                formattedDepartureTime);
    }
}
