package fr.train.api

import fr.train.spi.computeToOverview
import net.joshka.junit.json.params.JsonFileSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import javax.json.JsonObject

class SubwayIntegrationTest {

    @ParameterizedTest
    @JsonFileSource(resources = ["/input/CandidateInputExample.json"])
    fun `should compute test case appropriately`(input: JsonObject) {
        // WHEN
        parseJson(input.toString())
            .computeToOverview()
            .also {
                // THEN
                assertThat(it).isEqualToNormalizingWhitespace((javaClass::getResource)("/output/CandidateOutputExample.json").readText())
            }
    }

    @Test
    fun `should throw explicit exception on invalid JSON`() {
        assertThrows(RuntimeException::class.java)
        { parseJson((javaClass::getResource)("/input/InvalidJson.json").readText()) }
            .also { assertThat(it.message).isEqualTo("Invalid JSON !") }
    }

    @ParameterizedTest
    @JsonFileSource(resources = ["/input/CompleteTestCase.json"])
    fun `should compute complete test case appropriately`(input: JsonObject) {
        // WHEN
        parseJson(input.toString())
            .computeToOverview()
            .also {
                // THEN
                assertThat(it).isEqualToNormalizingWhitespace((javaClass::getResource)("/output/CompleteTestCase.json").readText())
            }
    }

    @ParameterizedTest
    @JsonFileSource(resources = ["/input/LostTraveler.json"])
    fun `should throw an exception if each customer entry does not match an exit`(input: JsonObject) {
        assertThrows(RuntimeException::class.java)
        { parseJson(input.toString()) }
            .also { assertThat(it.message).isEqualTo("At least one customer seem to be lost in the subway ! (Registered a starting point but no exit...)") }
    }

}