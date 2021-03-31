import fr.train.domain.Zone
import java.io.File

fun main(args: Array<String>) {

    File("C:\\Users\\david\\development\\test.json")
        .forEachLine {
            println(it)
        }
    Zone.values().onEach { println(it) }
}
