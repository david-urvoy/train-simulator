import fr.train.api.parseJson
import fr.train.spi.computeToOverview
import java.io.File

fun main(args: Array<String>) {

    when (args.size) {
        0, 1 -> throw RuntimeException("Please, provide both the Input and Output files as command line arguments.")
        in 3..Int.MAX_VALUE -> println("I only need two arguments. I don't know what you provided after that, but I will just ignore them.")
    }

    val customers = parseJson(File(args[0]).readText())

    customers.computeToOverview()
        .also { File(args[1]).writeText(it) }

}
