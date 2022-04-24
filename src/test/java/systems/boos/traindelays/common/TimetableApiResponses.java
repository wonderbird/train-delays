package systems.boos.traindelays.common;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
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
     * @param expectedDepartureTime Time of expected departure as string, e.g. "10:00" or "15:42".
     * @param clock                 Clock to use for the current time. This clock can either be
     *                              Clock.systemDefaultZone() or a preconfigured clock for test purpose.
     * @return API response body in XML format.
     */
    public static String createResponseWithDepartureTime(String expectedDepartureTime, Clock clock) {
        String formattedDepartureTime = Instant.now(clock)
                .atZone(ZoneId.of("Europe/Berlin"))
                .with(LocalTime.parse(expectedDepartureTime))
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
