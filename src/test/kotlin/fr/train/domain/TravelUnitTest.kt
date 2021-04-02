package fr.train.domain

import fr.train.domain.Station.*
import fr.train.domain.Zone.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TravelUnitTest {

    @ParameterizedTest
    @CsvSource(
        "A, B, 240",
        "D, E, 240",
        "A, C, 240",
        "B, I, 300",
        "H, D, 300",
        "C, G, 200",
        "C, F, 200",
        "E, A, 240"
    )
    fun `should compute travel cost between stations appropriately`(from: Station, to: Station, expected: Int) {
        assertThat(Travel(from to to, 0).travelPrice()).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("stationsZonesMapping")
    fun `should compute closest zones appropriately`(stations: Pair<Station, Station>, zones: Pair<Zone, Zone>) {
        assertThat(Travel(stations, 0).closestZones())
            .isEqualTo(zones)
    }

    fun stationsZonesMapping(): Stream<Arguments> =
        Stream.of(
            Arguments.of(A to B, Zone1 to Zone1),
            Arguments.of(D to E, Zone2 to Zone2),
            Arguments.of(A to C, Zone1 to Zone2),
            Arguments.of(B to I, Zone1 to Zone4),
            Arguments.of(H to D, Zone4 to Zone2),
            Arguments.of(C to G, Zone3 to Zone4),
            Arguments.of(C to F, Zone3 to Zone3),
        )

}