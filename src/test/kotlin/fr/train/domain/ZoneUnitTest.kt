package fr.train.domain

import fr.train.domain.Zone.Zone1
import fr.train.domain.Zone.Zone2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ZoneUnitTest {

    @ParameterizedTest
    @CsvSource(
        "Zone1, Zone1, 240",
        "Zone2, Zone1, 240",
        "Zone3, Zone1, 280",
        "Zone4, Zone2, 300",
        "Zone3, Zone4, 200",
        "Zone1, Zone4, 300",
        "Zone2, Zone3, 280"
    )
    fun `should compute travel cost between zones appropriately`(from: Zone, to: Zone, expected: Int) {
        assertThat((from to to).travelPrice()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "Zone3, Zone1, Zone2, Zone1"
    )
    fun `orIfCheaper should find cheapest Travel`(from1: Zone, to1: Zone, from2: Zone, to2: Zone) {
        assertThat((from1 to to1) orIfCheaper (from2 to to2)).isEqualTo(Zone2 to Zone1)
    }

}