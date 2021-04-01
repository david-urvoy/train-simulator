package fr.train.domain

import com.google.gson.Gson
import fr.train.api.TravelInput
import fr.train.api.toCustomers
import fr.train.domain.Zone.Zone1
import fr.train.domain.Zone.Zone2
import fr.train.spi.computeTravels
import net.joshka.junit.json.params.JsonFileSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.CsvSource
import javax.json.JsonObject
import javax.json.JsonString

class ZoneTest {

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

    @ParameterizedTest
    @CsvSource(
        "Zone3, Zone1, Zone2, Zone1"
    )
    fun `orIfCheaper should find cheapest Travel`(from1: Zone, to1: Zone, from2: Zone, to2: Zone) {
        assertThat((from1 to to1) orIfCheaper (from2 to to2)).isEqualTo(Zone2 to Zone1)
    }

    @ParameterizedTest
    @JsonFileSource(resources = ["/CandidateInputExample.json"])
    fun read(input: JsonObject) {
        Gson().fromJson(input.toString(), TravelInput::class.java)
            .let { Gson().toJson(it.toCustomers().map { customer -> customer.computeTravels() }) }
            .also { print(it) }
    }

}