package systems.boos.traindelays.common;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Recorded timetable API responses.
 *
 * @see <a href="../../../../../docs/timetables.http">docs/timetables.http</a> - configuration for the JetBrains
 * HTTP Client utility.
 */
public class TimetableApiResponses {
    @NotNull
    private static String formatTimeForApi(Instant instant) {
        return instant
                .atZone(ZoneId.of("Europe/Berlin"))
                .format(DateTimeFormatter.ofPattern("yyMMddHHmm"));
    }

    /**
     * Customize the expected departure time of a recorded response.
     * <p>
     * The encoded date will be the current day.
     *
     * @param expectedDeparture expected departure as Instant
     * @return API response body in XML format.
     */
    public static String createResponseWithExpectedDeparture(Instant expectedDeparture) {
        return String.format("""
                        <timetable station="R&#246;srath-St&#252;mpen" eva="8005143">

                            <s id="9185143715788257673-2204022151-6" eva="8005143">
                                <dp ct="%s" l="25"/>
                            </s>
                        </timetable>""",
                formatTimeForApi(expectedDeparture));
    }

    /**
     * Customize the planned departure time of a recorded response.
     * <p>
     * The encoded date will be the current day.
     *
     * @param plannedDeparture planned departure as Instant
     * @return API response body in XML format.
     */
    public static String createResponseWithPlannedDeparture(Instant plannedDeparture) {
        return String.format("""
                        <?xml version='1.0' encoding='UTF-8'?>
                        <timetable station='R&#246;srath-St&#252;mpen'>
                            <s id="9185143715788257673-2204022151-6">
                                <dp pt="%s" pp="1" l="25"
                                    ppth="K&#246;ln Frankfurter Stra&#223;e|K&#246;ln Trimbornstr|K&#246;ln Messe/Deutz|K&#246;ln Hbf|K&#246;ln Hansaring"/>
                            </s>
                        </timetable>""",
                formatTimeForApi(plannedDeparture));
    }
}
