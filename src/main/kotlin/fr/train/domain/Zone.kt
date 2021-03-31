package fr.train.domain

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

private fun Pair<Zone, Zone>.containsOnly(vararg zones: Zone) = zones.contains(first) && zones.contains(second)

fun computeTravel(zones: Pair<Zone, Zone>): Double = when {
    zones.containsOnly(Zone.Zone1, Zone.Zone2) -> 2.4
    zones.containsOnly(Zone.Zone3, Zone.Zone4) -> 2.0
    zones.containsOnly(Zone.Zone1, Zone.Zone2, Zone.Zone3) -> 2.8
    zones.containsOnly(Zone.Zone1, Zone.Zone2, Zone.Zone4) -> 3.0
    else -> throw RuntimeException("There is at least one unknown zone: $zones")
}

fun computeTravel(vararg zones: Zone) = if (zones.size == 2) computeTravel(zones[0] to zones[1])
else throw RuntimeException("Travel can only occur between a departure zone and a destination zone")
