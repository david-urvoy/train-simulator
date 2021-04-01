package fr.train.api

import fr.train.domain.Customer
import fr.train.domain.Station
import fr.train.domain.Travel

data class CheckIn(val unixTimestamp: Long, val customerId: Int, val station: Station)

data class TravelInput(val taps: List<CheckIn>)

fun TravelInput.toCustomers(): List<Customer> = taps
    .sortedBy { it.unixTimestamp }
    .groupBy { it.customerId }
    .map { (key: Int, value: List<CheckIn>) -> Customer(key, value.travels()) }

fun List<CheckIn>.travels(): List<Travel> = chunked(2).map { it[0].station to it[1].station }