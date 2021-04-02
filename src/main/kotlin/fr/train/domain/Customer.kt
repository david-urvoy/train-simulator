package fr.train.domain

/**
 * Representation of a Customer using the train network.
 */
data class Customer(val id: Int, val dailyTravels: List<Travel>)
