package fr.train.spi

import fr.train.domain.Customer
import fr.train.domain.Station.*
import fr.train.domain.Travel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SerializerUnitTest {

    @Test
    fun `should gather a customer travel data appropriately`() {
        // GIVEN
        val customers = listOf(
            Customer(
                1, listOf(
                    Travel(G to A, 897),
                    Travel(C to F, 7849)
                )
            ),
            Customer(2, listOf(Travel(H to I, 9873)))
        )

        // WHEN
        val computed = customers.computeToOverview()

        // THEN
        assertThat(computed)
            .isEqualToNormalizingWhitespace((javaClass::getResource)("/output/BasicTestCase.json").readText())
    }

}