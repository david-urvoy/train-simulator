package fr.train.spi

import com.google.gson.*
import fr.train.core.customersType
import fr.train.core.withExtensions
import fr.train.domain.Customer
import fr.train.domain.Travel
import java.lang.reflect.Type

/**
 * Serializes a List of [Customer]s to an overview of their travels.
 */
class OverviewSerializer : JsonSerializer<List<Customer>> {

    override fun serialize(customers: List<Customer>, p1: Type?, p2: JsonSerializationContext?) = JsonObject()
        .apply {
            add("customerSummaries", Gson().toJsonTree(customers
                .map { it.computeTravels() }
            ))
        }

    /**
     * Converts a Customer domain entity to the overview representation of his Travels.
     */
    private fun Customer.computeTravels(): JsonElement = JsonObject()
        .also {
            it.addProperty("customerId", id)
            it.addProperty("totalCostInCents", dailyTravels.sumOf { travel -> travel.travelPrice() })
            it.add("trips", Gson().toJsonTree(dailyTravels.map { travel -> travel.format() }))
        }


    /**
     * Aggregates all data (stations, zones, pricing, start time) related to a [Travel] to convert to an overview representation.
     */
    private fun Travel.format(): JsonElement = closestZones().let {
        JsonObject()
            .apply {
                addProperty("stationStart", stations.first.toString())
                addProperty("stationEnd", stations.second.toString())
                addProperty("startedJourneyAt", startTime)
                addProperty("costInCents", travelPrice())
                addProperty("zoneFrom", it.first.ordinal + 1)
                addProperty("zoneTo", it.second.ordinal + 1)
            }
    }

}

/**
 * Extension function of a List of [Customer]s that computes it to a JSON overview of the train network travels.
 */
fun List<Customer>.computeToOverview(serializer: JsonSerializer<*> = OverviewSerializer()): String = GsonBuilder()
    .withExtensions(serializer, { setPrettyPrinting() })
    .toJson(this, customersType)
