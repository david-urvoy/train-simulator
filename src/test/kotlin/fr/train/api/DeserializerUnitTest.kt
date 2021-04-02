package fr.train.api

import fr.train.domain.Customer
import fr.train.domain.Station.*
import fr.train.domain.Travel
import net.joshka.junit.json.params.JsonFileSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import javax.json.JsonObject

class DeserializerUnitTest {

    @ParameterizedTest
    @JsonFileSource(resources = ["/input/BasicTestCase.json"])
    fun `should convert TravelInputs to domain Customers appropriately`(input: JsonObject) {
        // GIVEN
        val expected: List<Customer> = listOf(
            Customer(
                1, listOf(
                    Travel(G to A, 897),
                    Travel(C to F, 7849)
                )
            ),
            Customer(2, listOf(Travel(H to I, 9873)))
        )

        // WHEN
        val customers = parseJson(input.toString())

        // THEN
        assertThat(customers)
            .isEqualTo(expected)
            .usingRecursiveFieldByFieldElementComparator()
    }

}