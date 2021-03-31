package fr.train.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
        assertThat((from to to).travelPrice()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "A, B, 2.4",
        "D, E, 2.4",
        "A, C, 2.4",
        "B, I, 3.0",
        "H, D, 3.0",
        "C, G, 2.0",
        "C, F, 2.0"
    )
    fun `should compute travel cost between stations appropriately`(from: Station, to: Station, expected: Double) {
        assertThat((from to to).travelPrice()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "A, B",
        "D, E",
        "A, C",
        "B, I",
        "H, D",
        "C, G",
        "C, F"
    )
    fun `should compute closest zones appropriately`(from: Station, to: Station) {
        println((from to to).closestZones())
    }

}