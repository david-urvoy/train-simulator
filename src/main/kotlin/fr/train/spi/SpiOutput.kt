package fr.train.spi

import fr.train.domain.*

data class SpiOutput(val customerSummaries: List<CustomerTravels>)

data class CustomerTravels(val customerId: Int, val totalCostInCents: Int, val trips: List<Trip>)

data class Trip(
    val stationStart: Station,
    val stationEnd: Station,
    val startedJourneyAt: Long,
    val costInCents: Int,
    val zoneFrom: Zone,
    val zoneTo: Zone
)

fun Travel.format(): Trip = closestZones().let {
    Trip(
        stationStart = first,
        stationEnd = second,
        startedJourneyAt = 0L,
        costInCents = travelPrice(),
        zoneFrom = it.first,
        zoneTo = it.second
    )
}

fun Customer.computeTravels(): CustomerTravels =
    CustomerTravels(
        customerId = id,
        totalCostInCents = dailyTravels.sumOf { it.travelPrice() },
        trips = dailyTravels.map { it.format() }
    )

