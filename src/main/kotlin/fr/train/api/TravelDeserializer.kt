package fr.train.api

import com.google.gson.*
import fr.train.core.LostTravellerException
import fr.train.core.customersType
import fr.train.core.withExtensions
import fr.train.domain.Customer
import fr.train.domain.Station
import fr.train.domain.Travel
import java.lang.reflect.Type

/**
 * Deserializes a Json Array of the travels input into a List of the domain [Customer]s
 */
class TravelDeserializer : JsonDeserializer<List<Customer>> {
    override fun deserialize(json: JsonElement, p1: Type?, p2: JsonDeserializationContext?) = json.asJsonObject
        .getAsJsonArray("taps")
        .sortedBy { it.asJsonObject.getAsJsonPrimitive("unixTimestamp").asLong }
        .groupBy { it.asJsonObject.getAsJsonPrimitive("customerId").asInt }
        .map { (key: Int, value: List<JsonElement>) -> Customer(key, value.computeTravels()) }

    private fun List<JsonElement>.computeTravels(): List<Travel> =
        if (this.size % 2 == 0)
            chunked(2).map {
                val from: Station = Station.valueOf(it[0].asJsonObject.get("station").asString)
                val to: Station = Station.valueOf(it[1].asJsonObject.get("station").asString)
                Travel(from to to, it[0].asJsonObject.getAsJsonPrimitive("unixTimestamp").asLong)
            }
        else throw LostTravellerException()

}

/**
 * Converts the provided Json String to a List of [Customer]s
 */
fun parseJson(text: String, deserializer: JsonDeserializer<*> = TravelDeserializer()): List<Customer> = try {
    GsonBuilder().withExtensions(deserializer)
        .fromJson(text, customersType)
} catch (e: JsonSyntaxException) {
    throw RuntimeException("Invalid JSON !", e)
}