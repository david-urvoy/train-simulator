package fr.train.spi

import fr.train.domain.Station
import fr.train.domain.Travel

/**
 * Representation of a Customer's travel with aggregated data (stations, zones, pricing, time).
 */
data class Trip(
    val stationStart: Station,
    val stationEnd: Station,
    val startedJourneyAt: Long,
    val costInCents: Int,
    val zoneFrom: Int,
    val zoneTo: Int
)

/**
 * Aggregates all data (stations, zones, pricing, start time) related to a [Travel] to convert to an overview representation.
 */
fun Travel.format(): Trip = closestZones().let {
    Trip(
        stationStart = stations.first,
        stationEnd = stations.second,
        startedJourneyAt = startTime,
        costInCents = travelPrice(),
        zoneFrom = it.first.ordinal + 1,
        zoneTo = it.second.ordinal + 1
    )
}