package fr.train.domain

/**
 * Representation of a Travel between two [Station]s on the network.
 */
data class Travel(val stations: Pair<Station, Station>, val startTime: Long) {

    /**
     * Compute the travel price using the Zones pricing.
     */
    fun travelPrice(): Int = closestZones().travelPrice()

    /**
     * Compute the Pair of the closest [Zone]s for the Pair of [Station]s of this Travel, considering a Station may belong to two [Zone]s.
     */
    fun closestZones(): Pair<Zone, Zone> = stations.first.zones()
        .foldRight(mostExpensiveTravel) { from, acc1 ->
            stations.second.zones()
                .foldRight(mostExpensiveTravel) { to, acc2 -> (from to to) orIfCheaper acc2 }
                .let { it orIfCheaper acc1 }
        }

}

