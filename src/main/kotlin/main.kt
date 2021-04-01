import com.google.gson.Gson
import fr.train.api.TravelInput

fun main(args: Array<String>) {

//    File("C:\\Users\\david\\development\\test.json")
//        .forEachLine {
//            println(it)
//        }

    val input = Gson().fromJson(Thread.currentThread().contextClassLoader.getResource("CandidateInputExample.json")?.readText(), TravelInput::class.java )

    print(input)

}
