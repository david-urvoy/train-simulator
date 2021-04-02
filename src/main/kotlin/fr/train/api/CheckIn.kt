package fr.train.api

import fr.train.core.LostTravellerException
import fr.train.domain.Station
import fr.train.domain.Travel

/**
 * Representation of a Customer CheckIn, a point in time when this Customer was registered at a Station.
 */
data class CheckIn(val unixTimestamp: Long, val customerId: Int, val station: Station)

/**
 * Splits a list of customer [CheckIn]s at stations to establish starting and ending points and define a list of [Travel].
 *
 * Throws [LostTravellerException] if the list of checkins is not even, which means a traveller entered the subway network but did not leave.
 */
fun List<CheckIn>.computeTravels(): List<Travel> =
    if (size % 2 == 0)
        chunked(2).map { Travel(it[0].station to it[1].station, it[0].unixTimestamp) }
    else throw LostTravellerException()
