package fr.train.domain

/**
 * Representation of a Subway station
 */
enum class Station {
    A, B, C, D, E, F, G, H, I;

    fun zones() = Zone.values().filter { it.stations.contains(this) }
}
