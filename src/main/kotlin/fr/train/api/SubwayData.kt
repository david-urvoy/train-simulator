package fr.train.api

import fr.train.domain.Customer

/**
 * Representation of the data gathered from the subway gateways throughout the day.
 */
data class SubwayData(val taps: List<CheckIn>)

/**
 * Converts the raw subway data to a set of domain Customers data
 */
fun SubwayData.toCustomers(): List<Customer> = taps
    .sortedBy { it.unixTimestamp }
    .groupBy { it.customerId }
    .map { (key: Int, value: List<CheckIn>) -> Customer(key, value.computeTravels()) }
