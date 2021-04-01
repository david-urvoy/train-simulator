package fr.train.domain

import fr.train.core.containsOnly
import fr.train.domain.Station.*
import fr.train.domain.Zone.*

/**
 * Representation of a city Zone delimiting
 */
enum class Zone(vararg stations: Station) {
    Zone1(A, B),
    Zone2(C, D, E),
    Zone3(C, E, F),
    Zone4(F, G, H, I);

    val stations: Set<Station>

    init {
        this.stations = setOf(*stations)
    }

}

val mostExpensiveTravel = Zone1 to Zone4

fun Pair<Zone, Zone>.travelPrice(): Int = when {
    containsOnly(Zone1, Zone2) -> 240
    containsOnly(Zone3, Zone4) -> 200
    containsOnly(Zone1, Zone2, Zone3) -> 280
    containsOnly(Zone1, Zone2, Zone4) -> 300
    else -> throw RuntimeException("There is at least one unknown zone: $this")
}

infix fun Pair<Zone, Zone>.orIfCheaper(other: Pair<Zone, Zone>) =
    (if (travelPrice() <= other.travelPrice()) this else other)
