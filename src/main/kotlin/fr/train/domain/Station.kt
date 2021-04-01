package fr.train.domain

enum class Station {
    A, B, C, D, E, F, G, H, I;

    fun zones() = Zone.values().filter { it.stations.contains(this) }

    override fun toString() = "station $name"
}

fun Pair<Station, Station>.travelPrice(): Int = closestZones().travelPrice()

fun Pair<Station, Station>.closestZones(): Pair<Zone, Zone> = first.zones()
    .foldRight(mostExpensiveTravel) { from, acc1 ->
        second.zones()
            .foldRight(mostExpensiveTravel) { to, acc2 -> (from to to) orIfCheaper acc2 }
            .let { it orIfCheaper acc1 }
    }
