package systems.boos.traindelays.common;

import java.time.Instant;

public class InstantBuilder {
    public static Instant atTime(String timeString) {
        String changedTimeString = String.format("2022-04-09T%s:00+02:00", timeString);
        return Instant.parse(changedTimeString);
    }

    public static Instant inMinutes(long minutes) {
        return Instant.now().plusSeconds(minutes * 60);
    }
}
