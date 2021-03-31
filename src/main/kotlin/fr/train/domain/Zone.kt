package fr.train.domain

import fr.train.core.containsOnly
import fr.train.domain.Station.*

enum class Zone(vararg stations: Station) {
    Zone1(A, B),
    Zone2(C, D, E),
    Zone3(C, E, F),
    Zone4(F, G, H, I);

    val stations: Set<Station>

    init {
        this.stations = setOf(*stations)
    }

    override fun toString(): String {
        return "$name: $stations"
    }

}

fun Pair<Zone, Zone>.travelPrice(): Double = when {
    containsOnly(Zone.Zone1, Zone.Zone2) -> 2.4
    containsOnly(Zone.Zone3, Zone.Zone4) -> 2.0
    containsOnly(Zone.Zone1, Zone.Zone2, Zone.Zone3) -> 2.8
    containsOnly(Zone.Zone1, Zone.Zone2, Zone.Zone4) -> 3.0
    else -> throw RuntimeException("There is at least one unknown zone: $this")
}

fun computeTravelPrice(vararg zones: Zone): Double = if (zones.size == 2) (zones[0] to zones[1]).travelPrice()
else throw RuntimeException("Travel can only occur between a departure zone and a destination zone")
