package fr.train.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*

class ZoneTest {

    @ParameterizedTest
    @CsvSource(
        "Zone1, Zone1, 2.4",
        "Zone2, Zone1, 2.4",
        "Zone3, Zone1, 2.8",
        "Zone4, Zone2, 3.0",
        "Zone3, Zone4, 2.0"
    )
    fun `should compute travel cost between zones appropriately`(from: Zone, to: Zone, expected: Double) {
        Assertions.assertThat(computeTravel(from, to)).isEqualTo(expected)
    }

}