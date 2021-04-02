package fr.train.api

import com.google.gson.Gson
import fr.train.spi.computeTravels
import net.joshka.junit.json.params.JsonFileSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import javax.json.JsonObject

class SubwayIntegrationTest {

    @ParameterizedTest
    @JsonFileSource(resources = ["/CandidateInputExample.json"])
    fun read(input: JsonObject) {
        Gson().fromJson(input.toString(), SubwayData::class.java)
            .let { Gson().toJson(it.toCustomers().map { customer -> customer.computeTravels() }) }
            .also { print(it) }
    }

    @ParameterizedTest
    @JsonFileSource(resources = ["/CompleteTestCase.json"])
    fun `dead traveller`(input: JsonObject) {
        Gson().fromJson(input.toString(), SubwayData::class.java)
            .let { Gson().toJson(it.toCustomers().map { customer -> customer.computeTravels() }) }
            .also { print(it) }
            .also { assertThat(it).isEqualTo("[{\"customerId\":1,\"totalCostInCents\":1460,\"trips\":[{\"stationStart\":\"A\",\"stationEnd\":\"B\",\"startedJourneyAt\":246343,\"costInCents\":240,\"zoneFrom\":1,\"zoneTo\":1},{\"stationStart\":\"C\",\"stationEnd\":\"A\",\"startedJourneyAt\":2467436,\"costInCents\":240,\"zoneFrom\":2,\"zoneTo\":1},{\"stationStart\":\"C\",\"stationEnd\":\"F\",\"startedJourneyAt\":2467438,\"costInCents\":200,\"zoneFrom\":3,\"zoneTo\":3},{\"stationStart\":\"F\",\"stationEnd\":\"C\",\"startedJourneyAt\":2467449,\"costInCents\":200,\"zoneFrom\":3,\"zoneTo\":3},{\"stationStart\":\"A\",\"stationEnd\":\"F\",\"startedJourneyAt\":2467476,\"costInCents\":280,\"zoneFrom\":1,\"zoneTo\":3},{\"stationStart\":\"B\",\"stationEnd\":\"H\",\"startedJourneyAt\":2469996,\"costInCents\":300,\"zoneFrom\":1,\"zoneTo\":4}]}]") }
    }

}