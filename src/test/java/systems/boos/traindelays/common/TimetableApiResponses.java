package systems.boos.traindelays.common;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimetableApiResponses {
    public static String createResponseWithDepartureTime(String expectedDepartureTime) {
        String formattedDepartureTime = Instant.now()
                .atZone(ZoneId.of("Europe/Berlin"))
                .with(LocalTime.parse(expectedDepartureTime))
                .format(DateTimeFormatter.ofPattern("yyMMddHHmm"));

        return String.format("""
                        <timetable>
                        <s>
                            <dp ct="%s" />
                        </s>
                        </timetable>""",
                formattedDepartureTime);
    }

    public static String getRecordedResponse() {
        return """
                <timetable station="R&#246;srath-St&#252;mpen" eva="8005143">

                    <s id="9185143715788257673-2204022151-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022210" l="25"/>
                        <dp ct="2204022211" l="25"/>
                    </s>


                    <s id="2186500656991364452-2204022116-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022148" l="25"/>
                        <dp ct="2204022150" l="25"/>
                    </s>


                    <s id="216861492552484918-2204022221-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022239" l="25"/>
                        <dp ct="2204022241" l="25"/>
                    </s>


                    <s id="-2415626382182141277-2204022216-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022249" l="25"/>
                        <dp ct="2204022250" l="25"/>
                    </s>


                    <s id="8380950407676847468-2204022032-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204022219" l="25">
                            <m id="r115410912" t="d" c="31" ts="2204021423" ts-tts="22-04-02 14:23:54.550"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204022220"
                            l="25">
                            <m id="r115410912" t="d" c="31" ts="2204021423" ts-tts="22-04-02 14:23:54.550"/>
                        </dp>
                    </s>


                    <s id="-6448465059972773378-2204022121-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022140" l="25"/>
                        <dp ct="2204022141" l="25"/>
                    </s>


                    <s id="-7344704538977750495-2204021932-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204022119" l="25">
                            <m id="r115410732" t="d" c="31" ts="2204021420" ts-tts="22-04-02 14:20:57.209"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204022120"
                            l="25">
                            <m id="r115410732" t="d" c="31" ts="2204021420" ts-tts="22-04-02 14:20:57.209"/>
                        </dp>
                    </s>


                    <s id="7102065794800823212-2204022051-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022110" l="25"/>
                        <dp ct="2204022111" l="25"/>
                    </s>


                    <s id="-4944743454732591340-2204022132-15" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204022319" l="25">
                            <m id="r115411107" t="d" c="31" ts="2204021426" ts-tts="22-04-02 14:26:03.489"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204022320"
                            l="25">
                            <m id="r115411107" t="d" c="31" ts="2204021426" ts-tts="22-04-02 14:26:03.490"/>
                        </dp>
                    </s>


                    <s id="-1321709297234859073-2204022016-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022049" l="25"/>
                        <dp ct="2204022050" l="25"/>
                    </s>


                    <s id="1439073147770674305-2204022021-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar ct="2204022040" l="25"/>
                        <dp ct="2204022042" l="25"/>
                    </s>


                    <s id="-1225211087215167015-2204021832-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204022018" l="25">
                            <m id="r115410372" t="d" c="31" ts="2204021414" ts-tts="22-04-02 14:14:28.345"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204022020"
                            l="25">
                            <m id="r115410372" t="d" c="31" ts="2204021414" ts-tts="22-04-02 14:14:28.346"/>
                        </dp>
                    </s>


                    <s id="1403393502957538927-2204030712-10" eva="8005143">
                        <ar l="25">
                            <m id="r115432263" t="f" c="0" ts="2204022024" ts-tts="22-04-02 20:24:31.245"/>
                        </ar>
                        <dp l="25">
                            <m id="r115432263" t="f" c="0" ts="2204022024" ts-tts="22-04-02 20:24:31.245"/>
                        </dp>
                    </s>


                    <s id="147536685485031123-2204021951-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <ar ct="2204022011" l="25"/>
                        <dp ct="2204022012" l="25"/>
                    </s>


                    <s id="-4556666340603435220-2204021916-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021950" l="25"/>
                        <dp ct="2204021951" l="25"/>
                    </s>


                    <s id="841117275488556088-2204021921-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Köln Hansaring|Köln Hbf|Köln Messe/Deutz|Köln Trimbornstr|Köln Frankfurter Straße" ct="2204021944"
                            l="25">
                            <m id="r115411105" t="d" c="31" ts="2204021426" ts-tts="22-04-02 14:26:03.489"/>
                            <m id="r115411143" t="d" c="31" ts="2204021426" ts-tts="22-04-02 14:26:35.701"/>
                        </ar>
                        <dp cpth="Rösrath|Hoffnungsthal|Honrath|Overath|Engelskirchen|Ründeroth|Gummersbach-Dieringhausen|Gummersbach|Marienheide|Meinerzhagen|Kierspe"
                            ct="2204021945" l="25">
                            <m id="r115411105" t="d" c="31" ts="2204021426" ts-tts="22-04-02 14:26:03.489"/>
                            <m id="r115411143" t="d" c="31" ts="2204021426" ts-tts="22-04-02 14:26:35.701"/>
                        </dp>
                    </s>


                    <s id="6133467750500129950-2204021732-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204021918" l="25">
                            <m id="r115410204" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:12:02.646"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204021920"
                            l="25">
                            <m id="r115410204" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:12:02.646"/>
                        </dp>
                    </s>


                    <s id="6694822737896087912-2204021851-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021911" l="25"/>
                        <dp ct="2204021913" l="25"/>
                    </s>


                    <s id="1953652307689083787-2204021816-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021849" l="25"/>
                        <dp ct="2204021850" l="25"/>
                    </s>


                    <s id="-6571587096714908809-2204021821-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Köln Hansaring|Köln Hbf|Köln Messe/Deutz|Köln Trimbornstr|Köln Frankfurter Straße" ct="2204021842"
                            l="25">
                            <m id="r115410914" t="d" c="31" ts="2204021423" ts-tts="22-04-02 14:23:54.550"/>
                            <m id="r115410990" t="d" c="31" ts="2204021424" ts-tts="22-04-02 14:24:42.888"/>
                        </ar>
                        <dp cpth="Rösrath|Hoffnungsthal|Honrath|Overath|Engelskirchen|Ründeroth|Gummersbach-Dieringhausen|Gummersbach|Marienheide|Meinerzhagen|Kierspe"
                            ct="2204021843" l="25">
                            <m id="r115410914" t="d" c="31" ts="2204021423" ts-tts="22-04-02 14:23:54.550"/>
                            <m id="r115410990" t="d" c="31" ts="2204021424" ts-tts="22-04-02 14:24:42.888"/>
                        </dp>
                    </s>


                    <s id="-6844414881880716293-2204021632-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204021823" l="25">
                            <m id="r115410146" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:11:30.321"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204021823"
                            l="25">
                            <m id="r115410146" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:11:30.321"/>
                        </dp>
                    </s>


                    <s id="-4393861739114930450-2204022232-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                    </s>


                    <s id="1267426608471442826-2204021721-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Köln Hansaring|Köln Hbf|Köln Messe/Deutz|Köln Trimbornstr|Köln Frankfurter Straße" ct="2204021742"
                            l="25">
                            <m id="r115410730" t="d" c="31" ts="2204021420" ts-tts="22-04-02 14:20:57.209"/>
                            <m id="r115410785" t="d" c="31" ts="2204021421" ts-tts="22-04-02 14:21:29.662"/>
                        </ar>
                        <dp cpth="Rösrath|Hoffnungsthal|Honrath|Overath|Engelskirchen|Ründeroth|Gummersbach-Dieringhausen|Gummersbach|Marienheide|Meinerzhagen|Kierspe"
                            ct="2204021743" l="25">
                            <m id="r115410730" t="d" c="31" ts="2204021420" ts-tts="22-04-02 14:20:57.209"/>
                            <m id="r115410785" t="d" c="31" ts="2204021421" ts-tts="22-04-02 14:21:29.662"/>
                        </dp>
                    </s>


                    <s id="-5386223490707047037-2204021621-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Köln Hansaring|Köln Hbf|Köln Messe/Deutz|Köln Trimbornstr|Köln Frankfurter Straße" ct="2204021642"
                            l="25">
                            <m id="r115410370" t="d" c="31" ts="2204021414" ts-tts="22-04-02 14:14:28.345"/>
                            <m id="r115410467" t="d" c="31" ts="2204021415" ts-tts="22-04-02 14:15:49.792"/>
                        </ar>
                        <dp cpth="Rösrath|Hoffnungsthal|Honrath|Overath|Engelskirchen|Ründeroth|Gummersbach-Dieringhausen|Gummersbach|Marienheide|Meinerzhagen|Kierspe"
                            ct="2204021644" l="25">
                            <m id="r115410370" t="d" c="31" ts="2204021414" ts-tts="22-04-02 14:14:28.345"/>
                            <m id="r115410467" t="d" c="31" ts="2204021415" ts-tts="22-04-02 14:15:49.792"/>
                        </dp>
                    </s>


                    <s id="8920033752250648768-2204021532-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204021718" l="25">
                            <m id="r115410054" t="d" c="31" ts="2204021410" ts-tts="22-04-02 14:10:09.059"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204021720"
                            l="25">
                            <m id="r115410054" t="d" c="31" ts="2204021410" ts-tts="22-04-02 14:10:09.059"/>
                        </dp>
                    </s>


                    <s id="7995181714624943176-2204021521-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Köln Hansaring|Köln Hbf|Köln Messe/Deutz|Köln Trimbornstr|Köln Frankfurter Straße" ct="2204021544"
                            l="25">
                            <m id="r115410202" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:12:02.645"/>
                            <m id="r115410453" t="d" c="31" ts="2204021415" ts-tts="22-04-02 14:15:33.574"/>
                        </ar>
                        <dp cpth="Rösrath|Hoffnungsthal|Honrath|Overath|Engelskirchen|Ründeroth|Gummersbach-Dieringhausen|Gummersbach|Marienheide|Meinerzhagen|Kierspe"
                            ct="2204021545" l="25">
                            <m id="r115410202" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:12:02.645"/>
                            <m id="r115410453" t="d" c="31" ts="2204021415" ts-tts="22-04-02 14:15:33.574"/>
                        </dp>
                    </s>


                    <s id="841291650074983123-2204021432-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204021618" l="25">
                            <m id="r115402022" t="d" c="31" ts="2204021212" ts-tts="22-04-02 12:13:00.993"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204021620"
                            l="25">
                            <m id="r115402022" t="d" c="31" ts="2204021212" ts-tts="22-04-02 12:13:00.993"/>
                        </dp>
                    </s>


                    <s id="2817164644946612514-2204021421-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Köln Hansaring|Köln Hbf|Köln Messe/Deutz|Köln Trimbornstr|Köln Frankfurter Straße" ct="2204021449"
                            l="25">
                            <m id="r115410144" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:11:30.321"/>
                            <m id="r115410408" t="d" c="31" ts="2204021415" ts-tts="22-04-02 14:15:17.356"/>
                        </ar>
                        <dp cpth="Rösrath|Hoffnungsthal|Honrath|Overath|Engelskirchen|Ründeroth|Gummersbach-Dieringhausen|Gummersbach|Marienheide|Meinerzhagen|Kierspe"
                            ct="2204021451" l="25">
                            <m id="r115410144" t="d" c="31" ts="2204021411" ts-tts="22-04-02 14:11:30.321"/>
                            <m id="r115410408" t="d" c="31" ts="2204021415" ts-tts="22-04-02 14:15:17.356"/>
                        </dp>
                    </s>


                    <s id="7570750901107321388-2204021332-15" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Kierspe|Meinerzhagen|Marienheide|Gummersbach|Gummersbach-Dieringhausen|Ründeroth|Engelskirchen|Overath|Honrath|Hoffnungsthal|Rösrath"
                            ct="2204021523" l="25">
                            <m id="r115398012" t="d" c="31" ts="2204021121" ts-tts="22-04-02 11:21:38.233"/>
                        </ar>
                        <dp cpth="Köln Frankfurter Straße|Köln Trimbornstr|Köln Messe/Deutz|Köln Hbf|Köln Hansaring" ct="2204021525"
                            l="25">
                            <m id="r115398012" t="d" c="31" ts="2204021121" ts-tts="22-04-02 11:21:38.233"/>
                        </dp>
                    </s>


                    <s id="7261896115039901626-2204021321-6" eva="8005143">
                        <m id="r99492s" t="r" from="2204021813" to="2204021816" ts="2204021813" ts-tts="22-04-02 18:13:10.093"/>
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                        <m id="r1566202" t="h" from="2204020821" to="2204040100" cat="Störung. (Quelle: zuginfo.nrw)" ts="2204021444"
                           ts-tts="22-04-02 14:44:55.011" pr="2"/>
                        <ar cpth="Köln Hansaring|Köln Hbf|Köln Messe/Deutz|Köln Trimbornstr|Köln Frankfurter Straße" ct="2204021350"
                            l="25"/>
                        <dp cpth="Rösrath|Hoffnungsthal|Honrath|Overath|Engelskirchen|Ründeroth|Gummersbach-Dieringhausen|Gummersbach|Marienheide|Meinerzhagen|Kierspe"
                            ct="2204021351" l="25"/>
                    </s>


                    <s id="8222421186570141033-2204021751-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021810" l="25"/>
                        <dp ct="2204021810" l="25"/>
                    </s>


                    <s id="-3828707685651015388-2204021716-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021748" l="25"/>
                        <dp ct="2204021749" l="25"/>
                    </s>


                    <s id="-4905036902213884996-2204021651-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021709" l="25"/>
                        <dp ct="2204021711" l="25"/>
                    </s>


                    <s id="-439668675444320022-2204021616-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021648" l="25"/>
                        <dp ct="2204021650" l="25"/>
                    </s>


                    <s id="-3372514427392107258-2204021551-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021611" l="25"/>
                        <dp ct="2204021612" l="25"/>
                    </s>


                    <s id="8007163114084429055-2204021516-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021549" l="25"/>
                        <dp ct="2204021551" l="25"/>
                    </s>


                    <s id="8878357663434397783-2204021451-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021510" l="25"/>
                        <dp ct="2204021511" l="25"/>
                    </s>


                    <s id="-5659500318539483295-2204021416-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.518" pr="3"/>
                        <ar ct="2204021455" l="25"/>
                        <dp ct="2204021456" l="25"/>
                    </s>


                    <s id="1838600143176975025-2204022351-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                    </s>


                    <s id="-2256648954726171797-2204022321-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                    </s>


                    <s id="-5568528229119202805-2204022316-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                    </s>


                    <s id="-1976239017755538201-2204022251-6" eva="8005143">
                        <m id="r1564370" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2203312312" ts-tts="22-04-01 23:16:08.838" pr="3"/>
                        <m id="r1565255" t="h" from="2204010900" to="2204152359" cat="Information. (Quelle: zuginfo.nrw)"
                           ts="2204012310" ts-tts="22-04-01 23:11:02.517" pr="3"/>
                    </s>

                </timetable>""";
    }
}
