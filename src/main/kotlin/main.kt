import com.google.gson.Gson
import fr.train.api.SubwayData
import fr.train.api.toCustomers
import fr.train.spi.SubwayOverview
import fr.train.spi.computeTravels
import java.io.File

fun main(args: Array<String>) {

    when(args.size) {
        0, 1 -> throw RuntimeException("Please, provide both the Input and Output files as command line arguments.")
        in 3..Int.MAX_VALUE -> println("I only need two arguments. I don't know what you provided after that, but I will just ignore them.")
    }

    Gson().fromJson(
        File(args[0])
            .readText(), SubwayData::class.java
    )
        .run { SubwayOverview(toCustomers().map { it.computeTravels() }) }
        .let { Gson().toJson(it) }
        .apply {
            File(args[1]).writeText(this.toString())
        }

}
