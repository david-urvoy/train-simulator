package fr.train.api

import fr.train.domain.Customer
import fr.train.domain.Station.*
import fr.train.domain.Travel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TravelUnitTest {

    @Test
    fun `should convert TravelInputs to domain Customers appropriately`() {
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
        val input = SubwayData(
            listOf(
                CheckIn(2425, 1, A),
                CheckIn(7849, 1, C),
                CheckIn(9873, 2, H),
                CheckIn(897, 1, G),
                CheckIn(81192, 2, I),
                CheckIn(9877, 1, F)
            )
        )

        // WHEN
        val customers = input.toCustomers()

        // THEN
        assertThat(customers)
            .isEqualTo(expected)
            .usingRecursiveFieldByFieldElementComparator()
    }

}