package fr.train.domain

import fr.train.domain.Station.*
import fr.train.domain.Zone.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StationUnitTest {

    @ParameterizedTest
    @MethodSource("stationsInput")
    fun `should return the Station's corresponding Zones`(station: Station, zones: List<Zone>) {
        assertThat(station.zones())
            .isEqualTo(zones)
            .usingRecursiveFieldByFieldElementComparator()
    }

    fun stationsInput(): Stream<Arguments> =
        Stream.of(
            Arguments.of(A, listOf(Zone1)),
            Arguments.of(B, listOf(Zone1)),
            Arguments.of(C, listOf(Zone2, Zone3)),
            Arguments.of(D, listOf(Zone2)),
            Arguments.of(E, listOf(Zone2, Zone3)),
            Arguments.of(F, listOf(Zone3, Zone4)),
            Arguments.of(G, listOf(Zone4)),
            Arguments.of(H, listOf(Zone4)),
            Arguments.of(I, listOf(Zone4)),
        )
}
