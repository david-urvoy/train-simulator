package fr.train.spi

import fr.train.domain.Customer
import fr.train.domain.Station.*
import fr.train.domain.Travel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DailyTravelUnitTest {

    @Test
    fun `should gather a customer travel data appropriately`() {
        // GIVEN
        val expected = DailyTravels(
            1, 520, listOf(
                Trip(A, F, 1, 280, 1, 3),
                Trip(B, C, 5, 240, 1, 2)
            )
        )

        // WHEN
        val computed = Customer(
            1, listOf(
                Travel(A to F, 1),
                Travel(B to C, 5)
            )
        ).computeTravels()

        // THEN
        assertThat(computed).isEqualTo(expected)
            .usingRecursiveComparison()
    }

}