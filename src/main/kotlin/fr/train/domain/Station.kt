package fr.train.domain

enum class Station {
    A, B, C, D, E, F, G, H, I;

    fun zones() = Zone.values().filter { it.stations.contains(this) }

    override fun toString() = "station $name"
}

fun travelPrice(vararg stations: Station): Double =
    if (stations.size == 2) (stations[0] to stations[1]).travelPrice()
    else throw RuntimeException("Travel can only occur between a departure stations and a destination stations")

fun Pair<Station, Station>.travelPrice(): Double = closestZones().travelPrice()

fun Pair<Station, Station>.closestZones(): Pair<Zone, Zone> = first.zones()
    .foldRight(Zone.Zone1 to Zone.Zone4) { zone1: Zone, acc1: Pair<Zone, Zone> ->
        val cheapestTravel = second.zones()
            .foldRight(Zone.Zone1 to Zone.Zone4) { zone2: Zone, acc2: Pair<Zone, Zone> ->
                if ((zone1 to zone2).travelPrice() <= acc2.travelPrice())
                    zone1 to zone2
                else acc2
            }
        if (cheapestTravel.travelPrice() <= acc1.travelPrice())
            cheapestTravel
        else acc1
    }
