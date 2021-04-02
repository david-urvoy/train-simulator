package fr.train.spi

import fr.train.domain.Customer

/**
 * Representation of the overview of the Travels that a Customer did throughout the day.
 */
data class DailyTravels(val customerId: Int, val totalCostInCents: Int, val trips: List<Trip>)

/**
 * Converts a Customer domain entity to the overview representation of his Travels.
 */
fun Customer.computeTravels(): DailyTravels = DailyTravels(
    customerId = id,
    totalCostInCents = dailyTravels.sumOf { it.travelPrice() },
    trips = dailyTravels.map { it.format() }
)